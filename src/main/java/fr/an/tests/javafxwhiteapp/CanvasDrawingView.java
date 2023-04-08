package fr.an.tests.javafxwhiteapp;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import fr.an.tests.javafxwhiteapp.BaseDrawingElements.CircleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.GroupDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.LineDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.RectangleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.TextDrawingElement;
import javafx.scene.Node;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CanvasDrawingView extends DrawingView {

    protected BorderPane component;
    protected Pane drawingPane; 

    public CanvasDrawingView(DrawingDocModel model) {
        super(model);
        this.component = new BorderPane();
        this.drawingPane = new Pane();
        component.setCenter(drawingPane);
        refreshModelToView();
    }

    protected class JavafxDrawingElementVisitor extends DrawingElementVisitor {
        
        protected void add(Node node) {
            drawingPane.getChildren().add(node);
        }
        @Override
        public void caseText(TextDrawingElement p) {
            add(new Text(p.pos.x, p.pos.y, p.text)); 
        }
        @Override
        public void caseLine(LineDrawingElement p) {
            add(new javafx.scene.shape.Line(p.start.x, p.start.y, p.end.x, p.end.y));
        } 
        @Override
        public void caseRect(RectangleDrawingElement p) {
            add(new javafx.scene.shape.Rectangle(p.upLeft.x, p.upLeft.y, p.downRight.x-p.upLeft.x, p.downRight.y-p.upLeft.y));
        }
        @Override
        public void caseCircle(CircleDrawingElement p) {
            add(new javafx.scene.shape.Circle(p.center.x, p.center.y, p.radius));
        }
        @Override
        public void caseGroup(GroupDrawingElement p) {
            for(DrawingElement child: p.elements) {
                child.accept(this);
            }
        }
        @Override
        public void caseOther(DrawingElement p) {
          // "not implemented/recognized drawingElement "+ p.getClass().getName();
        }
    }
        

    protected void refreshModelToView() {
        DrawingElement content = model.getContent();
        drawingPane.getChildren().clear();
        JavafxDrawingElementVisitor visitor = new JavafxDrawingElementVisitor();
        content.accept(visitor);
      }

    @Override
    public Node getComponent() {
        return component;
    }
}