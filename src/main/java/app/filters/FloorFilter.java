package app.filters;

public class FloorFilter implements FilterPart{
    private Integer floor;
    private String comparison;

    public void setFloor(Integer floor) {
        this.floor = floor;
    }

    public void setComparison(String comparison) {
        this.comparison = comparison;
    }

    @Override
    public Integer getValue() {
        return floor;
    }

    @Override
    public String getComparison() {
        return comparison;
    }

    public FloorFilter(Integer floor, String comparison) {
        this.floor = floor;
        this.comparison = comparison;
    }
}
