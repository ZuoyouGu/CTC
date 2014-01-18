import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class ConvertData {
	
	public static int feature_num = 44;
	public static List<double[]> V = new ArrayList<double[]>();
	
	public static void main(String args[]) throws IOException{
	    Map<String, String> params = new HashMap<String, String>();
	    Scanner scan = new Scanner(new File(args[0]));
	    String line = null;
	    do {
	      line = scan.nextLine();
	      String[] pair = line.split("=");
	      params.put(pair[0].trim(), pair[1].trim());
	    } while (scan.hasNext());
	    
	    // parameters required for this example to run
	    if (!params.containsKey("train")) {
	      System.err.println("Error: Parameters were missing.");
	      System.exit(1);
	    }
	    if (!params.containsKey("test")) {
		      System.err.println("Error: Parameters were missing.");
		      System.exit(1);
		}
	    if (!params.containsKey("c")) {
		      System.err.println("Error: Parameters were missing.");
		      System.exit(1);
		}
		String trainFile = params.get("train");
		String testFile = params.get("test");
		convertTrainfile(trainFile);
		convertTestfile(testFile);
	}
	
	private static int CategoryIDEnd(String line){
		int i=0;
		for(; i<line.length(); i++){
			if(line.charAt(i)==' ') break;
		}
		for(; i<line.length(); i++){
			if(line.charAt(i)==' ') break;
		}
		return i;
	}	
	
	private static void convertTrainfile(String trainFile) throws IOException{
		Scanner scan = new Scanner(new File(trainFile));
		// get all data from file into queries
		HashMap<Integer, Query> queries = new HashMap<Integer, Query>();
		do{
			String line = scan.nextLine();
	  	    String[] elems = line.split(" ");
	  	    int relevant = Integer.parseInt(elems[0].trim());
	  	    String[] pair = elems[1].trim().split("\\:");
	  	    int qid = Integer.parseInt(pair[1]);
	  	    Doc doc = new Doc(elems);
	  	    // if the query doesn't exist, then create a new one
	  	    if(!queries.containsKey(qid)){
	  	    	queries.put(qid, new Query());
	  	    }
	  	    queries.get(qid).add(doc, relevant);
		}while(scan.hasNext());
		getV(queries);
		writeV();
	}
	
	public static void getV(HashMap<Integer, Query> queries){
		Iterator<Integer> iter = queries.keySet().iterator();
		while(iter.hasNext()){
			Integer qid = iter.next();
			Query query = queries.get(qid);
			for(int p_cnt=0; p_cnt<query.positive_docs.size(); p_cnt++){
				Doc p_doc = query.positive_docs.get(p_cnt);
				for(int n_cnt=0; n_cnt<query.negative_docs.size(); n_cnt++){
					Doc n_doc = query.negative_docs.get(n_cnt);
					double[] v_np = new double[n_doc.features.length];
					//double sum = 0;
					for(int i=0; i<n_doc.features.length; i++){
						v_np[i] = p_doc.features[i] - n_doc.features[i];
						//sum += v_np[i] * v_np[i];
					}
					/*
					sum = Math.sqrt(sum);
					for(int i=0; i<n_doc.features.length; i++){
						v_np[i] = v_np[i]/sum;
					}*/
					V.add(v_np);
				}
			}
		}
	}
	
	private static void writeV() throws IOException{
		File file = new File("train.txt");
	    // if file doesn't exists, then create it
		if (file.exists()) {
			file.delete();
		}
		if (!file.exists()) {
			file.createNewFile();
		}		
		FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
		BufferedWriter bw = new BufferedWriter(fw);
		for(int i=0; i<V.size(); i++){
			double[] features = V.get(i);
			bw.write("1 ");
			for(int j=0; j<features.length; j++){
				bw.write((j+1)+":"+features[j]+" ");
			}
			bw.write("\n");
		}
		bw.close();
	}
	
	private static void convertTestfile(String testFile) throws IOException{
			Scanner scan = new Scanner(new File(testFile));
			File file = new File("test.txt");
		    // if file doesn't exists, then create it
			if (file.exists()) {
				file.delete();
			}
			if (!file.exists()) {
				file.createNewFile();
			}		
			FileWriter fw = new FileWriter(file.getAbsoluteFile(), true);
			BufferedWriter bw = new BufferedWriter(fw);
			do{
				String line = scan.nextLine();
		  	    String[] elems = line.split(" ");
		  	    int relevant = Integer.parseInt(elems[0].trim());
		  	    Doc doc = new Doc(elems);
		  	    bw.write("0 ");
		  	    double[] features = doc.features;
		  	    for(int i=0; i<features.length; i++){
		  	    	bw.write((i+1)+":"+features[i]+" ");
		  	    }
		  	    bw.write("\n");
			}while(scan.hasNext());
			bw.close();
	}
}
