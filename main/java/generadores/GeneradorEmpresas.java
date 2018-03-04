package generadores;

import clientes.Empresa;
import poblaciones.Poblacion;

import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GeneradorEmpresas extends GeneradorCliente {
    //Atributos
    private String tipos[] = {
            "Instalaciones", "Baños", "Cerámicas", "Fontanería", "Construcciones", "Inmobiliaria",
            "Arreglos y retales", "Colchones", "Mobiliario", "Peluquería", "Cooperativa Agricola",
            "Automóviles", "Reparaciones y Reformas", "Bar", "Restaurante", "Salón recreativo",
            "Alimentación", "Casino", "Electricidad", "Academia", "Clínicas Dentales", "Ordenadores",
            "Relojería", "Joyería", "Bisutería", "Academia de Baile", "Sonido", "Electrodomésticos",
            "Laboratorios", "Fotografía", "Comidas y Banquetes", "Catering", "Juguetes", "Viajes",
            "Floristería", "Abogados", "Transportes", "Ofertas", "Buenos Precios", "Carpintería",
            "Ferretería", "Ultramarinos", "Abacería", "Papelería", "Librería"
    };
    private Random random = new Random();

    public GeneradorEmpresas() {
    }

    private String eliminaDiacriticos(String string){
        string = string.toLowerCase();
        string = string.replaceAll("[áàâä]", "a");
        string = string.replaceAll("[éèêë]", "e");
        string = string.replaceAll("[íìîï]", "i");
        string = string.replaceAll("[óòôö]", "o");
        string = string.replaceAll("[úùûü]", "u");;
        return string;
    }

    public String getNombrePorSocios() {
        int limite = random.nextInt(5);
        if (limite<3) limite = 3;
        GeneradorParticulares apellido = new GeneradorParticulares();
        String nombreNegocio = "";
        for (int i=0; i < limite; i++){
            String socio = apellido.getApellido();
            socio = eliminaDiacriticos(socio);
            Pattern regexp =  Pattern.compile("([bcdfghjklmnpqrstvwxz]*[aeiouy][rnstl]*)");
            Matcher match = regexp.matcher(socio);
            match.find();
            nombreNegocio += match.group(0);
        }
        String fiscalidad [] = {"CB", "SL", "SLU", "SAU", "SA"};
        int alea = random.nextInt(fiscalidad.length);
        nombreNegocio += String.format(" %s", fiscalidad[alea]);
        return nombreNegocio.toUpperCase();
    }

    public String getNombrePorFundador() {
        int alea = random.nextInt(tipos.length);
        GeneradorParticulares fundador = new GeneradorParticulares();
        return String.format("%s %s", tipos[alea], fundador.getApellido());
    }

    public String getNombrePorPoblacion(Poblacion poblacion) {
        int alea = random.nextInt(tipos.length);
        return String.format("%s %s", tipos[alea], poblacion.getNombre());
    }

    public String getAleatorio(Poblacion poblacion) {
        String [] metodos = {getNombrePorSocios(), getNombrePorPoblacion(poblacion), getNombrePorFundador()};
        int alea = random.nextInt(metodos.length);
        return metodos[alea];
    }

    private Integer calculaA(int num) {
        double dig = Math.log10(num)+ 1;
        int digits = (int) dig;
        int total = 0;
        for (int i = 1; i < digits+1; i+=2) {
            int j = (int) (num % Math.pow(10, i) / Math.pow(10, i-1));
            total += j;
        }
        return total;
    }

    private Integer calculaB(int num) {
        double dig = Math.log10(num)+ 1;
        int digits = (int) dig;
        int total = 0;
        for (int i = 2; i < digits+1; i+=2) {
            int j = (int) (num % Math.pow(10, i) / Math.pow(10, i-1));
            j *= 2;
            if (j>9) j = j%10+1;
            total += j;
        }
        return total;
    }

    public String getCIF() {
        /*
        Se suman las posiciones pares de los 7 dígitos centrales, es decir, no se tiene en cuenta la letra inicial ni el
        código de control. (Suma = A)

        Por cada uno de los dígitos de las posiciones impares, se multiplica el dígito por 2 y se suman las cifras del
        resultado, pero si el resultado tiene un solo dígito simplemente esta cifra se suma. (p.e. si el dígito es 6, el
        resultado sería 6 x 2 = 12 -> 1 + 2 = 3, más si el dígito es 2, el resultado sería 2 x 2 = 4). (Suma = B)

        Sumar el resultado de los 2 pasos anteriores. (A + B = C)
        El último dígito de la suma anterior (C) se lo restamos a 10, cuyo resultado sería el código de control (p.e. si
        C = 14, el último dígito es 4, por lo que tendríamos 10 - 4 = 6). Si el último dígito de la suma del paso anterior
        es 0 (p.e. C = 30), no se realiza resta y se toma el 0 como código de control.

        Si el código de control es un número, este sería el resultado de la última operación. Si se trata de una letra, se
        utilizaría la siguiente relación:
        número obtenido ->		1	2	3	4	5	6	7	8	9	0
        código de control -> 	A 	B 	C 	D 	E 	F 	G 	H 	I 	J
        */
        int numeroCIF = random.nextInt(9999999);
        Double dDigits = Math.log10(numeroCIF) + 1;
        int iDigits = dDigits.intValue();
        int numA = calculaA(numeroCIF);
        int numB = calculaB(numeroCIF);
        int numC = 10 - ((numA + numB) % 10);
        return String.format("B%07d%d", numeroCIF, numC);
    }
}
