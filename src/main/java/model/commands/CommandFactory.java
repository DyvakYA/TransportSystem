package model.commands;

import model.commands.transport.TransportCommandFactory;
import model.commands.user.AutentificateUserCommand;
import model.commands.user.ChangeLocaleCommand;
import model.commands.user.RegisterUserCommand;


public class CommandFactory {

    public static final String REGISTER_USER_COMMAND = "registerUser";
    public static final String AUTENTIFICATION_COMMAND = "autentification";
    public static final String CHANGE_LOCALE_COMMAND = "changeLocaleCommand";

    private static final String TRANSPORT_COMMAND = "Transport";
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

//        if (name.contains(SCHEDULE_OF_STOP_COMMANDS)) {
//            ScheduleOfStopCommandFactory scheduleOfStopFactory = ScheduleOfStopCommandFactory
//                    .getInstanse();
//            if (scheduleOfStopFactory.getScheduleCommand(name) != null) {
//                return scheduleOfStopFactory.getScheduleCommand(name);
//            }
//        }
//
//        if (name.contains(SCHEDULE_COMMANDS)) {
//            ScheduleCommandFactory scheduleCommandFactory = ScheduleCommandFactory
//                    .getInstanse();
//            if (scheduleCommandFactory.getScheduleCommand(name) != null) {
//                return scheduleCommandFactory.getScheduleCommand(name);
//            }
//        }
//
//        if (name.contains(ROUTE_COMMANDS)) {
//
//            RouteCommandFactory routeFactory = RouteCommandFactory
//                    .getInstanse();
//            if (routeFactory.getRouteCommand(name) != null) {
//                return routeFactory.getRouteCommand(name);
//            }
//
//        }
//
//        if (name.contains(STOP_COMMANDS)) {
//            StopCommandFactory stopFactory = StopCommandFactory.getInstanse();
//            if (stopFactory.getStopCommand(name) != null) {
//                return stopFactory.getStopCommand(name);
//            }
//        }
//
        if (name.contains(TRANSPORT_COMMAND)) {
            TransportCommandFactory transportFactory = TransportCommandFactory
                    .getInstanse();
            if (transportFactory.getTransportCommand(name) != null) {
                return transportFactory.getTransportCommand(name);
            }
        }

        if (name.contains(AUTENTIFICATION_COMMAND)) {
            return new AutentificateUserCommand();
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
