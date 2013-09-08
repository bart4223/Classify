package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class BubbleSortManager {

    protected Main FApplication;
    protected Stage Stage;
    protected BubbleSortStageController StageController;

    public BubbleSortManager(Main aApplication) {
        FApplication = aApplication;
    }

    public void ShowStage(){
        if (Stage == null)
            CreateStage();
        Stage.show();

    }

    protected void CreateStage(){
        Stage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("BubbleSortStage.fxml"));
        try {
            lXMLLoader.load();
            StageController = (BubbleSortStageController)lXMLLoader.getController();
            Parent lRoot = lXMLLoader.getRoot();
            Stage.setTitle("Bubblesort");
            Stage.setScene(new Scene(lRoot, 800, 800));
        }
        catch( Exception e)
        {
        }
    }
}
