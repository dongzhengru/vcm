package top.zhengru.vcm.web.action;

import top.zhengru.vcm.bean.User;
import top.zhengru.vcm.utils.DBUtil;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/9
 * @Time: 11:47
 */
@WebServlet({"/login","/logout"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/login".equals(servletPath)){
            doLogin(request,response);
        } else if ("/logout".equals(servletPath)) {
            doExit(request,response);
        }
    }
    protected void doLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean success = false;
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        User user = new User();
        try {
            conn = DBUtil.GetConnection();
            String sql = "select * from user where username = ? and password = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1,username);
            ps.setString(2,password);
            rs = ps.executeQuery();
            if (rs.next()){
                success = true;
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                user.setUsername(rs.getString("username"));
                user.setPassword(rs.getString("password"));
                user.setPhone(rs.getString("phone"));
                user.setRole(rs.getInt("role"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        if (success){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            if (user.getRole() == 0) {
                response.sendRedirect(request.getContextPath()+"/home0.jsp");
            } else if (user.getRole() == 1) {
                response.sendRedirect(request.getContextPath()+"/home1.jsp");
            } else if (user.getRole() == 2) {
                response.sendRedirect(request.getContextPath()+"/home2.jsp");
            }
        } else {
            request.getSession().setAttribute("message","登录失败，用户名或密码错误！");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }
    private void doExit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null){
            session.removeAttribute("user");
            session.invalidate();
            response.sendRedirect(request.getContextPath()+"/login.jsp");
        }
    }
}
