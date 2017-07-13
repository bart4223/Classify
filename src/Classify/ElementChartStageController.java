package Classify;

import Uniwork.Misc.NGLogEntry;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import java.util.ResourceBundle;
import java.net.URL;
import java.util.concurrent.CopyOnWriteArrayList;

import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.paint.Color;

public class ElementChartStageController implements Initializable {

    public enum PaintMode{Bar, Point};

    @FXML
    private Canvas Canvas;

    @FXML
    private TextArea TextArea;

    @FXML
    private RadioButton rbPaintBar;

    @FXML
    private RadioButton rbPaintPoint;

    @FXML
    private void handlePaintModeAction(ActionEvent event) {
        UpdatePaintMode();
    }

    protected GraphicsContext gc;
    protected CopyOnWriteArrayList<Integer> FElements;
    protected Integer FElementLineWidth;
    protected Integer FTickIndicator;
    protected PaintMode FPaintMode;
    protected ToggleGroup FPaintModeGroup;

    protected void UpdatePaintMode() {
        if (rbPaintBar.isSelected())
            FPaintMode = PaintMode.Bar;
        else
            FPaintMode = PaintMode.Point;
        RenderElements(FTickIndicator!=0);
    }

    protected void ClearCanvas() {
        gc.clearRect(12, 0, Canvas.getWidth()-11, Canvas.getHeight()-11);
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

    protected void PaintElementsAsBar() {
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

    protected void Initialize() {
        rbPaintBar.setToggleGroup(FPaintModeGroup);
        rbPaintBar.setSelected(true);
        rbPaintPoint.setToggleGroup(FPaintModeGroup);
        UpdatePaintMode();
    }

    public ElementChartStageController() {
        FPaintModeGroup = new ToggleGroup();
        FElementLineWidth = 10;
        FTickIndicator = 0;
    }

    public void SetElements (CopyOnWriteArrayList<Integer> aElements) {
        FElements = aElements;
    }

    public synchronized void RenderElements(Boolean aWithTickIndicator) {
        ClearCanvas();
        switch(FPaintMode){
            case Bar:
                PaintElementsAsBar();
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

    public void DisplayLogEntry(NGLogEntry aLogEntry) {
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
