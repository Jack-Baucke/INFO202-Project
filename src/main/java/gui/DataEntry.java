/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import com.sun.jndi.toolkit.corba.CorbaUtils;
import dao.DAO;
import dao.DAOException;
import dao.DatabaseManager;
import domain.Product;
import gui.helpers.SimpleListModel;
import gui.helpers.ValidationHelper;
import java.math.BigDecimal;
import javax.swing.JOptionPane;

/**
 *
 * @author bauja773
 */
public class DataEntry extends javax.swing.JDialog {

    private DatabaseManager dataAccess = new DatabaseManager();
    private Product product = new Product();
    private ValidationHelper helper = new ValidationHelper();
    private SimpleListModel myModel = new SimpleListModel();

    /**
     * Creates new form DataEntry
     */
    public DataEntry(java.awt.Window parent, boolean modal) {
        super(parent);
        super.setModal(modal);
        initComponents();
        helper.addTypeFormatter(txtPrice, "#0.00", BigDecimal.class);
        helper.addTypeFormatter(txtQuantity, "#", Integer.class);
        myModel.updateItems(dataAccess.getCategories());
        comboBoxCategory.setModel(myModel);
    }

    public DataEntry(java.awt.Window parent, boolean modal, Product product) {
        // call other constructor
        this(parent, modal);
        // assign the product that we are editing to the product field
        this.product = product;
        txtID.setText(product.getProductID());
        txtID.setEditable(false);
        txtName.setText(product.getName());
        txtPrice.setValue(product.getListPrice());
        txtQuantity.setValue(product.getQuantity());
        textAreaDescription.setText(product.getDescription());
        comboBoxCategory.setSelectedItem(product.getCategory());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        button1 = new java.awt.Button();
        labelID = new javax.swing.JLabel();
        txtID = new javax.swing.JTextField();
        labelName = new javax.swing.JLabel();
        txtName = new javax.swing.JTextField();
        labelDescription = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        textAreaDescription = new javax.swing.JTextArea();
        labelCategory = new javax.swing.JLabel();
        comboBoxCategory = new javax.swing.JComboBox<>();
        labelPrice = new javax.swing.JLabel();
        labelQuantity = new javax.swing.JLabel();
        buttonSave = new javax.swing.JButton();
        buttonCancel = new javax.swing.JButton();
        labelTitle = new javax.swing.JLabel();
        txtPrice = new javax.swing.JFormattedTextField();
        txtQuantity = new javax.swing.JFormattedTextField();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        button1.setLabel("button1");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        labelID.setText("ID:");

        labelName.setText("Name:");

        txtName.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNameActionPerformed(evt);
            }
        });

        labelDescription.setText("Description:");

        textAreaDescription.setColumns(20);
        textAreaDescription.setRows(5);
        jScrollPane1.setViewportView(textAreaDescription);

        labelCategory.setText("Category:");

        comboBoxCategory.setEditable(true);

        labelPrice.setText("Price");

        labelQuantity.setText("Quantity in Stock:");

        buttonSave.setText("Save");
        buttonSave.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonSaveActionPerformed(evt);
            }
        });

        buttonCancel.setText("Cancel");
        buttonCancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonCancelActionPerformed(evt);
            }
        });

        labelTitle.setText("Product Editor");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelID, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelName, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelDescription, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelCategory, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelPrice, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(labelQuantity, javax.swing.GroupLayout.Alignment.TRAILING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addGap(15, 15, 15))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(txtQuantity, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtPrice, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtID, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(comboBoxCategory, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(buttonSave, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonCancel, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(labelTitle)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelID)
                    .addComponent(txtID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelName)
                    .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelDescription)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelCategory)
                    .addComponent(comboBoxCategory, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(labelPrice)
                    .addComponent(txtPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelQuantity)
                    .addComponent(txtQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(buttonSave)
                    .addComponent(buttonCancel))
                .addGap(15, 15, 15))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtNameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNameActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtNameActionPerformed

    private void buttonCancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonCancelActionPerformed
        // TODO add your handling code here:
        dispose();
    }//GEN-LAST:event_buttonCancelActionPerformed

    private void buttonSaveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonSaveActionPerformed
        try {
            if (dataAccess.search(txtID.getText()) != null) {
                JOptionPane.showMessageDialog(this, "Can't have two products with the same ID", "Warning", JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            String id = txtID.getText();
            String name = txtName.getText();
            String description = textAreaDescription.getText();
            Object category = comboBoxCategory.getSelectedItem();
            String catString = (String) category;
            BigDecimal price = (BigDecimal) txtPrice.getValue();
            Integer quant = (Integer) txtQuantity.getValue();

            product.setProductID(id);
            product.setName(name);
            product.setCategory(catString);
            product.setDescription(description);
            product.setListPrice(price);
            product.setQuantity(quant);

            if (helper.isObjectValid(product)) {
                dataAccess.saveProduct(this.product);
                dispose();
            }
        } catch (DAOException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage(), "Warning", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_buttonSaveActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DataEntry.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                DataEntry dialog = new DataEntry(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private java.awt.Button button1;
    private javax.swing.JButton buttonCancel;
    private javax.swing.JButton buttonSave;
    private javax.swing.JComboBox<String> comboBoxCategory;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel labelCategory;
    private javax.swing.JLabel labelDescription;
    private javax.swing.JLabel labelID;
    private javax.swing.JLabel labelName;
    private javax.swing.JLabel labelPrice;
    private javax.swing.JLabel labelQuantity;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextArea textAreaDescription;
    private javax.swing.JTextField txtID;
    private javax.swing.JTextField txtName;
    private javax.swing.JFormattedTextField txtPrice;
    private javax.swing.JFormattedTextField txtQuantity;
    // End of variables declaration//GEN-END:variables
}
