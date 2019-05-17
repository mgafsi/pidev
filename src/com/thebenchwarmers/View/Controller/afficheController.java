/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.thebenchwarmers.View.Controller;

import com.sun.prism.impl.Disposer.Record;
import com.thebenchwarmers.utility.User;
import com.thebenchwarmers.utility.evenement;
import com.thebenchwarmers.utility.MyServiceEvenement;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.util.Callback;
import com.thebenchwarmers.utility.Template;


/**
 *
 * 
 */
public class afficheController implements Initializable{
    
    MyServiceEvenement service_pr=new MyServiceEvenement();
    
    
    private TableView table = new TableView();
    
    private ListView<User> allproducts_LV;
    
    @FXML
    private TableView<User> table_view;
    
  
    
    
    
    @FXML
    private TableColumn<User, String> nom_ev;
    @FXML
    private TableColumn<User, String> adresse_ev;
    @FXML
    private Button supp;

   

    
    
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        
         ObservableList<User> list= FXCollections.observableArrayList();
        for (User p:service_pr.afficheUser())
        {
            list.add(p);
            
        }
      
 nom_ev.setCellValueFactory(new PropertyValueFactory<>("username"));
       
 adresse_ev.setCellValueFactory(new PropertyValueFactory<>("email"));

      
  


  
                
  TableColumn col_modifier = new TableColumn<>("INVITER");
        table_view.getColumns().add(col_modifier);
        
        col_modifier.setCellValueFactory(
                new Callback<TableColumn.CellDataFeatures<Record, Boolean>, 
                ObservableValue<Boolean>>() {

            @Override
            public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Record, Boolean> p) {
                return new SimpleBooleanProperty(p.getValue() != null);
            }
        });

        //Adding the Button to the cell
        col_modifier.setCellFactory(
                new Callback<TableColumn<Record, Boolean>, TableCell<Record, Boolean>>() {

            @Override
            public TableCell<Record, Boolean> call(TableColumn<Record, Boolean> p) {
                return new ButtonCell2();
            }
        
        });
        

        
        
        
        
        table_view.setItems(list);
        
        
    }

    
    
    
    private void retourner(ActionEvent event) throws IOException {
      
        
        
            Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/AjouterEvFXML.fxml"))));             
    }

    @FXML
    private void close(MouseEvent event) throws IOException {
                    Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"))));             

    }

    
}
