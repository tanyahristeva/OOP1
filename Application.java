import command.*;
import manager.AutomatonManager;
import utils.FileHandler;
import command.PrintCommand;
import command.SaveSerializedAutomatonCommand;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
/**
 * Основен клас на програмата.
 * Точка на входа и основен цикъл на програмата.
 */
public class Application {
    //private final Map<String, Command> commands = new HashMap<>();
    private final FileHandler fileHandler = new FileHandler();
   // private final AutomatonManager automatonManager = AutomatonManager.getInstance();
    /**
     * Конструктор. Инициализира командите.
     */
    public Application() {
//description, command collections
        /*
        commands.put("open", new OpenCommand(fileHandler));
        commands.put("save", new SaveCommand(fileHandler));
        commands.put("saveas", new SaveAsCommand(fileHandler));
        commands.put("close", new CloseCommand(fileHandler));
        commands.put("exit", new ExitCommand(fileHandler));
        commands.put("help", new HelpCommand());
        commands.put("list", new ListCommand(automatonManager));
        commands.put("print", new PrintCommand());
        commands.put("empty", new EmptyCommand());
        commands.put("deterministic",new DeterministicCommand());
        commands.put("recognize", new RecognizeCommand());
        commands.put("serialize",new SaveSerializedAutomatonCommand(AutomatonManager.getInstance()));
        commands.put("load",new LoadCommand(AutomatonManager.getInstance()));
        commands.put("concat",new ConcatCommand(AutomatonManager.getInstance()));
        commands.put("union",new UnionCommand(AutomatonManager.getInstance()));
        commands.put("finite",new FiniteLanguageCommand(AutomatonManager.getInstance()));
        commands.put("mutator",new DeterministicMutatorCommand(AutomatonManager.getInstance()));
        commands.put("reg",new RegCommand(AutomatonManager.getInstance()));
        commands.put("un",new PositiveClosureCommand(AutomatonManager.getInstance()));
        CommandInitializer.initializeCommands(fileHandler);

         */
        CommandInitializer.initializeCommands(fileHandler);
    }
    /**
     * Стартира приложението.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добре дошли в системата за крайни автомати!");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            CommandNames commandName = CommandNames.valueOf(parts[0].toUpperCase());

            Command command = CommandRegistry.getCommand(commandName);

            if (command != null) {
                try {
                    command.execute(parts);
                } catch (Exception e) {
                    System.out.println("Грешка при изпълнение на командата: " + e.getMessage());
                }
            } else {
                System.out.println("Непозната команда. Използвай 'help' за списък.");
            }
        }
    }
    /**
     * Главен метод.
     *
     * @param args аргументи от командния ред
     */
    public static void main(String[] args) {
        new Application().run();
    }
}
