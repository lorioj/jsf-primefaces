/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplejsf;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.event.AjaxBehaviorEvent;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Admin
 */
@ManagedBean
public class CheckboxTree {

    private TreeNode<String> tree;

    private List<TreeNode> selected = new ArrayList<>();

    private String textAreaValue;

    private Set<String> result = new TreeSet<>();

    private int totalNodeCount;

    private String radioValue;

    private String rootName = "Root";

    @PostConstruct
    public void init() {
        System.err.println("CheckboxTree init");

        tree = createDynamicTree();
        totalNodeCount = getTotalNodeCount(tree);
    }

    public TreeNode<String> createDynamicTree() {
        TreeNode<String> root = new CheckboxTreeNode<>(rootName, null);

        TreeNode<String> n = new CheckboxTreeNode<>("All", root);
        n.setExpanded(true);

        TreeNode<String> m2 = new CheckboxTreeNode<>("m", n);
        m2.setExpanded(true);

        TreeNode<String> m3 = new CheckboxTreeNode<>("m2", m2);
        m3.setExpanded(true);

        TreeNode<String> m4 = new CheckboxTreeNode<>("m3", m2);
        m4.setExpanded(true);

        TreeNode<String> n2 = new CheckboxTreeNode<>("n", n);
        n2.setExpanded(true);

        TreeNode<String> n3 = new CheckboxTreeNode<>("n2", n2);
        n3.setExpanded(true);

        TreeNode<String> n4 = new CheckboxTreeNode<>("n3", n2);
        n4.setExpanded(true);

        totalNodeCount = getTotalNodeCount(root);

        return root;

    }

    public void setTree(TreeNode<String> tree) {
        this.tree = tree;
    }

    public TreeNode getTree() {
        return this.tree;
    }

    public void setSelected(List<TreeNode> selected) {
        this.selected = selected;
    }

    public List<TreeNode> getSelected() {
        return this.selected;
    }

    public Set<String> getResult() {
        return this.result;
    }

    public void setResult(Set<String> result) {
        this.result = result;
    }

    public String getRadioValue() {
        return this.radioValue;
    }

    public void setRadioValue(String radioValue) {
        this.radioValue = radioValue;
    }

    public void onSelectedNode(NodeSelectEvent event) {
        System.err.println("Select node...");

        TreeNode<String> node = event.getTreeNode();
        String d = node.getParent().getData();
        List<TreeNode<String>> children = node.getChildren();

        if (!this.selected.contains(node)) {
            this.selected.add(node);
        }

        onUpdate();
        textAreaValue = joinResult();

        //other logic
    }

    public void onUnselectedNode(NodeUnselectEvent event) {
        System.err.println("Unselect node.....");
        TreeNode<String> node = event.getTreeNode();
        this.selected.remove(node);

        onUpdate();
        //other logic
    }

    public void onUpdate() {
        System.err.println("node size" + selected.size());
        for (TreeNode<String> n : selected) {

            traverseChild(n.getChildren(), n);
            traverseParent(n);

        }

        textAreaValue = joinResult();
    }

    public void setTextAreaValue(String textAreaValue) {
        this.textAreaValue = textAreaValue;
    }

    public String getTextAreaValue() {
        return this.textAreaValue;
    }

    public void traverseChild(List<TreeNode<String>> node, TreeNode<String> parent) {
        if (node == null) {
            return;
        }
        result.add(parent.getData());
        for (TreeNode<String> p : node) {
            traverseChild(p.getChildren(), p);
        }

    }

    public void traverseParent(TreeNode<String> node) {
        if (node.getData().equals(rootName) || node.getParent() == null) {
            return;
        }
        result.add(node.getData());
//        System.out.println("simplejsf.CheckboxTree.traverseParent() " + node.getData());
        traverseParent(node.getParent());

    }

    public String joinResult() {
        StringBuilder sb = new StringBuilder();

        result.forEach(s -> {
            sb.append(s + "\n");
        });
        if (result.size() != totalNodeCount - 1) {
            sb.replace(0, 3, "");
        }

        return sb.toString();
    }

    public int getTotalNodeCount(TreeNode<String> node) {
        if (node == null) {
            return 0;
        }
        int count = 1;
        for (TreeNode c : node.getChildren()) {
            count += getTotalNodeCount(c);
        }
        return count;
    }

    public void onRadioChange(AjaxBehaviorEvent event) {

        if (radioValue.equals("A")) {
            setAll(tree);
            onUpdate();

        } else if (radioValue.equals("E")) {

        } else if (radioValue.equals("S")) {

        }
    }

    public void onInputChange(AjaxBehaviorEvent event) {
        String[] arr = textAreaValue.split("\n");

        Set<String> valuesSet = new HashSet<>();
        for (String str : arr) {
            valuesSet.add(str);
        }

        traverseExcept(valuesSet, tree.getChildren(), tree);
    }

    public void traverseExcept(Set<String> sets, List<TreeNode<String>> children, TreeNode<String> node) {

        System.err.println(sets);
        if (node == null) {
            return;
        }

        if (sets.contains(node.getData())) {
            node.setSelected(false);
            selected.add(node);
            return;
        } else {
            node.setSelected(true);
            selected.add(node);
        }

        for (TreeNode<String> p : children) {

            traverseExcept(sets, p.getChildren(), p);
        }

    }

    public void setAll(TreeNode<String> node) {
        if (node == null) {
            return;
        }

        for (TreeNode<String> c : node.getChildren()) {
            selected.add(c);
            c.setSelected(true);
            setAll(c);
        }
    }


}
