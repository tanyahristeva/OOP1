package command;

import base.Automaton;
import manager.AutomatonManager;
import serialization.AutomatonSerializer;

import java.util.ArrayList;
import java.util.List;
/**
 * Команда за сериализиране на автомат във файл.
 * Запазва автомат в двоичен формат чрез Java сериализация.
 *
 * @see AutomatonSerializer
 * @see AutomatonManager
 * @see Command
 */
public class SaveSerializedAutomatonCommand implements Command {
    private final AutomatonManager manager;

    public SaveSerializedAutomatonCommand(AutomatonManager manager) {
        this.manager = manager;
    }
    /**
     * Изпълнява сериализация на автомат.
     * Очаква аргументи: {@code serialize <id> <файл>}
     *
     * @param args аргументи от командния ред, където args[1] е ID, args[2] е името на файла
     *
     * {@code
     * serialize 3 automaton.ser
     * // Запазва автомат с ID 3 във файл automaton.ser
     * }
     */
    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            throw new IllegalArgumentException("Нужнo e ID I IME NA FILE ");
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton automaton = manager.getAutomaton(id);
            if (automaton == null) {
                throw new IllegalArgumentException("Няма автомат с ID: "+id);
            }
            String file = args[2];
            AutomatonSerializer.saveSingleAutomaton(automaton,file);
        } catch (NumberFormatException e) {
            System.out.println("ID трябва да е цяло число.");
        }
    }

    @Override
    public String getDescription() {
        return "Команда за сериализиране на автомат във файл.";
    }
}
