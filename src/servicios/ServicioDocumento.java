package servicios;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import entidades.Documento;

public class ServicioDocumento {
    private static List<Documento> documentos = new ArrayList<>();

    public static void cargar(String nombreArchivo) {
        var br = Archivo.abrirArchivo(nombreArchivo);
        if (br != null) {
            try {
                var linea = br.readLine();
                linea = br.readLine();
                while (linea != null) {
                    var textos = linea.split(";");
                    var documento = new Documento(textos[0], textos[1], textos[2], textos[3]);
                    documentos.add(documento);
                    linea = br.readLine();
                }
            } catch (Exception ex) {

            }
        }
    }

    public static void mostrar(JTable tbl) {
        String[] encabezados = new String[] { "#", "Primer Apellido", "Segundo Apellido", "Nombres", "Documento" };
        String[][] datos = new String[documentos.size()][encabezados.length];

        int fila = 0;
        for (var documento : documentos) {
            datos[fila][0] = String.valueOf(fila + 1);
            datos[fila][1] = documento.getApellido1();
            datos[fila][2] = documento.getApellido2();
            datos[fila][3] = documento.getNombre();
            datos[fila][4] = documento.getDocumento();
            fila++;
        }

        var dtm = new DefaultTableModel(datos, encabezados);
        tbl.setModel(dtm);
    }
}
