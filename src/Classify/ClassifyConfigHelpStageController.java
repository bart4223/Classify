package Classify;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;

public class ClassifyConfigHelpStageController {

    public ClassifyConfigLoader ConfigLoader;

    @FXML
    private TextArea TextArea;

    public void DisplayHelp(String aText) {
        TextArea.setText(aText);
    }

}
