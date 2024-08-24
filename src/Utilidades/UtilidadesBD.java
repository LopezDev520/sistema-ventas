package Utilidades;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UtilidadesBD {
    public static Connection conn = Conector.obtenerConexion();
    
    public static void sql(String sqlQuery, SetterStatement setterStatement) {
        try (PreparedStatement stm = conn.prepareStatement(sqlQuery)) {
            setterStatement.settearDatosStatement(stm);
            stm.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static ResultSet query(String sqlQuery) {
        ResultSet rs = null;
        
        try (PreparedStatement stm = conn.prepareStatement(sqlQuery)) {
            rs = stm.executeQuery();
        } catch (SQLException ex) {
            Logger.getLogger(UtilidadesBD.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return rs;
    }
}
