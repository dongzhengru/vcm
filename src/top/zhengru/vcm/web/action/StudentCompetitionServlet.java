package top.zhengru.vcm.web.action;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import top.zhengru.vcm.bean.Competition;
import top.zhengru.vcm.bean.Register;
import top.zhengru.vcm.bean.User;
import top.zhengru.vcm.utils.DBUtil;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/10
 * @Time: 10:29
 */
@WebServlet({"/stucomp","/register","/register/addteam"})
public class StudentCompetitionServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String servletPath = request.getServletPath();
        if ("/stucomp".equals(servletPath)){
            doList(request,response);
        } else if ("/register".equals(servletPath)) {
            doReg(request,response);
        } else if ("/register/addteam".equals(servletPath)) {
            doAddTeam(request,response);
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
        request.getRequestDispatcher("/allcomp.jsp").forward(request,response);
    }
    private void doReg(HttpServletRequest request, HttpServletResponse response)
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
        request.getRequestDispatcher("/register.jsp").forward(request,response);
    }

    private void doAddTeam(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String message = "";
        HttpSession session  = request.getSession();
        Boolean flag = true;
        User currentUser = (User) session.getAttribute("user");
        String comp_id = request.getParameter("comp_id");
        String team_name = request.getParameter("team_name");

        String stu_id = request.getParameter("stu_id");
        List<String> stu_ids = new ArrayList<>();
        if (stu_id != null){
            stu_id = stu_id.trim().replaceAll("^,|,$", "");
            stu_ids = new ArrayList<>(Arrays.asList(stu_id.split(",")));
        }

        String teach_id = request.getParameter("teach_id");
        teach_id = teach_id.trim().replaceAll("^,|,$", "");
        List<String> teach_ids = new ArrayList<>(Arrays.asList(teach_id.split(",")));

        Integer tid = null;
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Competition competition = new Competition();
        while (flag){
            try {
                conn = DBUtil.GetConnection();
                String sql = "select id,name,teacher_num,student_num,teach_max from competition where id = ?";
                ps = conn.prepareStatement(sql);
                ps.setString(1,comp_id);
                rs = ps.executeQuery();
                while (rs.next()){
                    competition.setId(rs.getInt("id"));
                    competition.setName(rs.getString("name"));
                    competition.setTeacherNum(rs.getInt("teacher_num"));
                    competition.setStudentNum(rs.getInt("student_num"));
                    competition.setTeachMax(rs.getInt("teach_max"));
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }
            if (stu_ids.get(0).equals("")){
                if (stu_ids.size()> competition.getStudentNum()){
                    flag = false;
                    message = "队员人数超出比赛限制！";
                    break;
                }
            }else {
                if (stu_ids.size() + 1 > competition.getStudentNum()){
                    flag = false;
                    message = "队员人数超出比赛限制！";
                    break;
                }
            }
            if (teach_ids.size() > competition.getTeacherNum()){
                flag = false;
                message = "指导教师超出比赛限制！";
                break;
            }
            conn = null;
            ps = null;
            rs = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "select cid from team t,register r where t.id = r.tid and r.uid = ?;";
                ps = conn.prepareStatement(sql);
                ps.setInt(1,currentUser.getId());
                rs = ps.executeQuery();
                while (rs.next()){
                    if (rs.getString("cid").equals(comp_id)){
                        flag = false;
                        message = "你已参加该竞赛，请勿重复参加！";
                        break;
                    }
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }


            for (String teachId : teach_ids) {
                conn = null;
                ps = null;
                rs = null;
                try {
                    conn = DBUtil.GetConnection();
                    String sql = "select count(*) from team t,register r where t.id = r.tid and r.uid = ? and cid = ?";
                    ps = conn.prepareStatement(sql);
                    ps.setInt(1,Integer.valueOf(teachId));
                    ps.setInt(2,Integer.valueOf(comp_id));
                    rs = ps.executeQuery();
                    if (rs.next()){
                        int count = rs.getInt("count(*)");
                        if (count + 1 > competition.getTeachMax()){
                            flag = false;
                            message = "指导老师已达该竞赛的上限，请寻找其他指导老师！";
                            break;
                        }
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                } finally {
                    DBUtil.close(conn,ps,rs);
                }
            }
            break;
        }


        if (flag){
            conn = null;
            ps = null;
            rs = null;
            int count = 0;
            try {
                conn = DBUtil.GetConnection();
                String sql = "insert into team(cid,team_name,captain_uid,enabled) values(?,?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,comp_id);
                ps.setString(2,team_name);
                ps.setString(3,currentUser.getId().toString());
                ps.setString(4,"0");
                count = ps.executeUpdate();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }

            conn = null;
            ps = null;
            rs = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "select id from team where cid = ? and captain_uid = ?;";
                ps = conn.prepareStatement(sql);
                ps.setString(1,comp_id.toString());
                ps.setString(2,currentUser.getId().toString());
                rs = ps.executeQuery();
                if (rs.next()){
                    tid = rs.getInt("id");
                }
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                DBUtil.close(conn,ps,rs);
            }

            conn = null;
            ps = null;
            try {
                conn = DBUtil.GetConnection();
                String sql = "insert into register(tid,uid,isCaptain) values(?,?,?)";
                ps = conn.prepareStatement(sql);
                ps.setString(1,tid.toString());
                ps.setString(2,currentUser.getId().toString());
                ps.setString(3,"1");
                ps.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            count = 0;
            if (stu_id != null && stu_id != ""){
                for (String stuId : stu_ids) {
                    conn = null;
                    ps = null;
                    try {
                        conn = DBUtil.GetConnection();
                        String sql = "insert into invitation(cid,tid,uid1,uid2,accepted) values(?,?,?,?,?)";
                        ps = conn.prepareStatement(sql);
                        ps.setString(1,comp_id);
                        ps.setString(2,tid.toString());
                        ps.setString(3,currentUser.getId().toString());
                        ps.setString(4,stuId);
                        ps.setString(5,"0");
                        count += ps.executeUpdate();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

            for (String teachId : teach_ids) {
                conn = null;
                ps = null;
                try {
                    conn = DBUtil.GetConnection();
                    String sql = "insert into invitation(cid,tid,uid1,uid2,accepted) values(?,?,?,?,?)";
                    ps = conn.prepareStatement(sql);
                    ps.setString(1,comp_id);
                    ps.setString(2,tid.toString());
                    ps.setString(3,currentUser.getId().toString());
                    ps.setString(4,teachId);
                    ps.setString(5,"0");
                    count += ps.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            response.sendRedirect(request.getContextPath()+"/register");
        } else {
            session.setAttribute("message",message);
            response.sendRedirect(request.getContextPath()+"/error.jsp");
        }
    }

}
