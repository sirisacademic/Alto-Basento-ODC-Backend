package controllers;

/**
 * Class containing all the SPARQL queries used by the API
 */

class SparqlQueries {
    static String SINGLE_TENDER = "prefix : <http://unics.cloud/openbasento/ontology#>\n" +
            "prefix epo: <http://data.europa.eu/ePO/ontology#>\n" +
            "prefix vcard: <http://www.w3.org/2006/vcard/ns#>\n" +
            "prefix ubl: <http://docs.oasis-open.org/ubl#>\n" +
            "prefix ccts: <http://www.unece.org/cefact#>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "select \n" +
            "\t?tenderId \n" +
            "\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) as ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) as ?tipoIntervento) \n" +
            "\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t(sample(?totalCost) as ?totalCost)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryCode ; separator=\"|\") AS ?tenderCategoryCodes)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryName ; separator=\"|\") AS ?tenderCategoryNames)\n" +
            "\t(GROUP_CONCAT(DISTINCT CONCAT(?tenderCategoryCode,\"=\",?categoryCost) ; separator=\"|\") AS ?costByCategory)\n" +
            "\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) \n" +
            "\t(sample(?tendererMunicipality) as ?tendererMunicipality) (sample(?tendererProvince) as ?tendererProvince) \n" +
            "\t(sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t(sample(?tendererCategoryCodes) as ?tendererCategoryCodes) (sample(?tendererCategoryNames) as ?tendererCategoryNames)\n" +
            "\t(sample(?candidates) as ?candidates)\n" +
            "where {\n" +
            "\tselect\n" +
            "\t\t?tenderId \n" +
            "\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryCode ; separator=\"|\") AS ?tendererCategoryCodes)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryName ; separator=\"|\") AS ?tendererCategoryNames)\n" +
            "\t\t(sample(?candidates) as ?candidates)\n" +
            "\twhere {\n" +
            "\t\tselect\n" +
            "\t\t\t?tenderId \n" +
            "\t\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t\t?tendererCategoryCode ?tendererCategoryName\n" +
            "\t\t\t(GROUP_CONCAT(DISTINCT IF(BOUND(?candidateName),CONCAT(?candidateName,\"=\",?candidatePercentageRibasso),\"\") ; separator=\"|\") AS ?candidates)\n" +
            "\t\twhere {\n" +
            "\t\t\t?tender a epo:Tender .\n" +
            "\t\t\t?tender epo:appliesToLot ?lot .\n" +
            "\t\t\t?lot epo:belongsToProcurementProcedure ?procurementProc .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectDescription ?tenderDescription .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectIdentifier ?procurementProcId .\n" +
            "\t\t\t?procurementProcId ccts:identifierValue ?tenderId .\n" +
            "\t\t\t?procurementProc :municipality ?municipality .\n" +
            "\n" +
            "\t\t\t# Select the tender by its ID\n" +
            "\t\t\tFILTER (?tenderId = \"%s\")\n" +
            "\n" +
            "\t\t\t?lot :category ?tenderCategory .\n" +
            "\t\t\t?tenderCategory :categoryCode ?tenderCategoryCode .\n" +
            "\t\t\t?tenderCategory :categoryName ?tenderCategoryName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?lot :mainCategory ?tenderMainCategory .\n" +
            "\t\t\t\t?tenderMainCategory :categoryCode ?tenderMainCategoryCode .\n" +
            "\t\t\t\t?tenderMainCategory :categoryName ?tenderMainCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\t?tender epo:isReferedInContract ?contract .\n" +
            "\t\t\t?contract :startingDate ?startDate .\n" +
            "\t\t\t?contract :litigationStatus ?statoContenzioso .\n" +
            "\n" +
            "\t\t\t?contract epo:hasContractPurpose ?purpose .\n" +
            "\t\t\t?purpose epo:hasPurposeSubjectMatter ?tipoIntervento .\n" +
            "\t\t\t?purpose epo:usesContractNatureType ?contractNatureType .\n" +
            "\t\t\t?contractNatureType rdfs:label ?tipoAppalto .\n" +
            "\t\t\t?purpose epo:hasContractEstimatedDuration ?period .\n" +
            "\t\t\t?period ubl:durationMeasure ?mesure .\n" +
            "\t\t\t?mesure ccts:measureValue ?contractDuration .\n" +
            "\n" +
            "\t\t\t?contract epo:hasProcurementValue ?procVal .\n" +
            "\t\t\t?procVal epo:hasTotalAmount ?totalAmount .\n" +
            "\t\t\t?totalAmount ccts:amountValue ?totalCost .\n" +
            "\n" +
            "\t\t\t?contract :categorizedProcurementValue ?catProcVal .\n" +
            "\t\t\t?catProcVal :category ?tenderCategory .\n" +
            "\t\t\t?catProcVal epo:hasTotalAmount ?catTotalAmount .\n" +
            "\t\t\t?catTotalAmount ccts:amountValue ?categoryCost .\n" +
            "\n" +
            "\t\t\t?tender epo:tenderSubmittedBy ?tenderer .\n" +
            "\t\t\t?tenderer rdfs:label ?tendererName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?tender :tendererAddress ?address .\n" +
            "\t\t\t\t?address vcard:country-name ?tendererCountry .\n" +
            "\t\t\t\t?address vcard:locality ?tendererMunicipality .\n" +
            "\t\t\t\t?address :province ?tendererProvince .\n" +
            "\t\t\t\t?address vcard:region ?tendererRegion .\n" +
            "\t\t\t}\n" +
            "\t\t\toptional{\n" +
            "\t\t\t\t?tender :tendererCategory ?tendererCategory .\n" +
            "\t\t\t\t?tendererCategory :categoryCode ?tendererCategoryCode .\n" +
            "\t\t\t\t?tendererCategory :categoryName ?tendererCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?percentageRate a :PercentageRate .\n" +
            "\t\t\t\t?percentageRate epo:appliedToTender ?tender .\n" +
            "\t\t\t\t?percentageRate epo:hasCriterionPropertyGroup ?critPropGroup .\n" +
            "\t\t\t\t?critPropGroup epo:hasCriterionProperty ?critProp .\n" +
            "\t\t\t\t?critProp epo:asksForData ?critPropDatum .\n" +
            "\t\t\t\t?critPropDatum epo:hasValueAmount ?percentageRateAmount .\n" +
            "\t\t\t\t?percentageRateAmount ccts:amountValue ?percentageRibasso .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?candidateTender a epo:Tender .\n" +
            "\t\t\t\t?candidateTender epo:appliesToLot ?lot .\n" +
            "\t\t\t\t?candidateTender epo:tenderSubmittedBy ?candidateTenderer .\n" +
            "\t\t\t\t?candidateTenderer rdfs:label ?candidateName .\n" +
            "\t\t\t\tFILTER (?candidateName != ?tendererName)\n" +
            "\n" +
            "\t\t\t\toptional {\n" +
            "\t\t\t\t\t?candidatePercentageRate a :PercentageRate .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:appliedToTender ?candidateTender .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:hasCriterionPropertyGroup ?candidateCritPropGroup .\n" +
            "\t\t\t\t\t?candidateCritPropGroup epo:hasCriterionProperty ?candidateCritProp .\n" +
            "\t\t\t\t\t?candidateCritProp epo:asksForData ?candidateCritPropDatum .\n" +
            "\t\t\t\t\t?candidateCritPropDatum epo:hasValueAmount ?candidatePercentageRateAmount .\n" +
            "\t\t\t\t\t?candidatePercentageRateAmount ccts:amountValue ?candidatePercentageRibasso .\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName ?tendererCategoryCode ?tendererCategoryName\n" +
            "\t}\n" +
            "\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName \n" +
            "}\n" +
            "GROUP BY ?tenderId ";

