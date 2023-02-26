public class Paths {
    City[] path;
    int sp;
    public Paths() {
        path = new City[52];  // Total of 52 cities
        sp = 0;
    }


    // Shortest 2: Path
    public Integer shortest2(City from, City to, Integer max) {
        if (max < 0)
            return null;
        if (from == to)
            return 0;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        path[sp++] = from;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] != null) {
                Connection conn = from.neighbours[i];
                Integer tmp = shortest2(conn.city, to, max-conn.distance);
                if(tmp == null)
                    continue;
                else
                    tmp += conn.distance;
                if (shrt == null)
                    shrt = tmp;
                else if (tmp < shrt)
                    shrt = tmp;
            }
        }
        path[--sp] = null;
        return shrt;
    }


    // Shortest 3: Optimized path
    public static Integer shortest3(City from, City to, Integer max){
        Paths p = new Paths();
        return p.shortest3b(from, to, max);
    }
    private Integer shortest3b(City from, City to, Integer max) {
        if (from == to)
            return 0;
        for (int i = 0; i < sp; i++) {
            if (path[i] == from)
                return null;
        }
        path[sp++] = from;
        Integer shrt = null;
        for (int i = 0; i < from.neighbours.length; i++) {
            if (from.neighbours[i] != null) {

                Integer tmp = null;

                Connection conn = from.neighbours[i];
                if(max != null){
                    if(conn.distance < max){
                        tmp = shortest3b(conn.city, to, max);
                    }
                }
                else{
                    tmp = shortest3b(conn.city, to, max);
                }

                // Tmp
                if(tmp == null)
                    continue;
                else
                    tmp += conn.distance;

                // Shrt
                if (shrt == null)
                    shrt = tmp;
                else if (tmp < shrt)
                    shrt = tmp;

                // Max
                if(max == null){
                    max = shrt;
                }
            }
        }
        path[--sp] = null;
        return shrt;
    }
}
