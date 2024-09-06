package Zoho.Strings;

public class UpperToLower {
    public static void main(String[] args) {
        String str = "Myckelraj";
        StringBuilder newStr = new StringBuilder(str);
        for (int i = 0; i < str.length(); i++) {
            if (Character.isLowerCase(str.charAt(i))) {
                newStr.setCharAt(i, Character.toUpperCase(str.charAt(i)));
            } else if (Character.isUpperCase(str.charAt(i))) {
                newStr.setCharAt(i, Character.toLowerCase(str.charAt(i)));
            }
        }
        System.out.print(newStr);
    }
}
