package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TranslateController {
    @FXML
    TextArea textSearch;
    @FXML
    TextArea textShow;

    public void goBack(ActionEvent e) throws Exception {
        Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent sampleParent = loader.load();
        Scene scene = new Scene(sampleParent);
        scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
        stage.setScene(scene);
    }

    public String translate(String langFrom, String langTo, String text) throws IOException {
        // INSERT YOUR URL HERE
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

    public void translateSubmit(ActionEvent e) throws Exception {
        String text = textSearch.getText();
        String[] temp = text.split("\n");
        for (String s : temp) {
            String fromLang = "en";
            String toLang = "vi";
            textShow.appendText(translate(fromLang, toLang, s) + "\n");
        }
    }
}
