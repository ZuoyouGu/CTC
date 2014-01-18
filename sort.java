public class Sort{
	private int[] arr;
	private int[] helper;
	
	public Sort(int[] a){
		arr = a;
		helper = new int[a.length];
	}
	
	public void sort_recursive(){
		mergesort_recursive(0, arr.length-1);
	}
		
	private void mergesort_recursive(int start, int end){
		if(start >= end) return;
		if(end-start<=1){
			if(arr[start]>arr[end]){
				int tmp = arr[start];
				arr[start] = arr[end];
				arr[end] = tmp;
			}
		}
		else{
			int mid = (start+end)/2;
			mergesort_recursive(start, mid);
			mergesort_recursive(mid+1, end);
			merge_recursive(start, end);
		}
	}
	
	private void merge_recursive(int start, int end){
		int k;
		for(k=start; k<=end; k++){
			helper[k] = arr[k];
		}
		int mid = (start+end)/2;
		int i=start, j=mid+1;
		k = start;
		while(i<=mid && j<=end){
			if(helper[i]<=helper[j]){
				arr[k] = helper[i];
				i++;
			}
			else{
				arr[k] = helper[j];
				j++;
			}
			k++;
		}
		while(i<=mid){
			arr[k++] = helper[i++];
		}
	}	
	
	public void sort_iterative(){
		mergesort_iterative();
	}
	
	public void mergesort_iterative(){
		int step = 1;
		int length = arr.length;
		while(step < length){
			int i = 0;
			while(i<length){
				merge_iterative(i, step);
				i += step*2;
			}
			step <<= 1;
		}
	}
	
	private void merge_iterative(int start, int step){
		int s1 = start, s2 = start+step;
		int e1 = s1+step-1, e2 = s2+step-1;
		if(e1 >= arr.length) return;
		if(e2 >= arr.length) e2 = arr.length-1;
		int k;
		for(k = s1; k<=e2; k++){
			helper[k] = arr[k];
		}
		k = s1;
		while(s1<=e1 && s2<=e2){
			if(helper[s1] > helper[s2]){
				arr[k] = helper[s2];
				s2++;
			}
			else{
				arr[k] = helper[s1];
				s1++;
			}
			k++;
		}
		while(s1<=e1){
			arr[k++] = helper[s1++];
		}
	}
	
	public void quicksort(int start, int end){
		if(start>=end) return;
		int s = start, e = end;
		int pivot = (start+end)/2;
		while(start < end){
			while(start<pivot && arr[start]<=arr[pivot]){
				start++;
			}
			while(end>pivot && arr[pivot]<arr[end]){
				end--;
			}
			if(start<pivot){
				if(end>pivot){// exchange this two numbers
					int tmp = arr[end];
					arr[end] = arr[start];
					arr[start] = tmp;
					start++;
					end--;
				}
				else{
					int tmp = arr[start];
					arr[start] = arr[pivot];
					arr[pivot] = tmp;
					end--;
					pivot = start;
				}
			}
			else{// start>=pivot
				if(end>pivot){
					int tmp = arr[end];
					arr[end] = arr[pivot];
					arr[pivot] = tmp;
					start++;
					pivot = end;
				}
			}
		}
		quicksort(s, pivot-1);
		quicksort(pivot+1, e);
	}
	
	public void print(){
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void main(String[] args){
		int[] arr = {13, 8, 9, 11, 13, 13, 7, 15, 2, 10, 15, 1, 13, 12, 13};
		Sort sort = new Sort(arr);
		//sort.sort_iterative();
		//sort.sort_recursive();
		sort.quicksort(0, arr.length-1);
		sort.print();
		System.out.println();
		for(Integer i: arr){
			System.out.print(i+" ");
		}
	}
}