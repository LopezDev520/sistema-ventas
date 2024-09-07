package Principal;

import Modelos.Usuario;
import Utilidades.UtilidadesBD;
import com.formdev.flatlaf.themes.FlatMacLightLaf;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class Main {

    public static void main(String[] args) throws UnsupportedLookAndFeelException {

        // Establezco el Look And Feel, proporcionado por la biblioteca FlatLaf
        UIManager.setLookAndFeel(new FlatMacLightLaf());

        Usuario usuario = null;
        Login login = new Login();
        boolean sesionIniciada = false;

        do {
            login.setVisible(true);

            if (!login.isAceptado()) {
                break;
            }

            String user = login.getUsuario();
            String pass = login.getPass();

            if (user.isEmpty() || user.isBlank()) {
                JOptionPane.showMessageDialog(login, "Ingrese el usuario");
                continue;
            }

            if (pass.isEmpty() || pass.isBlank()) {
                JOptionPane.showMessageDialog(login, "Ingrese la contraseña");
                continue;
            }

            try (PreparedStatement stmt = UtilidadesBD.conn.prepareStatement("SELECT * FROM usuario WHERE nombre = ?")) {
                stmt.setString(1, user);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (!rs.next()) {
                        JOptionPane.showMessageDialog(login, "Usuario no encontrado");
                        login.limpiarCampos();
                        login.setAceptado(false);
                    }

                    String dbUser = rs.getString(2);
                    String dbPass = rs.getString(3);

                    if (!user.equals(dbUser) && !pass.equals(dbPass)) {
                        break;
                    }

                    usuario = new Usuario();
                    usuario.setId(rs.getInt(1));
                    usuario.setNombre(rs.getString(2));
                    usuario.setIdTipoUsuario(rs.getInt(4));
                    sesionIniciada = true;
                    Global.usuarioIniciado = dbUser;
                    break;
                }
            } catch (SQLException ex) {
            }
        } while (true);

        if (sesionIniciada && usuario != null) {
            PrincipalFrame pf = new PrincipalFrame(usuario);
            pf.setVisible(true);
        }

    }

}
