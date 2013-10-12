package Classify;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import java.util.ResourceBundle;
import java.net.URL;

import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class ElementChartStageController implements Initializable {

    public enum PaintMode{Rectangle, Point};

    @FXML
    private Canvas Canvas;

    @FXML
    private TextArea TextArea;

    @FXML
    private void handlePaintChoice1Action(ActionEvent event) {
        //ToDo
    }

    protected GraphicsContext gc;
    protected ArrayList<Integer> FElements;
    protected Integer FElementLineWidth;
    protected Integer FTickIndicator;
    protected PaintMode FPaintMode;

    protected void ClearCanvas() {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(12, 0, Canvas.getWidth()-12, Canvas.getHeight()-12);
    }

    protected void PaintAxis() {
        gc.beginPath();
        gc.moveTo(10, 10);
        gc.lineTo(10, Canvas.getHeight() - 10);
        gc.lineTo(Canvas.getHeight()-10,Canvas.getWidth()-10);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(2);
        gc.stroke();
        gc.closePath();
    }

    protected void PaintElementsAsPoint() {
        if (FElements != null && FElements.size()>0) {
            Integer x;
            Integer i = 0;
            Iterator<Integer> iterator = FElements.iterator();
            gc.setFill(Color.DARKGREEN);
            while (iterator.hasNext()) {
                x = iterator.next();
                gc.fillRect(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth-x,FElementLineWidth,FElementLineWidth);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                gc.strokeRect(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth-x,FElementLineWidth,FElementLineWidth);
                i = i + FElementLineWidth;
            }
        }
    }

    protected void PaintElementsAsRect() {
        if (FElements != null && FElements.size()>0) {
            Integer x;
            Integer i = 0;
            Iterator<Integer> iterator = FElements.iterator();
            gc.setFill(Color.DARKGREEN);
            while (iterator.hasNext()) {
                x = iterator.next();
                gc.fillRect(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth-x,FElementLineWidth,x);
                gc.setStroke(Color.BLACK);
                gc.setLineWidth(1);
                gc.strokeRect(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth-x,FElementLineWidth,x);
                i = i + FElementLineWidth;
            }
        }
    }

    protected void PaintTickIndicator() {
        FTickIndicator = FTickIndicator + 1;
        if ((FTickIndicator/10)%2 == 0)
            gc.setFill(Color.DARKGRAY);
        else
            gc.setFill(Color.GRAY);
        gc.fillOval(470, 10, 20, 20);
    }

    public ElementChartStageController() {
        FElementLineWidth = 10;
        FTickIndicator = 0;
        FPaintMode = PaintMode.Point;
    }

    public void SetElements (ArrayList<Integer> aElements) {
        FElements = aElements;
    }

    public synchronized void RenderElements(Boolean aWithTickIndicator) {
        ClearCanvas();
        switch(FPaintMode){
            case Rectangle:
                PaintElementsAsRect();
                break;
            case Point:
                PaintElementsAsPoint();
                break;
        }
        if (aWithTickIndicator)
            PaintTickIndicator();
        else
            FTickIndicator = 0;
    }

    public void DisplayLogEntry(LogEntry aLogEntry) {
        String lStr = TextArea.getText();
        if (lStr.length() == 0) {
            lStr = aLogEntry.GetDateAsString() + "  " + aLogEntry.GetText();
        }
        else {
            lStr = aLogEntry.GetDateAsString() + "  " + aLogEntry.GetText() + "\n" + lStr;
        }
        TextArea.setText(lStr);
    }

    public void ClearLog() {
        TextArea.clear();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = Canvas.getGraphicsContext2D();
        PaintAxis();
    }

}
