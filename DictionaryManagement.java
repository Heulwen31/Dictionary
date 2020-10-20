package sample;

import java.io.*;

public class DictionaryManagement extends Dictionary {
    private static final String DATA_FILE_PATH = "E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";

    public void insertFromCommandline() {
        int n = scanner.nextInt();
        for (int i = 0; i < n; i++) {
            String English = scanner.next();
            String Vietnamese = scanner.next();
            Word a = new Word(English, Vietnamese);
            database.add(a);
        }
        scanner.close();
    }

    public void insertFromFile() {
        BufferedReader reader;
        try {
            reader =
                    new BufferedReader(
                            new FileReader("C:\\Users\\admin\\Downloads\\Dictionary\\Dictionary\\src\\E_V.txt"));
            String line = reader.readLine();
            while (line != null) {
                String[] temp = line.split(SPLITTING_CHARACTERS);
                Word Temp = new Word(temp[0], SPLITTING_CHARACTERS + temp[1]);
                database.add(Temp);
                line = reader.readLine();
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Word dictionaryLookup(String a) {
        for (Word e : database) {
            if (e.word_target.equals(a)) {
                return e;
            }
        }
        return null;
    }

    public void removeWord() {
        String a = scanner.next();
        database.removeIf(e -> e.word_target.equals(a));
    }

    public void addWord() {
        String a = scanner.next();
        String b = scanner.next();
        Word e = new Word(a, b);
        database.add(e);
    }

    public void changeWord() {
        String a = scanner.next();
        for (Word e : database) {
            if (a.equals(e.word_target)) {
                e.word_target = scanner.next();
                e.word_explain = scanner.nextLine();
            }
        }
    }

    public void dictionaryExportToFile() {
        BufferedWriter bw = null;
        FileWriter fw = null;
        try {
            String enLine = "\n";
            String data = scanner.nextLine();

            File file = new File("C:\\Users\\namtr\\IdeaProjects\\Dictionary\\src\\sample\\dictionaries.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(enLine);
            bw.write(data);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bw != null) bw.close();
                if (fw != null) fw.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
