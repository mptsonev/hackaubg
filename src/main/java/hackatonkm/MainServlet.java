package hackatonkm;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import services.UtilService;

@Singleton
public class MainServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Inject
    private UtilService utilService;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        try {
            Connection conn = DriverManager.getConnection(
                "postgres://K231CHL2mVF0yYum:l1yswwHrudHbMOo8@10.11.241.22:49971/uNzE4kBu9otGp4QJ", "K231CHL2mVF0yYum", "l1yswwHrudHbMOo8");
        } catch (SQLException e) {
            out.print("KUR" + e.getMessage());
        }
        String res = request.getParameter("krasi");
        response.setContentType("text/html");
        out.print(utilService.appendGuice(res));
        out.flush();
    }
}