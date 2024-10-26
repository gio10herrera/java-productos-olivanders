import java.util.Map;

public class Venta {
    private int numFactura;
    private Map<Producto, Integer> productosVenta;
    private double total;

    public Venta(int numFactura, Map<Producto, Integer> productosVenta) {
        this.numFactura = numFactura;
        this.productosVenta = productosVenta;
        this.total = 0;
    }

    public void calcularTotal() {
        for (Producto producto : productosVenta.keySet()) {
            int cant = productosVenta.get(producto);
            total += producto.getValor() * cant;
        }
    }

    public int getNumFactura() {
        return numFactura;
    }

    public void setNumFactura(int numFactura) {
        this.numFactura = numFactura;
    }

    public Map<Producto, Integer> getProductosVenta() {
        return productosVenta;
    }

    public void setProductosVenta(Map<Producto, Integer> productosVenta) {
        this.productosVenta = productosVenta;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        String stringToShow = "Numero de factura: " + this.numFactura + "\n\nProducto\tCantidad\tValor unit\tTotal parcial\n";
        for (Producto producto : productosVenta.keySet()) {
            int cant = productosVenta.get(producto);
            stringToShow += producto.getNombre() + "\t" + cant + "\t" + producto.getValor() + "\t" + (cant * producto.getValor()) + "\n";
        }
        stringToShow += "\n\n\t\t\tTotal a Pagar: " + this.total + "\n";
        return stringToShow;
    }
}
