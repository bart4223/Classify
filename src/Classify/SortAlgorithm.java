package Classify;

import java.util.*;
import static java.lang.Thread.sleep;

public class SortAlgorithm {

    protected String FDescription;
    protected Boolean FInProgress;
    protected Boolean FInterrupted;
    protected Boolean FTerminated;
    protected Boolean FSorted;
    protected ArrayList<Integer> FElements;
    protected List FEventListeners;
    protected Integer FSortSteps;
    protected long FStartTime;
    protected long FEndTime;
    protected ArrayList<LogEntry> FLogEntries;

    protected void DoExecute() throws Exception{
        OneStepSorted();
    }

    protected synchronized void RaiseOneStepSortedEvent() {
        ClassifySortEvent lEvent = new ClassifySortEvent(this);
        lEvent.Elements = FElements;
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((ClassifyEventListener)lItr.next()).handleOneStepSorted(lEvent);
        }
    }

    protected void OneStepSorted() throws Exception{
        IncreaseSortSteps();
        RaiseOneStepSortedEvent();
        FInterrupted = true;
        while (FInterrupted) {
            try {
                sleep(10);
            } catch (Exception e) {
            }
        }
        if (FTerminated) {
            throw new Exception();
        }
    }

    protected void BeforeExecute() {
        FInProgress = true;
        FInterrupted = false;
        FTerminated = false;
        FSortSteps = 0;
        FStartTime = System.currentTimeMillis();
        WriteLog("Start sort of "+Integer.toString(GetElementSize())+" elements");
    }

    protected Integer IncreaseSortSteps() {
        FSortSteps = FSortSteps + 1;
        return FSortSteps;
    }

    protected Integer GetElementSize() {
        if (FElements != null)
            return FElements.size();
        else
            return 0;
    }

    protected void AfterExecute() {
        FEndTime = System.currentTimeMillis();
        WriteLog("Time used "+((FEndTime-FStartTime)/1000)+" seconds");
        WriteLog("Sort in "+Integer.toString(FSortSteps)+" steps");
        WriteLog("End sort of "+Integer.toString(GetElementSize())+" elements");
        FInProgress = false;
    }

    protected void WriteLog(String aText) {
        Date lDate = new Date();
        LogEntry aLogEntry = new LogEntry(lDate, aText);
        FLogEntries.add(aLogEntry);
        RaiseLogEntryEvent(aLogEntry);
    }

    protected synchronized void RaiseLogEntryEvent(LogEntry aLogEntry) {
        ClassifyLogEvent lEvent = new ClassifyLogEvent(this);
        lEvent.LogEntry = aLogEntry;
        Iterator lItr = FEventListeners.iterator();
        while(lItr.hasNext())  {
            ((ClassifyEventListener)lItr.next()).handleWriteLog(lEvent);
        }
    }

    public SortAlgorithm() {
        FEventListeners= new ArrayList();
        FLogEntries = new ArrayList<LogEntry>();
        FInProgress = false;
        FInterrupted = false;
        FTerminated = false;
        FSorted = false;
        FDescription = "";
        FSortSteps = 0;
        FStartTime = 0;
        FEndTime = 0;
    }

    public String GetDescription() {
        return(FDescription);
    }

    public void SetElements(ArrayList<Integer> aValue) {
        FElements = aValue;
        FSorted = false;
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

    public synchronized void addEventListener(ClassifyEventListener aListener)  {
        FEventListeners.add(aListener);
    }

    public synchronized void removeEventListener(ClassifyEventListener aListener)   {
        FEventListeners.remove(aListener);
    }

    public void Execute() {
        if (!FInProgress && !FSorted) {
            BeforeExecute();
            try {
                DoExecute();
                FSorted = true;
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

    public void ClearLog() {
        FLogEntries.clear();
    }

}
