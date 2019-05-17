/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.View.Controller;

import com.google.zxing.qrcode.QRCodeWriter;
import com.thebenchwarmers.utility.evenement;
import java.io.File;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import com.thebenchwarmers.utility.MyServiceEvenement;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 */
public class quickview implements Initializable {

    @FXML
    private ImageView p_image;
    @FXML
    private Text nom_p;
    @FXML
    private Text p_prix;
    @FXML
    private Label p_desc;

    /**
     * Initializes the controller class.
     */
    evenement p1 = new evenement();
    MyServiceEvenement service = new MyServiceEvenement();

    @FXML
    private Text nomorgt;
    @FXML
    private Text adresset;
    @FXML
    private Text date;
    @FXML
    private ImageView qrcode;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        try {
            p1 = service.GetEvbyid(evenement.getId_courant());
        } catch (SQLException ex) {
            Logger.getLogger(quickview.class.getName()).log(Level.SEVERE, null, ex);
        }

        //qrcode
        QRCodeWriter qrCodeWriter = new QRCodeWriter();
        String myWeb = "La cité de culture organise un événement dont le nom est "+p1.getNom()+" la date de debut est "+p1.getDatedeb()+" et la date de fin est "+p1.getDatefin() +" , c'est votre ocasion de participer !!";
        int width = 300;
        int height = 300;
        String fileType = "png";
        BufferedImage bufferedImage = null;

        try {
            BitMatrix byteMatrix = qrCodeWriter.encode(myWeb, BarcodeFormat.QR_CODE, width, height);
            bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
            bufferedImage.createGraphics();

            Graphics2D graphics = (Graphics2D) bufferedImage.getGraphics();
            graphics.setColor(Color.WHITE);
            graphics.fillRect(0, 0, width, height);
            graphics.setColor(Color.BLACK);

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (byteMatrix.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }

            System.out.println("Success...");
        } catch (WriterException ex) {
            Logger.getLogger(quickview.class.getName()).log(Level.SEVERE, null, ex);
        }

        ImageView qrView = new ImageView();
//        BufferedImage bufferedImage = null;
        qrView.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        qrcode.setImage(SwingFXUtils.toFXImage(bufferedImage, null));
        StackPane root = new StackPane();
        root.getChildren().add(qrView);

        //qrcode
        nom_p.setText(p1.getNom());
        p_prix.setText(String.valueOf(p1.getPrix()));
        p_desc.setText(p1.getDescription());
        adresset.setText(p1.getAdresse());

        nomorgt.setText("  " + p1.getNomorg());
        String a = p1.getImage();

        a = "C:\\wamp64\\www\\datatable_21\\web\\" + a;
        System.out.println(p1.getImage());
        File file = new File(a);

        Image image1 = new Image(file.toURI().toString());

        p_image.setImage(image1);

        date.setText(p1.getDatedeb().toString() + " / " + p1.getDatefin().toString());
    }

}
