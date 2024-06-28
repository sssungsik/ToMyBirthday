package birthday;


import lombok.Getter;

@Getter
public class studyLombok {
    private final String name;
    private final int age;

    public studyLombok(String name, int age) {
        this.name = name;
        this.age = age;
    }

}
