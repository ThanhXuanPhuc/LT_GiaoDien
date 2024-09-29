
package test;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FrmQLKhachHang extends JFrame {
    private QLKhachHang qlkh;
    private JTable table;
    private JTextField txtThapNhat, txtCaoNhat, txtTrungBinh;
    private JCheckBox chkSapXep;

    public FrmQLKhachHang() {
        qlkh = new QLKhachHang();

        setTitle("Quản lý điện năng");
        setSize(450, 300);  // Kích thước nhỏ hơn
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Đặt form ở giữa màn hình
        setLayout(new BorderLayout(5, 5)); // Cách đều giữa các vùng trong BorderLayout

        // Tạo bảng JTable
        table = new JTable();
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setPreferredSize(new Dimension(400, 100)); // Thiết lập kích thước bảng nhỏ hơn
        add(scrollPane, BorderLayout.CENTER);

        // Tạo panel dưới để chứa các thành phần điều khiển
        JPanel panelDuoi = new JPanel();
        panelDuoi.setLayout(new GridLayout(2, 3, 5, 5)); // Sử dụng GridLayout với 2 hàng 3 cột

        panelDuoi.add(new JLabel("Tiêu thụ thấp nhất:"));
        txtThapNhat = new JTextField(7);  // Kích thước nhỏ hơn
        txtThapNhat.setEditable(false);
        panelDuoi.add(txtThapNhat);

        panelDuoi.add(new JLabel("Tiêu thụ cao nhất:"));
        txtCaoNhat = new JTextField(7);
        txtCaoNhat.setEditable(false);
        panelDuoi.add(txtCaoNhat);

        panelDuoi.add(new JLabel("Tiêu thụ trung bình:"));
        txtTrungBinh = new JTextField(7);
        txtTrungBinh.setEditable(false);
        panelDuoi.add(txtTrungBinh);

        chkSapXep = new JCheckBox("Sắp xếp theo loại");
        panelDuoi.add(chkSapXep);

        add(panelDuoi, BorderLayout.SOUTH);

        // Tạo panel trên để chứa nút bấm
        JPanel panelTren = new JPanel();
        panelTren.setLayout(new FlowLayout(FlowLayout.LEFT)); // Căn trái để tiết kiệm không gian

        JButton btnNhap = new JButton("Nhập dữ liệu");
        btnNhap.setPreferredSize(new Dimension(130, 25)); // Kích thước nhỏ gọn cho nút
        panelTren.add(btnNhap);

        JButton btnXuat = new JButton("Xuất hóa đơn");
        btnXuat.setPreferredSize(new Dimension(130, 25)); // Kích thước nhỏ gọn cho nút
        panelTren.add(btnXuat);

        add(panelTren, BorderLayout.NORTH);

        // Xử lý sự kiện nhập dữ liệu khách hàng
        btnNhap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                qlkh.docDanhSachKhachHang("input.txt");
                hienThiDanhSach();
                txtThapNhat.setText(String.valueOf(qlkh.getTieuThuThapNhat()));
                txtCaoNhat.setText(String.valueOf(qlkh.getTieuThuCaoNhat()));
                txtTrungBinh.setText(String.valueOf(qlkh.getTieuThuTrungBinh()));
            }
        });

        // Xử lý sự kiện xuất hóa đơn thanh toán
        btnXuat.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean success = qlkh.xuatHoaDon("output.txt");
                if (success) {
                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thành công!");
                } else {
                    JOptionPane.showMessageDialog(null, "Xuất hóa đơn thất bại!");
                }
            }
        });

        // Xử lý sự kiện sắp xếp theo loại hình
        chkSapXep.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (chkSapXep.isSelected()) {
                    qlkh.sapXepTheoLoaiHinh();
                    hienThiDanhSach();
                }
            }
        });
    }

    // Phương thức hiển thị danh sách khách hàng lên JTable
    private void hienThiDanhSach() {
        String[] columnNames = {"Mã số", "Họ tên", "Loại", "Chỉ số cũ", "Chỉ số mới", "Tiêu thụ", "Tiền"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (KhachHang kh : qlkh.getDsKhachHang()) {
            Object[] rowData = {
                kh.getMaso(),
                kh.getHoten(),
                kh.getLoai() == 1 ? "Kinh doanh" : "Sinh hoạt",
                kh.getChisocu(),
                kh.getChisomoi(),
                kh.getTieuThu(),
                kh.getTienTra()
            };
            model.addRow(rowData);
        }
        table.setModel(model);
    }

    public static void main(String[] args) {
        new FrmQLKhachHang().setVisible(true);
    }
}
