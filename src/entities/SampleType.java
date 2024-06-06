package entities;


public class SampleType {
    private String prefix;
    private String name;

    public SampleType(String prefix, String name) {

        setPrefix(prefix);
        setName(name);

    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Getters and Setters
}