package com.github.uem.control;

import com.github.uem.gui.VMenu;
import com.github.uem.lang.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class CMultiTool implements ActionListener {

    private final VMenu vMenu;

    public CMultiTool(VMenu vMenu){
        this.vMenu = vMenu;
    }

    public ArrayList<String> cargarDatos() {
        ArrayList<String> lista = new ArrayList<>();
        Scanner scan = null;
        String web;
        try {
            scan = new Scanner (new FileReader("src/main/resources/history.txt"));
            while (scan.hasNext()) {
                web = scan.nextLine();
                lista.add(web);
            }

        } catch (FileNotFoundException | NoSuchElementException e) {
            e.printStackTrace();
        } finally{
            assert scan != null;
            scan.close();
        }
        return lista;

    }

    public void anadirWebArchivo(String web, ArrayList<String> listaWeb) throws IOException {
        FileWriter fw;
        BufferedWriter bw = null;
        Scanner sc = new Scanner(System.in);
        try {
            File file = new File("src/main/resources/history.txt");
            fw = new FileWriter(file.getAbsoluteFile(), true);
            bw = new BufferedWriter(fw);

            bw.write(web);
            bw.write("\n");

            bw.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        sc.close();
    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource() instanceof JButton){
            if (e.getActionCommand().equals(Messages.BT_EXCEL)) {
                System.out.println("(DEBUG) Get btnExcel");
                openExcel();
            } else if (e.getActionCommand().equals(Messages.BTN_POWERP)) {
                System.out.println("(DEBUG) Get btnPower");
                openPowerP();
            } else if (e.getActionCommand().equals(Messages.BTN_WORD)) {
                System.out.println("(DEBUG) Get btnWord");
                openWord();
            } else if (e.getActionCommand().equals(Messages.BTN_NAVEGAR)) {
                System.out.println("(DEBUG) Get btnNavegar");

                try {
                    navigate();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }

    private void navigate() throws IOException {

        ArrayList<String> listaWeb;
        listaWeb = vMenu.cargarDatos();

        if (vMenu.getUrl().isBlank() && vMenu.getUrlSelected() == null) {
            vMenu.showErrorMsg("No se ha introducido ninguna URL");
            System.out.println("(DEBUG) No se ha introducido ninguna URL");
        } else {

            //Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + vMenu.getUrl());
            System.out.println("(OK DEBUG) URL introducida: " + vMenu.getUrl());

            if (isValidURL(vMenu.getUrl()) || !vMenu.getUrlSelected().isBlank()) {
                System.out.println("(DEBUG) URL is valid");
                try {
                    System.out.println("(DEBUG) Adding URL to file");;
                    anadirWebArchivo(vMenu.getUrl(), listaWeb);
                    String web = vMenu.getUrl().trim();
                    listaWeb.add(web);
                    vMenu.cargarDatos();

                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

                vMenu.cargarDatos();
                vMenu.mostrarLista(listaWeb);

            } else {
                System.out.println("(DEsBUG) URL is not valid");
                vMenu.showErrorMsg("La URL no es válida");
            }

            System.out.println("(DEBUG) URL introducida: " + vMenu.getUrl());
        }
    }

    private boolean isValidURL(String url) {
        try {
            new java.net.URL(url).toURI();
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private void openWord() {

        try {
            Process miProceso = new ProcessBuilder("C:\\Program Files\\Microsoft Office\\root\\Office16\\WINWORD.EXE").start();
            System.out.println("Se ejecuta Word");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al abrir Word");
        }
    }

    private void openPowerP() {

        try {
            Process miProceso = new ProcessBuilder("C:\\Program Files\\Microsoft Office\\root\\Office16\\POWERPNT.EXE").start();
            System.out.println("Se ejecuta power Point");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al abrir PowerPoint");
        }


    }

    private void openExcel() {

        try {
            Process miProceso = new ProcessBuilder("C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.exe").start();
            System.out.println("Se ejecuta excel");

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error al abrir Excel");
        }

    }


}
