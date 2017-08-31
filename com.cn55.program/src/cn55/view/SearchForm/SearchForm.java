package cn55.view.SearchForm;

import cn55.view.ButtonListener;
import cn55.view.CustomComponents.*;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SearchForm extends JPanel {

    private JPanel searchForm;
    private FormLabel searchLabel;
    private FormTextField searchIDTextField;
    private ErrorLabel errorLabel;
    private ErrorLabel ruleErrLabel;
    private FormButton searchBtn;
    private SearchListener searchListener;
    private ButtonListener cancelListener;

    public SearchForm() {
        /* NOTE: searchForm is the form Container within*/
        searchForm = new JPanel(new GridBagLayout());
        JButton cancelBtn = new JButton("Cancel Search");
        searchLabel = new FormLabel("Search by Card ID");
        searchLabel.setVisible(true);
        searchIDTextField = new FormTextField(20);
        searchIDTextField.setVisible(true);
        errorLabel = new ErrorLabel("CARD DOES NOT EXIST");
        ruleErrLabel = new ErrorLabel("INVALID CARD ID NUMBER");
        searchBtn = new FormButton("Search");
        searchBtn.setVisible(true);

        setName("SearchForm");
        setLayout(new BorderLayout());
        Dimension dim = getPreferredSize();
        dim.width = 800;
        setPreferredSize(dim);
        setMinimumSize(getPreferredSize());

        /* BORDERS - Adding 3 Borders around the form */
        Border outInnerBorder = BorderFactory.createTitledBorder(
                BorderFactory.createMatteBorder(1,1,1,1,Style.red500()),
                "Search Cards",
                TitledBorder.LEFT,
                TitledBorder.CENTER,
                new Font("Verdana",Font.BOLD,24),
                Style.red500());
        Border inInnerBorder = BorderFactory.createEmptyBorder(25,25,25,25);
        Border innerBorder = BorderFactory.createCompoundBorder(outInnerBorder, inInnerBorder);
        Border outerBorder = BorderFactory.createEmptyBorder(1,10,10,10);
        setBorder(BorderFactory.createCompoundBorder(outerBorder,innerBorder));

        GridBagConstraints gc = new GridBagConstraints();

        gc.fill = GridBagConstraints.NONE;
        gc.gridx = 0; gc.gridy = 0; gc.weightx = 1; gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.PAGE_END;
        gc.insets = new Insets(20,0,10,0);
        searchForm.add(searchLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.2;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(20,0,10,0);
        Dimension textFieldDim = getPreferredSize();
        textFieldDim.width = 350;
        textFieldDim.height = 50;
        searchIDTextField.setPreferredSize(textFieldDim);
        searchForm.add(searchIDTextField, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(0,0,0,0);
        searchForm.add(errorLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 0.1;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(0,0,0,0);
        searchForm.add(ruleErrLabel, gc);

        gc.gridy++; gc.gridx = 0; gc.weightx = 1; gc.weighty = 3;
        gc.anchor = GridBagConstraints.PAGE_START;
        gc.insets = new Insets(10,0,10,0);
        searchForm.add(searchBtn, gc);

        /* Register listener, handle the event, and raise an Event object
        * to pass the source to that SearchEvent object that then passes
        *  that event to Program controller to handle */
        searchBtn.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                SearchEvent event = new SearchEvent(this, searchLabel, searchIDTextField,
                        errorLabel, ruleErrLabel);

                if (searchListener != null)
                    searchListener.searchEventOccurred(event);
            }
        });

        add(searchForm, BorderLayout.CENTER);

        cancelBtn.setFont(Style.buttonFont());
        cancelBtn.setForeground(Style.btnTextColor());
        cancelBtn.setBackground(Style.red500());
        add(cancelBtn, BorderLayout.SOUTH);

        cancelBtn.addActionListener(e -> {
            if (cancelListener != null) {
                cancelListener.buttonActionOccurred();
            }
        });

        setVisible(false);
    }

    /*============================== MUTATORS ==============================*/
    public void setSearchListener(SearchListener listener) { this.searchListener = listener; }

    public void setCancelListener(ButtonListener cancelListener) {
        this.cancelListener = cancelListener;
    }

    /*============================== ACCESSORS ==============================*/
    public JPanel getSearchForm() {
        return searchForm;
    }

    public FormLabel getSearchLabel() {
        return searchLabel;
    }

    public FormTextField getSearchIDTextField() {
        return searchIDTextField;
    }

    public FormButton getSearchBtn() {
        return searchBtn;
    }
}