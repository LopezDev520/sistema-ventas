package Admin;

import Modelos.Categoria;
import Utilidades.CreadorObjeto;
import Utilidades.Notificador.NotificadorCambios;
import Utilidades.SetterStatement;
import Utilidades.UtilidadesBD;
import Utilidades.UtilidadesComboBox;
import Utilidades.UtilidadesTablas;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

public class AdminPanel extends javax.swing.JPanel {

  public AdminPanel() {
    initComponents();
    mostrarTablaCategorias();
    mostrarTablaProductos();
    mostrarDatosComboCategorias();
    mostrarTablaUsuarios();

    // Conectando los listeners a cada tabla
    categoriaTabla.getSelectionModel().addListSelectionListener(this::categoriaTablaListener);
    productoTabla.getSelectionModel().addListSelectionListener(this::productoTablaListener);
    usuarioTabla.getSelectionModel().addListSelectionListener(this::usuarioTablaListener);
    
    // Añadiendo el metodo "mostrarTablaProductos" al notificador, de forma que
    // pueda ser llamado desde otra clase
    NotificadorCambios.registrarNotificacion(this::mostrarTablaProductos);
  }

  private void mostrarTablaCategorias() {
    String[] columnas = {"ID", "Nombre"};
    String sql = "SELECT * FROM categoria";
    Class[] tiposDatosColumnas = {Integer.class, String.class};
    UtilidadesTablas.mostrarTabla(columnas, sql, categoriaTabla, tiposDatosColumnas);
  }

  private void mostrarDatosComboCategorias() {
    String sql = "SELECT * FROM categoria";
    CreadorObjeto creador = (rs) -> new Categoria(rs.getInt(1), rs.getString(2)); // 1 para id, 2 para nombre
    UtilidadesComboBox.mostrarDatosComboBox(sql, comboCategoriaProducto, creador);
  }

  private void mostrarTablaProductos() {
    String[] columnas = {"ID", "Nombre", "Precio de venta", "Cantidad en stock", "Categoria"};
    String sql = "SELECT * FROM view_producto";
    Class[] tiposDatosColumnas = {Integer.class, String.class, Integer.class, Integer.class, String.class};
    UtilidadesTablas.mostrarTabla(columnas, sql, productoTabla, tiposDatosColumnas);
  }

  private void mostrarTablaUsuarios() {
    String[] columnas = {"ID", "Nombre", "Contraseña", "Tipo de usuario"};
    String sql = "SELECT * FROM view_usuario";
    Class[] tiposDatosColumnas = {Integer.class, String.class, String.class, String.class};
    UtilidadesTablas.mostrarTabla(columnas, sql, usuarioTabla, tiposDatosColumnas);
  }

  private void categoriaTablaListener(ListSelectionEvent e) {
    int fila = categoriaTabla.getSelectedRow();
    // Se mira si la fila es diferente de -1, significa que hay un dato seleccionado
    // en la tabla
    if (fila != -1) {
      eliminarCategoriaBtn.setEnabled(true);
      nombreCategoriaTxt.setText(((String) categoriaTabla.getValueAt(fila, 1)));
    } else {
      eliminarCategoriaBtn.setEnabled(false);
    }
  }

  private void productoTablaListener(ListSelectionEvent e) {
    int fila = productoTabla.getSelectedRow();
    if (fila != -1) {
      eliminarProductoBtn.setEnabled(true);

      nombreProductoTxt.setText(((String) productoTabla.getValueAt(fila, 1)));
      precioVentaProductoTxt.setText(String.valueOf(((int) productoTabla.getValueAt(fila, 2))));

      for (int i = 0; i < comboCategoriaProducto.getItemCount(); i++) {
        Categoria c = ((Categoria) comboCategoriaProducto.getItemAt(i));
        if (c.getNombre().equals(((String) productoTabla.getValueAt(fila, 4)))) {
          comboCategoriaProducto.setSelectedItem(c);
          break;
        }
      }

    } else {
      eliminarProductoBtn.setEnabled(false);
    }
  }

