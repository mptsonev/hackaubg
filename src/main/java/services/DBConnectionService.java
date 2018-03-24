package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import models.Classroom;
import models.User;

public class DBConnectionService {

    private Connection conn = null;

    public DBConnectionService() {
        try {
            Class.forName("org.postgresql.Driver");
            this.conn = DriverManager.getConnection("jdbc:postgresql://10.11.241.22:49971/uNzE4kBu9otGp4QJ", "K231CHL2mVF0yYum",
                "l1yswwHrudHbMOo8");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error connection to db " + e);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error connection to db " + e);
        }
    }

    public List<User> getUsers() {
        List<User> users = new ArrayList<User>();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM PERSON");
            while (rs.next()) {
                users.add(getUser(rs));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return users;
    }

    public User getUserByName(String userName) {
        Statement st;
        User user;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery(MessageFormat.format("SELECT * FROM PERSON WHERE user_name=''{0}''", userName));
            rs.next();
            user = getUser(rs);
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return user;
    }

    public List<Classroom> getClassrooms() {
        List<Classroom> classrooms = new ArrayList<Classroom>();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLASSROOM");
            while (rs.next()) {
                classrooms.add(getClassroom(rs));
            }
            st.close();
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return classrooms;
    }

    public void addUser(User user) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(MessageFormat.format("INSERT INTO PERSON VALUES (DEFAULT, ''{0}'', ''{1}'', ''{2}'', ''{3}'')",
                user.getUserName(), user.getUserPassword(), user.getUserRole(), user.getWebRtcId()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addClassroom(Classroom classroom) {
        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(MessageFormat.format(
                "INSERT INTO CLASSROOM VALUES (DEFAULT, ''{0}'', ''{1}'', ''{2}'', ''{3}'', ''{4}'', ''{5}'', ''{6}'')",
                classroom.getRoomName(), classroom.getSubject(), classroom.getTeacherName(), classroom.getWhiteboardId(),
                classroom.getTeacherWebcamId(), classroom.getStartTime()));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private User getUser(ResultSet rs) throws SQLException {
        User user = new User();
        user.setUserId(rs.getInt(1));
        user.setUserName(rs.getString(2));
        user.setUserPassword(rs.getString(3));
        user.setUserRole(rs.getString(4));
        user.setWebRtcId(rs.getString(5));
        return user;
    }

    private Classroom getClassroom(ResultSet rs) throws SQLException {
        Classroom classroom = new Classroom();
        classroom.setRoomId(rs.getInt(1));
        classroom.setRoomName(rs.getString(2));
        classroom.setSubject(rs.getString(3));
        classroom.setTeacherName(rs.getString(4));
        classroom.setWhiteboardId(rs.getString(5));
        classroom.setTeacherWebcamId(rs.getString(6));
        classroom.setStartTime(rs.getDate(7));
        return classroom;
    }
}
