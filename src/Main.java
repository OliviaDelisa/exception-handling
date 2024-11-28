import java.util.InputMismatchException;
import java.util.Scanner;
import java.text.NumberFormat;

// Parent atau induk class Barang
class Barang {
    public String kodeBarang;
    public String namaBarang;
    public double hargaBarang;

    // Constructor Barang dengan parameter kode barang, nama barang dan harga
    public Barang(String kodeBarang, String namaBarang, double hargaBarang) {
        this.kodeBarang = kodeBarang;
        this.namaBarang = namaBarang;
        this.hargaBarang = hargaBarang;
    }
}

//kelas anak, transaksi adalah kelas anak atau subclass dari kelas barang, ini merupakan inheritance, sehingga kelas anak mewarisi atribut dari kelas barang
class Transaksi extends Barang {
    private String noFaktur; 
    private int jumlahBeli;  

    // Constructor transaksi
    public Transaksi(String kodeBarang, String namaBarang, double hargaBarang, String noFaktur, int jumlahBeli) {
        super(kodeBarang, namaBarang, hargaBarang); // Memanggil constructor parent class Barang
        this.noFaktur = noFaktur;
        this.jumlahBeli = jumlahBeli;
    }

    // Method untuk menghitung total harga transaksi
    public double hitungTotal() throws IllegalArgumentException {
        if (jumlahBeli <= 0) {
            throw new IllegalArgumentException("Jumlah beli harus lebih dari 0"); // Exception untuk validasi
        }
        return hargaBarang * jumlahBeli;
    }

    // Method untuk menampilkan detail transaksi
    public void tampilkanTransaksi(double total) {
        NumberFormat currencyFormat = NumberFormat.getCurrencyInstance(); 
        System.out.println("\n===== Detail Transaksi =====");
        System.out.println("No Faktur      : " + noFaktur);
        System.out.println("Kode Barang    : " + kodeBarang);
        System.out.println("Nama Barang    : " + namaBarang);
        System.out.println("Harga Barang   : " + currencyFormat.format(hargaBarang));
        System.out.println("Jumlah Beli    : " + jumlahBeli);
        System.out.println("Total Harga    : " + currencyFormat.format(total));
        System.out.println("------------------------------------------------------------------------");
        System.out.println("Kasir  : IBUK KOS");
    }
}

// Main class
public class Main {
    public static void runTransaksi() {
        Scanner scanner = new Scanner(System.in); 
        boolean continueProgram = true; 
        
        while (continueProgram) {
            try {
                //input transaksi
                System.out.print("Input No Faktur: ");
                String noFaktur = scanner.nextLine();

                System.out.print("Input Kode Barang: ");
                String kodeBarang = scanner.nextLine();

                System.out.print("Input Nama Barang: ");
                String namaBarang = scanner.nextLine();

               
                double hargaBarang = 0;
                while (true) {
                    System.out.print("Input Harga Barang: ");
                    try {
                        hargaBarang = scanner.nextDouble();
                        if (hargaBarang < 0) {
                            throw new IllegalArgumentException("Harga barang tidak boleh bernilai negatif.");
                        }
                        break; 
                         // Exception jika input tidak valid (bukan angka)
                    } catch (InputMismatchException e) { 
                        System.out.println("Error: Masukkan angka yang valid untuk harga."); 
                        scanner.next(); 
                    }
                }

            
                int jumlahBeli = 0;
                while (true) {
                    System.out.print("Input Jumlah Beli: ");
                    try {
                        jumlahBeli = scanner.nextInt();
                        if (jumlahBeli <= 0) {
                            throw new IllegalArgumentException("Jumlah beli harus lebih dari 0.");
                        }
                        break; 
                        // Exception jika input tidak valid (bukan angka)
                    } catch (InputMismatchException e) {
                        System.out.println("Error: Masukkan angka yang valid untuk jumlah beli.");
                        scanner.next();
                          // Exception jika jumlah beli tidak logis (MISALNYA NEGATIF)
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                }


                Transaksi transaksi = new Transaksi(kodeBarang, namaBarang, hargaBarang, noFaktur, jumlahBeli);

                // Menghitung total harga
                double total = transaksi.hitungTotal();

                // Menampilkan detail transaksi
                transaksi.tampilkanTransaksi(total);

            } catch (IllegalArgumentException e) {
                // Exception untuk validasi input jumlah beli atau harga
                System.out.println("Error: " + e.getMessage());
            } catch (Exception e) {
                // Exception untuk menangani kesalahan umum lainnya
                System.out.println("Terjadi kesalahan: " + e.getMessage());
            }

    
            System.out.print("\nApakah Anda ingin melanjutkan transaksi? (Y/N): ");
            scanner.nextLine(); 
            String pilihan = scanner.nextLine();
            // menggunakan equals ignore case untuk mengabaikan huruf besar dan huruf kecil antara yang di set dengan yang diinputkan
            if (!pilihan.equalsIgnoreCase("Y")) { 
                continueProgram = false; 
            }
        }

        // Menutup scanner setelah selesai
        scanner.close();
        System.out.println("Program selesai.");
    }
}
