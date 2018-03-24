package hackatonkm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import hackatonkm.response.FailResponse;
import hackatonkm.response.GeneralResponse;
import hackatonkm.response.GetClassroomsResponse;
import hackatonkm.response.GetUsersResponse;
import hackatonkm.response.TestResponse;
import models.Classroom;
import models.User;
import services.DBConnectionService;

@Singleton
public class MainServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Inject
    private DBConnectionService dbConnection;

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        GeneralResponse rsp = getResponseForRequest(request);
        if (rsp instanceof FailResponse) {
            FailResponse failResponse = (FailResponse) rsp;
            response.sendError(failResponse.getStatusCode(), failResponse.getErrorMessage());
        }
        response.setContentType("json");
        out.print(gson.toJson(rsp));
        out.flush();
    }

    private GeneralResponse getResponseForRequest(HttpServletRequest request) throws IOException {
        String cmd = request.getParameter("cmd");

        if (cmd == null) {
            return new FailResponse(400, "Command is null");
        }
        switch (cmd) {
            case "getUsers": {
                return new GetUsersResponse(dbConnection.getUsers());
            }
            case "getClassrooms":
                return new GetClassroomsResponse(dbConnection.getClassrooms());
            case "register": {
                User user = new User();
                String userName = request.getParameter("userName");
                String userPassword = request.getParameter("password");
                String role = request.getParameter("role");
                user.setUserName(userName);
                user.setUserPassword(userPassword);
                user.setUserRole(role);
                user.setWebRtcId("N/A");
                dbConnection.addUser(user);
                return new TestResponse("User " + userName + " created");
            }
            case "createClassroom": {
                BufferedReader reader = request.getReader();
                Gson gson = new Gson();
                Classroom classroom = gson.fromJson(reader, Classroom.class);
                dbConnection.addClassroom(classroom);
                return new TestResponse("Classroom created");
            }
            case "login": {
                String userName = request.getParameter("user");
                String password = request.getParameter("password");
                User user = dbConnection.getUserByName(userName);
                if (user == null || !password.equals(user.getUserPassword())) {
                    return new FailResponse(403, "Incorrect credentials");
                }
                return new TestResponse("Welcome " + userName);
            }
            default:
                return new FailResponse(400, "Not recognised command: " + cmd);
        }
    }
}