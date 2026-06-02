package model;

public class Pedido {
    private int idPedido;
    private int idCliente;
    private String fechaPedido;
    private double total;
    private String estado;
    private String metodoPago;
    
    public Pedido() {}
    
    public Pedido(int idCliente, double total, String metodoPago) {
        this.idCliente = idCliente;
        this.total = total;
        this.metodoPago = metodoPago;
        this.estado = "PENDIENTE";
    }
    
    // Getters y Setters
    public int getIdPedido() { return idPedido; }
    public void setIdPedido(int idPedido) { this.idPedido = idPedido; }
    
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }
    
    public String getFechaPedido() { return fechaPedido; }
    public void setFechaPedido(String fechaPedido) { this.fechaPedido = fechaPedido; }
    
    public double getTotal() { return total; }
    public void setTotal(double total) { this.total = total; }
    
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    
    public String getMetodoPago() { return metodoPago; }
    public void setMetodoPago(String metodoPago) { this.metodoPago = metodoPago; }
    
    @Override
    public String toString() {
        return idPedido + " | Cliente ID: " + idCliente + " | $" + total + " | " + estado;
    }
}