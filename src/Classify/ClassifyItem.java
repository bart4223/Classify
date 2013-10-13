package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.*;

public class ClassifyItem implements ClassifyEventListener {

    protected Stage FStage;
    protected ElementChartStageController FStageController;
    protected ArrayList<Integer> FElements;
    protected SortAlgorithm FAlgorithm;
    protected String FAlgorithmClassName;
    protected Boolean FTimerRunning;
    protected Timer FTimer;
    protected ElementGenerator ElementGenerator;
    protected ElementGenerator.Scenarios FScenario;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ElementChartStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ElementChartStageController)lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle(FAlgorithm.GetDescription());
            FStage.setScene(new Scene(lRoot, 500, 600, Color.LIGHTGRAY));
            FStage.setResizable(false);
            FStageController.Initialize();
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

    public ClassifyItem(String aAlgorithmClassName, ElementGenerator.Scenarios aScenario) {
        FElements = new ArrayList<Integer>();
        FAlgorithmClassName = aAlgorithmClassName;
        FTimerRunning = false;
        FScenario = aScenario;
    }

    public void Initialize() {
        CreateSortAlgorithm();
        CreateStage();
    }

    public void ShowStage(){
        FStage.show();
    }

    public void CloseStage(){
        FStage.close();
    }

    public void ToggleInterrupted() {
        FAlgorithm.SetInterrupted(!FAlgorithm.GetInterrupted());
    }

    public void Terminate() {
        FAlgorithm.Terminate();
        if (FTimer != null) {
            FTimer.cancel();
        }
        FTimerRunning = false;
    }

    public void InitRun() {
        Terminate();
        ElementGenerator.Fill(FScenario, FElements);
        FAlgorithm.SetElements(FElements);
        FStageController.SetElements(FElements);
        FStageController.RenderElements(false);
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

    protected void DisplayElements(ArrayList<Integer> aElements,Boolean aFinished) {
        FStageController.SetElements(aElements);
        FStageController.RenderElements(aFinished);
    }

    @Override
    public void handleOneStep(ClassifySortEvent e) {
        DisplayElements(e.Elements,true);
    }

    @Override
    public void handleWriteLog(ClassifyLogEvent e) {
        FStageController.DisplayLogEntry(e.LogEntry);
    }

    @Override
    public void handleSortFinished(ClassifySortEvent e) {
        DisplayElements(e.Elements,false);
    }

    public void ClearLog() {
        FAlgorithm.ClearLog();
        FStageController.ClearLog();
    }

}
