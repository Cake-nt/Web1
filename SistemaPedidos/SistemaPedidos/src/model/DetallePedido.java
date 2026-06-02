package model;

public class DetallePedido {
    private int idDetalle;
    private int idPedido;
    private int idProducto;
    private int cantidad;
    private double precioUnitario;
    private double subtotal;
    
    // Constructor vacío
    public DetallePedido() {}
    
    // Constructor completo
    public DetallePedido(int idDetalle, int idPedido, int idProducto, 
                         int cantidad, double precioUnitario, double subtotal) {
        this.idDetalle = idDetalle;
        this.idPedido = idPedido;
        this.idProducto = idProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.subtotal = subtotal;
    }
    
    // Getters y Setters
    public int getIdDetalle() { return idDetalle; }
    public void setIdDetalle(int idDetalle) { this.idDetalle = idDetalle; }
    
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    
    public int getIdProducto() { return idProducto; }
    public void setIdProducto(int idProducto) { this.idProducto = idProducto; }
    
    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }
    
    public double getPrecioUnitario() { return precioUnitario; }
    public void setPrecioUnitario(double precioUnitario) { this.precioUnitario = precioUnitario; }
    
    public double getSubtotal() { return subtotal; }
    public void setSubtotal(double subtotal) { this.subtotal = subtotal; }
    
    @Override
    public String toString() {
        return "Producto ID: " + idProducto + " | Cantidad: " + cantidad + 
               " | Subtotal: $" + subtotal;
    }
}