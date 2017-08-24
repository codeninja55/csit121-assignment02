import javax.swing.*;
import java.awt.*;

public class SummaryPanel extends JPanel {
    private JPanel summaryPanel;

    public SummaryPanel() {
        summaryPanel = new JPanel();

        setLayout(new BorderLayout());

        add(summaryPanel, BorderLayout.CENTER);

    }
}
