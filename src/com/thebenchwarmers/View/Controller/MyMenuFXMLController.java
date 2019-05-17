/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.thebenchwarmers.View.Controller;


import com.thebenchwarmers.utility.CurrentUser;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.thebenchwarmers.utility.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import com.thebenchwarmers.utility.MyServiceEvenement;
//import util.Authentification;

/**
 * FXML Controller class
 *
 * 
 */
public class MyMenuFXMLController implements Initializable {
    
    
      @FXML
    private BorderPane BorderPane;
    @FXML
    private Text nbr;
    MyServiceEvenement s=new MyServiceEvenement();
      int nbrnotif;   
    @FXML
    private FontAwesomeIconView notif;
    @FXML
    private Text user;
      
    
//                   private final User currentUser=Authentification.user;

                 
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
         LoadUI("AfficheEvFXML");
          System.out.println("hello said ");
         user.setText(CurrentUser.prenom);
         System.out.println("suer name is "+CurrentUser.prenom);
//         user.setText("test");
  nbrnotif =s.nbr();
        System.out.println ("-___ ___ __ __ _____"+nbrnotif);
        
        nbr.setText(String.valueOf(nbrnotif));
        
    
    }    


    @FXML
    private void AjoutP(MouseEvent event) throws IOException {
        LoadUI("AjouterEvFXML");
    }

    @FXML
    private void AfficheP(MouseEvent event) {
       LoadUI("AfficheEvFXML");
    
    }

    @FXML
    private void CommandeP(MouseEvent event) {
           LoadUI("MesEvFXML");
    } 


    
    private void LoadUI(String ui)
    {
        Parent root= null;
        try {
                  // Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/views/Affiche.fxml"))));             

            root= FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/"+ui+".fxml"));
        } catch (IOException ex) {
            Logger.getLogger(MyMenuFXMLController.class.getName()).log(Level.SEVERE, null, ex);
        }
        BorderPane.setCenter(root);
        
    }

    private void notif(MouseEvent event) throws IOException {
        
        System.out.println("aaaaaaa nekhdem ");
        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/NotifFXML.fxml")));

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
        
        
    }

      @FXML
    private void notification(MouseEvent event) throws IOException {
Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/NotifFXML.fxml")));

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
         MyServiceEvenement e=new MyServiceEvenement();
               
       
    }

    private void evenement(MouseEvent event) {
           LoadUI("AfficheEvFXML");
        
        
    }

    @FXML
    private void ToHome(MouseEvent event) throws IOException {
//       Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/menuAdmin.fxml"))));             

        
    }

    @FXML
    private void Profile(MouseEvent event) {
    }

    @FXML
    private void Statistique(MouseEvent event) {
                   LoadUI("MesPartFXML");

    }


  

    
}
