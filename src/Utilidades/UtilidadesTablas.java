package Utilidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UtilidadesTablas {

    private static Connection conn = Conector.obtenerConexion();
    
    public static void mostrarTabla(String[] columnas, String sqlQuery, JTable tabla, Class[] tiposDatosColumnas) {
        DefaultTableModel modelo = new DefaultTableModel();
        for (String col : columnas) {
            modelo.addColumn(col);
        }
        tabla.setModel(modelo);
        Object[] datos = new Object[columnas.length];

        try (PreparedStatement stm = conn.prepareStatement(sqlQuery)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                for (int i = 0; i < columnas.length; i++) {
                    if (tiposDatosColumnas[i] == Integer.class) {
                        datos[i] = rs.getInt(i + 1);
                    } else if (tiposDatosColumnas[i] == String.class) {
                        datos[i] = rs.getString(i + 1);
                    } else if (tiposDatosColumnas[i] == Date.class) {
                        datos[i] = rs.getDate(i);
                    }
                }

                modelo.addRow(datos);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesTablas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
