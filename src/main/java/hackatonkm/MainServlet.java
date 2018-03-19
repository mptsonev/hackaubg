package hackatonkm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import services.DBConnectionService;
import services.UtilService;

@Singleton
public class MainServlet extends HttpServlet {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    
    @Inject
    private UtilService utilService;
    
    @Inject
    private DBConnectionService dbConnection;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        String res = request.getParameter("krasi");
        response.setContentType("text/html");
        
        res += dbConnection.getExampleDataFromDB();
        res += dbConnection.getExampleDataFromDB();
        
        out.print(utilService.appendGuice(res));
        out.flush();
    }
}