import java.util.Set;

public class PrintCommand implements Command {

    @Override
    public void execute(String[] args) {
        if(args.length<2){
            System.out.println("Нужно е ID. ");
            return;
        }
        try{
            int id=Integer.parseInt(args[1]);
           Automaton automaton= AutomatonManager.getInstance().getAutomaton(id);
           if(automaton==null){
               System.out.println("Автомат с ID "+id+" не съществува.");
               return;
           }
            if(automaton.getTransitionManager()==null){
                System.out.println("Няма преходи.");
            }
            Set<Transition> transitions=automaton.getTransitions();
           if(transitions.isEmpty()){
               System.out.println("Автомат с ID "+id+" няма преходи.");
               return;
           }
           System.out.println("Преходи на автомат с ID "+id+" :");
           for (Transition transition:transitions){
               System.out.println(transition);
           }

        }

        catch (NumberFormatException e){
            System.out.println("Невалидно ID.");
        }
    }
}
