import java.util.ArrayList;
import java.util.HashSet;

// Base Feature class
public abstract class Feature {
    protected String fName;
    public String featureName() { return fName; }
    protected Feature() {}
    public Feature(String n) { fName = n; }
}

// Derived Feature classes
class IntegerFeature extends Feature {
    int uniqueCount;
    int highest;
    int lowest;

    public IntegerFeature(String n) { super(n); }
}

class DoubleFeature extends Feature {
    double mean;
    double median;
    double stdDev;

    public DoubleFeature(String n) { super(n); }
}

class StringFeature extends Feature {
    HashSet<String> uniqueStrings = new HashSet<>();

    public StringFeature(String n) { super(n); }

    public int countUnique() { return uniqueStrings.size(); }
    public boolean contains(String s) { return uniqueStrings.contains(s); }
}

// Base DataItem class
 abstract class DataItem {
    public String showString() { return "Should not happen"; }
}

// Derived DataItem classes
class IntegerDataItem extends DataItem {
    int value;
    public IntegerDataItem(int v) { value = v; }
    @Override
    public String showString() { return Integer.toString(value); }
}

class DoubleDataItem extends DataItem {
    double value;
    public DoubleDataItem(double v) { value = v; }
    @Override
    public String showString() { return Double.toString(value); }
}

class StringDataItem extends DataItem {
    String value;
    public StringDataItem(String v) { value = v; }
    @Override
    public String showString() { return value; }
}

// Dataset class
class Dataset {
    ArrayList<Feature> featureList = new ArrayList<>();
    ArrayList<ArrayList<DataItem>> data = new ArrayList<>();
}
