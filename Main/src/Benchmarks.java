public class Benchmarks {


    // Shortest 1
    public static void naive(){
        System.out.println("\nNAIVE BENCHMARK");

        String[][] data = {
                new String[]{"Malmö", "Göteborg", "300"},
                new String[]{"Göteborg", "Stockholm", "300"},
                new String[]{"Malmö", "Stockholm", "400"},
                new String[]{"Stockholm", "Sundsvall", "400"},
                new String[]{"Stockholm", "Umeå", "400"},
                new String[]{"Göteborg", "Sundsvall", "500"},
                new String[]{"Sundsvall", "Umeå", "300"},
                new String[]{"Umeå", "Göteborg", "900"},
                //new String[]{"Göteborg", "Umeå", "900"},  // To long to search
        };
        Map map = new Map(Main.file);
        for(String[] path : data){
            long t0 = System.nanoTime();
            Integer dist = map.shortest1(map.lookup(path[0]), map.lookup(path[1]), Integer.parseInt(path[2]));
            long time = (System.nanoTime() - t0)/1_000_000;
            System.out.println(path[0] + "-" + path[1] + ": " + dist + " min (" + time + " ms)");
        }
    }


    // Shortest 2
    public static void paths(){
        System.out.println("\nPATHS BENCHMARK");

        String[][] data = {
                new String[]{"Malmö", "Göteborg", "300"},
                new String[]{"Göteborg", "Stockholm", "300"},
                new String[]{"Malmö", "Stockholm", "400"},
                new String[]{"Stockholm", "Sundsvall", "400"},
                new String[]{"Stockholm", "Umeå", "400"},
                new String[]{"Göteborg", "Sundsvall", "500"},
                new String[]{"Sundsvall", "Umeå", "300"},
                new String[]{"Umeå", "Göteborg", "900"},
                new String[]{"Göteborg", "Umeå", "900"},
        };
        Map map = new Map(Main.file);
        for(String[] set : data){
            Paths path = new Paths();
            long t0 = System.nanoTime();
            Integer dist = path.shortest2(map.lookup(set[0]), map.lookup(set[1]), 10000);
            long time = (System.nanoTime() - t0)/1_000_000;
            System.out.println(set[0] + "-" + set[1] + ": " + dist + " min (" + time + " ms)");
        }
    }


    // Shortest 3
    public static void improved_paths(){
        System.out.println("\nIMPROVED PATHS BENCHMARK");

        String[][] data = {
                new String[]{"Malmö", "Göteborg", "300"},
                new String[]{"Göteborg", "Stockholm", "300"},
                new String[]{"Malmö", "Stockholm", "400"},
                new String[]{"Stockholm", "Sundsvall", "400"},
                new String[]{"Stockholm", "Umeå", "400"},
                new String[]{"Göteborg", "Sundsvall", "500"},
                new String[]{"Sundsvall", "Umeå", "300"},
                new String[]{"Umeå", "Göteborg", "900"},
                new String[]{"Göteborg", "Umeå", "900"},
        };
        Map map = new Map(Main.file);
        for(String[] path : data){
            long t0 = System.nanoTime();
            Integer dist = Paths.shortest3(map.lookup(path[0]), map.lookup(path[1]), null);
            long time = (System.nanoTime() - t0)/1_000_000;
            System.out.println(path[0] + "-" + path[1] + ": " + dist + " min (" + time + " ms)");
        }
    }
}
