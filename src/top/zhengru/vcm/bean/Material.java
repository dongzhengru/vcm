package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/11
 * @Time: 16:39
 */
public class Material {
    private Integer id;
    private Integer cid;
    private String cname;
    private Integer tid;
    private String tname;
    private String url;
    private String pwd;
    private Integer enabled;

    public Material() {
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

    public String getTname() {
        return tname;
    }

    public void setTname(String tname) {
        this.tname = tname;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public Integer getEnabled() {
        return enabled;
    }

    public void setEnabled(Integer enabled) {
        this.enabled = enabled;
    }

    public Material(Integer id, Integer cid, String cname, Integer tid, String tname, String url, String pwd, Integer enabled) {
        this.id = id;
        this.cid = cid;
        this.cname = cname;
        this.tid = tid;
        this.tname = tname;
        this.url = url;
        this.pwd = pwd;
        this.enabled = enabled;
    }
}
