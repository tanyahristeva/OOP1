public class EmptyCommand implements Command {
    private final AutomatonManager manager = AutomatonManager.getInstance();

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Употреба: empty <id>");
            return;
        }

        try {
            int id = Integer.parseInt(args[1]);
            Automaton automaton = manager.getAutomaton(id);
            if (automaton == null) {
                System.out.println("Автомат с ID " + id + " не съществува.");
                return;
            }

            if (LanguageChecker.isEmptyLanguage(automaton)) {
                System.out.println("Езикът на автомата е празен.");
            } else {
                System.out.println("Езикът на автомата НЕ е празен.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID: " + args[1]);
        }
    }
}

