package arobial.ta.bukukondangan;

public class DataKondangan {
    public String nama, kecamatan, kelurahan, alamat, rp, liter, status;
    private  String kategori; // memberi nama acara hajatan, misal : nikhan, sunatan, dll

    public String getNama() {
        return nama; }
    public void setNama(String nama) {
        this.nama = nama; }

    public String getKecamatan() {
        return kecamatan; }
    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan; }

    public String getKelurahan() {
        return kelurahan; }
    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan; }

    public String getAlamat() {
        return alamat; }
    public void setAlamat(String alamat) {
        this.alamat = alamat; }

    public String getRp() {
        return rp; }
    public void setRp(String rp) {
        this.rp = rp; }

    public String getLiter() {
        return liter; }
    public void setLiter(String liter) {
        this.liter = liter; }

    public void setKategori(String kategori) {
        this.kategori = kategori; }
    public String getKategori() {
        return kategori; }

    public String getStatus() {
        return status; }
    public void setStatus(String status) {
        this.status = status; }

    public DataKondangan() {
    }
}
