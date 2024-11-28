import java.util.Scanner;
import java.text.SimpleDateFormat;
import java.util.Date;

public class LoginProgram {
    
    private static final String USERNAME = "OLIVIA MART";
    private static final String PASSWORD = "AL-QURAN";
    private static final String CAPTCHA = "SI23";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean loggedIn = false;
        System.out.println("Silahkan Login Terlebih Dahulu");
        while (!loggedIn) {

            System.out.print("Masukkan Username: ");
            String usernameInput = scanner.nextLine();
            
            System.out.print("Masukkan Password: ");
            String passwordInput = scanner.nextLine(); 
            
            System.out.print("Masukkan Captcha (SI23): ");
            String captchaInput = scanner.nextLine(); 
            
            if (isInputValid(usernameInput, passwordInput, captchaInput)) {
                if (isLoginSuccessful(usernameInput, passwordInput, captchaInput)) {
                    loggedIn = true;
                    System.out.println("Login berhasil!");
                    System.out.println("---------------------------------------------------------------------------");
                    System.out.println("Selamat datang di Olivia Supermarket!");
                    System.out.println("Waktu saat ini: " + getCurrentDate());
                    System.out.println("---------------------------------------------------------------------------");
                    
                    // Memanggil metode dari kelas Main untuk menjalankan transaksi
                    Main.runTransaksi();
                } else {
                    System.out.println("Login gagal. Username or Password or Captcha Salah. Silakan coba lagi.");
                }
            } else {
                System.out.println("Input tidak valid. Pastikan semua field diisi.");
            }
        }

        scanner.close();
    }

    private static boolean isInputValid(String username, String password, String captcha) {
        return !username.isEmpty() && !password.isEmpty() && !captcha.isEmpty();
    }

    // Menggunakan method string yaitu equals() untuk perbandingan pada username dan password (artinya username dan password harus persis sama)
    // Menggunakan equalsIgnoreCase() untuk perbandingan yang mengabaikan huruf besar dan kecil pada captcha
    private static boolean isLoginSuccessful(String username, String password, String captcha) {
        return username.equals(USERNAME) && password.equals(PASSWORD) && captcha.equalsIgnoreCase(CAPTCHA);
    }
    
    // method date (menampilkan tanggal dan waktu sekarang saat login)
    private static String getCurrentDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    public static String getCAPTCHA() {
        return CAPTCHA;
    }
}