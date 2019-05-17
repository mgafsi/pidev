/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.View.Controller;

import com.jfoenix.controls.JFXListView;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.thebenchwarmers.utility.User;
import com.thebenchwarmers.utility.evenement;
import com.thebenchwarmers.utility.notif;
//import entities.produit_donation;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
//import service.MyServiceDonation;
import com.thebenchwarmers.utility.MyServiceEvenement;
//import util.Authentification;

/**
 *
 */
public class AdminEvController implements Initializable{
    
        
             //  private final User currentUser=Authentification.user;
     MyServiceEvenement service_pr=new MyServiceEvenement();
//     produit_donation p = new produit_donation();
    @FXML
    private JFXListView<Pane> ListView_Produits ;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       
        
       // ListView_Produits.setMouseTransparent( true );
ListView_Produits.setFocusTraversable( false );
          getShowPane();
         
          
    }
    
 
    public void getShowPane()
    {
        List <evenement> AllProducts  = new ArrayList();
        for (evenement p: service_pr.afficheEv())
        {
            AllProducts.add(p);
        }
        System.out.println(AllProducts);
        int i=0;
        int j=0;
        ObservableList<Pane> Panes = FXCollections.observableArrayList();  

        List <evenement> ThreeProducts= new ArrayList();
        for (evenement p:AllProducts )
        {
          
                ThreeProducts.add(p);
             
                
              
                    Panes.add(AddPane(ThreeProducts));
                
                    ThreeProducts.clear();
                    
                

        }
       ListView_Produits.setItems(Panes);
}
    public Pane AddPane( List<evenement> ThreeProduct)
    {
        Pane pane = new Pane();
                    int k =1;
                    for (evenement p3:ThreeProduct )
                        {
                         
                            
                           
                            
                                Pane pane2=new Pane();
                                pane2.setLayoutX(25);
                               
                                pane2.setMaxWidth(215); 
                               
                                //pane2.setStyle("-fx-background-radius: 50;");
        pane2.setStyle(" -fx-border-radius: 10 10 0 0;-fx-border-color: #383d3b ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");
                            
                        Text t=new Text("quick view");        
                        Text t1=new Text("acheter");        
                        t1.setStyle("-fx-font-weight: bold;");
                        t.setStyle("-fx-font-weight: bold;");
        
                                String A = p3.getImage();
                                A = "C:\\wamp64\\www\\datatable_21\\web\\"+A;
                                          File F1 = new File(A);
                                           Image image2 = new Image(F1.toURI().toString());
                                          
                                ImageView image=new ImageView();
                                image.setFitWidth(70);
                                image.setFitHeight(70);
                                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                                image.setImage(image2);
                                image.setLayoutX(0);
                                image.setLayoutY(0);
                                pane2.getChildren().add(image);
                                

                                
                                Text nomt=new Text("Nom Evenement: ");
                                Label nom = new Label(p3.getNom());
                                
                                
                                Text prixt=new Text("Nom Createur  : ");
                                Label prix = new Label((p3.getNomorg()));
                                nomt.setLayoutX(110);
                                nomt.setLayoutY(20);
                                nom.setLayoutX(110);
                                nom.setLayoutY(20);
                                
                                prixt.setLayoutX(110);
                                prixt.setLayoutY(50);
                                prix.setLayoutX(110);
                                prix.setLayoutY(50);
                                
                               
                                
                                nomt.setStyle("-fx-font-weight: bold;-fx-fill : blue");
                                prixt.setStyle("-fx-font-weight: bold;-fx-fill : blue");
                            
                            
                                 
                        FontAwesomeIconView f=new FontAwesomeIconView(FontAwesomeIcon.TRASH_ALT);
                             f.setFill(Color.BLACK);
                              f.setSize("30");
                              f.setLayoutX(250);
                             f.setLayoutY(50);
                             f.setCursor(Cursor.HAND);
                           
                           
                         
                             
                             f.setOnMouseClicked((MouseEvent event) -> {
                              
                                    try {
                                        service_pr.deleteEv(p3,p3.getId());
                                    } catch (SQLException ex) {
                                        Logger.getLogger(MesEv.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                     ListView_Produits.setItems(null);
                                      getShowPane();
                                });
                          
                            
                            
                             
                             
                   
                            
                            
                        pane.getChildren().addAll(pane2,nomt,prixt,nom,prix,f);
                        
                   
   
                            }
                          
                            
                            
                    return pane;
    }
    
    
}
