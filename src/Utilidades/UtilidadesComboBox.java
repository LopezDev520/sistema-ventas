package Utilidades;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JComboBox;
import java.sql.*;

public class UtilidadesComboBox {
    private static Connection conn = Conector.obtenerConexion();
    
    // Metodo para llenar un ComboBox a partir de una consulta SQL
    public static void mostrarDatosComboBox(String sql, JComboBox comboBox, CreadorObjeto creador) {
        comboBox.removeAllItems();

        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            ResultSet rs = stm.executeQuery();

            while (rs.next()) {
                Object obj = creador.crearObjeto(rs);
                comboBox.addItem(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesComboBox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
