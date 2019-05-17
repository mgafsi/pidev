/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package  com.thebenchwarmers.View.Controller;

import com.thebenchwarmers.utility.User;
import com.thebenchwarmers.utility.evenement;
import com.thebenchwarmers.utility.participer;
import java.io.IOException;
import static java.lang.System.in;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import static javax.management.Query.and;
import org.controlsfx.control.Notifications;
import com.thebenchwarmers.utility.MyServiceEvenement;
//import util.Authentification;
import com.thebenchwarmers.utility.ConnectionDB;
import com.thebenchwarmers.utility.CurrentUser;
import com.thebenchwarmers.utility.Template;

/**
 *
 *
 */
public class ParticiperController {
   
    
//    private final User currentUser=Authentification.user;
    
       MyServiceEvenement service = new MyServiceEvenement();
           int currentUser = CurrentUser.id;

 
       
       
       
       public boolean test() throws SQLException{
     boolean t = false;
           int a;
           int e ;
           boolean b=false;
 int i=0;
 int k=1;
    evenement e1 = service.GetEvbyid(evenement.getId_courant());
       int[] anArray = new int[100];
           String query="select * from participer where ide="+e1.getId();
           Statement st= ConnectionDB.getInstance().getConnection().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next() && i<100)
        {   
         
          a= rest.getInt("ide");
          
        anArray[i]=a;
              i++;
            
            }
      
            
           for (int j = 0; j < anArray.length; j++) {
               
           
           if ( anArray[j]==e1.getId()   ){   
         t=true;
         b=t;
           }
     
          }
        
        return b;
   
       }
       
       
       /******************
        * 
        * 
        */
       
         public boolean testdatemawjoud() throws SQLException{
     boolean t = false;
             Date a = null ;
              Date j;
          java.util.Date e ;
           boolean b=false;

 int f=0;
 int k=1;
    evenement e1 = service.GetEvbyid(evenement.getId_courant());
        Date[] anArray = new Date[100];
           String query="select * from participer where ide="+e1.getId();
           Statement st= ConnectionDB.getInstance().getConnection().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next())
        {   
         
          a= rest.getDate("datedeb");
          
    
            
            }
      
            
             
//             String query1="select * from participer where idu="+currentUser.getId();
             String query1="select * from participer where idu="+currentUser;
           Statement st1= ConnectionDB.getInstance().getConnection().createStatement();
           ResultSet rest1 = st1.executeQuery(query1);  
             while(rest1.next() && f<100)
        {   
         
            
          j= rest1.getDate("datedeb");
          
    anArray[f]=j;
            f++;
            }
             System.out.println("_________________________________");
             
             
//             for (int y = 0; y < 10; y++) {
//                 System.out.println( "les date____"+anArray[y]);
//             }
             
             
             
             for (int i = 0; i < anArray.length; i++) {
                 if(anArray[i]!=null){
                    if (anArray[i].equals(a)) {
                
                     b=true;
                  
                            }
                 
                     else{
                  
                            }
                
             }
             
             
             
         
        
        
   
       }
       
         
       /**********************************/
           System.out.println("//////////////////////////////////"+b);
       return  b;
         
         }       
       
       
       
       
       
       
       
        public boolean testuser() throws SQLException{
     boolean t = false;
      boolean b = false;
           int a;
           int e ;
 int i=0;
 int k=1;
    evenement e1 = service.GetEvbyid(evenement.getId_courant());
       int[] anArray = new int[100];

           String query="select * from participer where ide="+e1.getId();
           Statement st= ConnectionDB.getInstance().getConnection().createStatement();
           ResultSet rest = st.executeQuery(query);  
             while(rest.next() && i<100)
        {   
         
          a= rest.getInt("idu");
          
        anArray[i]=a;
              i++;
            
            }
          
        
         
            for (int j = 0; j < 100; j++) {
//                if ( anArray[j]==currentUser.getId()  ){   
                if ( anArray[j]==1  ){   
         t=true;
         b=t;
           }  
            }
  
     
          
       return b;
       }
       
       /****************************/
       
       
       
       
       
       
    public void part(evenement e11) throws SQLException, IOException{
          final evenement e =new evenement();
          participer p=new participer();
           evenement e1 = service.GetEvbyid(evenement.getId_courant());
           System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxzzzzzzzzzzzzzzzzzz");
        System.out.println("test id ev"+test());
   System.out.println("test id user"+testuser());
        System.out.println("---------________________"+testdatemawjoud());
        System.out.println("---------------------");
   

            if (((test()==false && testuser()==false) ) ||((test()==true && testuser()==false )) ) {
            
     if (testdatemawjoud()==false){
               e1.setParticiate(e1.getParticiate()+1);
   ;
        service.participate(e1);
//        p.setIdu(currentUser.getId());
        p.setIdu(currentUser);
           p.setType(e1.getType());
           p.setNomorg(e1.getNomorg());
             p.setNom(e1.getNom());
           p.setPrix(e1.getPrix());
           p.setImage(e1.getImage());
           p.setAdresse(e1.getAdresse());
           p.setDatedeb(e1.getDatedeb());
           p.setDatefin(e1.getDatefin());
           p.setDescription(e1.getDescription());
        
        service.insertionparticipation(p,e1.getId());
                                 Notifications n =Notifications.create().title("Notification")
             .text("Participation effectuer avec success")
             .graphic(null)
             .position(Pos.BASELINE_LEFT)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showConfirm();  
            
                  
    Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/com/thebenchwarmers/View/MyMenuFXML.fxml"))));             

     }
     
     else { Notifications n =Notifications.create().title("Notification")
             .text("vous étes déja participer dans un autre evenement de meme date")
             .graphic(null)
             .position(Pos.CENTER)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showError();}
     
        }    
            else {
            
                
                
                
          Notifications n =Notifications.create().title("Notification")
             .text("Vous étes déja  participer")
             .graphic(null)
             .position(Pos.CENTER)
             .onAction(new  EventHandler<ActionEvent>() {
             
             public void handle (ActionEvent event){
                 System.out.println("notifocation");
             }
             });
     n.showWarning();
            /*************/
            
                                        
                      //  template.Template.getInstance().changescene(new Scene(FXMLLoader.load(getClass().getResource("/GUI/MyMenuFXML.fxml"))));             

            
            }
   
        
     
        
        
    };
    
    
    
    
    
    

    
}
