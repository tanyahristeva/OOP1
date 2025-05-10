import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
public class AutomatonSerializer{
    public static void saveSingleAutomaton(Automaton automaton, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(automaton);
            out.close();
            System.out.println("Автоматът е записан успешно в " + fileName);
        } catch (IOException e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }
    public static Automaton loadSingleAutomaton(String fileName) {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            Automaton automaton = (Automaton) in.readObject();
            in.close();
            System.out.println("Автоматът е зареден успешно от " + fileName);
            return automaton;
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Грешка при зареждане: " + e.getMessage());
            return null;
        }
    }

    public static void saveMultipleAutomatons(List<Automaton>automatons, String fileName)throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Automaton automaton : automatons) {
                out.writeObject(automaton);
            }
        }
    }

    public static List<Automaton> loadMultipleAutomatons(String fileName)throws IOException {
        List<Automaton> automatons = new ArrayList<>();
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(fileName))) {
            while (true) {
                try {
                    Automaton automaton = (Automaton) in.readObject();
                    automatons.add(automaton);
                } catch (IOException | ClassNotFoundException e) {
                    break;
                }
            }
        }
        return automatons;
    }




}
