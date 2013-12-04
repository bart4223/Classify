package Classify;

import Uniwork.Base.TickGenerator;
import Uniwork.Base.TickListener;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.*;

public class ClassifyManager implements TickListener {

    protected Main FApplication;
    protected Stage FStage;
    protected Boolean FInitialized;
    protected Boolean FTickGeneratorEnabled;
    protected ClassifyControllerStageController FStageController;
    protected ArrayList<ClassifyItem> FItems;
    protected ElementGenerator FElementGenerator;
    protected ClassifyConfigLoader FConfigLoader;
    protected TickGenerator FTickGenerator;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ClassifyControllerStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ClassifyControllerStageController)lXMLLoader.getController();
            FStageController.Manager = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Classify-Controller");
            FStage.setScene(new Scene(lRoot, 780, 128, Color.GRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void WriteLog(String aText) {
        FStageController.DisplayText(aText);
    }

    protected void DoTick() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Run();
        }
    }

    protected void UpdateController() {
        FStageController.UpdateControls();
    }

    protected void Terminate() {
        FTickGenerator.SetItemEnabled("MAIN",false);
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).Terminate();
        }
    }

    protected void InitConfig(){
        FConfigLoader.Initialize();
    }

    protected void InitTickGenerator() {
        FTickGenerator.Initialize();
        FTickGenerator.NewItem("MAIN",100);
        FTickGenerator.addListener("MAIN",this);
    }

    protected void InitElementGenerator() {
        FElementGenerator.Initialize();
    }

    public ClassifyManager(Main aApplication) {
        FItems = new ArrayList<ClassifyItem>();
        FConfigLoader = new ClassifyConfigLoader(this);
        FElementGenerator = new ElementGenerator();
        FElementGenerator.SetCount(42);
        FTickGenerator = new TickGenerator();
        FTickGeneratorEnabled = true;
        FInitialized = false;
        FApplication = aApplication;
    }

    public void Initialize() {
        CreateStage();
        InitConfig();
        InitTickGenerator();
        InitElementGenerator();
        WriteLog(LoadResourceFileContent("texts/welcome.txt"));
        FInitialized = true;
        UpdateController();
    }

    public void Finalize() {
        FTickGenerator.Finalize();
        Terminate();
    }

    public void ShowController() {
        FStage.show();
    }

    public void ShowConfig() {
        FConfigLoader.ShowConfig();
    }

    public void ShowStages() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).ShowStage();
        }
    }

    public void CloseStages() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).CloseStage();
        }
    }

    public void Clear() {
        Terminate();
        FItems.clear();
    }

    public void ToggleRun() {
        if (FTickGeneratorEnabled)
            FTickGenerator.SetItemEnabled("MAIN",!IsRunning());
        else
            DoTick();
        UpdateController();
    }

    public void InitRun() {
        FElementGenerator.InitRandom();
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).InitRun();
        }
    }

    public void UnRegisterClassifyItems() {
        Terminate();
        CloseStages();
        FItems.clear();
        UpdateController();
    }

    public void RegisterClassifyItem(ClassifyItem aClassifyItem) {
        aClassifyItem.ElementGenerator = FElementGenerator;
        aClassifyItem.Initialize();
        FItems.add(aClassifyItem);
        UpdateController();
    }

    public void ClearLog() {
        Iterator lItr = FItems.iterator();
        while(lItr.hasNext())  {
            ((ClassifyItem)lItr.next()).ClearLog();
        }
    }

    public void SetElementCount(Integer aCount) {
        FElementGenerator.SetCount(aCount);
    }

    public void SetMaxElementValue(Integer aMaxValue) {
        FElementGenerator.SetMaxValue(aMaxValue);
    }

    public void SetTickInterval(Integer aInterval) {
        FTickGenerator.SetItemInterval("MAIN",aInterval);
    }

    public void SetTickGeneratorEnabled(Boolean aValue) {
        FTickGenerator.SetItemEnabled("MAIN",false);
        FTickGeneratorEnabled = aValue;
    }

    public Boolean HasItems() {
        return FItems.size() > 0;
    }

    public Boolean IsRunning() {
        return FTickGenerator.GetItemEnabled("MAIN");
    }

    public String LoadResourceFileContent(String aFilename) {
        String lResult = "";
        try {
            InputStream lFileStream = new FileInputStream("resources/"+aFilename);
            if (lFileStream != null) {
                int lContent;
                while ((lContent = lFileStream.read()) != -1) {
                    lResult = lResult + (char)lContent;
                }
            }
        } catch (Exception e) {
        }
        return lResult;
    }

    @Override
    public void handleTick(Uniwork.Base.TickEvent e) {
        DoTick();
    }

    public void Shutdown() {
        Platform.exit();
    }

}
