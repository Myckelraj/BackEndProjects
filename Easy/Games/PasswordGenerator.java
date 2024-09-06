import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class PasswordGenerator {

  // Define character sets for different complexity levels
  private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
  private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
  private static final String DIGITS = "0123456789";
  private static final String SPECIAL_CHARACTERS = "!@#$%^&*()-_=+[]{}|;:',.<>?/";

  public static String generatePassword(int length, boolean useLower, boolean useUpper, boolean useDigits,boolean useSpecial) {
    if (length <= 0) {
      throw new IllegalArgumentException("Password length must be greater than zero.");
    }

    StringBuilder password = new StringBuilder(length);
    SecureRandom random = new SecureRandom();
    List<String> characterPool = new ArrayList<>();

    if (useLower) {
      characterPool.add(LOWERCASE);
    }
    if (useUpper) {
      characterPool.add(UPPERCASE);
    }
    if (useDigits) {
      characterPool.add(DIGITS);
    }
    if (useSpecial) {
      characterPool.add(SPECIAL_CHARACTERS);
    }

    if (characterPool.isEmpty()) {
      throw new IllegalArgumentException("At least one character set must be selected.");
    }

    // Ensure at least one character from each selected set is included
    for (String charSet : characterPool) {
      password.append(charSet.charAt(random.nextInt(charSet.length())));
    }

    // Fill the remaining password length with random characters from the combined
    // pool
    String combinedPool = String.join("", characterPool);
    for (int i = password.length(); i < length; i++) {
      password.append(combinedPool.charAt(random.nextInt(combinedPool.length())));
    }

    // Shuffle the result to avoid predictable patterns
    return shuffleString(password.toString());
  }

  private static String shuffleString(String input) {
    List<Character> characters = new ArrayList<>();
    for (char c : input.toCharArray()) {
      characters.add(c);
    }
    StringBuilder output = new StringBuilder(input.length());
    while (!characters.isEmpty()) {
      output.append(characters.remove((int) (Math.random() * characters.size())));
    }
    return output.toString();
  }

  public static void main(String[] args) {
    // Example usage:
    int length = 12;
    boolean useLower = true;
    boolean useUpper = true;
    boolean useDigits = true;
    boolean useSpecial = true;

    String password = generatePassword(length, useLower, useUpper, useDigits, useSpecial);
    System.out.println("Generated Password: " + password);
  }
}
