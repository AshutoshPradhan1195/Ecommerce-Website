
package resources;

import java.io.File;

public class myConstants {
    public static final String Driver_Name = "com.mysql.jdbc.Driver";
    public static final String Db_Url = "jdbc:mysql://localhost:3306/empyrean";
    public static final String Db_User = "root";
    public static final String Db_Pass = "";
    public static final String Check_Email = "Select customer_email from customers Where customer_email = ?";
    public static final String Get_Password_By_Email = "Select user_pass from login_info Where user_email = ?";
    public static final String Get_All_Products = "Select * from products";
    public static final String Insert_Into_Customer = "INSERT INTO customers(customerFname,customerLname, customer_email, customer_contact, delivery_address, cc_number, customer_birthDate,image_link) VALUES(?,?,?,?,?,?,?,? )";
    public static final String Insert_Into_Login_Info = "INSERT INTO login_info(user_email, user_pass) VALUES (?,?)";
    public static final String IMAGE_DIR = "xampp\\tomcat\\webapps\\images\\";
    public static final String IMAGE_DIR_SAVE_PATH = "C:" + File.separator + "xampp\\tomcat\\webapps\\images\\";
}
