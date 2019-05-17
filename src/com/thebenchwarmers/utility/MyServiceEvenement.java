/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.thebenchwarmers.utility;

//import entities.User;
import com.thebenchwarmers.utility.evenement;
import com.thebenchwarmers.utility.notif;
import com.thebenchwarmers.utility.participer;
//import entities.produit_donation;
//import util.Authentification;
import com.thebenchwarmers.utility.ConnectionDB;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class MyServiceEvenement {

//               private final User currentUser= CurrentUser.class;
    int currentUser = CurrentUser.id;

    public void participate(evenement e) throws SQLException {
        //-------------------- Update ----------//

        String reqUp = "update evenement set particiate=? where id=?";
        PreparedStatement pss = ConnectionDB.getInstance().getConnection().prepareStatement(reqUp);

        pss.setInt(1, e.getParticiate());
        pss.setInt(2, e.getId());

        pss.executeUpdate();

    }

    public void UpdateEv(evenement b) throws SQLException {
        //-------------------- Update ----------//

//                String reqUp="update evenement set nom=? ,adresse=? ,description=? ,prix=? ,type=?, datedeb=?,datefin=?,image=? where id=?";
        String reqUp = "update evenement set nom=? ,adresse=? ,description=? ,prix=? ,type=?, datedeb=?,datefin=? where id=?";
        PreparedStatement pss = ConnectionDB.getInstance().getConnection().prepareStatement(reqUp);

        pss.setString(1, b.getNom());
        pss.setString(2, b.getAdresse());
        pss.setString(3, b.getDescription());
        pss.setInt(4, b.getPrix());
        pss.setString(5, b.getType());
        pss.setDate(6, (Date) b.getDatedeb());
        pss.setDate(7, (Date) b.getDatefin());
//     pss.setString(8, b.getImage());
        pss.setInt(8, b.getId());

        pss.executeUpdate();

    }

    public void insertionEv(evenement ev) {

        try {
            String req = "insert into evenement (nom,adresse,prix,description,datedeb,datefin,type,image,idu,nomorg,lien) values (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = ConnectionDB.getInstance().getConnection().prepareStatement(req);

            ps.setString(1, ev.getNom());
            ps.setString(2, ev.getAdresse());
            ps.setInt(3, ev.getPrix());
            ps.setString(4, ev.getDescription());
            ps.setDate(5, (Date) ev.getDatedeb());
            ps.setDate(6, (Date) ev.getDatefin());
            ps.setString(7, ev.getType());
            ps.setString(8, ev.getImage());
            ps.setInt(9, ev.getIdu());
            ps.setString(10, ev.getNomorg());
            ps.setString(11, ev.getLien());

            ps.executeUpdate();
            System.out.println(" ok ajouter");

        } catch (SQLException ex) {
//            Logger.getLogger(MyServiceDonation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * *******************************
     */
    public void insertionparticipation(participer ev, int ide) {

        try {
            String req = "insert into participer  (nom,adresse,prix,description,datedeb,datefin,type,image,idu,nomorg,ide) values (?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement ps = ConnectionDB.getInstance().getConnection().prepareStatement(req);

            ps.setString(1, ev.getNom());
            ps.setString(2, ev.getAdresse());
            ps.setInt(3, ev.getPrix());
            ps.setString(4, ev.getDescription());
            ps.setDate(5, (Date) ev.getDatedeb());
            ps.setDate(6, (Date) ev.getDatefin());
            ps.setString(7, ev.getType());
            ps.setString(8, ev.getImage());
            ps.setInt(9, ev.getIdu());
            ps.setString(10, ev.getNomorg());
            ps.setInt(11, ide);

            ps.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void insertionNotif(int ide, int idu, String nome, String nominviteur, String imagee, String adressee) {

        try {
            String req = "insert into notif (ide,idu,nome,nominviteur,imagee,adressee) values (?,?,?,?,?,?)";

            PreparedStatement ps = ConnectionDB.getInstance().getConnection().prepareStatement(req);

            ps.setInt(1, ide);
            ps.setInt(2, idu);

            ps.setString(3, nome);
            ps.setString(4, nominviteur);
            ps.setString(5, imagee);
            ps.setString(6, adressee);

            ps.executeUpdate();

        } catch (SQLException ex) {
//            Logger.getLogger(MyServiceDonation.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    /**
     * **************************************************************************
     */
    public List<evenement> afficheEv() {
        List ALLproducts = new ArrayList();

        try {
            String query = "select * from evenement where datedeb >= '" + LocalDateTime.now() + "'";
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                evenement pr = new evenement();

                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));

                pr.setType(rest.getString("type"));

                pr.setDescription(rest.getString("description"));
                pr.setLien(rest.getString("lien"));
                pr.setImage(rest.getString("image"));

                pr.setDatedeb(rest.getDate("datedeb"));
                pr.setDatefin(rest.getDate("datefin"));
                pr.setParticiate(rest.getInt("particiate"));
                pr.setNomorg(rest.getString("nomorg"));
                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * ************user
     *
     *
     *
     *
     *
     ********************************************************************************
     */
    public List<User> afficheUser() {
        List ALLproducts = new ArrayList();
        try {
//           String query="select * from fos_user where id !="+currentUser.getId();
            String query = "select * from user where userId !=" + currentUser;
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                User pr = new User();

                pr.setUserId(rest.getInt("userId"));
                pr.setUsername(rest.getString("username"));
                pr.setEmail(rest.getString("email"));

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * **********
     */
    public List<evenement> MesEv() {
        List ALLproducts = new ArrayList();
        try {
            String query = "select * from evenement  where idu=" + currentUser;
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                evenement pr = new evenement();

                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));

                pr.setType(rest.getString("type"));

                pr.setDescription(rest.getString("description"));

                pr.setImage(rest.getString("image"));
                pr.setParticiate(rest.getInt("particiate"));
                pr.setDatedeb(rest.getDate("datedeb"));

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    public List<notif> MesNotif() {
        List ALLproducts = new ArrayList();
        try {
//           String query="select DISTINCT ide,nome,nominviteur,imagee,ide,idu,adressee from notif  where  idu="+currentUser.getId();
            String query = "select DISTINCT ide,nome,nominviteur,imagee,ide,idu,adressee from notif  where  idu=" + currentUser;
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                notif pr = new notif();

                pr.setIdu(rest.getInt("idu"));
                pr.setIde(rest.getInt("ide"));
                pr.setNome(rest.getString("nome"));
                pr.setNominviteur(rest.getString("nominviteur"));
                pr.setAdressee(rest.getString("adressee"));
                pr.setImagee(rest.getString("imagee"));
                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * ********************************************************************************************
     */
    public int nbr() {
        int nbrnotif;
        try {
//           String query="select count(DISTINCT ide) as nb from notif where idu="+currentUser.getId();
            String query = "select count(DISTINCT ide) as nb from notif where idu=" + currentUser;
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {

                return rest.getInt("nb");

            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return 0;

    }

    /**
     * **********************************************
     */
    public List<evenement> MesPart() {
        List ALLproducts = new ArrayList();
        try {
//           String query="select * from participer  where idu="+currentUser.getId();
            String query = "select * from participer  where idu=" + currentUser;
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                evenement pr = new evenement();

                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));

                pr.setType(rest.getString("type"));

                pr.setDescription(rest.getString("description"));

                pr.setImage(rest.getString("image"));
                pr.setParticiate(rest.getInt("particiate"));
                pr.setDatedeb(rest.getDate("datedeb"));

                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * *****************************************
     */
    public void deleteEv(evenement e, int id) throws SQLException {

        String reqD = "delete from evenement  where id=?";
        PreparedStatement pss = ConnectionDB.getInstance().getConnection().prepareStatement(reqD);
        pss.setInt(1, id);

        pss.executeUpdate();

    }

    public evenement GetEvbyid(int b) throws SQLException {
        //-------------------- Update ----------//
        evenement pr = new evenement();

        String query = "select * from evenement where id = ? ";
        PreparedStatement ps;
        try {
            ps = ConnectionDB.getInstance().getConnection().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {

                pr.setNomorg(rest.getString("nomorg"));
                pr.setParticiate(rest.getInt("particiate"));
                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));
                pr.setType(rest.getString("type"));
                pr.setPrix(rest.getInt("prix"));
                pr.setDescription(rest.getString("description"));
                pr.setImage(rest.getString("image"));
                pr.setDatedeb(rest.getDate("datedeb"));
                pr.setDatefin(rest.getDate("datefin"));
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;

    }

    /**
     * ********************************************************************************************
     */
    public User GetUserbyid(int b) throws SQLException {
        //-------------------- Update ----------//
        User pr = new User();

        String query = "select * from fos_user where id = ? ";
        PreparedStatement ps;
        try {
            ps = ConnectionDB.getInstance().getConnection().prepareCall(query);
            ps.setInt(1, b);
            ResultSet rest = ps.executeQuery();

            while (rest.next()) {

                pr.setUsername(rest.getString("username"));
                pr.setEmail(rest.getString("email"));

            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pr;

    }

    public List<evenement> RechercheEvenementParNom(String recherche) {
        List ALLproducts = new ArrayList();
        try {
            String query = "select * from evenement WHERE nom LIKE '%" + recherche + "%';";
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                evenement pr = new evenement();

                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));

                pr.setType(rest.getString("type"));

                pr.setDescription(rest.getString("description"));
                pr.setLien(rest.getString("lien"));
                pr.setImage(rest.getString("image"));

                pr.setDatedeb(rest.getDate("datedeb"));
                pr.setParticiate(rest.getInt("particiate"));
                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * *****************************************
     */
    public List<evenement> RechercheParDate(Date d1, Date d2) {
        List ALLproducts = new ArrayList();
        try {
            String query = "select * from evenement where datedeb >= '" + d1 + "' AND  datefin <= '" + d2 + "'";
            Statement st = ConnectionDB.getInstance().getConnection().createStatement();
            ResultSet rest = st.executeQuery(query);
            while (rest.next()) {
                evenement pr = new evenement();

                pr.setId(rest.getInt("id"));
                pr.setNom(rest.getString("nom"));
                pr.setAdresse(rest.getString("adresse"));

                pr.setType(rest.getString("type"));

                pr.setDescription(rest.getString("description"));
                pr.setLien(rest.getString("lien"));
                pr.setImage(rest.getString("image"));

                pr.setDatedeb(rest.getDate("datedeb"));
                pr.setParticiate(rest.getInt("particiate"));
                ALLproducts.add(pr);
            }

        } catch (SQLException ex) {
            Logger.getLogger(MyServiceEvenement.class.getName()).log(Level.SEVERE, null, ex);
        }

        return ALLproducts;

    }

    /**
     * **************
     */
}
