/** @brief Sorting algorithms
 *  quicksort, mergesort (both recursive and iterative version)
 *
 *  Simple sorting algorithms selection sorting, bubble sorting
 *  and insertion sorting (not implemented yet)
 *  
 *  @author Zuoyou Gu
 */

public class Sort{
	private int[] arr;
	private int[] helper;
	
	public Sort(int[] a){
		arr = a;
		helper = new int[a.length];
	}
	
	/** --------------------------------------------------------
	 *	@brief Recursive merge sort
	 *  Extra space needed. Merge can writen seperatedly
	 */
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
	
	/** @brief Merge two sub arrays
	 *  Use different indexes for each array. If there are extra numbers
	 *  are still not visted in the first array, remember to move them to
	 *  the end of the updated array.
	 */
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
	
	/** ---------------------------------------------------------
	 *  @brief Iterative merge sort
	 *  Extra space needed. Merge can writen seperatedly
	 *  The merge function above can be used here.
	 */	
	public void sort_iterative(){
		mergesort_iterative();
	}
	
	/* step is the length of each sub array, starting from 1,
		multipled by 2 every time.
	 */
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
	
	/** --------------------------------------------------------------------
		@brief Quick sort
		There is not extra space needed here (in-space)
		
		Select a pivot every time, and move smaller or same equal numbers to
		left of pivot and larger number to right. Use two walkers every time.
		One walks from start, one from end, when they meet each other, this 
		recursion is over. 1st walker stops when it meets a number larger than
		pivot or it's in the pivot's place, while the 2nd walker stops when
		it meets a number smaller than pivot. Then exchange these two walkers
		*/
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
	
	/** @brief Bubble sort
		Compare every a pair of consecutive numbers, exchange them if 1st > 2nd
	*/
	public void bubblesort(){
		while(true){
			int exchanged_num = 0;
			for(int i=1; i<arr.length; i++){
				if(arr[i-1]>arr[i]){
					int tmp = arr[i-1];
					arr[i-1] = arr[i];
					arr[i] = tmp;
					exchanged_num++;
				}
			}
			if(exchanged_num==0)
				break;
		}
	}
	
	public void selectionsort(){
		int length = arr.length;
		for(int i=0; i<length-1; i++){
			int min = arr[i];
			int idx = i;
			for(int j=i+1; j<length; j++){
				if(min>arr[j]){
					min = arr[j];
					idx = j;
				}
			}
			if(idx != i){
				swap(idx, i);
			}
		}
	}
	
	public void insertionsort(){
		for(int i=1; i<arr.length; i++){
			// hold the newest number, as it's gonna be compared to all
			// previous numbers
			int tmp = arr[i];
			int j;
			for(j=i-1; j>=0; j--){
				if(tmp<arr[j]){
					arr[j+1] = arr[j];
				}
				else{
					break;
				}
			}
			arr[j+1] = tmp;
		}
	}
	
	public void swap(int i, int j){
		int tmp = arr[i];
		arr[i] = arr[j];
		arr[j] = tmp;
	}
	
	public void print(){
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}
	}
	
	public static void main(String[] args){
		int[] arr = {5, 8, 9, 11, 3, 4, 7, 14, 2, 10, 15, 1, 13, 12, 6};
		Sort sort = new Sort(arr);
		//sort.sort_iterative();
		//sort.sort_recursive();
		//sort.quicksort(0, arr.length-1);
		//sort.bubblesort();
		//sort.selectionsort();
		sort.insertionsort();
		sort.print();
		/*System.out.println();
		for(Integer i: arr){
			System.out.print(i+" ");
		}*/
	}
}