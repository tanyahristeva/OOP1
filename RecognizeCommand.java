package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.RecognizeChecker;
/**
 * Команда за проверка дали дума се разпознава от автомат.
 * Проверява дали входната дума принадлежи към езика на автомата.
 *
 * @see RecognizeChecker
 * @see AutomatonManager
 * @see Command
 */
public class RecognizeCommand implements Command {
    /**
     * Изпълнява проверка за разпознаване на дума.
     * Очаква аргументи във формат: {@code recognize <id> <дума>}
     *
     * @param args масив с аргументи, където args[1] е ID, args[2] е думата
     */
    private final AutomatonManager automatonManager;

    public RecognizeCommand(AutomatonManager automatonManager) {
        this.automatonManager = automatonManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Нужнo e id и дума.");
        }


        String word = args[2];
        int id = Integer.parseInt(args[1]);
        Automaton automaton = automatonManager.getAutomaton(id);
        if (automaton == null) {
            throw new IllegalArgumentException("Няма автомат с ID: "+id);
        }

        boolean result = RecognizeChecker.recognize(automaton, word);
        System.out.println(result ? "Думата е приета от автомата. " : "Думата не е приета от автомата.");
    }

    @Override
    public String getDescription() {
        return "Проверява дали входната дума принадлежи към езика на автомата.";
    }
}
