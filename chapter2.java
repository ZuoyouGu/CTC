import java.util.HashMap;


class LinkedListNode{
	int val;
	LinkedListNode next = null;
	public LinkedListNode(int v){
		this.val = v;
	}
	public void appendToTail(int v){
		LinkedListNode node = new LinkedListNod(v);
		LinkedListNode n = this;
		while(n.next != null){
			n = n.next;
		}
		n.next = node;
	}
}

2.1
public static LinkedListNode removeDuplicates(LinkedListNode list){
	HashMap<Integer, Boolean> table = new HashMap<Integer, Boolean>();
	LinkedListNode node = list;
	while(node.next != null){
		int key = node.val;
		if(!table.containsKey(key)){
			table.put(key, false);
		}
		else{
			table.get(key) = true;
		}
		node = node.next;
	}
	int key = list.val;
	while(table.get(key) == true){
		list = list.next;
		key = list.val;
	}
	node = list;
	while(node.next != null){
		LinkedListNode node2 = node.next;
		key = node2.val;
		if(table.get(key) == true){
			node.next = node2.next;
		}
		node = node.next;
	}
	return list;
}

// no addtional memory allowed
public static void removeDups(LinkedListNode list){
	while(list!=null){
		int val1 = list.val;
		LinkedListNode list2 = list.next;
		LinkedListNode previous = list;
		while(list2!=null){
			int val2 = list2.val;
			if(val1==val2){
				previous.next = list2.next;
			}
			else{
				previous = list2;
			}
			list2 = list2.next;
		}
		list = list.next;
	}
}

2.2
public static LinkedListNode findnth(LinkedListNode node, int n){
	LinkedListNode p1 = node, p2 = node;
	for(int i=0; i<n-1; i++){
		if(p2 == null) return null;
		p2 = p2.next;
	}
	while(p2!=null){
		p1 = p1.next;
		p2 = p2.next;
	}
	return p1;
}

2.3
public static LinkedListNode deleteMidNode(LinkedListNode node){
	LinkedList runner = node;
	if(runner == null) return null;
	if(runner.next == null) {
		return null;
	}
	int length = 0;
	while(runner!=null){
		length++;
		runner = runner.next;
	}
	length = length/2;
	LinkedListNode previous = node;
	for(int i=0; i<length; i++){
		previous = previous.next;
	}
	previous.next = previous.next.next;
	return node;
}

2.4
public static LinkedListNode add(LinkedListNode node1, LinkedListNode node2, int carrier){
	int length1 = 0, length2 = 0;
	LinkedListNode head1 = node1, head2 = node2;
	while(head1 != null){
		length1++;
		head1 = head1.next;
	}
	while(head2!=null){
		length2++;
		head2 = head2.next;
	}
	if(length1 > length2){
		int lengthdiff = length1 - length2;
		while(lengthdiff>0){
			LinkedListNode node = new LinkedListNode(0);
			node.next = node2;
			node2 = node;
		}
	}
	if(length2 > length1){
		int lengthdiff = length2 - length1;
		while(lengthdiff>0){
			LinkedListNode node = new LinkedListNode(0);
			node.next = node1;
			node1 = node;
		}
	}
	LinkedListNode nunode = addequal(node1, node2);
	return Normalize(nunode);
}

public static LinkedListNode addequal(LinkedListNode node1, LinkedListNode node2){
	LinkedListNode nunode = null;
	if(node1.next!=null){
		nunode = new LinkedListNode(node1.val+node2.val);
		nunode.next = addequal(node1.next, node2.next)
	}
	else{
		nunode = new LinkedListNode(node1.val+node2.val);
	}
	return nunode
}

public static LinkedListNode Normalize(LinkedListNode node){
	int carrier = normal(node);
	while(carrier!=0){
		int remainder = carrier%10;
		LinkedListNode nunode = new LinkedListNode(remainder);
		nunode.next = node;
		node = nunode;
		carrier = carrier/10;
	}
	return node;
}

public static int normal(LinkedListNode node){
	if(node.next!=null){
		node.val += normal(node.next);
	}
	int carrier = node.val/10;
	int remainder = node.val%10;
	node.val = remainder;
	return carrier;
}

2.5
public static LinkedListNode findBeginning(LinkedListedNode head){
	LinkedListNode p1 = head, p2 = head;
	if(head == null) return null;
	while(p2.next!=null){
		p1 = p1.next;
		p2 = p2.next.next;
		if(p1==p2)
			break;
		if(p2 == null)
			return null;
	}
	if(p1!=p2) return null;
	p1 = head;
	while(p1!=p2){
		p1 = p1.next;
		p2 = p2.next;
	}
	return p2;
}
