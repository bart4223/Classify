package Classify;

import java.util.ArrayList;
import java.util.Random;

public class ElementGenerator {

    public enum Scenarios{Scenario1, Scenario2, Scenario3};

    protected Integer FCount;
    protected Integer FMaxValue;
    protected ArrayList<Integer> FCommonElements;
    protected Random FGenerator;

    protected void FillRandom(ArrayList<Integer> aElements) {
        aElements.clear();
        Integer lRandom;
        for(int i=0; i < FCount; i++) {
            lRandom = FGenerator.nextInt(FMaxValue);
            //lRandom = Math.abs(FGenerator.nextInt() % 10);
            aElements.add(lRandom);
        }
    }

    protected void FillDescending(ArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add((FCount-i)*10);
        }
    }

    protected void FillCommon(ArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add(FCommonElements.get(i));
        }
    }

    public ElementGenerator() {
        FGenerator = new Random();
        FCommonElements = new ArrayList<Integer>();
        FCount = 0;
        FMaxValue = 400;
    }

    public void Initialize() {
        FillRandom(FCommonElements);
    }

    public void Fill(Scenarios aScenario, ArrayList<Integer> aElements) {
        switch(aScenario){
            case Scenario1:
                FillRandom(aElements);
                break;
            case Scenario2:
                FillDescending(aElements);
                break;
            case Scenario3:
                FillCommon(aElements);
                break;
        }
    }

    public void SetCount(Integer aCount) {
        FCount = aCount;
    }

    public void SetMaxValue(Integer aMaxValue) {
        FMaxValue = aMaxValue;
    }

}
