import java.util.ArrayList;

public class Perpustakaan {
    private String nama;
    private ArrayList<Buku> daftarBuku;
    private ArrayList<Anggota> daftarAnggota;
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
    }
    public void kembalikanBuku(String idTransaksi) {
    }
    public void tampilkanStatusPerpustakaan() {
    }
}