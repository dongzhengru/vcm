package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/12
 * @Time: 15:02
 */
public class Award {
    private Integer id;
    private String name;
    private Integer cid;
    private String cname;
    private Integer uid;
    private String uname;

    public Award() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public Award(Integer id, String name, Integer cid, String cname, Integer uid, String uname) {
        this.id = id;
        this.name = name;
        this.cid = cid;
        this.cname = cname;
        this.uid = uid;
        this.uname = uname;
    }
}
