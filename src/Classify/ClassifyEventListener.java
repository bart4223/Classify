package Classify;

import java.util.EventObject;

public interface ClassifyEventListener {

    public void handleOneStep(ClassifySortEvent e);

    public void handleWriteLog(ClassifyLogEvent e);

    public void handleSortFinished(ClassifySortEvent e);

}
