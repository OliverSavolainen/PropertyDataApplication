package app.filters;

import app.properties.RentApartment;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for filtering apartments according to the filter given
 */
public class FilterLogic {

    private OverallFilter overallFilter;
    private List<RentApartment> currentList;


    public FilterLogic(OverallFilter overallFilter, List<RentApartment> currentList) {
        this.overallFilter = overallFilter;
        this.currentList = currentList;
    }
    public OverallFilter getOverallFilter() {
        return overallFilter;
    }

    public void setOverallFilter(OverallFilter overallFilter) {
        this.overallFilter = overallFilter;
    }

    public List<RentApartment> getCurrentList() {
        return currentList;
    }

    public void setCurrentList(List<RentApartment> currentList) {
        this.currentList = currentList;
    }

    /**
     * Method for getting the list of apartments through all filters
     * @return list that got through all filters
     * @throws Exception when an incorrect filter name is given
     */
    public List<RentApartment> filterAll() throws Exception {
        integerValueFilter(overallFilter.getFloorFilter(), "floor");
        stringValueFilter(overallFilter.getAddressFilter(), "address");
        stringValueFilter(overallFilter.getEnergyLabelFilter(), "energy");
        floatValueFilter(overallFilter.getSizeFilter(), "size");
        floatValueFilter(overallFilter.getPriceFilter(), "price");
        integerValueFilter(overallFilter.getRoomsFilter(), "rooms");
        integerValueFilter(overallFilter.getBedroomsFilter(), "bedrooms");
        stringValueFilter(overallFilter.getConditionFilter(), "condition");
        integerValueFilter(overallFilter.getRenovationYearFilter(), "year");
        return currentList;
    }

    /**
     * Filter for all properties with float values
     * @param filterPart the filter that the apartments are being put through
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private void floatValueFilter(FilterPart filterPart, String comparable) throws Exception {
        List<RentApartment> filtered = new ArrayList<>();
        if (filterPart!= null && filterPart.getComparison() != null && filterPart.getValue() != null) {
            switch (filterPart.getComparison()) {
                case "more" -> {
                    for (RentApartment rentApartment : currentList) {
                        Float value = getComparatorFloat(rentApartment, comparable);
                        if (value != null && value > (Float) filterPart.getValue()) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                case "less" -> {
                    for (RentApartment rentApartment : currentList) {
                        Float value = getComparatorFloat(rentApartment, comparable);
                        if (value != null && value < (Float) filterPart.getValue()) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                case "equal" -> {
                    for (RentApartment rentApartment : currentList) {
                        Float value = getComparatorFloat(rentApartment, comparable);
                        if (value != null && Math.abs(value - (Float) filterPart.getValue()) < 0.0001) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                default -> {
                }
            }
            currentList.clear();
            currentList.addAll(filtered);
        }
    }
    /**
     * Filter for all properties with string values
     * @param filterPart the filter that the apartments are being put through
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private void stringValueFilter(FilterPart filterPart, String comparable) throws Exception {
        List<RentApartment> filtered = new ArrayList<>();
        if (filterPart!= null && filterPart.getComparison() != null && filterPart.getValue() != null) {
            switch (filterPart.getComparison()) {
                case "contains" -> {
                    for (RentApartment rentApartment : currentList) {
                        String value = getComparatorString(rentApartment, comparable);
                        String filtersTextValue = (String) filterPart.getValue();
                        if (value != null && value.contains(filtersTextValue)) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                case "equal" -> {
                    for (RentApartment rentApartment : currentList) {
                        String value = getComparatorString(rentApartment, comparable);
                        if (value != null && value.equals(filterPart.getValue())) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                default -> {
                }
            }
            currentList.clear();
            currentList.addAll(filtered);
        }
    }
    /**
     * Filter for all properties with integer values
     * @param filterPart the filter that the apartments are being put through
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private void integerValueFilter(FilterPart filterPart, String comparable) throws Exception {
        List<RentApartment> filtered = new ArrayList<>();
        if (filterPart!= null && filterPart.getComparison() != null && filterPart.getValue() != null) {
            switch (filterPart.getComparison()) {
                case "more" -> {
                    for (RentApartment rentApartment : currentList) {
                        Integer value = getComparatorInteger(rentApartment, comparable);
                        if (value != null && value > (Integer) filterPart.getValue()) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                case "less" -> {
                    for (RentApartment rentApartment : currentList) {
                        Integer value = getComparatorInteger(rentApartment, comparable);
                        if (value != null && value < (Integer) filterPart.getValue()) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                case "equal" -> {
                    for (RentApartment rentApartment : currentList) {
                        Integer value = getComparatorInteger(rentApartment, comparable);
                        if (value != null && value == filterPart.getValue()) {
                            filtered.add(rentApartment);
                        }
                    }
                }
                default -> {
                }
            }
            currentList.clear();
            currentList.addAll(filtered);
        }
    }

    /**
     * Method for getting the correct property of the apartment (integer)
     * @param rentApartment apartment to get the property from
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private Integer getComparatorInteger(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "rooms" -> {
                return rentApartment.getRooms();
            }
            case "bedrooms" -> {
                return rentApartment.getBedrooms();
            }
            case "year" -> {
                return rentApartment.getRenovationYear();
            }
            case "floor" -> {
                return rentApartment.getFloor();
            }
        }
        throw new Exception("Wrong comparable");
    }
    /**
     * Method for getting the correct property of the apartment (string)
     * @param rentApartment apartment to get the property from
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private String getComparatorString(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "address" -> {
                return rentApartment.getAddress();
            }
            case "condition" -> {
                return rentApartment.getPropertyCondition();
            }
            case "energy" -> {
                return rentApartment.getEnergyLabel();
            }
        }
        throw new Exception("Wrong comparable");
    }
    /**
     * Method for getting the correct property of the apartment (float)
     * @param rentApartment apartment to get the property from
     * @param comparable the name of property of the apartment in string form
     * @throws Exception when an incorrect comparable name is given
     */
    private Float getComparatorFloat(RentApartment rentApartment, String comparable) throws Exception {
        switch (comparable) {
            case "price" -> {
                return rentApartment.getPrice();
            }
            case "size" -> {
                return rentApartment.getSize();
            }
        }
        throw new Exception("Wrong comparable");
    }

}
