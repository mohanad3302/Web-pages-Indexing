/*
    name : mohanad ayman ibrahim khaled 
    ID : 20100259
    name : khaled ezz eldin younis
    ID : 20100252
*/
class AVLTree implements AVLTreeInterface{
    TreeNode root ;

    public AVLTree(){
        root = null ;
    }

    public void insertTreeNode (String[] data){
        root = insertTreeNodeRecursion(root , data);
    }

    private TreeNode insertTreeNodeRecursion (TreeNode root , String[] data){
        if ( root == null ){
           return creatNewTreeNode(data);
        }
        if(data[0].substring(0 , data[0].indexOf(".")).equals(root.value.head.data.substring(0 , root.value.head.data.indexOf(".")))) {
            root.value.insert(data[1]);
        }
        else if (data[0].charAt(0) > root.value.head.data.charAt(0)){
            root.right = insertTreeNodeRecursion(root.right, data);
            if( getHeight( root.right ) - getHeight( root.left ) == 2 )
                if( data[0].charAt(0) > root.right.value.head.data.charAt(0))
                    root = rotateWithRight( root );
                else
                    root = doubleWithRight( root );
        }
        else if ( data[0].charAt(0) < root.value.head.data.charAt(0) ){
            root.left = insertTreeNodeRecursion(root.left, data);
            if( getHeight( root.left ) - getHeight( root.right ) == 2 )
                if( data[0].charAt(0) < root.left.value.head.data.charAt(0) )
                    root = rotateWithLeft( root );
                else
                    root = doubleWithLeft( root );
        }
        else { 
            return root ;
        }


        return root ;
    }

    private TreeNode creatNewTreeNode (String[] data){
        LinkedList list = new LinkedList();
        list.insert(data[0]);
        list.insert(data[1]);
        TreeNode newTreeNode = new TreeNode(list);
        return newTreeNode ;
    }

    public void inorder() { 
        inorderRecursion(root); 
    } 
   
 
    private void inorderRecursion(TreeNode root) { 
        if (root != null) { 
            inorderRecursion(root.left); 
            root.value.display(); 
            inorderRecursion(root.right); 
        } 
    }

    public TreeNode searchForURl (String url){
        if ( root == null ){
            return root ; 
        }
        else {
            TreeNode current = root ;
            current = searchForURlRecursion(root, url);
            if ( current != null){
                return current ;
            }
            else{
                return null ;
            }
        }
        
    }

    private TreeNode searchForURlRecursion(TreeNode root , String url){
        int rootIndex = root.value.head.data.indexOf(".");
        String rootValue = root.value.head.data.substring(0 , rootIndex);
        int urlIndex = url.indexOf(".");
        String urlValue = url.substring(0, urlIndex);
        if (root == null || rootValue.equals(urlValue)){
            return root ;
        }
        if ( root.left != null ){
            if (rootValue.charAt(0) > url.charAt(0)){
                return searchForURlRecursion(root.left, urlValue);
            }    
        }
        if (root.right != null ){
            if (rootValue.charAt(0) < url.charAt(0) ){
                return searchForURlRecursion(root.right, urlValue);
            }
        }
        
        return root ;
    }

    public TreeNode searchForIP (String IP ) {
        TreeNode current = searchForIPRecursion(IP , root );
        if (current != null){
            return current;
        }else {
            return null ;
        }
    }

    private TreeNode searchForIPRecursion (String IP  , TreeNode root){
        if ( root == null){
            return root ;
        }

        boolean x = root.value.search(IP);
        if (x == true ){
            return root ;
        }
        
        TreeNode left = searchForIPRecursion(IP , root.left );
        if ( left != null && left.value.search(IP) == true){
            return left ;
        }

        TreeNode right = searchForIPRecursion(IP , root.right);
        if ( right != null && right.value.search(IP) == true ){
            return right ;
        }

        return root ;
    }

    private int getHeight(TreeNode node )
    {
        if ( node == null ){
            return -1 ;
        }else {
            return node.height ;
        }
    }

    private int getMaxHeight(int leftNodeHeight, int rightNodeHeight)
    {   
        if ( leftNodeHeight > rightNodeHeight){
            return leftNodeHeight ;
        }
        else {
            return rightNodeHeight ;
        }
    }

    private TreeNode rotateWithLeft(TreeNode TreeNode2)
    {
        TreeNode TreeNode1 = TreeNode2.left;
        TreeNode2.left = TreeNode1.right;
        TreeNode1.right = TreeNode2;
        TreeNode2.height = getMaxHeight( getHeight( TreeNode2.left ), getHeight( TreeNode2.right ) ) + 1;
        TreeNode1.height = getMaxHeight( getHeight( TreeNode1.left ), TreeNode2.height ) + 1;
        return TreeNode1;
    }

    private TreeNode rotateWithRight(TreeNode TreeNode1)
    {
        TreeNode TreeNode2 = TreeNode1.right;
        TreeNode1.right = TreeNode2.left;
        TreeNode2.left = TreeNode1;
        TreeNode1.height = getMaxHeight( getHeight( TreeNode1.left ), getHeight( TreeNode1.right ) ) + 1;
        TreeNode2.height = getMaxHeight( getHeight( TreeNode2.right ), TreeNode1.height ) + 1;
        return TreeNode2;
    }

    private TreeNode doubleWithLeft(TreeNode TreeNode3)
    {
        TreeNode3.left = rotateWithRight( TreeNode3.left );
        return rotateWithLeft( TreeNode3 );
    }

    private TreeNode doubleWithRight(TreeNode TreeNode1)
    {
        TreeNode1.right = rotateWithLeft( TreeNode1.right );
        return rotateWithRight( TreeNode1 );
    }
}  
