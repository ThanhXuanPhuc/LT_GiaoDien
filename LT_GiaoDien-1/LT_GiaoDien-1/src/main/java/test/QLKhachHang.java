
package test;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class QLKhachHang {
    private ArrayList<KhachHang> dsKhachHang;

    // Constructor
    public QLKhachHang() {
        dsKhachHang = new ArrayList<>();
    }

    // Getter cho danh sách khách hàng
    public ArrayList<KhachHang> getDsKhachHang() {
        return dsKhachHang;
    }

    // Đọc danh sách khách hàng từ file input.txt
    public void docDanhSachKhachHang(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            dsKhachHang.clear(); // Xóa danh sách cũ trước khi đọc dữ liệu mới
            while ((line = br.readLine()) != null) {
                String[] tokens = line.split(";");
                if (tokens.length == 5) {
                    String maso = tokens[0];
                    String hoten = tokens[1];
                    int loai = Integer.parseInt(tokens[2]);
                    double chisocu = Double.parseDouble(tokens[3]);
                    double chisomoi = Double.parseDouble(tokens[4]);
                    dsKhachHang.add(new KhachHang(maso, hoten, loai, chisocu, chisomoi));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Phương thức tìm số kWh tiêu thụ thấp nhất
    public double getTieuThuThapNhat() {
        if (dsKhachHang.isEmpty()) return 0;
        double minTieuThu = dsKhachHang.get(0).getTieuThu();
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() < minTieuThu) {
                minTieuThu = kh.getTieuThu();
            }
        }
        return minTieuThu;
    }

    // Phương thức tìm số kWh tiêu thụ cao nhất
    public double getTieuThuCaoNhat() {
        if (dsKhachHang.isEmpty()) return 0;
        double maxTieuThu = dsKhachHang.get(0).getTieuThu();
        for (KhachHang kh : dsKhachHang) {
            if (kh.getTieuThu() > maxTieuThu) {
                maxTieuThu = kh.getTieuThu();
            }
        }
        return maxTieuThu;
    }

    // Phương thức tính số kWh tiêu thụ trung bình
    public double getTieuThuTrungBinh() {
        if (dsKhachHang.isEmpty()) return 0;
        double tongTieuThu = 0;
        for (KhachHang kh : dsKhachHang) {
            tongTieuThu += kh.getTieuThu();
        }
        return tongTieuThu / dsKhachHang.size();
    }

    // Phương thức sắp xếp danh sách khách hàng theo loại hình
    public void sapXepTheoLoaiHinh() {
        Collections.sort(dsKhachHang, new Comparator<KhachHang>() {
            @Override
            public int compare(KhachHang kh1, KhachHang kh2) {
                return Integer.compare(kh1.getLoai(), kh2.getLoai());
            }
        });
    }

    // Phương thức xuất hóa đơn ra file output.txt
    public boolean xuatHoaDon(String fileName) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(fileName))) {
            for (KhachHang kh : dsKhachHang) {
                String line = kh.getMaso() + ";" +
                              kh.getHoten() + ";" +
                              kh.getTieuThu() + ";" +
                              kh.getTienTra();
                bw.write(line);
                bw.newLine();
            }
            return true; // Xuất thành công
        } catch (IOException e) {
            e.printStackTrace();
            return false; // Xuất thất bại
        }
    }
}
