public class BukuFiksi extends Buku {
    private final String genre;
    
    public BukuFiksi(String idBuku, String judul, String pengarang, String genre, int stok) {
        super(idBuku, judul, pengarang, stok);
        this.genre = genre;
    }
    
    void tampilkanInfo() {
        System.out.println("Buku Fiksi: " + getJudul() + " | Pengarang: " + getPengarang() + " | Genre: " + genre + " | Stok: " + getStok());
    }
}
