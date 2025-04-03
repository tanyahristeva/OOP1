import java.util.HashMap;
import java.util.Map;

public class CommandRegistry {
    private static Map<String,Command> commands=new HashMap<>();

    public static void registerCommand(String name, Command command){
        commands.put(name,command);
    }
    public static Command getCommand(String name){
        return commands.get(name);
    }
    public static void executeCommand(String name,String[] args){
        Command command=commands.get(name);
        if(command!=null){
            command.execute(args);
        }
        else{
            System.out.println("Невалидна команда: "+ name);
        }
    }
}
