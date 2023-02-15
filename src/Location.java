public class Location {
    int x;
    int y;

    Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    int getX()
    {
        return x;
    }

    int getY()
    {
        return y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
