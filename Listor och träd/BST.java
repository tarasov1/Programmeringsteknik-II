import java.util.ArrayList;

/**
 * Binary search tree with strings as keys.
 * @version 2017-09-13
 */
public class BST {
   /**
    * Inner class for tree nodes
    */
   private static class Node {
      private String key;
      private Node left, right;
      
      private Node(String key, Node left, Node right) {
         this.key = key;
         this.left  = left;
         this.right = right;
      }
   }
   
   /**
    * Inner class for TreeExceptions
    */
   public static class BSTException extends RuntimeException {
      public BSTException(String msg) {
         super(msg);
      }
   }
   
   private Node root;
  
   /**
    * Standard constructor
    */
   public BST() { 
     root = null; 
   }
  
   private BST(Node r) { 
     root = r; 
   }
  
   /**
   * Searches a specified key
   * @param key the key to be searched
   * @return true if the key is found, else false
   */ 
  public boolean contains(String key) {
     return contains(key, root);
   }
   
   private static boolean contains(String key, Node r) {
      if (r==null) {
         return false;
      } else if (key.compareTo(r.key) < 0) {
         return contains(key, r.left);   
      } else if (key.compareTo(r.key) > 0) {
         return contains(key, r.right);  
      } else {
         return true;
      }
   }
  
  
   /**
    * Insert a key preserving the sorted condition
    * @param key the key to be inserted
    */
   public void add(String key) {
     root = add(key, root);
   }
   
   private static Node add(String key, Node r) {
     if (r==null) {
       return new Node(key, null, null);
     } else if (key.compareTo(r.key) < 0) {
       r.left = add(key, r.left);
     } else if (key.compareTo(r.key) > 0) {
       r.right = add(key, r.right);
     } else {
       // Do nothing - the key is already in the tree
     }
     return r;
   }
     
   public String toString() {
     return "<" + toString(root) + ">";
   }
   
   private static String toString(Node r) {
     if (r==null) {
       return "";
     } else {
       return toString(r.left) + " " + r.key + toString(r.right);
     }
   }

   /******************** Methods to be implemented *************/
   
   /**
    * Compute the number of nodes in the tree
    * @return the number of nodes
    */
   public int size() {
    return size(root); 
   }

   private int size(Node n) {
    if (n == null) 
      return 0; 
    else 
      return 1 + size(n.left) + size(n.right); 
   }
  
   
   /**
    * Compute the height.
    * The height is defined as the number of nodes on 
    * the longest path from the root to a leaf.
    * @return the height
    */ 
   public int height() {
      return height(root);
   }

   private int height(Node n) {
    if (n == null) 
      return 0; 
    else
      return 1 + (height(n.left) > height(n.right) ? height(n.left) : height(n.right)); 
   }

   
   /** 
    * Find the smallest (defined by compareTo()) key in the tree
    * @return the smallest key
    */
   public String smallest() {
        return smallest(root, root.key);
    }
    
  private String smallest(Node cur, String value) {
        if (cur == null)
            return value;
        if (value.compareTo(cur.key) < 0) {
            value = cur.key;
        }
        String leftMinValue = smallest(cur.left, value);
        String rightMinValue = smallest(cur.right, value);
        if (leftMinValue.compareTo(value) > 0) {
            value = leftMinValue;
        }
        if (rightMinValue.compareTo(value) > 0) {
            value = rightMinValue;
        }
        return value;
    }

 
     
   /**
    * Construct an arraylist containing the keys from the nodes in symmetric order
    * i.e. the keys will be stored in alphabetic order.
    * @return an arraylist containing all keys from the tree i alphabetic order
    */
   
 
   public ArrayList<String> toArrayList() {
    ArrayList<String> keys = new ArrayList<String>(); 
    toArrayList(root, keys);
    return keys;
   }

   private void toArrayList(Node n, ArrayList keys) {
    if (n != null) {

        toArrayList(n.left, keys); 
        keys.add(n.key); 
        toArrayList(n.right, keys); 
    }
   }
   /**
    * Create a (deep) copy of the tree structure
    * @return a tree containing a copy of this tree
    */
   public BST copy() {
      if (size() == 0)
       return new BST();
     return new BST(copy(root));
   }

   public Node copy(Node n){
      if (n==null)
          return null;
      else
          return new Node(n.key, copy(n.left),copy(n.right));
   }

   
   /**
    * Check if this tree is equal to another tree.
    * Equal means the same branching structure and the same keys in the nodes.
    * @param t the tree to be compared with
    * @return <code>true</code> if the trees are equal, else <code>false</code
    */
    public boolean equals(BST t) {
      return equals(this.root,t.root);
    }
   
    private boolean equals(Node n1, Node n2){
       if (n1 == null && n2 == null){
         return true;
       }else if (n1 == null || n2 == null){
         return false;
       }else if (n1.key == n2.key){
         return ( equals(n1.right,n2.right) && equals(n1.left,n2.left) );
       }else{
         return false;
       }
    }
    
   
   /**
    * Check if two trees have exactly the same contents
    * @param t the tree to be compared with
    * @return <code>true</code> if the trees have the same contents, else <code>false</code>
    */
  
   
   public boolean sameContents(BST t) {
     ArrayList<String> storage1 = new ArrayList<String>(); 
     ArrayList<String> storage2 = new ArrayList<String>();
     return sameContents(this.root, t.root, storage1, storage2);
   }
   
   private boolean sameContents(Node n1, Node n2, ArrayList storage1, ArrayList storage2){
      if (n1 != null && n2 != null) 
        return true;
      if ((n1 == null && n2 != null) || (n1 != null && n2 == null))
        return false;

      
      toArrayList(n1, storage1); 
      toArrayList(n2, storage2); 

      return (storage1.equals(storage2));
  }
 

   
   /**
    * Compute the internal path length.
    * The internal path length can be defined as the
    * sum of the depths of the individual nodes.
    * The root has depth 1, the children of the root depth 2 etc.
    * Thus, a tree with one node has ipl 1, 
    * a tree with two nodes has ipl 3 and a tree with three nodes 
    * ipl 5 or 6 depending on shape.
    */
   
   public int ipl(){
     return ipl(root,1);
   }

   public static int ipl(Node n,int x){
     if (n==null)
       return 0;
     else if (n.right == null && n.left == null){
       System.out.println(x);
       return x;
     }else{
       //System.out.println(ipl(n.right,x+1));
       return x + ipl(n.right,x+1)+ipl(n.left,x+1);
     }
   }

   /**
    * Main-method showing calls to and results from all methods above
    */
   
   public static void main(String[] args) {
     System.out.println("BST main version of 2017-09-13");

     
     System.out.println("----------------------------------------------------------"); 
     BST test = new BST();

     BST test2 = new BST(new Node("d", new Node("b",null,null), new Node("z",null,null)));
     BST test4 = new BST(new Node("d", new Node("z",null,null), new Node("b",null,null)));
     test2.add("a");
     test2.add("c");
     test2.add("l");
     test2.add("k");
     test2.add("m");
     test4.add("a");
     test4.add("c");
     test4.add("l");
     test4.add("k");
     test4.add("m");
     System.out.println(test2.toString());
     System.out.println(test2.smallest());
    
    System.out.println(test2.toArrayList().toString());
    test = test2.copy(); 
    System.out.println(test.toString());
    System.out.println(test4.sameContents(test2));
    BST blabla = new BST();
    System.out.println(test.sameContents(blabla));
   }
  
}
