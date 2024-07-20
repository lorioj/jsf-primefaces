package simplejsf;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Admin
 */
public class Person {
    private String name;
    private List<Person> children;

    public Person(String name) {
            this.name = name;
            this.children = new ArrayList<>();
    }

    public String getName() {
            return name;
    }

    public void setName(String name) {
            this.name = name;
    }

    public List<Person> getChildren() {
            return children;
    }

    public void setChildren(List<Person> children) {
            this.children = children;
    }
}