  private void usuarioTablaListener(ListSelectionEvent e) {
    int fila = usuarioTabla.getSelectedRow();
    if (fila != -1) {
      eliminarUsuarioBtn.setEnabled(true);

      nombreUsuarioTxt.setText(((String) usuarioTabla.getValueAt(fila, 1)));
      contrasenaUsuarioTxt.setText(((String) usuarioTabla.getValueAt(fila, 2)));
      tipoUsuarioCombo.setSelectedItem(usuarioTabla.getValueAt(fila, 3));
    } else {
      eliminarUsuarioBtn.setEnabled(false);
    }

  }

  // FUNCIONES CRUD -------------------------------------------------------------------------------------------
  private void guardarCategoria(String nombre) {
    String sql = "INSERT INTO categoria (nombre) VALUES (?)";
    SetterStatement setter = (stm) -> stm.setString(1, nombre);
    UtilidadesBD.sql(sql, setter);
    mostrarTablaCategorias();
    mostrarDatosComboCategorias();
    limpiarCamposCategoria();
  }

  private void modificarCategoria(String nombre) {
    int id = ((int) categoriaTabla.getValueAt(categoriaTabla.getSelectedRow(), 0));
    String sql = "UPDATE categoria SET nombre = ? WHERE id_categoria = ?";
    SetterStatement setter = (stm) -> {
      stm.setString(1, nombre);
      stm.setInt(2, id);
    };
    UtilidadesBD.sql(sql, setter);

    mostrarDatosComboCategorias();
    mostrarTablaCategorias();
    mostrarTablaProductos();
    limpiarCamposCategoria();
  }

  private void guardarProducto(String nombre, int precioVenta, int idCategoria) {
    String sql = "INSERT INTO producto (nombre, precio_venta, cantidad_stock, id_categoria) VALUES (?,?,0,?)";
    SetterStatement setter = (stm) -> {
      stm.setString(1, nombre);
      stm.setInt(2, precioVenta);
      stm.setInt(3, idCategoria);
    };
    UtilidadesBD.sql(sql, setter);
    mostrarTablaProductos();
    limpiarCamposProducto();
  }

  private void modificarProducto(String nombre, int precioVenta, int idCategoria) {
    int id = ((int) productoTabla.getValueAt(productoTabla.getSelectedRow(), 0));
    String sql = "UPDATE producto SET nombre = ?, precio_venta = ?, id_categoria = ? WHERE id_producto = ?";
    SetterStatement setter = (stm) -> {
      stm.setString(1, nombre);
      stm.setInt(2, precioVenta);
      stm.setInt(3, idCategoria);
      stm.setInt(4, id);
    };
    UtilidadesBD.sql(sql, setter);
    mostrarTablaProductos();
    limpiarCamposProducto();
  }

  private void guardarUsuario(String nombre, String contra, int idTipoUsuario) {
    String sql = "INSERT INTO usuario (nombre, contrasena, id_tipo_usuario) VALUES (?,?,?)";
    SetterStatement setter = (stm) -> {
      stm.setString(1, nombre);
      stm.setString(2, contra);
      stm.setInt(3, idTipoUsuario);
    };
    UtilidadesBD.sql(sql, setter);
    mostrarTablaUsuarios();
    limpiarCamposUsuario();
  }

  private void modificarUsuario(String nombre, String contra, int idTipoUsuario) {
    int id = ((int) usuarioTabla.getValueAt(usuarioTabla.getSelectedRow(), 0));
    String sql = "UPDATE usuario SET nombre = ?, contrasena = ?, id_tipo_usuario = ? WHERE id_usuario = ?";
    SetterStatement setter = (stm) -> {
      stm.setString(1, nombre);
      stm.setString(2, contra);
      stm.setInt(3, idTipoUsuario);
      stm.setInt(4, id);
    };
    UtilidadesBD.sql(sql, setter);
    mostrarTablaUsuarios();
    limpiarCamposUsuario();
  }

  private void eliminarCategoria() {
    int id = ((int) categoriaTabla.getValueAt(categoriaTabla.getSelectedRow(), 0));

    String sql = "DELETE FROM categoria WHERE id_categoria = ?";
    SetterStatement setter = (stm) -> stm.setInt(1, id);
    UtilidadesBD.sql(sql, setter);
    mostrarTablaCategorias();
    limpiarCamposCategoria();
  }

