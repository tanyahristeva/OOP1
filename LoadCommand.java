
public class LoadCommand implements Command {
    private final AutomatonManager automatonManager;

    public LoadCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Използване: load <file>");
            return;
        }

        String file = args[1];
        try {
            Automaton automaton = AutomatonSerializer.loadSingleAutomaton(file);
            automatonManager.addAutomaton(automaton);
            System.out.println("Автоматът беше успешно зареден с ID: " + automaton.getId());

        } catch (IllegalArgumentException e) {
            System.out.println("Грешка при прочитането на автомата: " + e.getMessage());
            }
    }
}

