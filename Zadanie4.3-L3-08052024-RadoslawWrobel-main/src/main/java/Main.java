import java.io.IOException;
import java.util.Scanner;

class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    Service s = new Service();

    boolean programDziala = true;

    while (programDziala) {
      System.out.println("Wybierz opcję:");
      System.out.println("1. Wyświetl zapisane dane studentów");
      System.out.println("2. Dodaj nowego studenta");
      System.out.println("3. Wyczyść bazę danych");
      System.out.println("0. Wyjdź z programu");

      int wybor = scanner.nextInt();

      try {
        switch (wybor) {
          case 1:
            System.out.println("Zapisane dane studentów:");
            for (Student student : s.getStudents()) {
              System.out.println(student.ToString());
            }
            break;
          case 2:
            System.out.println("Podaj imię nowego studenta:");
            String imie = scanner.next();
            System.out.println("Podaj nazwisko nowego studenta:");
            String nazwisko = scanner.next();
            System.out.println("Podaj numer albumu nowego studenta:");
            int numerAlbumu = scanner.nextInt();
            System.out.println("Podaj wiek nowego studenta:");
            int wiek = scanner.nextInt();
            System.out.println("Podaj datę urodzenia nowego studenta w formacie DDMMRRRR:");
            String dataUrodzenia = scanner.next();

            if (isValidDate(dataUrodzenia)) {
              s.addStudent(new Student(imie, nazwisko, numerAlbumu, wiek, dataUrodzenia));
              System.out.println("Nowy student został dodany.");
            } else {
              System.out.println("Niepoprawny format daty. Użyj formatu DDMMRRRR i upewnij się, że data jest prawidłowa.");
            }
            break;
          case 3:
            s.clearDatabase();
            System.out.println("Baza danych została wyczyszczona.");
            break;
          case 0:
            System.out.println("Program zostanie zamknięty.");
            programDziala = false;
            break;
          default:
            System.out.println("Niepoprawny wybór. Wybierz ponownie.");
        }
      } catch (IOException e) {
        System.out.println("Wystąpił błąd podczas operacji na pliku.");
      }
    }
    scanner.close();
  }

  // Funkcja sprawdzająca poprawność daty w formacie DDMMRRRR
  public static boolean isValidDate(String date) {
    if (date.length() != 8) {
      return false;
    }

    int day, month, year;
    try {
      day = Integer.parseInt(date.substring(0, 2));
      month = Integer.parseInt(date.substring(2, 4));
      year = Integer.parseInt(date.substring(4, 8));
    } catch (NumberFormatException e) {
      return false;
    }

    if (month < 1 || month > 12) {
      return false;
    }

    if (day < 1) {
      return false;
    }

    // Maksymalna liczba dni dla każdego miesiąca
    int[] maxDays = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};

    // Sprawdzenie roku przestępnego
    if (month == 2 && isLeapYear(year)) {
      maxDays[1] = 29;
    }

    if (day > maxDays[month - 1]) {
      return false;
    }

    return true;
  }

  // Funkcja sprawdzająca, czy rok jest przestępny
  public static boolean isLeapYear(int year) {
    if (year % 4 == 0) {
      if (year % 100 == 0) {
        return year % 400 == 0;
      } else {
        return true;
      }
    } else {
      return false;
    }
  }
}
