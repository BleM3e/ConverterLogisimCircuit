import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    static List<Wire> wires;
    static List<Component> comps;

    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter
        File file = new File(
                "F:\\Coding_2_0\\Polytech\\Projet\\ConverterSimutech\\SortCircJava\\ConvCirc\\basicCircuit.circ");
        Scanner sc = new Scanner(file);
        wires = new ArrayList<Wire>();
        comps = new ArrayList<Component>();

        String buffer = "";
        boolean isWire, isComp;

        while (sc.hasNextLine()) {
            buffer = sc.nextLine();
            isWire = Pattern.matches("^[ \\t]*<wire.*", buffer);
            if (isWire) {
                createWire(buffer);
            }
            isComp = Pattern.matches("^[ \\t]*<comp.*", buffer);
            if (isComp) {
                createComponent(buffer);
            }
        }
        for (Wire wire : wires) {
            System.out.println(wire);
        }
        for (Component comp : comps) {
            System.out.println(comp);
        }
        sc.close();
    }

    public static void createWire(String str) {
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

    public static void addWire(int loc[]) {
        if (loc.length != 4)
            System.exit(0);
        wires.add(new Wire(new Location(loc[0], loc[1]), new Location(loc[2], loc[3])));
    }

    public static void createComponent(String str) {
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

    public static void addComponent(int loc[], String componentName) {
        if (loc.length != 2)
            System.exit(0);
        comps.add(new Component(new Location(loc[0], loc[1]), componentName));
    }
}
