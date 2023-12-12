import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class UserDataApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные (Фамилия Имя Отчество датарождения номертелефона пол):");
        String input = scanner.nextLine();

        try {
            processInput(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String input) throws Exception {
        String[] parts = input.split("\\s+");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество аргументов. Введено " + parts.length + ", ожидалось 6.");
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = validateDate(parts[3]);
        long phoneNumber = validatePhoneNumber(parts[4]);
        char gender = validateGender(parts[5]);

        writeToFile(lastName, firstName, middleName, birthDate, phoneNumber, gender);
    }

    private static String validateDate(String date) throws IllegalArgumentException {
        // проверка формата даты dd.mm.yyyy
        return date;
    }

    private static long validatePhoneNumber(String number) throws NumberFormatException {
        //проверка номера телефона
        return Long.parseLong(number);
    }

    private static char validateGender(String gender) throws IllegalArgumentException {
        if (!gender.equals("f") && !gender.equals("m")) {
            throw new IllegalArgumentException("Неверное значение пола. Ожидалось 'f' или 'm', получено: " + gender);
        }
        return gender.charAt(0);
    }

    private static void writeToFile(String lastName, String firstName, String middleName, String birthDate, long phoneNumber, char gender) {
        String filename = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename, true))) {
            writer.write(String.format("%s %s %s %s %d %c%n", lastName, firstName, middleName, birthDate, phoneNumber, gender));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}