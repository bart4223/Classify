package Classify;

import Uniwork.Base.NGLogEntry;

public class ClassifyLogEvent extends java.util.EventObject {

    public NGLogEntry LogEntry;

    public ClassifyLogEvent(Object source) {
        super(source);
    }

}
