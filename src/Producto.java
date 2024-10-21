public class Producto {
    private int id;
    private String nombre;
    private String numSerie;
    private double valor;
    private String nombreProveedor;
    
    public Producto(int id, String nombre, String numSerie, double valor, String nombreProveedor) {
        this.id = id;
        this.nombre = nombre;
        this.numSerie = numSerie;
        this.valor = valor;
        this.nombreProveedor = nombreProveedor;
    }
}
