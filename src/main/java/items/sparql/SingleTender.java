package items.sparql;

public class SingleTender {
    private String tenderId;
	private String municipality;
	private String tenderDescription;
	private String startDate;
	private String statoContenzioso;
	private String tipoIntervento;
	private String tipoAppalto;
	private String contractDuration;
	private String totalCost;
	private String tenderCategoryCodes;
	private String tenderCategoryNames;
	private String costByCategory;
	private String tenderMainCategoryCode;
	private String tenderMainCategoryName;
	private String tassoRisparmio;
	private String percentageRibasso;
	private String tendererName;
	private String tendererCountry;
	private String tendererMunicipality;
	private String tendererProvince;
	private String tendererRegion;
	private String tendererCategoryCodes;
	private String tendererCategoryNames;
	private String candidates;

    public SingleTender() {
    }

    public SingleTender(String tenderId, String municipality, String tenderDescription, String startDate, String statoContenzioso, String tipoIntervento, String tipoAppalto, String contractDuration, String totalCost, String tenderCategoryCodes, String tenderCategoryNames, String costByCategory, String tenderMainCategoryCode, String tenderMainCategoryName, String tassoRisparmio, String percentageRibasso, String tendererName, String tendererCountry, String tendererMunicipality, String tendererProvince, String tendererRegion, String tendererCategoryCodes, String tendererCategoryNames, String candidates) {
        this.tenderId = tenderId;
        this.municipality = municipality;
        this.tenderDescription = tenderDescription;
        this.startDate = startDate;
        this.statoContenzioso = statoContenzioso;
        this.tipoIntervento = tipoIntervento;
        this.tipoAppalto = tipoAppalto;
        this.contractDuration = contractDuration; //duration of awarded contract
        this.totalCost = totalCost;
        this.tenderCategoryCodes = tenderCategoryCodes;
        this.tenderCategoryNames = tenderCategoryNames;
        this.costByCategory = costByCategory;
        this.tenderMainCategoryCode = tenderMainCategoryCode;
        this.tenderMainCategoryName = tenderMainCategoryName;
        this.tassoRisparmio = tassoRisparmio; //added
        this.percentageRibasso = percentageRibasso; //added
        this.tendererName = tendererName;
        this.tendererCountry = tendererCountry;
        this.tendererMunicipality = tendererMunicipality;
        this.tendererProvince = tendererProvince;
        this.tendererRegion = tendererRegion;
        this.tendererCategoryCodes = tendererCategoryCodes;
        this.tendererCategoryNames = tendererCategoryNames;
        this.candidates = candidates;
    }

    public String getTenderId() {
        return tenderId;
    }

    public void setTenderId(String tenderId) {
        this.tenderId = tenderId;
    }

    public String getMunicipality() {
        return municipality;
    }

    public void setMunicipality(String municipality) {
        this.municipality = municipality;
    }

    public String getTenderDescription() {
        return tenderDescription;
    }

    public void setTenderDescription(String tenderDescription) {
        this.tenderDescription = tenderDescription;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getStatoContenzioso() {
        return statoContenzioso;
    }

    public void setStatoContenzioso(String statoContenzioso) {
        this.statoContenzioso = statoContenzioso;
    }

    public String getTipoIntervento() {
        return tipoIntervento;
    }

    public void setTipoIntervento(String tipoIntervento) {
        this.tipoIntervento = tipoIntervento;
    }

    public String getTipoAppalto() {
        return tipoAppalto;
    }

    public void setTipoAppalto(String tipoAppalto) {
        this.tipoAppalto = tipoAppalto;
    }

    public String getContractDuration() {
        return contractDuration;
    }

    public void setContractDuration(String contractDuration) {
        this.contractDuration = contractDuration;
    }

    public String getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(String totalCost) {
        this.totalCost = totalCost;
    }

    public String getTenderCategoryCodes() {
        return tenderCategoryCodes;
    }

    public void setTenderCategoryCodes(String tenderCategoryCodes) {
        this.tenderCategoryCodes = tenderCategoryCodes;
    }

    public String getTenderCategoryNames() {
        return tenderCategoryNames;
    }

    public void setTenderCategoryNames(String tenderCategoryNames) {
        this.tenderCategoryNames = tenderCategoryNames;
    }

    public String getCostByCategory() {
        return costByCategory;
    }

    public void setCostByCategory(String costByCategory) {
        this.costByCategory = costByCategory;
    }

    public String getTenderMainCategoryCode() {
        return tenderMainCategoryCode;
    }

    public void setTenderMainCategoryCode(String tenderMainCategoryCode) {
        this.tenderMainCategoryCode = tenderMainCategoryCode;
    }

    public String getTenderMainCategoryName() {
        return tenderMainCategoryName;
    }

    public void setTenderMainCategoryName(String tenderMainCategoryName) {
        this.tenderMainCategoryName = tenderMainCategoryName;
    }

    public String getTassoRisparmio() {
        return tassoRisparmio;
    }

    public void setTassoRisparmio(String tassoRisparmio) {
        this.tassoRisparmio = tassoRisparmio;
    }

    public String getPercentageRibasso() {
        return percentageRibasso;
    }

    public void setPercentageRibasso(String percentageRibasso) {
        this.percentageRibasso = percentageRibasso;
    }

    public String getTendererName() {
        return tendererName;
    }

    public void setTendererName(String tendererName) {
        this.tendererName = tendererName;
    }

    public String getTendererCountry() {
        return tendererCountry;
    }

    public void setTendererCountry(String tendererCountry) {
        this.tendererCountry = tendererCountry;
    }

    public String getTendererMunicipality() {
        return tendererMunicipality;
    }

    public void setTendererMunicipality(String tendererMunicipality) {
        this.tendererMunicipality = tendererMunicipality;
    }

    public String getTendererProvince() {
        return tendererProvince;
    }

    public void setTendererProvince(String tendererProvince) {
        this.tendererProvince = tendererProvince;
    }

    public String getTendererRegion() {
        return tendererRegion;
    }

    public void setTendererRegion(String tendererRegion) {
        this.tendererRegion = tendererRegion;
    }

    public String getTendererCategoryCodes() {
        return tendererCategoryCodes;
    }

    public void setTendererCategoryCodes(String tendererCategoryCodes) {
        this.tendererCategoryCodes = tendererCategoryCodes;
    }

    public String getTendererCategoryNames() {
        return tendererCategoryNames;
    }

    public void setTendererCategoryNames(String tendererCategoryNames) {
        this.tendererCategoryNames = tendererCategoryNames;
    }

    public String getCandidates() {
        return candidates;
    }

    public void setCandidates(String candidates) {
        this.candidates = candidates;
    }
}
