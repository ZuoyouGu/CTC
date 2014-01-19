/** @brief Implemented basic tree
 * 	Including insert, delete and findMax, findMin, find
 *  maxDepth, minDepth and isBalanced.
 *  Also functions like preorder and inorder used to examine the tree
 * 
 *  @author Zuoyou Gu
 *
 */

public class Tree{// binary search tree node
	public class Node{
	    public int data;
	    public Node left = null;
	    public Node right = null;
	    
	    public Node(int d){
	        data = d;
	    }
	}
    Node root = null;
    
    public Tree(int da){
        root = new Node(da);
    }
    
    public Tree() {
    	root = null;
	}
    
    public Tree(int[] arr){
    	int length = arr.length;
    	for(int i=0; i<length; i++){
    		this.insert(arr[i]);
    	}
    }
    
    public Tree(int[] arr, int k){
    	root = buildTree(arr, 0, arr.length-1);
    }
    
    public Node buildTree(int[] arr, int start, int end){
    	//System.out.println(start+", "+end);
        if(start > end) return null;
        Node node;
        if(start == end){
             node = new Node(arr[start]);
        }
        else{
            int mid = (start + end)/2;
            node = new Node(arr[mid]);
            node.left = buildTree(arr, start, mid-1);
            node.right = buildTree(arr, mid+1, end);
        }
        return node;
    }

	public void insert(int da){
        if(root == null) {
            root = new Node(da);
            return;
        }
        Node node = root;
        
        while(node!=null){
            if(node.data>da){
                if(node.left != null)
                    node = node.left;
                else{
                    node.left = new Node(da);
                    break;
                }
            }
            else{
                if(node.right != null){
                    node = node.right;
                }
                else{
                    node.right = new Node(da);
                    break;
                }
            }
        }
    }
    
	public Node find(int da){
		Node node = root;
		while(node!=null){
			if(da<node.data){
				node = node.left;
			}
			else if(da>node.data){
				node = node.right;
			}
			else{
				return node;
			}
		}
		return null;
	}
	
	public Node findMax(Node node){
		if(node==null) return null;
		while(node.right!=null){
			node = node.right;
		}
		return node;
	}
	
	public Node findMin(Node node){
		if(node==null) return null;
		while(node.left!=null){
			node = node.left;
		}
		return node;
	}
		
	/** @brief Remove a node from the tree.
	 * 
	 * 	First we search this data in the tree as we are going to return it.
	 *  Here we'd better separate remove_r and find procedure.
	 *  remove_r can be used recursively. So remove_r return the root for updated
	 *  sub tree, while find is the one used to return the value.
	 *  @param da The data to be deleted
	 *  @return
	 */
	public Node remove(int da){
		Node ret = find(da);
		// if this node is root, then we need update the root.
		if(ret == null)
			return null;
		if(ret == root){
			root = remove_r(root, da);
		}
		// otherwise, we just delete it and keep the root unchanged.
		else{
			remove_r(root, da);
		}
		return ret;
	}
	
	// recursive version deletion
    public Node remove_r(Node node, int da){
		Node r = node;
		Node parent = null;
		boolean isleft = false;
		while(node!=null){
			if(da<node.data){
				parent = node;
				isleft = true;
				node = node.left;
			}
			else if(da>node.data){
				parent = node;
				isleft = false;
				node = node.right;
			}
			else{//find this node and delete it
				/* m is the selected node that is going to replace the removed
				 * node.
				 */
				Node m = null;
				if(node.left!=null){
					m = findMax(node.left);
					m.left = remove_r(node.left, m.data);
					m.right = node.right;
				}
				else if(node.right!=null){
					m = findMin(node.right);
					m.left = node.left;
					m.right = remove_r(node.right, m.data);
				}
				// if the root isn't the node to removed
				// we will return root
				if(parent!=null){
					if(isleft){
						parent.left = m;
					}
					else{
						parent.right = m;
					}
				}
				// if the root is the node to be removed
				// we return m, which is the new root.
				else{
					r = m;
				}
				// as we have found the node and deleted the node
				// now we get out of the loop and return the root.
				break;
			}
		}
		// return the new root, possibly the same root.
		return r;
    }
    
    public boolean isLeaf(Node node){
        if(node.right == null && node.left == null) return true;
        return false;
    }
	
    public int maxDepth(Node node){
        if(node == null){
            return 0;
        }
        return Math.max(maxDepth(node.left), maxDepth(node.right))+1;
    }    
	
    public int minDepth(Node node){
        if(node == null){
            return 0;
        }
        return Math.min(minDepth(node.right), minDepth(node.left))+1;
    }
    
    public boolean isBalanced(){
        return ((maxDepth(root) - minDepth(root))<=1);
    }
    
    public void preorder(){
    	preorder_r(root);
    }
    
    public void preorder_r(Node node){
    	if(node!=null){
    		System.out.print(node.data+", ");
    		if(node.left!=null)
    			preorder_r(node.left);
    		if(node.right!=null)
    			preorder_r(node.right);
    	}
    }
    
    public void inorder(){
    	inorder_r(root);
    }
    
    public void inorder_r(Node node){
    	if(node!=null){
        	if(node.left!=null)
        		inorder_r(node.left);
    		System.out.print(node.data+", ");
        	if(node.right!=null)
        		inorder_r(node.right);
    	}
    }
    
    public static void main(String[] args){
    	int[] arr = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};
    	//int[] arr = {8, 4, 2, 6, 1, 3, 5, 7, 11, 10, 12, 9, 13};
    	Tree tree = new Tree(arr, 1);
    	//tree.remove(3);
    	tree.inorder();
    	System.out.println();
    	tree.preorder();
    	System.out.println();
    	System.out.println(tree.isBalanced());
    }
}