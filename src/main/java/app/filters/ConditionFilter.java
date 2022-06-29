package app.filters;

public class ConditionFilter implements FilterPart {
    private String condition;
    private String comparison;

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public String getValue() {
        return condition;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public ConditionFilter(String condition, String comparison) {
        this.condition = condition;
        this.comparison = comparison;
    }
}
