package Classify;

public class InsertSortAlgorithm extends SortAlgorithm {

    protected void DoExecute() throws Exception{
        FElements.add(0,0);
        for (int i=2; i<FElements.size(); i=i+1){
            int x = FElements.get(i);
            FElements.set(0, x);
            int j = i - 1;
            while (x<FElements.get(j)) {
                FElements.set(j+1,FElements.get(j));
                OneStepSorted();
                j = j - 1;
            }
            FElements.set(j+1,x);
            OneStepSorted();
        }
        FElements.remove(0);
    }

    public InsertSortAlgorithm() {
        FDescription = "InsertSort-Algorithm";
    }

}
