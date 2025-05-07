package unam.fes.aragon.codigoLR0.elemnetosTablas;

public class ElementoLR0 {
    String ladoIzquierdo;
    String ladoDerecho;
    int punto;  // posición del punto

    public ElementoLR0(String ladoIzquierdo, String ladoDerecho, int punto) {
        this.ladoIzquierdo = ladoIzquierdo;
        this.ladoDerecho = ladoDerecho;
        this.punto = punto;
    }

    public String getProduccion() {
        return ladoIzquierdo + " → " + ladoDerecho.substring(0, punto) + "." + ladoDerecho.substring(punto);
    }

// Conjunto de estados
//List<List<ElementoLR0>> In = new ArrayList<>();
}

