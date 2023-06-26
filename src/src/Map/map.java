package Map;

import java.util.ArrayList;

public class Map {
    private final Dijkstra<Integer> dijkstra;

    public Map(String MapPath) {
        dijkstra = new Dijkstra<Integer>(MapPath);
    }

    public Long getCost(Integer start, Integer end) {
        if(dijkstra.isSame(start, end)) return dijkstra.getCost();
        if(!dijkstra.search(start,end)) return null;  // there is no path
        return dijkstra.getCost();
    }

    public ArrayList<Integer> getPath(Integer start, Integer end) {
        if(dijkstra.isSame(start, end)) return dijkstra.getPath();
        if(!dijkstra.search(start, end)) return null;  // there is no path
        return dijkstra.getPath();
    }

    public Long getTime(Integer start, Integer end) {
        if(dijkstra.isSame(start, end)) return dijkstra.getTime();
        if(!dijkstra.search(start, end)) return null; // there is no path
        return dijkstra.getTime();
    }
}
