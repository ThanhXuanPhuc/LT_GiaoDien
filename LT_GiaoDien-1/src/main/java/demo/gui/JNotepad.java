/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo.gui;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import javax.swing.*;
import javax.swing.plaf.ToolBarUI;

/**
 *
 * @author ADMIN
 */
public class JNotepad extends JFrame {

    private JMenuBar mBar;
    private JMenu mFile, mEdit, mFomat, mView, mZoom, mHelp;
    private JMenuItem itemNew, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit;
    private JMenuItem itemUndo, itemCut, itemCopy, itemPast, itemDelete, itemSearch, itemFind, itemFindNext, itemFindPrevious, itemReplace, itemGoTo, itemSelectAll, itemTimeDate;
    private JMenuItem itemFont, itemZoomIn, itemZoomOut, itemRestore, itemViewHelp, itemSendFeedback, itemaboutNotepad;
    private JCheckBoxMenuItem itemWrap, itemStatusBar;
    private JTextArea txtEditor;
    private JToolBar toolBar;
    private JButton btNew, btOpen, btSave;

    int size = 20;

    public JNotepad(String title) {
        super(title);
        createMenu();
        createGui();
        createToolBar();
        processEvent();
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JNotepad frm = new JNotepad("DemoNotePad");
        frm.setVisible(true);
    }

    private void createMenu() {
        mBar = new JMenuBar();
        mBar.add(mFile = new JMenu("File"));
        mBar.add(mEdit = new JMenu("Edit"));
        mBar.add(mFomat = new JMenu("Fomat"));
        mBar.add(mView = new JMenu("View"));
        mBar.add(mHelp = new JMenu("Help"));

        mFile.add(itemNew = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save As..."));
        mFile.addSeparator();
        mFile.add(itemPageSetup = new JMenuItem("Page Setup..."));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));

