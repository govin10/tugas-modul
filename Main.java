import java.util.Scanner;

public class Main {
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "123";
    private static final int MAHASISWA_NIM_LENGTH = 15;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Selamat datang di sistem login!");

        while (true) {
            System.out.println("Silakan pilih jenis user (admin/mahasiswa) atau ketik 'exit' untuk keluar:");
            String userType = scanner.nextLine();

            if (userType.equalsIgnoreCase("exit")) {
                break;
            }

            if (userType.equalsIgnoreCase("admin")) {
                adminLogin(scanner);
                break;
            } else if (userType.equalsIgnoreCase("mahasiswa")) {
                mahasiswaLogin(scanner);
                break;
            } else {
                System.out.println("Jenis user tidak valid. Silakan coba lagi.");
            }
        }

        System.out.println("Terima kasih telah menggunakan sistem login library.");

    }

    private static void adminLogin(Scanner scanner) {
        System.out.println("Masukkan username admin:");
        String username = scanner.nextLine();
        System.out.println("Masukkan password admin:");
        String password = scanner.nextLine();

        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            System.out.println("Login admin berhasil!");
        } else {
            System.out.println("Username atau password admin salah.");
        }
    }

    private static void mahasiswaLogin(Scanner scanner) {
        System.out.println("Masukkan NIM (harus 15 karakter):");
        String nim = scanner.nextLine();

        if (nim.length() == MAHASISWA_NIM_LENGTH) {
            System.out.println("Login mahasiswa berhasil!");
        } else {
            System.out.println("Panjang NIM tidak valid.");
        }
    }
}