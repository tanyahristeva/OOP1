public class DeterministicCommand implements Command {


    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Нужно е ID. ");
            return;
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton automaton = AutomatonManager.getInstance().getAutomaton(id);
            if (automaton == null) {
                System.out.println("Автомат с ID " + id + " не съществува.");
                return;
            }

            if (DeterminismChecker.isDeterministic(automaton)) {
                System.out.println("Автоматът е детерминиран.");
            } else {
                System.out.println("Автоматът не е детерминиран.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Неяалидно ID. ");
        }
    }
}

