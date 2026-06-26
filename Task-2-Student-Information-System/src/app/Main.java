package app;

import ui.MainFrame;

import javax.swing.*;

public class Main {

    public static void main(String[] args) {

        // Set Nimbus Look and Feel
        try {

            for (UIManager.LookAndFeelInfo info :
                    UIManager.getInstalledLookAndFeels()) {

                if ("Nimbus".equals(info.getName())) {

                    UIManager.setLookAndFeel(
                            info.getClassName());

                    break;
                }
            }

        } catch (Exception e) {

            System.out.println(
                    "Nimbus Look & Feel not available.");
        }

        SwingUtilities.invokeLater(() -> {

            new MainFrame();

        });

    }

}