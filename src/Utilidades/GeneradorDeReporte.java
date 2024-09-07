package Utilidades;

import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class GeneradorDeReporte {

    public static void emitirReporte(String nombre, String sql) {
        try {
            Workbook libro = new XSSFWorkbook();
            generarHoja(libro, nombre, sql, null);
            guardarReporte(libro, nombre);
        } catch (SQLException ex) {
            Logger.getLogger(GeneradorDeReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void emitirReporte(String[] nombres, String[] sqls, String[][] columnas) {
        try {
            Workbook libro = new XSSFWorkbook();
            for (int i = 0; i < nombres.length; i++) {
                String nombre = nombres[i];
                String sql = sqls[i];
                String[] columnasActuales = null;
                if (columnas != null) {
                     columnasActuales = columnas[i];
                }
                System.out.println(sql);
                generarHoja(libro, nombre, sql, columnasActuales);
            }

            Date date = new Date();
            guardarReporte(libro, "Reporte " + date.getTime());
        } catch (SQLException ex) {
            Logger.getLogger(GeneradorDeReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void generarHoja(Workbook libro, String nombre, String sql, String[] columnas) throws SQLException {
        Connection conn = Conector.obtenerConexion();
        PreparedStatement stm = conn.prepareStatement(sql);
        ResultSet datos = stm.executeQuery();
        Sheet hoja = libro.createSheet(nombre);
        hoja.setZoom(150);

        // generar columnas
        Row row = hoja.createRow(0);
        
        int numColumnas = columnas != null ? columnas.length : datos.getMetaData().getColumnCount();

        if (columnas != null) {
            for (int i = 0; i < numColumnas; i++) {
                Cell celda = row.createCell(i);
                celda.setCellValue(columnas[i]);
                celda.setCellStyle(obtenerEstiloEncabezados(libro));
            }
        } else {
            for (int i = 1; i <= numColumnas; i++) {
                Cell celda = row.createCell(i - 1);
                celda.setCellValue(datos.getMetaData().getColumnName(i));
                celda.setCellStyle(obtenerEstiloEncabezados(libro));
            }
        }

        // llenar datos
        int numFila = 1;
        while (datos.next()) {
            Row fila = hoja.createRow(numFila);
            for (int i = 1; i <= numColumnas; i++) {
                Cell celda = fila.createCell(i - 1);
                celda.setCellValue(datos.getString(i));
                celda.setCellStyle(obtenerEstiloCeldaDatos(libro));
            }
            numFila++;
        }

        for (int i = 1; i <= numColumnas; i++) {
            hoja.autoSizeColumn(i);
        }
    }

    private static CellStyle obtenerEstiloEncabezados(Workbook libro) {
        CellStyle estilo = libro.createCellStyle();
        estilo.setBorderBottom(BorderStyle.THICK);
        estilo.setBorderRight(BorderStyle.THICK);
        estilo.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex());
        estilo.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        return estilo;
    }

    private static CellStyle obtenerEstiloCeldaDatos(Workbook libro) {
        CellStyle estilo = libro.createCellStyle();
        estilo.setBorderTop(BorderStyle.THIN);
        estilo.setBorderLeft(BorderStyle.THIN);
        estilo.setBorderRight(BorderStyle.THIN);
        estilo.setBorderBottom(BorderStyle.THIN);
        return estilo;
    }

    private static void guardarReporte(Workbook libro, String nombreReporte) {
        try {
            String ruta = obtenerRutaGuardado();
            if (ruta == null) {
                return;
            }
            OutputStream output = new FileOutputStream(ruta + "/" + nombreReporte + ".xlsx");
            libro.write(output);
            libro.close();

            abrirArchivo(ruta + "/" + nombreReporte + ".xlsx");

        } catch (FileNotFoundException ex) {
            Logger.getLogger(GeneradorDeReporte.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorDeReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static String obtenerRutaGuardado() {
        JFileChooser folderChooser = new JFileChooser();
        folderChooser.setDialogTitle("Seleccione la ruta para guardar el reporte");
        folderChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        folderChooser.setAcceptAllFileFilterUsed(false);

        int userSelection = folderChooser.showOpenDialog(null);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File carpetaSeleccionada = folderChooser.getSelectedFile();
            return carpetaSeleccionada.getAbsolutePath(); // La ruta
        } else {
            return null;
        }

    }

    private static void abrirArchivo(String rutaCompleta) {
        try {
            File file = new File(rutaCompleta);
            Desktop.getDesktop().open(file);
        } catch (IOException ex) {
            Logger.getLogger(GeneradorDeReporte.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
