public class BukuNonFiksi extends Buku {
    private String kategori;
    
    public BukuNonFiksi(String idBuku, String judul, String pengarang, String kategori, int stok) {
        super(idBuku, judul, pengarang, stok);
        this.kategori = kategori;
    }
    
    void tampilkanInfo() {
        System.out.println("Buku Non-Fiksi: " + getJudul() + " | Pengarang: " + getPengarang() + " | Kategori: " + kategori + " | Stok: " + getStok());
    }
}
