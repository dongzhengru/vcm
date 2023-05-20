package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/10
 * @Time: 15:59
 */
public class Invitation {
    private Integer id;
    private Team team;

    public Invitation() {
    }

    public Invitation(Integer id, Team team) {
        this.id = id;
        this.team = team;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
