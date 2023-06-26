package Map;

public class Main {
    public static void main(String[] args) {
        String mapPath = "";
        Integer start = null, end = null;

        Map map = new Map(mapPath);

        System.out.println(map.getCost(start, end));
        System.out.println(map.getPath(start, end));
        System.out.println(map.getTime(start, end)); // in seconds

        System.out.println(Map.getCost(mapPath, start, end));
        System.out.println(Map.getPath(mapPath, start, end));
        System.out.println(Map.getTime(mapPath, start, end));

        map.search(start, end);
        System.out.println(map.getCost());
        System.out.println(map.getPath());
        System.out.println(map.getTime());
    }
}
