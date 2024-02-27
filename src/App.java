import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String NIM = "202310370311198";
        String username = "Afllah";
        String password = "Admin";

        while (true) {

            System.out.println("=== Menu Login ===:");
            System.out.println("1. Login Untuk Mahasiswa");
            System.out.println("2. Login Untuk Admin");
            System.out.println("3. Exit");

            System.out.print("Pilih opsi (1-3): ");
            int opsi = scan.nextInt();
            scan.nextLine();

            String inputNIM, inputUsername, inputPassword;

            switch (opsi) {
                case 1:
                    System.out.println("Login Untuk Mahasiswa:");
                    System.out.print("Masukkan NIM Anda: ");
                    inputNIM = scan.nextLine();


                    if (inputNIM.matches("\\d{15}") && inputNIM.equals(NIM)) {
                        System.out.println("Login Mahasiswa Berhasil");
                    } else {
                        System.out.println("NIM Tidak DiTemukan");
                    }
                    break;

                case 2:
                    System.out.println("Login Untuk Admin:");
                    System.out.print("Masukkan Username Admin: ");
                    inputUsername = scan.nextLine();
                    System.out.print("Masukkan Password Admin: ");
                    inputPassword = scan.nextLine();

                    if (inputUsername.equals(username) && inputPassword.equals(password)) {
                        System.out.println("Login Admin Berhasil");
                    } else {
                        System.out.println("User Admin Tidak Di Temukan");
                    }
                    break;

                case 3:
                    System.out.println("Exit.");
                    scan.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("INVALID");
            }
        }
    }
}
