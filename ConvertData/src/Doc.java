

public class Doc {

	int doc_id;
	double[] features;
	
	public Doc(String[] elems){
		// no new features
		//this.features = new double[elems.length-5];
		// add 2 features
		this.features = new double[ConvertData.feature_num];
		double sum = 0;
		for(int i=2; i<elems.length-3; i++){
			String[] pair = elems[i].trim().split("\\:");
			features[Integer.parseInt(pair[0])-1] = Double.parseDouble(pair[1]);
			sum += features[Integer.parseInt(pair[0])-1]*features[Integer.parseInt(pair[0])-1];
		}
		// sum of tf
		//features[44] = features[27]+features[28]+features[29]+features[30];
		//sum += features[44]*features[44];
		// 
		//features[45] = features[27]/features[1];
		//sum += features[45]*features[45];
		sum = Math.sqrt(sum/features.length);
		for(int i=0; i<features.length; i++){
			features[i] /= sum;
		}
		this.doc_id = Integer.parseInt(elems[elems.length-1]);
		//print();
	}
	
	public void Normal(){
		double sum = 0;
		for(int i=0; i<this.features.length; i++){
			sum += features[i];
		}
		for(int i=0; i<this.features.length; i++){
			features[i] /= sum;
		}
	}
	
	public void print(){
		System.out.println("Doc id: "+this.doc_id+"\tFeatures: "+features.length);
		for(int i=0; i<features.length; i++){
			System.out.print(i+":"+features[i]+" ");
		}
		System.out.println();
	}
}
