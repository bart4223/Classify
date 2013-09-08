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

public class BubbleSortStageController implements Initializable {

    @FXML
    private Canvas Canvas;

    private GraphicsContext gc;

    protected ArrayList<Integer> FElements;

    protected void ClearCanvas() {
        gc.setFill(Color.LIGHTGRAY);
        gc.fillRect(0, 0, Canvas.getWidth(), Canvas.getHeight());
        PaintAxis();
    }

    protected void PaintAxis() {
        gc.beginPath();
        gc.moveTo(10,10);
        gc.lineTo(10,Canvas.getHeight()-10);
        gc.lineTo(Canvas.getHeight()-10,Canvas.getWidth()-10);
        gc.setStroke(Color.DARKRED);
        gc.setLineWidth(2);
        gc.stroke();
        gc.closePath();
    }

    protected void PaintElements() {
        if (FElements != null && FElements.size()>0) {
            Integer i = 0;
            Iterator<Integer> iterator = FElements.iterator();
            gc.beginPath();
            while (iterator.hasNext()) {
                gc.moveTo(10+i+2,Canvas.getHeight()-12);
                gc.lineTo(10+i+2,Canvas.getHeight()-12-iterator.next()*Canvas.getHeight()/10);
                i += 1;
            }
            gc.setStroke(Color.DARKGREEN);
            gc.setLineWidth(2);
            gc.stroke();
            gc.closePath();
        }
    }

    public BubbleSortManager BubbleSortManager;

    public BubbleSortStageController() {
    }

    public void SetElements (ArrayList<Integer> aElements) {
        FElements = aElements;
    }

    public void RenderScene() {
        ClearCanvas();
        PaintAxis();
        PaintElements();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = Canvas.getGraphicsContext2D();
        RenderScene();
    }

}
