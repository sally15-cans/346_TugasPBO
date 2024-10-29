import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Scanner;

public class SistemPerpustakaan {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        

        System.out.print("Masukkan nama perpustakaan: ");
        String namaPerpus = scanner.nextLine();
        Perpustakaan perpus = new Perpustakaan(namaPerpus);
        
        int pilihan;
        do {
            System.out.println("\n=== MENU PERPUSTAKAAN ===");
            System.out.println("1. Tambah Buku");
            System.out.println("2. Tambah Anggota");
            System.out.println("3. Pinjam Buku");
            System.out.println("4. Kembalikan Buku");
            System.out.println("5. Tampilkan Status Perpustakaan");
            System.out.println("0. Keluar");
            System.out.print("Pilihan Anda: ");
            pilihan = scanner.nextInt();
            scanner.nextLine(); 
            
            switch (pilihan) {
                case 1 -> tambahBuku(scanner, perpus);
                case 2 -> tambahAnggota(scanner, perpus);
                case 3 -> pinjamBuku(scanner, perpus);
                case 4 -> kembalikanBuku(scanner, perpus);
                case 5 -> perpus.tampilkanStatusPerpustakaan();
                case 0 -> System.out.println("Terima kasih telah menggunakan sistem perpustakaan!");
                default -> System.out.println("Pilihan tidak valid!");
            }
        } while (pilihan != 0);
        
        scanner.close();
    }
    
    private static void tambahBuku(Scanner scanner, Perpustakaan perpus) {
        System.out.println("\n=== TAMBAH BUKU ===");
        System.out.print("Masukkan ID Buku: ");
        String idBuku = scanner.nextLine();
        System.out.print("Masukkan Judul: ");
        String judul = scanner.nextLine();
        System.out.print("Masukkan Pengarang: ");
        String pengarang = scanner.nextLine();
        System.out.print("Masukkan Stok: ");
        int stok = scanner.nextInt();
        scanner.nextLine(); 
        
        System.out.println("Jenis Buku:");
        System.out.println("1. Fiksi");
        System.out.println("2. Non-Fiksi");
        System.out.print("Pilihan: ");
        int jenisBuku = scanner.nextInt();
        scanner.nextLine(); 
        
        if (jenisBuku == 1) {
            System.out.print("Masukkan Genre: ");
            String genre = scanner.nextLine();
            BukuFiksi bukuFiksi = new BukuFiksi(idBuku, judul, pengarang, genre, stok);
            perpus.tambahBuku(bukuFiksi);
        } else if (jenisBuku == 2) {
            System.out.print("Masukkan Kategori: ");
            String kategori = scanner.nextLine();
            BukuNonFiksi bukuNonFiksi = new BukuNonFiksi(idBuku, judul, pengarang, kategori, stok);
            perpus.tambahBuku(bukuNonFiksi);
        }
        
        System.out.println("Buku berhasil ditambahkan!");
    }
    
    private static void tambahAnggota(Scanner scanner, Perpustakaan perpus) {
        System.out.println("\n=== TAMBAH ANGGOTA ===");
        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine();
        System.out.print("Masukkan Nama: ");
        String nama = scanner.nextLine();
        
        Anggota anggota = new Anggota(idAnggota, nama);
        perpus.tambahAnggota(anggota);
        System.out.println("Anggota berhasil ditambahkan!");
    }
    
    private static void pinjamBuku(Scanner scanner, Perpustakaan perpus) {
        System.out.println("\n=== PINJAM BUKU ===");
        System.out.print("Masukkan ID Anggota: ");
        String idAnggota = scanner.nextLine();
        System.out.print("Masukkan ID Buku: ");
        String idBuku = scanner.nextLine();
        
        perpus.pinjamBuku(idAnggota, idBuku);
    }
    
    private static void kembalikanBuku(Scanner scanner, Perpustakaan perpus) {
        System.out.println("\n=== KEMBALIKAN BUKU ===");
        System.out.print("Masukkan ID Transaksi: ");
        String idTransaksi = scanner.nextLine();
        
        perpus.kembalikanBuku(idTransaksi);
    }
}

class Perpustakaan {
    private String nama;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Buku> daftarBuku;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Anggota> daftarAnggota;
    @SuppressWarnings("FieldMayBeFinal")
    private ArrayList<Transaksi> daftarTransaksi;
    
    public Perpustakaan(String nama) {
        this.nama = nama;
        this.daftarBuku = new ArrayList<>();
        this.daftarAnggota = new ArrayList<>();
        this.daftarTransaksi = new ArrayList<>();
    }
    
    public void tambahBuku(Buku buku) {
        daftarBuku.add(buku);
    }
    
    public void tambahAnggota(Anggota anggota) {
        daftarAnggota.add(anggota);
    }
    
