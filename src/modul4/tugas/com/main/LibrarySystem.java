package modul4.tugas.com.main;

import modul4.tugas.books.*;
import modul4.tugas.data.Admin;
import modul4.tugas.data.Student;
import modul4.tugas.data.User;

import java.util.ArrayList;
import java.util.Scanner;

public class LibrarySystem {
    public static Book daftarBuku[] = new Book[100];
    public static ArrayList<Student> studentList = new ArrayList<>();
    public static int i = 0;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        daftarBuku[i++] = new StoryBook("SB32F", "Kisah Nabi", 10, "Story", "Al Katiri");
        daftarBuku[i++] = new HistoryBook("HB324", "WW2", 2, "History", "Putin");
        daftarBuku[i++] = new TextBook("TB323", "Puisi", 4, "Text", "Imelda");
        studentList.add(0, new Student("202310370311198", "Afllah Abdi Pratomo", "Teknik","Informatika"));

        boolean isRunning = true;
        while (isRunning) {
            System.out.println("===== Library System =====");
            System.out.println("1. Login sebagai Mahasiswa");
            System.out.println("2. Login sebagai Admin");
            System.out.println("3. Keluar");
            System.out.print("Pilih antara (1-3): ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Masukkan NIM : ");
                    String nimStudent = scanner.next();
                    scanner.nextLine();
                    while(true){
                        if (nimStudent.length() != 15 ) {
                            System.out.print("Nim Harus 15 Digit!!!\n");
                            System.out.print("Masukkan NIM : ");
                            nimStudent = scanner.nextLine();

                        } else if (checkNim(nimStudent)){
                            Student student = new Student(nimStudent);
                            student.login();
                            break;
                        } else {
                            System.out.println("Nim tidak terdaftar!");
                            break;
                        }
                    }
                    break;
                case 2:
                    Admin admin = new Admin();
                    admin.login();
                    break;
                case 3:
                    System.out.println("Terima kasih semoga puas dengan pelayanan kami");
                    isRunning = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
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
