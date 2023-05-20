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
 * @Time: 21:14
 */
@WebServlet({"/teachcomp","/teamstatus1","/invitation1","/invitation1/accept","/award"})
public class TeacherServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/teachcomp".equals(servletPath)){
            doList(request,response);
        } else if ("/teamstatus1".equals(servletPath)){
            doInitOne(request,response);
        } else if ("/invitation1".equals(servletPath)){
            doInitTwo(request,response);
        } else if ("/invitation1/accept".equals(servletPath)) {
            doAccept(request,response);
        } else if ("/award".equals(servletPath)) {
            doInitThree(request,response);
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
                competition.setTeacherNum(Integer.valueOf(rs.getString("teacher_num")));
                competition.setStudentNum(Integer.valueOf(rs.getString("student_num")));
                competition.setTeachMax(Integer.valueOf(rs.getString("teach_max")));
                competitions.add(competition);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }
        request.setAttribute("competitionList",competitions);
        request.getRequestDispatcher("/allcomp1.jsp").forward(request,response);
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
            String sql = "select c.id as cid,c.name as cname,t.id as tid,t.team_name as tname,t.enabled as enabled from register r,team t,competition c where c.id = t.cid and t.id = r.tid and uid = ? order by cid";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,currentUser.getId());
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

        List<Material> materials = new ArrayList<>();
        for (Integer i = 0; i < cid_count; i++) {
            Team team = teams.get(i);
            try {
                conn = DBUtil.GetConnection();
                String sql = "select m.id as mid,m.cid as cid,c.name as cname,m.tid as tid,t.team_name as tname,m.url as url,m.pwd as pwd,m.enabled as enabled from material m,competition c,team t where t.id = m.tid and c.id = m.cid and m.tid = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,team.getTid());
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
        }
        request.setAttribute("teams",teams);
        request.setAttribute("materials",materials);
        request.getRequestDispatcher("/teamstatus1.jsp").forward(request,response);
    }

    private void doInitTwo(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Invitation> invitations = new ArrayList<>();
        Integer invite_count = 0;
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
            String sql = "select i.id as iid,i.tid as tid,t.team_name as tname,c.id as cid,c.name as cname from invitation i,competition c,team t where t.id = i.tid and c.id = i.cid and i.uid2 = ? and i.accepted = 0";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,currentUser.getId());
            rs = ps.executeQuery();
            while (rs.next()){
                invite_count++;
                Invitation invitation = new Invitation();
                invitation.setId(rs.getInt("iid"));
                Team team = new Team();
                team.setTid(rs.getInt("tid"));
                team.setTeamName(rs.getString("tname"));
                team.setCid(rs.getInt("cid"));
                team.setCname(rs.getString("cname"));
                invitation.setTeam(team);
                invitations.add(invitation);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        for (Integer i = 0; i < invite_count; i++) {
            Team team = invitations.get(i).getTeam();
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
        request.setAttribute("invitations",invitations);
        request.getRequestDispatcher("/invitation1.jsp").forward(request,response);
    }

    private void doAccept(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Boolean flag = true;
        String message = null;
        HttpSession session  = request.getSession();
        User currentUser = (User) session.getAttribute("user");
        Integer iid = Integer.valueOf(request.getParameter("iid"));
        Integer tid = Integer.valueOf(request.getParameter("tid"));
        Integer cid = Integer.valueOf(request.getParameter("cid"));
        Integer teachMax = 0;
        Integer currentTeachNum = 0;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select teach_max from competition where id = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,cid);
            rs = ps.executeQuery();
            if (rs.next()){
                teachMax = rs.getInt("teach_max");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        conn = null;
        ps = null;
        rs = null;
        try {
            conn = DBUtil.GetConnection();
            String sql = "select count(*) from team t,register r where t.id = r.tid and r.uid = ? and cid = ?";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,currentUser.getId());
            ps.setInt(2,cid);
            rs = ps.executeQuery();
            if (rs.next()){
                currentTeachNum = rs.getInt("count(*)");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn,ps,rs);
        }

        if (currentTeachNum + 1 > teachMax){
            flag = false;
            message = "您已超过该竞赛的最大指导队伍数！";
        }

        Integer count = 0;
        if (flag){
            conn = null;
            ps = null;
            rs = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "update invitation set accepted = 1 where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,iid);
                count += ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }

            conn = null;
            ps = null;
            rs = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "insert into register(tid,uid,isCaptain) values(?,?,0)";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,tid);
                ps.setInt(2,currentUser.getId());
                count += ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
            if (count == 2){
                message = "添加成功";
            }
            request.getSession().setAttribute("message",message);
            request.getRequestDispatcher("/invitation1").forward(request,response);
        }else {
            request.getSession().setAttribute("message",message);
            request.getRequestDispatcher("/error.jsp").forward(request,response);
        }
    }

    private void doInitThree(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Award> awards = new ArrayList<>();
        String message = null;
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
            String sql = "select a.id as aid,a.name as aname,a.cid as cid,c.name as cname,a.uid as uid,u.name as uname from award a,competition c,user u where u.id = a.uid and c.id = a.cid and a.id in (select distinct r.uid from register r where r.tid in (select r.tid from register r where r.uid = ?) and uid != ?) order by aid";
            ps = conn.prepareStatement(sql);
            ps.setInt(1,currentUser.getId());
            ps.setInt(2,currentUser.getId());
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
        request.getRequestDispatcher("/award1.jsp").forward(request,response);
    }
}
