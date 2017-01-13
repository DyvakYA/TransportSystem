package commands.validators;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface CommandValidator {

    boolean validate(HttpServletRequest request, HttpServletResponse response);

}

