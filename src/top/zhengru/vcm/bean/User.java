package top.zhengru.vcm.bean;

/**
 * @Author: dongzhengru
 * @Blog: zhengru.top
 * @Date: 2023/5/9
 * @Time: 11:54
 */
public class User {
    private Integer id;
    private String name;
    private String username;
    private String password;
    private String phone;
    private Integer role;

    public User() {
    }

    public User(Integer id, String name, String username, String password, String phone, Integer role) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.role = role;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getRole() {
        return role;
    }

    public void setRole(Integer role) {
        this.role = role;
    }
}
