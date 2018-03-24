package models;

public class User {
    int userId;
    String userName;
    String userPassword;
    String userRole;
    String webRtcId;

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getWebRtcId() {
        return webRtcId;
    }

    public void setWebRtcId(String webRtcId) {
        this.webRtcId = webRtcId;
    }

}
