package cn55.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class PurchaseToolbar extends JPanel {

    private JButton addPurchaseBtn;
    private JButton deletePurchaseBtn;
    private FormListener createPurchaseListener;
    private FormListener deletePurchaseListener;

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
        ButtonListener handler = new ButtonListener();
        addPurchaseBtn.addActionListener(handler);
        deletePurchaseBtn.addActionListener(handler);
    }

    void setCreatePurchaseListener(FormListener listener) {
        this.createPurchaseListener = listener;
    }

    void setDeletePurchaseListener(FormListener listener) {
        this.deletePurchaseListener = listener;
    }

    public class ButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == addPurchaseBtn) {
                /*if (createCardListener != null) {
                    createCardListener.cardFormActionOccurred();
                }*/
            } else if (e.getSource() == deletePurchaseBtn) {
                /*if (deleteCardListener != null) {
                    deleteCardListener.cardFormActionOccurred();
                }*/
            }
        }
    }
}
