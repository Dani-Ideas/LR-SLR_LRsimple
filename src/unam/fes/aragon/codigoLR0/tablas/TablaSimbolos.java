package unam.fes.aragon.codigoLR0.tablas;

import unam.fes.aragon.codigoLR0.elemnetosTablas.Datos;
import unam.fes.aragon.compilador.Simbolos;

import java.util.HashMap;
import java.util.Iterator;

public class TablaSimbolos {
    private HashMap<String, Datos> t;
    public TablaSimbolos() {
        t = new HashMap<>();
    }
    public Datos insertar(String nombre) {
        Datos ss = new Datos();
        ss.setLexema(Simbolos.ID);
        ss.setNombreVariable(nombre);
        ss.setValor("0");
        t.put(nombre,ss);
        return ss;
    }
    public Datos buscar(String nombre) {
        return (Datos)(t.get(nombre));
    }
    public void imprimir() {
        // posible cambio
        System.out.println("Valores de la tabla de simbolos: \n");
        Iterator it= t.values().iterator();
        while(it.hasNext()) {
            Datos s = (Datos)it.next();
            System.out.println(s.getNombreVariable()+":"+s.getValor());
        }
    }
}
