package app.filters;

public class PriceFilter implements FilterPart {
    private Float amount;
    private String comparison;

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public Float getValue() {
        return amount;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public PriceFilter(Float amount, String comparison) {
        this.amount = amount;
        this.comparison = comparison;
    }
}
