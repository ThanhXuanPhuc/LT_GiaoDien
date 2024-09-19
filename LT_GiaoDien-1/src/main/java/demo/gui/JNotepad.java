/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo.gui;

import java.awt.*;
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

    private JMenuBar menuBar;
    private JMenu mFile, mEdit, mFormat, mView, mHelp, mZoom;
    private JMenuItem itemNev, itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit, itemFont, itemCopy, itemPaste, itemFind, itemReplace, itemZoomIn, itemZoomOut, itemZoomRestore;
    private JCheckBoxMenuItem itemWrap, itemStatusBar;
    private JTextArea txtEditor;
    private JToolBar toolBar;
    private JButton btNew, btOpen, btSave;

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
        //tạo đối tượng thanh thực đơn
        menuBar = new JMenuBar();
        //tạo các thực đơn và thêm vào thanh thực đơn
        menuBar.add(mFile = new JMenu("File"));
        menuBar.add(mEdit = new JMenu("Edit"));
        menuBar.add(mFormat = new JMenu("Format"));
        menuBar.add(mView = new JMenu("View"));
        menuBar.add(mHelp = new JMenu("Help"));

        //tạo các item cho menu file
        mFile.add(itemNev = new JMenuItem("New"));
        mFile.add(itemOpen = new JMenuItem("Open..."));
        mFile.add(itemSave = new JMenuItem("Save"));
        mFile.add(itemSaveAs = new JMenuItem("Save as..."));
        mFile.add(new JSeparator());
        mFile.add(itemPageSetup = new JMenuItem("Page setup..."));
        mFile.add(itemPrint = new JMenuItem("Print..."));
        mFile.addSeparator();
        mFile.add(itemExit = new JMenuItem("Exit"));

        //tạo item cho menu warp
        mFormat.add(itemWrap = new JCheckBoxMenuItem("Word Warp", true));
        mFormat.add(itemFont = new JMenuItem("Font..."));

        //tạo các item cho menu View
        mView.add(mZoom = new JMenu("Zoom"));
        mZoom.add(itemZoomIn = new JMenuItem("Zoom In"));
        mZoom.add(itemZoomOut = new JMenuItem("Zoom Out"));
        mZoom.add(itemZoomRestore = new JMenuItem("Restore Default Zoom"));
        mView.add(itemStatusBar = new JCheckBoxMenuItem("Status Bar"));

        //tạo item cho edit
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemFind = new JMenuItem("Find..."));
        mEdit.add(itemReplace = new JMenuItem("Replace..."));

        //tạo phím nóng cho các Item
        itemNev.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, KeyEvent.CTRL_DOWN_MASK));
        itemOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, KeyEvent.CTRL_DOWN_MASK));
        itemSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK));
        itemSaveAs.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, KeyEvent.CTRL_DOWN_MASK + KeyEvent.SHIFT_DOWN_MASK));
        itemPrint.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, KeyEvent.CTRL_DOWN_MASK));
        itemExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, KeyEvent.CTRL_DOWN_MASK));

        itemZoomIn.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_ADD, KeyEvent.CTRL_DOWN_MASK));
        itemZoomOut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_SUBTRACT, KeyEvent.CTRL_DOWN_MASK));

        //gắn thanh thực đơn vào cửa sổ
        setJMenuBar(menuBar);

    }

    private void createGui() {
        txtEditor = new JTextArea();
        JScrollPane scrollPane = new JScrollPane(txtEditor);
        add(scrollPane);
        txtEditor.setLineWrap(true);
        txtEditor.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    private void createToolBar() {
        toolBar = new JToolBar();
        toolBar.add(btNew = new JButton("New"));
        toolBar.add(btOpen = new JButton("Open"));
        toolBar.add(btSave = new JButton("Save"));
        
        
        add(toolBar, BorderLayout.NORTH);
    }

    private void processEvent() {
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
    }

    private void openFile() {
        JFileChooser dlgFile = new JFileChooser();
        if (dlgFile.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            try {
                //tạo luồng và liên kết với tập tin
                FileInputStream fis = new FileInputStream(dlgFile.getSelectedFile());
                byte[] b = new byte[fis.available()];
                //Đọc nội dung tập tin
                fis.read(b);
                //hiển thị vào vùng văn bản
                txtEditor.setText(new String(b));
                fis.close();
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Lỗi đọc File");
            }
        }
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
            } catch(Exception ex){
                JOptionPane.showMessageDialog(this, "Lỗi đọc File");
            }
        }
    }

}
