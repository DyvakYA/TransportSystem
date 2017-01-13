package commands;

import commands.plan.PlanCommandFactory;
import commands.plan_of_stops.PlanOfStopsCommandFactory;
import commands.route.RouteCommandFactory;
import commands.stop.StopCommandFactory;
import commands.transport.TransportCommandFactory;
import commands.user.AuthenticateUserCommand;
import commands.user.ChangeLocaleCommand;
import commands.user.RegisterUserCommand;


public class CommandFactory {

    public static final String REGISTER_USER_COMMAND = "registerUser";
    public static final String AUTHENTICATION_COMMAND = "authentication";
    public static final String CHANGE_LOCALE_COMMAND = "changeLocaleCommand";

    private static final String TRANSPORT_COMMANDS = "Transport";
    private static final String STOP_COMMANDS = "Stop";
    private static final String ROUTE_COMMANDS = "Route";
    private static final String SCHEDULE_COMMANDS = "Schedule";
    private static final String SCHEDULE_OF_STOP_COMMANDS = "ScheduleOfStop";

    private static final CommandFactory instance = new CommandFactory();

    private CommandFactory() {

    }

    public static CommandFactory getInstance() {
        return instance;
    }

    public Command getCommand(String name) {

        System.out.println(name);

        if (name == null) {
        }

        if (name.contains(SCHEDULE_OF_STOP_COMMANDS)) {
            PlanOfStopsCommandFactory planOfStopsCommandFactory = PlanOfStopsCommandFactory.getInstance();
            if (planOfStopsCommandFactory.getPlanCommand(name) != null) {
                return planOfStopsCommandFactory.getPlanCommand(name);
            }
        }

        if (name.contains(SCHEDULE_COMMANDS)) {
            PlanCommandFactory planCommandFactory = PlanCommandFactory.getInstance();
            if (planCommandFactory.getPlanCommand(name) != null) {
                return planCommandFactory.getPlanCommand(name);
            }
        }

        if (name.contains(ROUTE_COMMANDS)) {
            RouteCommandFactory routeFactory = RouteCommandFactory.getInstance();
            if (routeFactory.getRouteCommand(name) != null) {
                return routeFactory.getRouteCommand(name);
            }

        }

        if (name.contains(STOP_COMMANDS)) {
            StopCommandFactory stopFactory = StopCommandFactory.getInstance();
            if (stopFactory.getStopCommand(name) != null) {
                return stopFactory.getStopCommand(name);
            }
        }

        if (name.contains(TRANSPORT_COMMANDS)) {
            TransportCommandFactory transportFactory = TransportCommandFactory.getInstance();
            if (transportFactory.getTransportCommand(name) != null) {
                return transportFactory.getTransportCommand(name);
            }
        }

        if (name.contains(AUTHENTICATION_COMMAND)) {
            return new AuthenticateUserCommand();
        }

        if (name.contains(CHANGE_LOCALE_COMMAND)) {
            return new ChangeLocaleCommand();
        }

        if (name.contains(REGISTER_USER_COMMAND)) {
            return new RegisterUserCommand();
        }
        return null;
    }
}
