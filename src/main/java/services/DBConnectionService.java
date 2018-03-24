package services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
                User user = new User();
                user.setUserId(rs.getInt(1));
                user.setUserName(rs.getString(2));
                user.setUserPassword(rs.getString(3));
                user.setUserRole(rs.getString(4));
                user.setWebRtcId(rs.getString(5));
                users.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return users;
    }

    public List<Classroom> getClassrooms() {
        List<Classroom> classrooms = new ArrayList<Classroom>();
        Statement st;
        try {
            st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT * FROM CLASSROOM");
            while (rs.next()) {
                Classroom classroom = new Classroom();
                classroom.setRoomId(rs.getInt(1));
                classroom.setRoomTitle(rs.getString(2));
                classroom.setSubject(rs.getString(3));
                classroom.setTeacherId(rs.getInt(4));
                classroom.setStartTime(rs.getDate(5));
                classrooms.add(classroom);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
        return classrooms;
    }
}
