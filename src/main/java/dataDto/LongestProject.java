package dataDto;

public class LongestProject {
    private final String name;
    private final int monthCount;

    public LongestProject(String name, int monthCount) {
        this.name = name;
        this.monthCount = monthCount;
    }

    @Override
    public String toString() {
        return "{ " +
                "name = '" + name + '\'' +
                ", month_count = " + monthCount +
                " }";
    }
}
