import org.json.JSONException;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;

public class ControllerLogin {

    @FXML
    private Button button;

    @FXML
    private Button button1;

    @FXML
    private void encript() {
        
        UtilsViews.setViewAnimating("encript");
                    
    }
    
    @FXML
    private void desencript() {
        
        UtilsViews.setViewAnimating("desencript");
                    
    }
    
}
