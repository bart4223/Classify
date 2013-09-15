package Classify;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassifyManager {

    protected ArrayList<ClassifyItem> FItems;

    public ClassifyManager() {
        FItems = new ArrayList<ClassifyItem>();
    }

    public void ShowStages() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).ShowStage();
        }
    }

    public void Terminate() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Terminate();
        }
    }

    public void Run() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Run();
        }
    }

    public void RegisterClassifyItem(ClassifyItem aClassifyItem) {
        aClassifyItem.Initialize();
        FItems.add(aClassifyItem);
    }

}
