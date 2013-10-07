package Classify;

public class QuickSortAlgorithm extends SortAlgorithm {

    @Override
    protected void DoExecute() throws Exception{
        if (FElements.size()>0)
            PartIt(0,FElements.size()-1);
    }

    protected void PartIt(Integer aLow, Integer aUp) throws Exception{
        int i = aLow;
        int j = aUp;
        int x = FElements.get((aLow+aUp)/2);
        System.out.println("aLow:"+aLow+" /aUp:"+aUp+" /Div:"+(aLow+aUp)/2);
        do {
            while (FElements.get(i) < x) {
                i = i + 1;
            }
            while (x < FElements.get(j)) {
                j = j - 1;
            }
            if (i <= j) {
                int h = FElements.get(i);
                FElements.set(i,FElements.get(j));
                FElements.set(j,h);
                i = i + 1;
                j = j - 1;
                OneStepSorted();
            }
        } while (i <= j);
        if (aLow < j)
            PartIt(aLow,j);
        if (i < aUp)
            PartIt(i,aUp);
    }

    public QuickSortAlgorithm() {
        FDescription = "QuickSort-Algorithm";
    }

}
