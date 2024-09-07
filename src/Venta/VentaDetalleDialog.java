package Venta;

import Modelos.Venta;
import Utilidades.CreadorObjeto;
import Utilidades.SetterStatement;
import Utilidades.UtilidadesJList;
import Utilidades.UtilidadesTablas;
import javax.swing.JFrame;

public class VentaDetalleDialog extends javax.swing.JDialog {

    public VentaDetalleDialog() {
        super(((JFrame) null), false);
        initComponents();
        setTitle("Detalle de ventas");
        setLocationRelativeTo(null);
        mostrarListVentas();

        System.out.println(listaVentas.getSelectedIndex());

        listaVentas.getSelectionModel().addListSelectionListener((x) -> {
            int fila = listaVentas.getSelectedIndex();

            // El concepto de fila diferente de -1, se aplica
            // igualmente a los JList
            if (fila != -1) {
                Venta v = (Venta) listaVentas.getSelectedValue();
                mostrarTablaDetalleVenta(v.getId());
            }
        });
    }

    private void mostrarTablaDetalleVenta(int idVenta) {
        String[] columnas = {"Producto", "Precio venta", "Cantidad"};
        String sql = "SELECT nombre, precio_venta, cantidad FROM view_detalle_venta_productos WHERE id_venta = ?";
        Class[] tiposDatosColumnas = {String.class, Integer.class, Integer.class};
        SetterStatement setter = stm -> stm.setInt(1, idVenta);
        UtilidadesTablas.mostrarTabla(columnas, sql, jTable1, tiposDatosColumnas, setter);
    }

    private void mostrarListVentas() {
        String sql = "SELECT * FROM view_venta";
        SetterStatement setter = stm -> {
        };
        CreadorObjeto creador = rs -> {
            Venta v = new Venta();
            v.setId(rs.getInt(1));
            v.setNombreCliente(rs.getString(2));
            v.setFechaVenta(rs.getDate(5));
            return v;
        };
        UtilidadesJList.mostrarDatosLista(sql, listaVentas, setter, creador);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaVentas = new javax.swing.JList();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        listaVentas.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane1.setViewportView(listaVentas);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jTable1.setFocusable(false);
        jTable1.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(jTable1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 560, Short.MAX_VALUE)
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JList listaVentas;
    // End of variables declaration//GEN-END:variables
}
