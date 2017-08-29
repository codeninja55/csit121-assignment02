package cn55.view.PurchaseView;

import cn55.view.CustomComponents.Style;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PurchaseToolbar extends JPanel {

    private JButton addPurchaseBtn;
    private JButton deletePurchaseBtn;
    private ToolbarButtonListener createPurchaseListener;
    private ToolbarButtonListener deletePurchaseListener;

    // Constructor
    PurchaseToolbar() {
        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Actions",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                Style.titledBorderFont());
        Border outerBorder = BorderFactory.createEmptyBorder(20,10,20,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder, innerBorder));

        Font btnFont = Style.buttonFont();
        Color btnColor = Style.btnColor();
        Color textColor = Style.btnTextColor();
        addPurchaseBtn = new JButton("Add Purchase");
        addPurchaseBtn.setFont(btnFont);
        addPurchaseBtn.setForeground(textColor);
        addPurchaseBtn.setBackground(btnColor);
        deletePurchaseBtn = new JButton("Delete Purchase");
        deletePurchaseBtn.setFont(btnFont);
        deletePurchaseBtn.setForeground(textColor);
        deletePurchaseBtn.setBackground(btnColor);

        setLayout(new FlowLayout(FlowLayout.LEFT));

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

                // THIS EVENT SHOULD OCCUR INSIDE THE FORM TO CAPTURE
                // INPUTS FROM USER IN THE EVENT OBJECT TO SEND BACK
                // TO MAINFRAME
                //PurchaseEvent event = new PurchaseEvent(this);

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