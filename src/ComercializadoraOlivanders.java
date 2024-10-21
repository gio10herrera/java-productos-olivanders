import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class ComercializadoraOlivanders {
    static Producto[] productos;
    static Object[][] existenciasProductos;
    static int id = 1; //automatizar asignacion de id

    public static void main(String[] args) {
        int n, existencias;
        String nombre, numSerie, nombreProveedor;
        double valor;
        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de productos a ingresar", "Numero de productos", JOptionPane.INFORMATION_MESSAGE));
        productos = new Producto[n];
        existenciasProductos = new Object[n][2];
        for (int i = 0; i < n; i++) {
            nombre = JOptionPane.showInputDialog(null, "Digite el nombre del producto " + (i+1), "Nombre del producto", JOptionPane.INFORMATION_MESSAGE);
            numSerie = JOptionPane.showInputDialog(null, "Numero de serie", "Numero de serie", JOptionPane.INFORMATION_MESSAGE);
            valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el valor", "Valor", JOptionPane.INFORMATION_MESSAGE));
            nombreProveedor = JOptionPane.showInputDialog(null, "Nombre del proveedor", "Proveedor", JOptionPane.INFORMATION_MESSAGE);
            existencias = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de existencias del producto " + (i+1), "Existencias", JOptionPane.INFORMATION_MESSAGE));

            productos[i] = new Producto(id, nombre, numSerie, valor, nombreProveedor);
            existenciasProductos[i][0] = productos[i];
            existenciasProductos[i][1] = existencias;
            id++;
        }

        JOptionPane.showMessageDialog(null, "Procedemos a listar los productos", "Productos", JOptionPane.INFORMATION_MESSAGE);
        listarProductos();
    }

    private static void listarProductos() {
        String stringToShow = "Identificacion\tNombre del Producto\tExistencia\n\n";
        for (int i = 0; i < productos.length; i++) {
            stringToShow += existenciasProductos[i][0].toString() + "\tExistencias: " + existenciasProductos[i][1] + "\n";
        }
        //JTextArea Configuration
        JTextArea textArea = new JTextArea();
        textArea.setEditable(false);
        textArea.setSize(460, 400);
        textArea.setBorder(new EmptyBorder(5, 45, 5, 5));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setText(stringToShow);
        JOptionPane.showMessageDialog(null, textArea, "Productos", JOptionPane.INFORMATION_MESSAGE);
    }
}
