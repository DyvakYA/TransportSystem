package controller.filters;

import model.entities.User;
import model.entities.enums.UserRoles;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

@WebFilter("/admin/*")
public class AuthenticateFilter implements Filter {

	private static final Logger logger = Logger.getLogger(String.valueOf(AuthenticateFilter.class));
	private static final String ACCESS_DENIED = "Access denied for user %d";
	private static final String USER_NOT_AUTHORIZED = "User isn't authorized";

	private Map<UserRoles, Authorizer> authorizeByRole = new HashMap<>() ;
	{
		authorizeByRole.put(UserRoles.USER, new UserAuthorizer());
		authorizeByRole.put(UserRoles.ADMIN, new AdminAuthorizer());
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = ((HttpServletRequest) request);
		HttpSession session = req.getSession();
		String uri = req.getRequestURI();
		User user = (User)session.getAttribute("role");
		if (user==null) {
			req.getRequestDispatcher("/admin").forward(request, response);
					logger.info(String.format(USER_NOT_AUTHORIZED));
			return;
		}

		if(!checkUserPermissions(uri, user)){
			req.getRequestDispatcher("/WEB-INF/pages/accessDenied.jsp").forward(request, response);
					logger.info(String.format(ACCESS_DENIED, user.getId()));
			return;
		}

		chain.doFilter(request, response);
	}


	private boolean checkUserPermissions(String uri, User user){
		Authorizer authorizer = authorizeByRole.get(user.getRole());
		return authorizer.check(uri, user);
	}

	private interface Authorizer {
			boolean check(String uri, User user);
	}

	private class AdminAuthorizer implements Authorizer {
		public boolean check(String uri, User user) {
			return !uri.startsWith("/user");

		}
	}

	private class UserAuthorizer implements Authorizer {
		public boolean check(String uri, User user) {
			return  !uri.startsWith("/admin");
		}
	}

	@Override
	public void init(FilterConfig fConfig) throws ServletException {
	}

	@Override
	public void destroy() {
	}

}
