package cn55.controller;

import cn55.view.CustomComponents.Style;

import javax.swing.*;

/*
 * @author Dinh Che
 * Student Number: 5721970
 * Email: dbac496@uowmail.edu.au
 */

public class Assignment2 {

    public static void main(String[] args) {

        /*try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }*/

        UIManager.put("TabbedPane.selected", Style.blueGrey500());
        UIManager.put("TabbedPane.selectedForeground", Style.red500());

        /*// Looping through CardType Enum
        for (CardType item : CardType.values()) {
            System.out.println(item.getId() + " : " + item.getName());
        }*/

        new Program();

    }

}
