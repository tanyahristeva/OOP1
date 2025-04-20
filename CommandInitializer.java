public class CommandInitializer {

    public static void initializeCommands(){
        CommandRegistry.registerCommand("list",new ListCommand(AutomatonManager.getInstance()));
        CommandRegistry.registerCommand("print",new PrintCommand(1));
        CommandRegistry.registerCommand("empty", new EmptyCommand());

    }
}
