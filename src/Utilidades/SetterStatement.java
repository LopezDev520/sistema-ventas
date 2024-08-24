
package Utilidades;


import java.sql.PreparedStatement;
import java.sql.SQLException;

@FunctionalInterface
public interface SetterStatement {
    void settearDatosStatement(PreparedStatement stm) throws SQLException;
}