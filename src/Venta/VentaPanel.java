package Venta;

import Modelos.Cliente;
import Modelos.Producto;
import Utilidades.CreadorObjeto;
import Utilidades.Notificador.NotificadorCambios;
import Utilidades.SetterStatement;
import Utilidades.UtilidadesBD;
import Utilidades.UtilidadesJList;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.DefaultListModel;

public class VentaPanel extends javax.swing.JPanel {
    
    private VentaTablaModelo modelo = new VentaTablaModelo();

    private int total = 0;

    public VentaPanel() {
        initComponents();
        tabla.setModel(modelo);
    }
    
    private void realizarCompra() {
        int idCliente = ((Cliente) listaCliente.getSelectedValue()).getId();
        calcularTotal(); // para obtener el total actual
        int recibido = Integer.parseInt(cambioTxt.getText());
        int idFormaPago = 1; // Luego arreglaré forma de pago, por ahora quedará 
                             // hardcodeado al 1
        
        String sqlInsertVentas = "INSERT INTO venta (id_cliente, total_venta, recibido, id_forma_pago) VALUES (?,?,?,?)";
        try (PreparedStatement stm = UtilidadesBD.conn.prepareStatement(sqlInsertVentas, Statement.RETURN_GENERATED_KEYS)) {
            stm.setInt(1, idCliente);
            stm.setInt(2, total);
            stm.setInt(3, recibido);
            stm.setInt(4, idFormaPago);
            
            int affectedRows = stm.executeUpdate();
            
            if (affectedRows > 0) {
                ResultSet rsGenKeys = stm.getGeneratedKeys();
                
                if (rsGenKeys.next()) {
                    int idVentaGenerada = rsGenKeys.getInt(1);
                    
                    List<String> strGeneradosInsert = obtenerProductos()
                            .stream()
                            .map(p -> "(" + idVentaGenerada + "," + p.getId() + "," + p.getCantidadStock() + ")")
                            .toList();
                    
                    String sqlInsertProductos = "INSERT INTO detalle_venta_productos (id_venta, id_producto, cantidad) VALUES ";
                    String insertVals = String.join(",", strGeneradosInsert);
                    sqlInsertProductos += insertVals + ";";
                    
                    System.out.println(sqlInsertProductos);
                    
                    PreparedStatement stmInsert = UtilidadesBD.conn.prepareStatement(sqlInsertProductos);
                    stmInsert.executeUpdate();
                    NotificadorCambios.notificar();
                }
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(VentaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        limpiarCampos();
    }
    
    private ArrayList<Producto> obtenerProductos() {
        ArrayList<Producto> productos = new ArrayList<>();
        
        for (int i = 0; i < tabla.getRowCount(); i++) {
            Producto p = (Producto) tabla.getValueAt(i, 0);
            p.setCantidadStock(((int) tabla.getValueAt(i, 2)));
            productos.add(p);
        }
        
        return productos;
    }
    
    private void calcularTotal() {
        total = 0;
        for (int i = 0; i < tabla.getRowCount(); i++) {
            total += ((int) tabla.getValueAt(i, 3));
        }
        totalLbl.setText("$ " + total);
    }

    private void calcularCambio() {
        String cambioStr = cambioTxt.getText();

        if (cambioStr.isEmpty() || cambioStr.isBlank()) {
            cambioLbl.setText("$ ------");
            return;
        }

        int totalVenta = Integer.parseInt(totalLbl.getText().split(" ")[1]);
        int cambio = Integer.parseInt(cambioStr);
        int totalCambio = cambio - totalVenta;
        cambioLbl.setText("$ " + totalCambio);
    }

    private void agregarProductoAVenta(Producto p, int cantidad) {
        modelo.agregarProductoACarrito(p, cantidad);
        calcularTotal();
        calcularCambio();
    }

    private void buscarProducto() {
        String sql = "SELECT id_producto, nombre, precio_venta, cantidad_stock FROM producto WHERE nombre LIKE ?";
        SetterStatement setter = stm -> stm.setString(1, "%" + productoTxt.getText() + "%");

        CreadorObjeto creador = rs -> {
            Producto p = new Producto();
            p.setId(rs.getInt(1));
            p.setNombre(rs.getString(2));
            p.setPrecioVenta(rs.getInt(3));
            p.setCantidadStock(rs.getInt(4));
            return p;
        };

        UtilidadesJList.mostrarDatosLista(sql, listaProducto, setter, creador);
    }
    
    private void buscarCliente() {
        String sql = "SELECT id_cliente, nombre FROM cliente WHERE nombre LIKE ?";
        SetterStatement setter = stm -> stm.setString(1, "%" + clienteTxt.getText() + "%");
        
        CreadorObjeto creador = rs -> {
            Cliente c = new Cliente();
            c.setId(rs.getInt(1));
            c.setNombre(rs.getString(2));
            return c;
        };
        
        UtilidadesJList.mostrarDatosLista(sql, listaCliente, setter, creador);
    }
    
    private void limpiarCampos() {
        productoTxt.setText("");
        cantidadSpn.setValue(0);
        
        modelo = new VentaTablaModelo(); // para limpiar la tabla
        tabla.setModel(modelo); // para limpiar la tabla
        
        clienteTxt.setText("");
        
        DefaultListModel modelo = new DefaultListModel();
        listaProducto.setModel(modelo);
        listaCliente.setModel(modelo);

        totalLbl.setText("$ ------");
        cambioTxt.setText("");
        cambioLbl.setText("$ ------");
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        productoTxt = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaProducto = new javax.swing.JList();
        jLabel3 = new javax.swing.JLabel();
        agregarProductoBtn = new javax.swing.JButton();
        limpiarBtn = new javax.swing.JButton();
        eliminarProductoBtn = new javax.swing.JButton();
        cantidadSpn = new javax.swing.JSpinner();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        listaCliente = new javax.swing.JList();
        jLabel4 = new javax.swing.JLabel();
        clienteTxt = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        totalLbl = new javax.swing.JLabel();
        cambioLbl = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        cambioTxt = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel1.setText("VENTAS");

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

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Registrar producto a la venta"));

        jLabel2.setText("Producto");

        productoTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                productoTxtCaretUpdate(evt);
            }
        });

        listaProducto.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane2.setViewportView(listaProducto);

        jLabel3.setText("Cantidad");

        agregarProductoBtn.setBackground(new java.awt.Color(102, 204, 255));
        agregarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsArrowForward.png"))); // NOI18N
        agregarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarProductoBtnActionPerformed(evt);
            }
        });

        limpiarBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N

        eliminarProductoBtn.setBackground(new java.awt.Color(255, 153, 153));
        eliminarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarProductoBtn.setEnabled(false);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 214, Short.MAX_VALUE)
                            .addComponent(productoTxt)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(agregarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(limpiarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(eliminarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cantidadSpn)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(productoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(cantidadSpn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(agregarProductoBtn)
                    .addComponent(limpiarBtn)
                    .addComponent(eliminarProductoBtn))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Facturacion"));

        listaCliente.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane3.setViewportView(listaCliente);

        jLabel4.setText("Cliente:");

        clienteTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                clienteTxtCaretUpdate(evt);
            }
        });

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder("TOTAL"));

        totalLbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        totalLbl.setText("$ ------");

        cambioLbl.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        cambioLbl.setText("$ ------");

        jLabel7.setText("Cambio:");

        jLabel8.setText("Recibido:");

        cambioTxt.addCaretListener(new javax.swing.event.CaretListener() {
            public void caretUpdate(javax.swing.event.CaretEvent evt) {
                cambioTxtCaretUpdate(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cambioTxt))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(totalLbl)
                            .addComponent(cambioLbl)
                            .addComponent(jLabel7))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(totalLbl)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(cambioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(11, 11, 11)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cambioLbl)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setBackground(new java.awt.Color(102, 255, 102));
        jButton1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiCashRegister.png"))); // NOI18N
        jButton1.setText("VENDER");
        jButton1.setIconTextGap(10);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setBackground(new java.awt.Color(255, 153, 153));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/OcticonXCircleFill12.png"))); // NOI18N
        jButton2.setText("CANCELAR");
        jButton2.setIconTextGap(10);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(clienteTxt))
                    .addComponent(jScrollPane3)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(clienteTxt, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, 75, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsArrowForward.png"))); // NOI18N
        jButton3.setText("Ver detalle de ventas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 542, Short.MAX_VALUE))
                    .addComponent(jButton3))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void agregarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarProductoBtnActionPerformed
        Producto p = (Producto) listaProducto.getSelectedValue();
        int cantidad = ((int) cantidadSpn.getValue());
        agregarProductoAVenta(p, cantidad);
    }//GEN-LAST:event_agregarProductoBtnActionPerformed

    private void productoTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_productoTxtCaretUpdate
        String producto = productoTxt.getText();
        if (producto.isEmpty() || producto.isBlank()) {
            return;
        }
        buscarProducto();
    }//GEN-LAST:event_productoTxtCaretUpdate

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        realizarCompra();
        //obtenerProductos();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void cambioTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_cambioTxtCaretUpdate
        calcularCambio();
    }//GEN-LAST:event_cambioTxtCaretUpdate

    private void clienteTxtCaretUpdate(javax.swing.event.CaretEvent evt) {//GEN-FIRST:event_clienteTxtCaretUpdate
        String cliente = clienteTxt.getText();
        if (cliente.isEmpty() || cliente.isBlank()) return;
        buscarCliente();
    }//GEN-LAST:event_clienteTxtCaretUpdate

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        VentaDetalleDialog vdd = new VentaDetalleDialog();
        vdd.setVisible(true);
    }//GEN-LAST:event_jButton3ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarProductoBtn;
    private javax.swing.JLabel cambioLbl;
    private javax.swing.JTextField cambioTxt;
    private javax.swing.JSpinner cantidadSpn;
    private javax.swing.JTextField clienteTxt;
    private javax.swing.JButton eliminarProductoBtn;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton limpiarBtn;
    private javax.swing.JList listaCliente;
    private javax.swing.JList listaProducto;
    private javax.swing.JTextField productoTxt;
    private javax.swing.JTable tabla;
    private javax.swing.JLabel totalLbl;
    // End of variables declaration//GEN-END:variables

}
