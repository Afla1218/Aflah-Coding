package com.tugas;

import java.util.*;

public class Main {

        Scanner Input = new Scanner(System.in);
        static ArrayList<Student> userStudent= new ArrayList<>();
        static ArrayList<Buku> bookList= new ArrayList<>();    
        public static ArrayList<Student> getUserStudent(){
            return userStudent;
        }
        public static Admin admin = new Admin(null, null, null, null);
        static Main main = new Main();
        public static void main(String[] args){
           
            
            userStudent.add(new Student("Afllah Abdi Pratomo", "202310370311198", "Teknik","Informatika"));

            bookList.add(new Buku("388c-e681-9152", "Pemograman Java OOP", "Bangda", "Novel", 10,5));
            
           
            main.Menu();
            admin.addStudent();
            admin.inputBook();
    }
    
    public void Menu(){
        int pilihan;
        do {
            System.out.println("====== Library System ======");
            System.out.println("1. Login Sebagai Mahasiswa");
            System.out.println("2. Login Sebagai Admin");
            System.out.println("3. Exit");
            System.out.print("Pilih Opsi (1-3): ");
            pilihan= Input.nextInt();

            switch (pilihan) {
                case 1:
                    loginStudent();
                    break;
                case 2:
                    loginAdmin();
                    break;
                case 3:
                    exit();
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        }while (pilihan !=3);

    }


    void loginStudent() {

        System.out.print("Masukan NIM : ");
        String NIM= Input.next();
            if (checkNim(NIM)){
            menuStudent();
            
            }else {
            System.out.println("User Not Found!! ");
        }
    }    
 
    boolean checkNim(String NIM){
        for (Student student : userStudent) {
            if (student.getNIM().equals(NIM)) {
                return true;
            }
        }    
        return false; 
    }

    public void menuStudent(){
        Student student = new Student(null, null, null, null);
        int pilihan;

        do {
            System.out.println("==== Student Menu ====");
            System.out.println("1. Tampilkan Buku yang Dipinjam");
            System.out.println("2. Pinjam Buku");
            System.out.println("3. Kembalikan Buku");
            System.out.println("4. Pinjam Buku atau Keluar");
            System.out.print("Pilih opsi (1-4): ");
            pilihan = Input.nextInt();

            switch (pilihan) {
                case 1:
                    student.showBorrowedBooks();
                    break;
                case 2:
                    student.displayBooks();
                    break;
                case 3:
                    student.returnBooks();
                    break;
                case 4:
                    student.logOut();
                    return;
                default:
                    System.out.println("Pilihan tidak valid.");
            }
        } while (pilihan != 4);
    }
    

    void loginAdmin() {

        System.out.print("Masukan Username (admin) : ");
        String username = Input.next();
        Input.nextLine();
        System.out.print("Masukan Password (admin) : ");
        String pw = Input.next();

        if (username.equals("afla") && pw.equals("admin")){
            menuAdmin();
        }else {
            System.out.println("Admin User Not Found!!");
        }

    }

    public void menuAdmin(){
        
        int pilihan;
        do {
            System.out.println("===== Dashboard Admin =====");
            System.out.println("1. Tambah Mahasiswa");
            System.out.println("2. Tambah Buku");
            System.out.println("3. Tampilkan Daftar Mahasiswa");
            System.out.println("4. Daftar Buku Tersedia");
            System.out.println("5. Logout");
            System.out.print("Pilih Opsi (1-5): ");
            pilihan = Input.nextInt();

            switch (pilihan) {
                case 1:
                    admin.addStudent();
                    break;
                case 2:
                    admin.inputBook();
                    break;
                case 3:
                    admin.displayStudent();
                    break;
                case 4:
                    admin.displayBooks();
                    break;
                case 5:
                    System.out.println("System Logout...");
                    Menu();
                    break;
                default:
                    System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            }
        } while (pilihan != 3);
        System.out.println("");
     }

    static void exit(){
        System.out.println("Terima Kasih!!!");
        System.exit(0);
    }

}
