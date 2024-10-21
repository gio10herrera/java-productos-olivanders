import javax.swing.*;

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
        existenciasProductos = new Object[2][n];
        for (int i = 0; i < n; i++) {
            nombre = JOptionPane.showInputDialog(null, "Digite el nombre del producto", "Nombre del producto", JOptionPane.INFORMATION_MESSAGE);
            numSerie = JOptionPane.showInputDialog(null, "Numero de serie", "Numero de serie", JOptionPane.INFORMATION_MESSAGE);
            valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el valor", "Valor", JOptionPane.INFORMATION_MESSAGE));
            nombreProveedor = JOptionPane.showInputDialog(null, "Nombre del producto", "Nombre del producto", JOptionPane.INFORMATION_MESSAGE);
            existencias = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de existencias del producto", "Existencias", JOptionPane.INFORMATION_MESSAGE));

            productos[i] = new Producto(id, nombre, numSerie, valor, nombreProveedor);
            existenciasProductos[i][0] = productos[i];
            existenciasProductos[i][1] = existencias;
            id++;
        }
    }
}
