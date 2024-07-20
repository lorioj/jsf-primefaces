/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package simplejsf;

import javax.faces.bean.ManagedBean;
import org.primefaces.model.TreeNode;
import org.primefaces.model.DefaultTreeNode;
import simplejsf.Document;

/**
 *
 * @author Admin
 */

@ManagedBean
public class TreeTableManageBean {
    private TreeNode root = new DefaultTreeNode("Root Node", null);

	public TreeTableManageBean(){
		// Populate Document Instances
		Document doc01 = new Document("Primefaces Tutorial","1","Primefaces Company");
		
		Document doc02 = new Document("Hibernate Tutorial","2","JournalDev");
		
		// Create Documents TreeNode
		TreeNode documents = new DefaultTreeNode(new Document("Documents","0","Documents"), this.root);
		// Create Document TreeNode
		TreeNode document01 = new DefaultTreeNode(doc01, documents);
		TreeNode document02 = new DefaultTreeNode(doc02, documents);
                TreeNode document03 = new DefaultTreeNode(doc02, document01);
	}
	
	public TreeNode getRoot() {
		return root;
	}

	public void setRoot(TreeNode root) {
		this.root = root;
	}
    
}
