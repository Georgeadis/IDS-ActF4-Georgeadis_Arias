public abstract class Producto implements Calculable {

    protected int productoId;
    protected String nombre;
    protected String descripcion;
    protected String categoria;
    protected double precioUnitario;
    protected int stockActual;
    protected Proveedor proveedor;

    public Producto(int productoId, String nombre, String descripcion, String categoria,
                    double precioUnitario, int stockActual, Proveedor proveedor) {
        this.productoId = productoId;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.categoria = categoria;
        this.precioUnitario = precioUnitario;
        this.stockActual = stockActual;
        this.proveedor = proveedor;
    }

    public abstract String tipoProducto();

    @Override
    public double calcularValorInventario() {
        return precioUnitario * stockActual;
    }

    @Override
    public String toString() {
        return "ID:" + productoId + " - " + tipoProducto() + " - " + nombre + " $" + precioUnitario + " Stock: " + stockActual + " Proveedor: " + (proveedor != null ? proveedor.getNombre() : "-");
    }

}
