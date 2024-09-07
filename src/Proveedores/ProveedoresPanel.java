package Proveedores;

import Utilidades.SetterStatement;
import Utilidades.UtilidadesBD;
import Utilidades.UtilidadesTablas;

public class ProveedoresPanel extends javax.swing.JPanel {

    public ProveedoresPanel() {
        initComponents();
        actualizarTabla();
        conectarListenerTabla();
    }
    
    private void actualizarTabla() {
        String[] columnas = {"ID", "Nombre", "Tipo de documento", "Numero documento", "Telefono"};
        String sql = "SELECT * FROM proveedor";
        Class[] tiposDatos = {Integer.class, String.class, String.class, String.class, String.class};
        UtilidadesTablas.mostrarTabla(columnas, sql, tabla, tiposDatos);
    }

    private void eliminarProveedor() {
        int fila = tabla.getSelectedRow();
        int id = ((int) tabla.getValueAt(fila, 0));
        String deleteSql = "DELETE FROM proveedor WHERE id_proveedor = ?";
        SetterStatement setterStatementDelete = (stm) -> stm.setInt(1, id);
        UtilidadesBD.sql(deleteSql, setterStatementDelete);
        actualizarTabla();
        limpiarCampos();
    }
    
    private void guardarProveedor(String nombre, String tipoDocumento, String numDocumento, String telefono) {
        String sql = "INSERT INTO proveedor (nombre, tipo_documento, numero_documento, telefono) VALUES (?,?,?,?)";
        SetterStatement setter = (stm) -> {
            stm.setString(1, nombre);
            stm.setString(2, tipoDocumento);
            stm.setString(3, numDocumento);
            stm.setString(4, telefono);
        };

        UtilidadesBD.sql(sql, setter);
    }

    private void modificarProveedor(String nombre, String tipoDocumento, String numDocumento, String telefono) {
        int id = ((int) tabla.getValueAt(tabla.getSelectedRow(), 0));
        String sql = "UPDATE proveedor SET nombre = ?, tipo_documento = ?, numero_documento = ?, telefono = ? WHERE id_proveedor = ?";
        SetterStatement setter = (stm) -> {
            stm.setString(2, tipoDocumento);
            stm.setString(1, nombre);
            stm.setString(3, numDocumento);
            stm.setString(4, telefono);
            stm.setInt(5, id);
        };
        UtilidadesBD.sql(sql, setter);
    }

    private void limpiarCampos() {
        nombreTxt.setText("");
        comboTipoDocumento.setSelectedIndex(0);
        numeroDocumentoTxt.setText("");
        telefonoTxt.setText("");

        tabla.clearSelection();
        eliminarBtn.setEnabled(false);
    }

    private void conectarListenerTabla() {
        tabla.getSelectionModel().addListSelectionListener((model) -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                eliminarBtn.setEnabled(true);

                nombreTxt.setText((String) tabla.getValueAt(fila, 1));
                comboTipoDocumento.setSelectedItem(tabla.getValueAt(fila, 2));
                numeroDocumentoTxt.setText((String) tabla.getValueAt(fila, 3));
                telefonoTxt.setText((String) tabla.getValueAt(fila, 4));
            } else {
                eliminarBtn.setEnabled(false);
            }
        });
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        comboTipoDocumento = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        numeroDocumentoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        telefonoTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        limpiarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("PROVEEDORES");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar datos de proveedor"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre - Razon Social:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Tipo de documento: ");

        comboTipoDocumento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Cédula", "NIT", "Otro" }));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Numero de documento:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Telefono:");

        guardarBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarBtn.setText("Guardar proveedor");
        guardarBtn.setIconTextGap(10);
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        limpiarBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        limpiarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N
        limpiarBtn.setText("Limpiar campos");
        limpiarBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        limpiarBtn.setIconTextGap(10);
        limpiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        eliminarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarBtn.setText("Eliminar dato seleccionado");
        eliminarBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eliminarBtn.setEnabled(false);
        eliminarBtn.setIconTextGap(10);
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreTxt)
                    .addComponent(numeroDocumentoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(telefonoTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(comboTipoDocumento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(limpiarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(nombreTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comboTipoDocumento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(12, 12, 12)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(numeroDocumentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpiarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(tabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 565, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(12, 12, 12))
        );
    }// </editor-fold>//GEN-END:initComponents

  private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
      String nombre = nombreTxt.getText(),
              tipoDocumento = ((String) comboTipoDocumento.getSelectedItem()),
              numDocumento = numeroDocumentoTxt.getText(),
              telefono = telefonoTxt.getText();

      // si hay un dato seleccionado en la tabla, modificar, caso contrario, guardar uno nuevo
      if (tabla.getSelectedRow() != -1) {
          modificarProveedor(nombre, tipoDocumento, numDocumento, telefono);
      } else {
          guardarProveedor(nombre, tipoDocumento, numDocumento, telefono);
      }

      actualizarTabla();
      limpiarCampos();
  }//GEN-LAST:event_guardarBtnActionPerformed

  private void limpiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBtnActionPerformed
      limpiarCampos();
  }//GEN-LAST:event_limpiarBtnActionPerformed

  private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
      eliminarProveedor();
  }//GEN-LAST:event_eliminarBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> comboTipoDocumento;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarBtn;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTextField numeroDocumentoTxt;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField telefonoTxt;
    // End of variables declaration//GEN-END:variables
}
