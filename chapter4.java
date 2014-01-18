public class Node{
    int val;
    Node left = null;
    Node right = null;
    
    public Node(int v){
        val = v;
    }
    
    public void add(int v){
        if(v>val){
            right = new Node(v);
        }
        else{
            left = new Node(v);
        }
    }
}

public class Tree{
    Node root;
    
    public void Tree(int v){
        root = new Node(v);
    }
    
    public void insert(int v){
        Node node = root;
        if(root == null) {
            root = new Node(v);
            return;
        }
        
        while(node){
            if(node.val>v){
                if(node.left != null)
                    node = node.left;
                else{
                    node.left = new Node(v);
                    break;
                }
            }
            else{
                if(node.right != null){
                    node = node.right;
                }
                else{
                    node.right = new Node(v);
                    break;
                }
            }
        }
    }
    
    public Node remove(int v){
        Node node = root;
        while(node != null){
            if(node.val == v){
                Node replace;
                if(node.left){
                    replace = max(node);
                }
                else if(node.right){
                    replace = min(node);
                }
                else{
                    root = null;
                    return node;
                }
                replace.left = node.left;
                replace.right = node.right;
                return node;
            }
        }
        return null;
    }
    
    public Node max(Node node){
        if(node == null) return null;
        Node ret = node.left;
        if(ret.right==null) {
            node.left = ret.left;
            return ret;
        }
        Node previous;
        while(ret.right){
            previous = ret;
            ret = ret.right;
        }
        previous.right = ret.left;
        return ret;
    }
    
    public Node min(Node node){
        if(node == null) return null;
        Node ret = node.right;
        if(ret.left == null){
            node.right = ret.right;
            return ret;
        }
        Node previous = null;
        while(ret.left){
            previous = ret;
            ret = ret.left;
        }
        previous.left = ret.right;
        return ret;
    }
    
    private boolean isLeaf(Node node){
        if(node.right == null && node.left == null) return true;
        return false;
    }
}

4.1
public class Tree{
    public root;
    
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
}

4.2
// this implementation is depth-first search
// if you want to use a width-first search, you may need a stack or queue to
// store the adjacent nodes.

import java.util.ArrayList;

public class GraphNode{
    int val;
    ArrayList<GraphNode> inlink = new ArrayList<GraphNode>();
    ArrayList<GraphNode> outlink = new ArrayList<GraphNode>();
    boolean isvisited = false;
    
    // construction funcs
    public GraphNode(int val){
        this.val = val;
    }
    
    public void addInLink(GraphNode innode){
        inlink.add(innode);
    }
    
    public void addOutLink(GraphNode outnode){
        outlink.add(outnode);
    }
    
    //
    public boolean outRouteExist(GraphNode dest){
        this.isvisited = true;
        if(this==dest) return true;
        for(int i=0; i<outlink.size(); i++){
            GraphNode outnode = outlink.get(i);
            if(outnode.isvisited == false){
                boolean subroute = outnode.outRouteExist(dest);
                if(subroute == true) return true;
            }
        }
        return false;
    }
    
    public void resetOutRoute(GraphNode dest){
        this.isvisited = false;
        if(this == dest) return;
        for(int i=0; i<outlink.size(); i++){
            GraphNode outnode = outlink.get(i);
            if(outnode.isvisited == true){
                outnode.resetOutRoute(dest);
            }
        }
    }
    
    public void printOutlinks(){
    	System.out.println("node "+this.val+"'s outlinks");
        for(int i=0; i<outlink.size(); i++){
            GraphNode outnode = outlink.get(i);
            System.out.println(outnode.val+" "+outnode.isvisited);
        }
    }
    
    public void print(){
    	System.out.println("node "+this.val+" "+this.isvisited);
    }
    
    public static void main(String[] args){
        GraphNode node1 = new GraphNode(1);
        GraphNode node2 = new GraphNode(2);
        GraphNode node3 = new GraphNode(3);
        GraphNode node4 = new GraphNode(4);
        GraphNode node5 = new GraphNode(5);
        GraphNode node6 = new GraphNode(6);
        node1.addOutLink(node2);
        node1.addOutLink(node3);
        node1.addOutLink(node6);
        node2.addOutLink(node4);
        node3.addOutLink(node4);
        //node4.addOutLink(node5);
        System.out.println(node1.outRouteExist(node5));
        node1.print();
        node2.print();
        node3.print();
        node4.print();
        node5.print();
        node6.print();
    }
}

4.3
public class Tree{
    Node root;
    
    public Tree(int[] arr){
        root = buildTree(arr, 0, arr.size()-1);
    }
    
    public Node buildTree(int[] arr, int start, int end){
        if(start < end) return null;
        if(start == end){
            Node node = new Node(arr[start]);
        }
        else{
            int mid = (start + end)/2;
            Node node = new Node(arr[mid]);
            node.left = buildTree(arr, start, mid-1);
            node.right = buildTree(arr, mid+1, end);
        }
        return Node;
    }
}

4.4
public class Tree{
    Node root;
    
    public ArrayList<LinkedList> TreeToLinkedList(){
        ArrayList<Node> levelnodes = new ArrayList<Node>();
        ArrayList<LinkedList> linkedlists = new ArrayList<LinkedList>();
        
        levelnodes.add(root);
        while(levelnodes.size()!=0){
            LinkedList list = new LinkedList();
            int size = levelnodes.size();
            for(int i=0; i<size; i++){
                Node node = levelnodes.get(0);
                levelnodes.remove(0);
                list.add(node.val);
                if(node.left!=null) levelnodes.add(node.left);
                if(node.right!=null) levelnodes.add(node.right);
            }
            linkedlists.add(list);
        }
        return linkedlists;
    }
}
// here we can use LinkedList ArrayList only
// 只存链表，从链表中取出nodes，然后把nodes的左右儿子都存到新的链表里面，再插入到链表数组中。

4.5
public class Tree{
    public Node findNextNode(Node node){
        if(node.right!=null){
            node = node.right;
            while(node.left != null){
                node = node.left;
            }
            return node;
        }
        Node parent = node.parent;
        if(parent==null) return null;
        if(parent.left == node){
            return parent;
        }
        while(parent!=null && parent.left != node){
            node = parent;
            parent = parent.parent;
        }
        return parent;
    }
}
