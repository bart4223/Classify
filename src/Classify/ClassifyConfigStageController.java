package Classify;

import Uniwork.Misc.NGLogEntry;

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
    private void handleSample04Action(ActionEvent event) {
        ConfigLoader.SubmitSample("04");
    }

    @FXML
    private void handleLoadAction(ActionEvent event) {
        ConfigLoader.LoadConfig(ConfigXML.getText());
    }

    @FXML
    private void handleHelpAction(ActionEvent event) {
        ConfigLoader.ShowHelp();
    }

    public void DisplayConfigXML(String aXMLText) {
        ConfigXML.setText(aXMLText);
    }

    public void DisplayLogEntry(NGLogEntry aLogEntry) {
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
