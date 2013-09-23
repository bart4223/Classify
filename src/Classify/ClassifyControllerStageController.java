package Classify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ClassifyControllerStageController {

    public ClassifyManager Manager;

    @FXML
    private TextArea TextArea;

    @FXML
    private void handleShowAction(ActionEvent event) {
       Manager.ShowStages();
    }

    @FXML
    private void handleCloseAction(ActionEvent event) {
        Manager.CloseStages();
    }

    @FXML
    private void handleRunAction(ActionEvent event) {
        Manager.Run();
    }

    @FXML
    private void handleInitAction(ActionEvent event) {
        Manager.InitRun();
    }

    @FXML
    private void handleClearLogAction(ActionEvent event) {
        Manager.ClearLog();
    }

    @FXML
    private void handleConfigAction(ActionEvent event) {
        Manager.ShowConfig();
    }

    public void DisplayText(String aText) {
        TextArea.setText(aText);
    }

}
