package Zoho.Strings;
/* 
 * For example:
 * "Hollywood" -> String S
 * 'H' = 0 enclosure = 0
 * 'o'= 1 enclosure = repeated twice * 3= 1*3 = 3
 * 'l'= 0 enclosure=0
 * 'l'= 0 enclosure=0
 * 'y'= 0 enclosure=0
 * 'w'= 0 enclosure=0
 * 'd'= 1 enclosure=1
 * = 3+1 =4
 */
import java.util.HashMap;
import java.util.Map;

public class EnclosedAreasCalculator {

    // Step 1: Define a map for the number of enclosed areas for each character
    private static final Map<Character, Integer> enclosedAreasMap = new HashMap<>();

    static {
        enclosedAreasMap.put('A', 1);
        enclosedAreasMap.put('B', 2);
        enclosedAreasMap.put('D', 1);
        enclosedAreasMap.put('O', 1);
        enclosedAreasMap.put('P', 1);
        enclosedAreasMap.put('Q', 1);
        enclosedAreasMap.put('R', 1);
        enclosedAreasMap.put('a', 1);
        enclosedAreasMap.put('b', 1);
        enclosedAreasMap.put('d', 1);
        enclosedAreasMap.put('e', 1);
        enclosedAreasMap.put('g', 1);
        enclosedAreasMap.put('o', 1);
        enclosedAreasMap.put('p', 1);
        enclosedAreasMap.put('q', 1);
        enclosedAreasMap.put('0', 1);
        enclosedAreasMap.put('6', 1);
        enclosedAreasMap.put('8', 2);
    }

    // Step 2: Function to calculate the total enclosed areas in the string
    public static int calculateEnclosedAreas(String s) {
        int totalEnclosedArea = 0;
        for (int c : s.toCharArray()) {
            totalEnclosedArea += enclosedAreasMap.getOrDefault(c, 0);
        }
        return totalEnclosedArea;
    }

    public static void main(String[] args) {
        String input = "Baloon";

        // B=2+1+2=5
        int result = calculateEnclosedAreas(input);
        System.out.println("Total enclosed areas in '" + input + "': " + result);
    }
}
