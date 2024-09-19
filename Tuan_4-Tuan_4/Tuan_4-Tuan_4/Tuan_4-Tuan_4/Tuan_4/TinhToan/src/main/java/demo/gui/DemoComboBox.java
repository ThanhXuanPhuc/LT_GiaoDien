package demo.gui;

import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DemoComboBox extends JFrame {

    private JLabel lb1, lb2, lbPhepTinh, lbKq;
    private JTextField txtSo1, txtSo2, txtKq;
    private JButton btTinh, btNhapLai;
    private JComboBox<String> cboPhepTinh;

    public DemoComboBox() {
        setTitle("Máy tính đơn giản");
        taoGiaoDien();
        pack(); // Tự động căn chỉnh kích thước cửa sổ
        setLocationRelativeTo(null); // Căn giữa cửa sổ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void taoGiaoDien() {
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(5, 2, 20, 20)); // Tạo lưới với 5 hàng và 2 cột, khoảng cách 5px

        // Thêm các thành phần cho phần nhập số và phép tính
        p1.add(lb1 = new JLabel("Số 1"));
        p1.add(txtSo1 = new JTextField());

        p1.add(lb2 = new JLabel("Số 2"));
        p1.add(txtSo2 = new JTextField());

        p1.add(lbPhepTinh = new JLabel("Phép tính"));
        cboPhepTinh = new JComboBox<>();
        cboPhepTinh.addItem("Cộng");
        cboPhepTinh.addItem("Trừ");
        cboPhepTinh.addItem("Nhân");
        cboPhepTinh.addItem("Chia");
        p1.add(cboPhepTinh);

        p1.add(lbKq = new JLabel("Kết quả"));
        txtKq = new JTextField();
        txtKq.setEditable(false); // Không cho phép chỉnh sửa ô kết quả
        p1.add(txtKq);

        add(p1, BorderLayout.CENTER);

        // Thêm các nút tính toán và nhập lại phía dưới
        JPanel p2 = new JPanel();
        p2.setLayout(new FlowLayout());
        p2.add(btTinh = new JButton("Tính"));
        p2.add(btNhapLai = new JButton("Nhập lại"));
        add(p2, BorderLayout.SOUTH);

        // Xử lý sự kiện cho nút "Tính"
        btTinh.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    double so1 = Double.parseDouble(txtSo1.getText());
                    double so2 = Double.parseDouble(txtSo2.getText());
                    double kq = 0;
                    String phepTinh = (String) cboPhepTinh.getSelectedItem();

                    switch (phepTinh) {
                        case "Cộng":
                            kq = so1 + so2;
                            break;
                        case "Trừ":
                            kq = so1 - so2;
                            break;
                        case "Nhân":
                            kq = so1 * so2;
                            break;
                        case "Chia":
                            if (so2 != 0) {
                                kq = so1 / so2;
                            } else {
                                JOptionPane.showMessageDialog(null, "Không thể chia cho 0", "Lỗi", JOptionPane.ERROR_MESSAGE);
                                return;
                            }
                            break;
                    }
                    txtKq.setText(String.valueOf(kq));
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Vui lòng nhập số hợp lệ", "Lỗi nhập liệu", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Xử lý sự kiện cho nút "Nhập lại"
        btNhapLai.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txtSo1.setText("");
                txtSo2.setText("");
                txtKq.setText("");
                cboPhepTinh.setSelectedIndex(0); // Đặt phép tính mặc định là "Cộng"
                txtSo1.requestFocus(); // Đặt con trỏ vào ô nhập số 1
            }
        });
    }

    public static void main(String[] args) {
        DemoComboBox frm = new DemoComboBox();
        frm.setVisible(true);
    }
}
