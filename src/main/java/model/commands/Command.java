package model.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author KIRIL
 */
public interface Command {

    /**
     *
     * @param request
     * @param response
     */
    void execute(HttpServletRequest request, HttpServletResponse response);
}
