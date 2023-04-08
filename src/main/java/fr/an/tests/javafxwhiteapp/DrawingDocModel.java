package fr.an.tests.javafxwhiteapp;

import java.util.ArrayList;
import java.util.List;

public class DrawingDocModel {

    public String documentName;
    public DrawingElement content;
    protected List<DrawingModelListener> listeners = new ArrayList<>();

    public String getDocumentName() {
        return documentName;
    }
    public void setDocumentName(String documentName) {
        this.documentName = documentName;
    }
    public DrawingElement getContent() {
        return content;
    }

    public void setContent(DrawingElement content) {
        this.content = content;
        fireModelChange();
    }

    public void addListener(DrawingModelListener p) {
        this.listeners.add(p);
    }
    public void removeListener(DrawingModelListener p) {
        this.listeners.remove(p);
    }

    protected void fireModelChange() {
        for(DrawingModelListener listener : listeners) {
            listener.onModelChange();
        }
    }
}
