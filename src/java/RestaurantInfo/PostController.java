/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package RestaurantInfo;

import java.util.List;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.sql.*;
import java.util.ArrayList;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;

/**
 *
 * @author Saurabh
 */
@ManagedBean
@ApplicationScoped
public class PostController {

    private List<post> posts;
    private post currentPost;
    private String searchValue;
    int size;

    /**
     *
     * @return
     */
    public int getSize() {
        return count();
    }

    /**
     *
     * @param size
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     *
     * @return
     */
    public String getSearchValue() {
        return searchValue;
    }

    /**
     *
     * @param searchValue
     */
    public void setSearchValue(String searchValue) {
        this.searchValue = searchValue;
    }

    /**
     * Retrieve the List of Post objects
     *
     * @return the List of Post objects
     */
    public List<post> getPosts() {
        return posts;
    }

    /**
     * Retrieve the current Post
     *
     * @return the registered Current Post
     */
    public post getCurrentPost() {
        return currentPost;
    }

    /**
     * No-arg Constructor -- sets up list from DB
     */
    public PostController() {
        currentPost = new post(-1, "", "", "", "", "", "", "", -1.0, -1.0, "", "", "", "");
        getPostFromDB();
    }

    /**
     * Wipe the Posts list and update it from the DB
     */
    public void getPostFromDB() {
        Connection conn;
        try {
            conn = utils.getConnection();

            posts = new ArrayList<>();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select *from bistro");
            while (rs.next()) {
                post p = new post(rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("menu"),
                        rs.getString("price"),
                        rs.getString("phone"),
                        rs.getString("address"),
                        rs.getString("postal_code"),
                        rs.getString("country_code"),
                        rs.getDouble("latitude"),
                        rs.getDouble("longitude"),
                        rs.getString("mobile_url"),
                        rs.getString("rating"),
                        rs.getString("rating_image_url"),
                        rs.getString("snippet_text"));
                posts.add(p);

            }
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
            // This Fails Silently -- Sets Post List as Empty
            posts = new ArrayList<>();
        }

    }

    /**
     * Retrieve a Post by ID
     *
     * @param id the ID to search for
     * @return the post -- null if not found
     */
    public post getPostById(int id) {
        for (post p : posts) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    /**
     * Retrieve a Post by title
     *
     * @param title the title to search for
     * @return the post -- null if not found
     */
    public String getPostByTitle(String title) {
        for (post p : posts) {
            if (p.getName().equals(title)) {
                currentPost = p;
                return "newjsf";
            }
        }
        return null;
    }

    /**
     * Navigate to a specific post to view
     *
     * @param post the post to view
     * @return the navigation rule
     */
    public String viewPost(post post) {
        currentPost = post;
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        Map<String, Object> sessionMap = externalContext.getSessionMap();
        sessionMap.put("restraunID", currentPost.id);
        return "each";
    }

    /**
     *
     * @param post
     * @return
     */
    public String adminPost(post post) {
        currentPost = post;

        return "edit";
    }

    /**
     *
     * @return
     */
    public int count() {
        Connection conn;
        try {
            conn = utils.getConnection();
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("select coutn(*) as count from bistro");
            size = rs.getInt("count");

        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }

        return size;
    }

    /**
     *
     * @return
     */
    public String editPost() {
        Connection con;

        try {
            con = utils.getConnection();

            PreparedStatement pst = con.prepareStatement("Update bistro SET name = ?, menu = ?, price = ?, phone = ?, "
                    + "address = ?, postal_code = ?, country_code = ?, latitude = ?, longitude = ?, mobile_url = ?, "
                    + "rating = ?, snippet_text = ? where id = ?");

            pst.setString(1, currentPost.getName());
            pst.setString(2, currentPost.getMenu());
            pst.setString(3, currentPost.getPrice());
            pst.setString(4, currentPost.getPhone());
            pst.setString(5, currentPost.getAddress());
            pst.setString(6, currentPost.getPostal_code());
            pst.setString(7, currentPost.getCountry_code());
            pst.setDouble(8, currentPost.getLatitude());
            pst.setDouble(9, currentPost.getLongitude());
            pst.setString(10, currentPost.getMobile_url());
            pst.setString(11, currentPost.getRating());
            pst.setString(12, currentPost.getSnippet_text());
            pst.setInt(13, currentPost.getId());

            pst.executeUpdate();
            con.close();

        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostFromDB();
        return "admin";
    }

    /**
     *
     * @return
     */
    public String addPost() {
        Connection conn;

        try {
            conn = utils.getConnection();

            PreparedStatement pst = conn.prepareStatement("Insert into bistro(id, name, menu, price, phone, address, postal_code, country_code, latitude, longitude, mobile_url, rating, rating_image_url, snippet_text) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

            pst.setInt(1, currentPost.getId());
            pst.setString(2, currentPost.getName());
            pst.setString(3, currentPost.getMenu());
            pst.setString(4, currentPost.getPrice());
            pst.setString(5, currentPost.getPhone());
            pst.setString(6, currentPost.getAddress());
            pst.setString(7, currentPost.getPostal_code());
            pst.setString(8, currentPost.getCountry_code());
            pst.setDouble(9, currentPost.getLatitude());
            pst.setDouble(10, currentPost.getLongitude());
            pst.setString(11, currentPost.getMobile_url());
            pst.setString(12, currentPost.getRating());
            pst.setString(13, currentPost.getRating_image_url());
            pst.setString(14, currentPost.getSnippet_text());

            int i = pst.executeUpdate();
            getPostFromDB();
            return "admin";

        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     *
     * @return
     */
    public String deletePost() {
        Connection con;

        try {
            con = utils.getConnection();
            PreparedStatement pst = con.prepareStatement("Delete from bistro where id = ? ");
            pst.setInt(1, currentPost.getId());
            pst.executeUpdate();
            con.close();
        } catch (SQLException ex) {
            Logger.getLogger(PostController.class.getName()).log(Level.SEVERE, null, ex);
        }
        getPostFromDB();
        return "admin";
    }

}
