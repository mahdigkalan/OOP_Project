package Map;

public class Main {
    public static void main(String[] args) {
        String mapPath = "";
        Integer start = null, end = null;

        Map map = new Map(mapPath);

        System.out.println(map.getCost(start, end));
        System.out.println(map.getPath(start, end));
        System.out.println(map.getTime(start, end)); // in seconds
    }
}
