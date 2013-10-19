package Classify;

import Uniwork.Base.LogEntry;

public class ClassifyLogEvent extends java.util.EventObject {

    public LogEntry LogEntry;

    public ClassifyLogEvent(Object source) {
        super(source);
    }

}
