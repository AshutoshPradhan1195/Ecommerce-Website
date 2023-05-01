package controller.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.PasswordEncryption;
import model.User;
import resources.myConstants;

public class DBConnection {
    public static Connection getConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://localhost:3306/empyrean";
            String user = "root";
            String pass = "";
            Connection con = DriverManager.getConnection(url, user, pass);
            return con;
        }
        catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public ResultSet selectAllQuery(String query) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(query);
                ResultSet result = statement.executeQuery();
                return result;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public Boolean enterLoginInfo(String email, String password) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            try {
                PreparedStatement statement1 = con.prepareStatement(myConstants.Insert_Into_Login_Info);
                statement1.setString(1, email);
                statement1.setString(2, PasswordEncryption.encrypt(email, password));
                int result = statement1.executeUpdate();
                if (result == 0) {
                    return false;
                }
                if (result == 1) {
                    return true;
                }
                return false;
            }
            catch (SQLException ex) {
                return false;
            }
        }
        return null;
    }

    public Boolean registerNewUser(User user) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            try {
                PreparedStatement statement = con.prepareStatement(myConstants.Insert_Into_Customer);
                statement.setString(1, user.getFirstName());
                statement.setString(2, user.getLastName());
                statement.setString(3, user.getEmail());
                statement.setString(4, user.getUserContact());
                statement.setString(5, user.getUserAdd());
                statement.setString(6, user.getUserCCN());
                statement.setString(7, user.getUserBirth());
                statement.setString(8, user.getImageUrlFromPart());
                int result = statement.executeUpdate();
                if (result == 0) {
                    return false;
                }
                if (result == 1) {
                    return true;
                }
                return false;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }

    public Boolean isUserRegistered(String email) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement("Select customer_email from customers Where customer_email = ?");
                statement.setString(1, email);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    return false;
                }
                return true;
            }
            catch (SQLException e) {
                return false;
            }
        }
        return false;
    }

    public Boolean checkPassword(String query, String username, String password) {
        Connection dbConnection = DBConnection.getConnection();
        if (dbConnection != null) {
            try {
                PreparedStatement statement = dbConnection.prepareStatement(query);
                statement.setString(1, username);
                ResultSet result = statement.executeQuery();
                if (result.next()) {
                    String encryPass = result.getString("user_pass");
                    String decryPass = PasswordEncryption.decrypt((String)encryPass, (String)username);
                    try {
                        if (decryPass.equals(password)) {
                            return true;
                        }
                        return false;
                    }
                    catch (NullPointerException ex) {
                        return null;
                    }
                }
                return null;
            }
            catch (SQLException e) {
                return null;
            }
        }
        return null;
    }
}