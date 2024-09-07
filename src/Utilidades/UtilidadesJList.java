
package Utilidades;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;

public class UtilidadesJList {
    private static Connection conn = Conector.obtenerConexion();
    
    // Metodo para llenar un JList a partir de una consulta SQL
    public static void mostrarDatosLista(String sql, JList jList, 
            SetterStatement setter, CreadorObjeto creador) {
        
        DefaultListModel modelo = new DefaultListModel();
        jList.setModel(modelo);
        
        try (PreparedStatement stm = conn.prepareStatement(sql)) {
            setter.settearDatosStatement(stm);
            ResultSet rs = stm.executeQuery();
            
            while (rs.next()) {
                Object obj = creador.crearObjeto(rs);
                modelo.addElement(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesJList.class.getName()).log(Level.SEVERE,
                    null, ex);
        }
    }
}
