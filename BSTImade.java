public class BSTImade {

     public TreeNode rootNode;
     
     public BSTImade(){
          this.rootNode = null;
     }
    
     public String search(int key) {
          return search(rootNode,key);
     }
     
     private String search(TreeNode startingNode, int key){
          //Exit case, reached end of tree without finding value
          if (startingNode == null){
               return null;
          }
          //if paramater key is less than examined node key, call search function on next left node
          else if (key < startingNode.key){
               return search(startingNode.left, key);
          }
          //if paramater key is greater than examined node key, call search function on next right node
          else if (key > startingNode.key){
               return search(startingNode.right, key);
          }
          //To reach this, the parameter key must be equal to the examined node key, so we return the examined node's
          //paired value
          return startingNode.value;     
     }

     //inorder = left, root, right
     public void printInOrder(){
          printInOrder(rootNode);
     }
     
     private void printInOrder(TreeNode startingNode){
          if(startingNode != null){
               printInOrder(startingNode.left);
               System.out.println(startingNode.key + ":" + startingNode.value);
               printInOrder(startingNode.right);
          }    
     }
        
     //Preorder = root, left, right
     public void printPreOrder(){
          printPreOrder(rootNode);
     }
     
     private void printPreOrder(TreeNode startingNode){
          if(startingNode != null){
               System.out.println(startingNode.key + ":" + startingNode.value);
               printPreOrder(startingNode.left);
               printPreOrder(startingNode.right);
          }    
     }
         
     //postorder = right, left, root
     public void printPostOrder(){
          printPostOrder(rootNode);
     }
     
     private void printPostOrder(TreeNode startingNode){
          if(startingNode != null){
               printPostOrder(startingNode.left);
               printPostOrder(startingNode.right);
               System.out.println(startingNode.key + ":" + startingNode.value);                             
          }    
     }

     public void insert(int key, String value){
          rootNode = insert(rootNode, key, value);
     }
     
     private TreeNode insert(TreeNode rootNode, int key, String value){
          //We've reached the end of the branch, so we insert our new node here
          if(rootNode == null){
               rootNode = new TreeNode(key, value);
          }
          //The key we want to insert is less than the node we are examining, so we go left
          else if (key < rootNode.key){
               rootNode.left = insert(rootNode.left, key, value);
          }
          //The key we want to insert is greater than the node we are examining, so we go right
          else if (key > rootNode.key){
               rootNode.right = insert(rootNode.right, key, value);
          }     
          //The key we want to insert is equal to the node we are examing, so we update the examined nodes's value field
          else {
               rootNode.value = value;
          }
          return rootNode;
     }

     //Rightmost node will always be the maximum
     public int getMax(){
          return getMax(rootNode);    
     }

     private int getMax(TreeNode rootNode){
          if (rootNode.right != null){
               return getMax(rootNode.right);
          }          
          return rootNode.key;
     }

     //Leftmost node will always be the minimum
     public int getMin(){
          return getMin(rootNode);
     }
     
     private int getMin(TreeNode rootNode){
          if (rootNode.left != null){
               return getMin(rootNode.left);
          }
          return rootNode.key;
     }

     public void remove(int key){
          rootNode = remove(rootNode, key);
     }

     private TreeNode remove(TreeNode rootNode, int key){
          if(rootNode == null){
               return null;
          }
          
          else if(key < rootNode.key){
               rootNode.left = remove(rootNode.left, key);
          }
          
          else if(key > rootNode.key){
               rootNode.right = remove(rootNode.right, key);
          }
          //at this point parameter key is equal to examined node key
          else{
               //If node that is being removed has only a left child
               if(rootNode.right == null){
                    return rootNode.left;
               }
               else if(rootNode.left == null){
                    return rootNode.right;
               }
               //at this point the node being removed must have both right and left children
               else{
                    //Find the minimum of the subtree of the right child
                    rootNode.key = getMin(rootNode.right);//the node being removed's key is now equal to the minimum of right subtree
                    rootNode.value = search(rootNode.right, rootNode.key);//updating node being removed's value from obtained key
                    rootNode.right = remove(rootNode.right, rootNode.key);//Now we remove the minimum of the right subtree
               }                                                          //so we dont have 2 copies
          }
          return rootNode;          
     }

     public int leafCount() {
          return leafCount(rootNode);
     }
     
     private int leafCount(TreeNode rootNode){
          //Exit cases
          //Special case for null tree
          if(rootNode == null){
               return 0;
          }
          //examined node is a leaf
          else if(rootNode.right == null && rootNode.left == null){
               return 1;
          }
          else {//If the examined node is not a leaf, examine it's right child & it's left child
               return leafCount(rootNode.left) + leafCount(rootNode.right);
          }    
     }




     public int countParents(){
          return countParents(rootNode);
     }
     
     
     private int countParents(TreeNode rootNode){
          //If the rootNode is null then it's a null tree
          
          //If rootNode.right and rootNode.left are null then it's a leaf
          //The second condition makes it such that we can stop examining when we get to the leaf
          //If we stay with only the first condition, then we examine the left and right null next nodes of the leaf
          //We we can make our program faster if we look ahead at both next nodes rather than
          //Going in to the next nodes knowing that they are already null                 
          if (rootNode == null || (rootNode.right == null && rootNode.left == null)){
               return 0;
          }
          
          else if (rootNode.left == null && rootNode.right != null){
               return 1 + countParents(rootNode.right);
          }         
          else if (rootNode.left != null && rootNode.right == null){
               return 1 + countParents(rootNode.left);
          }
          
          else {
               return countParents(rootNode.left) + countParents(rootNode.right);
          }
     }
     
     
     private int guess(TreeNode rootNode) {
          if (rootNode == null) {
               return 0;
          } else {
               return rootNode.key + guess(rootNode.left) + guess(rootNode.right);
          }
     }
     
     public int guess(){
         return guess(rootNode);
     }





     //node class
     private class TreeNode {
     
          public int key;
          public String value;
          public TreeNode left;
          public TreeNode right;
          
          public TreeNode(int key, String value, TreeNode left, TreeNode right){
               this.key = key;
               this.value = value;
               this.left = left;
               this.right = right;
          }
          
          public TreeNode(int key, String value){
               this(key, value, null, null);
          }    
     }
}