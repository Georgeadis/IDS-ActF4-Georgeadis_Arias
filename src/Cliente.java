public class Cliente {
    private int clienteId;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;
    private String direccion;

    public Cliente(int clienteId, String nombre, String apellido, String telefono, String email, String direccion) {
        this.clienteId = clienteId;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
        this.direccion = direccion;
    }

    public int getClienteId() { return clienteId; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getTelefono() { return telefono; }
    public String getEmail() { return email; }
    public String getDireccion() { return direccion; }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (" + email + ")";
    }

}
