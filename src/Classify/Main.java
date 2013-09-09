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

    TimerTask FTimerTask = new TimerTask() {
        public void run() {
            synchronized (this) {
                Sort();
            }
        }
    };

    protected MainStageController FMainStageController;
    protected BubbleSortManager FBubbleSortManager;
    protected Timer FTimer;
    protected Boolean FTimerRunning = false;

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
        FTimer = new Timer(true);
    }

    @Override
    public void stop() throws Exception {
        Cancel();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public void ShowBubbleSortStage(){
        FBubbleSortManager.ShowStage();
    }

    public void Run(){
        if (!FTimerRunning) {
            FTimerRunning = !FTimerRunning;
            FBubbleSortManager.InitElements();
            FTimer.schedule(FTimerTask,1000,50);
        }
    }

    public void Cancel(){
        FTimer.cancel();
        FTimerRunning = false;
    }

    public void Sort() {
        FBubbleSortManager.SortElements();
    }

}
