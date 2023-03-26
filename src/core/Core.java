package core;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Core {

    protected List<Wire> wires;
    protected List<Component> comps;

    static int wireId = 0;

    public Core(File file) throws Exception {
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

        /* Processing */
        WireAnalyser wireAnalyser = new WireAnalyser(wires, comps);
        wireAnalyser.analyse();

        // for (Wire wire : wires) {
        // System.out.println(wire);
        // }
        // for (Component comp : comps) {
        // System.out.println(comp);
        // }
        sc.close();
    }

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

    public String toString() {
        String result = "";
        for (Wire wire : wires) {
            result += wire + "\n";
        }
        for (Component comp : comps) {
            result += comp + "\n";
        }
        return result;
    }

    public List<Wire> getWires() {
        return this.wires;
    }

    public List<Component> getComps() {
        return this.comps;
    }
}
