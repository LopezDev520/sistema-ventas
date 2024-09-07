package Principal;

import Modelos.Usuario;
import javax.swing.plaf.basic.BasicTabbedPaneUI;

public class PrincipalFrame extends javax.swing.JFrame {
    
    public PrincipalFrame(Usuario u) {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Punto de venta - Usuario: " + Global.usuarioIniciado);
        setResizable(false);
        
        // Modificar el alto de las pestañas del TabbedPane Principal, de forma
        // que no se muestren las pestañas
        tabbedPrincipal.setUI(new BasicTabbedPaneUI() {
            @Override
            protected int calculateTabAreaHeight(int tabPlacement, int horizRunCount, int maxTabHeight) {
                return 0;
            }
        });
        
        // Deshabilitar botones para restringir la navegacion a ciertas partes
        // de la aplicacion (en caso de ser vendedor)
        if (u.getIdTipoUsuario() == 2) {
            comprasBtn.setEnabled(false);
            proveedoresBtn.setEnabled(false);
            administracionBtn.setEnabled(false);
            
        }
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        contenido = new javax.swing.JPanel();
        sideBar = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        clientesBtn = new javax.swing.JButton();
        productosBtn = new javax.swing.JButton();
        comprasBtn = new javax.swing.JButton();
        ventasBtn = new javax.swing.JButton();
        proveedoresBtn = new javax.swing.JButton();
        administracionBtn = new javax.swing.JButton();
        reportesBtn = new javax.swing.JButton();
        tabbedPrincipal = new javax.swing.JTabbedPane();
        clientesPanel1 = new Clientes.ClientesPanel();
        productosPanel1 = new Productos.ProductosPanel();
        ventaPanel1 = new Venta.VentaPanel();
        comprasPanel2 = new Compras.ComprasPanel();
        proveedoresPanel1 = new Proveedores.ProveedoresPanel();
        adminPanel1 = new Admin.AdminPanel();
        reportesPanel1 = new Reportes.ReportesPanel();

        javax.swing.GroupLayout contenidoLayout = new javax.swing.GroupLayout(contenido);
        contenido.setLayout(contenidoLayout);
        contenidoLayout.setHorizontalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 868, Short.MAX_VALUE)
        );
        contenidoLayout.setVerticalGroup(
            contenidoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 550, Short.MAX_VALUE)
        );

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        sideBar.setBackground(new java.awt.Color(0, 102, 153));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Punto de venta");

        clientesBtn.setBackground(new java.awt.Color(0, 102, 153));
        clientesBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        clientesBtn.setForeground(new java.awt.Color(255, 255, 255));
        clientesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsGroupWhite.png"))); // NOI18N
        clientesBtn.setText("Clientes");
        clientesBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        clientesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        clientesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        clientesBtn.setIconTextGap(10);
        clientesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clientesBtnActionPerformed(evt);
            }
        });

        productosBtn.setBackground(new java.awt.Color(0, 102, 153));
        productosBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        productosBtn.setForeground(new java.awt.Color(255, 255, 255));
        productosBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/EpGoodsFilledWhite.png"))); // NOI18N
        productosBtn.setText("Productos");
        productosBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        productosBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        productosBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        productosBtn.setIconTextGap(10);
        productosBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                productosBtnActionPerformed(evt);
            }
        });

        comprasBtn.setBackground(new java.awt.Color(0, 102, 153));
        comprasBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        comprasBtn.setForeground(new java.awt.Color(255, 255, 255));
        comprasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsShoppingCartWhite.png"))); // NOI18N
        comprasBtn.setText("Compras");
        comprasBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        comprasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        comprasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        comprasBtn.setIconTextGap(10);
        comprasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                comprasBtnActionPerformed(evt);
            }
        });

        ventasBtn.setBackground(new java.awt.Color(0, 102, 153));
        ventasBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        ventasBtn.setForeground(new java.awt.Color(255, 255, 255));
        ventasBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsLightSellWhite.png"))); // NOI18N
        ventasBtn.setText("Ventas");
        ventasBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        ventasBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        ventasBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        ventasBtn.setIconTextGap(10);
        ventasBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventasBtnActionPerformed(evt);
            }
        });

        proveedoresBtn.setBackground(new java.awt.Color(0, 102, 153));
        proveedoresBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        proveedoresBtn.setForeground(new java.awt.Color(255, 255, 255));
        proveedoresBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/OcticonPackage16White.png"))); // NOI18N
        proveedoresBtn.setText("Proveedores");
        proveedoresBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        proveedoresBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        proveedoresBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        proveedoresBtn.setIconTextGap(10);
        proveedoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedoresBtnActionPerformed(evt);
            }
        });

        administracionBtn.setBackground(new java.awt.Color(0, 102, 153));
        administracionBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        administracionBtn.setForeground(new java.awt.Color(255, 255, 255));
        administracionBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/EosIconsAdminWhite.png"))); // NOI18N
        administracionBtn.setText("Administracion");
        administracionBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        administracionBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        administracionBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        administracionBtn.setIconTextGap(10);
        administracionBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                administracionBtnActionPerformed(evt);
            }
        });

        reportesBtn.setBackground(new java.awt.Color(0, 102, 153));
        reportesBtn.setFont(new java.awt.Font("Segoe UI Semibold", 0, 18)); // NOI18N
        reportesBtn.setForeground(new java.awt.Color(255, 255, 255));
        reportesBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/BxsReportWhite.png"))); // NOI18N
        reportesBtn.setText("Reportes");
        reportesBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 10, 1, 1));
        reportesBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        reportesBtn.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        reportesBtn.setIconTextGap(10);
        reportesBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportesBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout sideBarLayout = new javax.swing.GroupLayout(sideBar);
        sideBar.setLayout(sideBarLayout);
        sideBarLayout.setHorizontalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(productosBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sideBarLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
            .addComponent(ventasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(comprasBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(proveedoresBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(administracionBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(reportesBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(sideBarLayout.createSequentialGroup()
                .addComponent(clientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        sideBarLayout.setVerticalGroup(
            sideBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(sideBarLayout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(clientesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(productosBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ventasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(comprasBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(proveedoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(administracionBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(reportesBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 197, Short.MAX_VALUE))
        );

        tabbedPrincipal.setTabPlacement(javax.swing.JTabbedPane.BOTTOM);
        tabbedPrincipal.addTab("1. Clientes", clientesPanel1);
        tabbedPrincipal.addTab("2. Productos", productosPanel1);
        tabbedPrincipal.addTab("3. Ventas", ventaPanel1);
        tabbedPrincipal.addTab("4. Compras", comprasPanel2);
        tabbedPrincipal.addTab("5. Proveedores", proveedoresPanel1);
        tabbedPrincipal.addTab("6. Administración", adminPanel1);
        tabbedPrincipal.addTab("7. Reportes", reportesPanel1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(tabbedPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 852, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(sideBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addComponent(tabbedPrincipal, javax.swing.GroupLayout.PREFERRED_SIZE, 581, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void clientesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clientesBtnActionPerformed
        // Moverse a pestaña 0 (Clientes)
        tabbedPrincipal.setSelectedIndex(0);
    }//GEN-LAST:event_clientesBtnActionPerformed

    private void productosBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_productosBtnActionPerformed
        // Moverse a pestaña 1 (Productos)
        tabbedPrincipal.setSelectedIndex(1);
    }//GEN-LAST:event_productosBtnActionPerformed

    private void ventasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventasBtnActionPerformed
        // Moverse a pestaña 2 (Ventas)
        tabbedPrincipal.setSelectedIndex(2);
    }//GEN-LAST:event_ventasBtnActionPerformed

    private void comprasBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_comprasBtnActionPerformed
        // Moverse a pestaña 3 (Compras)
        tabbedPrincipal.setSelectedIndex(3);
    }//GEN-LAST:event_comprasBtnActionPerformed

    private void proveedoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedoresBtnActionPerformed
        // Moverse a pestaña 4 (Proveedores)
        tabbedPrincipal.setSelectedIndex(4);
    }//GEN-LAST:event_proveedoresBtnActionPerformed

    private void administracionBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_administracionBtnActionPerformed
        // Moverse a pestaña 5 (Administracion)
        tabbedPrincipal.setSelectedIndex(5);
    }//GEN-LAST:event_administracionBtnActionPerformed

    private void reportesBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportesBtnActionPerformed
        // Moverse a pestaña 6 (Reportes)
        tabbedPrincipal.setSelectedIndex(6);
    }//GEN-LAST:event_reportesBtnActionPerformed
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private Admin.AdminPanel adminPanel1;
    private javax.swing.JButton administracionBtn;
    private javax.swing.JButton clientesBtn;
    private Clientes.ClientesPanel clientesPanel1;
    private javax.swing.JButton comprasBtn;
    private Compras.ComprasPanel comprasPanel2;
    private javax.swing.JPanel contenido;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton productosBtn;
    private Productos.ProductosPanel productosPanel1;
    private javax.swing.JButton proveedoresBtn;
    private Proveedores.ProveedoresPanel proveedoresPanel1;
    private javax.swing.JButton reportesBtn;
    private Reportes.ReportesPanel reportesPanel1;
    private javax.swing.JPanel sideBar;
    private javax.swing.JTabbedPane tabbedPrincipal;
    private Venta.VentaPanel ventaPanel1;
    private javax.swing.JButton ventasBtn;
    // End of variables declaration//GEN-END:variables
}