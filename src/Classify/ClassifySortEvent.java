package Classify;

import java.util.ArrayList;

public class ClassifySortEvent extends java.util.EventObject {

    public ArrayList<Integer> Elements;

    public ClassifySortEvent(Object source) {
        super(source);
    }

}
