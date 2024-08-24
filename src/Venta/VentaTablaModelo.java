
package Venta;

import Modelos.Producto;
import Modelos.VentaItem;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.AbstractTableModel;

public class VentaTablaModelo extends AbstractTableModel {
    
    private ArrayList<VentaItem> ventaItems = new ArrayList<>();

    String[] columnas = {
        "Producto",
        "Precio",
        "Cantidad",
        "Total"
    };
    
    public void agregarProductoACarrito(Producto p, int cantidad) {
        if (cantidad <= 0) {
            JOptionPane.showMessageDialog(null, "No puede comprar 0 o menos cantidades");
            return;
        }
        
        if (cantidad > p.getCantidadStock()) {
            JOptionPane.showMessageDialog(null, "No hay suficiente cantidad");
            return;
        }
        
        VentaItem vi = new VentaItem();
        vi.setProducto(p);
        vi.setCantidad(cantidad);
        ventaItems.add(vi);
        fireTableDataChanged();
    }
    
    @Override
    public String getColumnName(int column) {
        if (column < 0 || column >= columnas.length) return null;
        return columnas[column];
    }

    @Override
    public int getRowCount() {
        return ventaItems.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        VentaItem vi = ventaItems.get(rowIndex);
        
        return switch (columnIndex) {
            case 0 -> vi.getProducto();
            case 1 -> vi.getProducto().getPrecioVenta();
            case 2 -> vi.getCantidad(); // cantidad de venta
            case 3 -> vi.getProducto().getPrecioVenta() * vi.getCantidad();
            default -> null;
        };
    }
    
}
