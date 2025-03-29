public class ListCommand implements Command {

    private final AutomatonManager manager;

    public ListCommand(AutomatonManager manager) {
        this.manager = AutomatonManager.getInstance();
    }

    @Override
    public void execute(String[] args) {
        manager.listAutomatons();
    }
}
