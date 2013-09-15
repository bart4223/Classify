package Classify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class ClassifyControllerStageController {

    public ClassifyManager Manager;

    @FXML
    private void handleShowAction(ActionEvent event) {
       Manager.ShowStages();
    }

    @FXML
    private void handleRunAction(ActionEvent event) {
        Manager.Run();
    }

    @FXML
    private void handleInitAction(ActionEvent event) {
        Manager.InitElements();
    }

}
