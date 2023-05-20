package top.zhengru.vcm.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import top.zhengru.vcm.bean.*;
import top.zhengru.vcm.utils.DBUtil;

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
 * @Date: 2023/5/11
 * @Time: 18:26
 */
@WebServlet({"/teampass","/teampass/pass","/materialpass","/materialpass/pass"})
public class PassServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/teampass".equals(servletPath)){
            doInitOne(request,response);
        } else if ("/teampass/pass".equals(servletPath)) {
            doPassOne(request,response);
        } else if ("/materialpass".equals(servletPath)) {
            doInitTwo(request,response);
        } else if ("/materialpass/pass".equals(servletPath)) {
            doPassTwo(request,response);
        }
    }

    private void doInitOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Team> teams = new ArrayList<>();
        Integer cid_count = 0;
        HttpSession session  = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select distinct c.id as cid,c.name as cname,t.id as tid,t.team_name as tname,t.enabled as enabled from register r,team t,competition c where c.id = t.cid and t.id = r.tid order by cid";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                cid_count++;
                Team team = new Team();
                team.setCid(rs.getInt("cid"));
                team.setCname(rs.getString("cname"));
                team.setTid(rs.getInt("tid"));
                team.setTeamName(rs.getString("tname"));
                team.setEnabled(rs.getInt("enabled"));
                teams.add(team);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        for (Integer i = 0; i < cid_count; i++) {
            Team team = teams.get(i);
            conn = null;
            ps = null;
            rs = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "select u.id as captain_uid,u.name as captain_uname from user u,register r where u.id = r.uid and r.isCaptain = 1 and r.tid = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,team.getTid());
                rs = ps.executeQuery();
                while (rs.next()){
                    team.setCaptainUid(rs.getInt("captain_uid"));
                    team.setCaptainName(rs.getString("captain_uname"));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }

            conn = null;
            ps = null;
            rs = null;
            List<TeamTeacher> teamTeachers = new ArrayList<>();
            try {
                conn = DBUtil.GetConnection();
                String sql = "select u.id as teach_uid,u.name as teach_uname from user u,register r where u.id = r.uid and u.role = 1 and r.isCaptain = 0 and r.tid = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,team.getTid());
                rs = ps.executeQuery();
                int teach_count = 0;
                while (rs.next()){
                    teach_count++;
                    TeamTeacher teamTeacher = new TeamTeacher();
                    teamTeacher.setId(rs.getInt("teach_uid"));
                    teamTeacher.setName(rs.getString("teach_uname"));
                    teamTeachers.add(teamTeacher);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
            team.setTeachers(teamTeachers);

            conn = null;
            ps = null;
            rs = null;
            List<TeamMember> teamMembers = new ArrayList<>();
            try {
                conn = DBUtil.GetConnection();
                String sql = "select u.id as uid,u.name as uname from user u,register r where u.id = r.uid and u.role = 2 and r.isCaptain = 0 and r.tid = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,team.getTid());
                rs = ps.executeQuery();
                while (rs.next()){
                    TeamMember teamMember = new TeamMember();
                    teamMember.setId(rs.getInt("uid"));
                    teamMember.setName(rs.getString("uname"));
                    teamMembers.add(teamMember);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
            team.setMembers(teamMembers);
        }

        request.setAttribute("teams",teams);
        request.getRequestDispatcher("/teampass.jsp").forward(request,response);
    }

    private void doPassOne(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer tid = Integer.valueOf(request.getParameter("tid"));
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "update team set enabled = 1 where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,tid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        response.sendRedirect(request.getContextPath()+"/teampass");
    }


    private void doInitTwo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Material> materials = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;


        try {
            conn = DBUtil.GetConnection();
            String sql = "select m.id as mid,m.cid as cid,c.name as cname,m.tid as tid,t.team_name as tname,m.url as url,m.pwd as pwd,m.enabled as enabled from material m,competition c,team t where t.id = m.tid and c.id = m.cid";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()){
                Material material = new Material();
                material.setId(rs.getInt("mid"));
                material.setCid(rs.getInt("cid"));
                material.setCname((rs.getString("cname")));
                material.setTid(rs.getInt("tid"));
                material.setTname(rs.getString("tname"));
                material.setUrl(rs.getString("url"));
                material.setPwd(rs.getString("pwd"));
                material.setEnabled(rs.getInt("enabled"));
                materials.add(material);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        request.setAttribute("materials",materials);
        request.getRequestDispatcher("/materialpass.jsp").forward(request,response);
    }

    private void doPassTwo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Integer mid = Integer.valueOf(request.getParameter("mid"));
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "update material set enabled = 1 where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,mid);
            ps.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        response.sendRedirect(request.getContextPath()+"/materialpass");
    }

}
