package Classify;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

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
            WriteLog("Failed to load the configuration ("+e.getMessage()+")!");
        }
        return FDocument != null;
    }

    protected Integer GetConfigElementCount() {
        return (GetDocumentElementValueByNameAsInteger("Elements/Count"));
    }

    protected Integer GetConfigElementMaxValue() {
        return (GetDocumentElementValueByNameAsInteger("Elements/MaxValue"));
    }

    protected String GetConfigDescription() {
        return (GetDocumentElementValueByNameAsString("Description"));
    }

    protected ArrayList<ClassifyItem> GetConfigItems() {
        ArrayList<ClassifyItem> lResult = new ArrayList<ClassifyItem>();
        NodeList lNodes = GetDocumentElementsByName("Items/Item");
        for (int i=0;i<lNodes.getLength();i++) {
            NodeList lNodesSub;
            String lSortAlgorithm = "";
            String lScenarioStr = "";
            Element lElement = (Element)lNodes.item(i);
            lNodesSub = lElement.getElementsByTagName("SortAlgorithm");
            if (lNodesSub.getLength()>0){
                lSortAlgorithm = "Classify."+lNodesSub.item(0).getTextContent();
            }
            lNodesSub = lElement.getElementsByTagName("Scenario");
            if (lNodesSub.getLength()>0){
                lScenarioStr = lNodesSub.item(0).getTextContent();
            }
            lResult.add(new ClassifyItem(lSortAlgorithm,ElementGenerator.Scenarios.valueOf(lScenarioStr)));
        }

        return lResult;
    }

    protected NodeList GetDocumentElementsByName(String aPath) {
        int i = 0;
        String[] lStrings = aPath.split("/");
        NodeList lResult = null;
        if (FDocument != null) {
            Element lElement = FDocument.getDocumentElement();
            while (i < lStrings.length){
                lResult = lElement.getElementsByTagName(lStrings[i]);
                if (lResult != null) {
                    lElement = (Element)lResult.item(0);
                    i = i + 1;
                }
            }
        }
        return lResult;
    }

    protected String GetDocumentElementValueByNameAsString(String aPath) {
        String lResult = "";
        NodeList lNodes = GetDocumentElementsByName(aPath);
        if (lNodes != null && lNodes.getLength() > 0) {
            lResult = lNodes.item(0).getTextContent();
        }
        return lResult;
    }

    protected Integer GetDocumentElementValueByNameAsInteger(String aPath) {
        Integer lResult;
        try {
            lResult = Integer.parseInt(GetDocumentElementValueByNameAsString(aPath));
        } catch (Exception e) {
            lResult = 0;
            WriteLog("Failed load integer element ("+e.getMessage()+")!");
        }
        return lResult;
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
            WriteLog("Error by submitting sample"+aNumber+"!");
        }
    }

    public void LoadConfig(String aXML) {
        if (LoadXMLDocument(aXML)) {
            String lDesc;
            ArrayList<ClassifyItem> lItems;
            FManager.UnRegisterClassifyItems();
            lDesc = GetConfigDescription();
            lItems = GetConfigItems();
            Iterator lItr = lItems.iterator();
            while(lItr.hasNext())  {
                FManager.RegisterClassifyItem((ClassifyItem) lItr.next());
            }
            FManager.SetElementCount(GetConfigElementCount());
            FManager.SetMaxElementValue(GetConfigElementMaxValue());
            WriteLog("Configuration (" + lDesc + ") loaded with "+Integer.toString(lItems.size())+" sort environments...");
        }
    }

}
