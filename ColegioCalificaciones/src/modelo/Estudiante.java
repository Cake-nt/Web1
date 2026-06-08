package modelo;

public class Estudiante {
    private String nombre;
    private String apellido;
    private String curso;
    private String mes;
    private double matematica;
    private double lengua;
    private double naturales;
    private double sociales;
    
    public Estudiante() {}
    
    public Estudiante(String nombre, String apellido, String curso, String mes,
                     double matematica, double lengua, double naturales, double sociales) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.curso = curso;
        this.mes = mes;
        setMatematica(matematica);
        setLengua(lengua);
        setNaturales(naturales);
        setSociales(sociales);
    }
    
    // Getters y Setters con validación
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    
    public String getCurso() { return curso; }
    public void setCurso(String curso) { this.curso = curso; }
    
    public String getMes() { return mes; }
    public void setMes(String mes) { this.mes = mes; }
    
    public double getMatematica() { return matematica; }
    public void setMatematica(double matematica) {
        if (matematica < 0 || matematica > 100) {
            throw new IllegalArgumentException("Matemática debe estar entre 0 y 100");
        }
        this.matematica = matematica;
    }
    
    public double getLengua() { return lengua; }
    public void setLengua(double lengua) {
        if (lengua < 0 || lengua > 100) {
            throw new IllegalArgumentException("Lengua debe estar entre 0 y 100");
        }
        this.lengua = lengua;
    }
    
    public double getNaturales() { return naturales; }
    public void setNaturales(double naturales) {
        if (naturales < 0 || naturales > 100) {
            throw new IllegalArgumentException("Naturales debe estar entre 0 y 100");
        }
        this.naturales = naturales;
    }
    
    public double getSociales() { return sociales; }
    public void setSociales(double sociales) {
        if (sociales < 0 || sociales > 100) {
            throw new IllegalArgumentException("Sociales debe estar entre 0 y 100");
        }
        this.sociales = sociales;
    }
    
    public double calcularPromedio() {
        double suma = matematica + lengua + naturales + sociales;
        if (suma == 0) {
            throw new ArithmeticException("No se puede dividir por cero");
        }
        return suma / 4;
    }
    
    public String obtenerLiteral() {
        double promedio = calcularPromedio();
        if (promedio > 90 && promedio <= 100) return "A";
        else if (promedio > 80 && promedio <= 90) return "B";
        else if (promedio > 70 && promedio <= 80) return "C";
        else return "D";
    }
}