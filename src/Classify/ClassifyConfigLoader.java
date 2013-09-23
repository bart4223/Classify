package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Document;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;

public class ClassifyConfigLoader {

    protected ClassifyManager FManager;
    protected Stage FStage;
    protected ArrayList<LogEntry> FLogEntries;
    protected ClassifyConfigStageController FStageController;

    protected void CreateStage(){
        FStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ClassifyConfigStage.fxml"));
        try {
            lXMLLoader.load();
            FStageController = (ClassifyConfigStageController)lXMLLoader.getController();
            FStageController.ConfigLoader = this;
            Parent lRoot = lXMLLoader.getRoot();
            FStage.setTitle("Classify-Configuration");
            FStage.setScene(new Scene(lRoot, 600, 600, Color.DARKGRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void WriteLog(String aText) {
        Date lDate = new Date();
        LogEntry aLogEntry = new LogEntry(lDate, aText);
        FLogEntries.add(aLogEntry);
        FStageController.DisplayLogEntry(aLogEntry);
    }

    public ClassifyConfigLoader(ClassifyManager aManager) {
        FManager = aManager;
        FLogEntries = new ArrayList<LogEntry>();
    }

    public void Initialize() {
        CreateStage();
    }

    public void ShowConfig() {
        FStage.show();
    }

    public void SubmitSample(String aNumber) {
        InputStream in = null;
        try {
            in = new FileInputStream("resources/samples/sample"+aNumber+".xml");
            String lString = "";
            if (in != null) {
                int content;
                while ((content = in.read()) != -1) {
                    lString = lString + (char)content;
                }
                FStageController.DisplayConfigXML(lString);
                WriteLog("Configuration sample"+aNumber+" submitted...");
            }
        } catch (Exception e) {
            WriteLog("Error by submitting sample"+aNumber);
        }
    }

    public void LoadConfig(String aConfigXML) {
        int i;
        String lString;
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            ByteArrayInputStream IN = new ByteArrayInputStream(aConfigXML.getBytes());
            Document doc = dBuilder.parse(IN);
            //System.out.println("Root element2: " + doc.getDocumentElement().getNodeName());
            //System.out.println(doc.getDocumentElement().getElementsByTagName("ElementCount").item(0).getTextContent());
            lString = doc.getDocumentElement().getElementsByTagName("ElementCount").item(0).getTextContent();
            i = Integer.parseInt(lString);
            FManager.SetElementCount(i);
            WriteLog("Configuration loaded...");
        } catch (Exception e) {
            WriteLog("Failed to load the configuration ("+e.getMessage()+")...");
        }
    }

}
