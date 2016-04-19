/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestaurantInfo;

/**
 *
 * @author gurvinder singh
 */
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.FlowEvent;
import RestaurantInfo.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;

/**
 *
 * @author Saurabh
 */
@ManagedBean
@ViewScoped
public class UserWizard implements Serializable {

    private User user = new User();
    List<User> users ;

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
    

    private boolean skip;

    /**
     *
     * @return
     */
    public User getUser() {
        return user;
    }
    /**
     * 
     */
    public void getPostFromDB() {
        Connection conn;
        try {
            conn = utils.getConnection();

            users = new ArrayList<>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select comment from Comments");
            while (rs.next()) {
                User p = new User(rs.getString("comment"));
                users.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Post List as Empty
            users = new ArrayList<>();
        }

    }
    /**
     *
     * @param user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     *
     */
    public void save() {
        Connection conn;
        try {
            conn = utils.getConnection();
            
            PreparedStatement pst =conn.prepareStatement("Insert into users(email, first_name, last_name) values (?,?,?)");
            pst.setString(1, user.getEmail());
            pst.setString(2, user.getFirstname());
            pst.setString(3, user.getLastname());
            
            int val1 = pst.executeUpdate();
           ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
           Map<String, Object> sessionMap = externalContext.getSessionMap();
           
           Integer id = (Integer) sessionMap.get("restraunID");

            PreparedStatement ps = conn.prepareStatement("INSERT into comments (user_id, restaurant_id, comment) VALUES(?,?,?)");
            ps.setString(1, user.getEmail());
            ps.setInt(2, id);
            ps.setString(3, user.getInfo());
           
           ps.executeUpdate();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);

        }

        FacesMessage msg = new FacesMessage("Successfully posted your comment", "Welcome :" + user.getFirstname());
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    /**
     *
     * @return
     */
    public boolean isSkip() {
        return skip;
    }

    /**
     *
     * @param skip
     */
    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     *
     * @param event
     * @return
     */
    public String onFlowProcess(FlowEvent event) {
        if (skip) {
            skip = false;   //reset in case user goes back
            return "confirm";
        } else {
            return event.getNewStep();
        }
    }
}
