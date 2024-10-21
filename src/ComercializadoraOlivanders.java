import javax.swing.*;

public class ComercializadoraOlivanders {
    static Producto[] productos;
    static Object[][] existenciasProductos;

    public static void main(String[] args) {
        int n;
        n = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el numero de productos a ingresar", "Numero de productos", JOptionPane.INFORMATION_MESSAGE));
        productos = new Producto[n];
        existenciasProductos = new Object[2][n];
    }
}
