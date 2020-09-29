import java.util.Map;

public class DictionaryCommandline {
    public DictionaryManagement dictionaryManagement = new DictionaryManagement();

    public static void main(String[] args) {
        DictionaryCommandline dictionaryCommandline = new DictionaryCommandline();
        dictionaryCommandline.dictionaryBasic();
    }

    public void showAllWords() {
        for (Map.Entry e : dictionaryManagement.dictionary.database.entrySet()) {
            System.out.println(e.getKey() + " " + e.getValue());
        }
    }

    public void dictionaryBasic() {
        dictionaryManagement.insertFromCommandline();
        showAllWords();
    }
}
