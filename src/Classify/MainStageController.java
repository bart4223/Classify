package Classify;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainStageController {

    public Main Application;

    @FXML
    private void handleShowBSAction(ActionEvent event) {
       Application.ShowBubbleSortStage();
    }
}
