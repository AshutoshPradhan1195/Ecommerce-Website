
package controller.servlets.signin;

import controller.dbconnection.DBConnection;
import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import model.User;
import resources.myConstants;

@WebServlet(asyncSupported=true, urlPatterns={"/signInServlet"})
@MultipartConfig(fileSizeThreshold=0x200000, maxFileSize=0xA00000L, maxRequestSize=0x3200000L)
public class SignInServlet
extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBConnection con;
        String fName = request.getParameter("userFName");
        String lName = request.getParameter("userLName");
        String email = request.getParameter("userEmail");
        String password = request.getParameter("userPassword");
        String contactNo = request.getParameter("userPhone");
        String userAdd = request.getParameter("userAddress");
        String userCCN = request.getParameter("userCCN");
        String userBirth = request.getParameter("userBirth");
        Part userImg = request.getPart("userImg");
        User user = new User(fName, lName, userAdd, email, userImg, userCCN, userBirth, contactNo);
        String savePath = myConstants.IMAGE_DIR_SAVE_PATH;
        String fileName = user.getImageUrlFromPart();
        if (!fileName.isEmpty() && fileName != null) {
            userImg.write(String.valueOf(savePath) + fileName);
        }
        if ((con = new DBConnection()).isUserRegistered(email).booleanValue()) {
            response.sendRedirect(String.valueOf(request.getContextPath()) + "/pages/login.jsp");
        } else {
            request.setAttribute("errorMessage", (Object)"User Email already exists");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/signIn.jsp");
            dispatcher.forward((ServletRequest)request, (ServletResponse)response);
        }
    }
}