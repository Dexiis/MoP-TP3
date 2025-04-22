public class ZooSingleton {
    private static Zoo zooInstance;

    private ZooSingleton() {
    }

    public static Zoo getZooInstance(String name, int precario) {
        if (zooInstance == null) {
            zooInstance = new Zoo(name, precario);
        }
        return zooInstance;
    }
}