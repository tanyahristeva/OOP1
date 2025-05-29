package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.AutomatonConcatenation;
/**
 * Команда за конкатенация на два автомата.
 * Създава нов автомат, който разпознава думите на първия автомат, последвани от думите на втория.
 *
 * @see AutomatonConcatenation
 * @see AutomatonManager
 * @see Command
 */
public class ConcatCommand implements Command{
    private final AutomatonManager automatonManager;
    private final AutomatonConcatenation concatAutomaton;

    public ConcatCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
        this.concatAutomaton = new AutomatonConcatenation();
    }
    /**
     * Изпълнява конкатенация на два автомата.
     * Очаква аргументи във формат: {@code concat <id1> <id2>}
     *
     * @param args масив с аргументи, където args[1] и args[2] са ID-та на автоматите
     * @throws IllegalArgumentException при невалидни ID-та или липсващи автомати
     *
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
                throw new IllegalArgumentException("Няма автомат.");
            }

            Automaton result = concatAutomaton.concatAutomatons(automaton1,automaton2);
            int newID = automatonManager.addAutomaton(result);
            System.out.println("Новият автомат след конкатенация има ID: " + newID);
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID.");
        }
    }

    @Override
    public String getDescription() {
        return "Конкатенация на автомати.";
    }
}
