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

public class PurchaseViewToolbar extends JPanel {

    private JPanel leftToolbar;
    private JPanel rightToolbar;
    private ToolbarButton addPurchaseBtn;
    private ToolbarButton deletePurchaseBtn;
    private JComboBox<String> sortPurchaseCombo;
    private ToolbarButton viewPurchaseBtn;

    private ToolbarButtonListener createPurchaseListener;
    private ToolbarButtonListener deletePurchaseListener;
    private ToolbarButtonListener viewPurchaseListener;
    private ItemListener sortPurchaseListener;

    /*============================== CONSTRUCTORS  ==============================*/
    PurchaseViewToolbar() {
        leftToolbar = new JPanel(new FlowLayout(FlowLayout.LEFT, 20, 20));
        rightToolbar = new JPanel(new FlowLayout(FlowLayout.RIGHT, 20, 20));
        addPurchaseBtn = new ToolbarButton("Add Purchase");
        addPurchaseBtn.setName("CreateButton");
        deletePurchaseBtn = new ToolbarButton("Delete Purchase");
        deletePurchaseBtn.setName("DeleteButton");
        viewPurchaseBtn = new ToolbarButton("View Details");
        viewPurchaseBtn.setName("ViewButton");
        sortPurchaseCombo = new JComboBox<>();
        DefaultComboBoxModel<String> options = new DefaultComboBoxModel<>();

        setName("PurchaseViewToolbar");
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
        rightToolbar.add(viewPurchaseBtn);
        rightToolbar.add(sortPurchaseCombo);
        add(leftToolbar);
        add(rightToolbar);

        // Registration of listeners
        ToolbarListener handler = new ToolbarListener();
        addPurchaseBtn.addActionListener(handler);
        deletePurchaseBtn.addActionListener(handler);
        viewPurchaseBtn.addActionListener(handler);
    }

    /*============================== MUTATORS  ==============================*/
    public void setCreatePurchaseListener(ToolbarButtonListener listener) {
        this.createPurchaseListener = listener;
    }

    public void setDeletePurchaseListener(ToolbarButtonListener listener) {
        this.deletePurchaseListener = listener;
    }

    public void setViewPurchaseListener(ToolbarButtonListener listener) {
        this.viewPurchaseListener = listener;
    }

    public void setSortPurchaseListener(ItemListener listener) { this.sortPurchaseListener = listener; }

    public void disableCreatePurchaseButton(boolean isDisabled) { addPurchaseBtn.setEnabled(!isDisabled); }

    /*============================== ACCESSORS  ==============================*/

    /*============================== INNER CLASS  ==============================*/
    class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addPurchaseBtn) {
                if (createPurchaseListener != null) {
                    createPurchaseListener.toolbarButtonEventOccurred();
                }
            } else if (e.getSource() == deletePurchaseBtn) {
                /* TEST CODE */
                System.err.println("Purchase View Delete Purchase");
                System.out.println("Delete Purchase Button Pressed");
            } else if (e.getSource() == viewPurchaseBtn) {
                if (viewPurchaseListener != null) {
                    viewPurchaseListener.toolbarButtonEventOccurred();
                }
            }
        }
    }
}
