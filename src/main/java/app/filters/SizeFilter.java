package app.filters;

public class SizeFilter implements FilterPart {
    private Float size;
    private String comparison;

    public void setSize(Float size) {
        this.size = size;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public Float getValue() {
        return size;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public SizeFilter(Float size, String comparison) {
        this.size = size;
        this.comparison = comparison;
    }
}
