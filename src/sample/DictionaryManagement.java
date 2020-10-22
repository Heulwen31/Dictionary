package sample;

import java.io.*;

public class DictionaryManagement extends Dictionary {

    private static final String DATA_FILE_PATH = "C:\\Users\\Rukitori\\Documents\\IntelliJ Project\\Dictionary\\src\\E_V.txt";
    private static final String SPLITTING_CHARACTERS = "<html>";

    public void insertFromFile() {
        BufferedReader reader;
        try {
            reader =
                    new BufferedReader(
                            new FileReader(DATA_FILE_PATH));
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
}
