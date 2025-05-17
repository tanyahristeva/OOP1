import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Application {
    private final Map<String, Command> commands = new HashMap<>();
    private final FileManager fileManager = new FileManager();
    private final AutomatonManager automatonManager = AutomatonManager.getInstance();

    public Application() {

        commands.put("open", new OpenCommand(fileManager));
        commands.put("save", new SaveCommand(fileManager));
        commands.put("saveas", new SaveAsCommand(fileManager));
        commands.put("close", args -> fileManager.close());
        commands.put("exit", new ExitCommand(fileManager));
        commands.put("help", new HelpCommand());
        commands.put("list", new ListCommand(automatonManager));
        commands.put("print", new PrintCommand());
        commands.put("empty", new EmptyCommand());
        commands.put("deterministic",new DeterministicCommand());
        commands.put("recognize", new RecognizeCommand());
        commands.put("serialize",new SaveSerializedAutomaton(AutomatonManager.getInstance()));
        commands.put("load",new LoadCommand(AutomatonManager.getInstance()));
        commands.put("concat",new ConcatCommand(AutomatonManager.getInstance()));
        commands.put("union",new UnionCommand(AutomatonManager.getInstance()));
        commands.put("finite",new FiniteLanguageCommand(AutomatonManager.getInstance()));
        commands.put("mutator",new DeterministicMutatorCommand(AutomatonManager.getInstance()));
        commands.put("reg",new RegCommand(AutomatonManager.getInstance()));
        commands.put("un",new PositiveClosureCommand(AutomatonManager.getInstance()));
        CommandInitializer.initializeCommands(fileManager);
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Добре дошли в системата за крайни автомати!");
        while (true) {
            System.out.print("> ");
            String input = scanner.nextLine().trim();

            if (input.isEmpty()) continue;

            String[] parts = input.split("\\s+");
            String commandName = parts[0].toLowerCase();
            Command command = commands.get(commandName);

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

    public static void main(String[] args) {
        new Application().run();
    }
}
