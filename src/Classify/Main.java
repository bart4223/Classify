package Classify;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    protected ClassifyManager FClassifyManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FClassifyManager = new ClassifyManager();
        FClassifyManager.Initialize();
        ClassifyItem lClassifyItem;
        for (int i=0; i<1; i=i+1) {
            lClassifyItem = new ClassifyItem("Classify.BubbleSortAlgorithm",ElementGenerator.Scenarios.Scenario2);
            FClassifyManager.RegisterClassifyItem(lClassifyItem);
        }
        for (int i=0; i<1; i=i+1) {
            lClassifyItem = new ClassifyItem("Classify.MergeSortAlgorithm",ElementGenerator.Scenarios.Scenario2);
            FClassifyManager.RegisterClassifyItem(lClassifyItem);
        }
        FClassifyManager.ShowController();
    }

    @Override
    public void stop() throws Exception {
        FClassifyManager.Terminate();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
