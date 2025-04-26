public class PrintCommand implements Command {

    @Override
    public void execute(String[] args) {
        if(args.length<2){
            System.out.println("Нужно е ID. ");
            return;
        }
        try{
            int id=Integer.parseInt(args[1]);
            AutomatonManager.getInstance().print(id);
        }catch (NumberFormatException e){
            System.out.println("Невалидно ID.");
        }
    }
}
