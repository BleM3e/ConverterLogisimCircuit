import java.util.List;

public class Component {
    Location e;
    String componentName;
    List<Location> inputs;

    Component(Location e, String componentName) {
        this.e = e;
        this.componentName = componentName;
    }

    public void listInputs(List<Wire> wires) {
        switch (componentName) {
            case "AND":

                break;

            default:
                break;
        }
    }

    @Override
    public String toString() {
        return "Component : " + componentName + " out : " + e;
    }
}

/* Notes
 *
 * NOT (1 input)  : 1st (x - 30, y)
 * AND (2 inputs) : 1st (x - 50, y + 20) and 2nd (x - 50, y - 20)
 * OR  (2 inputs) : 1st (x - 50, y + 20) and 2nd (x - 50, y - 20)
 * NAND(2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * NOR (2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * XOR (2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * XNOR(2 inputs) : 1st (x - 70, y + 20) and 2nd (x - 70, y - 20)
 *
 */
