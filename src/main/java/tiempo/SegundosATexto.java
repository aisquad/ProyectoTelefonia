package tiempo;

import java.util.ArrayList;
import java.util.HashMap;

public class SegundosATexto {
    private int dias;
    private int horas;
    private int minutos;
    private int segundos;
    
    public SegundosATexto() {
        dias = 0;
        horas = 0;
        minutos = 0;
        segundos = 0;
    }

    public void generarValores(int segundos){
        Double ndias = Math.floor(segundos / 86400);
        Double nhoras = Math.floor((segundos % 86400) / 3600);
        Double nminutos = Math.floor(((segundos % 86400) % 3600) / 60);
        Integer nsegundos = ((segundos % 86400) % 3600) % 60;

        dias = ndias.intValue();
        horas = nhoras.intValue();
        minutos = nminutos.intValue();
        this.segundos = nsegundos;
    }

    public ArrayList<String> obtenerListaElementos(boolean abreviado){
        HashMap<String, Integer> tokens = new HashMap<String, Integer>();
        String keys[] = {"dia", "hora", "minuto", "segundo"};
        tokens.put(keys[0], dias);
        tokens.put(keys[1], horas);
        tokens.put(keys[2], minutos);
        tokens.put(keys[3], segundos);

        ArrayList<String> res = new ArrayList<String>();
        for (String key : keys) {
            Integer token = tokens.get(key);
            if (token > 0)
                res.add(
                    String.format(
                        "%d %s%s",
                        token,
                        abreviado ? key.substring(0,1) : key,
                        token > 1 && !abreviado ? "s" : ""
                    )
                );
        }
        return res;
    }

    public String segundosATexto(int arg) {
        generarValores(arg);
        ArrayList<String> elementos = obtenerListaElementos(false);
        String rtn = "";
        for (int i = 0; i < elementos.size(); i++) {
            rtn += elementos.get(i);
            if (i < elementos.size() - 2)
                rtn += ", ";
            else if (i < elementos.size() - 1)
                rtn += " y ";
            else
                rtn +=".";
        }
        return rtn;
    }

    public String segundosATextoAbreviado(int arg){
        generarValores(arg);
        ArrayList<String> elementos = obtenerListaElementos(true);
        String rtn = "";
        for (int i = 0; i < elementos.size(); i++) {
            rtn += elementos.get(i);
            if (i < elementos.size() - 1)
                rtn += ", ";
        }
        return rtn;
    }
}
