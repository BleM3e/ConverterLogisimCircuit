package core;
import java.util.Comparator;

public class LocationComparator implements Comparator<Location> {

    @Override
    public int compare(Location e0, Location e1) {
        boolean is_x_equal = e0.getX() == e1.getX();
        if (is_x_equal) {
            if (e0.getY() > e1.getY()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            boolean is_y_equal = e0.getY() == e1.getY();
            if (is_y_equal) {
                return 0;
            } else {
                if (e0.getX() > e1.getX()) {
                    return -1;
                } else {
                    return 1;
                }
            }
        }
    }
}
