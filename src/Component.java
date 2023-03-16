import java.util.ArrayList;
import java.util.List;

public class Component {
    public Location e;
    public String componentName;
    public List<Location> inputs;
    public List<Component> inputsComponents;
    public Wire outputWire;

    Component(Location e, String componentName) {
        this.e = e;
        this.componentName = componentName;
        inputs = new ArrayList<Location>();
        inputsComponents = new ArrayList<Component>();
        listInputs();
    }

    public void listInputs() {
        switch (componentName) {
            case "NOT":
                inputs.add(new Location(e.x - 30, e.y));
                break;
            case "AND":
                inputs.add(new Location(e.x - 50, e.y - 20));
                inputs.add(new Location(e.x - 50, e.y + 20));
                break;
            case "OR":
                inputs.add(new Location(e.x - 50, e.y - 20));
                inputs.add(new Location(e.x - 50, e.y + 20));
                break;
            case "NAND":
                inputs.add(new Location(e.x - 60, e.y - 20));
                inputs.add(new Location(e.x - 60, e.y + 20));
                break;
            case "NOR":
                inputs.add(new Location(e.x - 60, e.y - 20));
                inputs.add(new Location(e.x - 60, e.y + 20));
                break;
            case "XOR":
                inputs.add(new Location(e.x - 60, e.y - 20));
                inputs.add(new Location(e.x - 60, e.y + 20));
                break;
            case "XNOR":
                inputs.add(new Location(e.x - 70, e.y - 20));
                inputs.add(new Location(e.x - 70, e.y + 20));
                break;
            case "LED":
                inputs.add(new Location(e.x, e.y));
                break;
            default:
                break;
        }
    }

    public void setOutputWire(Wire wire) {
        this.outputWire = wire;
    }

    public boolean isInInputs(Component c) {
        for (Component component : inputsComponents) {
            if (component == c)
                return true;
        }
        return false;
    }

    @Override
    public String toString() {
        String result = "Component : " + componentName + " out : " + e;
        if (inputs.size() != 0) {
            result += " | all inputs : ";
            for (Location location : inputs) {
                result += location + " ";
            }
        }
        if (inputsComponents.size() != 0) {
            System.out.println("Component.toString.inputsComponents.print");
            result += " | all components inputs : ";
            for (Component component : inputsComponents) {
                result += component.componentName + " ";
            }
        }
        return result;
    }
}

/*
 * Notes
 *
 * NOT (1 input) : 1st (x - 30, y)
 * AND (2 inputs) : 1st (x - 50, y + 20) and 2nd (x - 50, y - 20)
 * OR (2 inputs) : 1st (x - 50, y + 20) and 2nd (x - 50, y - 20)
 * NAND(2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * NOR (2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * XOR (2 inputs) : 1st (x - 60, y + 20) and 2nd (x - 60, y - 20)
 * XNOR(2 inputs) : 1st (x - 70, y + 20) and 2nd (x - 70, y - 20)
 *
 */
