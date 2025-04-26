public class CommandInitializer {

    public static void initializeCommands(){
        CommandRegistry.registerCommand("list",new ListCommand(AutomatonManager.getInstance()));
        CommandRegistry.registerCommand("print",new PrintCommand());
        CommandRegistry.registerCommand("empty", new EmptyCommand());
        CommandRegistry.registerCommand("deterministic",new DeterministicCommand());
        CommandRegistry.registerCommand("recognize",new RecognizeCommand());
    }
}
