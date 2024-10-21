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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getNumSerie() {
        return numSerie;
    }

    public void setNumSerie(String numSerie) {
        this.numSerie = numSerie;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getNombreProveedor() {
        return nombreProveedor;
    }

    public void setNombreProveedor(String nombreProveedor) {
        this.nombreProveedor = nombreProveedor;
    }
}
