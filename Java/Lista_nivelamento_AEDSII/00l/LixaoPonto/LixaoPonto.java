public class LixaoPonto {
    private double x;
    private double y;
    private int id;
    private static int nextID = 0;

    public LixaoPonto() {
        this.x = 0;
        this.y = 0;
        this.id = nextID;
        nextID++;
    }

    public LixaoPonto(double x, double y) {
        this.x = x;
        this.y = y;
        this.id = nextID;
        nextID++;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double dist(LixaoPonto outroPonto) {
        double dx = x - outroPonto.x;
        double dy = y - outroPonto.y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public double dist(double x, double y) {
        double dx = this.x - x;
        double dy = this.y - y;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static double dist(double x1, double y1, double x2, double y2) {
        double dx = x1 - x2;
        double dy = y1 - y2;
        return Math.sqrt(dx * dx + dy * dy);
    }

    public static boolean isTriangulo(LixaoPonto p1, LixaoPonto p2, LixaoPonto p3) {
        return (p1.dist(p2) + p2.dist(p3) > p1.dist(p3))
            && (p1.dist(p3) + p3.dist(p2) > p1.dist(p2))
            && (p2.dist(p1) + p1.dist(p3) > p2.dist(p3));
    }

    public double getAreaRetangulo(LixaoPonto outroPonto) {
        double lado1 = Math.abs(x - outroPonto.x);
        double lado2 = Math.abs(y - outroPonto.y);
        return lado1 * lado2;
    }

    public int getID() {
        return id;
    }

    public static int getNextID() {
        return nextID;
    }

    public static void main (String[] args){
        LixaoPonto p1 = new LixaoPonto(4,3);
        LixaoPonto p2 = new LixaoPonto(8,5);
        LixaoPonto p3 = new LixaoPonto(9.2,10);
        System.out.println("Distancia p1 entre e p2: " + p1.dist(p2));
        System.out.println("Distancia p1 entre e (5,2): " + p1.dist(5,2));
        System.out.println("Distancia (4,3) entre e (5,2): " + LixaoPonto.dist(4,3,5,2));
        System.out.println("P1, P2, P3 sao triangulo:" + LixaoPonto.isTriangulo(p1,p2,p3));
        System.out.println("Area retangulo:" + p1.getAreaRetangulo(p2));
        System.out.println("ID de P1: " + p1.getID());
        System.out.println("ID de P2: " + p2.getID());
        System.out.println("ID de P3: " + p3.getID());
        System.out.println("Next ID: " + LixaoPonto.getNextID());
        }
}
