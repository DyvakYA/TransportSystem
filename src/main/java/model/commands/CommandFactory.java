package model.commands;

import ua.kpi.epam.transport.commands.route.RouteCommandFactory;
import ua.kpi.epam.transport.commands.schedule.ScheduleCommandFactory;
import ua.kpi.epam.transport.commands.schedule_of_stop.ScheduleOfStopCommandFactory;
import ua.kpi.epam.transport.commands.stop.StopCommandFactory;
import ua.kpi.epam.transport.commands.transport.TransportCommandFactory;
import ua.kpi.epam.transport.commands.user.AutentificateUserCommand;
import ua.kpi.epam.transport.commands.user.ChangeLocaleCommand;
import ua.kpi.epam.transport.commands.user.RegisterUserCommand;

/**
 *
 * @author KIRIL
 */
public class CommandFactory {

    /**
     *
     */
    public static final String REGISTER_USER_COMMAND = "registerUser";

    /**
     *
     */
    public static final String AUTENTIFICATION_COMMAND = "autentification";

    /**
     *
     */
    public static final String CHANGE_LOCALE_COMMAND = "changeLocaleCommand";

    private static final String TRANSPORT_COMMANDS = "Transport";
    private static final String STOP_COMMANDS = "Stop";
    private static final String ROUTE_COMMANDS = "Route";
    private static final String SCHEDULE_COMMANDS = "Schedule";
    private static final String SCHEDULE_OF_STOP_COMMANDS = "ScheduleOfStop";

    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {

    }

    /**
     *
     * @return
     */
    public static CommandFactory getInstance() {
        return instance;
    }

    /**
     *
     * @param name
     * @return
     */
    public Command getCommand(String name) {

        if(name == null){

        }
        
        if (name.contains(SCHEDULE_OF_STOP_COMMANDS)) {
            ScheduleOfStopCommandFactory scheduleOfStopFactory = ScheduleOfStopCommandFactory
                    .getInstanse();
            if (scheduleOfStopFactory.getScheduleCommand(name) != null) {
                return scheduleOfStopFactory.getScheduleCommand(name);
            }
        }

        if (name.contains(SCHEDULE_COMMANDS)) {
            ScheduleCommandFactory scheduleCommandFactory = ScheduleCommandFactory
                    .getInstanse();
            if (scheduleCommandFactory.getScheduleCommand(name) != null) {
                return scheduleCommandFactory.getScheduleCommand(name);
            }
        }

        if (name.contains(ROUTE_COMMANDS)) {

            RouteCommandFactory routeFactory = RouteCommandFactory
                    .getInstanse();
            if (routeFactory.getRouteCommand(name) != null) {
                return routeFactory.getRouteCommand(name);
            }

        }

        if (name.contains(STOP_COMMANDS)) {
            StopCommandFactory stopFactory = StopCommandFactory.getInstanse();
            if (stopFactory.getStopCommand(name) != null) {
                return stopFactory.getStopCommand(name);
            }
        }

        if (name.contains(TRANSPORT_COMMANDS)) {
            TransportCommandFactory transportFactory = TransportCommandFactory
                    .getInstanse();
            if (transportFactory.getTransportCommand(name) != null) {
                return transportFactory.getTransportCommand(name);
            }
        }

        switch (name) {
            case AUTENTIFICATION_COMMAND: {
                return new AutentificateUserCommand();
            }

            case CHANGE_LOCALE_COMMAND: {
                return new ChangeLocaleCommand();
            }
            case REGISTER_USER_COMMAND: {
                return new RegisterUserCommand();
            }

            default: {
                return null;

            }
        }
    }

}
