package unam.fes.aragon.codigoLR0.elemnetosTablas;

public class Simbolo {
    private int valor;
    private String nombre;

    public Simbolo(String nombre, int valor) {
        super();
        this.nombre = nombre;
        this.valor = valor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
}
