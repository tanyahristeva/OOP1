package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.AutomatonUnion;
/**
 * Команда за обединение на два автомата.
 * Създава автомат разпознаващ думите на първия или втория автомат.
 *
 * @see AutomatonUnion
 * @see AutomatonManager
 * @see Command
 */
public class UnionCommand implements Command {
    private final AutomatonManager automatonManager;
    private final AutomatonUnion union;

    public UnionCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
        this.union = new AutomatonUnion();
    }
    /**
     * Изпълнява операцията обединение.
     * Очаква аргументи във формат: {@code union <id1> <id2>}
     *
     * @param args масив с аргументи, където args[1] и args[2] са ID-та на автоматите
     * @throws IllegalArgumentException при невалидни ID-та

     */
    @Override
    public void execute(String[] args) {
        if (args.length < 3) {
            throw new IllegalArgumentException("Нужни са две ID-та.");
        }
        try {
            int id1 = Integer.parseInt(args[1]);
            int id2 = Integer.parseInt(args[2]);
            Automaton automaton1 = automatonManager.getAutomaton(id1);
            Automaton automaton2 = automatonManager.getAutomaton(id2);
            if (automaton1 == null || automaton2 == null) {
                throw new IllegalArgumentException("Edin ot avtomatite ne sushtestvuva");
            }
            Automaton result = union.union(automaton1, automaton2);
            int newID = automatonManager.addAutomaton(result);
            System.out.println("Новият автомат след обединение има ID: " + newID);
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID.");
        }
    }

    @Override
    public String getDescription() {
        return "Команда за обединение на два автомата.";
    }
}