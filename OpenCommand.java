package command;

import base.Automaton;
import manager.AutomatonManager;
import utils.FileHandler;
import utils.Parser;

import java.util.List;
/**
 * Команда за отваряне на файл с автомати.
 * Поддържа текстови файлове с дефиниции на автомати.
 *
 * @see Parser
 * @see FileHandler
 * @see Command
 */
public class OpenCommand implements Command {
    private final FileHandler fileHandler;
    private final AutomatonManager automatonManager;

    public OpenCommand(FileHandler fileHandler, AutomatonManager automatonManager) {
        this.fileHandler = fileHandler;
        this.automatonManager=automatonManager;
    }
    /**
     * Изпълнява отваряне на файл и зареждане на автоматите, записани вътре в него.
     * Очаква аргумент: {@code open <файл>}
     *
     * @param args аргументи от командния ред, където args[1] е името на файла
     */
    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            throw new IllegalArgumentException("Нужно е име на файл. ");
        }

        String fileName = args[1];
        fileHandler.open(fileName);

        try {
            List<Automaton>automatons= Parser.parseMultipleAutomatons(fileName);
            for(Automaton automaton: automatons){
                int id=automatonManager.addAutomaton(automaton);
                System.out.println("Файлът е отворен и автоматът е зареден с ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Грешка при прочитане на автомата: " + e.getMessage());
        }
    }

    @Override
    public String getDescription() {
        return "Отваряне на файл";
    }
}

