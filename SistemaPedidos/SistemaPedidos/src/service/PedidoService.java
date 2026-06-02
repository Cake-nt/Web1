package service;

import dao.PedidoDAO;        // ← Importa desde DAO
import model.Pedido;          // ← Importa desde model
import model.DetallePedido;   // ← Importa desde model
import java.util.List;

public class PedidoService {
    
    private PedidoDAO pedidoDAO;
    
    public PedidoService() {
        this.pedidoDAO = new PedidoDAO();
    }
    
    public int realizarPedido(int idCliente, int idProducto, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return -1;
        }
        return pedidoDAO.registrarPedido(idCliente, idProducto, cantidad);
    }
    
    public List<Pedido> listarPedidos() {
        return pedidoDAO.obtenerTodosPedidos();
    }
    
    public List<Pedido> listarPedidosPorCliente(int idCliente) {
        return pedidoDAO.obtenerPedidosPorCliente(idCliente);
    }
    
    public List<DetallePedido> listarDetallesPedido(int idPedido) {
        return pedidoDAO.obtenerDetallesPedido(idPedido);
    }
    
    public boolean cambiarEstadoPedido(int idPedido, String estado) {
        return pedidoDAO.actualizarEstadoPedido(idPedido, estado);
    }
}