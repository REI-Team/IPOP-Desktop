import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.VBox;
public class ControllerEncript {
    
    

    @FXML
    private void back() {
        UtilsViews.setViewAnimating("login");
    }
    
}
