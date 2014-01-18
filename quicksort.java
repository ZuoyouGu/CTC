public Class Sort{
	private int[] arr;
	
	public Sort(int[] a){
		arr = a;
	}
	
	public void quicksort(int start, int end){
		if(start>=end) return;
		int s = start, e = end;
		int pivot = (start+end)/2;
		while(start < end){
			while(start<pivot && arr[start]<=arr[pivot]){
				start++;
			}
			while(end>pivot && arr[pvot]<arr[end]){
				end--;
			}
			if(start<pivot){
				if(end>pivot){// exchange this two numbers
					int tmp = arr[end];
					arr[end] = arr[start];
					arr[start] = arr[end];
					start++;
					end--;
				}
				else{// exchange the pivot with start
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
	
	public static void main(String[] args){
		int[] arr = {14, 8, 9, 11, 5, 13, 7, 4, 2, 10, 15, 1, 6, 12, 3};
		Sort sort = new Sort(arr);
		//sort.sort_iterative();
		sort.sort_recursive();
		sort.print();
	}
}