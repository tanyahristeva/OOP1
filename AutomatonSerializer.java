package serialization;

import base.Automaton;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * Клас за сериализация и десериализация на автомати.
 * Поддържа запис и зареждане както на единични, така и на списъци от автомати.
 */
public class AutomatonSerializer{
    /**
     * Сериализира и записва един автомат във файл.
     *
     * @param automaton автоматът, който ще бъде записан
     * @param fileName име и път до файла
     */
    public static void saveSingleAutomaton(Automaton automaton, String fileName) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            out.writeObject(automaton);
            out.close();
            System.out.println("Автоматът е записан успешно в " + fileName);
        } catch (IOException e) {
            System.out.println("Грешка при запис: " + e.getMessage());
        }
    }
    /**
     * Зарежда автомат от сериализиран файл.
     *
     * @param fileName път до файла
     * @return автоматът, който е бил зареден, или null при грешка
     */
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
    /**
     * Записва списък от автомати чрез сериализация във файл.
     *
     * @param automatons списък с автомати за запис
     * @param fileName път до файла
     * @throws IOException при възникване на грешка по време на запис
     */
    public static void saveMultipleAutomatons(List<Automaton>automatons, String fileName)throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(fileName))) {
            for (Automaton automaton : automatons) {
                out.writeObject(automaton);
            }
        }
    }
    /**
     * Зарежда множество автомати от файл със сериализирани обекти.
     *
     * @param fileName път до файла
     * @return списък с автоматите
     * @throws IOException при грешка при четене
     */
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
