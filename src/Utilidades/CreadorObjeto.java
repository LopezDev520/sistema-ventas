package Utilidades;

import java.sql.ResultSet;
import java.sql.SQLException;

// Interfaz funcional para crear un objeto a partir de un ResultSet
@FunctionalInterface
public interface CreadorObjeto<T> {
    T crearObjeto(ResultSet rs) throws SQLException;
}
