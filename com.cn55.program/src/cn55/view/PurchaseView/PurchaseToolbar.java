package cn55.view.PurchaseView;

import cn55.model.SortPurchaseType;
import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class PurchaseToolbar extends JPanel {

    JPanel leftToolbar;
    JPanel rightToolbar;
    private ToolbarButton addPurchaseBtn;
    private ToolbarButton deletePurchaseBtn;
    private JComboBox<String> sortPurchaseCombo;

    private ToolbarButtonListener createPurchaseListener;
    private ToolbarButtonListener deletePurchaseListener;
    private ItemListener sortPurchaseListener;

    /*============================== CONSTRUCTORS  ==============================*/
    PurchaseToolbar() {
        leftToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        rightToolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        addPurchaseBtn = new ToolbarButton("Add Purchase");
        addPurchaseBtn.setName("CreateButton");
        deletePurchaseBtn = new ToolbarButton("Delete Purchase");
        deletePurchaseBtn.setName("DeleteButton");
        sortPurchaseCombo = new JComboBox<>();
        DefaultComboBoxModel<String> options = new DefaultComboBoxModel<>();

        setName("PurchaseToolbar");
        setBorder(Style.toolbarBorder("Actions"));
        setLayout(new GridLayout(1,2));

        /* Sort Purchases Combo Setup */
        options.addElement(SortPurchaseType.All.getName());
        options.addElement(SortPurchaseType.Card.getName());
        options.addElement(SortPurchaseType.Cash.getName());
        sortPurchaseCombo.setModel(options);

        sortPurchaseCombo.setSize(addPurchaseBtn.getPreferredSize());
        sortPurchaseCombo.setFont(Style.toolbarButtonFont());
        sortPurchaseCombo.setBackground(Style.grey50());
        sortPurchaseCombo.setForeground(Style.red500());
        sortPurchaseCombo.setBorder(BorderFactory.createMatteBorder(2,2,2,2, Style.red900()));
        sortPurchaseCombo.setSelectedIndex(0);

        sortPurchaseCombo.addItemListener(new ItemListener() {
            public void itemStateChanged(ItemEvent e) {
                if (sortPurchaseListener != null) {
                    sortPurchaseListener.itemStateChanged(e);
                }
            }
        });

        leftToolbar.add(addPurchaseBtn);
        //add(deletePurchaseBtn);
        rightToolbar.add(sortPurchaseCombo);
        add(leftToolbar);
        add(rightToolbar);

        // Registration of listeners
        ToolbarListener handler = new ToolbarListener();
        addPurchaseBtn.addActionListener(handler);
        deletePurchaseBtn.addActionListener(handler);
    }

    /*============================== MUTATORS  ==============================*/
    public void setCreatePurchaseListener(ToolbarButtonListener listener) {
        this.createPurchaseListener = listener;
    }

    public void setDeletePurchaseListener(ToolbarButtonListener listener) {
        this.deletePurchaseListener = listener;
    }

    public void setSortPurchaseListener(ItemListener listener) { this.sortPurchaseListener = listener; }

    public void disableCreatePurchaseButton(boolean isDisabled) { addPurchaseBtn.setEnabled(!isDisabled); }

    /*============================== ACCESSORS  ==============================*/
    public JPanel getLeftToolbar() {
        return leftToolbar;
    }

    public JPanel getRightToolbar() {
        return rightToolbar;
    }

    /*============================== INNER CLASS  ==============================*/
    class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addPurchaseBtn) {
                if (createPurchaseListener != null) {
                    createPurchaseListener.toolbarButtonEventOccurred();
                }
            } /*else if (e.getSource() == deletePurchaseBtn) {
                if (deleteCardListener != null) {
                    deleteCardListener.formAction();
                }

                System.out.println("Delete Purchase Button Pressed");
            }*/
        }
    }
}
