package fr.an.tests.javafxwhiteapp;

public abstract class DrawingElement {
    public abstract void accept(DrawingElementVisitor visitor);
}