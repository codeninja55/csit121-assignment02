package cn55;

import cn55.controller.Program;
import cn55.view.CustomComponents.Style;

import javax.swing.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

/* LIST OF TASKS STILL TO DO */
// TODO - Create Card Form and Event Handler
// TODO - Add a View button to display card
// TODO - Error checking for creating card and display of error information
// TODO - Refactor CardView and PurchaseView to use the Toolbar custom component
// TODO - Refactor CardView and PurchaseView to include all listeners in the CardViewPane and PurchaseViewPane
// NOTE: This is to be done with a ToolbarListener inner class


public class Assignment2 {

    public static void main(String[] args) {

        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        UIManager.put("TabbedPane.selected", Style.red500());
        UIManager.put("TabbedPane.selectedForeground", Style.blueGrey500());

        UIManager.put("ComboBox.buttonBackground", Style.red500());
        UIManager.put("ComboBox.buttonHighlight", Style.grey50());
        UIManager.put("ComboBox.selectionBackground", Style.red300());
        UIManager.put("ComboBox.selectionForeground", Style.grey50());

        UIManager.put("Button.background", Style.red900());
        UIManager.put("Button.foreground", Style.grey50());
        UIManager.put("Button.font", Style.buttonFont());

        UIManager.put("TextField.selectionBackground", Style.grey500());
        UIManager.put("TextField.font", Style.textFieldFont());
        UIManager.put("TextField.caretForeground", Style.red500());

        UIManager.put("OptionPane.buttonFont", Style.buttonFont());
        UIManager.put("OptionPane.messageFont", Style.textPaneFont());
        UIManager.put("OptionPane.messageForeground", Style.red900());

        UIManager.put("Table.selectionBackground", Style.red300());
        UIManager.put("TableHeader.font", Style.buttonFont());
        UIManager.put("TableHeader.background", Style.red500());
        UIManager.put("TableHeader.foreground", Style.grey50());

        UIManager.put("ScrollBar.background", Style.red500());
        UIManager.put("ScrollBar.foreground", Style.red500());
        UIManager.put("ScrollBar.track", Style.red500());

        /* Create and display the Program the safe Java way */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Program();
            }
        });


    }

}
