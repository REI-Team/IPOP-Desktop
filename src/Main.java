import java.net.URL;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    // Camps JavaFX a modificar
    public static String protocol = "https";
    public static String host = "nodejs-api-production-a765.up.railway.app";

    public static void main(String[] args) {
  
        launch(args);
    }
    
    @Override
    public void start(Stage stage)  {
        try {
            
            final int windowWidth = 400;
            final int windowHeight = 400;
    
            UtilsViews.parentContainer.setStyle("-fx-font: 14 arial;");
    
            UtilsViews.addView(getClass(), "main", "./assets/main.fxml");
            
            Scene scene = new Scene(UtilsViews.parentContainer);
            
            stage.setScene(scene);
            stage.onCloseRequestProperty(); // Call close method when closing window
            stage.setTitle("Gestió ranking.");
            stage.setMinWidth(windowWidth);
            stage.setMinHeight(windowHeight);
            stage.show();
    
            ControllerMain ctrl = (ControllerMain) UtilsViews.getController("main");
            ctrl.loadUsers("0");
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println(e);
        }

    }

    @Override
    public void stop() { 
        
        System.exit(1); // Kill all executor services
    }
}
