package validator;

import java.util.Set;
/**
 * Клас за валидация на азбука на креаен автомат, който проверява дали всички символи в азбуката са разрешени.
 * Разрешените символи включват малки латински букви (a-z) и цифрите от 0 до 9.
 */
public class AlphabetValidator {

    private static final String validCharacters ="abcdefghijklmεnopqrstuvwxyz0123456789";
    /**
     * Проверява дали дадената азбука е валидна.
     * Азбуката се счита за валидна ако не е празна и съдържа само валидни символи - малки латински букви (a-z) и цифрите от 0 до 9.
     * @param alphabet множество от символи за проверка
     * @return true ако азбуката е валидна, false в противен случай
     */
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
