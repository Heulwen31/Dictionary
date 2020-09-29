import java.util.Scanner;

public class DictionaryManagement {
    public Dictionary dictionary = new Dictionary();

    public void insertFromCommandline() {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        Scanner sc = new Scanner(System.in);
        for (int i = 0; i < n; i++) {
            String English = sc.nextLine();
            String Vietnamese = sc.nextLine();
            dictionary.database.put(English, Vietnamese);
        }
        scanner.close();
    }
}