public class Main {

    static String file = "trains.csv";

    public static void main(String[] args){
        //test();
        Benchmarks.naive();
        Benchmarks.paths();
        Benchmarks.improved_paths();
    }


    public static void test(){

        // City and connection
        City sthlm = new City("Stockholm");
        City vst = new City("Västerås");
        City enk = new City("Enköping");
        sthlm.add_connection(vst, 50);
        sthlm.add_connection(enk, 40);
        System.out.println("City and connection creation complete");

        // Maps
        Map map = new Map(file);
        System.out.println("Map creation complete");

        // Done
        System.out.println("All tests complete");
    }
}
