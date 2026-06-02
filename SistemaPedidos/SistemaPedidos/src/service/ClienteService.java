package service;

import model.Cliente;
import dao.ClienteDAO;
import java.util.List;

public class ClienteService {
    
    private ClienteDAO clienteDAO;
    
    public ClienteService() {
        this.clienteDAO = new ClienteDAO();
    }
    
    private boolean validar(Cliente cliente) {
        if (cliente.getNombre() == null || cliente.getNombre().trim().isEmpty()) {
            System.out.println("Error: El nombre es obligatorio");
            return false;
        }
        if (cliente.getEmail() == null || !cliente.getEmail().contains("@")) {
            System.out.println("Error: Email inválido");
            return false;
        }
        return true;
    }
    
    public boolean registrar(Cliente cliente) {
        if (!validar(cliente)) return false;
        return clienteDAO.insertar(cliente);
    }
    
    public List<Cliente> listar() {
        return clienteDAO.obtenerTodos();
    }
    
    public Cliente buscar(int id) {
        return clienteDAO.obtenerPorId(id);
    }
    
    public boolean actualizar(Cliente cliente) {
        if (!validar(cliente)) return false;
        return clienteDAO.actualizar(cliente);
    }
    
    public boolean eliminar(int id) {
        return clienteDAO.eliminar(id);
    }
}