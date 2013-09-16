package Classify;

import java.util.ArrayList;
import java.util.Random;

public class ElementGenerator {

    public enum Scenarios{Scenario1, Scenario2};
    public Integer Count;

    protected Random FGenerator;

    protected void FillRandom(ArrayList<Integer> aElements) {
        aElements.clear();
        Integer lRandom;
        for(int i=0; i < Count; i++) {
            lRandom = FGenerator.nextInt(400);
            //lRandom = Math.abs(FGenerator.nextInt() % 10);
            aElements.add(lRandom);
        }
    }

    protected void FillDescending(ArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < Count; i++) {
            aElements.add((Count-i)*10);
        }
    }

    public ElementGenerator() {
        FGenerator = new Random();
        Count = 0;
    }

}
