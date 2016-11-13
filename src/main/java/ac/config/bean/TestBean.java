package ac.config.bean;

/**
 * Created by tianq on 11/11/16.
 */
public class TestBean {
    String username;
    Integer userId;
    T2Bean t2;
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public T2Bean getT2() {
        return t2;
    }

    public void setT2(T2Bean t2) {
        this.t2 = t2;
    }

    @Override
    public String toString() {
        return "username = " + username + " \nuserId = " + userId;
    }
}
