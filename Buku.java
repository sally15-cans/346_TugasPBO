public abstract class Buku {
    private  String idBuku;
    private final String judul;
    private final String pengarang;
    private int stok;
    
    public Buku(String idBuku, String judul, String pengarang, int stok) {
        this.idBuku = idBuku;
        this.judul = judul;
        this.pengarang = pengarang;
        this.stok = stok;
    }
    
    
    public String getIdBuku() { return idBuku; }
    public String getJudul() { return judul; }
    public String getPengarang() { return pengarang; }
    public int getStok() { return stok; }
    
    public void kurangiStok() { if (stok > 0) stok--; }
    public void tambahStok() { stok++; }

    public void setIdBuku(String idBuku) {
        this.idBuku = idBuku;
    }
}
