package Classify;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class MainStageController {

    public Main Application;

    @FXML
    private void handleShowBSAction(ActionEvent event) {
       Application.ShowStages();
    }

    @FXML
    private void handleRunAction(ActionEvent event) {
        Application.Run();
    }

    @FXML
    private void handleCancelAction(ActionEvent event) {
        //Application.Stop();
    }

}
