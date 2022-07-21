package app.filters;

/**
 * Interface for all parts of the overall filter, every part has a value that the property of the apartment gets compared against and
 * the comparison, which can be one of [more,less,equal] for float and integer values and either contains or equal for string values
 */
public interface FilterPart {

    Object getValue();

    String getComparison();

}
