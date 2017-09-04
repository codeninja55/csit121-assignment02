package cn55.view.CategoriesView;
import cn55.model.*;
import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.Toolbar;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.DeleteForm.DeleteCategoryForm;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;

public class CategoriesViewPane extends JPanel implements Observer {
    private Subject database;
    private CategoriesTableModel categoriesTableModel;
    private Toolbar toolbar;
    private JTable categoriesTablePane;

    private CategoriesForm createCategoryForm;
    private DeleteCategoryForm deleteCategoryForm;

    private ToolbarButton createCategoryBtn;
    private ToolbarButton deleteCategoryBtn;

    private ToolbarButtonListener createCategoryListener;
    private ToolbarButtonListener deleteCategoryListener;

    /*============================== CONSTRUCTORS  ==============================*/
    public CategoriesViewPane() {
        toolbar = new Toolbar();
        createCategoryBtn = new ToolbarButton("Create Category");
        deleteCategoryBtn = new ToolbarButton("Delete Category");

        categoriesTableModel = new CategoriesTableModel();
        categoriesTablePane = new JTable();
        JScrollPane tableScrollPane = new JScrollPane(categoriesTablePane);
        tableScrollPane.setName("CategoriesViewTableScrollPane");

        setName("CategoriesViewPane");
        setLayout(new BorderLayout());

        /* TOOLBAR */
        toolbar.getLeftToolbar().add(createCategoryBtn);
        toolbar.getLeftToolbar().add(deleteCategoryBtn);
        add(toolbar, BorderLayout.NORTH);

        add(tableScrollPane, BorderLayout.CENTER);

        /* REGISTRATION OF TOOLBAR BUTTON LISTENERS */
        ToolbarListener handler = new ToolbarListener();
        createCategoryBtn.addActionListener(handler);
        deleteCategoryBtn.addActionListener(handler);

    }

    /*============================== MUTATORS  ==============================*/
    public void setCreateCategoryListener(ToolbarButtonListener listener) {
        this.createCategoryListener = listener;
    }

    public void setDeleteCategoryListener(ToolbarButtonListener listener) {
        this.deleteCategoryListener = listener;
    }

    public void setCategoriesTableModel() {
        categoriesTablePane.setModel(categoriesTableModel);
        categoriesTablePane.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tableFormatter();
        this.revalidate();
        this.repaint();
    }

    private void tableFormatter() {
        categoriesTablePane.setRowHeight(45);
        categoriesTablePane.setFont(Style.tableDataFont());
        categoriesTablePane.getColumnModel().getColumn(0).setCellRenderer(Style.centerRenderer());
        categoriesTablePane.getColumnModel().getColumn(1).setCellRenderer(Style.centerRenderer());
        categoriesTablePane.getColumnModel().getColumn(2).setCellRenderer(Style.leftRenderer());
        categoriesTablePane.getColumnModel().getColumn(3).setCellRenderer(Style.rightRenderer());
    }

    public void setCreateCategoryForm(CategoriesForm createCategoryForm) {
        this.createCategoryForm = createCategoryForm;
    }

    public void setDeleteCategoryForm(DeleteCategoryForm deleteCategoryForm) {
        this.deleteCategoryForm = deleteCategoryForm;
    }

    /* OBSERVER DESIGN PATTERN IMPLEMENTATION */
    @Override
    public void setSubject(Subject subject) {
        this.database = subject;
    }

    @Override
    public void update() {
        categoriesTableModel.setData(database.getCategoriesUpdate(this));
        categoriesTableModel.fireTableDataChanged();
    }

    /*============================== ACCESSORS  ==============================*/
    public ToolbarButton getCreateCategoryBtn() {
        return createCategoryBtn;
    }

    public ToolbarButton getDeleteCategoryBtn() {
        return deleteCategoryBtn;
    }

    public CategoriesForm getCreateCategoryForm() {
        return createCategoryForm;
    }

    public DeleteCategoryForm getDeleteCategoryForm() {
        return deleteCategoryForm;
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/

    /*=========================== TOOLBAR LISTENER ============+===============*/
    class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createCategoryBtn) {
                if (createCategoryListener != null)
                    createCategoryListener.toolbarButtonEventOccurred();
            } else if (e.getSource() == deleteCategoryBtn) {
                if (deleteCategoryListener != null) {
                    deleteCategoryListener.toolbarButtonEventOccurred();
                }
            }
        }
    }

    /*========================== CategoriesTableModel =========================*/
    public class CategoriesTableModel extends AbstractTableModel {

        private ArrayList<Category> categories;
        private String[] tableHeaders = {"ID", "Name", "Description", "Total Amount"};

        void setData(ArrayList<Category> categories) {
            Collections.sort(categories);
            this.categories = categories;
        }

        public String getColumnName(int column) { return tableHeaders[column]; }

        public int getRowCount() {
            return categories.size();
        }

        public int getColumnCount() {
            return tableHeaders.length;
        }

        public Object getValueAt(int rowIndex, int columnIndex) {
            Category category = categories.get(rowIndex);

            switch (columnIndex) {
                case 0:
                    return category.getId();
                case 1:
                    return category.getName();
                case 2:
                    return category.getDescription();
                case 3:
                    return Database.getCategoriesTotalMap().get(category.getId());
            }
            return null;
        }
    }

}
