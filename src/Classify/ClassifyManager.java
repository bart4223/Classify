package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Iterator;

public class ClassifyManager {

    protected Stage FStage;
    protected ClassifyControllerStageController FStageController;
    protected ArrayList<ClassifyItem> FItems;
    protected ElementGenerator FElementGenerator;
    protected ClassifyConfigLoader FConfigLoader;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ClassifyControllerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ClassifyControllerStageController)lXMLLoader.getController();
            FStageController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Classify-Controller");
            FStage.setScene(new Scene(lRoot, 670, 128, Color.GRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void DisplayMessage(String aText) {
        FStageController.DisplayText(aText);
    }

    public ClassifyManager() {
        FItems = new ArrayList<ClassifyItem>();
        FConfigLoader = new ClassifyConfigLoader(this);
        FElementGenerator = new ElementGenerator();
        FElementGenerator.SetCount(42);
    }

    public void Initialize() {
        FConfigLoader.Initialize();
        CreateStage();
        DisplayMessage("Welcome to classify...");
    }

    public void ShowController() {
        FStage.show();
    }

    public void ShowConfig() {
        FConfigLoader.ShowConfig();
    }

    public void ShowStages() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).ShowStage();
        }
    }

    public void CloseStages() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).CloseStage();
        }
    }

    public void Terminate() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Terminate();
        }
    }

    public void Clear() {
        Terminate();
        FItems.clear();
    }

    public void Run() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Run();
        }
    }

    public void InitRun() {
        FElementGenerator.Initialize();
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).InitRun();
        }
    }

    public void UnRegisterClassifyItems() {
        Terminate();
        CloseStages();
        FItems.clear();
    }

    public void RegisterClassifyItem(ClassifyItem aClassifyItem) {
        aClassifyItem.ElementGenerator = FElementGenerator;
        aClassifyItem.Initialize();
        FItems.add(aClassifyItem);
    }

    public void ClearLog() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).ClearLog();
        }
    }

    public void SetElementCount(Integer aCount) {
        FElementGenerator.SetCount(aCount);
    }

    public void SetMaxElementValue(Integer aMaxValue) {
        FElementGenerator.SetMaxValue(aMaxValue);
    }

}
