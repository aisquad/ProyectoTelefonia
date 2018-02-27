package places;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Places {
    /*
    Clase en la que obtenemos los municipios, provincias y códigos postales.

    - objTowns contiene el nombre del municipio más legible
    - towns y provinces permiten almacenar más cómodamente los datos de objTowns
     */
    //Attributes
    private final String dataPath = "src/main/resources/data/";
    private final String pattern = "^(0[1-9]|[1-4]\\d|5[012])\\d{3}$";
    private HashMap<String, Town> objTowns;
    private HashMap<String, String> towns;
    private HashMap<String, String> provinces;

    //Constructors
    public Places() {}

    //Methods
    public void start() {
        towns = loadEntities("zipcodes.dat");
        provinces = loadEntities("provinces.dat");
        objTowns = new HashMap<String, Town>();
        loadTowns();
    }

    protected HashMap<String, String> loadEntities(String filename) {
        HashMap<String, String> entities =  new HashMap<String, String>();
        try {
            FileReader file = new FileReader(dataPath + filename);
            BufferedReader buffer = new BufferedReader(file);
            String id = "";
            String toponym = "";
            String line = "";
            while ((line = buffer.readLine()) != null) {
                if (line.startsWith("#"))
                    continue;

                String tokens[] = line.split("\t");

                id = tokens[0];
                toponym = tokens[1];

                entities.put(id, toponym);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return entities;
    }

    private void loadTowns() {
        /*
        Llenamos el diccionario objTowns con objetos del tipo Town
         */
        if (provinces.isEmpty())
            return;
        String toponym = "";
        for(String id : towns.keySet()) {
            Town town = new Town();
            toponym = towns.get(id);
            town.setZipCode(id);
            town.setName(toponym);
            town.setProvince(fetchProvince(id));
            objTowns.put(id, town);
        }
    }

    protected boolean checkZipCode(String code) {
        /*
        Verificamos que se nos pase un código postal válido.
        Han de ser cinco dígitos cuyo primer dígito no pueda
        ser superior a 5.
        */
        boolean bool = false;
        bool = code.matches(pattern);
        if (!bool)
            //TODO: Hay que rodear con un try-catch?
            throw new IllegalArgumentException();
        return bool;
    }

    private String fetchProvince(String code){
        /*
        Extraemos la provincia de un municipio del diccionario
        mediante el código postal que se nos pasa.

        Nos aseguramos que la cadena introducida tiene el formato de
        código postal y nos quedamos con los primeros dos dígitos para
        obtener la provincia almacenada en el diccionario de provincias.
        */
        String province = "";
        Pattern regexp = Pattern.compile(pattern);
        Matcher matcher = regexp.matcher(code);
        if (matcher.find()) {
            province = provinces.get(matcher.group(1));
        }
        return province;
    }

    public String getProvinceByCode(String code){
        //Obtenemos la provincia mediante el código postal del municipio.
        checkZipCode(code);
        return fetchProvince(code);
    }

    public String getTownByCode(String code) {
        //Obtenemos el nombre de un municipio mediante su código postal.
        checkZipCode(code);
        return towns.get(code);
    }

    public boolean townExists(String town){
        //Verificamos que el nombre corresponde a un municipio.
        return towns.values().contains(town);
    }

    public Town getTown(String code){
        return objTowns.get(code);
    }

    public String getProvinceByPrefix(String prefix){
        /*
        Uso hipotético.

        Obtenemos la provincia con un número (con cero significativo)
        entre 01 y 52, son las claves del diccionario de provincias.
         */
        if (!prefix.matches("^(?:0[1-9]|[1-4]\\d|5[012])$"))
            //TODO: hay que rodear con un try-catch?
            throw new IllegalArgumentException();

        if (prefix.length() != 2)
            throw new IllegalArgumentException();

        return provinces.get(prefix);
    }

    public Town fetchTown(){
        Random rnd = new Random();
        Object array[] = objTowns.keySet().toArray();
        int dice = rnd.nextInt(array.length);
        return objTowns.get(array[dice]);
    }
}