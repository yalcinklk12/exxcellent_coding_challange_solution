package de.exxcellent.challenge;

import java.util.List;

public class ComparableRow implements Comparable<ComparableRow> {
    
    private final String name;
    private final int min;
    private final int max;
    
    public ComparableRow(List<String> value, int minIndex, int maxIndex) {
        this.name = value.get(0);
        this.min = Integer.parseInt(value.get(minIndex));
        this.max = Integer.parseInt(value.get(maxIndex));
    }
    
    @Override public int compareTo(ComparableRow o) {
        return getDif() - o.getDif();
    }
    
    public String getName() {
        return name;
    }
    
    public int getDif() {
        return max - min;
    }
}
