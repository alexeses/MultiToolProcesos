package com.github.uem;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.github.uem.control.CMultiTool;
import com.github.uem.gui.VMenu;

import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatIntelliJLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        EventQueue.invokeLater(() -> {
            VMenu vM = new VMenu();

            CMultiTool controller = new CMultiTool(vM);

            vM.setControlled(controller);
            vM.setVisible(true);

        });
    }
}