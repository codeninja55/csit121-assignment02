import javax.swing.*;
import java.awt.*;

public class PurchasesPanel extends JPanel {
    private JPanel purchasesPanel;

    public PurchasesPanel() {
        purchasesPanel = new JPanel();

        setLayout(new BorderLayout());

        add(purchasesPanel, BorderLayout.CENTER);

    }
}
