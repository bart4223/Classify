package Classify;

import java.util.concurrent.CopyOnWriteArrayList;

public class ClassifySortEvent extends java.util.EventObject {

    public CopyOnWriteArrayList<Integer> Elements;

    public ClassifySortEvent(Object source) {
        super(source);
    }

}
