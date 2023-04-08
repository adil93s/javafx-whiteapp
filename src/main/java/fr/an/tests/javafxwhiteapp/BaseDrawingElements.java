package fr.an.tests.javafxwhiteapp;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class BaseDrawingElements {
    public static class TextDrawingElement extends DrawingElement {
        public String text;
        public DrawingPt pos;
        public Map<String, Object> properties;
        public TextDrawingElement(String text, DrawingPt pos) {
            this.text = text;
            this.pos = pos;
        }
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseText(this);
        }
    }

    public static class LineDrawingElement extends DrawingElement {
        public DrawingPt start;
        public DrawingPt end;
        public Map<String, Object> properties;
        public LineDrawingElement(DrawingPt start, DrawingPt end) {
            this.start = start;
            this.end = end;
        }
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseLine(this);
        }
    }

    public static class RectangleDrawingElement extends DrawingElement {
        public DrawingPt upLeft;
        public DrawingPt downRight;
        public Map<String,Object> properties;
        public RectangleDrawingElement(DrawingPt upLeft, DrawingPt downRight) {
            this.upLeft = upLeft;
            this.downRight = downRight;
        }
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseRect(this);
        }
    }

    public static class CircleDrawingElement extends DrawingElement {
        public DrawingPt center;
        public double radius;
        public Map<String,Object> properties;
        public CircleDrawingElement(DrawingPt center, double radius) {
            this.center = center;
            this.radius = radius;
        }
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseCircle(this);
        }
    }

    public static class GroupDrawingElement extends DrawingElement {
        public List<DrawingElement> elements = new ArrayList<>();
        public void addAll(TextDrawingElement text, LineDrawingElement line, RectangleDrawingElement rectangle, CircleDrawingElement circle) {
            elements.add(text);
            elements.add(line);
            elements.add(rectangle);
            elements.add(circle);
        }
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseGroup(this);
        }
    }
    

    public static class ImageDrawingElement extends DrawingElement {
        public javafx.scene.image.Image image;

        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseOther(this);
        }
    }

    public static class ProxyDrawingElement extends DrawingElement {
        public DrawingElement underlying;
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseOther(this);
        }
    }

    public static class AffineTransformedDrawingElement extends DrawingElement {
        public DrawingElement underlying;
        public DrawingPt translate;
        public double rotateAngle;
        public double scale;   
        @Override
        public void accept(DrawingElementVisitor visitor) {
            visitor.caseOther(this);
        }
    }
}
