package servlet;

import model.User;
import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/dologin")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        User u = UserService.getUserService().login(username, password);
        if(u != null){
            req.getSession().setAttribute("user", u);
            req.getRequestDispatcher("/personalcenter/shopping.jsp").forward(req, resp);
        }else {
            req.setAttribute("message", "<font style='color:red'>账号或密码错误</font>");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }
    }
}
