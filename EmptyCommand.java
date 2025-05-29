package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.LanguageChecker;
/**
 * Команда за проверка дали езикът на автомата е празен.
 * Проверява дали има достижими финални състояния.
 *
 * @see LanguageChecker
 * @see AutomatonManager
 * @see Command
 */
public class EmptyCommand implements Command {
    private final AutomatonManager automatonManager;

    public EmptyCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    /**
     * Изпълнява проверка за празен език.
     * Очаква аргументи във формат: {@code empty <id>}
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

            if (LanguageChecker.isEmptyLanguage(automaton)) {
                System.out.println("Езикът на автомата е празен.");
            } else {
                System.out.println("Езикът на автомата НЕ е празен.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Невалидно ID: " + args[1]);
        }
    }

    @Override
    public String getDescription() {
        return "Проверка за празност на език на автомат.";
    }
}

