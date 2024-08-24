
package Modelos;

public class Producto {

    private int id;
    private String nombre;
    private String descripcion;
    private int precioVenta;
    private int precioCompra;
    private int cantidadStock;
    private int idCategoria;

    public Producto() {}
    
    public Producto(int id, String nombre, String descripcion, int precioVenta, int precioCompra, int cantidadStock, int idCategoria) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precioVenta = precioVenta;
        this.precioCompra = precioCompra;
        this.cantidadStock = cantidadStock;
        this.idCategoria = idCategoria;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getPrecioVenta() {
        return precioVenta;
    }

    public void setPrecioVenta(int precioVenta) {
        this.precioVenta = precioVenta;
    }

    public int getPrecioCompra() {
        return precioCompra;
    }

    public void setPrecioCompra(int precioCompra) {
        this.precioCompra = precioCompra;
    }

    public int getCantidadStock() {
        return cantidadStock;
    }

    public void setCantidadStock(int cantidadStock) {
        this.cantidadStock = cantidadStock;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    @Override
    public String toString() {
        return nombre;
    }

    public void presentarProducto() {
        System.out.println("ID:"  + id);
        System.out.println("Nombre: " + nombre);
        System.out.println("Cantidad: " + cantidadStock);
    }
    
}
