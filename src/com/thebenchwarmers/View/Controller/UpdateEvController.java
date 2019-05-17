/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.View.Controller;

import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import com.thebenchwarmers.utility.evenement;
import com.thebenchwarmers.utility.MyServiceEvenement;
import com.thebenchwarmers.utility.Template;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import static java.time.LocalDate.of;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.util.Callback;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 *
 */
public class UpdateEvController implements Initializable {

    @FXML
    private TextField nom;
    @FXML
    private Button update_evenement;
    @FXML
    private JFXTextField adresse;
    @FXML
    private JFXTextArea description;
    @FXML
    private RadioButton gratuit;
    @FXML
    private RadioButton payant;

    private ToggleGroup toggle;
    @FXML
    private TextField prix_ev;
    @FXML
    private JFXDatePicker datedeb;
    @FXML
    private JFXDatePicker datefin;

    @FXML
    private ImageView image_p;

    private String file_image;
    private Path pathfrom;
    private Path pathto;
    private File Current_file;

    @FXML
    private void update_evenement(ActionEvent event) throws SQLException, IOException {
        P.setNom(nom.getText());
        P.setAdresse(adresse.getText());
        P.setDescription(description.getText());

        System.out.println(P.getImage());
        if (this.toggle.getSelectedToggle().equals(this.payant)) {
            P.setPrix(Integer.parseInt(prix_ev.getText()));
            P.setType("Payant");
        } else {
            P.setPrix(0);
            P.setType("Gratuit");

        }

        Date datedeb = Date.valueOf(this.datedeb.getValue());
        Date datefin = Date.valueOf(this.datefin.getValue());
        P.setDatedeb(datedeb);
        P.setDatefin(datefin);

//            file_image="/images/"+file_image;
//   P.setImage(file_image);
//        
//   
//   
//        
//            pathfrom = FileSystems.getDefault().getPath(Current_file.getPath());
//            pathto = FileSystems.getDefault().getPath("C:\\xampp\\htdocs\\datatable_21\\web\\images\\"+Current_file.getName());
//            Path targetDir = FileSystems.getDefault().getPath("C:\\xampp\\htdocs\\datatable_21\\web\\images\\");
//        System.out.println(targetDir);
//                    Files.copy(pathfrom, pathto,StandardCopyOption.REPLACE_EXISTING);
//         
//        
        service_pr.UpdateEv(P);

        Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"))));

    }

    MyServiceEvenement service_pr = new MyServiceEvenement();

    private evenement P;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        toggle = new ToggleGroup();
        this.payant.setToggleGroup(toggle);
        this.gratuit.setToggleGroup(toggle);
        this.prix_ev.setVisible(false);

        try {

            P = service_pr.GetEvbyid(evenement.getId_courant());

        } catch (SQLException ex) {
            Logger.getLogger(UpdateEvController.class.getName()).log(Level.SEVERE, null, ex);
        }

        nom.setText(P.getNom());
        adresse.setText(P.getAdresse());
        description.setText(P.getDescription());
        prix_ev.setText(String.valueOf(P.getPrix()));

        if (P.getType() == "Payant") {
            payant.setSelected(true);
            gratuit.setSelected(false);
        } else {
            gratuit.setSelected(true);
            payant.setSelected(false);

        }

        java.util.Date e = P.getDatefin();
//        datedeb.setValue(LocalDate.of(P.getDatefin().getYear(), P.getDatefin().getMonth(), P.getDatefin().getDay()));
        datedeb.setValue(LocalDate.now());
//        datefin.setValue(LocalDate.of(P.getDatefin().getYear(), P.getDatefin().getMonth(), P.getDatefin().getDay()));
        datefin.setValue(LocalDate.now());
//        datefin.setValue(e);
        String a = P.getImage();

        a = "C:\\xampp\\htdocs\\datatable_21\\web\\" + a;
        System.out.println(P.getImage());
        File file = new File(a);

        Image image1 = new Image(file.toURI().toString());

        image_p.setImage(image1);

//BufferedImage img1;
//        try {
//            img1 = ImageIO.read(new File(P.getImage()));
//        } catch (IOException ex) {
//            Logger.getLogger(UpdateEvController.class.getName()).log(Level.SEVERE, null, ex);
//        }
// 
//image_p.dr(P.getImage(), 50 , 50 , this);
//image_p.setImage(new Image("C:\\xampp\\htdocs\\datatable_21\\web\\images\\"+P.getImage()));
//payant.setText(P.getType());
//        if (P.getType().equals("Payant")){
//              toggle.getSelectedToggle().equals(payant);
//
//        }c
//  else {
//           toggle.getSelectedToggle().equals(gratuit);
//
//        }
// TODO
    }

    @FXML
    private void change(ActionEvent event) {
        if (this.toggle.getSelectedToggle().equals(this.payant)) {

            this.prix_ev.setVisible(true);
        } else {
            this.prix_ev.setVisible(false);
        }

    }

    @FXML
    private void imageDragOver(DragEvent event) {

        Dragboard board = event.getDragboard();
        if (board.hasFiles()) {
            event.acceptTransferModes(TransferMode.ANY);
        }

    }

    @FXML
    private void imageDropped(DragEvent event) throws FileNotFoundException {
        Dragboard board = event.getDragboard();
        List<File> phil = board.getFiles();
        FileInputStream fis;
        fis = new FileInputStream(phil.get(0));
        Image image = new Image(fis);
        File selectedFile = phil.get(0);
        if (selectedFile != null) {

            String test = selectedFile.getAbsolutePath();
            System.out.println(test);

            Current_file = selectedFile.getAbsoluteFile();
            file_image = Current_file.getName();
            evenement e = new evenement();
            e.setImage(selectedFile.getName());
            image_p.setImage(image);

        }
    }
}
