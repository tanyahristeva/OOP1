import java.util.Set;

public class AlphabetValidator {

    private static final String validCharacters ="abcdefghijklmnopqrstuvwxyz0123456789";

    public static boolean isValidAlphabet(Set<Character> alphabet){
        if(alphabet==null||alphabet.isEmpty()){
            return false;
        }
        for(Character ch:alphabet){
            if(validCharacters.indexOf(ch)==-1){
                return false;
            }
        }
        return true;
    }
}
