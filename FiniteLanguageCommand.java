package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.FiniteLanguageChecker;
/**
 * Команда за проверка дали езикът на автомат е краен.
 * Проверява за наличие на цикли, достижими от финални състояния.
 *
 * @see FiniteLanguageChecker
 * @see AutomatonManager
 * @see Command
 */
public class FiniteLanguageCommand implements Command{
    private final AutomatonManager automatonManager;

    public FiniteLanguageCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }
    /**
     * Изпълнява проверка за краен език.
     * Очаква аргумент: {@code finite <id>}
     *
     * @param args аргументи от командния ред, където args[1] е идентификационният номер на автомата
     */
    @Override
    public void execute(String[] args) {
    if(args.length!=2){
        throw new IllegalArgumentException("Nujno e ID.");
    }
    try {
        int id=Integer.parseInt(args[1]);
        Automaton automaton=automatonManager.getAutomaton(id);
        if(automaton==null){
            throw new IllegalArgumentException("Няма автомат с ID: "+id);
        }
        boolean isFinite= FiniteLanguageChecker.isFinite(automaton);
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

    @Override
    public String getDescription() {
        return "Команда за проверка за крайност на езика.";
    }
}
