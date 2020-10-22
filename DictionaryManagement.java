package sample;

import java.io.*;

public class DictionaryManagement extends Dictionary {

    private static final String DATA_FILE_PATH = "C:\\Users\\namtr\\IdeaProjects\\Dictionary\\src\\E_V.txt";
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


//    public void dictionaryExportToFile() {
//        BufferedWriter bw = null;
//        FileWriter fw = null;
//        try {
//            String enLine = "\n";
//            String data = scanner.nextLine();
//
//            File file = new File(DATA_FILE_PATH);
//
//            if (!file.exists()) {
//                file.createNewFile();
//            }
//
//            fw = new FileWriter(file.getAbsoluteFile(), true);
//            bw = new BufferedWriter(fw);
//            bw.write(enLine);
//            bw.write(data);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bw != null) bw.close();
//                if (fw != null) fw.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
}
