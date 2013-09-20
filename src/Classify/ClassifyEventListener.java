package Classify;

public interface ClassifyEventListener {

    public void handleOneStepSorted(ClassifySortEvent e);

    public void handleWriteLog(ClassifyLogEvent e);

}
