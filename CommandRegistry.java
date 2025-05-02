import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class CommandRegistry {
    private static Map<String,Command> commands=new HashMap<>();
    private static Map<String, String> commandDescriptions=new HashMap<>();
    public static void registerCommand(String name, Command command,String description){

        commands.put(name,command);
        commandDescriptions.put(name,description);
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

   public static String getCommandDescription(String name){
        return commandDescriptions.getOrDefault(name,"Няма описание.");
   }

   public static Set<String> getSupportedCommands(){
        return commands.keySet();
   }
}
