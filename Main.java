package main;
import javafx.application.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.stage.*;
public class Main extends Application {
    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("form.fxml"));
        primaryStage.setTitle("Простая смета");
        primaryStage.setScene(new Scene(root, 480, 475));
        primaryStage.show();
    }
    public static void main(String[] args) {
        launch(args);
    }
}
