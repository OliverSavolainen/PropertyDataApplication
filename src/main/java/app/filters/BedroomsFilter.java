package app.filters;

public class BedroomsFilter implements FilterPart{
    private Integer amount;
    private String comparison;

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public Integer getValue() {
        return amount;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public BedroomsFilter(Integer amount, String comparison) {
        this.amount = amount;
        this.comparison = comparison;
    }
}
