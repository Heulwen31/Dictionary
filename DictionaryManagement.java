package sample;

import java.io.*;

public class DictionaryManagement extends Dictionary {

    private static final String DATA_FILE_PATH = "C:\\Users\\admin\\IdeaProjects\\TuDien\\src\\E_V.txt";
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

    public void deleteWord(String word) throws IOException {
        File inFile = new File(DATA_FILE_PATH);
        if (!inFile.isFile()) {
            return;
        }
        File tempFile = new File(inFile.getAbsoluteFile() + ".tmp");
        BufferedReader br = new BufferedReader(new FileReader(DATA_FILE_PATH));
        PrintWriter pw = new PrintWriter(new FileWriter(tempFile));

        String line;

        while ((line = br.readLine()) != null) {
            String[] temp = line.split(SPLITTING_CHARACTERS);
            if (!temp[0].equals(word)) {
                pw.println(line);
                pw.flush();
            }
        }
        pw.close();
        br.close();

        if (!inFile.delete()) {
            System.out.println("Could not delete file");
            return;
        }
        if (!tempFile.renameTo(inFile)) {
            System.out.println("Could not delete file");
        }
    }

    public void dictionaryExportToFile(String word, String mean, String typeWord, String spelling) {
        BufferedWriter bw = null;
        FileWriter fw = null;
        String word_add = word + "<html><i>"
                + spelling + "</i><br/><ul><li><b><i> "
                + typeWord + "</i></b><ul><li><font color='#cc0000'><b> "
                + mean + "</b></font></li></ul></li></ul></html>\n";
        try {
            File file = new File(DATA_FILE_PATH);
            if (!file.exists()) {
                file.createNewFile();
            }
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);
            bw.write(word_add);
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
