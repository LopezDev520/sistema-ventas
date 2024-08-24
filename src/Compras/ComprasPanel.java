package Compras;

import Modelos.Producto;
import Modelos.Proveedor;
import Utilidades.CreadorObjeto;
import Utilidades.Notificador.NotificadorCambios;
import Utilidades.SetterStatement;
import Utilidades.UtilidadesBD;
import Utilidades.UtilidadesJList;
import Utilidades.UtilidadesTablas;
import java.sql.Date;

public class ComprasPanel extends javax.swing.JPanel {

    public ComprasPanel() {
        initComponents();
        mostrarTablaCompras();
    }
    
    
    private void buscarProductoEnBD() {
        String producto = productoTxt.getText();
        String sql = "SELECT id_producto, nombre FROM producto WHERE nombre LIKE ?";        
        SetterStatement setter = (stm) -> {
            stm.setString(1, "%" + producto + "%");
        };
        CreadorObjeto creador = (rs) -> {
            Producto p = new Producto();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            return p;
        };
        UtilidadesJList.mostrarDatosLista(sql, resProductosList, setter, creador);
    }

    private void buscarProveedorEnBD() {
        String proveedor = proveedorTxt.getText();
        String sql = "SELECT id_proveedor, nombre FROM proveedor WHERE nombre LIKE ?";
        SetterStatement setter = (stm) -> {
            stm.setString(1, "%" + proveedor + "%");
        };
        CreadorObjeto creador = (rs) -> {
            Proveedor p = new Proveedor();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            return p;
        };
        UtilidadesJList.mostrarDatosLista(sql, resProveedorList, setter, creador);
    }
    
    private void realizarCompra(int idProductoComprado, int cantidadComprada, 
            int precioCompra, String comprobante, String tipoIngreso, int numero, int idProveedor) {
        String sql = "INSERT INTO compra (id_producto_comprado, cantidad_comprada, precio_compra, comprobante, tipo_ingreso, numero, id_proveedor) VALUES (?,?,?,?,?,?,?)";
        SetterStatement setter = (stm) -> {
            stm.setInt(1, idProductoComprado);
            stm.setInt(2, cantidadComprada);
            stm.setInt(3, precioCompra);
            stm.setString(4, comprobante);
            stm.setString(5, tipoIngreso);
            stm.setInt(6, numero);
            stm.setInt(7, idProveedor);
        };
        UtilidadesBD.sql(sql, setter);
        NotificadorCambios.notificar();
    }

    private void mostrarTablaCompras() {
        String[] columnas = { "ID", "Producto Comprado", "Precio Compra", "Cantidad", "Comprobante", "Tipo Ingreso", "Proveedor", "Fecha de compra" };
        String sql = "SELECT * FROM view_compra";
        Class[] tiposDatos = { Integer.class, String.class, Integer.class, Integer.class, String.class, String.class, String.class, String.class, Date.class };
        UtilidadesTablas.mostrarTabla(columnas, sql, comprasTabla, tiposDatos);
    }
    
    private void mostrarTotal(int cantidad, int precio) {
        int res = cantidad * precio;
        lblTotal.setText(String.valueOf(res));
    }
    
