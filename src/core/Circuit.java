package core;
import java.util.*;

public class Circuit {
    public List<Wire> wires;
    public List<Component> comps;

    private int wireId = 0;

    public void createWire(String str) {
        String result, theDigits[];
        int location[] = new int[4], index = 0;

        result = str.replaceAll("[ \t]", "");
        theDigits = result.split("[^0-9]", 0);
        for (String string : theDigits) {
            if (string.matches("[0-9]+")) {
                location[index] = Integer.parseInt(string);
                index++;
            }
        }
        addWire(location);
    }

    public void addWire(int loc[]) {
        if (loc.length != 4)
            System.exit(0);
        wires.add(new Wire(new Location(loc[0], loc[1]), new Location(loc[2], loc[3]), wireId++));
    }

    public void createComponent(String str) {
        int location[] = new int[2], index = 0;
        String componentName = "";
        String locationString[];

        String newBuffer = str.trim();
        String splitedBuffer[] = newBuffer.split(" ", 0);
        for (String string : splitedBuffer) {
            if (string.matches("loc=.*")) {
                locationString = string.split("[^0-9]");
                for (String string2 : locationString) {
                    if (string2.matches("[0-9]+")) {
                        location[index] = Integer.parseInt(string2);
                        index++;
                    }
                }
                index = 0;
            }
            if (string.matches("name=.*")) {
                String nameSplit[] = string.split("\"");
                componentName = nameSplit[1];
            }
        }
        addComponent(location, componentName);
    }

    public void addComponent(int loc[], String componentName) {
        if (loc.length != 2)
            System.exit(0);
        comps.add(new Component(new Location(loc[0], loc[1]), componentName));
    }

    /* public void sortWires() {
        wires.sort(new WireComparator());
    } */
}
