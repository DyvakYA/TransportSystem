package controller.filters;

import controller.commands.user.AuthenticateUserCommand;
import model.entities.User;
import model.entities.enums.UserRoles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/admin/*")
public class AuthenticateFilter implements Filter {

	private static final String ERROR_PAGE = "/privilegeError.jsp";

	public AuthenticateFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession session = req.getSession(false);

		if (session == null) {
			resp.sendRedirect(req.getContextPath()
					+ AuthenticateUserCommand.RESULT_PAGE);
		} else {
			User user = ((User) session
					.getAttribute(AuthenticateUserCommand.USER_SESSION_ATTRIBUTE));
			if (user == null) {
				resp.sendRedirect(req.getContextPath()
						+ AuthenticateUserCommand.RESULT_PAGE);
			} else {
				if (user.getRole() != UserRoles.ADMIN) {
					resp.sendRedirect(req.getContextPath() + ERROR_PAGE);
				} else {
					chain.doFilter(request, response);
				}
			}
		}
	}

	public void init(FilterConfig fConfig) throws ServletException {
	}
}
