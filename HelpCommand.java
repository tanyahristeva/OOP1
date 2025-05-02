public class HelpCommand implements Command{

    @Override
    public void execute(String[] args) {
        System.out.println("Поддържани команди:");
        for(String command:CommandRegistry.getSupportedCommands()){
            System.out.println(command+" - "+CommandRegistry.getCommandDescription(command));
        }
    }
}
