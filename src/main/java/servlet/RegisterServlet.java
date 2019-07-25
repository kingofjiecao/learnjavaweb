package servlet;

import service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/doregister" )
public class RegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        boolean result = UserService.getUserService().register(username, password);
        if (result == false){
            req.setAttribute("message", "注册失败！");
            req.getRequestDispatcher("/register.jsp").forward(req, resp);
        }else{
            req.setAttribute("message", "注册成功！");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
        }
    }
}