        mEdit.add(itemUndo = new JMenuItem("Undo"));
        mEdit.addSeparator();
        mEdit.add(itemCut = new JMenuItem("Cut"));
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPast = new JMenuItem("Paste"));
        mEdit.add(itemDelete = new JMenuItem("Delete"));
        mEdit.addSeparator();
        mEdit.add(itemSearch = new JMenuItem("Search with Bing..."));
        mEdit.add(itemFind = new JMenuItem("Find"));
        mEdit.add(itemFindNext = new JMenuItem("Find Text"));
        mEdit.add(itemFindPrevious = new JMenuItem("Find Previous"));
        mEdit.add(itemReplace = new JMenuItem("Replace..."));
        mEdit.add(itemGoTo = new JMenuItem("Go To..."));
        mEdit.addSeparator();
        mEdit.add(itemSelectAll = new JMenuItem("Select All"));
        mEdit.add(itemTimeDate = new JMenuItem("Time/Date"));

        mFomat.add(itemWrap = new JCheckBoxMenuItem("Word Wrap", true));
        mFomat.add(itemFont = new JMenuItem("Font..."));

        mView.add(mZoom = new JMenu("Zoom"));
        mView.add(itemStatusBar = new JCheckBoxMenuItem("Status Bar", true));
        mZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomOut = new JMenuItem("Zoom Out"));
        mZoom.add(itemRestore = new JMenuItem("Restore Default Zoom"));

        mHelp.add(itemViewHelp = new JMenuItem("View Help"));
        mHelp.add(itemSendFeedback = new JMenuItem("Send Feedback"));
        mHelp.add(itemaboutNotepad = new JMenuItem("About Notepad"));

        itemNew.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_W, KeyEvent.CTRL_DOWN_MASK));

        itemUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, KeyEvent.CTRL_DOWN_MASK));
        itemCut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, KeyEvent.CTRL_DOWN_MASK));
        itemCopy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, KeyEvent.CTRL_DOWN_MASK));
        itemPast.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, KeyEvent.CTRL_DOWN_MASK));
        itemDelete.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0));
        itemSearch.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, KeyEvent.CTRL_DOWN_MASK));
        itemFind.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, KeyEvent.CTRL_DOWN_MASK));
        itemFindNext.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, 0));
        itemFindPrevious.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F3, KeyEvent.SHIFT_DOWN_MASK));
        itemReplace.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, KeyEvent.CTRL_DOWN_MASK));
        itemGoTo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_G, KeyEvent.CTRL_DOWN_MASK));
        itemSelectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, KeyEvent.CTRL_DOWN_MASK));
        itemTimeDate.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F5, 0));

        itemZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));
        itemZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_MINUS, KeyEvent.CTRL_DOWN_MASK));
        itemRestore.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_0, KeyEvent.CTRL_DOWN_MASK));

        setJMenuBar(mBar);

    }

    private void createGui() {
        txtEditor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtEditor);
        add(scrollPane);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
    }

    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.add(btNew = new JButton("New"));
        toolBar.add(btOpen = new JButton("Open"));
        toolBar.add(btSave = new JButton("Save"));

        // Thêm sự kiện cho nút New
        btNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();  // Gọi phương thức tạo file mới
            }
        });

        // Thêm sự kiện cho nút Open
        btOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();  // Gọi phương thức mở file
            }
        });

        // Thêm sự kiện cho nút Save
        btSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();  // Gọi phương thức lưu file
            }
        });

        add(toolBar, BorderLayout.NORTH);
    }

    private void processEvent() {
        itemPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtEditor.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi in: " + ex.getMessage());
                }
            }
        });
        itemPrint.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    txtEditor.print();
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Lỗi khi in: " + ex.getMessage());
                }
            }
        });
        itemZoomIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                size += 4;
                txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
            }
        });
        itemZoomOut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                size -= 4;
                txtEditor.setFont(new Font("Arial", Font.PLAIN, size));
            }
        });
        itemRestore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                txtEditor.setFont(new Font("Arial", Font.PLAIN, 20));
            }
        });
        itemSaveAs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAsFile();
            }

        });
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Exit();
            }

        });

        itemFindNext.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                FindNext();
            }
        });
        itemReplace.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                replaceText();
            }
        });

        itemCopy.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Copy();
            }

        });
        itemFont.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFontDialog fontDialog = new JFontDialog(JNotepad.this, true);
                fontDialog.setVisible(true);
            }
        });
        itemPast.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Paste();
            }

        });
        //Xu ly item Word Wrap
        itemWrap.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (itemWrap.isSelected()) {
                    txtEditor.setLineWrap(true);
                } else {
                    txtEditor.setLineWrap(false);
                }
            }
        });

        //xu ly item Open
        itemOpen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openFile();
            }

        });

        //xu ly item Save
        itemSave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveFile();
            }

        });
        itemNew.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                newFile();
            }

        });
    }

    private void newFile() {
        if (JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn tạo file mới?") == JOptionPane.YES_OPTION) {
            txtEditor.setText("");  // Xóa sạch nội dung trong JTextArea
        }
    }

    private void replaceText() {
        String find = JOptionPane.showInputDialog(this, "Tìm:");
        String replace = JOptionPane.showInputDialog(this, "Thay thế bằng:");
        if (find != null && replace != null && !find.isEmpty()) {
            txtEditor.setText(txtEditor.getText().replaceAll(find, replace));
        }
    }

    private void FindNext() {
        String find = JOptionPane.showInputDialog(this, "Tìm:");
        if (find != null && !find.isEmpty()) {
            String content = txtEditor.getText();
            int index = content.indexOf(find);
            if (index != -1) {
                txtEditor.setCaretPosition(index);
                txtEditor.select(index, index + find.length());
            } else {
                JOptionPane.showMessageDialog(this, "Không tìm thấy văn bản");
            }
        }
    }

    private void Exit() {
        if (JOptionPane.showConfirmDialog(null, "Bạn chắc chắn muốn thoát chứ?") == JOptionPane.YES_OPTION) {
            System.exit(0);
        }
    }

    private void Copy() {
        String selectedText = txtEditor.getSelectedText();
        if (selectedText != null && !selectedText.isEmpty()) {
            StringSelection stringSelection = new StringSelection(selectedText);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
        } else {
            JOptionPane.showMessageDialog(txtEditor, "Không có nội dung để sao chép!!!");
        }
    }

    private void Paste() {
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        try {
            String pastedText = (String) clipboard.getData(DataFlavor.stringFlavor);
            txtEditor.insert(pastedText, txtEditor.getCaretPosition());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(txtEditor, "Lỗi dán nội dung: " + ex.getMessage());
        }
    }

    private void openFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileInputStream fis = new FileInputStream(dlgFile.getSelectedFile());
                byte[] b = new byte[fis.available()];
                fis.read(b);
                txtEditor.setText(new String(b));
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, " Lỗi đọc File");
            }
        }
    }

    private void saveAsFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                FileOutputStream fos = new FileOutputStream(dlgFile.getSelectedFile());
                fos.write(txtEditor.getText().getBytes());
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi ghi file: " + ex.getMessage());
            }
        }
    }

    public JTextArea getEditor() {
        return txtEditor;
    }

    private void saveFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showSaveDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                //tạo luồng và liên kết với tập tin
                FileOutputStream fos = new FileOutputStream(dlgFile.getSelectedFile());
                //Ghi nội dung vùng văn bản ra tập tin
                fos.write(txtEditor.getText().getBytes());
                //Đóng luồng
                fos.close();
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(this, "Lỗi đọc File");
            }
        }
    }

}
