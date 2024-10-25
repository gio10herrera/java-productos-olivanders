import com.sun.tools.javac.Main;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ComercializadoraOlivanders {
    private static final Logger logger = Logger.getLogger(ComercializadoraOlivanders.class.getName());
    static List<Producto> productos = new ArrayList<>();
    static Map<Producto, Integer> productosStock = new HashMap<>();
    static int id = 1; //automatizar asignacion de id
    static boolean salir = false;
    static int numFactura = 1010;
    static List<Venta> ventas = new ArrayList<>();

    public static void main(String[] args) {
        int opcion;
        opcion = menu();
        while (!salir) {
            switch (opcion) {
                case 1 -> {
                    registrarProducto();
                    opcion = menu();
                }
                case 2 -> {
                    modificarProducto();
                    opcion = menu();
                }
                case 3 -> {
                    eliminarProducto();
                    opcion = menu();
                }
                case 4 -> {
                    listarProductos();
                    opcion = menu();
                }
                case 5 -> {
                    nuevaVenta();
                    opcion = menu();
                }
                case 6 -> {
                    mostrarVenta();
                    opcion = menu();
                }
                case 7 -> {
                    listarVentas();
                    opcion = menu();
                }
                case 8 -> salir = true;
                default -> JOptionPane.showMessageDialog(null, "Opcion no valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private static void nuevaVenta() {
        if (!productosStock.isEmpty()) {
            int codigo, cant;
            Map<Producto, Integer>  productosVenta  = new HashMap<>();

            int numProductos = Integer.parseInt(JOptionPane.showInputDialog(null, "Cuantos productos desea comprar?", "Numero de productos", JOptionPane.INFORMATION_MESSAGE));
            for (int i = 0; i < numProductos; i++) {
                String stringToShow = "Digita el codigo del producto a comprar\n\n";
                for (Producto producto : productosStock.keySet()) {
                    int stock = productosStock.get(producto);
                    stringToShow += "Codigo: " + producto.getId() + ". " + producto.getNombre() + " - La cantidad no puede ser mayor a " + stock + "\n";
                }
                codigo = Integer.parseInt(JOptionPane.showInputDialog(null, stringToShow, "Producto", JOptionPane.INFORMATION_MESSAGE));
                Producto p = getProductoById(codigo);
                if (p != null) {
                    boolean sw = false;
                    int cantActual = productosStock.get(p);
                    cant = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite la cantidad, no debe ser mayor a: " + cantActual, "Cantidad", JOptionPane.INFORMATION_MESSAGE));
                    while (cant > cantActual) {
                        cant = Integer.parseInt(JOptionPane.showInputDialog(null, "ERROR! cantidad, no debe ser mayor a: " + cantActual, "Digite la Cantidad", JOptionPane.INFORMATION_MESSAGE));
                    }
                    productosVenta.put(p, cant);
                    int actualizarCant = cantActual - cant;
                    productosStock.put(p, actualizarCant);
                } else {
                    JOptionPane.showMessageDialog(null, "El producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            if (!productosVenta.isEmpty()) {
                Venta venta = new Venta(numFactura++, productosVenta);
                venta.calcularTotal();
                ventas.add(venta);
                JOptionPane.showMessageDialog(null, venta.toString(), "Factura de Venta", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Intente realizar la venta de nuevo, Error en los productos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "La lista de productos esta vacia", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static void eliminarProducto() {
        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el id del producto", "Buscar producto", JOptionPane.INFORMATION_MESSAGE));
        Producto p = getProductoById(id);
        if (p != null) {
            if (productos.remove(p)){
                productosStock.remove(p);
                JOptionPane.showMessageDialog(null, "Producto eliminado exitosamente", "Existoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(null, "Producto no existe", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }

    }

    private static void modificarProducto() {

        int id = Integer.parseInt(JOptionPane.showInputDialog(null, "Digite el id del producto", "Buscar producto", JOptionPane.INFORMATION_MESSAGE));
        Producto p = getProductoById(id);
        if (p != null) {
            int op = Integer.parseInt(JOptionPane.showInputDialog(null, "1. Modificar nombre del producto\n2. Modificar precio del producto\n3. Modificar nombre del proveedor\n4. Modificar Existencias", "Modificar producto", JOptionPane.INFORMATION_MESSAGE));

            switch (op) {
                case 1 -> {
                    String name = JOptionPane.showInputDialog(null, "Digite el nuevo nombre del producto", "Modificar nombre", JOptionPane.INFORMATION_MESSAGE);
                    p.setNombre(name);
                    JOptionPane.showMessageDialog(null, "Nombre modificado", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
                case 2 -> {
                    double price = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el precio del producto", "Modificar precio", JOptionPane.INFORMATION_MESSAGE));
                    p.setValor(price);
                    JOptionPane.showMessageDialog(null, "Precio modificado", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
                case 3 -> {
                    String providerName = JOptionPane.showInputDialog(null, "Digite el nuevo nombre del producto", "Modificar nombre", JOptionPane.INFORMATION_MESSAGE);
                    p.setNombreProveedor(providerName);
                    JOptionPane.showMessageDialog(null, "Nombre del proveedor modificado", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                }
                case 4 -> {
                    int stock = Integer.parseInt(JOptionPane.showInputDialog(null, "Ingrese la nueva cantidad", "Modificar stock", JOptionPane.INFORMATION_MESSAGE));
                    if (productosStock.containsKey(p)) {
                        productosStock.put(p, stock);
                        logger.log(Level.INFO, "nueva cantidad {0}", productosStock.get(p));
                        JOptionPane.showMessageDialog(null, "Existencias modificadas", "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encuentra el Producto y las existencias", "Exitoso", JOptionPane.ERROR_MESSAGE);
                    }
                }
                default -> JOptionPane.showMessageDialog(null, "Opcion no valida", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(null, "No existe el producto", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private static Producto getProductoById(int id) {
        for (Producto p : productos) {
            if (p.getId() == id) {
                return p;
            }
        }
        return null;
    }

    private static int menu() {
        return Integer.parseInt(JOptionPane.showInputDialog(null, "Menu: \n1. Registrar producto\n2. Modificar producto \n3. Eliminar producto\4. Venta\n4. Mostrar productos\n5. Nueva Venta\n6. Mostrar venta\n7. Listar ventas\n8. Salir", "Numero de productos", JOptionPane.INFORMATION_MESSAGE));
    }

    private static void registrarProducto() {
        String nombre = JOptionPane.showInputDialog(null, "Digite el nombre del producto cuyo id será " + (id), "Nombre del producto", JOptionPane.INFORMATION_MESSAGE);
        String numSerie = JOptionPane.showInputDialog(null, "Numero de serie", "Numero de serie", JOptionPane.INFORMATION_MESSAGE);
        double valor = Double.parseDouble(JOptionPane.showInputDialog(null, "Digite el valor", "Valor", JOptionPane.INFORMATION_MESSAGE));
        String nombreProveedor = JOptionPane.showInputDialog(null, "Nombre del proveedor", "Proveedor", JOptionPane.INFORMATION_MESSAGE);
        int existencias = Integer.parseInt(JOptionPane.showInputDialog(null, "Numero de existencias del producto", "Existencias", JOptionPane.INFORMATION_MESSAGE));

        Producto p = new Producto(id, nombre, numSerie, valor, nombreProveedor);
        productos.add(p);
        productosStock.put(p, existencias);
        id++;
    }

    private static void listarProductos() {
        String stringToShow = "Identificacion\tNombre del Producto\tExistencia\n\n";

        for (Map.Entry<Producto, Integer> entry : productosStock.entrySet()) {
            Producto p = entry.getKey();
            int stock = entry.getValue();
            stringToShow += p.toString() + "\tExistencias: " + stock + "\n";
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
