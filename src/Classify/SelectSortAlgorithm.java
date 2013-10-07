package Classify;

public class SelectSortAlgorithm extends SortAlgorithm {

    @Override
    protected void DoExecute() throws Exception{
        for (int i=0; i<FElements.size(); i++){
            int x = FElements.get(i);
            int k = i;
            for (int j=i+1; j<FElements.size();j++) {
                if (FElements.get(j) < x) {
                    k = j;
                    x = FElements.get(j);
                }
                OneStepSorted();
            }
            FElements.set(k,FElements.get(i));
            FElements.set(i,x);
        }
    }

    public SelectSortAlgorithm() {
        FDescription = "SelectSort-Algorithm";
    }

}
