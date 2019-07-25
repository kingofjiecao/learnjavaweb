package servlet;

import model.Item;
import model.User;
import service.UserService;
import util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;


@WebServlet(urlPatterns = "/doshopping")
public class ShoppingServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String item = req.getParameter("item");
        int userid = ((User)req.getSession().getAttribute("user")).getId();
        boolean result = UserService.getUserService().shopping(userid, item);
        if (result){
            List<Item> list = JDBCUtil.selectCartById(userid);
            req.setAttribute("cart", list);
            req.getRequestDispatcher("/personalcenter/mycart.jsp").forward(req, resp);
        } else{
            req.setAttribute("message", "<font sytle='color:red'>添加失败，请重试</font>");
            req.getRequestDispatcher("/personalcenter/shopping.jsp").forward(req, resp);
        }
    }
}
