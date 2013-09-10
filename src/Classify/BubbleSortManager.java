package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Random;

public class BubbleSortManager {

    protected Main FApplication;
    protected Stage FStage;
    protected ElementChartStageController FStageController;
    protected ArrayList<Integer> FElements;
    protected BubbleSortAlgorithm FAlgorithm;

    public BubbleSortManager(Main aApplication) {
        FApplication = aApplication;
        FElements = new ArrayList<Integer>();
        FAlgorithm = new BubbleSortAlgorithm();
    }

    public void ShowStage(){
        if (FStage == null)
            CreateStage();
        FStage.show();
    }

    public void SortElements(){
        FAlgorithm.Sort(FElements);
        if (FStageController != null) {
            FStageController.SetElements(FElements);
            FStageController.RenderScene();
        }
    }

    public  void InitElements() {
        DemoInitElements();
        FAlgorithm.Init(FElements,true);
    }

    public void DemoInitElements() {
        FElements.clear();
        Integer lCount = 40;
        Integer lRandom;
        Random lGenerator = new Random();
        for(int i=0; i < lCount; i++) {
            lRandom = lGenerator.nextInt(400);
            //lRandom = Math.abs(FGenerator.nextInt() % 10);
            FElements.add(lRandom);
        }
    }

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ElementChartStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ElementChartStageController)lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Bubblesort-Algorithm");
            FStage.setScene(new Scene(lRoot, 500, 500, Color.DARKGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e)
        {
        }
    }
}
