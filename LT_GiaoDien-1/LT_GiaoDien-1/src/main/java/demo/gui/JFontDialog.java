package demo.gui;

import java.awt.Font;
import java.awt.Frame;
import java.awt.GraphicsEnvironment;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author ADMIN
 */
public class JFontDialog extends JDialog{

    private JList lstFontName, lstStyle, lstSize;
    private JLabel lbFontName, lbStyle, lbSize, lbPreview;
    private JButton btOk, btCancel;
    
    private Font font;
    private int[] arrStyle = { Font.PLAIN, Font.ITALIC, Font.BOLD, Font.ITALIC + Font.BOLD};    
    
    private JNotepad parent;
    
    public JFontDialog(Frame owner, boolean modal) {
        super(owner, modal);
        parent = (JNotepad) owner; // Nhận cửa sổ cha
        createGUI();
        setFontPreview();
        processEvent();
        setSize(550, 450); // Kích thước của hộp thoại
        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(owner); // Đặt vị trí trung tâm
    }

    private void createGUI() {
        JPanel p = new JPanel();
        p.setLayout(null);
        
        // Nhãn cho danh sách font, style, và kích thước
        p.add(lbFontName = new JLabel("Font:"));
        lbFontName.setBounds(10, 10, 150, 25);
        
        p.add(lbStyle = new JLabel("Font style:"));
        lbStyle.setBounds(220, 10, 100, 25);
        
        p.add(lbSize = new JLabel("Size:"));
        lbSize.setBounds(350, 10, 50, 25);
        
        // Danh sách tên font
        String[] fontnames = GraphicsEnvironment.getLocalGraphicsEnvironment().getAvailableFontFamilyNames();
        lstFontName = new JList(fontnames);
        JScrollPane scrollFontName = new JScrollPane(lstFontName);
        lstFontName.setSelectedIndex(0);
        p.add(scrollFontName);
        scrollFontName.setBounds(10, 40, 200, 200);
        
        // Danh sách style
        String[] styles = {"Regular", "Italic", "Bold", "Bold Italic"};
        lstStyle = new JList(styles);
        JScrollPane scrollStyle = new JScrollPane(lstStyle);
        lstStyle.setSelectedIndex(0);
        p.add(scrollStyle);
        scrollStyle.setBounds(220, 40, 100, 200);
        
        // Danh sách kích thước
        String[] sizes = {"8", "9", "10", "11", "12", "14", "16", "18", "20", "22", "24", "26", "28", "36", "48", "72"};
        lstSize = new JList(sizes);
        JScrollPane scrollSize = new JScrollPane(lstSize);
        lstSize.setSelectedIndex(5); // Chọn mặc định 14
        p.add(scrollSize);
        scrollSize.setBounds(350, 40, 80, 200);
        
        // Nhãn xem trước
        p.add(lbPreview = new JLabel("AaBbYyZz"));
        lbPreview.setBounds(200, 260, 300, 80);
        lbPreview.setFont(new Font("Serif", Font.PLAIN, 24)); // Font mặc định xem trước
        
        // Nút OK và Cancel
        p.add(btOk = new JButton("OK"));
        btOk.setBounds(150, 350, 100, 40);
        
        p.add(btCancel = new JButton("Cancel"));
        btCancel.setBounds(280, 350, 100, 40);
        
        add(p);                 
    }

    private void setFontPreview() {
        String name = (String) lstFontName.getSelectedValue();
        int style = arrStyle[lstStyle.getSelectedIndex()];
        int size = Integer.parseInt(lstSize.getSelectedValue().toString());
        font = new Font(name, style, size);
        lbPreview.setFont(font); // Cập nhật font xem trước
    }

    private void processEvent() {
        // Xử lý sự kiện thay đổi font
        lstFontName.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setFontPreview();
            }
        });

        // Xử lý sự kiện thay đổi style
        lstStyle.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setFontPreview();
            }
        });

        // Xử lý sự kiện thay đổi size
        lstSize.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                setFontPreview();
            }
        });

        // Xử lý nút OK
        btOk.addActionListener((e) -> {
            // Áp dụng font cho vùng soạn thảo trong cửa sổ cha (JNotepad)
            parent.getEditor().setFont(font);
            this.dispose(); // Đóng hộp thoại
        });

        // Xử lý nút Cancel
        btCancel.addActionListener((e) -> {
            this.dispose(); // Đóng hộp thoại mà không thay đổi
        });
    }
}
