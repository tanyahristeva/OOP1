package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.RegexParser;
/**
 * Команда за преобразуване на регулярен израз в краен автомат.
 * Поддържа основни операции като конкатенация, обединение (|) и звезда на Клини (*).
 * Създаденият автомат се добавя към AutomatonManager и може да бъде достъпен по ID.
 *
 * @see AutomatonManager
 * @see RegexParser
 * @see Command
 */
public class RegCommand implements Command {
    private final AutomatonManager manager;
    /**
     * Създава инстанция на командата с референция към мениджър на автомати.
     *
     * @param manager мениджър за автомати, където се съхранява резултатът
     */
    public RegCommand(AutomatonManager manager) {
        this.manager = manager;
    }
    /**
     * Изпълнява командата за преобразуване на регулярен израз.
     * Очаква аргументи във формат: {@code reg <регулярен_израз>}
     *
     * @param args аргументи от командния ред, където args[1] е регулярният израз
     * @throws IllegalArgumentException ако регулярният израз е невалиден
     * @throws ArrayIndexOutOfBoundsException ако няма подаден израз
     * {@code
     * reg a(b|c)*
     * // Създава автомат разпознаващ думи като: a, ab, ac, abb, abc, acb и други.
     * }
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 2) {
            throw new IllegalArgumentException("Нужen e izraz.");
        }

        String regex = args[1];
        try {
            RegexParser parser = new RegexParser(regex);
            Automaton automaton = parser.parse();
            int id = manager.addAutomaton(automaton);
            System.out.println("Създаден е автомат с ID: " + id);
        } catch (IllegalArgumentException e) {
            System.out.println("Невалиден регулярен израз: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Създава нов автомат по регулярен израз.";
    }
}

