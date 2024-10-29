public class Anggota {
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
}
