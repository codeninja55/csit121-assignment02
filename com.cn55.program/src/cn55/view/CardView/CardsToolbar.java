package cn55.view.CardView;

import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class CardsToolbar extends JPanel {

    private ToolbarButton createCardBtn;
    private ToolbarButton deleteCardBtn;
    private JComboBox<String> sortedCombo;
    private ToolbarButton sortCardsBtn;
    private ToolbarButton searchBtn;

    // Constructor
    CardsToolbar() {
        searchBtn = new ToolbarButton("Search");
        searchBtn.setName("SearchButton");
        createCardBtn = new ToolbarButton("Add Card");
        createCardBtn.setName("CreateButton");
        deleteCardBtn = new ToolbarButton("Delete Card");
        deleteCardBtn.setName("DeleteButton");
        sortCardsBtn = new ToolbarButton("Sort Cards");
        sortCardsBtn.setName("SortButton");

        setName("CardToolbar");
        setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        setBorder(Style.toolbarBorder("Actions"));

        String[] sortOptions = {"Sort","Sort by Created Order", "Sort by Name", "Sort by Points"};
        sortedCombo = new JComboBox<>(sortOptions);
        sortedCombo.setFont(new Font("Verdana", Font.BOLD, 26));
        sortedCombo.setBackground(Style.grey50());
        sortedCombo.setForeground(Style.red300());
        sortedCombo.setBorder(BorderFactory.createMatteBorder(1,1,1,1, Style.red500()));
        sortedCombo.setSelectedIndex(0);

        add(searchBtn, FlowLayout.LEFT, 0);
        add(createCardBtn, FlowLayout.LEFT, 1);
        add(deleteCardBtn, FlowLayout.LEFT, 2);
        add(sortedCombo, FlowLayout.RIGHT, 3);
        add(sortCardsBtn, FlowLayout.RIGHT, 4);

        // TODO - LISTENER FOR SORTING
        sortedCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                /* THINK ABOUT HOW TO DO THIS */
            }
        });
    }

    /*============================== MUTATORS ==============================*/

    public void disableToolbarButton(String button, boolean isDisabled) {
        switch (button) {
            case "SearchButton":
                searchBtn.setEnabled(!isDisabled);
                break;
            case "CreateButton":
                createCardBtn.setEnabled(!isDisabled);
                break;
            case "DeleteButton":
                deleteCardBtn.setEnabled(!isDisabled);
                break;
        }
    }

    /*============================== ACCESSORS ==============================*/
    public JComboBox<String> getSortedCombo() {
        return sortedCombo;
    }

    ToolbarButton getCreateCardBtn() {
        return createCardBtn;
    }

    ToolbarButton getDeleteCardBtn() {
        return deleteCardBtn;
    }

    ToolbarButton getSortCardsBtn() {
        return sortCardsBtn;
    }

    ToolbarButton getSearchBtn() {
        return searchBtn;
    }
}
