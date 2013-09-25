package Classify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ClassifyConfigStageController {

    public ClassifyConfigLoader ConfigLoader;

    @FXML
    private TextArea ConfigXML;

    @FXML
    private TextArea Log;

    @FXML
    private void handleSample01Action(ActionEvent event) {
        ConfigLoader.SubmitSample("01");
    }

    @FXML
    private void handleSample02Action(ActionEvent event) {
        ConfigLoader.SubmitSample("02");
    }

    @FXML
    private void handleSample03Action(ActionEvent event) {
        ConfigLoader.SubmitSample("03");
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
        ConfigLoader.LoadConfig(ConfigXML.getText());
    }

    public void DisplayConfigXML(String aXMLText) {
        ConfigXML.setText(aXMLText);
    }

    public void DisplayLogEntry(LogEntry aLogEntry) {
        String lStr = Log.getText();
        if (lStr.length() == 0) {
            lStr = aLogEntry.GetDateAsString() + "  " + aLogEntry.GetText();
        }
        else {
            lStr = aLogEntry.GetDateAsString() + "  " + aLogEntry.GetText() + "\n" + lStr;
        }
        Log.setText(lStr);
    }

}
