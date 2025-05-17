public class PositiveClosureCommand implements Command {
    private final AutomatonManager automatonManager;

    public PositiveClosureCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Нужно е ID. ");
            return;
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton original = automatonManager.getAutomaton(id);
            if (original == null) {
                System.out.println("Автомат с ID " + id + " не съществува.");
                return;
            }
            Automaton positiveClosure = PositiveClosure.createPositiveClosure(original);
            int newID = automatonManager.addAutomaton(positiveClosure);
            System.out.println("Създаден е автомат с позитивна обвивка с ID: " + newID);
        } catch (NumberFormatException e) {
            System.out.println("Невалиден ID. Използвайте цяло число");
        }
    }
}