  private void eliminarProducto() {
    int id = ((int) productoTabla.getValueAt(productoTabla.getSelectedRow(), 0));

    String sql = "DELETE FROM producto WHERE id_producto = ?";
    SetterStatement setter = (stm) -> stm.setInt(1, id);
    UtilidadesBD.sql(sql, setter);
    mostrarTablaProductos();
    limpiarCamposProducto();
    NotificadorCambios.notificar();
  }

  private void eliminarUsuario() {
    int id = ((int) usuarioTabla.getValueAt(usuarioTabla.getSelectedRow(), 0));

    String sql = "DELETE FROM usuario WHERE id_usuario = ?";
    SetterStatement setter = (stm) -> stm.setInt(1, id);
    try {
      UtilidadesBD.sql(sql, setter);
    } catch (Exception ex) {
      JOptionPane.showMessageDialog(null, "Hay productos registrados con esta categoria!");
    }
    mostrarTablaUsuarios();
    limpiarCamposUsuario();
  }
  // FUNCIONES CRUD -------------------------------------------------------------------------------------------

  private void limpiarCamposCategoria() {
    nombreCategoriaTxt.setText("");
    categoriaTabla.clearSelection();
  }

  private void limpiarCamposProducto() {
    nombreProductoTxt.setText("");
    precioVentaProductoTxt.setText("");
    comboCategoriaProducto.setSelectedIndex(0);
    productoTabla.clearSelection();
  }

  private void limpiarCamposUsuario() {
    nombreUsuarioTxt.setText("");
    contrasenaUsuarioTxt.setText("");
    tipoUsuarioCombo.setSelectedIndex(0);
    usuarioTabla.clearSelection();
  }

  @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        nombreCategoriaTxt = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        guardarCategoriaBtn = new javax.swing.JButton();
        limpiarCategoriaBtn = new javax.swing.JButton();
        eliminarCategoriaBtn = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        categoriaTabla = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        nombreProductoTxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        precioVentaProductoTxt = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        comboCategoriaProducto = new javax.swing.JComboBox<>();
        guardarProductoBtn = new javax.swing.JButton();
        limpiarProductoBtn = new javax.swing.JButton();
        eliminarProductoBtn = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        productoTabla = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        nombreUsuarioTxt = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        contrasenaUsuarioTxt = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        tipoUsuarioCombo = new javax.swing.JComboBox<>();
        guardarUsuarioBtn = new javax.swing.JButton();
        limpiarUsuarioBtn = new javax.swing.JButton();
        eliminarUsuarioBtn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        usuarioTabla = new javax.swing.JTable();

        setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 18)); // NOI18N
        jLabel1.setText("ADMINISTRADOR");

        jTabbedPane1.setBackground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        jTabbedPane1.setPreferredSize(new java.awt.Dimension(260, 507));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel2.setText("Nombre:");

        jLabel3.setText("Agregar nueva categoria:");

        guardarCategoriaBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarCategoriaBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        guardarCategoriaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarCategoriaBtn.setText("Guardar categoria");
        guardarCategoriaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        guardarCategoriaBtn.setIconTextGap(10);
        guardarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarCategoriaBtnActionPerformed(evt);
            }
        });

        limpiarCategoriaBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarCategoriaBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        limpiarCategoriaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N
        limpiarCategoriaBtn.setText("Limpiar campos");
        limpiarCategoriaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        limpiarCategoriaBtn.setIconTextGap(10);
        limpiarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarCategoriaBtnActionPerformed(evt);
            }
        });

        eliminarCategoriaBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarCategoriaBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        eliminarCategoriaBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarCategoriaBtn.setText("Eliminar dato seleccionado");
        eliminarCategoriaBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        eliminarCategoriaBtn.setEnabled(false);
        eliminarCategoriaBtn.setIconTextGap(10);
        eliminarCategoriaBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarCategoriaBtnActionPerformed(evt);
            }
        });

        categoriaTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane4.setViewportView(categoriaTabla);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(eliminarCategoriaBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(limpiarCategoriaBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarCategoriaBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreCategoriaTxt, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 596, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreCategoriaTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(guardarCategoriaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiarCategoriaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarCategoriaBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Categoria", jPanel1);

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        jLabel4.setText("Agregar nuevo producto");

        jLabel5.setText("Nombre:");

        jLabel6.setText("Precio de venta:");

        jLabel8.setText("Categoria:");

        guardarProductoBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarProductoBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        guardarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarProductoBtn.setText("Guardar producto");
        guardarProductoBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        guardarProductoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        guardarProductoBtn.setIconTextGap(10);
        guardarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarProductoBtnActionPerformed(evt);
            }
        });

        limpiarProductoBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarProductoBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        limpiarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N
        limpiarProductoBtn.setText("Limpiar campos");
        limpiarProductoBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        limpiarProductoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        limpiarProductoBtn.setIconTextGap(10);
        limpiarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarProductoBtnActionPerformed(evt);
            }
        });

        eliminarProductoBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarProductoBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        eliminarProductoBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarProductoBtn.setText("Eliminar dato seleccionado");
        eliminarProductoBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eliminarProductoBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        eliminarProductoBtn.setEnabled(false);
        eliminarProductoBtn.setIconTextGap(10);
        eliminarProductoBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarProductoBtnActionPerformed(evt);
            }
        });

        productoTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane3.setViewportView(productoTabla);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(eliminarProductoBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(limpiarProductoBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarProductoBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(comboCategoriaProducto, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(precioVentaProductoTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreProductoTxt, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(precioVentaProductoTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboCategoriaProducto, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(guardarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarProductoBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Productos", jPanel2);

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel9.setText("Agregar nuevo usuario:");

        jLabel10.setText("Nombre:");

        jLabel11.setText("Contraseña:");

        jLabel12.setText("Tipo de usuario:");

        tipoUsuarioCombo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Seleccionar", "Administrador", "Vendedor" }));

        guardarUsuarioBtn.setBackground(new java.awt.Color(102, 204, 255));
        guardarUsuarioBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        guardarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MdiFloppy.png"))); // NOI18N
        guardarUsuarioBtn.setText("Guardar usuario");
        guardarUsuarioBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        guardarUsuarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        guardarUsuarioBtn.setIconTextGap(10);
        guardarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarUsuarioBtnActionPerformed(evt);
            }
        });

        limpiarUsuarioBtn.setBackground(new java.awt.Color(102, 204, 255));
        limpiarUsuarioBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        limpiarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/CarbonClean.png"))); // NOI18N
        limpiarUsuarioBtn.setText("Limpiar campos");
        limpiarUsuarioBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        limpiarUsuarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        limpiarUsuarioBtn.setIconTextGap(10);
        limpiarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                limpiarUsuarioBtnActionPerformed(evt);
            }
        });

        eliminarUsuarioBtn.setBackground(new java.awt.Color(255, 0, 102));
        eliminarUsuarioBtn.setFont(new java.awt.Font("Segoe UI Semibold", 2, 14)); // NOI18N
        eliminarUsuarioBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Recursos/Imagenes/MaterialSymbolsDelete.png"))); // NOI18N
        eliminarUsuarioBtn.setText("Eliminar dato seleccionado");
        eliminarUsuarioBtn.setBorder(javax.swing.BorderFactory.createEmptyBorder(1, 1, 1, 1));
        eliminarUsuarioBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        eliminarUsuarioBtn.setEnabled(false);
        eliminarUsuarioBtn.setIconTextGap(10);
        eliminarUsuarioBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarUsuarioBtnActionPerformed(evt);
            }
        });

        usuarioTabla.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane2.setViewportView(usuarioTabla);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(eliminarUsuarioBtn, javax.swing.GroupLayout.DEFAULT_SIZE, 220, Short.MAX_VALUE)
                    .addComponent(limpiarUsuarioBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(guardarUsuarioBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(tipoUsuarioCombo, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(contrasenaUsuarioTxt, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nombreUsuarioTxt, javax.swing.GroupLayout.Alignment.LEADING))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 624, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 466, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nombreUsuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contrasenaUsuarioTxt, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tipoUsuarioCombo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(guardarUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(limpiarUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(eliminarUsuarioBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jTabbedPane1.addTab("Usuarios", jPanel3);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addContainerGap(714, Short.MAX_VALUE))
                    .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jTabbedPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void guardarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarCategoriaBtnActionPerformed
      String nombre = nombreCategoriaTxt.getText();
      
      if (categoriaTabla.getSelectedRow() != -1)
        modificarCategoria(nombre);
      else
        guardarCategoria(nombre);
    }//GEN-LAST:event_guardarCategoriaBtnActionPerformed

    private void guardarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarProductoBtnActionPerformed
      String nombre = nombreProductoTxt.getText();
      int precioVenta = Integer.parseInt(precioVentaProductoTxt.getText());
      int idCategoria = ((Categoria) comboCategoriaProducto.getSelectedItem()).getId();

      if (productoTabla.getSelectedRow() != -1)
        modificarProducto(nombre, precioVenta, idCategoria);
      else
        guardarProducto(nombre, precioVenta, idCategoria);
      
      NotificadorCambios.notificar();
    }//GEN-LAST:event_guardarProductoBtnActionPerformed

    private void guardarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarUsuarioBtnActionPerformed
      String nombre = nombreUsuarioTxt.getText();
      String contra = contrasenaUsuarioTxt.getText();
      int idTipoUsuario = switch (((String) tipoUsuarioCombo.getSelectedItem())) {
        case "Administrador" ->
          1;
        case "Vendedor" ->
          2;
        default ->
          0;
      };
      
      if (usuarioTabla.getSelectedRow() != -1)
        modificarUsuario(nombre, contra, idTipoUsuario);
      else
        guardarUsuario(nombre, contra, idTipoUsuario);
    }//GEN-LAST:event_guardarUsuarioBtnActionPerformed

    private void limpiarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarCategoriaBtnActionPerformed
      limpiarCamposCategoria();
    }//GEN-LAST:event_limpiarCategoriaBtnActionPerformed

    private void limpiarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarProductoBtnActionPerformed
      limpiarCamposProducto();
    }//GEN-LAST:event_limpiarProductoBtnActionPerformed

    private void limpiarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarUsuarioBtnActionPerformed
      limpiarCamposUsuario();
    }//GEN-LAST:event_limpiarUsuarioBtnActionPerformed

    private void eliminarCategoriaBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarCategoriaBtnActionPerformed
      eliminarCategoria();
    }//GEN-LAST:event_eliminarCategoriaBtnActionPerformed

    private void eliminarProductoBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarProductoBtnActionPerformed
      eliminarProducto();
    }//GEN-LAST:event_eliminarProductoBtnActionPerformed

    private void eliminarUsuarioBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarUsuarioBtnActionPerformed
      eliminarUsuario();
    }//GEN-LAST:event_eliminarUsuarioBtnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable categoriaTabla;
    private javax.swing.JComboBox<Categoria> comboCategoriaProducto;
    private javax.swing.JTextField contrasenaUsuarioTxt;
    private javax.swing.JButton eliminarCategoriaBtn;
    private javax.swing.JButton eliminarProductoBtn;
    private javax.swing.JButton eliminarUsuarioBtn;
    private javax.swing.JButton guardarCategoriaBtn;
    private javax.swing.JButton guardarProductoBtn;
    private javax.swing.JButton guardarUsuarioBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane1;
    private javax.swing.JButton limpiarCategoriaBtn;
    private javax.swing.JButton limpiarProductoBtn;
    private javax.swing.JButton limpiarUsuarioBtn;
    private javax.swing.JTextField nombreCategoriaTxt;
    private javax.swing.JTextField nombreProductoTxt;
    private javax.swing.JTextField nombreUsuarioTxt;
    private javax.swing.JTextField precioVentaProductoTxt;
    private javax.swing.JTable productoTabla;
    private javax.swing.JComboBox<String> tipoUsuarioCombo;
    private javax.swing.JTable usuarioTabla;
    // End of variables declaration//GEN-END:variables

}
