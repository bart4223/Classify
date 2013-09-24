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
    protected Document FDocument;

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

    protected boolean LoadXMLDocument(String aConfigXML) {
        try {
            DocumentBuilderFactory lDBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder lDocBuilder = lDBFactory.newDocumentBuilder();
            ByteArrayInputStream lInputStream = new ByteArrayInputStream(aConfigXML.getBytes());
            FDocument = lDocBuilder.parse(lInputStream);
        } catch (Exception e) {
            FDocument = null;
            WriteLog("Failed to load the configuration ("+e.getMessage()+")...");
        }
        return FDocument != null;
    }

    protected Integer GetElementCountFromDocument() {
        if (FDocument != null) {
            String lString = FDocument.getDocumentElement().getElementsByTagName("ElementCount").item(0).getTextContent();
            return Integer.parseInt(lString);
        }
        return (0);
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
        try {
            InputStream lFileStream = new FileInputStream("resources/samples/sample"+aNumber+".xml");
            String lString = "";
            if (lFileStream != null) {
                int lContent;
                while ((lContent = lFileStream.read()) != -1) {
                    lString = lString + (char)lContent;
                }
                FStageController.DisplayConfigXML(lString);
                WriteLog("Configuration sample"+aNumber+" submitted...");
            }
        } catch (Exception e) {
            WriteLog("Error by submitting sample"+aNumber);
        }
    }

    public void LoadConfig(String aXML) {
        if (LoadXMLDocument(aXML)) {
            FManager.UnRegisterClassifyItems();
            FManager.SetElementCount(GetElementCountFromDocument());
            // ToDo
            WriteLog("Configuration loaded with n sort environments...");
        }
    }

}
