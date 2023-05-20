package top.zhengru.vcm.web.action;

import jakarta.servlet.http.HttpSession;
import top.zhengru.vcm.bean.Award;
import top.zhengru.vcm.bean.Competition;
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
 * @Time: 21:55
 */
@WebServlet({"/compmana","/compmana/addcomp","/compmana/delete","/compmana/edit","/compmana/save","/compmana/*","/allaward"})
public class ManageCompetitionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/compmana".equals(servletPath)){
            doList(request,response);
        } else if ("/compmana/addcomp".equals(servletPath)) {
            doAdd(request,response);
        } else if ("/compmana/delete".equals(servletPath)) {
            doDel(request,response);
        } else if ("/compmana/edit".equals(servletPath)) {
            doEdit(request,response);
        } else if ("/compmana/save".equals(servletPath)) {
            doSave(request,response);
        } else if ("/allaward".equals(servletPath)) {
            doAward(request,response);
        }
    }
    private void doList(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Competition> competitions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select id,name,teacher_num,student_num,teach_max from competition";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Competition competition = new Competition();
                competition.setId(rs.getInt("id"));
                competition.setName(rs.getString("name"));
                competition.setTeacherNum(rs.getInt("teacher_num"));
                competition.setStudentNum(rs.getInt("student_num"));
                competition.setTeachMax(rs.getInt("teach_max"));
                competitions.add(competition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("competitionList",competitions);
        request.getRequestDispatcher("/compmana.jsp").forward(request,response);
    }

    private void doAdd(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String name = request.getParameter("name");
        String teacherNum = request.getParameter("teacherNum");
        String studentNum = request.getParameter("studentNum");
        String teachMax = request.getParameter("teachMax");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.GetConnection();
            String sql = "insert into competition(name,teacher_num,student_num,teach_max) values(?,?,?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1,name);
            ps.setString(2,teacherNum);
            ps.setString(3,studentNum);
            ps.setString(4,teachMax);
            count = ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,null);
        }
        if (count == 1){
            response.sendRedirect(request.getContextPath()+"/compmana");
        }else {
            request.getSession().setAttribute("message","竞赛添加失败！");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }

    private void doDel(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String id = request.getParameter("id");
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.GetConnection();
            String sql = "delete from competition where id = ?";
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
            response.sendRedirect(request.getContextPath()+"/compmana");
        }else {
            request.getSession().setAttribute("message","竞赛删除失败！");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }

    private void doEdit(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Competition> competitions = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select id,name,teacher_num,student_num,teach_max from competition";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Competition competition = new Competition();
                competition.setId(rs.getInt("id"));
                competition.setName(rs.getString("name"));
                competition.setTeacherNum(rs.getInt("teacher_num"));
                competition.setStudentNum(rs.getInt("student_num"));
                competition.setTeachMax(rs.getInt("teach_max"));
                competitions.add(competition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("competitionList",competitions);
        request.getRequestDispatcher("/compedit.jsp").forward(request,response);

    }

    private void doSave(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        Integer teacherNum = Integer.valueOf(request.getParameter("teacherNum"));
        Integer studentNum = Integer.valueOf(request.getParameter("studentNum"));
        Integer teachMax = Integer.valueOf(request.getParameter("teachMax"));
        Connection conn = null;
        PreparedStatement ps = null;
        int count = 0;
        try {
            conn = DBUtil.GetConnection();
            String sql = "update competition set teacher_num = ?,student_num = ?,teach_max = ? where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,teacherNum);
            ps.setInt(2,studentNum);
            ps.setInt(3,teachMax);
            ps.setInt(4,cid);
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
            response.sendRedirect(request.getContextPath()+"/compmana");
        }else {
            request.getSession().setAttribute("message","竞赛更新失败！");
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }

    private void doAward(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Award> awards = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select a.id as aid,a.name as aname,a.cid as cid,c.name as cname,a.uid as uid,u.name as uname from award a,competition c,user u where u.id = a.uid and c.id = a.cid order by aid";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Award award = new Award();
                award.setId(rs.getInt("aid"));
                award.setName(rs.getString("aname"));
                award.setCid(rs.getInt("cid"));
                award.setCname(rs.getString("cname"));
                award.setUid(rs.getInt("uid"));
                award.setUname(rs.getString("uname"));
                awards.add(award);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        request.setAttribute("awards",awards);
        request.getRequestDispatcher("/allaward.jsp").forward(request,response);
    }
}
