package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.PositiveClosure;
/**
 * Команда за създаване на позитивна обвивка на автомат.
 * Позитивната обвивка позволява едно или повече повторения на оригиналния език.
 *
 * @see PositiveClosure
 * @see AutomatonManager
 * @see Command
 */
public class PositiveClosureCommand implements Command {
    private final AutomatonManager automatonManager;

    public PositiveClosureCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }
    /**
     * Изпълнява създаване на позитивна обвивка.
     * Очаква аргумент: {@code un <id>}
     *
     * @param args аргументи от командния ред,където args[1] е ID на автомата
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            //System.out.println("Нужно е ID. ");
            throw new IllegalArgumentException("Нужно е ID .");
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton original = automatonManager.getAutomaton(id);
            if (original == null) {
                throw new IllegalArgumentException("Няма автомат с ID: "+id);
            }
            Automaton positiveClosure = PositiveClosure.createPositiveClosure(original);
            int newID = automatonManager.addAutomaton(positiveClosure);
            System.out.println("Създаден е автомат с позитивна обвивка с ID: " + newID);
        } catch (NumberFormatException e) {
            System.out.println("Невалиден ID. Използвайте цяло число");
        }
    }

    @Override
    public String getDescription() {
        return "Намира позитивна обвивка на автомат и създава нов автомат.";
    }
}

