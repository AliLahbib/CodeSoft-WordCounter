module com.example.task2_wordcounter {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.task2_wordcounter to javafx.fxml;
    exports com.example.task2_wordcounter;
}