package main;

import entities.Places;
import entities.Town;
import es.uji.belfern.generador.GeneradorDatosINE;

import java.io.PrintStream;
import java.util.Date;
import java.util.Scanner;

public class Main {
    static Places places = new Places();

    public void test(){
        GeneradorDatosINE gen = new GeneradorDatosINE();
        for (int i = 0; i < 100; i++) {
            String prov = gen.getProvincia();
            System.out.printf("%s %s %s [%s años] *%s*: %s (%s)\n", gen.getNombreHombre(),
                    gen.getApellido(), gen.getApellido(), gen.getEdad(), gen.getNIF(),
                    gen.getPoblacion(prov), prov
            );
        }
        System.out.print("introduïx un codi postal: ");
        Scanner sc = new Scanner(System.in);
        if (sc.hasNext()){
            String answer = sc.next();
            Town town = places.getTown(answer);
            if (town != null)
                System.out.printf("%s (%s)%n", town.getName(), town.getProvince());
            else
                System.out.println("El codi postal no existeix.");
        } else {
            System.out.println("Això no és cap codi postal");
        }
    }

    public static void main(String [] args){
        Date date;
        Date now = new Date();
        Long timestamp = now.getTime();
        System.out.println(timestamp);
        System.out.println(now.toString());

        Llamada llamada = new Llamada();
        System.out.println(llamada.getFecha());
        long duracion = 300;
        Date fecha = llamada.sumarSegundos(duracion);


        places.start();
        for (int i = 100; i<100; i++) {
            Town t = places.fetchTown();
            System.out.printf("%s %s (%s)%n", t.getZipCode(), t.getName(), t.getProvince());
        }

    }
}
