/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.View.Controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import com.thebenchwarmers.utility.Template;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXTextField;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import com.thebenchwarmers.utility.evenement;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
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
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.web.WebView;
import javafx.stage.Stage;
import org.controlsfx.control.Notifications;
import com.thebenchwarmers.utility.MyServiceEvenement;

/**
 * FXML Controller class
 *
 */
public class AfficheEvController implements Initializable {

    public String recherche = "";

    final Tooltip tooltip = new Tooltip();

    MyServiceEvenement service_pr = new MyServiceEvenement();
    evenement p = new evenement();
    @FXML
    private JFXListView<Pane> ListView_Produits;
    @FXML
    private JFXTextField rechercher;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        // ListView_Produits.setMouseTransparent( true );
        ListView_Produits.setFocusTraversable(false);
        getShowPane();

    }

    public void getShowPane() {
        List<evenement> AllProducts = new ArrayList();
        if (recherche.equals("")) {
            for (evenement p : service_pr.afficheEv()) {
                AllProducts.add(p);
            }
        } else {
            for (evenement p : service_pr.RechercheEvenementParNom(recherche)) {
                AllProducts.add(p);

            }

        }

        System.out.println(AllProducts);
        int i = 0;
        int j = 0;
        ObservableList<Pane> Panes = FXCollections.observableArrayList();

        List<evenement> ThreeProducts = new ArrayList();
        for (evenement p : AllProducts) {
            if (i == 0) {
                ThreeProducts.add(p);
                i++;
                j++;

                if (j == AllProducts.size()) {
                    System.out.println("hello322");
                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();

                }
            } else {
                ThreeProducts.add(p);
                i++;
                j++;
                if ((i % 3 == 0) || (j == AllProducts.size())) {

                    Panes.add(AddPane(ThreeProducts));

                    ThreeProducts.clear();

                }
            }
        }
        ListView_Produits.setItems(Panes);
    }

    public Pane AddPane(List<evenement> ThreeProduct) {
        Pane pane = new Pane();
        int k = 1;
        for (evenement p3 : ThreeProduct) {
            if (k == 1) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(25);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);
                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10 10 0 0;-fx-border-color: #383d3b ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                Text t = new Text("quick view");

                FontAwesomeIconView t1 = new FontAwesomeIconView(FontAwesomeIcon.BELL);
                t1.setFill(Color.BROWN);
                t1.setSize("20");
                t1.setLayoutX(755);
                t1.setLayoutY(85);
                t1.setCursor(Cursor.HAND);

                /**
                 * ************* participate *******************
                 */
                t1.setOnMouseClicked((MouseEvent event) -> {

                    System.out.println("aaaaaaa");
                    evenement.setId_courant(p3.getId());
                    int i = p3.getParticiate();

                    ParticiperController part = new ParticiperController();

                    try {
                        part.part(p3);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    t1.setIcon(FontAwesomeIcon.BELL_SLASH);
                    t1.setDisable(true);

                });

                /**
                 * ********************************************
                 */
                FontAwesomeIconView tq = new FontAwesomeIconView(FontAwesomeIcon.EXPAND);
                tq.setFill(Color.BLACK);
                tq.setSize("20");

                tq.setCursor(Cursor.HAND);
                /**
                 * ********** quick view (detail) **********
                 */

                tq.setOnMouseClicked((MouseEvent event) -> {
                    evenement.setId_courant(p3.getId());
                    System.out.println("------" + p3);

                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/Quickview.fxml")));

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                    } catch (IOException e) {
                        Logger logger = Logger.getLogger(getClass().getName());
                        logger.log(Level.SEVERE, "Failed to create new Window.", e);
                    }
                });

                /**
                 * ******************************
                 */
                HBox hb = new HBox(tq);
                HBox hb2 = new HBox(t1);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 190);
                hb.setPrefHeight(hb.getHeight() + 75);
                hb.setStyle("-fx-background-color: #2cbae3");

                hb2.setLayoutX(173);
                hb2.setLayoutY(170);
                hb2.setPrefWidth(hb.getWidth() + 45);
                hb2.setPrefHeight(hb.getHeight() + 75);
                hb2.setStyle("-fx-background-color: #2cbae3");
                pane2.getChildren().addAll(hb, hb2);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\datatable_21\\web\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();

                image.setFitWidth(130);
                image.setFitHeight(140);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(45);
                image.setLayoutY(-35);
                pane2.getChildren().add(image);

                tooltip.setGraphic(image);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Type : ");
                Label prix = new Label(p3.getType());

                FontAwesomeIconView users = new FontAwesomeIconView(FontAwesomeIcon.USERS);
                users.setFill(Color.DARKBLUE);
                users.setSize("15");

                users.setLayoutX(150);
                users.setLayoutY(185);

                Label nbpart = new Label(String.valueOf(p3.getParticiate()));

                nbpart.setLayoutX(180);
                nbpart.setLayoutY(165);

                prix.setLayoutX(100);
                prix.setLayoutY(185);

                nomt.setLayoutX(50);
                nomt.setLayoutY(180);
                nom.setLayoutX(100);
                nom.setLayoutY(165);
                prixt.setLayoutX(50);
                prixt.setLayoutY(200);

                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");

                /**
                 * ************* web view ***************
                 */
                FontAwesomeIconView web = new FontAwesomeIconView(FontAwesomeIcon.YOUTUBE_PLAY);
                web.setFill(Color.RED);
                web.setSize("20");

                web.setCursor(Cursor.HAND);

                web.setLayoutX(120);
                web.setLayoutY(240);

                web.setOnMouseClicked((MouseEvent event) -> {
                    System.out.println(" ev courant "+p3);
                    evenement.setId_courant(p3.getId());
                    String a = p3.getLien();
                    System.out.println("lien youtube "+a);
                    WebView webview1 = new WebView();
                    webview1.getEngine().load(
                            a
                    );
                    webview1.setPrefSize(640, 390);
                    Scene scene = new Scene(webview1);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });

                /**
                 * **********************************************
                 */
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix, users, nbpart, web);
//                             Label idp = new Label(String.valueOf(p3.getId()));
//                                idp.setLayoutX(25);
//                                idp.setLayoutY(15);
//                             Button btn = new Button("Delete");
//                             btn.setLayoutX(5);
//                             btn.setLayoutY(50);
//                               Button afficheid = new Button("afficheid");
//                             afficheid.setLayoutX(5);
//                             afficheid.setLayoutY(150);
//                            afficheid.setOnMouseClicked((MouseEvent event) -> {
//                                      Produit.setPanier(p3);
//                                     ListeCommande();
//                                      getShowPane();
//                                });
//                             
//                             btn.setOnMouseClicked((MouseEvent event) -> {
//                              
//                                     service_pr.SupprimerProduit(p3);
//                                     ListView_Produits.setItems(null);
//                                      getShowPane();
//                                });
//                            pane.getChildren().addAll(idp,btn,afficheid);
            }

            if (k == 2) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(300);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);
                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10 10 0 0;-fx-border-color: #383d3b ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                /**
                 * ********** quick view**********
                 */
                /**
                 * ********************************************
                 */
                FontAwesomeIconView tq = new FontAwesomeIconView(FontAwesomeIcon.EXPAND);
                tq.setFill(Color.BLACK);
                tq.setSize("20");

                tq.setCursor(Cursor.HAND);
                /**
                 * ********** quick view**********
                 */

                tq.setOnMouseClicked((MouseEvent event) -> {
                    evenement.setId_courant(p3.getId());

                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/Quickview.fxml")));

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        Logger logger = Logger.getLogger(getClass().getName());
                        logger.log(Level.SEVERE, "Failed to create new Window.", e);
                    }
                });

                /**
                 * ******************************
                 */
                /**
                 * ******************
                 */
                FontAwesomeIconView t1 = new FontAwesomeIconView(FontAwesomeIcon.BELL);
                t1.setFill(Color.BROWN);
                t1.setSize("20");
                t1.setLayoutX(755);
                t1.setLayoutY(85);
                t1.setCursor(Cursor.HAND);

                /**
                 * ************* participate *******************
                 */
                t1.setOnMouseClicked((MouseEvent event) -> {

                    System.out.println("aaaaaaa");
                    evenement.setId_courant(p3.getId());

                    ParticiperController part = new ParticiperController();

                    try {
                        part.part(p3);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                });

                /**
                 * ********************************************
                 */
                //.setStyle("-fx-font-weight: bold;");
                HBox hb = new HBox(tq);
                HBox hb2 = new HBox(t1);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 190);
                hb.setPrefHeight(hb.getHeight() + 75);
                hb.setStyle("-fx-background-color: #2cbae3");

                hb2.setLayoutX(173);
                hb2.setLayoutY(170);
                hb2.setPrefWidth(hb.getWidth() + 45);
                hb2.setPrefHeight(hb.getHeight() + 75);
                hb2.setStyle("-fx-background-color: #2cbae3");
                pane2.getChildren().addAll(hb, hb2);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\datatable_21\\web\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();
                image.setFitWidth(130);
                image.setFitHeight(140);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(45);
                image.setLayoutY(-35);
                pane2.getChildren().add(image);

                FontAwesomeIconView users = new FontAwesomeIconView(FontAwesomeIcon.USERS);
                users.setFill(Color.DARKBLUE);
                users.setSize("15");

                users.setLayoutX(430);
                users.setLayoutY(185);

                Label nbpart = new Label(String.valueOf(p3.getParticiate()));

                nbpart.setLayoutX(460);
                nbpart.setLayoutY(165);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Type : ");
                Label prix = new Label(String.valueOf(p3.getType()));
                nomt.setLayoutX(325);
                nomt.setLayoutY(180);
                nom.setLayoutX(375);
                nom.setLayoutY(165);
                prixt.setLayoutX(325);
                prixt.setLayoutY(200);
                prix.setLayoutX(375);
                prix.setLayoutY(185);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");

                /**
                 * ********** quick view**********
                 */
                /**
                 * ********************************************
                 */
                /**
                 * ************* web view ***************
                 */
                FontAwesomeIconView web = new FontAwesomeIconView(FontAwesomeIcon.YOUTUBE_PLAY);
                web.setFill(Color.RED);
                web.setSize("20");

                web.setCursor(Cursor.HAND);

                web.setLayoutX(370);
                web.setLayoutY(240);

                web.setOnMouseClicked((MouseEvent event) -> {
                    evenement.setId_courant(p3.getId());
                    String a = p3.getLien();
                    WebView webview1 = new WebView();
                    webview1.getEngine().load(
                            a
                    );
                    webview1.setPrefSize(640, 390);
                    Scene scene = new Scene(webview1);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });

                /**
                 * **********************************************
                 */
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix, nbpart, users, web);
            }

            if (k == 3) {
                Pane pane2 = new Pane();
                pane2.setLayoutX(575);
                pane2.setLayoutY(50);
                pane2.setPrefWidth(pane2.getWidth() + 215);
                pane2.setPrefHeight(pane2.getHeight() + 200);
                //pane2.setStyle("-fx-background-radius: 50;");
                pane2.setStyle(" -fx-border-radius: 10 10 0 0;-fx-border-color: #383d3b ;-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0); ");

                FontAwesomeIconView t1 = new FontAwesomeIconView(FontAwesomeIcon.BELL);
                t1.setFill(Color.BROWN);
                t1.setSize("20");
                t1.setLayoutX(755);
                t1.setLayoutY(85);
                t1.setCursor(Cursor.HAND);
                /**
                 * ************* participate *******************
                 */

                t1.setOnMouseClicked((MouseEvent event) -> {

                    System.out.println("aaaaaaa");
                    evenement.setId_courant(p3.getId());

                    ParticiperController part = new ParticiperController();

                    try {
                        part.part(p3);
                    } catch (SQLException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(AfficheEvController.class.getName()).log(Level.SEVERE, null, ex);
                    }

                    t1.setIcon(FontAwesomeIcon.BELL_SLASH);
                    t1.setDisable(true);

                });

                /**
                 * ********************************************
                 */
                /**
                 * ********** quick view**********
                 */
                /**
                 * ********************************************
                 */
                FontAwesomeIconView tq = new FontAwesomeIconView(FontAwesomeIcon.EXPAND);
                tq.setFill(Color.BLACK);
                tq.setSize("20");

                tq.setCursor(Cursor.HAND);
                /**
                 * ********** quick view**********
                 */

                tq.setOnMouseClicked((MouseEvent event) -> {
                    evenement.setId_courant(p3.getId());

                    try {
                        Scene scene = new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/Quickview.fxml")));

                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();
                    } catch (IOException e) {
                        Logger logger = Logger.getLogger(getClass().getName());
                        logger.log(Level.SEVERE, "Failed to create new Window.", e);
                    }
                });

                /**
                 * ******************************
                 */
                /**
                 * ******************
                 */
                //                Text t1=new Text("acheter");        
                HBox hb = new HBox(tq);
                HBox hb2 = new HBox(t1);

                FontAwesomeIconView users = new FontAwesomeIconView(FontAwesomeIcon.USERS);
                users.setFill(Color.DARKBLUE);
                users.setSize("15");

                users.setLayoutX(710);
                users.setLayoutY(185);

                Label nbpart = new Label(String.valueOf(p3.getParticiate()));

                nbpart.setLayoutX(740);
                nbpart.setLayoutY(165);

                hb.setLayoutX(0);
                hb.setLayoutY(170);
                hb.setPrefWidth(hb.getWidth() + 190);
                hb.setPrefHeight(hb.getHeight() + 75);
                hb.setStyle("-fx-background-color: #2cbae3");

                hb2.setLayoutX(173);
                hb2.setLayoutY(170);
                hb2.setPrefWidth(hb.getWidth() + 45);
                hb2.setPrefHeight(hb.getHeight() + 75);
                hb2.setStyle("-fx-background-color: #2cbae3");
                pane2.getChildren().addAll(hb, hb2);

                String A = p3.getImage();
                A = "C:\\wamp64\\www\\datatable_21\\web\\" + A;
                File F1 = new File(A);
                Image image2 = new Image(F1.toURI().toString());

                ImageView image = new ImageView();
                image.setFitWidth(130);
                image.setFitHeight(140);
                image.setStyle("-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.5), 8, 0, 0, 0);");

                image.setImage(image2);
                image.setLayoutX(45);
                image.setLayoutY(-35);
                pane2.getChildren().add(image);

                Text nomt = new Text("Nom : ");
                Label nom = new Label(p3.getNom());
                Text prixt = new Text("Type : ");
                Label prix = new Label(p3.getType());
                nomt.setLayoutX(600);
                nomt.setLayoutY(180);
                nom.setLayoutX(650);
                nom.setLayoutY(165);
                prixt.setLayoutX(600);
                prixt.setLayoutY(200);
                prix.setLayoutX(650);
                prix.setLayoutY(185);
                nomt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");
                prixt.setStyle("-fx-font-weight: bold;-fx-fill : #ce3b67");

                /**
                 * ************* web view ***************
                 */
                FontAwesomeIconView web = new FontAwesomeIconView(FontAwesomeIcon.YOUTUBE_PLAY);
                web.setFill(Color.RED);
                web.setSize("20");

                web.setCursor(Cursor.HAND);

                web.setLayoutX(650);
                web.setLayoutY(240);

                web.setOnMouseClicked((MouseEvent event) -> {
                    evenement.setId_courant(p3.getId());
                    String a = p3.getLien();
                    WebView webview1 = new WebView();
                    webview1.getEngine().load(
                            a
                    );
                    webview1.setPrefSize(640, 390);
                    Scene scene = new Scene(webview1);
                    Stage stage = new Stage();
                    stage.setScene(scene);
                    stage.show();
                });

                /**
                 * **********************************************
                 */
                pane.getChildren().addAll(pane2, nomt, prixt, nom, prix, nbpart, users, web);

            }
            k++;

        }
        return pane;
    }

    @FXML
    private void search(KeyEvent event) {

        recherche = rechercher.getText();
        getShowPane();

    }

    @FXML
    private void searchDate(MouseEvent event) {

    }
}
