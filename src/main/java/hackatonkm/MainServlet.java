package hackatonkm;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.inject.Inject;
import com.google.inject.Singleton;

import hackatonkm.response.GeneralResponse;
import hackatonkm.response.TestResponse;
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
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        PrintWriter out = response.getWriter();
        Gson gson = new Gson();
        GeneralResponse rsp = null;
        
        String cmd = request.getParameter("cmd");
        
        if(cmd.equals("Test")) {
        	String test = request.getParameter("test");
        	rsp = new TestResponse(test);
        }
        
        //dbConnection.getExampleDataFromDB();

        response.setContentType("json");
        out.print(gson.toJson(rsp));
        out.flush();
    }
}