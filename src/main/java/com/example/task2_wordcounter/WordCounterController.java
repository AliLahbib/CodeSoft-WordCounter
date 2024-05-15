package com.example.task2_wordcounter;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

public class WordCounterController {
    @FXML
    private TextArea inputTextArea;
    @FXML
    private Label wordCountLabel;
    @FXML
    private Label wordFrequencyLabel;

    @FXML
    public void chooseFile() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showOpenDialog(new Stage());

        if (file != null) {
            try {
                String content = new String(Files.readAllBytes(file.toPath()));
                inputTextArea.setText(content);
            } catch (IOException e) {
                inputTextArea.setText("Failed to load file.");
            }
        }
    }

    @FXML
    public void countWords() {
        String text = inputTextArea.getText().trim();
        String[] words = text.split("[\\s\\p{Punct}]+");

        int wordCount = 0;
        Map<String, Integer> wordFrequency = new HashMap<>();

        for (String word : words) {
            word = word.toLowerCase();
            wordFrequency.put(word, wordFrequency.getOrDefault(word, 0) + 1);
            wordCount++;
        }

        wordCountLabel.setText("Total words: " + wordCount);

        StringBuilder frequencyText = new StringBuilder("Word frequency statistics:\n");
        for (Map.Entry<String, Integer> entry : wordFrequency.entrySet()) {
            frequencyText.append(entry.getKey()).append(": ").append(entry.getValue()).append("\n");
        }
        wordFrequencyLabel.setText(frequencyText.toString());
    }
}