    static String COMPANY_TENDERS = "prefix : <http://unics.cloud/openbasento/ontology#>\n" +
            "prefix epo: <http://data.europa.eu/ePO/ontology#>\n" +
            "prefix vcard: <http://www.w3.org/2006/vcard/ns#>\n" +
            "prefix ubl: <http://docs.oasis-open.org/ubl#>\n" +
            "prefix ccts: <http://www.unece.org/cefact#>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "select \n" +
            "\t?tenderId \n" +
            "\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) as ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) as ?tipoIntervento) \n" +
            "\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t(sample(?totalCost) as ?totalCost)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryCode ; separator=\"|\") AS ?tenderCategoryCodes)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryName ; separator=\"|\") AS ?tenderCategoryNames)\n" +
            "\t(GROUP_CONCAT(DISTINCT CONCAT(?tenderCategoryCode,\"=\",?categoryCost) ; separator=\"|\") AS ?costByCategory)\n" +
            "\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) \n" +
            "\t(sample(?tendererMunicipality) as ?tendererMunicipality) (sample(?tendererProvince) as ?tendererProvince) \n" +
            "\t(sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t(sample(?tendererCategoryCodes) as ?tendererCategoryCodes) (sample(?tendererCategoryNames) as ?tendererCategoryNames)\n" +
            "\t(sample(?candidates) as ?candidates)\n" +
            "where {\n" +
            "\tselect\n" +
            "\t\t?tenderId \n" +
            "\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryCode ; separator=\"|\") AS ?tendererCategoryCodes)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryName ; separator=\"|\") AS ?tendererCategoryNames)\n" +
            "\t\t(sample(?candidates) as ?candidates)\n" +
            "\twhere {\n" +
            "\t\tselect\n" +
            "\t\t\t?tenderId \n" +
            "\t\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t\t?tendererCategoryCode ?tendererCategoryName\n" +
            "\t\t\t(GROUP_CONCAT(DISTINCT IF(BOUND(?candidateName),CONCAT(?candidateName,\"=\",?candidatePercentageRibasso),\"\") ; separator=\"|\") AS ?candidates)\n" +
            "\t\twhere {\n" +
            "\t\t\t?tender a epo:Tender .\n" +
            "\t\t\t?tender epo:appliesToLot ?lot .\n" +
            "\t\t\t?lot epo:belongsToProcurementProcedure ?procurementProc .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectDescription ?tenderDescription .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectIdentifier ?procurementProcId .\n" +
            "\t\t\t?procurementProcId ccts:identifierValue ?tenderId .\n" +
            "\t\t\t?procurementProc :municipality ?municipality .\n" +
            "\n" +
            "\t\t\t?lot :category ?tenderCategory .\n" +
            "\t\t\t?tenderCategory :categoryCode ?tenderCategoryCode .\n" +
            "\t\t\t?tenderCategory :categoryName ?tenderCategoryName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?lot :mainCategory ?tenderMainCategory .\n" +
            "\t\t\t\t?tenderMainCategory :categoryCode ?tenderMainCategoryCode .\n" +
            "\t\t\t\t?tenderMainCategory :categoryName ?tenderMainCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\t?tender epo:isReferedInContract ?contract .\n" +
            "\t\t\t?contract :startingDate ?startDate .\n" +
            "\t\t\t?contract :litigationStatus ?statoContenzioso .\n" +
            "\n" +
            "\t\t\t?contract epo:hasContractPurpose ?purpose .\n" +
            "\t\t\t?purpose epo:hasPurposeSubjectMatter ?tipoIntervento .\n" +
            "\t\t\t?purpose epo:usesContractNatureType ?contractNatureType .\n" +
            "\t\t\t?contractNatureType rdfs:label ?tipoAppalto .\n" +
            "\t\t\t?purpose epo:hasContractEstimatedDuration ?period .\n" +
            "\t\t\t?period ubl:durationMeasure ?mesure .\n" +
            "\t\t\t?mesure ccts:measureValue ?contractDuration .\n" +
            "\n" +
            "\t\t\t?contract epo:hasProcurementValue ?procVal .\n" +
            "\t\t\t?procVal epo:hasTotalAmount ?totalAmount .\n" +
            "\t\t\t?totalAmount ccts:amountValue ?totalCost .\n" +
            "\n" +
            "\t\t\t?contract :categorizedProcurementValue ?catProcVal .\n" +
            "\t\t\t?catProcVal :category ?tenderCategory .\n" +
            "\t\t\t?catProcVal epo:hasTotalAmount ?catTotalAmount .\n" +
            "\t\t\t?catTotalAmount ccts:amountValue ?categoryCost .\n" +
            "\n" +
            "\t\t\t?tender epo:tenderSubmittedBy ?tenderer .\n" +
            "\t\t\t?tenderer rdfs:label ?tendererName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?tender :tendererAddress ?address .\n" +
            "\t\t\t\t?address vcard:country-name ?tendererCountry .\n" +
            "\t\t\t\t?address vcard:locality ?tendererMunicipality .\n" +
            "\t\t\t\t?address :province ?tendererProvince .\n" +
            "\t\t\t\t?address vcard:region ?tendererRegion .\n" +
            "\t\t\t}\n" +
            "\t\t\toptional{\n" +
            "\t\t\t\t?tender :tendererCategory ?tendererCategory .\n" +
            "\t\t\t\t?tendererCategory :categoryCode ?tendererCategoryCode .\n" +
            "\t\t\t\t?tendererCategory :categoryName ?tendererCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?percentageRate a :PercentageRate .\n" +
            "\t\t\t\t?percentageRate epo:appliedToTender ?tender .\n" +
            "\t\t\t\t?percentageRate epo:hasCriterionPropertyGroup ?critPropGroup .\n" +
            "\t\t\t\t?critPropGroup epo:hasCriterionProperty ?critProp .\n" +
            "\t\t\t\t?critProp epo:asksForData ?critPropDatum .\n" +
            "\t\t\t\t?critPropDatum epo:hasValueAmount ?percentageRateAmount .\n" +
            "\t\t\t\t?percentageRateAmount ccts:amountValue ?percentageRibasso .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?candidateTender a epo:Tender .\n" +
            "\t\t\t\t?candidateTender epo:appliesToLot ?lot .\n" +
            "\t\t\t\t?candidateTender epo:tenderSubmittedBy ?candidateTenderer .\n" +
            "\t\t\t\t?candidateTenderer rdfs:label ?candidateName .\n" +
            "\t\t\t\tFILTER (?candidateName != ?tendererName)\n" +
            "\n" +
            "\t\t\t\toptional {\n" +
            "\t\t\t\t\t?candidatePercentageRate a :PercentageRate .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:appliedToTender ?candidateTender .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:hasCriterionPropertyGroup ?candidateCritPropGroup .\n" +
            "\t\t\t\t\t?candidateCritPropGroup epo:hasCriterionProperty ?candidateCritProp .\n" +
            "\t\t\t\t\t?candidateCritProp epo:asksForData ?candidateCritPropDatum .\n" +
            "\t\t\t\t\t?candidateCritPropDatum epo:hasValueAmount ?candidatePercentageRateAmount .\n" +
            "\t\t\t\t\t?candidatePercentageRateAmount ccts:amountValue ?candidatePercentageRibasso .\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\t# Filter by tenderer name (equality or regex)\n" +
            "\t\t\tFILTER (EXISTS {\n" +
            "\t\t\t\t?filterTender a epo:Tender .\n" +
            "\t\t\t\t?filterTender epo:appliesToLot ?lot .\n" +
            "\t\t\t\t?filterTender epo:tenderSubmittedBy ?filterTenderer .\n" +
            "\t\t\t\t?filterTenderer rdfs:label ?filterTendererName .\n" +
            "\t\t\t\t# FILTER (?filterTendererName = \"Edil Bradanica Calcestruzzi srl unip.\")\n" +
            "\t\t\t\tFILTER (regex(?filterTendererName, \"%s\", \"i\"))\n" +
            "\t\t\t})\n" +
            "\t\t}\n" +
            "\t\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName ?tendererCategoryCode ?tendererCategoryName\n" +
            "\t}\n" +
            "\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName \n" +
            "}\n" +
            "GROUP BY ?tenderId ";

