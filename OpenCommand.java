import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenCommand implements Command {
    private final FileManager fileManager;
    private final AutomatonManager automatonManager = AutomatonManager.getInstance();

    public OpenCommand(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length < 2) {
            System.out.println("Нужно е име на файла.");
            return;
        }

        String fileName = args[1];
        fileManager.open(fileName);

        try {
            List<Automaton>automatons=Parser.parseMultipleAutomatons(fileName);
            for(Automaton automaton: automatons){
                int id=automatonManager.addAutomaton(automaton);
                System.out.println("Файлът е отворен и автоматът е зареден с ID: " + id);
            }
        } catch (Exception e) {
            System.out.println("Грешка при прочитане на автомата: " + e.getMessage());
        }
    }
}

