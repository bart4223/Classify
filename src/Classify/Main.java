package Classify;


import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    protected ClassifyManager FClassifyManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FClassifyManager = new ClassifyManager();
        FClassifyManager.Initialize();
        for (int i=0; i<5; i=i+1) {
            ClassifyItem aClassifyItem = new ClassifyItem("Classify.BubbleSortAlgorithm");
            FClassifyManager.RegisterClassifyItem(aClassifyItem);
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
