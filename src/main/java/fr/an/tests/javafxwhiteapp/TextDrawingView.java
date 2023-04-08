package fr.an.tests.javafxwhiteapp;

import fr.an.tests.javafxwhiteapp.BaseDrawingElements.CircleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.GroupDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.LineDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.RectangleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.TextDrawingElement;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import fr.an.tests.javafxwhiteapp.DrawingPt;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

public class TextDrawingView extends DrawingView implements DrawingModelListener{

    protected BorderPane component;
    protected TextArea textArea;
    protected Button applyButton;
    XStream xstream = createXStream();

    public TextDrawingView(DrawingDocModel model) {
        super(model);
        this.component = new BorderPane();
        this.textArea = new TextArea();
        component.setCenter(textArea);
        this.applyButton = new Button("Apply");
        component.setBottom(applyButton);
        refreshModelToView();
        model.addListener(this);
        applyButton.setOnAction(e -> onClickApply());
    }

    static XStream createXStream() {
        XStream xstream = new XStream();
        xstream.addPermission(AnyTypePermission.ANY);
      
        xstream.alias("Pt", DrawingPt.class);
        xstream.alias("Text", TextDrawingElement.class);
        xstream.alias("Line", LineDrawingElement.class);
        xstream.alias("Circle", CircleDrawingElement.class);
        xstream.alias("Rectangle", RectangleDrawingElement.class);
        xstream.alias("Group", GroupDrawingElement.class);
        return xstream;
      }


    protected static class TextDrawingElementVisitor extends DrawingElementVisitor {

        String result;
    
        @Override
        public void caseText(TextDrawingElement p) {
            result = "Text(" + p.pos.getX() + "," + p.pos.getY() + ",'" + p.text + "')";
        }
    
        @Override
        public void caseLine(LineDrawingElement p) {
            result = "Line(" + p.start.getX() + "," + p.start.getY() + ", " + p.end.getX() + "," + p.end.getY() + ")";
        }
    
        @Override
        public void caseRect(RectangleDrawingElement p) {
            result = "Rect(" + p.upLeft.getX() + "," + p.upLeft.getY() + ", " + p.downRight.getX() + "," + p.downRight.getY() + ")";
        }
    
        @Override
        public void caseCircle(CircleDrawingElement p) {
            result = "Circle(" + p.center.getX() + "," + p.center.getY() + ", " + p.radius + ")";
        }
    
        @Override
        public void caseGroup(GroupDrawingElement p) {
            StringBuilder sb = new StringBuilder();
            sb.append("Group[\n");
            for (DrawingElement child : p.elements) {
                child.accept(this);
                sb.append(result);
                sb.append("\n");
            }
            sb.append("]");
            result = sb.toString();
        }
    
        @Override
        public void caseOther(DrawingElement p) {
            result = "not implemented/recognized drawingElement " + p.getClass().getName();
        }
    }
    

    private String recursiveElementToText(DrawingElement drawingElement) {
        TextDrawingElementVisitor visitor = new TextDrawingElementVisitor();
        drawingElement.accept(visitor);
        return visitor.result;
      }
      

    @Override
    public Node getComponent() {
        return component;
    }

    @Override
    public void onModelChange() {
        System.out.println("(from subscribe): model to view change");
        refreshModelToView();
    }

    protected void refreshModelToView() {
        DrawingElement content = model.getContent();
        String text = xstream.toXML(content);
        textArea.setText(text);
      }
      
      private void onClickApply() {
        System.out.println("apply view to model update");
        String text = textArea.getText();
        DrawingElement content =  (DrawingElement) xstream.fromXML(text);
        model.setContent(content); // => fireModelChange ..
      }
}
