package Utilidades.Notificador;

import java.util.ArrayList;

public class NotificadorCambios {
  private static ArrayList<Notificacion> notificaciones = new ArrayList<>();
  
  // Metodo para registrar una notificacion
  public static void registrarNotificacion(Notificacion notificacion) {
    notificaciones.add(notificacion);
  }
  
  // Ejecuta todas las funciones de notificacion guardadas en "notificaciones"
  public static void notificar() {
    for (Notificacion n : notificaciones) n.notificar();
  }
}
