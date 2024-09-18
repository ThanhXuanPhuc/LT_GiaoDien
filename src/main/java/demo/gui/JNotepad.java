/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package demo.gui;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author ADMIN
 */
public class JNotepad extends JFrame{
    private JMenuBar menuBar;
    private JMenu mFile,mEdit,mFormat,mView,mHelp;
    private JMenuItem itemNev,itemOpen, itemSave, itemSaveAs, itemPageSetup, itemPrint, itemExit, itemFont, itemCopy, itemPaste
            ,itemFind, itemReplace;
    private JCheckBoxMenuItem itemWarp;
    private JTextArea txtEditor;
    
    public JNotepad(String title){
        super(title);
        createMenu();
        createGui();
        processEvent();
        setSize(800,600);
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
        
        //tạo item cho edit
        mEdit.add(itemCopy = new JMenuItem("Copy"));
        mEdit.add(itemPaste = new JMenuItem("Paste"));
        mEdit.add(itemFind = new JMenuItem("Find..."));
        mEdit.add(itemReplace = new JMenuItem("Replace..."));
        //tạo item cho menu warp
        mFormat.add(itemWarp = new JCheckBoxMenuItem("Word Warp"));     
        mFormat.add(itemFont = new JMenuItem("Font..."));
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

    private void processEvent() {
        //Tiếp nhận sự kiện(event) cho itemExit
        itemExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
 
            }
        });
        
    }
    
}
