
package Utilidades;


import java.sql.PreparedStatement;
import java.sql.SQLException;

// Interfaz funcional para establecer los argumentos a un PreparedStatement
@FunctionalInterface
public interface SetterStatement {
    void settearDatosStatement(PreparedStatement stm) throws SQLException;
}