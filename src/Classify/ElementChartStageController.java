package Classify;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.canvas.Canvas;
import java.util.ArrayList;
import java.util.Iterator;
import javafx.scene.canvas.GraphicsContext;
import java.util.ResourceBundle;
import java.net.URL;
import javafx.scene.paint.Color;

public class ElementChartStageController implements Initializable {

    @FXML
    private Canvas Canvas;

    protected GraphicsContext gc;
    protected ArrayList<Integer> FElements;
    protected Integer FElementLineWidth;

    protected void ClearCanvas() {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(12, 0, Canvas.getWidth()-12, Canvas.getHeight()-12);
    }

    protected void PaintAxis() {
        gc.beginPath();
        gc.moveTo(10, 10);
        gc.lineTo(10, Canvas.getHeight() - 10);
        gc.lineTo(Canvas.getHeight()-10,Canvas.getWidth()-10);
        gc.setStroke(Color.DARKRED);
        gc.setLineWidth(2);
        gc.stroke();
        gc.closePath();
    }

    protected void PaintElementsWithLine() {
        if (FElements != null && FElements.size()>0) {
            Integer i = 0;
            Iterator<Integer> iterator = FElements.iterator();
            gc.beginPath();
            while (iterator.hasNext()) {
                gc.moveTo(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth);
                gc.lineTo(10+i+FElementLineWidth,Canvas.getHeight()-10-FElementLineWidth-iterator.next());
                i = i + FElementLineWidth;
            }
            gc.setStroke(Color.DARKGREEN);
            gc.setLineWidth(FElementLineWidth);
            gc.stroke();
            gc.closePath();
        }
    }

    protected void PaintElementsWithRect() {
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
                gc.strokeRect(10 + i + FElementLineWidth, Canvas.getHeight() - 10 - FElementLineWidth - x, FElementLineWidth, x);
                i = i + FElementLineWidth;
            }
        }
    }

    public ElementChartStageController() {
        FElementLineWidth = 10;
    }

    public void SetElements (ArrayList<Integer> aElements) {
        FElements = aElements;
    }

    public void RenderScene() {
        ClearCanvas();
        PaintElementsWithRect();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = Canvas.getGraphicsContext2D();
        PaintAxis();
    }

}
