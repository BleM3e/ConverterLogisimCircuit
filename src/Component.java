public class Component {
    Location e;
    String componentName;

    Component(Location e, String componentName) {
        this.e = e;
        this.componentName = componentName;
    }

    @Override
    public String toString() {
        return "Component : " + componentName + " out : " + e;
    }
}
