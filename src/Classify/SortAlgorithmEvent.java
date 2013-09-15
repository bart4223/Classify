package Classify;

import java.util.ArrayList;

public class SortAlgorithmEvent extends java.util.EventObject {

    public ArrayList<Integer> Elements;

    public SortAlgorithmEvent(Object source) {
        super(source);
    }

}
