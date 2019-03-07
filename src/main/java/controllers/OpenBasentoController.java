package controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import items.*;
import items.sparql.SingleTender;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.RepositoryConnection;
import org.eclipse.rdf4j.repository.sparql.SPARQLRepository;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@ResponseBody
public class OpenBasentoController {

    private Log logger = LogFactory.getLog(OpenBasentoController.class);

    @org.springframework.beans.factory.annotation.Value( "${sparql.endpoint}" )
    private String endpointURL;

    private SPARQLRepository sparqlEndpoint;

    private static final ThreadLocal<SimpleDateFormat> SPARQL_DATE = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-mm-dd"));
    private static  final ThreadLocal<SimpleDateFormat> ISO_8601 = ThreadLocal.withInitial(
            () -> new SimpleDateFormat("yyyy-MM-dd'T'HH:mm'Z'"));
    private static final ThreadLocal<DecimalFormat> CURRENCY_FORMATTER =
            ThreadLocal.withInitial(() -> {
                DecimalFormatSymbols symbols = new DecimalFormatSymbols();
                symbols.setDecimalSeparator(',');
                DecimalFormat format = new DecimalFormat();
                format.setDecimalFormatSymbols(symbols);
                return format;
            });

    @PostConstruct
    public void init(){
        sparqlEndpoint = new SPARQLRepository(endpointURL);
        sparqlEndpoint.initialize();
    }

    @RequestMapping(value = "/tender/{tenderId}",  produces = { "application/json", "application/xml" })
    @CrossOrigin()
    @ResponseBody
    public Tender selectTender(@PathVariable("tenderId") String id) {
        String apiQuery = String.format(SparqlQueries.SINGLE_TENDER, id);

        List<SingleTender> singleTenders = executeQuery(apiQuery, SingleTender.class);
        if (singleTenders == null || singleTenders.size() == 0) return null;

        return pojoToOCS(singleTenders.get(0));
    }


    @RequestMapping(value = "/company/{companyName}",  produces = { "application/json", "application/xml" })
    @CrossOrigin()
    @ResponseBody
    public List<Tender> selectCompanyTender(@PathVariable("companyName") String companyName){
        String apiQuery = String.format(SparqlQueries.COMPANY_TENDERS, companyName);

        List<SingleTender> tenderList = executeQuery(apiQuery, SingleTender.class);

        return tenderList != null ? tenderList.parallelStream()
                .map(st -> pojoToOCS(st)).collect(Collectors.toList()) : null;
    }

    @RequestMapping(value = "/all",  produces = { "application/json", "application/xml" })
    @CrossOrigin()
    @ResponseBody
    public List<Tender> selectAllTenders(){
        String apiQuery = SparqlQueries.ALL;

        List<SingleTender> tenderList = executeQuery(apiQuery, SingleTender.class);
        return tenderList != null ? tenderList.parallelStream()
                .map(st -> pojoToOCS(st)).collect(Collectors.toList()) : null;
    }

    /**
     *
     * @param apiQuery sparql query used by the API
     * @param c class of the POJO to return
     * @param <T> Pojo to return
     * @return SPARQL query result as List of POJOs of the class provided as parameter
     */
    private <T> List<T> executeQuery(String apiQuery, Class<T> c){
        try (RepositoryConnection con = sparqlEndpoint.getConnection()) {

            TupleQuery tupleQuery = con.prepareTupleQuery(QueryLanguage.SPARQL, apiQuery);
            TupleQueryResult result = tupleQuery.evaluate();

            return sparqlToPojo(result, c);

        } catch (IOException e) {
            logger.error(e);
        }
        return null;
    }

    /**
     *
     * @param result result of the SPARQL query
     * @param c class of the POJO to return
     * @param <T> Pojo to return
     * @return SPARQL query result as List of POJOs of the class provided as parameter
     * @throws IOException
     */
    private <T> List<T> sparqlToPojo(TupleQueryResult result, Class<T> c) throws IOException {
        List<String> bindingNames = result.getBindingNames();
        List<T> response = new LinkedList<>();

        ObjectMapper mapper = new ObjectMapper();

        while (result.hasNext()){
            BindingSet bindingSet = result.next();
            JSONObject j = new JSONObject();
            bindingNames.forEach(b -> {
                org.eclipse.rdf4j.model.Value value = bindingSet.getValue(b);
                String s = value != null ? value.stringValue() : null;
                j.put(b, s);
            });

            String jString = j.toString();  // conversion to string seems pointless but trying to convert directly from
            // from JSON makes all the fields disappear and a new one called "empty"
            // appears
            T t = mapper.readValue(jString, c);
            response.add(t);
        }
        return response;
    }

