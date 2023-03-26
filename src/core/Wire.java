package core;
public class Wire {
    private boolean is_x_equal;
    public Location e0;
    public Location e1;
    private int id;

    Wire(Location e0, Location e1, int id) {
        this.is_x_equal = e0.getX() == e1.getX();
        if (is_x_equal) {
            if (e0.getY() > e1.getY()) {
                this.e0 = e1;
                this.e1 = e0;
            } else {
                this.e0 = e0;
                this.e1 = e1;
            }
        } else {
            if (e0.getX() > e1.getX()) {
                this.e0 = e1;
                this.e1 = e0;
            } else {
                this.e0 = e0;
                this.e1 = e1;
            }
        }
        this.id = id;
    }

    public boolean hasSameEdge(Wire w) {
        if (this.e0 == w.e0 || this.e0 == w.e1)
            return true;
        if (this.e1 == w.e0 || this.e1 == w.e1)
            return true;

        return false;
    }

    public int getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Wire n°" + id + " | 1st node : " + e0 + "\t2nd node : " + e1;
    }
}
