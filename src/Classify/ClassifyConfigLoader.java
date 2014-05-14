package Classify;

import Uniwork.Misc.NGLogEntry;

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
    protected Stage FHelpStage;
    protected ArrayList<NGLogEntry> FLogEntries;
    protected ClassifyConfigStageController FStageController;
    protected ClassifyConfigHelpStageController FHelpStageController;
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
            FStage.setScene(new Scene(lRoot, 600, 600, Color.GRAY));
            FStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void CreateHelpStage(){
        FHelpStage = new Stage();
        FXMLLoader lXMLLoader = new FXMLLoader(getClass().getResource("ClassifyConfigHelpStage.fxml"));
        try {
            lXMLLoader.load();
            FHelpStageController = (ClassifyConfigHelpStageController)lXMLLoader.getController();
            FHelpStageController.ConfigLoader = this;
            Parent lRoot = lXMLLoader.getRoot();
            FHelpStage.setTitle("Classify-Configuration-Help");
            FHelpStage.setScene(new Scene(lRoot, 600, 610, Color.GRAY));
            FHelpStage.setResizable(false);
        }
        catch( Exception e) {
            e.printStackTrace();
        }
    }

    protected void WriteLog(String aText) {
        Date lDate = new Date();
        NGLogEntry lLogEntry = new NGLogEntry(lDate, aText);
        FLogEntries.add(lLogEntry);
        FStageController.DisplayLogEntry(lLogEntry);
    }

    protected boolean LoadXMLDocument(String aConfigXML) {
        try {
            DocumentBuilderFactory lDBFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder lDocBuilder = lDBFactory.newDocumentBuilder();
            ByteArrayInputStream lInputStream = new ByteArrayInputStream(aConfigXML.getBytes());
            FDocument = lDocBuilder.parse(lInputStream);
            if (!FDocument.getDocumentElement().getNodeName().equals("ClassifyManager")) {
                throw new Exception("Wrong root element!");
            }
        } catch (Exception e) {
            FDocument = null;
            WriteLog("Failed to load the configuration ("+e.getMessage()+")!");
        }
        return FDocument != null;
    }

    protected Integer GetConfigElementCount() {
        return (GetDocumentElementValueByNameAsInteger("ElementGenerator/Count"));
    }

    protected Integer GetConfigElementMaxValue() {
        return (GetDocumentElementValueByNameAsInteger("ElementGenerator/MaxValue"));
    }

    protected Integer GetConfigTickGeneratorInterval() {
        return (GetDocumentElementValueByNameAsInteger("TickGenerator/Interval"));
    }

    protected String GetConfigDescription() {
        return (GetDocumentElementValueByNameAsString("Description"));
    }

    protected Boolean GetTickGeneratorEnabled() {
        return (GetDocumentElementValueByNameAsBoolean("TickGenerator/Enabled"));
    }

    protected ArrayList<ClassifyItem> GetConfigItems() {
        ArrayList<ClassifyItem> lResult = new ArrayList<ClassifyItem>();
        NodeList lNodes = GetDocumentElementsByName("Items/Item");
        if (lNodes != null) {
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
        }
        return lResult;
    }

    protected NodeList GetDocumentElementsByName(String aPath) {
        int i = 0;
        String[] lStrings = aPath.split("/");
        NodeList lResult = null;
        if (FDocument != null) {
            Element lElement = FDocument.getDocumentElement();
            while (i < lStrings.length && lElement != null) {
                lResult = lElement.getElementsByTagName(lStrings[i]);
                if (lResult != null) {
                    lElement = (Element)lResult.item(0);
                    i++;
                }
                else {
                    lElement = null;
                }
            }
        }
        return lResult;
    }

    protected Boolean GetDocumentElementValueByNameAsBoolean(String aPath) {
        String lResult = "";
        NodeList lNodes = GetDocumentElementsByName(aPath);
        if (lNodes != null && lNodes.getLength() > 0) {
            lResult = lNodes.item(0).getTextContent();
        }
        return Boolean.parseBoolean(lResult);
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
        FLogEntries = new ArrayList<NGLogEntry>();
    }

    public void Initialize() {
        CreateStage();
        CreateHelpStage();
    }

    public void ShowConfig() {
        FStage.show();
    }

    public void ShowHelp() {
        FHelpStageController.DisplayHelp(FManager.LoadResourceFileContent("texts/help.txt"));
        FHelpStage.show();
    }

    public void SubmitSample(String aNumber) {
        String lContent = FManager.LoadResourceFileContent("samples/sample"+aNumber+".xml");
        if (lContent=="")
            WriteLog("File sample"+aNumber+" not available!");
        else
            WriteLog("Configuration Sample "+aNumber+" submitted...");
        FStageController.DisplayConfigXML(lContent);
    }

    public void LoadConfig(String aXML) {
        if (LoadXMLDocument(aXML)) {
            String lDesc;
            Integer lCount = 0;
            ArrayList<ClassifyItem> lItems;
            FManager.UnRegisterClassifyItems();
            lDesc = GetConfigDescription();
            lItems = GetConfigItems();
            if (lItems != null) {
                lCount = lItems.size();
                Iterator lItr = lItems.iterator();
                while(lItr.hasNext())  {
                    FManager.RegisterClassifyItem((ClassifyItem) lItr.next());
                }
            }
            FManager.SetElementCount(GetConfigElementCount());
            FManager.SetMaxElementValue(GetConfigElementMaxValue());
            FManager.SetTickInterval(GetConfigTickGeneratorInterval());
            FManager.SetTickGeneratorEnabled(GetTickGeneratorEnabled());
            WriteLog("Configuration " + lDesc + " loaded with "+Integer.toString(lCount)+" sort environments...");
        }
        else {
            WriteLog("No valid config available!");
        }
    }

}
