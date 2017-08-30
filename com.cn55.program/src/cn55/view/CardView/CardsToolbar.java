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

public class CardsToolbar extends JPanel {

    private ToolbarButton createCardBtn;
    private ToolbarButton deleteCardBtn;
    private ToolbarButton sortCardsBtn;
    //private JTextField searchField;
    private ToolbarButton searchButton;
    private ToolbarButtonListener searchCardListener;
    private ToolbarButtonListener createCardListener;
    private ToolbarButtonListener deleteCardListener;

    // Constructor
    CardsToolbar() {

        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                Style.titledBorderFont());

        Border outerBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));
        
        searchButton = new ToolbarButton("Search");
        createCardBtn = new ToolbarButton("Add Card");
        deleteCardBtn = new ToolbarButton("Delete Card");
        sortCardsBtn = new ToolbarButton("Sort Cards");

        setLayout(new FlowLayout(FlowLayout.LEFT));

        add(searchButton);
        add(createCardBtn);
        add(deleteCardBtn);

        // TODO Add a combobox for the type of sorting required
        add(sortCardsBtn);

        // Registration of listeners
        ToolbarListener handler = new ToolbarListener();
        createCardBtn.addActionListener(handler);
        deleteCardBtn.addActionListener(handler);
        sortCardsBtn.addActionListener(handler);
    }

    public void setSearchCardListener(ToolbarButtonListener listener) { this.searchCardListener = listener; }

    public void setCreateCardListener(ToolbarButtonListener listener) {
        this.createCardListener = listener;
    }

    public void setDeleteCardListener(ToolbarButtonListener listener) {
        this.deleteCardListener = listener;
    }

    public class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == searchButton) {

                // TODO - Listen on search button
                System.out.println("Toolbar Search Button Clicked");
            } else if (e.getSource() == createCardBtn) {
                //new CardForm();
                if (createCardListener != null)
                    createCardListener.toolbarButtonEventOccurred();
            } else if (e.getSource() == deleteCardBtn) {
                if (deleteCardListener != null)
                    deleteCardListener.toolbarButtonEventOccurred();
            } else if (e.getSource() == sortCardsBtn) {

                // TODO Listen on ComboBox changes
                JOptionPane.showMessageDialog(null,"Sorting Cards Selected");
            }
        }
    }
}
