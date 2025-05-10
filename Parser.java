import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Parser {

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

        public static List<Automaton> parseMultipleAutomatons (String filePath) throws IOException {
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


    public static Automaton parseFromFile(String filePath) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(filePath));
        return parseFromLines(lines);
    }
}

