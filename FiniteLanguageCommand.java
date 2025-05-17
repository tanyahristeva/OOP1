public class FiniteLanguageCommand implements Command{
    private final AutomatonManager automatonManager;

    public FiniteLanguageCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    @Override
    public void execute(String[] args) {
    if(args.length!=2){
        System.out.println("Нужно е ID.");
        return;
    }
    try {
        int id=Integer.parseInt(args[1]);
        Automaton automaton=automatonManager.getAutomaton(id);
        if(automaton==null){
            System.out.println("Автомат с ID "+id+" не съществува.");
        return;
        }
        boolean isFinite=FiniteLanguageChecker.isFinite(automaton);
        if(isFinite){
            System.out.println("Езикът на автомата с ID "+id+" е краен.");
        }
        else {
            System.out.println("Езикът на автомата с ID "+id+" не е краен.");
        }
    }catch (NumberFormatException e){
        System.out.println("Невалидно ID. Трябва да бъде цяло число.");
    }
    }
}
