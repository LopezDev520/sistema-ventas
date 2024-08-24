package UtilidadesGraficas;

public class MainDialog {

    public static void main(String[] args) {
        String msg = "Seguro que desea eliminar el archivo?";
        DeleteConfirmDialog deleteConfirmDialog = new DeleteConfirmDialog(null, msg);
        deleteConfirmDialog.setVisible(true);
        
        
        if (deleteConfirmDialog.isAceptado()) {
            System.out.println("Se aceptó!");
        } else {
            System.out.println("No se aceptó!");
        }
        
    }
    
}
