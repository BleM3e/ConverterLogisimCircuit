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

    private int mainGrid_axis;

    private boolean isArduinoProject;

    public Translator(File file, Boolean isArduinoProject) throws Exception {
        this.filtered_circuit = new Core(file);
        this.isArduinoProject = isArduinoProject;
        this.not_nb = 0;
        this.and_nb = 0;
        this.or_nb = 0;
        this.nand_nb = 0;
        this.nor_nb = 0;
        this.xor_nb = 0;
        this.xnor_nb = 0;
        this.btn_nb = 0;
        this.led_nb = 0;

        this.mainGrid_axis = 0;
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
                    component.componentId = this.not_nb;
                    declaration += "Not *not" + this.not_nb++ + " = new Not();\n";
                    break;
                case "AND":
                    component.componentId = this.and_nb;
                    declaration += "And *and" + this.and_nb++ + " = new And();\n";
                    break;
                case "OR":
                    component.componentId = this.or_nb;
                    declaration += "Or *or" + this.or_nb++ + " = new Or();\n";
                    break;
                case "NAND":
                    component.componentId = this.nand_nb;
                    declaration += "NAnd *nand" + this.nand_nb++ + " = new NAnd();\n";
                    break;
                case "NOR":
                    component.componentId = this.nor_nb;
                    declaration += "Nor *nor" + this.nor_nb++ + " = new Nor();\n";
                    break;
                case "XOR":
                    component.componentId = this.xor_nb;
                    declaration += "Xor *xor" + this.xor_nb++ + " = new Xor();\n";
                    break;
                case "XNOR":
                    component.componentId = this.xnor_nb;
                    declaration += "XNor *xnor" + this.xnor_nb++ + " = new XNor();\n";
                    break;
                case "Pin":
                    component.componentId = this.btn_nb;
                    declaration += "Button *btn" + this.btn_nb++ + " = new Button();\n";
                    break;
                case "LED":
                    component.componentId = this.led_nb;
                    declaration += "Led *led" + this.led_nb++ + " = new Led();\n";
                    break;
                default:
                    break;
            }
        }
        result += declaration + "\n";
        declaration = "//Affectation to the sources\n\n";
        for (Component component : filtered_circuit.getComps()) {
            for (Component source : component.inputsComponents) {
                String componentName = getComponentName(source.componentName);
                switch (component.componentName) {
                    case "NOT":
                        declaration += "not" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "AND":
                        declaration += "and" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "OR":
                        declaration += "or" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "NAND":
                        declaration += "nand" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "NOR":
                        declaration += "nor" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "XOR":
                        declaration += "xor" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "XNOR":
                        declaration += "xnor" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    case "Pin":
                        break;
                    case "LED":
                        declaration += "led" + component.componentId + "->add_sources(" + componentName
                                + source.componentId + ");\n";
                        break;
                    default:
                        break;
                }
            }
        }
        result += declaration;
        if (!isArduinoProject) {
            result += "\n//Component integration in window\n\n";
            result += getGtkmmTranslate();
        }
        return result;
    }

    static public String getComponentName(String componentName) {
        switch (componentName) {
            case "NOT":
                return "not";
            case "AND":
                return "and";
            case "OR":
                return "or";
            case "NAND":
                return "nand";
            case "NOR":
                return "nor";
            case "XOR":
                return "xor";
            case "XNOR":
                return "xnor";
            case "Pin":
                return "btn";
            case "LED":
                return "led";
            default:
                return "N/A";
        }
    }

    private String getGtkmmTranslate() {
        String result = "";
        for (Component component : filtered_circuit.getComps()) {
            if (component.componentName == "Pin" || component.componentName == "LED") {
                result += getComponentName(component.componentName) + component.componentId + "->add_input(mainGrid," + this.mainGrid_axis++ + ",0);";
            }
        }
        return result;
    }
}
