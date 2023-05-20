package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/9
 * @Time: 21:51
 */
public class Competition {
    private Integer id;
    private String name;
    private Integer teacherNum;
    private Integer studentNum;
    private Integer teachMax;

    public Competition() {
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

    public Integer getTeacherNum() {
        return teacherNum;
    }

    public void setTeacherNum(Integer teacherNum) {
        this.teacherNum = teacherNum;
    }

    public Integer getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(Integer studentNum) {
        this.studentNum = studentNum;
    }

    public Integer getTeachMax() {
        return teachMax;
    }

    public void setTeachMax(Integer teachMax) {
        this.teachMax = teachMax;
    }

    public Competition(Integer id, String name, Integer teacherNum, Integer studentNum, Integer teachMax) {
        this.id = id;
        this.name = name;
        this.teacherNum = teacherNum;
        this.studentNum = studentNum;
        this.teachMax = teachMax;
    }
}
