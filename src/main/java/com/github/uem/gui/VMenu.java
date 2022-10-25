package com.github.uem.gui;

import com.github.uem.control.CMultiTool;
import com.github.uem.lang.Messages;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class VMenu extends JFrame{
    public final static int ANCHO = 800;
    public final static int ALTO = 600;
    private JButton btnExcel;
    private JButton btnWord;
    private JButton btnPower;
    private JTextField txtUrl;
    private JButton btnSeach;
    private JPanel mainMenu;
    private JList<String> list;
    private DefaultListModel<String> dlm;
    private JScrollPane srcPanel;

    public VMenu() {
        add(mainMenu);
        initComponents();
    }

    public void mostrarLista(ArrayList<String> listaWeb) {
        dlm.clear();

        for (String str : listaWeb) {
            dlm.addElement(str);
        }
        txtUrl.setText("");
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

        dlm = new DefaultListModel<>();
        list.setModel(dlm);

        ArrayList<String> listaWeb;
        listaWeb = cargarDatos();
        mostrarLista(listaWeb);
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

    public String getUrl() throws IOException {
        String url = txtUrl.getText();



        if ( url.isEmpty() && getUrlSelected() != null) {
            System.out.println("No se ha introducido ninguna URL");
            Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + getUrlSelected());
            showErrorMsg("CUIDADO! No has introducido una URL válida");
        } else {
            return url;
        }
        return "";
    }

    public ArrayList<String> cargarDatos() {
        ArrayList<String> lista = new ArrayList<>();
        System.out.println("Cargando datos...");
        Scanner sc = null;
        String web;
        try {

            sc = new Scanner (new FileReader("src/main/resources/history.txt"));
            while (sc.hasNext()) {
                web = sc.nextLine();
                lista.add(web);
            }
        } catch (FileNotFoundException | NoSuchElementException e) {
            e.printStackTrace();
        } finally{
            assert sc != null;
            sc.close();
        }
        return lista;
    }

    public String getUrlSelected() {
        //txtUrl.setText(list.getSelectedValue().toString());
        return list.getSelectedValue();
    }

    public void showErrorMsg(String msg) {
        JOptionPane.showMessageDialog(this , msg, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
