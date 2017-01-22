package controller.commands.stop;

import controller.commands.Command;

public interface StopCommand extends Command {

    String CREATE_STOP_SUCCESSFUL_MSG = "CreateStopSuccessful";
    String UPDATE_STOP_SUCCESSFUL_MSG = "UpdateStopSuccessful";
    String DELETE_STOP_SUCCESSFUL_MSG = "DeleteStopSuccessful";

    String ID_ATTRIBUTE = "stop_id";

    String RESULT_STOP_ATTRIBUTE = "past_stop";

    String RESULT_LIST_ATTRIBUTE = "stopList";

    String ADMIN_DESTINATION_PAGE = "/admin/stops.jsp";
}
