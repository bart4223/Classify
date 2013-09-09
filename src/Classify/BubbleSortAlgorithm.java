package Classify;

import java.util.ArrayList;

public class BubbleSortAlgorithm {

    protected Integer FN;
    protected Integer FI;
    protected Boolean FGradually;

    public BubbleSortAlgorithm() {
        FGradually = false;
    }

    public void Init(ArrayList<Integer> aElements, Boolean aGradually) {
        FN = aElements.size();
        FI = 0;
        FGradually = aGradually;
    }

    public void Sort(ArrayList<Integer> aElements) {
        for (int n=FN; n>1; n=n-1){
            for (int i=FI; i<n-1; i=i+1){
                if (aElements.get(i) > aElements.get(i+1)) {
                    int x = aElements.get(i);
                    aElements.set(i, aElements.get(i+1));
                    aElements.set(i+1, x);
                }
                if (FGradually) {
                    FI = i+1;
                    break;
                }
            }
            if (FGradually) {
                if (FI>=n-1) {
                    FN = n-1;
                    FI = 0;
                }
                break;
            }
        }
    }

}
