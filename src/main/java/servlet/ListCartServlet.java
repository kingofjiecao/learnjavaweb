package servlet;

import com.alibaba.fastjson.JSON;
import jdk.nashorn.internal.runtime.JSONListAdapter;
import model.Item;
import model.User;
import util.JDBCUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/dolist")
public class ListCartServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int userid = ((User)req.getSession().getAttribute("user")).getId();
        List<Item> list = JDBCUtil.selectCartById(userid);
        String s = JSON.toJSONString(list);
        resp.setContentType("text/html;charset=UTF-8");
        resp.getWriter().append(s);
    }
}
