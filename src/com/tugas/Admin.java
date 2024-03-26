package com.tugas;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.UUID;

public class Admin extends User {
  Scanner Input = new Scanner(System.in);
  ArrayList<Student> userStudent = new ArrayList<>();
  ArrayList<Buku> bookList = new ArrayList<>(); 

  Main main = new Main();

  public Admin(String nama,String NIM,String fakulatas, String prodi){
    super(nama, NIM, fakulatas, prodi);
  }


  public void addStudent(){
    String NIM;
    
    System.out.print("Nama     : ");
    String nama = Input.next();
    do {
        System.out.print("NIM      : ");
        NIM = Input.next();
        if(NIM.length()!=15)
          System.out.println("NIM harus 15 digit !!!");
        }while(NIM.length()!=15);
    System.out.print("Fakultas : ");
    String fakultas = Input.next();
    System.out.print("Prodi    : ");
    String prodi = Input.next();

    Student mahasiswa = new Student(nama, NIM, fakultas, prodi);
    Main.userStudent.add(mahasiswa);
  
    System.out.println("Mahasiswa berhasil ditambahkan");

  }

  public void displayStudent(){
        System.out.println("===== Daftar Mahasiswa =====");
        for (Student mahasiswa : Main.getUserStudent()) {
            System.out.println("Nama     : " + mahasiswa.getNama());
            System.out.println("NIM      : " + mahasiswa.getNIM());
            System.out.println("Fakultas : " + mahasiswa.getFakultas());
            System.out.println("Prodi    : " + mahasiswa.getProdi());
            System.out.println("---------------------------------");
        }
       
        main.menuAdmin();
    }

    public void inputBook() {
      Buku buku=null;

      System.out.println("===== Tambah Buku =====");
      System.out.println("Pilih kategori buku:");
      System.out.println("1. Story Book");
      System.out.println("2. History Book");
      System.out.println("3. Text Book");
      System.out.print("Pilih kategori (1-3): ");
      int pilihan = Input.nextInt();
  
      Input.nextLine(); 
      System.out.print("Judul Buku: ");
      String judul = Input.nextLine();
      System.out.print("Penulis: ");
      String author = Input.nextLine();
      System.out.print("Stok Buku: ");
      int stok = Input.nextInt();

      switch (pilihan) {
        case 1:
            buku = new StoryBook(generateId(), judul, author, "Cerita", stok,0);
            break;
        case 2:
            buku = new HistoryBook(generateId(), judul, author, "Sejarah", stok,0);
            break;
        case 3:
            buku = new TextBook(generateId(), judul, author, "Novel", stok,0);
            break;
        default:
            System.out.println("Pilihan Tidak Valid!!\nPilih Nomor (1-3) !!!");
            return;
      }
      
      Main.bookList.add(buku);
  
      System.out.println("Buku berhasil ditambahkan ke perpustakaan.");
  }

    public String generateId(){
      return UUID.randomUUID().toString();
    }
    

    void displayBooks(){
      System.out.println("===== Daftar Buku =====");
      for (Buku buku : Main.bookList) {
          System.out.println("ID Buku    : " + buku.getId_buku());
          System.out.println("Judul Buku : " + buku.getJudul());
          System.out.println("Author     : " + buku.getAuthor());
          System.out.println("Category   : " + buku.getCategory());
          System.out.println("Stok Buku  : " + buku.getStockBuku());
          System.out.println("---------------------------------");
      }
  }

}
