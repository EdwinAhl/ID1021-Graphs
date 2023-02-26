import java.io.BufferedReader;
import java.io.FileReader;

public class Map {

    // Properties
    City[][] cities;  // Array of cities with buckets
    private final int mod = 541;  // Modulus operand, 541 because prime


    // Constructor
    public Map(String file){
        cities = new City[mod][];  // Number of rows as mod
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] row = line.split(",");  // {city1, city2, distance}
                add_cities(row[0], row[1], Integer.parseInt(row[2]));
            }
        } catch (Exception e) {
            System.out.println(" file " + file + " not found");
        }
    }


    // Adds two cities and their distance apart to the map
    public void add_cities(String c1_name, String c2_name, int distance){
        City c1 = lookup(c1_name);  // Check if c1 exists on map
        City c2 = lookup(c2_name);  // Check if c2 exists on map
        if(c1 == null){  // C1 isn't already on map
            c1 = new City(c1_name);  // Create new c1
            add_city(c1);  // Add c1 to map
        }
        if(c2 == null){  // C1 isn't already on map
            c2 = new City(c2_name);  // Create new c1
            add_city(c2);  // Add c2 to map
        }
        c1.add_connection(c2, distance);  // Add connection between cities
    }
    private void add_city(City city){  // Adds a single city to the map
        int index = hash(city.name);  // Retrieve it's hashed index
        if(cities[index] == null){  // No bucket at index
            cities[index] = new City[] {city};  // Create new bucket with c1
        }
        else{  // Bucket exists
            City[] tmp = new City[cities[index].length+1];  // Tmp one larger
            for(int i = 0; i < cities[index].length; i++){  // Iterate bucket
                tmp[i] = cities[index][i];  // Copy
            }
            tmp[cities[index].length] = city;  // Add given city
            cities[index] = tmp;  // Set new bucket as tmp
        }
    }


    // Returns the city with given name
    public City lookup(String name){
        int index = this.hash(name);  // Index from hash
        if(cities[index] != null){  // If bucket exists on index
            for(int i = 0; i < cities[index].length; i++){  // Iterate bucket
                if(cities[index][i].name.equals(name)){  // Found city
                    return cities[index][i];  // Return given city
                }
            }
        }
        return null;  // Bucket doesn't exist or city isn't found in bucket
    }


    // Hashes a name
    private Integer hash(String name) {
        int hash = 7;
        for (int i = 0; i < name.length(); i++) {
            hash = (hash*31 % mod) + name.charAt(i);
        }
        return hash % mod;
    }


    // Shortest 1: Naive way
    public Integer shortest1(City from, City to, int max){
        if (max < 0)
            return null;
        if (from == to)
            return 0;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] != null) {
                Connection conn = from.neighbours[i];
                Integer tmp = shortest1(conn.city, to, max-conn.distance);
                if(tmp == null)
                    tmp = conn.distance;
                else
                    tmp += conn.distance;
                if (shrt == null)
                    shrt = tmp;
                else if (tmp < shrt)
                    shrt = tmp;
            }
        }
        return shrt;
    }
}
