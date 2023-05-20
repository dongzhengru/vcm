package top.zhengru.vcm.web.action;

import top.zhengru.vcm.bean.User;
import top.zhengru.vcm.utils.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/9
 * @Time: 15:18
 */
@WebServlet({"/usermana","/usermana/delete"})
public class PeopleManaServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/usermana".equals(servletPath)){
            doList(request,response);
        } else if ("/usermana/delete".equals(servletPath)) {
            doDel(request,response);
        }
    }

    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<User> users = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select id,name,username,phone,role from user";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getInt("role"));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("userList",users);
        request.getRequestDispatcher("/usermana.jsp").forward(request,response);
    }
    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.GetConnection();
            String sql = "delete from user where id = ? and not (role = 0)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,id);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            if (conn != null){
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    e.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
        if (count == 1){
            response.sendRedirect(request.getContextPath()+"/usermana");
        }else {
            request.getSession().setAttribute("message","用户删除失败！");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
}
