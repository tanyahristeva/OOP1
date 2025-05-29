package command;
/**
 * Команда за показване на помощна информация.
 * Извежда списък с наличните команди и тяхното описание.
 *
 * @see CommandRegistry
 * @see Command
 */
public class HelpCommand implements Command {
    /**
     * Изпълнява показването на помощна информация.
     * Не изисква аргументи.
     *
     * @param args аргументи от командния ред (не се използват)
     */
    @Override
    public void execute(String[] args) {
        System.out.println("Поддържани команди:");
        for(CommandNames command: CommandRegistry.getSupportedCommands()){
            System.out.println(command+" - "+CommandRegistry.getCommandDescription(CommandNames.valueOf(String.valueOf(command))));
        }
    }

    @Override
    public String getDescription() {
        return "Извежда останалите команди.";
    }
}
