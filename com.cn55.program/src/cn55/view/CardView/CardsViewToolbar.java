package cn55.view.CardView;

import cn55.model.SortCardType;
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

public class CardsViewToolbar extends JPanel {

    private JPanel leftToolbar;
    private JPanel rightToolbar;
    private ToolbarButton createCardBtn;
    private ToolbarButton deleteCardBtn;
    private JComboBox<String> sortedCombo;
    private ToolbarButton searchBtn;

    /*============================== CONSTRUCTORS  ==============================*/
    CardsViewToolbar() {
        JPanel leftToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        JPanel rightToolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        searchBtn = new ToolbarButton("Search");
        searchBtn.setName("CardsViewSearchButton");
        createCardBtn = new ToolbarButton("Add Card");
        createCardBtn.setName("CardsViewCreateButton");
        deleteCardBtn = new ToolbarButton("Delete Card");
        deleteCardBtn.setName("CardsViewDeleteButton");

        setName("CardsViewToolbar");
        setLayout(new GridLayout(1,2));
        setBorder(Style.toolbarBorder("Actions"));

        String[] sortOptions = {"Sort..",
                SortCardType.CreatedOrder.getName(),
                SortCardType.Name.getName(),
                SortCardType.Points.getName()};
        sortedCombo = new JComboBox<>(sortOptions);
        sortedCombo.setSize(searchBtn.getPreferredSize());
        sortedCombo.setFont(Style.toolbarButtonFont());
        sortedCombo.setBackground(Style.grey50());
        sortedCombo.setForeground(Style.red500());
        sortedCombo.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Style.red900()));
        sortedCombo.setSelectedIndex(0);

        leftToolbar.add(searchBtn);
        leftToolbar.add(createCardBtn);
        leftToolbar.add(deleteCardBtn);
        rightToolbar.add(sortedCombo);
        add(leftToolbar);
        add(rightToolbar);

    }

    /*============================== MUTATORS ==============================*/

    /*============================== ACCESSORS ==============================*/
    public JPanel getLeftToolbar() {
        return leftToolbar;
    }

    public JPanel getRightToolbar() {
        return rightToolbar;
    }

    public JComboBox<String> getSortedCombo() {
        return sortedCombo;
    }

    ToolbarButton getCreateCardBtn() {
        return createCardBtn;
    }

    ToolbarButton getDeleteCardBtn() {
        return deleteCardBtn;
    }

    ToolbarButton getSearchBtn() {
        return searchBtn;
    }
}