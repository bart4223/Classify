package Classify;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.paint.Color;
import java.util.Timer;
import java.util.TimerTask;

public class Main extends Application {

    protected MainStageController FMainStageController;
    protected BubbleSortManager FBubbleSortManager;
    protected Boolean FTimerRunning = false;
    protected Timer FTimer;

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
        FBubbleSortManager = new BubbleSortManager(this);

    }

    @Override
    public void stop() throws Exception {
        if (FTimer != null) {
            FBubbleSortManager.Terminate();
            FTimer.cancel();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void ShowBubbleSortStage(){
        FBubbleSortManager.ShowStage();
    }

    public void Stop() {
        FBubbleSortManager.ToggleInterrupted();
    }

    public void Run(){

        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    FBubbleSortManager.InitElements();
                    FBubbleSortManager.SortElements();
                    FTimerRunning = false;
                    if (FTimer != null) {
                        FTimer.cancel();
                    }
                }
            }
        };

        if (!FTimerRunning) {
            FTimerRunning = !FTimerRunning;
            FTimer = new Timer();
            FTimer.schedule(lTimerTask,500);
        }
        else
            Stop();

    }

}
