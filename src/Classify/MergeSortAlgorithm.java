package Classify;

import java.util.concurrent.CopyOnWriteArrayList;

public class MergeSortAlgorithm extends SortAlgorithm{

    @Override
    protected void DoExecute() throws Exception{
        MergeSort(FElements, 0, FElements.size()-1);
    }

    protected void MergeSort(CopyOnWriteArrayList<Integer> aElements, Integer aL, Integer aR) throws Exception {
        int i, j, k, m;
        CopyOnWriteArrayList<Integer> bElements = new CopyOnWriteArrayList<Integer>();
        for (i=0;i<aElements.size();i++) {
            bElements.add(0);
        }
        if (aR > aL) {
            m = (aR+aL)/2;
            MergeSort(aElements, aL, m);
            MergeSort(aElements, m+1, aR);
            for (i=m+1;i>aL;i--) {
                bElements.set(i-1,aElements.get(i-1));
            }
            for (j=m;j<aR;j++) {
                bElements.set(aR+m-j,aElements.get(j+1));
            }
            for (k=aL;k<=aR;k++) {
                if (bElements.get(i)<bElements.get(j)) {
                    aElements.set(k,bElements.get(i++));
                }
                else {
                    aElements.set(k,bElements.get(j--));
                }
                OneStepSorted();
            }
        }
    }

    public MergeSortAlgorithm() {
        FDescription = "MergeSort-Algorithm";
    }

}
