package controller.commands.transport;

import controller.commands.Command;

public interface TransportCommand extends Command {

    String CREATE_TRANSPORT_SUCCESSFUL_MSG = "CreateTransportSuccessful";
    String UPDATE_TRANSPORT_SUCCESSFUL_MSG = "UpdateTransportSuccessful";
    String DELETE_TRANSPORT_SUCCESSFUL_MSG = "DeleteTransportSuccessful";

    String ID_ATTRIBUTE = "transport_id";

    String MODEL_ATTRIBUTE = "transport_model";

    String NUMBER_ATTRIBUTE = "transport_number";

    String TYPE_ATTRIBUTE = "transport_type";

    String TRANSPORT_RESULT_ATTRIBUTE ="past_transport";

    String RESULT_LIST_ATTRIBUTE = "transportList";

    String RESULT_MAP_ATTRIBUTE = "transportMap";

    String DESTINATION_PAGE = "past_transport.jsp";

    String ADMIN_DESTINATION_PAGE ="./admin/past_transport.jsp";
}
