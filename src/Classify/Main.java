package Classify;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    protected MainStageController FMainStageController;

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("MainStage.fxml"));
        lXMLLoader.load();
        Parent lMainRoot = lXMLLoader.getRoot();
        FMainStageController = (MainStageController)lXMLLoader.getController();
        FMainStageController.Application = this;
        primaryStage.setTitle("Classify");
        primaryStage.setScene(new Scene(lMainRoot, 500, 500));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
