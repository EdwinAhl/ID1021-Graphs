public class City {

    // Properties
    String name;
    Connection[] neighbours;


    // Constructor
    public City(String name){
        this.name = name;
        neighbours = null;
    }


    // Adds connection to city
    public void add_connection(City destination, Integer distance){
        this.add(destination, distance);
        destination.add(this, distance);
    }
    private void add(City destination, Integer distance){
        Connection con = new Connection(destination, distance);  // Connection to be added
        if(this.neighbours != null){  // Not empty
            int len = neighbours.length;  // Length of current array
            Connection[] new_neighbours = new Connection[len+1];  // Temporary
            for(int i = 0; i < len; i++){  // Copy
                new_neighbours[i] = this.neighbours[i];
            }
            new_neighbours[len] = con;  // Add given connection to end
            this.neighbours = new_neighbours;  // Set neighbours as temporary
        }
        else{  // Empty
            this.neighbours = new Connection[]{con};  // New array
        }
    }
}
