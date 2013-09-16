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

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ClassifyControllerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ClassifyControllerStageController)lXMLLoader.getController();
            FStageController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Classify-Controller");
            FStage.setScene(new Scene(lRoot, 300, 60, Color.DARKGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    public ClassifyManager() {
        FItems = new ArrayList<ClassifyItem>();
    }

    public void Initialize() {
        CreateStage();
    }

    public void ShowController() {
        FStage.show();
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

    public void InitRun() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).InitRun();
        }
    }

    public void RegisterClassifyItem(ClassifyItem aClassifyItem) {
        aClassifyItem.Initialize();
        FItems.add(aClassifyItem);
    }

}
