
package test;


public class KhachHang {
    private String maso;
    private String hoten;
    private int loai; // 1: Kinh doanh, 2: Sinh hoạt
    private double chisocu;
    private double chisomoi;

    public KhachHang(String maso, String hoten, int loai, double chisocu, double chisomoi) {
        this.maso = maso;
        this.hoten = hoten;
        this.loai = loai;
        this.chisocu = chisocu;
        this.chisomoi = chisomoi;
    }

    public String getMaso() {
        return maso;
    }

    public void setMaso(String maso) {
        this.maso = maso;
    }

    public String getHoten() {
        return hoten;
    }

    public void setHoten(String hoten) {
        this.hoten = hoten;
    }

    public int getLoai() {
        return loai;
    }

    public void setLoai(int loai) {
        this.loai = loai;
    }

    public double getChisocu() {
        return chisocu;
    }

    public void setChisocu(double chisocu) {
        this.chisocu = chisocu;
    }

    public double getChisomoi() {
        return chisomoi;
    }

    public void setChisomoi(double chisomoi) {
        this.chisomoi = chisomoi;
    }

    // Tính số kWh tiêu thụ
    public double getTieuThu() {
        return chisomoi - chisocu;
    }

    // Tính tiền trả
    public double getTienTra() {
        double tieuThu = getTieuThu();
        double tienTra = 0;

        if (loai == 1) { // Kinh doanh
            tienTra = tieuThu * 4575;
        } else if (loai == 2) { // Sinh hoạt
            if (tieuThu <= 50) {
                tienTra = tieuThu * 1806;
            } else if (tieuThu <= 100) {
                tienTra = 50 * 1806 + (tieuThu - 50) * 1866;
            } else if (tieuThu <= 200) {
                tienTra = 50 * 1806 + 50 * 1866 + (tieuThu - 100) * 2167;
            } else if (tieuThu <= 300) {
                tienTra = 50 * 1806 + 50 * 1866 + 100 * 2167 + (tieuThu - 200) * 2729;
            } else if (tieuThu <= 400) {
                tienTra = 50 * 1806 + 50 * 1866 + 100 * 2167 + 100 * 2729 + (tieuThu - 300) * 3050;
            } else {
                tienTra = 50 * 1806 + 50 * 1866 + 100 * 2167 + 100 * 2729 + 100 * 3050 + (tieuThu - 400) * 3151;
            }
        }
        return tienTra * 1.08; // Cộng thêm VAT 8%
    }
}
