package utils;

import base.Automaton;
import base.State;
import base.Transition;
import validator.AlphabetValidator;
import manager.TransitionManager;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
/**
 * Клас за парсване на автомати от текстови файлове.
 * Поддържа конструиране на автомат от дефиниции на състояния, преходи, начални и финални състояния.
 * Поддържа както единични автомати, така и множество автомати, разделени с "-----".
 */
public class Parser {
    /**
     * Парсира списък от редове, дефиниращи автомат в обект от тип автомат.
     * Използва се за създаване на автомат от текстови редове, съдържащи:
     * states: изброяване на състояния
     * start: начално състояние
     * final: финални състояния
     * transitions: - преходи във формат from symbol to
     *
     * @param lines списък с текстови редове, описващи автомат
     * @return създадения автомат
     * @throws IllegalArgumentException при невалиден входен формат
     */
    public static Automaton parseFromLines(List<String> lines) {

        TransitionManager transitionManager = new TransitionManager();
        Map<String, State> states = new HashMap<>();
        Set<Character> alphabet = new HashSet<>();
        Set<State> finalStates = new HashSet<>();
        State startState = null;

        Iterator<String> iterator = lines.iterator();
        while (iterator.hasNext()) {
            String line = iterator.next().trim();
            if (line.isEmpty()) continue;

            if (line.startsWith("states:")) {
                for (String name : line.substring(7).trim().split("\\s+")) {
                    State state = new State(name, false);
                    states.put(name, state);
                }
            } else if (line.startsWith("start:")) {
                String name = line.substring(6).trim();
                startState = states.get(name);
                if (startState == null)
                    throw new IllegalArgumentException("Началното състояние " + name + " не съществува. ");

            } else if (line.startsWith("final:")) {
                for (String name : line.substring(6).trim().split("\\s+")) {
                    State state = states.get(name);
                    if (state != null) {
                        state.setFinal(true);
                        finalStates.add(state);
                    }
                }
            } else if (line.startsWith("transitions:")) {
                while (iterator.hasNext()) {
                    String transitionLine = iterator.next().trim();
                    if (transitionLine.isEmpty()) break;

                    String[] parts = transitionLine.split("\\s+");
                    if (parts.length != 3) continue;

                    State from = states.get(parts[0]);
                    State to = states.get(parts[2]);
                    char symbol = parts[1].equals("ε") ? 'ε' : parts[1].charAt(0);

                    if (from == null || to == null) {
                        throw new IllegalArgumentException("Невалиден преход: " + line);
                    }
                    transitionManager.addTransition(new Transition(from, to, symbol));
                    alphabet.add(symbol);
                }
            }
        }

            if (!AlphabetValidator.isValidAlphabet(alphabet)) {
                throw new IllegalArgumentException("Азбуката съдържа невалидни символи.");
            }
            return new Automaton(alphabet, new HashSet<>(states.values()), startState, finalStates, transitionManager);
        }
    /**
     * Парсира файл, съдържащ множество автомати, разделени с редове "-----".
     * Всеки блок между разделителите трябва да съдържа валидна дефиниция на автомат.
     *
     * @param filePath път до текстовия файл
     * @return списък от автомати, дефинирани във файла
     * @throws IOException при проблем при четене на файла
     */
        public static List<Automaton> parseMultipleAutomatons (String filePath) throws IOException{
            List<String> allLines = Files.readAllLines(Paths.get(filePath));
            List<Automaton> automatons = new ArrayList<>();
            List<String> current = new ArrayList<>();

            for (String line : allLines) {
                if (line.trim().equals("-----")) {
                    if (!current.isEmpty()) {
                        automatons.add(parseFromLines(current));
                        current.clear();
                    }
                } else {
                    current.add(line);
                }
            }
            if (!current.isEmpty()) {
                automatons.add(parseFromLines(current));
            }
            return automatons;
        }

    /**
     * Парсира текстов файл, съдържащ дефиниция на един автомат.
     *
     * @param filePath път до файла с един автомат
     * @return създаденият автомат
     * @throws IOException ако възникне грешка при четене на файла
     */
    public static Automaton parseFromFile(String filePath) throws IOException{
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return parseFromLines(lines);
    }
}

