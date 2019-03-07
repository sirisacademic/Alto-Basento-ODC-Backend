package items;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Tender implements Serializable {
    String id;
    String title;
    String description;
    String status;
    OrganizationReference supplier;
    List<Item> items;
    Value value;
    Value minValue;
    String procurementMethod;
    String procurementMethodDetails;
    String procurementMethodRationale;
    Category mainProcurementCategory;
    List<Category> additionalProcurementCategories;
    String awardCriteria;
    List<AwardCriteriaDetail> awardCriteriaDetails;
    List<String> submissionMethod;
    String submissionMethodDetails;
    Period tenderPeriod;
    Period enquiryPeriod;
    Boolean hasEnquiries;
    String eligibilityCriteria;
    Period awardPeriod;
    Period contractPeriod;
    Integer numberOfTenderers;
    List<OrganizationReference> tenderers;
    List<Document> documents;
    List<Milestone> milestones;
    List<Amendment> amendments;
    List<Candidate> candidates;

    //extra field
    String municipality;
    String tipoIntervento;



    public Tender(String id, String description, String status, Category mainProcurementCategory, Period contractPeriod, Value value, OrganizationReference company, String municipality, String tipoAppalto, String tipoIntervento, List<AwardCriteriaDetail> awardCriteriaDetails, List<Category> tenderCategories, List<Candidate> candidates) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.supplier = company;
        this.value = value;
        this.mainProcurementCategory = mainProcurementCategory;
        this.contractPeriod = contractPeriod;
        this.municipality = municipality;
        this.procurementMethod = tipoAppalto;
        this.tipoIntervento = tipoIntervento;
        this.awardCriteriaDetails = awardCriteriaDetails;
        this.additionalProcurementCategories = tenderCategories;
        this.candidates = candidates;
        this.awardCriteria = "Rated Criteria";
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public OrganizationReference getSupplier() {
        return supplier;
    }

    public void setSupplier(OrganizationReference organizationReference) {
        this.supplier = organizationReference;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public Value getValue() {
        return value;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    public Value getMinValue() {
        return minValue;
    }

    public void setMinValue(Value minValue) {
        this.minValue = minValue;
    }

    public String getProcurementMethod() {
        return procurementMethod;
    }

    public void setProcurementMethod(String procurementMethod) {
        this.procurementMethod = procurementMethod;
    }

    public String getProcurementMethodDetails() {
        return procurementMethodDetails;
    }

    public void setProcurementMethodDetails(String procurementMethodDetails) {
        this.procurementMethodDetails = procurementMethodDetails;
    }

    public String getProcurementMethodRationale() {
        return procurementMethodRationale;
    }

    public void setProcurementMethodRationale(String procurementMethodRationale) {
        this.procurementMethodRationale = procurementMethodRationale;
    }

    public Category getMainProcurementCategory() {
        return mainProcurementCategory;
    }

    public void setMainProcurementCategory(Category mainProcurementCategory) {
        this.mainProcurementCategory = mainProcurementCategory;
    }

    public List<Category> getAdditionalProcurementCategories() {
        return additionalProcurementCategories;
    }

    public void setAdditionalProcurementCategories(List<Category> additionalProcurementCategories) {
        this.additionalProcurementCategories = additionalProcurementCategories;
    }

    public String getAwardCriteria() {
        return awardCriteria;
    }

    public void setAwardCriteria(String awardCriteria) {
        this.awardCriteria = awardCriteria;
    }

    public List<String> getSubmissionMethod(){
        return submissionMethod;
    }

    public void setSubmissionMethod(List<String> submissionMethod) {
        this.submissionMethod = submissionMethod;
    }

    public String getSubmissionMethodDetails() {
        return submissionMethodDetails;
    }

    public void setSubmissionMethodDetails(String submissionMethodDetails) {
        this.submissionMethodDetails = submissionMethodDetails;
    }

    public Period getTenderPeriod() {
        return tenderPeriod;
    }

    public void setTenderPeriod(Period tenderPeriod) {
        this.tenderPeriod = tenderPeriod;
    }

    public Period getEnquiryPeriod() {
        return enquiryPeriod;
    }

    public void setEnquiryPeriod(Period enquiryPeriod) {
        this.enquiryPeriod = enquiryPeriod;
    }

    public Boolean getHasEnquiries() {
        return hasEnquiries;
    }

    public void setHasEnquiries(Boolean hasEnquiries) {
        this.hasEnquiries = hasEnquiries;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public Period getAwardPeriod() {
        return awardPeriod;
    }

    public void setAwardPeriod(Period awardPeriod) {
        this.awardPeriod = awardPeriod;
    }

    public Period getContractPeriod() {
        return contractPeriod;
    }

    public void setContractPeriod(Period contractPeriod) {
        this.contractPeriod = contractPeriod;
    }

    public Integer getNumberOfTenderers() {
        return numberOfTenderers;
    }

    public void setNumberOfTenderers(Integer numberOfTenderers) {
        this.numberOfTenderers = numberOfTenderers;
    }

    public List<OrganizationReference> getTenderers() {
        return tenderers;
    }

    public void setTenderers(List<OrganizationReference> tenderers) {
        this.tenderers = tenderers;
    }

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public List<Milestone> getMilestones() {
        return milestones;
    }

    public void setMilestones(List<Milestone> milestones) {
        this.milestones = milestones;
    }

    public List<Amendment> getAmendments() {
        return amendments;
    }

    public void setAmendments(List<Amendment> amendments) {
        this.amendments = amendments;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getTipoIntervento() {
        return tipoIntervento;
    }

    public void setTipoIntervento(String tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }

    public List<Candidate> getCandidates() {
        return candidates;
    }

    public void setCandidates(List<Candidate> candidates) {
        this.candidates = candidates;
    }

    public List<AwardCriteriaDetail> getAwardCriteriaDetails() {
        return awardCriteriaDetails;
    }

    public void setAwardCriteriaDetails(List<AwardCriteriaDetail> awardCriteriaDetails) {
        this.awardCriteriaDetails = awardCriteriaDetails;
    }
}

