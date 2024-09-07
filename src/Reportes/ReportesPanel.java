package Reportes;

import Utilidades.GeneradorDeReporte;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ReportesPanel extends javax.swing.JPanel {

    public ReportesPanel() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        checkProducto = new javax.swing.JCheckBox();
        checkCompra = new javax.swing.JCheckBox();
        checkVenta = new javax.swing.JCheckBox();
        checkCliente = new javax.swing.JCheckBox();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        fechaDesde = new rojeru_san.componentes.RSDateChooser();
        fechaHasta = new rojeru_san.componentes.RSDateChooser();

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel1.setText("REPORTES");

        jButton2.setBackground(new java.awt.Color(153, 204, 255));
        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsArrowForward.png"))); // NOI18N
        jButton2.setText("GENERAR REPORTE");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jPanel1.setLayout(new java.awt.GridLayout(4, 1));

        checkProducto.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        checkProducto.setText("Producto");
        jPanel1.add(checkProducto);

        checkCompra.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        checkCompra.setText("Compra");
        jPanel1.add(checkCompra);

        checkVenta.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        checkVenta.setText("Venta");
        jPanel1.add(checkVenta);

        checkCliente.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        checkCliente.setText("Cliente");
        jPanel1.add(checkCliente);

        jLabel2.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel2.setText("DESDE:");

        jLabel3.setFont(new java.awt.Font("Segoe UI Semibold", 0, 24)); // NOI18N
        jLabel3.setText("HASTA:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton2))
                        .addGap(48, 48, 48)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(fechaHasta, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                .addContainerGap(191, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(fechaDesde, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(fechaHasta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addGap(35, 35, 35)
                            .addComponent(jLabel3))))
                .addContainerGap(286, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        if (fechaDesde.getDatoFecha() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione fecha de inicio");
            return;
        }

        if (fechaHasta.getDatoFecha() == null) {
            JOptionPane.showMessageDialog(this, "Seleccione fecha de fin");
            return;
        }

        Date dateFechaDesde = fechaDesde.getDatoFecha();
        Date dateFechaHasta = fechaHasta.getDatoFecha();

        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String fechaDesdeStr = formatter.format(dateFechaDesde) + " 00:00:00";
        String fechaHastaStr = formatter.format(dateFechaHasta) + " 23:59:59";

        ArrayList<String> nombres = new ArrayList<>();
        ArrayList<String> sqls = new ArrayList<>();
        ArrayList<String[]> columnas = new ArrayList<>();

        if (checkProducto.isSelected()) {
            nombres.add("Productos");
            sqls.add("SELECT * FROM view_producto");
            columnas.add(null);
            nombres.add("Productos vendidos");
            sqls.add("SELECT * FROM view_productos_vendidos");
            columnas.add(null);
        }

        if (checkCompra.isSelected()) {
            nombres.add("Compras");
            sqls.add("SELECT \n"
                    + "	c.id_compra AS 'ID compra',"
                    + "   	p.nombre AS 'Producto Comprado',"
                    + "    c.cantidad_comprada AS 'Cantidad Comprada',"
                    + "    c.precio_compra AS 'Precio compra',"
                    + "    c.fecha_compra AS 'Fecha de la compra',"
                    + "    pr.nombre AS 'Nombre del proveedeor' "
                    + "FROM compra c "
                    + "INNER JOIN producto p ON c.id_producto_comprado = p.id_producto "
                    + "INNER JOIN proveedor pr ON c.id_proveedor = pr.id_proveedor "
                    + "WHERE c.fecha_compra BETWEEN '" + fechaDesdeStr + "' AND '" + fechaHastaStr + "'");
            columnas.add(new String[]{"Id de compra", "Nombre", "Cantidad Comprada", "Precio de compra", "Fecha de Compra", "Proveedor"});

        }

        if (checkVenta.isSelected()) {
            nombres.add("Ventas");
            sqls.add("SELECT "
                    + "v.id_venta,"
                    + "c.nombre,"
                    + "v.total_venta,"
                    + "v.recibido,"
                    + "v.fecha_venta"
                    + " FROM venta v INNER JOIN cliente c ON v.id_cliente = c.id_cliente "
                    + "WHERE v.fecha_venta BETWEEN '" + fechaDesdeStr + "' AND '" + fechaHastaStr + "'");
            columnas.add(new String[] { "ID de venta", "Cliente", "Total", "Recibido", "Fecha de Venta" });
        }

        if (checkCliente.isSelected()) {
            nombres.add("Clientes");
            sqls.add("SELECT * FROM cliente");
            columnas.add(new String[] { "ID cliente", "Nombre", "Direccion", "Telefono", "Correo", "Documento" });
        }

        GeneradorDeReporte.emitirReporte(nombres.toArray(new String[0]), sqls.toArray(new String[0]), columnas.toArray(new String[0][]));
    }//GEN-LAST:event_jButton2ActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkCliente;
    private javax.swing.JCheckBox checkCompra;
    private javax.swing.JCheckBox checkProducto;
    private javax.swing.JCheckBox checkVenta;
    private rojeru_san.componentes.RSDateChooser fechaDesde;
    private rojeru_san.componentes.RSDateChooser fechaHasta;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
