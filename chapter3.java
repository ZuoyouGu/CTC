class Stack{
	Node top;
	Node pop(){
		if(top!=null){
			Node tmp = top;
			top = top.next;
			return tmp;
		}
	}
	void push(Object item){
		if(top==null) top = item;
		else{
			Node nu = new Node(item);
			nu.next = top;
			top = nu;
		}
	}
    boolean isEmpty(){
        return top==null;
    }
}

class Queue{
	Node head;
	Node tail;
	Node dequeue(){
		if(head == null) return null;
		Node tmp = head;
		head = head.next;
		if(head == null) tail = null;
		return tmp;
	}
	void enqueue(Object item){
		Node tmp = new Node(item);
		if(head == null){
			head = tmp;
			tail = tmp;
		}
		else{
			tail.next = tmp;
			tail = tmp;
		}
	}
}

3.1
Stack{
	int StackSize = 300;
	int[] buffer = new int[StackSize*3];
	int[] StartPt = {0, 0, 0};
	
	void Push(int num, int idx){
		if(StartPt[idx]==300) return;
		int index = StartPt[idx]+300*idx;
		buffer[index] = num;
		StartPt[idx]++;
	}
	int Pop(int idx){
		int index = StartPt[idx];
		if(index == 0) return 0;
		else{
			StartPt[idx]--;
			return buffer[StartPt[idx]+300*idx];
		}
	}
	int Peek(int idx){
		int index = idx*StackSize+StartPt[idx]-1;
		return buffer[index];
	}
}

3.2
class Stack{
	Node top;
	Node min;
	
	void Push(int item){
		if(top==null){
			top = new Node(item);
			min = top;
		}
		else{
			Node nu = new Node(item);
			if(item<min.val){
				min = nu;
			}
			nu.next = top;
			top = nu;
		}
	}
	
	int Min(){
		return min.val;
	}
	
	int Pop(){
		if(min!=null)
			if(min==top){
				Node runner = top.next;
				min = runner;
				while(runner!=null){
					if(runner.val<min.val){
						min = runner;
					}
					runner = runner.next;
				}
				runner = top;
				top = top.next;
				return runner.val;
			}
			else{
				Node tmp = top;
				top = top.next;
				return top.val;
			}
		}
		else{
			return 0;
		}
	}
}

// another way. Every node keeps the info about min num:
public class StackWithMin extends Stack<NodeWithMin>{
    public Push(int val){
        if(isEmpty()){
            super.Push(new NodeWithMin(val, val));
        }
        else{
            int newMin = Math.min(val, Peel().min);
            super.Push(new NodeWithMin(val, newMin));
        }
    }
    // in this situation, every top keeps the minimun value before itself.
}

public class NodeWithMin{
    public int val;
    public int min;
    public NodeWithMin(int val, int min){
        this.val = val;
        this.min = min;
    }
}

// optimization for the former program:
public class StackWithMin extends Stack<Node>{
    private MinStack = new Stack<node>();
    
    public void Push(int val){
        if(min()==null || val<=min().val){
            MinStack.Push(val);
        }
        super.Push(val);
    }
    
    public Node Pop(){
        Node node = super.Pop();
        if(node.val==min().val){
            MinStack.Pop();
        }
        return node;
    }
    private Node min(){
        return MinStack.Peek();
    }
}

3.3
public class SetOfStack{
    ArrayList<Stack> stacks = new ArrayList<Stack>();
    
    public void Push(int val){
        Stack last;
        if(stacks.size == 0) last = null;
        else last = stacks.get(stacks.size()-1);
        if(last!=null && last.size()<100){
            last.Push(val);
        }
        else{
            Stack stack = new Stack(capacity);
            stack.push(val);
            stacks.add(stack);
        }
    }
    
    public int Pop(){
        if(stacks.size()==0){
            return 0;
        }
        Stack last = stacks.get(stacks.size()-1);
        int v = last.Pop();
        if(last.size()==0) stacks.remove(stacks.size()-1);
        return v;
    }
    
    public int PopAt(int index){
        Stack stack = stacks.get(index);
        int val = stack.Pop().val;
        if(stack.size==0) stacks.remove(index);
        while(index<stacks.size()-1){
            stack = stacks.get(index);
            Stack nextstack = stacks.get(index+1);
            Node node = nextstack.getBottom();
            node.next = stack.top;
            stack.top = node;
            if(nextstack.size==0) stacks.remove(index+1);
        }
        return val;
    }
}

public class Stack{
    int capacity;
    int size = 0;
    public Node Top;
    
    Node Pop(){
        Node node = top;
        top = top.next;
        size--;
    }
    
    Node getBottom(){
        Node bottom = top;
        node previous = null;
        while(bottom.next!=null){
            Node previous = bottom;
            bottom = bottom.next;
        }
        if(previous != null){
            previous.next = null;
        }
        this.size--;
        return bottom;
    }
}

3.5

public class MyQueue<T>{
    Stack<T> s1, s2;
    
    public MyQueue(){
        s1 = new Stack<T>();
        s2 = new Stack<T>();
    }
    
    public void Enqueue(T t){
        s1.Push(t);
    }
    
    public T Dequeue(){
        if(!s2.isEmpty()){
            return s2.Pop();
        }
        while(!s1.isEmpty()){
            s2.Push(s1.Pop());
        }
        return s2.Pop();
    }
}

3.6
class SortStack{
    public static Stack<T> sortStack(Stack<T> s){
        Stack<T> s2 = new Stack<T>();
            while(!s.isEmpty){
                T tmp = s.Pop();
                while(!s2.isEmpty() && s2.Peek().val<tmp.val){
                    s.Push(s2.Pop());
                }
                s2.Push(tmp);
            }
        return s2;
    }
}
