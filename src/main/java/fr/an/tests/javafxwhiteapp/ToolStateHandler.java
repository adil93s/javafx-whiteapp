package fr.an.tests.javafxwhiteapp;

import javafx.scene.input.MouseEvent;

public abstract class ToolStateHandler {

    public abstract void onMouseEntered();
    public abstract void onMouseMove(MouseEvent event);
    public abstract void onMouseClick(MouseEvent event);
  
}