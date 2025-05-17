public class UnionCommand implements Command {
    private final AutomatonManager automatonManager;
    private final AutomatonUnion union;

    public UnionCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
        this.union = new AutomatonUnion();
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            System.out.println("Нужни са две ID-та.");
            return;
        }
        try {
            int id1 = Integer.parseInt(args[1]);
            int id2 = Integer.parseInt(args[2]);
            AutomatonManager automatonManager = AutomatonManager.getInstance();
            Automaton automaton1 = automatonManager.getAutomaton(id1);
            Automaton automaton2 = automatonManager.getAutomaton(id2);
            if (automaton1 == null || automaton2 == null) {
                System.out.println("Един от автоматите не съществува.");
                return;
            }
            Automaton result = union.union(automaton1, automaton2);
            int newID = automatonManager.addAutomaton(result);
            System.out.println("Новият автомат след обединение има ID: " + newID);
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID.");
        }
    }
}