public class CommandInitializer {

    public static void initializeCommands(FileManager fileManager){

        CommandRegistry.registerCommand("list",new ListCommand(AutomatonManager.getInstance()),"Извеждане на всички автомати.");
        CommandRegistry.registerCommand("print",new PrintCommand(),"Принтиране на конкретен автомат по ID.");
        CommandRegistry.registerCommand("empty", new EmptyCommand(),"Проверка дали даден автомат има празен език. ");
        CommandRegistry.registerCommand("deterministic",new DeterministicCommand(),"Проверка дали даден автомат е детерминиран.");
        CommandRegistry.registerCommand("recognize",new RecognizeCommand(),"Проверка дали дадена дума е в езика на автомата. ");
        CommandRegistry.registerCommand("open",new OpenCommand(fileManager),"Отваряне на файл.");
        CommandRegistry.registerCommand("close",new CloseCommand(fileManager),"Затваряне на файл.");
        CommandRegistry.registerCommand("save as",new SaveAsCommand(fileManager),"Запазване на файл в");
        CommandRegistry.registerCommand("help",new HelpCommand(),"Помощ");
        CommandRegistry.registerCommand("exit",new ExitCommand(fileManager),"Изход");
    }
}
