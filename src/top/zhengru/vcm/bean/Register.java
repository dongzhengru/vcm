package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/10
 * @Time: 14:45
 */
public class Register {
    private Integer id;
    private Integer tid;
    private Integer uid;
    private Integer isCaptain;

    public Register() {
    }

    public Register(Integer id, Integer tid, Integer uid, Integer isCaptain) {
        this.id = id;
        this.tid = tid;
        this.uid = uid;
        this.isCaptain = isCaptain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getUid() {
        return uid;
    }

    public void setUid(Integer uid) {
        this.uid = uid;
    }

    public Integer getIsCaptain() {
        return isCaptain;
    }

    public void setIsCaptain(Integer isCaptain) {
        this.isCaptain = isCaptain;
    }
}
