package model.servlets;

import model.commands.Command;
import model.commands.CommandFactory;
import model.extras.LoggerHelper;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Properties;

/**
 * Servlet implementation class TransportServlet
 */
@WebServlet("/TransportServlet")
public class TransportServlet extends HttpServlet {

    private static final String COMMAND = "command";
    private static final long serialVersionUID = 1L;
    private static final String UTF_8 = "UTF-8";
    private static final String CHARSET_UTF8 = "text/html;charset=UTF-8";
    private static final String LOGGER_PROPERTIES_PATH = "/log4j.properties";
    private static final String LOGGER_INIT_ERROR = "Logger properties could not be readed";

    /**
     *
     */
    public static final String LOGGER_NAME = "log4j";
    private static final String CHARSET_UNSOPORTED_ERROR = "Charset UTF-8 Unsoported";

    /**
     * @see HttpServlet#HttpServlet()
     */
    public TransportServlet() {
        super();
    }

    /**
     * @throws ServletException
     */
    @Override
    public void init() throws ServletException {
        super.init();

        Properties config = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try {
            config.load(classLoader.getResourceAsStream(LOGGER_PROPERTIES_PATH));
        } catch (IOException ex) {
            System.out.println(LOGGER_INIT_ERROR);
        }

        PropertyConfigurator.configure(config);

        Logger globallog = Logger.getRootLogger();
        getServletContext().setAttribute(LOGGER_NAME, globallog);
        LoggerHelper.getInstance().setLogger(globallog);

    }

    /**
     * @param request
     * @param response
     */
    public void processRequest(HttpServletRequest request,
                               HttpServletResponse response) {

        response.setContentType(CHARSET_UTF8);
        try {
            request.setCharacterEncoding(UTF_8);
        } catch (UnsupportedEncodingException e) {
            Logger logger = (Logger) getServletContext().getAttribute(LOGGER_NAME);
            logger.error(CHARSET_UNSOPORTED_ERROR);
        }
        CommandFactory factory = CommandFactory.getInstance();
        Command command = factory.getCommand(request.getParameter(COMMAND));
        command.execute(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * @param request
     * @param response
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
     * response)
     */
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
