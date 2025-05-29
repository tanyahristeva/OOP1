package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.DeterministicMutator;
/**
 * Команда за детерминиране на автомат.
 * Преобразува недетерминиран автомат в детерминиран.
 *
 * @see DeterministicMutator
 * @see AutomatonManager
 * @see Command
 */
public class DeterministicMutatorCommand implements Command{

    private final AutomatonManager automatonManager;

    public DeterministicMutatorCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }
    /**
     * Изпълнява детерминиране на автомат.
     * Очаква аргумент: {@code mutator <id>}
     *
     * @param args аргументи от командния ред, където args[1] е идентификационният номер на автомата
     */
    @Override
    public void execute(String[] args) {
    if(args.length!=2){
        throw new IllegalArgumentException("Нужнo e ID.");
    }
    try {
        int id=Integer.parseInt(args[1]);
        Automaton original=automatonManager.getAutomaton(id);

        if(original==null){
            throw new IllegalArgumentException("Няма автомат с ID: "+id);
        }

        Automaton dfa= DeterministicMutator.determinize(original);
        int newID=automatonManager.addAutomaton(dfa);
        System.out.println("Създаден е детерминиран автомат с ID: "+newID);
    }catch (NumberFormatException e){
        System.out.println("Невалиден ID: "+args[1]);
    }
    }

    @Override
    public String getDescription() {
        return "Детерминиране на автомат.";
    }
}
