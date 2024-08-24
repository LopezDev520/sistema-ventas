package Utilidades;

import java.sql.ResultSet;
import java.sql.SQLException;

@FunctionalInterface
public interface CreadorObjeto<T> {
    T crearObjeto(ResultSet rs) throws SQLException;
}
