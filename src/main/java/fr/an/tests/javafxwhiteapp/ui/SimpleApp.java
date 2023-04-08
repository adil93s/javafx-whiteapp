package fr.an.tests.javafxwhiteapp.ui;

import fr.an.tests.javafxwhiteapp.CanvasDrawingView;
import fr.an.tests.javafxwhiteapp.DrawingDocModel;
import fr.an.tests.javafxwhiteapp.DrawingElement;
import fr.an.tests.javafxwhiteapp.DrawingPt;
import fr.an.tests.javafxwhiteapp.TextDrawingView;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.CircleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.GroupDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.LineDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.RectangleDrawingElement;
import fr.an.tests.javafxwhiteapp.BaseDrawingElements.TextDrawingElement;
import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class SimpleApp extends Application {


	public GroupDrawingElement createSimpleDrawing() {
		TextDrawingElement text = new TextDrawingElement("Hello", new DrawingPt(100, 100));
	
		LineDrawingElement line = new LineDrawingElement(new DrawingPt(100, 130), new DrawingPt(200, 230));
	
		RectangleDrawingElement rectangle = new RectangleDrawingElement(new DrawingPt(100, 300), new DrawingPt(200, 350));
	
		CircleDrawingElement circle = new CircleDrawingElement(new DrawingPt(150, 400), 45);
	
		GroupDrawingElement res = new GroupDrawingElement();
		
		res.addAll(text, line, rectangle, circle);
		return res;
	}

	
    @Override
    public void start(Stage stage) {
    	BorderPane mainBorderPanel = new BorderPane();
    	
    	VBox menuAndToolbar = new VBox();
    	{ // MenuBar with "File" menu
            MenuBar mb = new MenuBar();
            menuAndToolbar.getChildren().add(mb);

            Menu fileMenu = new Menu("File");
            mb.getMenus().add(fileMenu);
            MenuItem openMenuItem = new MenuItem("Open");
            fileMenu.getItems().add(openMenuItem);
            MenuItem saveMenuItem = new MenuItem("Save");
            fileMenu.getItems().add(saveMenuItem);
    	}
    	
    	{ // button Toolbar
	    	ToolBar toolBar = new ToolBar();
	    	menuAndToolbar.getChildren().add(toolBar);
    		
	    	Button button1 = new Button("button1");
	    	toolBar.getItems().add(button1);
    	}
    	mainBorderPanel.setTop(menuAndToolbar);

		DrawingDocModel model = new DrawingDocModel();
		DrawingElement content = createSimpleDrawing();
		model.setContent(content);
		
    	{ // SplitPane( view1 | view2 )
			TextDrawingView view1 = new TextDrawingView(model);
			Node view1Comp = view1.getComponent();

			CanvasDrawingView view2 = new CanvasDrawingView(model);
			Node view2Comp = view2.getComponent();

			SplitPane splitViewPane = new SplitPane(view1Comp, view2Comp);
			mainBorderPanel.setCenter(splitViewPane);
    	}
    	
		Scene scene = new Scene(mainBorderPanel, 640, 480);
        stage.setScene(scene);
        stage.show();
    }



}
