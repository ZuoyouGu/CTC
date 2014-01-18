import java.util.ArrayList;
import java.util.List;


public class Query {
	public List<Doc> positive_docs = new ArrayList<Doc>();
	public List<Doc> negative_docs = new ArrayList<Doc>();
	
	public void add(Doc doc, int relevant){
		if(relevant==1){
			this.add_p(doc);
		}
		else{
			this.add_n(doc);
		}
	}
	
	private void add_p(Doc doc){
		this.positive_docs.add(doc);
	}	
	
	private void add_n(Doc doc){
		this.negative_docs.add(doc);
	}
	
	public void print(){
		print_p();
		print_n();
	}
	
	private void print_p(){
		System.out.println("------------ Positive docs: "+this.positive_docs.size());
		for(int i=0; i<this.positive_docs.size(); i++){
			this.positive_docs.get(i).print();
		}
		//System.out.println("------------------------");
	}
	
	private void print_n(){
		System.out.println("------------ Negative docs: "+this.negative_docs.size());
		for(int i=0; i<this.negative_docs.size(); i++){
			this.negative_docs.get(i).print();
		}
		//System.out.println("------------------------");
	}
}
