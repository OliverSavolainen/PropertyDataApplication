package app.filters;

public class AddressFilter implements FilterPart {

    private String address;
    private String comparison;

    public void setAddress(String address) {
        this.address = address;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public String getValue() {
        return address;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public AddressFilter(String address, String comparison) {
        this.address = address;
        this.comparison = comparison;
    }
}
