package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class ClassifyItem implements SortAlgorithmEventListener{

    protected Stage FStage;
    protected ElementChartStageController FStageController;
    protected ArrayList<Integer> FElements;
    protected SortAlgorithm FAlgorithm;
    protected String FAlgorithmClassName;
    protected Random FGenerator;
    protected Boolean FTimerRunning;
    protected Timer FTimer;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ElementChartStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ElementChartStageController)lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle(FAlgorithm.GetDescription());
            FStage.setScene(new Scene(lRoot, 500, 500, Color.DARKGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void CreateSortAlgorithm() {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        try {
            Class lClass = systemClassLoader.loadClass(FAlgorithmClassName);
            FAlgorithm = (SortAlgorithm)lClass.newInstance();
            FAlgorithm.addEventListener(this);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void DemoFillElementsRandom() {
        FElements.clear();
        Integer lCount = 30;
        Integer lRandom;
        for(int i=0; i < lCount; i++) {
            lRandom = FGenerator.nextInt(400);
            //lRandom = Math.abs(FGenerator.nextInt() % 10);
            FElements.add(lRandom);
        }
    }

    public ClassifyItem(String aAlgorithmClassName) {
        FElements = new ArrayList<Integer>();
        FAlgorithmClassName = aAlgorithmClassName;
        FGenerator = new Random();
        FTimerRunning = false;
    }

    public void Initialize() {
        CreateSortAlgorithm();
        CreateStage();
    }

    public void ShowStage(){
        FStage.show();
    }

    public void ToggleInterrupted() {
        FAlgorithm.SetInterrupted(!FAlgorithm.GetInterrupted());
    }

    public void Terminate() {
        FAlgorithm.Terminate();
        if (FTimer != null) {
            FTimer.cancel();
        }
    }

    public void InitElements() {
        Terminate();
        DemoFillElementsRandom();
        FAlgorithm.SetElements(FElements);
        FStageController.SetElements(FElements);
        FStageController.RenderScene();
    }

    public void Run(){

        TimerTask lTimerTask = new TimerTask() {
            public void run() {
                synchronized (this) {
                    FAlgorithm.Execute();
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
            FTimer.schedule(lTimerTask,100);
        }
        else
            ToggleInterrupted();
    }

    @Override
    public void handleOneStepSorted(EventObject e) {
        SortAlgorithmEvent lEvent = (SortAlgorithmEvent)e;
        FStageController.SetElements(lEvent.Elements);
        FStageController.RenderScene();
    }
}