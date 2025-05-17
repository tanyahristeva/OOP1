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
        CommandRegistry.registerCommand("serialize",new SaveSerializedAutomaton(AutomatonManager.getInstance()),"Сериализиране на автомат.");
        CommandRegistry.registerCommand("load",new LoadCommand(AutomatonManager.getInstance()),"Зареждане на сериализиран автомат.");
        CommandRegistry.registerCommand("concat",new ConcatCommand(AutomatonManager.getInstance()),"Конкатинация на автомати.");
        CommandRegistry.registerCommand("union",new UnionCommand(AutomatonManager.getInstance()),"Обединение на автомати.");
        CommandRegistry.registerCommand("finite",new FiniteLanguageCommand(AutomatonManager.getInstance()),"Проверява дали езикът на автомат е краен.");
        CommandRegistry.registerCommand("mutator",new DeterministicMutatorCommand(AutomatonManager.getInstance()),"Мутатор за детерминиране на автомат.");
        CommandRegistry.registerCommand("reg",new RegCommand(AutomatonManager.getInstance()),"Създава нов автомат по регулярен израз.");
        CommandRegistry.registerCommand("un",new PositiveClosureCommand(AutomatonManager.getInstance()),"Намира позитивна обвивка на автомат и създава нов автомат.");
    }
}
