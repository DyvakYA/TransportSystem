package controller.commands.transport;

import controller.commands.Command;

public interface TransportCommand extends Command {

    String CREATE_TRANSPORT_SUCCESSFUL_MSG = "CreateTransportSuccessful";
    String UPDATE_TRANSPORT_SUCCESSFUL_MSG = "UpdateTransportSuccessful";
    String DELETE_TRANSPORT_SUCCESSFUL_MSG = "DeleteTransportSuccessful";

    String ID_ATTRIBUTE = "transport_id";

    String MODEL_ATTRIBUTE = "model";

    String NUMBER_ATTRIBUTE = "number";

    String TYPE_ATTRIBUTE = "type";

    String TRANSPORT_RESULT_ATTRIBUTE ="past_transport";

    String TRANSPORT_LIST_ATTRIBUTE = "transportsList";

    String RESULT_MAP_ATTRIBUTE = "transportMap";

    String DESTINATION_PAGE = "past_transport.jsp";

    String ADMIN_DESTINATION_PAGE ="./admin/past_transport.jsp";
}
