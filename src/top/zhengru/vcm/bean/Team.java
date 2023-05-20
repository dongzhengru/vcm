package top.zhengru.vcm.bean;

import java.util.List;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/10
 * @Time: 14:42
 */
public class Team {
    private Integer id;
    private Integer cid;
    private String cname;
    private Integer tid;
    private String teamName;
    private Integer captainUid;
    private String captainName;
    private Integer teachUid;
    private List<TeamTeacher> teachers;
    private List<TeamMember> members;
    private Integer enabled;

    public Team() {
    }

    public Team(Integer id, Integer cid, String cname, Integer tid, String teamName, Integer captainUid, String captainName, Integer teachUid, List<TeamTeacher> teachers, List<TeamMember> members, Integer enabled) {
        this.id = id;
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;
        this.teamName = teamName;
        this.captainUid = captainUid;
        this.captainName = captainName;
        this.teachUid = teachUid;
        this.teachers = teachers;
        this.members = members;
        this.enabled = enabled;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getCaptainUid() {
        return captainUid;
    }

    public void setCaptainUid(Integer captainUid) {
        this.captainUid = captainUid;
    }

    public String getCaptainName() {
        return captainName;
    }

    public void setCaptainName(String captainName) {
        this.captainName = captainName;
    }

    public Integer getTeachUid() {
        return teachUid;
    }

    public void setTeachUid(Integer teachUid) {
        this.teachUid = teachUid;
    }

    public List<TeamTeacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<TeamTeacher> teachers) {
        this.teachers = teachers;
    }

    public List<TeamMember> getMembers() {
        return members;
    }

    public void setMembers(List<TeamMember> members) {
        this.members = members;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }
}
