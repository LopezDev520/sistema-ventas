package Utilidades;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conector {
    
    public static Connection obtenerConexion() {
        Connection conn = null;

        try {
            // URL para la conexion a la BD
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/inventario", "root", "");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
            Logger.getLogger(Conector.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
