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
