public class RegCommand implements Command {
    private final AutomatonManager manager;

    public RegCommand(AutomatonManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            System.out.println("Нужен е израз.");
            return;
        }

        String regex = args[1];
        try {
            RegexParser parser = new RegexParser(regex);
            Automaton automaton = parser.parse();
            int id = manager.addAutomaton(automaton);
            System.out.println("Създаден е автомат с ID: " + id);
        } catch (Exception e) {
            System.out.println("Невалиден регулярен израз: " + e.getMessage());
        }
    }
}

