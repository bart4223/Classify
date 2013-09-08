package Classify;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;

public class Main extends Application {

    protected MainStageController FMainStageController;
    protected BubbleSortManager FBubbleSortManager;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("MainStage.fxml"));
        lXMLLoader.load();
        Parent lMainRoot = lXMLLoader.getRoot();
        FMainStageController = (MainStageController)lXMLLoader.getController();
        FMainStageController.Application = this;
        primaryStage.setTitle("Classify");
        primaryStage.setScene(new Scene(lMainRoot, 500, 500, Color.DARKGRAY));
        primaryStage.show();
        FBubbleSortManager = new BubbleSortManager(this);
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void ShowBubbleSortStage(){
        FBubbleSortManager.ShowStage();
    }
}
