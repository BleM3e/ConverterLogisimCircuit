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
        return this.x;
    }

    int getY()
    {
        return this.y;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (!Location.class.isAssignableFrom(obj.getClass())) {
            return false;
        }

        Location location = (Location)obj;
        return (this.getX() == location.getX()) && (this.getY() == location.getY());
    }
}
