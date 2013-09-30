package Classify;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    protected ClassifyManager FClassifyManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FClassifyManager = new ClassifyManager();
        FClassifyManager.Initialize();
        FClassifyManager.ShowController();
    }

    @Override
    public void stop() throws Exception {
        FClassifyManager.Finalize();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
