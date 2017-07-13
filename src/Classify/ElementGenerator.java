package Classify;

import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class ElementGenerator {

    public enum Scenarios{Scenario1, Scenario2, Scenario3, Scenario4, Scenario5};

    protected Integer FCount;
    protected Integer FMaxValue;
    protected CopyOnWriteArrayList<Integer> FCommonRandomElements;
    protected CopyOnWriteArrayList<Integer> FCommonConsistentlyRandomElements;
    protected Random FGenerator;

    protected void FillRandom(CopyOnWriteArrayList<Integer> aElements) {
        aElements.clear();
        Integer lRandom;
        for(int i=0; i < FCount; i++) {
            lRandom = FGenerator.nextInt(FMaxValue);
            //lRandom = Math.abs(FGenerator.nextInt() % 10);
            aElements.add(lRandom);
        }
    }

    protected void SwapElements(CopyOnWriteArrayList<Integer> aElements, Integer aI, Integer aJ) {
        int x = aElements.get(aI);
        aElements.set(aI, aElements.get(aJ));
        aElements.set(aJ, x);
    }

    protected void FillConsistentlyRandom(CopyOnWriteArrayList<Integer> aElements) {
        int x;
        int y;
        FillAscending(aElements);
        for(int i=0; i < 100; i++) {
            x = FGenerator.nextInt(aElements.size()-1);
            y = x;
            while (x == y ) {
                y = FGenerator.nextInt(aElements.size()-1);
            }
            SwapElements(aElements,x,y);
        }
    }

    protected void FillDescending(CopyOnWriteArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add((FCount-i)*10);
        }
    }

    protected void FillAscending(CopyOnWriteArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add(i*10);
        }
    }

    protected void FillCommonRandom(CopyOnWriteArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add(FCommonRandomElements.get(i));
        }
    }

    protected void FillCommonConsistentlyRandom(CopyOnWriteArrayList<Integer> aElements) {
        aElements.clear();
        for(int i=0; i < FCount; i++) {
            aElements.add(FCommonConsistentlyRandomElements.get(i));
        }
    }

    public ElementGenerator() {
        FGenerator = new Random();
        FCommonRandomElements = new CopyOnWriteArrayList<Integer>();
        FCommonConsistentlyRandomElements = new CopyOnWriteArrayList<Integer>();
        FCount = 0;
        FMaxValue = 400;
    }

    public void Initialize() {

    }

    public void InitRandom() {
        FillRandom(FCommonRandomElements);
        FillConsistentlyRandom(FCommonConsistentlyRandomElements);
    }

    public void Fill(Scenarios aScenario, CopyOnWriteArrayList<Integer> aElements) {
        switch(aScenario){
            case Scenario1:
                FillDescending(aElements);
                break;
            case Scenario2:
                FillRandom(aElements);
                break;
            case Scenario3:
                FillCommonRandom(aElements);
                break;
            case Scenario4:
                FillConsistentlyRandom(aElements);
                break;
            case Scenario5:
                FillCommonConsistentlyRandom(aElements);
        }
    }

    public void SetCount(Integer aCount) {
        FCount = aCount;
    }

    public void SetMaxValue(Integer aMaxValue) {
        FMaxValue = aMaxValue;
    }

}
