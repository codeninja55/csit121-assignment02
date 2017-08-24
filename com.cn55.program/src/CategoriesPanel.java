import javax.swing.*;
import java.awt.*;

public class CategoriesPanel extends JPanel {
    private JPanel categoriesPanel;

    public CategoriesPanel() {

        categoriesPanel = new JPanel();

        setLayout(new BorderLayout());

        add(categoriesPanel, BorderLayout.CENTER);

    }
}
