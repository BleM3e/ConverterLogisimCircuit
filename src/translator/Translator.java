package translator;

import java.io.File;

import core.Component;
import core.Core;

public class Translator {
    private Core filtered_circuit;

    private int not_nb;
    private int and_nb;
    private int or_nb;
    private int nand_nb;
    private int nor_nb;
    private int xor_nb;
    private int xnor_nb;
    private int btn_nb;
    private int led_nb;

    public Translator(File file) throws Exception {
        this.filtered_circuit = new Core(file);
        this.not_nb = 0;
        this.and_nb = 0;
        this.or_nb = 0;
        this.nand_nb = 0;
        this.nor_nb = 0;
        this.xor_nb = 0;
        this.xnor_nb = 0;
        this.btn_nb = 0;
        this.led_nb = 0;
    }

    public Core get_circuit() {
        return this.filtered_circuit;
    }

    public String toString() {
        String result = "//Component declaration\n\n";
        String declaration = "";
        for (Component component : filtered_circuit.getComps()) {
            switch (component.componentName) {
                case "NOT":
                    declaration += "Not *not" + this.not_nb++ + " = new Not();\n";
                    break;
                case "AND":
                    declaration += "And *and" + this.and_nb++ + " = new And();\n";
                    break;
                case "OR":
                    declaration += "Or *or" + this.or_nb++ + " = new Or();\n";
                    break;
                case "NAND":
                    declaration += "NAnd *nand" + this.nand_nb++ + " = new NAnd();\n";
                    break;
                case "NOR":
                    declaration += "Nor *nor" + this.nor_nb++ + " = new Nor();\n";
                    break;
                case "XOR":
                    declaration += "Xor *xor" + this.xor_nb++ + " = new Xor();\n";
                    break;
                case "XNOR":
                    declaration += "XNor *xnor" + this.xnor_nb++ + " = new XNor();\n";
                    break;
                case "Pin":
                    declaration += "Button *btn" + this.btn_nb++ + " = new Button();\n";
                    break;
                case "LED":
                    declaration += "Led *led" + this.led_nb++ + " = new Led();\n";
                    break;
                default:
                    break;
            }
        }
        result += declaration;
        return result;
    }
}
