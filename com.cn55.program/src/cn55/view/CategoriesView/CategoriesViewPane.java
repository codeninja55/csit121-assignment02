package cn55.view.CategoriesView;
import cn55.model.Category;
import cn55.model.Database;
import cn55.model.Purchase;
import cn55.view.CustomComponents.Style;
import cn55.view.CustomComponents.Toolbar;
import cn55.view.CustomComponents.ToolbarButton;
import cn55.view.ToolbarButtonListener;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class CategoriesViewPane extends JPanel {
    private CategoriesTableModel categoriesTableModel;
    private Toolbar toolbar;
    private JTable categoriesTablePane;

    //private CreateCategoryForm createCategoryForm;
    //private DeleteCategoryForm deleteCategoryForm;

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
        categoriesTablePane = new JTable(categoriesTableModel);
        JScrollPane tableScrollPane = new JScrollPane(categoriesTablePane);
        tableScrollPane.setName("CategoriesViewTableScrollPane");

        setName("CategoriesViewPane");
        setLayout(new BorderLayout());

        /* TOOLBAR */
        toolbar.getLeftToolbar().add(createCategoryBtn);
        toolbar.getLeftToolbar().add(deleteCategoryBtn);
        add(toolbar, BorderLayout.NORTH);

        /* REGISTRATION OF TOOLBAR BUTTON LISTENERS */
        ToolbarListener handler = new ToolbarListener();
        createCategoryBtn.addActionListener(handler);
        deleteCategoryBtn.addActionListener(handler);

        tableFormatter();
        add(tableScrollPane, BorderLayout.CENTER);

    }

    /*============================== MUTATORS  ==============================*/
    public void setCreateCategoryListener(ToolbarButtonListener listener) {
        this.createCategoryListener = listener;
    }

    public void setDeleteCategoryListener(ToolbarButtonListener listener) {
        this.deleteCategoryListener = listener;
    }

    private void tableFormatter() {
        categoriesTablePane.setRowHeight(45);
        categoriesTablePane.setFont(Style.tableDataFont());
        categoriesTablePane.getColumnModel().getColumn(0).setCellRenderer(Style.centerRenderer());
        categoriesTablePane.getColumnModel().getColumn(1).setCellRenderer(Style.centerRenderer());
        categoriesTablePane.getColumnModel().getColumn(2).setCellRenderer(Style.leftRenderer());
        categoriesTablePane.getColumnModel().getColumn(3).setCellRenderer(Style.rightRenderer());
    }

    public void refreshCategoriesTable(ArrayList<Category> categories) {

        categoriesTableModel.setData(categories);
        categoriesTableModel.fireTableDataChanged();
    }

    /*============================== ACCESSORS  ==============================*/
    public ToolbarButton getCreateCategoryBtn() {
        return createCategoryBtn;
    }

    public ToolbarButton getDeleteCategoryBtn() {
        return deleteCategoryBtn;
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/

    /*=========================== TOOLBAR LISTENER ============+===============*/
    class ToolbarListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == createCategoryBtn) {
                System.out.println("Create Category Button Pressed");
            } else if (e.getSource() == deleteCategoryBtn) {
                System.out.println("Delete Category Button Pressed");
            }
        }
    }

    /*=========================================================================*/
    /*============================== INNER CLASS ==============================*/
    /*=========================================================================*/

    /*========================== CategoriesTableModel =========================*/
    public class CategoriesTableModel extends AbstractTableModel {

        private ArrayList<Category> categories;
        private String[] tableHeaders = {"ID", "Name", "Description", "Total Amount"};

        void setData(ArrayList<Category> categories) {
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