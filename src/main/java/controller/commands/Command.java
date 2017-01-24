package controller.commands;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by Dyvak on 21.01.2017.
 */
public interface Command {

    String NAME_ATTRIBUTE = "name";
    String SURNAME_ATTRIBUTE = "surname";
    String AGE_ATTRIBUTE = "age";
    String ROUTE_ID_ATTRIBUTE = "route_id";

    String ADDRESS_ATTRIBUTE = "address";
    String RESULT_ATTRIBUTE = "result";

    String execute(HttpServletRequest request,
                   HttpServletResponse response)
            throws ServletException, IOException, SQLException;
}
