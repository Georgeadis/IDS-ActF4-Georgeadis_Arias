public class Proveedor {
    private int proveedorId;
    private String nombre;
    private String telefono;
    private String email;
    private String direccion;

    public Proveedor(int proveedorId, String nombre, String telefono, String email, String direccion) {
        this.proveedorId = proveedorId;
        this.nombre = nombre;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getProveedorId() { return proveedorId; }
    public String getNombre() { return nombre; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }

    @Override
    public String toString() {
        return nombre + " (" + email + ")";
    }

}
