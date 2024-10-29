import java.time.LocalDateTime;

public class Transaksi {
    private String idTransaksi;
    private Anggota anggota;
    private Buku buku;
    private LocalDateTime tanggalPinjam;
    private boolean selesai;
    private static int counter = 1;
    
    public Transaksi(Anggota anggota, Buku buku) {
        this.idTransaksi = "T" + String.format("%03d", counter++);
        this.anggota = anggota;
        this.buku = buku;
        this.tanggalPinjam = LocalDateTime.now();
        this.selesai = false;
    }
    
    public void tampilkanInfo() {
        System.out.println("Transaksi " + idTransaksi + " | Anggota: " + anggota.getNama() + " | Buku: " + buku.getJudul() + " | Status: " + (selesai ? "Selesai" : "Dipinjam"));
    }
    
    public String getIdTransaksi() { return idTransaksi; }
    public Buku getBuku() { return buku; }
    public boolean isSelesai() { return selesai; }
    public void setSelesai(boolean selesai) { this.selesai = selesai; }

    public void setIdTransaksi(String idTransaksi) {
        this.idTransaksi = idTransaksi;
    }

    public Anggota getAnggota() {
        return anggota;
    }

    public void setAnggota(Anggota anggota) {
        this.anggota = anggota;
    }
}
