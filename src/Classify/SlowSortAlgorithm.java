package Classify;

public class SlowSortAlgorithm extends SortAlgorithm {

    @Override
    protected void DoExecute() throws Exception{
        if (FElements.size()>0)
            SlowSort(0,FElements.size()-1);
    }

    protected void SlowSort(Integer aI, Integer aJ) throws Exception{
        if (aI >= aJ)
            return;
        int m = (aI+aJ)/2;
        SlowSort(aI,m);
        SlowSort(m+1,aJ);
        if (FElements.get(aJ) < FElements.get(m)) {
            int x = FElements.get(aJ);
            FElements.set(aJ,FElements.get(m));
            FElements.set(m,x);
        }
        OneStepSorted();
        SlowSort(aI,aJ-1);
    }

    public SlowSortAlgorithm() {
        FDescription = "SlowSort-Algorithm";
    }

}
