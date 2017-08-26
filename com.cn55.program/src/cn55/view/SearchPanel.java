package cn55.view;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchPanel extends JPanel {

    public SearchPanel() {
        JTextField searchIDTextField = new JTextField(15);
        JButton searchBtn = new JButton("Search");
        JLabel searchLabel = new JLabel("Search by Card ID");

        Dimension dim = getPreferredSize();
        dim.width = 720;
        setPreferredSize(dim);
        setLayout(new GridBagLayout());

        Border innerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Color.BLACK),
                "Search Cards",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,22));
        Border outerBorder = BorderFactory.createEmptyBorder(5,10,10,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        GridBagConstraints gc = new GridBagConstraints();

        gc.gridx = 0;
        gc.gridy = 0;
        gc.weightx = 1;
        gc.weighty = 0.1;
        gc.fill = GridBagConstraints.LINE_END;
        gc.anchor = GridBagConstraints.CENTER;
        gc.insets = new Insets(20,10,10,20);

        searchLabel.setFont(new Font("Verdana", Font.BOLD, 24));
        add(searchLabel, gc);

        gc.gridx = 1;
        gc.gridy = 0;
        gc.anchor = GridBagConstraints.LINE_START;
        gc.insets = new Insets(20,0,10,0);
        Dimension textFieldDim = getPreferredSize();
        textFieldDim.width = 350;
        textFieldDim.height = 40;
        searchIDTextField.setPreferredSize(textFieldDim);
        add(searchIDTextField, gc);

        gc.gridx = 0;
        gc.gridy = 2;
        gc.weightx = 1;
        gc.weighty = 3;
        gc.anchor = GridBagConstraints.FIRST_LINE_END;
        gc.insets = new Insets(20,10,10,20);
        searchBtn.setFont(new Font("Verdana", Font.BOLD, 24));
        add(searchBtn, gc);

        /* Register listener, handle the event, and raise an Event object
        * to pass the source to that SearchEvent object that then passes
        *  that event to MainFrame controller to handle */
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String searchID = searchIDTextField.getText();

                SearchEvent event = new SearchEvent(this, searchID);
            }
        });
    }
}
