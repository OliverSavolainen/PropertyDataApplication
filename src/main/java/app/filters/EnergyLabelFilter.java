package app.filters;

public class EnergyLabelFilter implements FilterPart {

    private String label;
    private String comparison;

    public void setLabel(String label) {
        this.label = label;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public String getValue() {
        return label;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public EnergyLabelFilter(String label, String comparison) {
        this.label = label;
        this.comparison = comparison;
    }
}
