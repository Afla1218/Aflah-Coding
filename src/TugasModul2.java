import java.util.Scanner;
import java.util.InputMismatchException;
import java.util.HashMap;
import java.util.ArrayList;

class Student {
    public String nama;
    public String fakultas;
    public String programStudi;
    public String BookID;

    public Student(String nama, String fakultas, String programStudi) {
        this.nama = nama;
        this.fakultas = fakultas;
        this.programStudi = programStudi;
    }

    public void logout() {
        System.out.println("Logging out...");
    }

    public void displayBooks(HashMap<String, String[]> bookList) {
        System.out.println(" Daftar Buku ");
        System.out.printf("|| %-5s || %-30s || %-20s || %-5s ||\n", "ID", "Judul", "Penulis", "Stok");
        for (String bookID : bookList.keySet()) {
            String[] book = bookList.get(bookID);
            System.out.printf("|| %-5s || %-30s || %-20s || %-5s ||\n", bookID, book[0], book[1], book[2]);
        }

    }

    public void displayBorrowedBook(HashMap<String, String[]> bookList) {
        System.out.println(" Buku yang Dipinjam ");
        System.out.printf("|| %-5s || %-30s || %-20s ||\n", "ID", "Judul", "Penulis");
        String[] book = bookList.get(BookID);
        if (book != null) {
            System.out.printf("|| %-5s || %-30s || %-20s ||\n", BookID, book[0], book[1]);
        }
    }
}

class Admin {
    private static final String adminUsernama = "afllah";
    private static final String password = "admin";
    boolean studentAddPermission;
    ArrayList<String[]> userStudentList;

    public Admin(boolean studentAddPermission) {
        this.studentAddPermission = studentAddPermission;
        this.userStudentList = new ArrayList<>();
        userStudentList.add(new String[]{"202310370311198", "Afllah Abdi Pratomo", "Teknik", "Informatika"});
    }

    public void addStudent(String nama, String nim, String fakultas, String programStudi) {
        for (String[] student : userStudentList) {
            if (student[0].equals(nim)) {
                System.out.println("Mahasiswa dengan NIM tersebut sudah ada dalam sistem.");
                return;
            }
        }
        userStudentList.add(new String[]{nim, nama, fakultas, programStudi});
        System.out.println("Mahasiswa berhasil ditambahkan.");
    }

    public void displayStudents() {
        System.out.println(" Daftar Mahasiswa ");
        for (String[] student : userStudentList) {
            System.out.println("NIM: " + student[0]);
            System.out.println("Nama: " + student[1]);
            System.out.println("Fakultas: " + student[2]);
            System.out.println("Program Studi: " + student[3]);
            System.out.println();
        }
    }

    public static boolean login(String inputUsernama, String InputPass) {
        return inputUsernama.equals(adminUsernama) && InputPass.equals(password);
    }
}

public class TugasModul2 {
    HashMap<String, String[]> bookList = new HashMap<>();
    Admin admin;
    Student student;
    Scanner scanner = new Scanner(System.in);

    public TugasModul2() {
        bookList.put("321", new String[]{"Sains", "Gita Einstein", "2"});
        bookList.put("123", new String[]{"Biologi", "Rizki Newton", "1"});
    }

    void menu() {
        System.out.println(" Menu Login :");
        System.out.println("1. Masuk sebagai Mahasiswa");
        System.out.println("2. Masuk sebagai Admin");
        System.out.println("3. Keluar");
    }

    void inputNIM() {
        System.out.print("Masukkan NIM Anda: ");
        String inputNIM = scanner.next();
        checkNIM(inputNIM);
    }

    void checkNIM(String nim) {
        boolean found = false;
        for (String[] user : admin.userStudentList) {
            if (user[0].equals(nim)) {
                found = true;
                System.out.println("Login Mahasiswa Berhasil. Selamat datang, " + user[1] + "!");
                student = new Student(user[1], user[2], user[3]);
                student.displayBooks(bookList);
                menuStudent();
                return;
            }
        }
        if (!found)
            System.out.println("Mahasiswa Tidak Terdaftar.");
        menu();
    }

