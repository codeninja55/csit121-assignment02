package cn55.view.PurchaseView;

import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseToolbar extends JPanel {

    private ToolbarButton addPurchaseBtn;
    private ToolbarButton deletePurchaseBtn;
    private ToolbarButtonListener createPurchaseListener;
    private ToolbarButtonListener deletePurchaseListener;

    // Constructor
    PurchaseToolbar() {
        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(4,4,4,4,Style.blueGrey800()),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.ABOVE_TOP,
                Style.titledBorderFont(),
                Style.blueGrey800());
        Border outerBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        Font btnFont = Style.buttonFont();
        Color btnColor = Style.red500();
        Color textColor = Style.btnTextColor();
        addPurchaseBtn = new ToolbarButton("Add Purchase");
        addPurchaseBtn.setFont(btnFont);
        addPurchaseBtn.setForeground(textColor);
        addPurchaseBtn.setBackground(btnColor);
        deletePurchaseBtn = new ToolbarButton("Delete Purchase");
        deletePurchaseBtn.setFont(btnFont);
        deletePurchaseBtn.setForeground(textColor);
        deletePurchaseBtn.setBackground(btnColor);

        setLayout(new FlowLayout(FlowLayout.LEFT, 20, 20));

        add(addPurchaseBtn);
        add(deletePurchaseBtn);

        // Registration of listeners
        ToolbarListener handler = new ToolbarListener();
        addPurchaseBtn.addActionListener(handler);
        deletePurchaseBtn.addActionListener(handler);
    }

    public void setCreatePurchaseListener(ToolbarButtonListener listener) {
        this.createPurchaseListener = listener;
    }

    public void setDeletePurchaseListener(ToolbarButtonListener listener) {
        this.deletePurchaseListener = listener;
    }

    public void disableCreatePurchaseButton(boolean isDisabled) { addPurchaseBtn.setEnabled(!isDisabled); }

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
