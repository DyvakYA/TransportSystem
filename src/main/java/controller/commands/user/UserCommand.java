package controller.commands.user;

import controller.commands.Command;

public interface UserCommand extends Command {

    String USER_ID_ATTRIBUTE = "user_id";
    String USER_LIST_ATTRIBUTE = "usersList";
    String ADMIN_DESTINATION_PAGE = "/admin/users.jsp";
    String UPDATE_USER_SUCCESSFUL_MSG = "UpdateUserSuccessful";
    String CREATE_USER_SUCCESSFUL_MSG = "CreateUserSuccessful";

}
