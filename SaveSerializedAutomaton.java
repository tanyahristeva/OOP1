import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SaveSerializedAutomaton implements Command {
    private final AutomatonManager manager;

    public SaveSerializedAutomaton(AutomatonManager manager) {
        this.manager = manager;
    }

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Нужно е ID и име на файла.");
            return;
        }
        try {
            int id = Integer.parseInt(args[1]);
            Automaton automaton = manager.getAutomaton(id);
            if (automaton == null) {
                System.out.println("Няма автомат с ID " + id);
                return;
            }
            String file = args[2];
            List<Automaton> automatonList=new ArrayList<>();
            automatonList.add(automaton);
            AutomatonSerializer.saveSingleAutomaton(automaton,file);
        } catch (NumberFormatException e) {
            System.out.println("ID трябва да е цяло число.");
        }
    }
}
