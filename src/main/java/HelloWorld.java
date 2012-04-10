/**
 * Created with IntelliJ IDEA.
 * User: mikael.lof
 * Date: 2012-04-10
 * Time: 14:06
 * To change this template use File | Settings | File Templates.
 */
public class HelloWorld {
    import java.io.IOException;
    import javax.servlet.ServletException;
    import javax.servlet.http.*;
    import org.eclipse.jetty.server.Server;
    import org.eclipse.jetty.servlet.*;

    public class HelloWorld extends HttpServlet {

        @Override
        protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                throws ServletException, IOException {
            resp.getWriter().print("Hello Mike!\n");
        }

        public static void main(String[] args) throws Exception{
            Server server = new Server(Integer.valueOf(System.getenv("PORT")));
            ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
            context.setContextPath("/");
            server.setHandler(context);
            context.addServlet(new ServletHolder(new HelloWorld()),"/*");
            server.start();
            server.join();
        }
    }
}
