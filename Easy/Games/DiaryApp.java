package diary;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DiaryApp {
  private static PasswordManager passwordManager;
  private static DiaryManager diaryManager;
  private static final Scanner scanner = new Scanner(System.in);

  public static void main(String[] args) {
    passwordManager = new PasswordManager("default123");
    diaryManager = new DiaryManager();

    if (passwordManager.verifyPassword()) {
      boolean running = true;
      while (running) {
        showMenu();
        int choice = getIntInput();
        switch (choice) {
          case 1 -> addEntry();
          case 2 -> viewEntries();
          case 3 -> passwordManager.changePassword();
          case 4 -> running = false;
          default -> System.out.println("Invalid choice. Try again.");
        }
      }
    } else {
      System.out.println("Incorrect password. Exiting...");
    }
  }

  private static void showMenu() {
    System.out.println("\nDiary Menu");
    System.out.println("1. Add New Entry");
    System.out.println("2. View All Entries");
    System.out.println("3. Change Password");
    System.out.println("4. Exit");
    System.out.print("Choose an option: ");
  }

  private static void addEntry() {
    System.out.print("Enter title: ");
    String title = scanner.nextLine();
    System.out.print("Enter content: ");
    String content = scanner.nextLine();
    DiaryEntry entry = new DiaryEntry(title, content);
    diaryManager.addEntry(entry);
    System.out.println("Entry added.");
  }

  private static void viewEntries() {
    List<DiaryEntry> entries = diaryManager.getEntries();
    if (entries.isEmpty()) {
      System.out.println("No entries to display.");
    } else {
      for (DiaryEntry entry : entries) {
        System.out.println(entry);
        System.out.println("---------------");
      }
    }
  }

  private static int getIntInput() {
    int choice = -1;
    try {
      choice = Integer.parseInt(scanner.nextLine());
    } catch (NumberFormatException e) {
      System.out.println("Invalid input. Please enter a number.");
    }
    return choice;
  }
}

class PasswordManager {
  private String password;

  public PasswordManager(String initialPassword) {
    this.password = initialPassword;
  }

  @SuppressWarnings("resource")
  public boolean verifyPassword() {
    System.out.print("Enter your password: ");
    String input = new Scanner(System.in).nextLine();
    return input.equals(password);
  }

  public void changePassword() {
    if (verifyPassword()) {
      System.out.print("Enter new password: ");
      @SuppressWarnings("resource")
      String newPassword = new Scanner(System.in).nextLine();
      password = newPassword;
      System.out.println("Password changed successfully.");
    } else {
      System.out.println("Incorrect password.");
    }
  }
}

class DiaryEntry implements Serializable {
  private final String title;
  private final String content;
  private final LocalDateTime timestamp;

  public DiaryEntry(String title, String content) {
    this.title = title;
    this.content = content;
    this.timestamp = LocalDateTime.now();
  }

  public String getTitle() {
    return title;
  }

  public String getContent() {
    return content;
  }

  public LocalDateTime getTimestamp() {
    return timestamp;
  }

  @Override
  public String toString() {
    return "Title: " + title + "\nDate: " + timestamp + "\n\n" + content;
  }
}

class DiaryManager {
  private List<DiaryEntry> entries;
  private final String fileName = "diary.dat";

  public DiaryManager() {
    entries = new ArrayList<>();
    loadEntries();
  }

  public void addEntry(DiaryEntry entry) {
    entries.add(entry);
    saveEntries();
  }

  public List<DiaryEntry> getEntries() {
    return entries;
  }

  private void saveEntries() {
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(fileName))) {
      oos.writeObject(entries);
    } catch (IOException e) {
    }
  }

  @SuppressWarnings("unchecked")
  private void loadEntries() {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(fileName))) {
      entries = (List<DiaryEntry>) ois.readObject();
    } catch (FileNotFoundException e) {
      System.out.println("No previous entries found.");
    } catch (IOException | ClassNotFoundException e) {
    }
  }
}
