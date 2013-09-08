package Classify;

import java.util.ArrayList;

public class BubbleSortAlgorithm {

    public BubbleSortAlgorithm() {

    }

    public void Sort(ArrayList<Integer> aElements) {
        for(int i=0; i<aElements.size(); i++) {
            aElements.set(i,aElements.get(i)+1);
        }
    }

}
