public class DeterministicMutatorCommand implements Command{

    private AutomatonManager automatonManager;

    public DeterministicMutatorCommand(AutomatonManager automatonManager) {
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
        Automaton original=automatonManager.getAutomaton(id);

        if(original==null){
            System.out.println("Няма аетомат с ID: "+id);
            return;
        }

        Automaton dfa=DeterministicMutator.determinize(original);
        int newID=automatonManager.addAutomaton(dfa);
        System.out.println("Създаден е детерминиран автомат с ID: "+newID);
    }catch (NumberFormatException e){
        System.out.println("Невалиден ID: "+args[1]);
    } catch (IllegalAccessException e) {
        throw new RuntimeException(e);
    }
    }
}
