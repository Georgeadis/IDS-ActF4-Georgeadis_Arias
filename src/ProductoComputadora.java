public class ProductoComputadora extends Producto {

    public ProductoComputadora(int productoId, String nombre, String descripcion, String categoria,
                               double precioUnitario, int stockActual, Proveedor proveedor) {
        super(productoId, nombre, descripcion, categoria, precioUnitario, stockActual, proveedor);
    }

    @Override
    public String tipoProducto() {
        return "Computadora";
    }

}
