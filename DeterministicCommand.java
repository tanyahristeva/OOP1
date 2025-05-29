package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.DeterminismChecker;
/**
 * Команда за проверка дали автомат е детерминиран.
 * Проверява за наличие на ε-преходи и недетерминирани преходи.
 *
 * @see DeterminismChecker
 * @see AutomatonManager
 * @see Command
 */
public class DeterministicCommand implements Command {
    private final AutomatonManager automatonManager;

    public DeterministicCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    /**
     * Изпълнява проверка за детерминираност.
     * Очаква аргументи във формат: {@code deterministic <id>}
     *
     * @param args масив с аргументи, където args[1] е ID на автомата
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Нужнo e ID.");
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton automaton = automatonManager.getAutomaton(id);
            if (automaton == null) {
                throw new IllegalArgumentException("Няма автомат с ID: "+id);
            }

            if (DeterminismChecker.isDeterministic(automaton)) {
                System.out.println("Автоматът е детерминиран.");
            } else {
                System.out.println("Автоматът не е детерминиран.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID. ");
        }
    }

    @Override
    public String getDescription() {
        return "Проверка дали даден автомат е детерминиран.";
    }
}

