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
    private Button btnShow;

    @FXML
    private Button btnClose;

    @FXML
    private Button btnInit;

    @FXML
    private Button btnRun;

    @FXML
    private Button btnClearLog;

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
        Manager.ToggleRun();
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

    @FXML
    private void handleQuitAction(ActionEvent event) {
        Manager.Shutdown();
    }

    public void DisplayText(String aText) {
        String lStr = TextArea.getText();
        if (lStr.length() == 0) {
            lStr = aText;
        }
        else {
            lStr = lStr + "\n" + aText;
        }
        TextArea.setText(lStr);
    }

    public void UpdateControls() {
        if (Manager.HasItems()) {
            btnShow.setDisable(false);
            btnClose.setDisable(false);
            btnInit.setDisable(false);
            if (Manager.IsRunning())
                btnRun.setText("Stop");
            else
                btnRun.setText("Start");
            btnRun.setDisable(false);
            btnClearLog.setDisable(false);
        }
        else {
            btnShow.setDisable(true);
            btnClose.setDisable(true);
            btnInit.setDisable(true);
            btnRun.setText("Start");
            btnRun.setDisable(true);
            btnClearLog.setDisable(true);
        }
    }

}
