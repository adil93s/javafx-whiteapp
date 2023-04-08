package fr.an.tests.javafxwhiteapp;

public abstract class DrawingView {
    protected DrawingDocModel model;
    public DrawingView(DrawingDocModel model) {
        this.model = model;
    }
    public abstract javafx.scene.Node getComponent();
}