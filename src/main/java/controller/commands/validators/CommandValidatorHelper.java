package controller.commands.validators;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class CommandValidatorHelper {

    public static CommandValidatorHelper instance = new CommandValidatorHelper();

    private CommandValidatorHelper() {
    }

    public static CommandValidatorHelper getInstance() {
        return instance;
    }

    public boolean isNullValidate(String[] attributes, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;
        for (String attribute : attributes) {
            if (request.getParameter(attribute) == null) {
                result = false;
                request.setAttribute(resultAttribute, errorMgs);
                try {
                    request.getRequestDispatcher(destPage).forward(
                            request, response);
                } catch (ServletException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public boolean isEmptyValidate(String[] attributes, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;
        for (String attribute : attributes) {
            if (request.getParameter(attribute).isEmpty()) {
                result = false;
                request.setAttribute(resultAttribute, errorMgs);
                try {
                    request.getRequestDispatcher(destPage).forward(
                            request, response);
                } catch (ServletException | IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return result;
    }

    public boolean matchesValidate(String attribute, String regEx, String resultAttribute,
            String destPage, String errorMgs, HttpServletRequest request,
            HttpServletResponse response) {

        boolean result = true;

        if (!request.getParameter(attribute).matches(regEx)) {
            result = false;
            request.setAttribute(resultAttribute, errorMgs);
            try {
                request.getRequestDispatcher(destPage).forward(
                        request, response);
            } catch (ServletException | IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        return result;
    }
}
