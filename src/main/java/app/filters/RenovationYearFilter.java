package app.filters;

public class RenovationYearFilter implements FilterPart {
    private Integer year;
    private String comparison;

    public void setYear(Integer year) {
        this.year = year;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public Integer getValue() {
        return year;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public RenovationYearFilter(Integer year, String comparison) {
        this.year = year;
        this.comparison = comparison;
    }
}
