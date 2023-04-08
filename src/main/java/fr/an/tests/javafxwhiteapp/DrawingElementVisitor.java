package fr.an.tests.javafxwhiteapp;

import fr.an.tests.javafxwhiteapp.BaseDrawingElements.CircleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.GroupDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.LineDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.RectangleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.TextDrawingElement;

public abstract class DrawingElementVisitor {
    public abstract void caseText(TextDrawingElement p);
    public abstract void caseLine(LineDrawingElement p);
    public abstract void caseRect(RectangleDrawingElement p);
    public abstract void caseCircle(CircleDrawingElement p);
    public abstract void caseGroup(GroupDrawingElement p);  
    public abstract void caseOther(DrawingElement p);
}