    static String ALL = "prefix : <http://unics.cloud/openbasento/ontology#>\n" +
            "prefix epo: <http://data.europa.eu/ePO/ontology#>\n" +
            "prefix vcard: <http://www.w3.org/2006/vcard/ns#>\n" +
            "prefix ubl: <http://docs.oasis-open.org/ubl#>\n" +
            "prefix ccts: <http://www.unece.org/cefact#>\n" +
            "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
            "select \n" +
            "\t?tenderId \n" +
            "\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) as ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) as ?tipoIntervento) \n" +
            "\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t(sample(?totalCost) as ?totalCost)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryCode ; separator=\"|\") AS ?tenderCategoryCodes)\n" +
            "\t(GROUP_CONCAT(DISTINCT ?tenderCategoryName ; separator=\"|\") AS ?tenderCategoryNames)\n" +
            "\t(GROUP_CONCAT(DISTINCT CONCAT(?tenderCategoryCode,\"=\",?categoryCost) ; separator=\"|\") AS ?costByCategory)\n" +
            "\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) \n" +
            "\t(sample(?tendererMunicipality) as ?tendererMunicipality) (sample(?tendererProvince) as ?tendererProvince) \n" +
            "\t(sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t(sample(?tendererCategoryCodes) as ?tendererCategoryCodes) (sample(?tendererCategoryNames) as ?tendererCategoryNames)\n" +
            "\t(sample(?candidates) as ?candidates)\n" +
            "where {\n" +
            "\tselect\n" +
            "\t\t?tenderId \n" +
            "\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryCode ; separator=\"|\") AS ?tendererCategoryCodes)\n" +
            "\t\t(GROUP_CONCAT(DISTINCT ?tendererCategoryName ; separator=\"|\") AS ?tendererCategoryNames)\n" +
            "\t\t(sample(?candidates) as ?candidates)\n" +
            "\twhere {\n" +
            "\t\tselect\n" +
            "\t\t\t?tenderId \n" +
            "\t\t\t(sample(?municipality) as ?municipality) (sample(?tenderDescription) AS ?tenderDescription) (sample(?startDate) as ?startDate)\n" +
            "\t\t\t(sample(?statoContenzioso) as ?statoContenzioso) (sample(?tipoIntervento) AS ?tipoIntervento) \n" +
            "\t\t\t(sample(?tipoAppalto) as ?tipoAppalto) (sample(?contractDuration) as ?contractDuration)\n" +
            "\t\t\t(sample(?totalCost) as ?totalCost)\n" +
            "\t\t\t?tenderCategoryCode ?tenderCategoryName (sample(?categoryCost) as ?categoryCost)\n" +
            "\t\t\t(sample(?tenderMainCategoryCode) as ?tenderMainCategoryCode) (sample(?tenderMainCategoryName) as ?tenderMainCategoryName)\n" +
            "\t\t\t(sample(?percentageRibasso) as ?percentageRibasso)\n" +
            "\t\t\t(sample(?tendererName) as ?tendererName) (sample(?tendererCountry) as ?tendererCountry) (sample(?tendererMunicipality) as ?tendererMunicipality)\n" +
            "\t\t\t(sample(?tendererProvince) as ?tendererProvince) (sample(?tendererRegion) as ?tendererRegion)\n" +
            "\t\t\t?tendererCategoryCode ?tendererCategoryName\n" +
            "\t\t\t(GROUP_CONCAT(DISTINCT IF(BOUND(?candidateName),CONCAT(?candidateName,\"=\",?candidatePercentageRibasso),\"\") ; separator=\"|\") AS ?candidates)\n" +
            "\t\twhere {\n" +
            "\t\t\t?tender a epo:Tender .\n" +
            "\t\t\t?tender epo:appliesToLot ?lot .\n" +
            "\t\t\t?lot epo:belongsToProcurementProcedure ?procurementProc .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectDescription ?tenderDescription .\n" +
            "\t\t\t?procurementProc epo:hasProcurementProjectIdentifier ?procurementProcId .\n" +
            "\t\t\t?procurementProcId ccts:identifierValue ?tenderId .\n" +
            "\t\t\t?procurementProc :municipality ?municipality .\n" +
            "\n" +
            "\t\t\t?lot :category ?tenderCategory .\n" +
            "\t\t\t?tenderCategory :categoryCode ?tenderCategoryCode .\n" +
            "\t\t\t?tenderCategory :categoryName ?tenderCategoryName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?lot :mainCategory ?tenderMainCategory .\n" +
            "\t\t\t\t?tenderMainCategory :categoryCode ?tenderMainCategoryCode .\n" +
            "\t\t\t\t?tenderMainCategory :categoryName ?tenderMainCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\t?tender epo:isReferedInContract ?contract .\n" +
            "\t\t\t?contract :startingDate ?startDate .\n" +
            "\t\t\t?contract :litigationStatus ?statoContenzioso .\n" +
            "\n" +
            "\t\t\t?contract epo:hasContractPurpose ?purpose .\n" +
            "\t\t\t?purpose epo:hasPurposeSubjectMatter ?tipoIntervento .\n" +
            "\t\t\t?purpose epo:usesContractNatureType ?contractNatureType .\n" +
            "\t\t\t?contractNatureType rdfs:label ?tipoAppalto .\n" +
            "\t\t\t?purpose epo:hasContractEstimatedDuration ?period .\n" +
            "\t\t\t?period ubl:durationMeasure ?mesure .\n" +
            "\t\t\t?mesure ccts:measureValue ?contractDuration .\n" +
            "\n" +
            "\t\t\t?contract epo:hasProcurementValue ?procVal .\n" +
            "\t\t\t?procVal epo:hasTotalAmount ?totalAmount .\n" +
            "\t\t\t?totalAmount ccts:amountValue ?totalCost .\n" +
            "\n" +
            "\t\t\t?contract :categorizedProcurementValue ?catProcVal .\n" +
            "\t\t\t?catProcVal :category ?tenderCategory .\n" +
            "\t\t\t?catProcVal epo:hasTotalAmount ?catTotalAmount .\n" +
            "\t\t\t?catTotalAmount ccts:amountValue ?categoryCost .\n" +
            "\n" +
            "\t\t\t?tender epo:tenderSubmittedBy ?tenderer .\n" +
            "\t\t\t?tenderer rdfs:label ?tendererName .\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?tender :tendererAddress ?address .\n" +
            "\t\t\t\t?address vcard:country-name ?tendererCountry .\n" +
            "\t\t\t\t?address vcard:locality ?tendererMunicipality .\n" +
            "\t\t\t\t?address :province ?tendererProvince .\n" +
            "\t\t\t\t?address vcard:region ?tendererRegion .\n" +
            "\t\t\t}\n" +
            "\t\t\toptional{\n" +
            "\t\t\t\t?tender :tendererCategory ?tendererCategory .\n" +
            "\t\t\t\t?tendererCategory :categoryCode ?tendererCategoryCode .\n" +
            "\t\t\t\t?tendererCategory :categoryName ?tendererCategoryName .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?percentageRate a :PercentageRate .\n" +
            "\t\t\t\t?percentageRate epo:appliedToTender ?tender .\n" +
            "\t\t\t\t?percentageRate epo:hasCriterionPropertyGroup ?critPropGroup .\n" +
            "\t\t\t\t?critPropGroup epo:hasCriterionProperty ?critProp .\n" +
            "\t\t\t\t?critProp epo:asksForData ?critPropDatum .\n" +
            "\t\t\t\t?critPropDatum epo:hasValueAmount ?percentageRateAmount .\n" +
            "\t\t\t\t?percentageRateAmount ccts:amountValue ?percentageRibasso .\n" +
            "\t\t\t}\n" +
            "\n" +
            "\t\t\toptional {\n" +
            "\t\t\t\t?candidateTender a epo:Tender .\n" +
            "\t\t\t\t?candidateTender epo:appliesToLot ?lot .\n" +
            "\t\t\t\t?candidateTender epo:tenderSubmittedBy ?candidateTenderer .\n" +
            "\t\t\t\t?candidateTenderer rdfs:label ?candidateName .\n" +
            "\t\t\t\tFILTER (?candidateName != ?tendererName)\n" +
            "\n" +
            "\t\t\t\toptional {\n" +
            "\t\t\t\t\t?candidatePercentageRate a :PercentageRate .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:appliedToTender ?candidateTender .\n" +
            "\t\t\t\t\t?candidatePercentageRate epo:hasCriterionPropertyGroup ?candidateCritPropGroup .\n" +
            "\t\t\t\t\t?candidateCritPropGroup epo:hasCriterionProperty ?candidateCritProp .\n" +
            "\t\t\t\t\t?candidateCritProp epo:asksForData ?candidateCritPropDatum .\n" +
            "\t\t\t\t\t?candidateCritPropDatum epo:hasValueAmount ?candidatePercentageRateAmount .\n" +
            "\t\t\t\t\t?candidatePercentageRateAmount ccts:amountValue ?candidatePercentageRibasso .\n" +
            "\t\t\t\t}\n" +
            "\t\t\t}\n" +
            "\t\t}\n" +
            "\t\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName ?tendererCategoryCode ?tendererCategoryName\n" +
            "\t}\n" +
            "\tGROUP BY ?tenderId ?tenderCategoryCode ?tenderCategoryName \n" +
            "}\n" +
            "GROUP BY ?tenderId";


}
