/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package demo.gui;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ADMIN
 */
public class DemoJTable extends JFrame{
    
    private JTable tblSanPham;
    private JButton btThem;
    private DefaultTableModel model;
    
    public DemoJTable(String tile){
        super(tile);
        createGUI();
        processEvent();
        setSize(800, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
    }
    
    public static void main(String[] args) {
        DemoJTable frm = new DemoJTable("Demo Table");
        frm.setVisible(true);
    }

    private void createGUI() {
        
        
    }

    private void processEvent() {
        
        
    }
}
