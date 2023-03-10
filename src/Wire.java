public class Wire {
    boolean is_x_equal;
    Location e0;
    Location e1;

    Wire(Location e0, Location e1) {
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
    }

    @Override
    public String toString() {
        return "1st node : " + e0 + " 2nd node : " + e1;
    }
}
