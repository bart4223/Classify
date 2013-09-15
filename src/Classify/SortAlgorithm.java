package Classify;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import static java.lang.Thread.sleep;

public class SortAlgorithm {

    protected String FDescription;
    protected Boolean FInProgress;
    protected Boolean FInterrupted;
    protected Boolean FTerminated;
    protected ArrayList<Integer> FElements;
    protected List FEventlisteners;

    protected void DoExecute() throws Exception{
        OneStepSorted();
    }

    protected synchronized void RaiseOneStepSortedEvent() {
        SortAlgorithmEvent lEvent = new SortAlgorithmEvent(this);
        lEvent.Elements = FElements;
        Iterator lItr = FEventlisteners.iterator();
        while(lItr.hasNext())  {
            ((SortAlgorithmEventListener)lItr.next()).handleOneStepSorted(lEvent);
        }
    }

    protected void OneStepSorted() throws Exception{
        RaiseOneStepSortedEvent();
        while (FInterrupted) {
            try {
                sleep(100);
            } catch (Exception e) {
            }
        }
        Thread.sleep(50);
        if (FTerminated) {
            throw new Exception();
        }
    }

    protected void BeforeExecute() {
        FInProgress = true;
        FInterrupted = false;
        FTerminated = false;
    }

    protected void AfterExecute() {
        FInProgress = false;
    }

    public SortAlgorithm() {
        FEventlisteners= new ArrayList();
        FInProgress = false;
        FInterrupted = false;
        FTerminated = false;
        FDescription = "";
    }

    public String GetDescription() {
        return(FDescription);
    }

    public void SetElements(ArrayList<Integer> aValue) {
        FElements = aValue;
    }

    public ArrayList<Integer> GetElements() {
        return(FElements);
    }

    public void SetInterrupted(Boolean aValue) {
        FInterrupted = aValue;
    }

    public Boolean GetInterrupted() {
        return(FInterrupted);
    }

    public synchronized void addEventListener(SortAlgorithmEventListener aListener)  {
        FEventlisteners.add(aListener);
    }
    public synchronized void removeEventListener(SortAlgorithmEventListener aListener)   {
        FEventlisteners.remove(aListener);
    }

    public void Execute() {
        if (!FInProgress) {
            BeforeExecute();
            try {
                DoExecute();
            } catch (Exception e) {
                FTerminated = false;
            }
            AfterExecute();
        }
    }

    public void Terminate() {
        FTerminated = true;
        FInterrupted = false;
    }

}
