package Utilidades.Notificador;

import java.util.ArrayList;

public class NotificadorCambios {
  private static ArrayList<Notificacion> notificaciones = new ArrayList<>();
  
  public static void registrarNotificacion(Notificacion notificacion) {
    notificaciones.add(notificacion);
  }
  
  public static void notificar() {
    for (Notificacion n : notificaciones) n.notificar();
  }
}
