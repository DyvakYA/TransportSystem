package commands.stop;

import commands.Command;

public interface StopCommand extends Command {

    /**
     *
     */
    String ADDRESS_ATTRIBUTE = "address";

    /**
     *
     */
    String NAME_ATTRIBUTE = "name";

    /**
     *
     */
    String ID_ATTRIBUTE = "stop_id";

    /**
     *
     */
    String RESULT_ATTRIBUTE = "result";

    /**
     *
     */
    String RESULT_STOP_ATTRIBUTE = "stop";

    /**
     *
     */
    String RESULT_LIST_ATTRIBUTE = "stopList";

    /**
     *
     */
    String DESTINATION_PAGE = "/admin/stops.jsp";
}
