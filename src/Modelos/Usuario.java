package Modelos;

public class Usuario {

    private int id;
    private String nombre;
    private int idTipoUsuario;

    public Usuario() {
    }

    public Usuario(int id, String nombre, int idTipoUsuario) {
        this.id = id;
        this.nombre = nombre;
        this.idTipoUsuario = idTipoUsuario;
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

    public int getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(int idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }

}