    public void pinjamBuku(String idAnggota, String idBuku) {
        Anggota anggota = cariAnggota(idAnggota);
        Buku buku = cariBuku(idBuku);
        
        if (anggota != null && buku != null) {
            if (buku.getStok() > 0) {
                Transaksi transaksi = new Transaksi(anggota, buku);
                daftarTransaksi.add(transaksi);
                buku.kurangiStok();
                System.out.println("Peminjaman berhasil!");
            } else {
                System.out.println("Stok buku tidak tersedia!");
            }
        } else {
            System.out.println("Anggota atau buku tidak ditemukan!");
        }
    }
    
    public void kembalikanBuku(String idTransaksi) {
        for (Transaksi transaksi : daftarTransaksi) {
            if (transaksi.getIdTransaksi().equals(idTransaksi) && !transaksi.isSelesai()) {
                transaksi.setSelesai(true);
                transaksi.getBuku().tambahStok();
                System.out.println("Pengembalian berhasil!");
                return;
            }
        }
        System.out.println("Transaksi tidak ditemukan!");
    }
    
    private Anggota cariAnggota(String idAnggota) {
        for (Anggota anggota : daftarAnggota) {
            if (anggota.getIdAnggota().equals(idAnggota)) {
                return anggota;
            }
        }
        return null;
    }
    
    private Buku cariBuku(String idBuku) {
        for (Buku buku : daftarBuku) {
            if (buku.getIdBuku().equals(idBuku)) {
                return buku;
            }
        }
        return null;
    }
    
    public void tampilkanStatusPerpustakaan() {
        System.out.println("\n=== Status Perpustakaan " + nama + " ===");
        System.out.println("\nDaftar Buku:");
        for (Buku buku : daftarBuku) {
            buku.tampilkanInfo();
        }
        
        System.out.println("\nDaftar Anggota:");
        for (Anggota anggota : daftarAnggota) {
            anggota.tampilkanInfo();
        }
        
        System.out.println("\nDaftar Transaksi:");
        for (Transaksi transaksi : daftarTransaksi) {
            transaksi.tampilkanInfo();
        }
    }
}

abstract class Buku {
    private String idBuku;
    private String judul;
    private String pengarang;
    private int stok;
    
    public Buku(String idBuku, String judul, String pengarang, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.stok = stok;
    }
    
    abstract void tampilkanInfo();
    
    public String getIdBuku() { return idBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getStok() { return stok; }
    
    public void kurangiStok() { if (stok > 0) stok--; }
    public void tambahStok() { stok++; }
}

class BukuFiksi extends Buku {
    private String genre;
    
    public BukuFiksi(String idBuku, String judul, String pengarang, String genre, int stok) {
        super(idBuku, judul, pengarang, stok);
        this.genre = genre;
    }
    
    @Override
    void tampilkanInfo() {
        System.out.println("Buku Fiksi: " + getJudul() + " | Pengarang: " + getPengarang() +  " | Genre: " + genre + " | Stok: " + getStok());
    }
}

class BukuNonFiksi extends Buku {
  
    private String kategori;
    
    public BukuNonFiksi(String idBuku, String judul, String pengarang, String kategori, int stok) {
        super(idBuku, judul, pengarang, stok);
        this.kategori = kategori;
    }
    
    @Override
    void tampilkanInfo() {
        System.out.println("Buku Non-Fiksi: " + getJudul() + " | Pengarang: " + getPengarang() +  " | Kategori: " + kategori + " | Stok: " + getStok());
    }
}

class Anggota {
    private String idAnggota;
    private String nama;
    
    public Anggota(String idAnggota, String nama) {
        this.idAnggota = idAnggota;
        this.nama = nama;
    }
    
    public void tampilkanInfo() {
        System.out.println("Anggota: " + nama + " (ID: " + idAnggota + ")");
    }
    
    public String getIdAnggota() { return idAnggota; }
    public String getNama() { return nama; }

    public void setIdAnggota(String idAnggota) {
        this.idAnggota = idAnggota;
    }
}

class Transaksi {
    private final String idTransaksi;
    private Anggota anggota;
    private final Buku buku;
    private boolean selesai;
    private static int counter = 1;
    
    public Transaksi(Anggota anggota, Buku buku) {
        this.idTransaksi = "T" + String.format("%03d", counter++);
        this.anggota = anggota;
        this.buku = buku;
        LocalDateTime.now();
        this.selesai = false;
    }
    
    public void tampilkanInfo() {
        System.out.println("Transaksi " + idTransaksi + " | Anggota: " + anggota.getNama() + " | Buku: " + buku.getJudul() + " | Status: " +  (selesai ? "Selesai" : "Dipinjam"));
    }
    
    public String getIdTransaksi() { return idTransaksi; }
    public Buku getBuku() { return buku; }
    public boolean isSelesai() { return selesai; }
    public void setSelesai(boolean selesai) { this.selesai = selesai; }
}