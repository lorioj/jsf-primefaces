package simplejsf;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.faces.bean.ManagedBean;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Admin
 */
@ManagedBean
public class PersonView {
    
    private TreeNode<Person> root;
    
    public PersonView(){
        
        root = new  DefaultTreeNode("Root Node", null);
        Map<String, List<String>> m = new HashMap<>();
        m.put(null, new ArrayList<>(Arrays.asList("Joseph", "john4")));
        m.put("max", new ArrayList<>(Arrays.asList("Joseph")));
        m.put("Joseph", new ArrayList<>(Arrays.asList("jeff", "john", "cynthia")));
        m.put("jeff", new ArrayList<>(Arrays.asList("totoy", "yuna")));
        m.put("john", new ArrayList<>(Arrays.asList("john1", "john2", "john3")));
        m.put("john1", new ArrayList<>(Arrays.asList("john4")));
        m.put("totoy", new ArrayList<>(Arrays.asList("totoy1", "totoy2")));
        m.put("totoy1", new ArrayList<>(Arrays.asList("totoy1111", "totoy2111")));
        
         
        List<Person> grand = ancestors(m, null); // start from level 0
        for (Person p : grand) {
            System.err.println(p.getName());
            TreeNode parent = new DefaultTreeNode(p, root);
            printP(p.getChildren(), parent); 
        }
    }
    
    public TreeNode getRoot(){
        return this.root;
    }
    
    

    static void printP(List<Person> l, TreeNode parent) {
        if (l == null) {
                return;
        }

        for (Person p : l) {
            TreeNode c = new DefaultTreeNode(p, parent);
            printP(p.getChildren(), c);
         
        }

    }

    
    public  List<Person> ancestors(Map<String, List<String>> g, String p) {
        if (g.containsKey(p)) {
                List<String> parents = g.get(p);
                List<Person> result = new ArrayList<>();
                for (String parent : parents) {
                        Person person = new Person(parent);
                        person.setChildren(ancestors(g, parent));
                        result.add(person);
                }
                return result;
        }
        return new ArrayList<>();
    }

    
    
}
