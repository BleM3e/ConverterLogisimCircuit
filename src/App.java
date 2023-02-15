import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;

public class App {
    static List<Wire> wires;

    public static void main(String[] args) throws Exception {
        // pass the path to the file as a parameter
        File file = new File(
                "F:\\Coding_2_0\\Polytech\\Projet\\ConverterSimutech\\SortCircJava\\ConvCirc\\basicCircuit.circ");
        Scanner sc = new Scanner(file);
        wires = new ArrayList<Wire>();

        String buffer = "";
        boolean isWire;
        while (sc.hasNextLine()) {
            buffer = sc.nextLine();
            isWire = Pattern.matches("^[ \\t]*<wire.*", buffer);
            if (isWire) {
                createWire(buffer);
            }
        }
        for (Wire wire : wires) {
            System.out.println(wire);
        }
        sc.close();
    }

    public static void createWire(String str) {
        String result,theDigits[];
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

    public static void addWire(int loc[]){
        if (loc.length != 4) System.exit(0);
        wires.add(new Wire(new Location(loc[0], loc[1]), new Location(loc[2], loc[3])));
    }
}
