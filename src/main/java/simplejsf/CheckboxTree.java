/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplejsf;

import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import org.primefaces.event.NodeSelectEvent;
import org.primefaces.event.NodeUnselectEvent;
import org.primefaces.model.CheckboxTreeNode;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

/**
 *
 * @author Admin
 */
@ManagedBean
public class CheckboxTree {

    private TreeNode<String> tree;

    private List<TreeNode> selected;

    private String textAreaValue;

    @PostConstruct
    public void init() {
        System.err.println("CheckboxTree init");
        tree = createDynamicTree();
    }

    public TreeNode<String> createDynamicTree() {
        TreeNode<String> root = new CheckboxTreeNode<>();

        TreeNode<String> n = new CheckboxTreeNode<>("Root", root);

        TreeNode<String> n2 = new CheckboxTreeNode<>("n", n);

        TreeNode<String> n3 = new CheckboxTreeNode<>("n2", n2);

        TreeNode<String> n4 = new CheckboxTreeNode<>("n3", n2);
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

    public void onSelectedNode(NodeSelectEvent event) {
        System.err.println("Select node...");

        TreeNode<String> node = event.getTreeNode();
        String d = node.getParent().getData();
        List<TreeNode<String>> children = node.getChildren();
        this.selected.add(node);

        //other logic
    }

    public void onUnselectedNode(NodeUnselectEvent event) {
        System.err.println("Unselect node.....");
        TreeNode<String> node = event.getTreeNode();
        this.selected.remove(node);

        //other logic
    }

    public void setTextAreaValue(String textAreaValue) {
        this.textAreaValue = textAreaValue;
    }

    public String getTextAreaValue() {
        return this.textAreaValue;
    }

    
    
    
    
    public void printP(List<TreeNode<String>> l, TreeNode<String> parent) {
        if (l == null) {
            return;
        }

        for (TreeNode<String> p : l) {
            TreeNode<String> c = new DefaultTreeNode<>(p.getData(), parent);
            printP(c.getChildren(), c);
        }

    }

}
