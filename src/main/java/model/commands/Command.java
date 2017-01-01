package model.commands;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface Command {

    /**
     *
     * @param request
     * @param response
     */
    void execute(HttpServletRequest request, HttpServletResponse response);
}
