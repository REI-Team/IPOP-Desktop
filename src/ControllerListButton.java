import org.json.JSONObject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

public class ControllerListButton {

    private Integer id;
    
    @FXML
    private Rectangle rectangle;

    @FXML
    private Label title;

    @FXML
    private Label points;

    @FXML
    private ImageView img;

    @FXML
    private void handleMenuAction() {
        ControllerMain c0 = (ControllerMain) UtilsViews.getController("main");
        c0.loading.setVisible(true);
        c0.Info.setText("");
        c0.clean();
        JSONObject obj = new JSONObject("{}");
        obj.put("id", id);
        
        UtilsHTTP.sendPOST(Main.protocol + "://" + Main.host + "/API/set_visibility", obj.toString(), (response) -> {
           
            JSONObject objResponse = new JSONObject(response);
            // System.out.println(objResponse);
            if (objResponse.getString("status").equals("OK")) {
                c0.loadUsers(String.valueOf(c0.page));
            }else{
                c0.Info.setText("Error al cambiar visibilidad");
            }
            c0.loading.setVisible(false);
        });
    }

    public void setTitle(String title) {
        this.title.setText(title);
    }

    public void setPoints(String id){
        this.points.setText(id);
    }

    @FXML
    public void setImg(Integer visible){
        if(visible==0){
            this.img.setImage(new Image("./assets/close.png"));
        }else if(visible==1){
            this.img.setImage(new Image("./assets/open.png"));

        }
    }

    public void setId(Integer ide){
        this.id=ide;
    }
    @FXML
    public void mouse(){
        this.rectangle.setVisible(true);
    }
    @FXML
    public void exit(){
        this.rectangle.setVisible(false);
    }
}
