import java.util.*;

public class WireAnalyser {

    private List<Wire> wires;
    private List<Component> components;

    WireAnalyser(List<Wire> wires, List<Component> components) {
        this.wires = wires;
        this.components = components;
    }

    void analyse() {
        for (Component component : components) {
            recursiveAnalyst(component, component.e, null);
        }
    }

    void recursiveAnalyst(Component component, Location recursive_Location, Wire originalWire) {
        Component componentMatched;
        List<Wire> matchedWires;
        if ((componentMatched = componentMatch(component, recursive_Location)) != null) {
            System.out.println("WireAnalyser.recursiveAnalyst.if");
            if (!componentMatched.isInInputs(component))
                componentMatched.inputsComponents.add(component);
            return;
        }

        System.out.println("WireAnalyser.recursiveAnalyst.afterif");
        matchedWires = wiresMatch(recursive_Location, originalWire);
        for (Wire wire : matchedWires) {
            System.out.println(wire);
            recursiveAnalyst(component, nonCommonLocation(recursive_Location, originalWire, wire), wire);
        }
    }

    List<Wire> wiresMatch(Location originLocation, Wire originalWire) {
        List<Wire> matchedWires = new ArrayList<Wire>();
        System.out.println(
                "WireAnalyser.wiresMatch | origineLocation : " + originLocation + " originalWire : " + originalWire);
        for (Wire wire : wires) {
            System.out.println("WireAnalyser.wiresMatch.for | " + wire);
            if ((originalWire != null) && (wire.getId() == originalWire.getId())) {
                System.out.println("WireAnalyser.wiresMatch.if.originalWire");
                continue;
            }
            // System.out.println("wire.e0 = "+wire.e0+" ==? origineLocation =
            // "+originLocation+" || "+"wire.e1 = "+wire.e1+" ==? origineLocation =
            // "+originLocation);
            if (wire.e0.equals(originLocation) || wire.e1.equals(originLocation)) {
                System.out.println("WireAnalyser.wiresMatch.if.location");
                matchedWires.add(wire);
            }
        }
        System.out.println(matchedWires);
        return matchedWires;
    }

    Component componentMatch(Component originalComponent, Location originLocation) {
        for (Component component : components) {
            if (component.componentName == originalComponent.componentName)
                continue;

            for (Location input : component.inputs) {
                if (input.equals(originLocation))
                    return component;
            }
        }
        return null;
    }

    Location nonCommonLocation(Location originLocation, Wire originalWire, Wire matchedWire) {
        System.out.println("WireAnalyser.nonCommonLocation");
        if (originalWire == null) {
            return (matchedWire.e0.equals(originLocation)) ? matchedWire.e1 : matchedWire.e0;
        } else {
            if (originalWire.e0.equals(matchedWire.e0) || originalWire.e1.equals(matchedWire.e0))
                return matchedWire.e1;
            if (originalWire.e0.equals(matchedWire.e1) || originalWire.e1.equals(matchedWire.e1))
                return matchedWire.e0;
        }
        return null;
    }
}
