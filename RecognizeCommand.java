public class RecognizeCommand implements Command {

    @Override
    public void execute(String[] args) {
        if (args.length != 3) {
            System.out.println("Трябва да има ID и дума. ");
            return;
        }

        String id = args[1];
        String word = args[2];

        Automaton automaton = AutomatonManager.getInstance().getAutomaton(Integer.parseInt(id));
        if (automaton == null) {
            System.out.println("Автоматът не е намерен.");
            return;
        }

        boolean result = RecognizeChecker.recognize(automaton, word);
        System.out.println(result ? "Думата е приета от автомата. " : "Думата не е приета от автомата.");
    }
}
