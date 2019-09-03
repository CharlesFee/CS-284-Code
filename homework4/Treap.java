package homework4;

import java.util.Random;

/**
 * A class to represent a treap, that is, a BST with node placement
 * randomized by probabilistic heap-like priorities
 * @author CS284 team
 * @author Charles Fee
 * I pledge my honor that I have abided by the Stevens Honors System
 */
public class Treap<E extends Comparable<E>> extends BinarySearchTree<E> {
    protected static class Node<E> {
	public E data; // key for the search
	public int priority; // random heap priority
	public Node<E> left;
	public Node<E> right;

	/** Creates a new node with the given data and priority. The
	 *  pointers to child nodes are null. Throw exceptions if data
	 *  is null. 
	 */
	public Node(E data, int priority){
	    if(null == data){
		//THROW EXCEPTION
	    }else{
		this.data = data;
		this.priority = priority;
		left = null;
		right = null;
	    }
	}
	
	public Node<E> rotateRight() {
	    Node<E> temp = new Node<E>(this.data, this.priority);
	    this.data = left.data;
	    this.priority = left.priority;
	    temp.left = left.right;
	    temp.right = right;
	    left = left.left;
	    right = temp;
	    return new Node<E>(this.data, this.priority);	    
	}

	public Node<E> rotateLeft() {
	    Node<E> temp = new Node<E>(this.data, this.priority);
	    this.data = right.data;
	    this.priority = right.priority;
	    temp.right = right.left;
	    temp.left = left;
	    right = right.right;
	    left = temp;
	    return new Node<E>(this.data, this.priority); 
	}
    }

    private Random priorityGenerator;
    private Node<E> root;
    protected boolean addReturn;
    protected boolean deleteReturn;
    /** Create an empty treap. Initialize {@code priorityGenerator}
     * using {@code new Random()}. See {@url
     * http://docs.oracle.com/javase/8/docs/api/java/util/Random.html}
     * for more information regarding Java's pseudo-random number
     * generator. 
     */
    public Treap() {
	priorityGenerator = new Random();
	root = null;
    }


    /** Create an empty treap and initializes {@code
     * priorityGenerator} using {@code new Random(seed)}
     */
    public Treap(long seed) {
	priorityGenerator = new Random(seed);
	root = null;
    }
    public boolean add(E key, Integer priority){
	root = add(root, key, (int) priority);
	return addReturn;
    }
    public boolean add(E key) {
	root = add(root, key, priorityGenerator.nextInt());
	return addReturn;
    }

    private Node<E> add(Node<E> localroot, E key, int priority) {

	if(localroot == null){
	    addReturn = true;
	    return new Node<E>(key, priority);
	}else if(key.compareTo(localroot.data) == 0){
	    addReturn = false;
	    return localroot;
	}else if(key.compareTo(localroot.data) < 0){
	    localroot.left = add(localroot.left, key, priority);
	    if(priority < localroot.priority){
		localroot.rotateRight();
	    }
	    return localroot;
	}else{
	    localroot.right = add(localroot.right, key, priority);
	    if(priority < localroot.priority){
		localroot.rotateLeft();
	    }
	    return localroot;
	}
    }

    public E delete(E key) {
	Node<E> deleteyBoi = deleteyBoifind(this.root, key);
	while(deleteyBoi.left != null || deleteyBoi.right !=null){
		if((deleteyBoi.left !=null)){
			if(deleteyBoi.right != null){
				if(deleteyBoi.right.priority > deleteyBoi.left.priority){
					deleteyBoi.rotateRight();
				}
				else{
					deleteyBoi.rotateLeft();
				}
			}else{
				deleteyBoi.rotateRight();
			}
	    }else{
			deleteyBoi.rotateLeft();
	    }
		deleteyBoi = deleteyBoifind(this.root, key);
	}
	Node<E> localRoot = root;
	while(null != localRoot.left || null != localRoot.right){
	    if(localRoot.data.compareTo(deleteyBoi.data) > 0){
			if(localRoot.left != deleteyBoi){
			localRoot = localRoot.left;
			}else{
				break;
			}
	    }else if(localRoot.data.compareTo(deleteyBoi.data) < 0){
			if(localRoot.right != deleteyBoi){
			localRoot = localRoot.right;
			}else{
				break;
			}
		}else{
			break;
	    }
	}
	if(null != localRoot.left && localRoot.left.data == deleteyBoi.data){
	    localRoot.left = null;
	}else if(null != localRoot.right && localRoot.right.data == deleteyBoi.data){
	    localRoot.right = null;
	}
	return deleteyBoi.data;
    }
    private Node<E> deleteyBoifind(Node<E> root, E key) {
        if (root == null) {
            return null;
        }
	
        // Compare the target with the data field at the root.
        int compResult = key.compareTo(root.data);
        if (compResult == 0) {
            return root;
        } else if (compResult < 0) {
            return deleteyBoifind(root.left, key);
        } else {
            return deleteyBoifind(root.right, key);
        }
    }
    
    private E find(Node<E> root, E key) {
        if (root == null) {
            return null;
        }

        // Compare the target with the data field at the root.
        int compResult = key.compareTo(root.data);
        if (compResult == 0) {
            return root.data;
        } else if (compResult < 0) {
            return find(root.left, key);
        } else {
            return find(root.right, key);
        }
    }

    public E find(E key) {
        return find(root, key);
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        preOrderTraverse(root, 1, sb);
        return sb.toString();
    }

    /**
     * Perform a preorder traversal.
     * @param node The local root
     * @param depth The depth
     * @param sb The string buffer to save the output
     */
    private void preOrderTraverse(Node<E> node, int depth,
            StringBuilder sb) {
        for (int i = 1; i < depth; i++) {
            sb.append("\t");
        }
        if (node == null) {
            sb.append("null\n");
        } else {
            sb.append("(key="+node.data+", priority="+node.priority+")");
            sb.append("\n");
            preOrderTraverse(node.left, depth + 1, sb);
            preOrderTraverse(node.right, depth + 1, sb);
        }
    }
}
