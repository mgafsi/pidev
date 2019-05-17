/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.View.Controller;

import com.thebenchwarmers.utility.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import com.sun.prism.shader.FillCircle_Color_AlphaTest_Loader;
import java.io.IOException;
import java.net.URL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static javafx.application.Application.launch;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 */
public class LoginController implements Initializable {


    private Label verif;
    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField pwdd;
    @FXML
    private AnchorPane rootPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }
    UserService us = new UserService() ;



    @FXML
    private void loginToMain(ActionEvent event) throws SQLException, IOException {
//                if (user.getText().equals("")) {
//            verif.setText("veuillez saisir votre email");
//        } else if (pwdd.getText().equals("")) {
//            verif.setText("veuillez saisir votre mot de passe");
//        } else if (!us.login(user.getText(), pwdd.getText())) {
//            verif.setText("cordonnÃ©es invalides");
//        } else {
//            System.out.println(" user +++++++++++++++++   "+user.getText());
//            CurrentUser cu = new CurrentUser(us.getUserByEmail(user.getText()));
//
//            if (cu.type.equals(TypeUser.Gerant)) {
//                Parent root = FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setTitle("Cite De La Culture");
//                stage.setScene(scene);
//                stage.show();
////                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
//            }
//            else if(cu.type.equals(TypeUser.Admin)){
//                Parent root = FXMLLoader.load(getClass().getResource("../View/adminHomePage.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setTitle("Cite De La Culture");
//                stage.setScene(scene);
//                stage.show();
////                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
//
//            }
//            else if(cu.type.equals(TypeUser.SimpleUser)){
//                Parent root = FXMLLoader.load(getClass().getResource("../View/HomePage.fxml"));
//                Scene scene = new Scene(root);
//                Stage stage = new Stage();
//                stage.setTitle("Cite De La Culture");
//                stage.setScene(scene);
//                stage.show();
////                ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
//
//            }
//            else {
//            verif.setText("introuvable");
//            }
//            
//        }

if ( user.getText().equals("said@gmail.com"))
{
        CurrentUser cu = new CurrentUser(us.getUserByEmail(user.getText()));
        System.out.println("    current user  "+cu);
       // Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"))));             
//admin
        Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/AdminEvFXML.fxml"))));             

}
else if ( user.getText().equals("maysa@gmail.com"))
{
        CurrentUser cu = new CurrentUser(us.getUserByEmail(user.getText()));
        System.out.println("    current user  "+cu);
        Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"))));             

}
else 
{
    System.out.println("intouvable !!");
}
    }

    private void signup(ActionEvent event) throws IOException {
            Stage stage = new Stage();
        ((Node) event.getSource()).getScene().getWindow().hide();
        Parent root = FXMLLoader.load(getClass().getResource("newaccount.fxml"));
        Scene scene = new Scene(root);

        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
   
   
}
   
    