    /**
     * Converts the POJO obtained from a sparql query in a JSON compliant with the Open Contracting Standard
     * @param st pojo representing a Tender
     * @return OCS representation of the tender object
     */
    private Tender pojoToOCS(SingleTender st) {
        String id = st.getTenderId();
        String description = st.getTenderDescription();
        String status = st.getStatoContenzioso();

        //start date must be compliant to ISO 8601
        String startDate = st.getStartDate();
        String isoDate = null;
        try {
            Date d = SPARQL_DATE.get().parse(startDate);
            isoDate = ISO_8601.get().format(d);
        } catch (ParseException e){
            logger.error(e);
        }

        Period contractPeriod = new Period(isoDate, st.getContractDuration());

        BigDecimal expense = null;
        Map<String, Value> categoryExpenseMap = new HashMap<>();
        try {
            expense = BigDecimal.valueOf(CURRENCY_FORMATTER.get().parse(st.getTotalCost()).doubleValue());
            for (String costByCat : st.getCostByCategory().split("\\|")) {
                String[] split = costByCat.split("=");
                Value cost = new Value(BigDecimal.valueOf(CURRENCY_FORMATTER.get().parse(split[1]).doubleValue()));
                categoryExpenseMap.put(split[0], cost);
            }
        } catch (ParseException e) {
            logger.error(e);

        }
        Value value = new Value(expense);

        Category mainProcurementCategory = new Category(st.getTenderMainCategoryCode(), st.getTenderMainCategoryName(), categoryExpenseMap.get(st.getTenderMainCategoryCode()));
        List<Category> tenderCategories = new ArrayList<>();
        String[] splitCodes = st.getTenderCategoryCodes().split("\\|");
        String[] splitNames = st.getTenderCategoryNames().split("\\|");
        if (splitCodes.length > 0 ||splitNames.length > 0){
            for (int i = 0; i < splitCodes.length ; i++) {
                if (splitCodes[i] != null && mainProcurementCategory.getCode() != null && !splitCodes[i].trim().equals(mainProcurementCategory.getCode().trim())){
                    Category category = new Category(splitCodes[i], splitNames[i], categoryExpenseMap.get(splitCodes[i]));
                    tenderCategories.add(category);
                }

            }
        }

        //company
        Address address = new Address(st.getTendererRegion(), st.getTendererProvince(), st.getTendererMunicipality());

        List<Category> companyCategories = new LinkedList<>();
        List<String> companyCategoriesCodes = Arrays.asList(st.getTendererCategoryCodes().split("\\|"));
        List<String> companyCategoriesNames = Arrays.asList(st.getTendererCategoryNames().split("\\|"));
        for (int i = 0; i < companyCategoriesCodes.size() ; i++) {
            if (companyCategoriesCodes.get(i).length() > 0){
                companyCategories.add(new Category(companyCategoriesCodes.get(i), companyCategoriesNames.get(i)));
            }
        }

        OrganizationReference company = new OrganizationReference(st.getTendererName(), address, companyCategories);

        List<Candidate> candidates = Arrays.stream(st.getCandidates().split("\\|"))
                .map(c -> getCandidate(c))
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        List<AwardCriteriaDetail> awardCriteriaDetails = new ArrayList<>();
        if (st.getTassoRisparmio() != null){
            awardCriteriaDetails.add(new AwardCriteriaDetail("Tasso Risparmio", st.getTassoRisparmio()));
        }
        if (st.getPercentageRibasso() != null){
            awardCriteriaDetails.add(new AwardCriteriaDetail("Percentuale Ribasso", st.getPercentageRibasso()));
        }
        return new Tender(id, description, status, mainProcurementCategory, contractPeriod, value,
                company, st.getMunicipality(), st.getTipoAppalto(), st.getTipoIntervento(),
                awardCriteriaDetails, tenderCategories, candidates);
    }

    /**
     * Function to convert string representation of the candidate into Object representation
     * @param c String representation of the Candidate
     * @return Object representation of the candidate
     */
    private Candidate getCandidate(String c){
        String[] split = c.split("=");
        String cleanedName = cleanString(split[0]);

        if (cleanedName == null){
            return null;
        }

        if (split.length > 1) return new Candidate(cleanedName, Double.valueOf(split[1]));
        else return new Candidate(cleanedName);
    }

    /**
     * Function to clean strings and nullify empty strings
     *
     * @param s string to be cleaned
     * @return cleaned function or null if the string was empty
     */
    private static String cleanString(String s){
        if (s == null) return null;

        String trimmed = s.trim();
        if (trimmed.length() == 0) return null;

        return trimmed.replace("\n", " ");
    }
}
