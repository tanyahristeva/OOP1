public class PrintCommand implements Command {
    private int id;

    public PrintCommand(int id) {
        this.id = id;
    }

    @Override
    public void execute(String[] args) {
        AutomatonManager.getInstance().print(id);
    }
}
