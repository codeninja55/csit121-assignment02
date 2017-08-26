package cn55.view;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class PurchasesPanel extends JPanel {
    private JPanel purchasesPanel;
    private PurchaseToolbar toolbar;

    public PurchasesPanel() {
        purchasesPanel = new JPanel();
        toolbar = new PurchaseToolbar();

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);
        add(purchasesPanel, BorderLayout.CENTER);

    }
}
