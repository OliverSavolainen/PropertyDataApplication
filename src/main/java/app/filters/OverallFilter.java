package app.filters;

public class OverallFilter {
    private AddressFilter addressFilter;
    private BedroomsFilter bedroomsFilter;
    private ConditionFilter conditionFilter;
    private EnergyLabelFilter energyLabelFilter;
    private FloorFilter floorFilter;
    private PriceFilter priceFilter;
    private RenovationYearFilter renovationYearFilter;
    private RoomsFilter roomsFilter;
    private SizeFilter sizeFilter;


    public OverallFilter(AddressFilter addressFilter, BedroomsFilter bedroomsFilter, ConditionFilter conditionFilter, EnergyLabelFilter energyLabelFilter, FloorFilter floorFilter, PriceFilter priceFilter, RenovationYearFilter renovationYearFilter, RoomsFilter roomsFilter, SizeFilter sizeFilter) {
        this.addressFilter = addressFilter;
        this.bedroomsFilter = bedroomsFilter;
        this.conditionFilter = conditionFilter;
        this.energyLabelFilter = energyLabelFilter;
        this.floorFilter = floorFilter;
        this.priceFilter = priceFilter;
        this.renovationYearFilter = renovationYearFilter;
        this.roomsFilter = roomsFilter;
        this.sizeFilter = sizeFilter;
    }

    public OverallFilter() {
    }

    public AddressFilter getAddressFilter() {
        return addressFilter;
    }

    public void setAddressFilter(AddressFilter addressFilter) {
        this.addressFilter = addressFilter;
    }

    public BedroomsFilter getBedroomsFilter() {
        return bedroomsFilter;
    }

    public void setBedroomsFilter(BedroomsFilter bedroomsFilter) {
        this.bedroomsFilter = bedroomsFilter;
    }

    public ConditionFilter getConditionFilter() {
        return conditionFilter;
    }

    public void setConditionFilter(ConditionFilter conditionFilter) {
        this.conditionFilter = conditionFilter;
    }

    public EnergyLabelFilter getEnergyLabelFilter() {
        return energyLabelFilter;
    }

    public void setEnergyLabelFilter(EnergyLabelFilter energyLabelFilter) {
        this.energyLabelFilter = energyLabelFilter;
    }

    public FloorFilter getFloorFilter() {
        return floorFilter;
    }

    public void setFloorFilter(FloorFilter floorFilter) {
        this.floorFilter = floorFilter;
    }

    public PriceFilter getPriceFilter() {
        return priceFilter;
    }

    public void setPriceFilter(PriceFilter priceFilter) {
        this.priceFilter = priceFilter;
    }

    public RenovationYearFilter getRenovationYearFilter() {
        return renovationYearFilter;
    }

    public void setRenovationYearFilter(RenovationYearFilter renovationYearFilter) {
        this.renovationYearFilter = renovationYearFilter;
    }

    public RoomsFilter getRoomsFilter() {
        return roomsFilter;
    }

    public void setRoomsFilter(RoomsFilter roomsFilter) {
        this.roomsFilter = roomsFilter;
    }

    public SizeFilter getSizeFilter() {
        return sizeFilter;
    }

    public void setSizeFilter(SizeFilter sizeFilter) {
        this.sizeFilter = sizeFilter;
    }
}
