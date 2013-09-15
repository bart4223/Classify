package Classify;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    protected MainStageController FMainStageController;
    protected ClassifyManager FClassifyManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("MainStage.fxml"));
        lXMLLoader.load();
        Parent lMainRoot = lXMLLoader.getRoot();
        FMainStageController = (MainStageController)lXMLLoader.getController();
        FMainStageController.Application = this;
        primaryStage.setTitle("Classify");
        primaryStage.setScene(new Scene(lMainRoot, 500, 500, Color.DARKGRAY));
        primaryStage.setResizable(false);
        primaryStage.show();
        FClassifyManager = new ClassifyManager();
        for (int i=0; i<5; i=i+1) {
            ClassifyItem aClassifyItem = new ClassifyItem("Classify.BubbleSortAlgorithm");
            FClassifyManager.RegisterClassifyItem(aClassifyItem);
        }
    }

    @Override
    public void stop() throws Exception {
        FClassifyManager.Terminate();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void ShowStages(){
        FClassifyManager.ShowStages();
    }

    public void Run(){
        FClassifyManager.Run();
    }

}
