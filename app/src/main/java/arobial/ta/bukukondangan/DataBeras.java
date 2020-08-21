package arobial.ta.bukukondangan;

public class DataBeras {

    private String nama1;
    private String nama2;
    private String kecamatan;
    private String kelurahan;
    private String alamat;
    private String liter;

    public DataBeras() {

    }

    public DataBeras(String nama1, String nama2, String kecamatan, String kelurahan, String alamat, String liter) {
        this.nama1 = nama1;
        this.nama2 = nama2;
        this.kecamatan = kecamatan;
        this.kelurahan = kelurahan;
        this.alamat = alamat;
        this.liter = liter;
    }

    public String getNama1() {
        return nama1;
    }

    public void setNama1(String nama1) {
        this.nama1 = nama1;
    }

    public String getNama2() {
        return nama2;
    }

    public void setNama2(String nama2) {
        this.nama2 = nama2;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getKelurahan() {
        return kelurahan;
    }

    public void setKelurahan(String kelurahan) {
        this.kelurahan = kelurahan;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getLiter() {
        return liter;
    }

    public void setLiter(String liter) {
        this.liter = liter;
    }
}
