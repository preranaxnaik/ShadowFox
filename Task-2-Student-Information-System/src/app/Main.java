package app;

import ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class Main {

    public static void main(String[] args) {

        try {

            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }

            // Global Fonts
            Font font = new Font("Segoe UI", Font.PLAIN, 16);

            UIManager.put("Button.font", font);
            UIManager.put("Label.font", font);
            UIManager.put("TextField.font", font);
            UIManager.put("ComboBox.font", font);
            UIManager.put("Table.font", font);
            UIManager.put("TableHeader.font",
                    new Font("Segoe UI", Font.BOLD, 14));
            UIManager.put("Menu.font", font);
            UIManager.put("MenuItem.font", font);

        } catch (Exception e) {

            System.out.println("Nimbus Look & Feel not available.");

        }

        SwingUtilities.invokeLater(MainFrame::new);
    }
}