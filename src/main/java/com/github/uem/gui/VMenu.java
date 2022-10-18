package com.github.uem.gui;

import com.github.uem.control.CMultiTool;
import com.github.uem.lang.Messages;

import javax.swing.*;
import java.awt.*;

public class VMenu extends JFrame{
    public final static int ANCHO = 800;
    public final static int ALTO = 600;

    private JButton btnExcel;
    private JButton btnWord;
    private JButton btnPower;
    private JTextField txtUrl;
    private JButton btnSeach;
    private JPanel mainMenu;

    public VMenu() {
        add(mainMenu);
        initComponents();
    }

    private void initComponents() {
        pack();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setTitle("Menu de Gestión");
        btnWord.setText(Messages.BTN_WORD);
        btnExcel.setText(Messages.BT_EXCEL);
        btnPower.setText(Messages.BTN_POWERP);
        btnSeach.setText(Messages.BTN_NAVEGAR);
        setSize(ANCHO, ALTO);
        centrarVentana();
    }

    private void centrarVentana() {
        setPreferredSize(new Dimension(ANCHO, ALTO));
        Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension ventana = this.getPreferredSize();
        setLocation((pantalla.width - ventana.width) / 2,  (pantalla.height - ventana.height) / 2);
    }


    public void setControlled(CMultiTool controller) {
        btnPower.addActionListener(controller);
        btnSeach.addActionListener(controller);
        btnWord.addActionListener(controller);
        btnExcel.addActionListener(controller);
    }

    public String getUrl() {
        String url = txtUrl.getText();

        if (url.isBlank()){
            showErrorMsg("Debe introducir una url");
        } else {
            return url;
        }

        return "";
    }

    public void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this , msg, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
