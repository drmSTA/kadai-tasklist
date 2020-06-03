package utility;

public class StringValidator {
    public static boolean isEmpty(String input){
      if(input == null || input.equals("")) return true;
      return false;
    }

    public static boolean isNotAcceptableLength(String input, int lengthUL){
      if(input.length() > lengthUL) return true;
      return false;
    }
}
