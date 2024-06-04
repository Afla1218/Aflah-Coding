package org.example.tugasmodulke6.com.main;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.example.tugasmodulke6.books.*;
import org.example.tugasmodulke6.data.Admin;
import org.example.tugasmodulke6.data.Student;

import java.util.ArrayList;

public class LibrarySystem {
    public static ArrayList<Book> daftarBuku = new ArrayList<>();
    public static ArrayList<Student> studentList = new ArrayList<>();

    public static void startLibrarySystem(Stage stage) {
        daftarBuku.add(new StoryBook("1234", "Hitman", 17, "Story", "Botak"));
        daftarBuku.add(new HistoryBook("1324", "Laskar Matahari", 2, "History", "Goblin"));
        daftarBuku.add(new TextBook("2323", "Operasi Pemuda 24", 4, "Text", "Afla"));
        studentList.add(new Student("202310370311198", "Afllah Abdi Pratomo", "Teknik", "Informatika"));
        
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: lightblue");

        Scene scene = new Scene(root, 400, 300);

        root.setAlignment(Pos.CENTER);
        Label label = new Label("  Welcome Library System  ");
        Button studentLoginButton = new Button("Login sebagai Mahasiswa");
        studentLoginButton.setStyle("-fx-background-color: #7FFFD4");
        Button adminLoginButton = new Button("Login sebagai Admin");
        adminLoginButton.setStyle("-fx-background-color: #F5FFFA");
        Button exitButton = new Button("Keluar");
        exitButton.setStyle("-fx-background-color: #FF6347");

        studentLoginButton.setOnAction(event -> studentLogin(stage));
        adminLoginButton.setOnAction(event -> {
            try {
                new Admin().login(stage);
            } catch (Exception e) {
                showErrorDialog("Error", e.getMessage());
            }
        });
        exitButton.setOnAction(event -> stage.close());

        root.getChildren().addAll(label, studentLoginButton, adminLoginButton, exitButton);

        stage.setScene(scene);
        stage.setTitle("Library System");
        stage.show();
    }

    private static void studentLogin(Stage stage) {
        VBox root = new VBox(10);
        root.setStyle("-fx-background-color: lightblue");

        Scene scene = new Scene(root, 400, 300);

        Label label = new Label("Masukkan NIM : ");
        TextField nimField = new TextField();
        Button loginButton = new Button("Login");
        Button backButton = new Button("Kembali");

        loginButton.setOnAction(event -> {
            String nimStudent = nimField.getText();
            if (nimStudent.length() == 15 && checkNim(nimStudent)) {
                Student student = new Student(nimStudent);
                student.login(stage);
            } else {
                showErrorDialog("Error", "Nim tidak terdaftar atau tidak valid!");
            }
        });

        backButton.setOnAction(event -> startLibrarySystem(stage));

        root.getChildren().addAll(label, nimField, loginButton, backButton);

        stage.setScene(scene);
    }

    private static void showErrorDialog(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }

    public static boolean checkNim(String nim) {
        for (Student student : studentList) {
            if (student.getNim().equals(nim)) {
                return true;
            }
        }
        return false;
    }
}