    private void limpiarCampos() {
        // TODO: Limpiar campos
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        cantidadTxt = new javax.swing.JTextField();
        precioTxt = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        lblTotal = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        comboComprobante = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        numeroTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        proveedorTxt = new javax.swing.JTextField();
        buscarProveedorBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        resProveedorList = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        resProductosList = new javax.swing.JList();
        productoTxt = new javax.swing.JTextField();
        buscarProductoBtn = new javax.swing.JButton();
        comboTipoIngreso = new javax.swing.JComboBox<>();
        guardarBtn = new javax.swing.JButton();
        limpiarBtn = new javax.swing.JButton();
        eliminarBtn = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        comprasTabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel1.setText("COMPRAS");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ingresar datos de la compra", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI Semibold", 2, 14))); // NOI18N

        jLabel2.setText("Producto:");

        jLabel3.setText("Cantidad:");

        jLabel4.setText("Precio:");

        cantidadTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                cantidadTxtCaretUpdate(evt);
            }
        });

        precioTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                cantidadTxtCaretUpdate(evt);
            }
        });

        jLabel5.setText("Ud - Unidades");

        jLabel6.setText("Ud - Unidades");

        jLabel7.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        jLabel7.setText("TOTAL:");

        lblTotal.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        lblTotal.setText("0");

        jLabel9.setText("Comprobante: ");

        comboComprobante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Factura", "Recibo", "Remision" }));

        jLabel10.setText("Numero:");

        jLabel11.setText("Tipo de Ingreso:");

        jLabel12.setText("Proveedor:");

        buscarProveedorBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsSearch.png"))); // NOI18N
        buscarProveedorBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProveedorBtnActionPerformed(evt);
            }
        });

        resProveedorList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(resProveedorList);

        resProductosList.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(resProductosList);

        buscarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsSearch.png"))); // NOI18N
        buscarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarProductoBtnActionPerformed(evt);
            }
        });

        comboTipoIngreso.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Compra", "Consumo Interno", "Ajuste Inventario", "Otro" }));

        guardarBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        limpiarBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N

        eliminarBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarBtn.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 265, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4))
                        .addGap(12, 12, 12)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(precioTxt, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                            .addComponent(cantidadTxt))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboComprobante, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(proveedorTxt)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarProveedorBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblTotal))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(productoTxt)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(buscarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel10)
                            .addComponent(jLabel11))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(numeroTxt)
                            .addComponent(comboTipoIngreso, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(buscarProductoBtn)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(productoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cantidadTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(precioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(lblTotal))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(comboComprobante, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(numeroTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(comboTipoIngreso, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(proveedorTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(buscarProveedorBtn))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(guardarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(limpiarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(eliminarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        comprasTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(comprasTabla);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 572, Short.MAX_VALUE)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void buscarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProductoBtnActionPerformed
        buscarProductoEnBD();
    }//GEN-LAST:event_buscarProductoBtnActionPerformed

    private void buscarProveedorBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarProveedorBtnActionPerformed
        buscarProveedorEnBD();
    }//GEN-LAST:event_buscarProveedorBtnActionPerformed

    private void cantidadTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_cantidadTxtCaretUpdate
        String cantidadStr = cantidadTxt.getText();
        String precioStr = precioTxt.getText();
        
        if (cantidadStr.isEmpty() || cantidadStr.isBlank()) return;
        else if (precioStr.isEmpty() || precioStr.isBlank()) return;
        
        int cantidad = Integer.parseInt(cantidadStr);
        int precio = Integer.parseInt(precioStr);
        
        mostrarTotal(cantidad, precio);
    }//GEN-LAST:event_cantidadTxtCaretUpdate

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        int idProductoComprado = ((Producto) resProductosList.getSelectedValue()).getId();
        int cantidadComprada = Integer.parseInt(cantidadTxt.getText());
        int precioCompra = Integer.parseInt(precioTxt.getText());
        String comprobante = ((String) comboComprobante.getSelectedItem());
        String tipoIngreso = ((String) comboTipoIngreso.getSelectedItem());
        int numero = Integer.parseInt(numeroTxt.getText());
        int idProveedor = ((Proveedor) resProveedorList.getSelectedValue()).getId();
        
        realizarCompra(idProductoComprado, cantidadComprada, precioCompra, comprobante, tipoIngreso, numero, idProveedor);
        mostrarTablaCompras();
    }//GEN-LAST:event_guardarBtnActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buscarProductoBtn;
    private javax.swing.JButton buscarProveedorBtn;
    private javax.swing.JTextField cantidadTxt;
    private javax.swing.JComboBox<String> comboComprobante;
    private javax.swing.JComboBox<String> comboTipoIngreso;
    private javax.swing.JTable comprasTabla;
    private javax.swing.JButton eliminarBtn;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JLabel lblTotal;
    private javax.swing.JButton limpiarBtn;
    private javax.swing.JTextField numeroTxt;
    private javax.swing.JTextField precioTxt;
    private javax.swing.JTextField productoTxt;
    private javax.swing.JTextField proveedorTxt;
    private javax.swing.JList resProductosList;
    private javax.swing.JList resProveedorList;
    // End of variables declaration//GEN-END:variables

}
