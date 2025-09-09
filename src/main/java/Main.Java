import java.util.*;

// ================= Feature base class =================
abstract class Feature {
    protected String name;
    protected String type;

    public Feature(String name, String type) {
        this.name = name;
        this.type = type;
    }

    public String getName() { return name; }
    public String getType() { return type; }

    public abstract void printDetails();
}

// ================= StringFeature =================
class StringFeature extends Feature {
    private ArrayList<String> values;
    private LinkedHashSet<String> uniqueValues; // preserves insertion order

    public StringFeature(String name, ArrayList<String> values) {
        super(name, "StringFeature");
        this.values = values;
        this.uniqueValues = new LinkedHashSet<>(values);
    }

    @Override
    public void printDetails() {
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.println("unique values: " + uniqueValues);

        // search for XXX
        System.out.println("search for XXX: " +
                (uniqueValues.contains("XXX") ? "found" : "not found"));

        // last unique value
        String last = "";
        for (String v : uniqueValues) last = v;
        System.out.println("search for last unique value (" + last + "): found");
    }
}

// ================= DoubleFeature =================
class DoubleFeature extends Feature {
    private ArrayList<Double> values;

    public DoubleFeature(String name, ArrayList<Double> values) {
        super(name, "DoubleFeature");
        this.values = values;
    }

    private double mean() {
        double sum = 0;
        for (double v : values) sum += v;
        return sum / values.size();
    }

    private double median() {
        ArrayList<Double> sorted = new ArrayList<>(values);
        Collections.sort(sorted);
        int mid = sorted.size() / 2;
        if (sorted.size() % 2 == 0) {
            return (sorted.get(mid - 1) + sorted.get(mid)) / 2.0;
        } else {
            return sorted.get(mid);
        }
    }

    private double stddev() {
        double m = mean();
        double sum = 0;
        for (double v : values) sum += Math.pow(v - m, 2);
        return Math.sqrt(sum / values.size());
    }

    @Override
    public void printDetails() {
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.printf("mean: %.2f\n", mean());
        System.out.printf("median: %.2f\n", median());
        System.out.printf("std dev: %.4f\n", stddev());
    }
}

// ================= IntegerFeature =================
class IntegerFeature extends Feature {
    private ArrayList<Integer> values;
    private LinkedHashSet<Integer> uniqueValues;
    private int min, max;

    public IntegerFeature(String name, ArrayList<Integer> values) {
        super(name, "IntegerFeature");
        this.values = values;
        this.uniqueValues = new LinkedHashSet<>(values);

        min = Integer.MAX_VALUE;
        max = Integer.MIN_VALUE;
        for (int v : values) {
            if (v < min) min = v;
            if (v > max) max = v;
        }
    }

    @Override
    public void printDetails() {
        System.out.println("name: " + name);
        System.out.println("type: " + type);
        System.out.println("unique values: " + uniqueValues);
        System.out.println("low value: " + min);
        System.out.println("high value: " + max);
    }
}

// ================= Dataset =================
class Dataset {
    private ArrayList<Feature> features;

    public Dataset() {
        features = new ArrayList<>();

        // Demo data (replace with file reading later)
        features.add(new StringFeature("Type1", new ArrayList<>(Arrays.asList("1","2","x","y","3"))));
        features.add(new StringFeature("Type2", new ArrayList<>(Arrays.asList("avalue","bvalue","cvalue"))));
        features.add(new DoubleFeature("Type3", new ArrayList<>(Arrays.asList(2.5, 3.0, 3.2, 3.5, 2.9))));
        features.add(new StringFeature("Type4", new ArrayList<>(Arrays.asList("yes","no"))));
        features.add(new StringFeature("Type5", new ArrayList<>(Arrays.asList("v1","v2"))));
        features.add(new IntegerFeature("Type6", new ArrayList<>(Arrays.asList(1,49,1,68))));
        features.add(new StringFeature("C", new ArrayList<>(Arrays.asList("Yes"))));

        // print all feature details
        int index = 1;
        for (Feature f : features) {
            System.out.println("Feature " + index + ":");
            f.printDetails();
            System.out.println();
            index++;
        }
    }
}

// ================= Main =================
 class Main {
    public static void main(String[] args) {
        new Dataset(); // create dataset ( prints everything)
    }
}