    void menuAdmin() {
        System.out.println(" Menu Admin ");
        System.out.println("1. Tambah Mahasiswa");
        System.out.println("2. Tampilkan Daftar Mahasiswa");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi (1-3): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addStudent();
                break;
            case 2:
                admin.displayStudents();
                menuAdmin();
                break;
            case 3:
                menu();
                break;
            default:
                System.out.println("Pilihan Tidak Valid.");
        }
    }

    void addStudent() {
        System.out.println(" Menambahkan Mahasiswa ");
        System.out.print("Masukkan Nama Mahasiswa: ");
        scanner.nextLine();
        String nama = scanner.nextLine();
        System.out.print("Masukkan NIM Mahasiswa: ");
        String nim = scanner.next();
        System.out.print("Masukkan Fakultas Mahasiswa: ");
        String fakultas = scanner.next();
        System.out.print("Masukkan Program Studi Mahasiswa: ");
        String programStudi = scanner.next();

        admin.addStudent(nama, nim, fakultas, programStudi);
        menuAdmin();
    }

    void borrowBook() {
        System.out.println(" Meminjam Buku ");
        System.out.print("Masukkan ID Buku yang ingin dipinjam: ");
        String bookID = scanner.next();

        String[] book = bookList.get(bookID);
        if (book != null) {
            int stock = Integer.parseInt(book[2]);
            if (stock > 0) {
                System.out.println("Buku '" + book[0] + "' berhasil dipinjam.");
                stock--;
                book[2] = String.valueOf(stock);
                student.BookID = bookID;
            } else {
                System.out.println("Maaf, stok buku tidak mencukupi.");
            }
        } else {
            System.out.println("Buku dengan ID tersebut tidak ditemukan.");
        }

        menuStudent();
    }

    void returnBook() {
        System.out.println(" Melihat Buku yang Dipinjam ");
        student.displayBorrowedBook(bookList);
        menuStudent();
    }

    void menuStudent() {
        System.out.println(" Menu Mahasiswa ");
        System.out.println("1. Pinjam Buku");
        System.out.println("2. Lihat Buku yang Dipinjam");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi (1-3): ");
        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                borrowBook();
                break;
            case 2:
                returnBook();
                break;
            case 3:
                student.logout();
                menu();
                break;
            default:
                System.out.println("Pilihan Tidak TersediaS");
                menuStudent();
        }
    }

    void logout() {
        System.out.println("Terima kasih telah menggunakan program. Sampai jumpa!");
        scanner.close();
    }

    public static void main(String[] args) {
        TugasModul2 main = new TugasModul2();
        boolean studentAddPermission = true;

        main.admin = new Admin(studentAddPermission);

        main.menu();

        while (true) {
            System.out.print("Pilih opsi (1-3): ");
            try {
                int opsi = main.scanner.nextInt();

                switch (opsi) {
                    case 1:
                        System.out.println("Masuk sebagai Mahasiswa:");
                        main.inputNIM();
                        break;

                    case 2:
                        System.out.println("Masuk sebagai Admin:");
                        System.out.print("Masukkan usernama: ");
                        String inputUsernama = main.scanner.next();
                        System.out.print("Masukkan password: ");
                        String InputPass = main.scanner.next();

                        if (Admin.login(inputUsernama, InputPass)) {
                            main.menuAdmin();
                        } else {
                            System.out.println("Admin Tidak Ditemukan.");
                            main.menu();
                        }
                        break;

                    case 3:
                        main.logout();
                        return;

                    default:
                        System.out.println("Pilihan Tidak Valid.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Masukkan harus berupa bilangan bulat.");
                main.scanner.next();
            }
        }
    }
}
