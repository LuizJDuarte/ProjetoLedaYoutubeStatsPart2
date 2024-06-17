package src;

public class StringArrayComparable implements Comparable<StringArrayComparable> {
    private String[] array;

    public StringArrayComparable(String[] array) {
        this.array = array;
    }

    public String[] getArray() {
        return array;
    }

    @Override
    public int compareTo(StringArrayComparable o) {
        return this.array[0].compareTo(o.array[0]);
    }
}
