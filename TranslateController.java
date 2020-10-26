package sample;

import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ResourceBundle;

public class TranslateController implements Initializable {
    @FXML
    TextArea textSearch;
    @FXML
    TextArea textShow;

    private Language language = new Language();
    @FXML
    public ComboBox<String> comboBoxSearch;
    @FXML
    public ComboBox<String> comboBoxShow;

    ObservableList<String> list = FXCollections.observableArrayList(language.listLanguage);

    private static final String VOICENAME = "kevin16";
    private VoiceManager vm = VoiceManager.getInstance();
    private Voice voice = vm.getVoice(VOICENAME);

    private String fromLang = new String();
    private String toLang = new String();

    public String translate(String langFrom, String langTo, String text) throws IOException {
        String urlStr = "https://script.google.com/macros/s/AKfycbxh9AJ0VmB37q-ELDPAE4hz88VXO2z3eSyGYfjwQPetZO2IFP1z/exec" +
                "?q=" + URLEncoder.encode(text, "UTF-8") +
                "&target=" + langTo +
                "&source=" + langFrom;
        URL url = new URL(urlStr);
        StringBuilder response = new StringBuilder();
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        return response.toString();
    }

    public void goBack(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    public void clearSearch(ActionEvent e) {
        textSearch.clear();
    }

    public void clearShow(ActionEvent e) {
        textShow.clear();
    }

    public void talkSearch(ActionEvent actionEvent) {
        String word = textSearch.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void talkShow(ActionEvent actionEvent) {
        String word = textShow.getText();
        voice.allocate();
        try {
            voice.speak(word);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        comboBoxSearch.setItems(list);
        comboBoxShow.setItems(list);
    }

    public void translateSubmit(ActionEvent e) throws Exception {
        for (int i = 0; i < language.listLanguage.size(); i++) {
            if (language.listLanguage.get(i).equals(comboBoxSearch.getValue())) {
                fromLang = language.codelanguageArray[i];
            }
            if (language.listLanguage.get(i).equals(comboBoxShow.getValue())) {
                toLang = language.codelanguageArray[i];
            }
        }

        String text = textSearch.getText();
        String[] temp = text.split("\n");
        for (String s : temp) {
            textShow.appendText(translate(fromLang, toLang, s) + "\n");
        }
    }
}
