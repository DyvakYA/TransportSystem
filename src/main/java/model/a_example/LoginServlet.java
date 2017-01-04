package model.a_example;

/**
 * Created by Dyvak on 28.12.2016.
 */
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LoginServlet
 */
public class LoginServlet extends HttpServlet {


    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {

        try
        {

            UserBean user = new UserBean();
            user.setUserName(request.getParameter("login"));
            user.setPassword(request.getParameter("password"));
            //System.out.println(user.getUsername()+user.getPassword());

            user = UserDAO.login(user);

            if (user.isValid())
            {

                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser",user);
                response.sendRedirect("userLogged.jsp"); //logged-in page
            }

            else
                response.sendRedirect("invalidLogin.jsp"); //error page
        }


        catch (Throwable theException)
        {
            System.out.println(theException);
        }
    }
}