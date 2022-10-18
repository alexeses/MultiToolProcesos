package com.github.uem.control;

import com.github.uem.gui.VMenu;
import com.github.uem.lang.Messages;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CMultiTool implements ActionListener {

    private VMenu vMenu;

    public CMultiTool(VMenu vMenu){
        this.vMenu = vMenu;

    }

    public void actionPerformed(ActionEvent e){

        if (e.getSource() instanceof JButton){
            // Test comments
            if (e.getActionCommand().equals(Messages.BT_EXCEL)) {
                System.out.printf("Estas pulsando el boton excel");
                openExcel();
            } else if (e.getActionCommand().equals(Messages.BTN_POWERP)) {
                System.out.printf("Power Point");
                System.out.println("asd");
                openPoweP();
            } else if (e.getActionCommand().equals(Messages.BTN_WORD)) {
                System.out.printf("Word");
                openWord();
            } else if (e.getActionCommand().equals(Messages.BTN_NAVEGAR)) {
                System.out.printf("Navegar");
                navigate();
            }
        }
    }

    private void navigate() {
    }

    private void openWord() {
    }

    private void openPoweP() {


    }

    private void openExcel() {

        try {
            Process miProceso = new ProcessBuilder("C:\\Program Files\\Microsoft Office\\root\\Office16\\EXCEL.EXE").start();
            System.out.println("Se ejecuta excel");

        } catch (IOException e) {
            e.printStackTrace();
        }

    }


}
