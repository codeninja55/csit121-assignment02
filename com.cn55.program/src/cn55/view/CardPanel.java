package cn55.view;
import javax.swing.*;
import java.awt.*;

public class CardPanel extends JPanel {

    private JPanel cardPanel;
    private CardsToolbar toolbar;

    public CardPanel() {
        cardPanel = new JPanel();
        toolbar = new CardsToolbar();

        setLayout(new BorderLayout());

        add(toolbar, BorderLayout.NORTH);
        add(cardPanel, BorderLayout.CENTER);

        //setBorder(BorderFactory.createEtchedBorder());
    }
}
