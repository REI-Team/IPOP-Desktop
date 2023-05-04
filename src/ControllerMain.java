import java.io.IOException;
import java.net.URL;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;

public class ControllerMain {

    @FXML
    public ProgressIndicator loading;

    @FXML
    public Label Info;

    @FXML
    private Button next;

    @FXML
    private Button back;

    @FXML
    private VBox vbox = new VBox();

    public Integer page=0;

    private void loadUsersCallback (String response) {

        JSONObject objResponse = new JSONObject(response);
        // System.out.println(objResponse);
        if (objResponse.getString("status").equals("OK")) {

            JSONArray JSONlist = objResponse.getJSONArray("result");
            URL resource = this.getClass().getResource("./assets/item.fxml");
            
            // Clear the list of consoles
            vbox.getChildren().clear();
            // System.out.println("post");
            // Add received consoles from the JSON to the list
            for (int i = 0; i < JSONlist.length(); i++) {
                JSONObject console = JSONlist.getJSONObject(i);

                try {
                    if(i<10){

                        // Load template and set controller
                        FXMLLoader loader = new FXMLLoader(resource);
                        Parent itemTemplate = loader.load();
                        ControllerListButton itemController = loader.getController();
                    
                        // Fill template with console information
                        itemController.setTitle(console.getString("name"));
                        itemController.setImg(console.getInt("visible"));
                        itemController.setPoints(String.valueOf(console.getInt("score")));
                        itemController.setId(console.getInt("id"));
                        
                        // Add template to the list
                        vbox.getChildren().add(itemTemplate);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(JSONlist.length()>=11){
                next.setDisable(false);
            }else{
                next.setDisable(true);
            }
        } else {
            Info.setText("Error de conexió");
            System.out.println("ERROR#ListController");
        }
    }
    public void loadUsers(String skip){
        JSONObject obj = new JSONObject("{}");
        obj.put("elements", "11");
        obj.put("start", skip);
        Info.setText("");
        loading.setVisible(true);
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + "/API/get_ranking_desk", obj.toString(), (response) -> {
           
            this.loadUsersCallback(response);
            loading.setVisible(false);
        });
    }

    @FXML
    private void nextPage(){
        page+=10;
        loadUsers(String.valueOf(page));
        back.setDisable(false);
    }
    @FXML
    private void previousPage(){
        page-=10;
        loadUsers(String.valueOf(page));
        if(page==0){
            back.setDisable(true);
        }
    }
    
    public void clean() {
        this.vbox.getChildren().clear();
    }

}
