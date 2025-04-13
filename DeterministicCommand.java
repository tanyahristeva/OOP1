public class DeterministicCommand implements Command {
    private final int id;

    public DeterministicCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(String[] args) {
        Automaton automaton = AutomatonManager.getInstance().getAutomaton(id);
        if (automaton == null) {
            System.out.println("Автомат с ID " + id + " не съществува.");
            return;
        }

        if (DeterminismChecker.isDeterministic(automaton)) {
            System.out.println("Автоматът е детерминиран.");
        } else {
            System.out.println("Автоматът НЕ е детерминиран.");
        }
    }
}

