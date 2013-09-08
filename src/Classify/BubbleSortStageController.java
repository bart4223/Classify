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
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);
        gc.beginPath();
        gc.moveTo(10,10);
        gc.lineTo(10,Canvas.getHeight()-10);
        gc.lineTo(Canvas.getHeight()-10,Canvas.getWidth()-10);
        gc.stroke();
        gc.closePath();
    }

    public BubbleSortManager BubbleSortManager;

    public BubbleSortStageController() {
        FElements = new ArrayList<Integer>();
    }

    public void SetElements (ArrayList<Integer> aElements) {
        FElements.clear();
        Iterator<Integer> iterator = aElements.iterator();
        while (iterator.hasNext()) {
            FElements.add(iterator.next());
        }
    }

    public void RenderScene() {
        ClearCanvas();
        PaintAxis();
        Iterator<Integer> iterator = FElements.iterator();
        while (iterator.hasNext()) {

        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        gc = Canvas.getGraphicsContext2D();
        RenderScene();
    }

}
