package com.tugas;

import java.util.*;

public class Student extends User {
    Scanner Input = new Scanner(System.in);
    private ArrayList<Buku> borrowedBooks = new ArrayList<>();

    public Student(String nama, String NIM, String fakultas, String prodi) {
        super(nama, NIM, fakultas, prodi);
    }

    public void displayInfo() {
        System.out.println("===== Data Diri Mahasiswa =====");
        System.out.println("Nama: " + getNama());
        System.out.println("NIM: " + getNIM());
        System.out.println("Fakultas: " + getFakultas());
        System.out.println("Prodi: " + getProdi());
    }

    public void showBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Belum ada buku yang dipinjam.");
        } else {
            System.out.println("===== Daftar Buku Dipinjam =====");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                Buku book = borrowedBooks.get(i);
                System.out.println("ID Buku    : " + book.getId_buku());
                System.out.println("Judul Buku : " + book.getJudul());
                System.out.println("Author     : " + book.getAuthor());
                System.out.println("Category   : " + book.getCategory());
                System.out.println("Durasi     : " + book.getDuration() + " hari");
                System.out.println("---------------------------------");
            }
        }
    }
    
  

  public void logOut() {
    Main main = new Main();
    if (borrowedBooks.isEmpty()) {
        System.out.println("Anda telah berhasil logout.");
    } else {
        System.out.println("Apakah kamu yakin untuk meminjam semua buku tersebut?");
        System.out.println("Input Y (iya) atau T (tidak):");

        String choice = Input.nextLine();
        if (choice.equalsIgnoreCase("Y")) {
            System.out.println("Peminjaman buku berhasil dilakukan.");
        } else {
            System.out.println("Logout dibatalkan.");
            main.menuStudent();
        }
    }
}


    @Override
    public void displayBooks() {
        super.displayBooks();
        System.out.println("99. Kembali ke Menu");
        System.out.print("Pilih buku yang ingin dipinjam (input ID buku atau 99 untuk kembali): ");
        String bookId = Input.next();
        if (bookId.equals("99")) {
            return;
        }
    
        Buku bookToBorrow = null;
        for (Buku buku : Main.bookList) {
            if (buku.getId_buku().equals(bookId)) {
                bookToBorrow = buku;
                break;
            }
        }
    
        if (bookToBorrow == null) {
            System.out.println("Buku tidak ditemukan.");
        } else if (bookToBorrow.getStockBuku() <= 0) {
            System.out.println("Stok buku habis!");
        } else {
            System.out.print("Masukkan durasi peminjaman (maksimal 14 hari): ");
            int duration = Input.nextInt();
            Input.nextLine(); 
            if (duration > 14) {
                System.out.println("Anda hanya dapat meminjam buku maksimal selama 14 hari.");
            } else {
                bookToBorrow.setStockBuku(bookToBorrow.getStockBuku() - 1);
                borrowedBooks.add(bookToBorrow);
                System.out.println("Peminjaman buku berhasil dilakukan.");
            }
        }
    }

    public void returnBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println("Anda belum meminjam buku apapun.");
        } else {
            System.out.println("===== Buku yang Dipinjam =====");
            for (int i = 0; i < borrowedBooks.size(); i++) {
                Buku book = borrowedBooks.get(i);
                System.out.println("ID Buku    : " + book.getId_buku());
                System.out.println("Judul Buku : " + book.getJudul());
                System.out.println("Author     : " + book.getAuthor());
                System.out.println("Category   : " + book.getCategory());
                System.out.println("Durasi     : " + book.getDuration() + " hari");
                System.out.println("---------------------------------");
            }
            System.out.print("Apakah kamu yakin untuk mengembalikan semua buku tersebut? (Y/T): ");
            String choice = Input.next();
            if (choice.equalsIgnoreCase("Y")) {
                for (Buku book : borrowedBooks) {
                    book.setStockBuku(book.getStockBuku() + 1); 
                }
                borrowedBooks.clear();
                System.out.println("Pengembalian buku berhasil dilakukan.");
            } else {
                System.out.println("Pengembalian buku dibatalkan.");
            }
        }
    }
    
   
  }
  


 
