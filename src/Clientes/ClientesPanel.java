package Clientes;

import Utilidades.UtilidadesBD;
import Utilidades.UtilidadesTablas;
import UtilidadesGraficas.DeleteConfirmDialog;

public class ClientesPanel extends javax.swing.JPanel {

    public ClientesPanel() {
        initComponents();
        actualizarTablaClientes();
        conectarListenerTabla();
    }

    private void actualizarTablaClientes() {
        String[] columnas = { "ID", "Nombre", "Direccion", "Telefono", "Correo Electronico", "Documento" };
        String sql = "SELECT * FROM cliente";
        Class[] tiposDatos = { Integer.class, String.class, String.class, String.class, String.class, String.class };
        UtilidadesTablas.mostrarTabla(columnas, sql, tabla, tiposDatos);
    }

    private void conectarListenerTabla() {
        tabla.getSelectionModel().addListSelectionListener((model) -> {
            int fila = tabla.getSelectedRow();
            if (fila != -1) {
                eliminarBtn.setEnabled(true);

                nombreTxt.setText((String) tabla.getValueAt(fila, 1));
                direccionTxt.setText((String) tabla.getValueAt(fila, 2));
                telefonoTxt.setText((String) tabla.getValueAt(fila, 3));
                correoTxt.setText((String) tabla.getValueAt(fila, 4));
                documentoTxt.setText((String) tabla.getValueAt(fila, 5));
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
        direccionTxt = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        telefonoTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        correoTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        documentoTxt = new javax.swing.JTextField();
        guardarBtn = new javax.swing.JButton();
        limpiarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));
        setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel1.setText("CLIENTES");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Insertar datos de cliente"));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Nombre:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("Direccion:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel4.setText("Telefono:");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel5.setText("Correo Electronico: ");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("Documento:");

        guardarBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarBtn.setText("Guardar cliente");
        guardarBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        guardarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        guardarBtn.setIconTextGap(10);
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        limpiarBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        limpiarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N
        limpiarBtn.setText("Limpiar campos");
        limpiarBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        limpiarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        limpiarBtn.setIconTextGap(10);
        limpiarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarBtnActionPerformed(evt);
            }
        });

        eliminarBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 14)); // NOI18N
        eliminarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarBtn.setText("Eliminar dato seleccionado");
        eliminarBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eliminarBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        eliminarBtn.setEnabled(false);
        eliminarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 263, Short.MAX_VALUE)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nombreTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(direccionTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(telefonoTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(correoTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(documentoTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(limpiarBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE)))
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
                .addComponent(direccionTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(telefonoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(correoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(documentoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
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
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 538, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        guardar();
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void limpiarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarBtnActionPerformed
        limpiarCampos();
    }//GEN-LAST:event_limpiarBtnActionPerformed

    private void eliminarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarBtnActionPerformed
        DeleteConfirmDialog confirmDelete = new DeleteConfirmDialog(null, "Desea eliminar este dato?");
        confirmDelete.setVisible(true);
        if (!confirmDelete.isAceptado()) return;
        eliminar();
    }//GEN-LAST:event_eliminarBtnActionPerformed

    private void eliminar() {
        if (eliminarBtn.isEnabled()) {
            int fila = tabla.getSelectedRow();
            int id = ((int) tabla.getValueAt(fila, 0));
            UtilidadesBD.sql("DELETE FROM cliente WHERE id_cliente = ?", (stm) -> stm.setInt(1, id));
            actualizarTablaClientes();
            limpiarCampos();
        }
    }

    private void guardar() {
        String nombre = nombreTxt.getText();
        String direccion = direccionTxt.getText();
        String telefono = telefonoTxt.getText();
        String correoElectronico = correoTxt.getText();
        String documento = documentoTxt.getText();

        int fila = tabla.getSelectedRow();
        if (fila != -1) {
            int id = ((int) tabla.getValueAt(fila, 0));
            UtilidadesBD.sql(
                    """
                UPDATE cliente 
                    SET nombre = ?,
                        direccion = ?,
                        telefono = ?,
                        correo = ?,
                        documento = ?
                WHERE id_cliente = ?
                """,
                    (stm) -> {
                        stm.setString(1, nombre);
                        stm.setString(2, direccion);
                        stm.setString(3, telefono);
                        stm.setString(4, correoElectronico);
                        stm.setString(5, documento);
                        stm.setInt(6, id);
                    }
            );

            actualizarTablaClientes();
            limpiarCampos();

            return;
        }

        UtilidadesBD.sql("""
            INSERT INTO cliente (nombre, direccion, telefono, correo, documento)
                         VALUES (?, ?, ?, ?, ?)
        """, (stm) -> {
            stm.setString(1, nombre);
            stm.setString(2, direccion);
            stm.setString(3, telefono);
            stm.setString(4, correoElectronico);
            stm.setString(5, documento);
        });

        actualizarTablaClientes();
        limpiarCampos();
    }

    void limpiarCampos() {
        nombreTxt.setText("");
        direccionTxt.setText("");
        telefonoTxt.setText("");
        correoTxt.setText("");
        documentoTxt.setText("");
        tabla.clearSelection();
        eliminarBtn.setEnabled(false);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField correoTxt;
    private javax.swing.JTextField direccionTxt;
    private javax.swing.JTextField documentoTxt;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarBtn;
    private javax.swing.JTextField nombreTxt;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField telefonoTxt;
    // End of variables declaration//GEN-END:variables
}
