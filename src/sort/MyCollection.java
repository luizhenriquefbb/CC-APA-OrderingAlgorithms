package sort;

import exceptions.NegativeNumberException;
import fileManager.FileManager;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author lhfba
 */
public class MyCollection {

    private List<Integer> myCollection;

 
    /**
     * Read from a file txt. <br>
     * File format: One number per line
     *
     * @param filePath
     */
    public MyCollection(String filePath) {
        this.myCollection = new ArrayList<>();
        //read file
        String txt = FileManager.readFromFile(filePath);

        //pasing
        String[] numbers = txt.split("\n");

        // adding to list
        for (String number : numbers) {
            this.myCollection.add(Integer.parseInt(number));
        }
    }

    /**
     * Main method: the object of study
     */
    public void selectionSort() {
        int position = 0;
        int posSmaller = 0;
        int aux = position + 1;
        int swapAux;

        // go through the entire array
        for (position = 0; position < this.myCollection.size() - 1; position++) {
            //seek smaller number
            for (aux = position + 1; aux < this.myCollection.size(); aux++) {
                // compare
                if (this.myCollection.get(aux) < this.myCollection.get(posSmaller)) {
                    // find smaller int
                    posSmaller = (aux);
                }
            }
            // swap
            if (this.myCollection.get(posSmaller) < this.myCollection.get(position)) {
                swapAux = this.myCollection.get(position);
                this.myCollection.set(position, this.myCollection.get(posSmaller));
                this.myCollection.set(posSmaller, swapAux);
            }

        }
    }

    /**
     * Main method: the object of study
     */
    public void insertionSort() {
        int posUnSorted;
        int posSorted = 0;
        int number;

        // go through the entire array
        // obs: posUnsirted starts at 'posSorted + 1' because the list is divided in sorted and unsorted
        for (posUnSorted = posSorted + 1; posUnSorted < this.myCollection.size(); posUnSorted++) {
            number = this.myCollection.get(posUnSorted);

            //shitf
            for (posSorted = posUnSorted - 1;
                    posSorted >= 0 && number < this.myCollection.get(posSorted);
                    posSorted--) {
                this.myCollection.set(posSorted+1, this.myCollection.get(posSorted));
            }

            this.myCollection.set(posSorted + 1, number);
        }

    }
    
    /**
     * Main method: the object of study
     * <p>
     * datapted from: <a href="http://www.java2novice.com/java-sorting-algorithms/merge-sort/">
     * this site</a>
     * </p>
     */
    public void mergeSort(){
        this.splitAndMerge(0, this.myCollection.size()-1);
    }
    
    /**
     * recursive method
     * @param beginList beginning of the list
     * @param endList end of the list
     */
    private void splitAndMerge(int beginList, int endList){
        int middle = (beginList+(endList))/2;
        int[] temp = new int[this.myCollection.size()];
        // stop condition (array size == 1)
        if(beginList < endList){
            
            //left side
            splitAndMerge(beginList, middle);
            //right side
            splitAndMerge(middle+1, endList);
           //merging
            merge(beginList, middle, endList, temp);
            
        }
    }
    
    /**
     * Merge two arrays (in this case, two lists) based on their indexes
     * @param beginList
     * @param middle
     * @param endList 
     * @param tempArray temporary auxiliar array
     */
    private void merge(int beginList,int middle,int endList, int[] tempArray){
        int indexLeft = beginList;
        int indexRight = middle+1;
        
        int iTemp = beginList; // position on 'tempArray'
        
        int numElements = endList - beginList + 1;

        
        while(indexLeft <= middle && indexRight <= endList ){
            // compare values of left and right
            if(this.myCollection.get(indexLeft) <= this.myCollection.get(indexRight)){
                // put left value 
                tempArray[iTemp] = this.myCollection.get(indexLeft);
                indexLeft++;
            }else{
                // put right value
                tempArray[iTemp] = this.myCollection.get(indexRight);
                indexRight++;
            }
            iTemp++;
        }
        
        // remains values
        while(indexLeft <= middle){
            tempArray[iTemp] = this.myCollection.get(indexLeft);
            indexLeft++;
            iTemp++;
        }
        
        while(indexRight <= endList){
            tempArray[iTemp] = this.myCollection.get(indexRight);
            indexRight++;
            iTemp++; 
        }
        
        // coppying tempArray to list
         for( int i = 0; i < numElements; i++, endList-- ){
            this.myCollection.set(endList, tempArray[ endList ]);
        }

        
    }

    /**
     * Main method: the object of study
     */
    public void quickSort(){
        quick(0, this.myCollection.size()-1);
    }
    
    private void quick(int beginList, int endList){
        if (beginList < endList) {
            int pivot;
            pivot = this.splitAndQuick(beginList, endList);
            quick(beginList, pivot - 1);
            quick(pivot + 1, endList);
        }
    }
    
    /**
     * @param beginList
     * @param endList
     * @return 
     */
    private int splitAndQuick(int beginList, int endList) {
        /** by convention, pivot is the first element */
        int pivot = this.myCollection.get(beginList);
        int b = beginList + 1, e = endList;
        // until the indexes cross
        while (b <= e) {
            // left side must be less than the pivot
            if (this.myCollection.get(b) <= pivot) {
                b++;
                
            // right side must be greater than the pivot
            } else if (pivot < this.myCollection.get(e)) {
                e--;
            //swap elements of the indexes
            } else {
                int swap = this.myCollection.get(b);
                this.myCollection.set(b, this.myCollection.get(e));
                this.myCollection.set(e, swap);
                b++;
                e--;
            }
        }
        //??
        this.myCollection.set(beginList, this.myCollection.get(e));
        this.myCollection.set(e, pivot);
        return e;
    }
     
    /**
     * Main method: the object of study
     */
    public void heapSort(){
        MaxHeap maxHeap = new MaxHeap(myCollection);
        this.myCollection = maxHeap.heapSort();
    }
    
    /**
     * Adapted from https://www.geeksforgeeks.org/counting-sort/
     */
    public void countingSort(){
       
        // array to be sorted in, this array is necessary
        // when we sort object datatypes, if we don't, 
        // we can sort directly into the input array     
        int[] aux = new int[myCollection.size()];

        // find the smallest and the largest value
        int min = myCollection.get(0);
        int max = myCollection.get(0);
        for (int i = 1; i < myCollection.size(); i++) {
            if (myCollection.get(i) < min) {
                min = myCollection.get(i);
            } else if (myCollection.get(i) > max) {
                max = myCollection.get(i);
            }
        }

        // init array of frequencies
        int[] counts = new int[max - min + 1];

        // init the frequencies
        for (int i = 0; i < myCollection.size(); i++) {
            counts[myCollection.get(i) - min]++;
        }

        // recalculate the array - create the array of occurences
        counts[0]--;
        for (int i = 1; i < counts.length; i++) {
            counts[i] = counts[i] + counts[i - 1];
        }

        /*
            Sort the array right to the left
            1) Look up in the array of occurences the last occurence of the given value
            2) Place it into the sorted array
            3) Decrement the index of the last occurence of the given value
            4) Continue with the previous value of the input array (goto set1), 
               terminate if all values were already sorted
         */
        for (int i = myCollection.size() - 1; i >= 0; i--) {
            aux[counts[myCollection.get(i) - min]--] = myCollection.get(i);
        }

        // update myCollection
        myCollection.clear();
        for (int i : aux){
            myCollection.add(i);
        }
        
    
    }
    
    /**
     * adpted from https://gist.github.com/yeison/5606963
     * adpted from https://www.geeksforgeeks.org/radix-sort/
     */
    public void radixSort() throws NegativeNumberException{
        // put lis into array
        Integer[] arr = new Integer[myCollection.size()];
        for (int i = 0; i<myCollection.size();i++){
            arr[i] = myCollection.get(i);
        }
        
        // Find the maximum number to know number of digits
        int m = getMax();
 
        // Do counting sort for every digit. Note that instead
        // of passing digit number, exp is passed. exp is 10^i
        // where i is current digit number
        for (int exp = 1; m/exp > 0; exp *= 10)
            auxCountingSort(arr, exp);
        
        // put array into list
        for (int i = 0; i<myCollection.size();i++){
            myCollection.set(i, arr[i]);
        }
    }
    
    private void auxCountingSort(Integer[] arr, int exp) throws NegativeNumberException{
        int n = arr.length;
        int output[] = new int[n]; // output array
        int i;
        int count[] = new int[10];
        
        

        // Store count of occurrences in count[]
        for (i = 0; i < n; i++) {
            try{
                count[(arr[i] / exp) % 10]++;
            }catch (ArrayIndexOutOfBoundsException ex){
                throw new NegativeNumberException(ex.getMessage());
            }
        }

        // Change count[i] so that count[i] now contains
        // actual position of this digit in output[]
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Build the output array
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copy the output array to arr[], so that arr[] now
        // contains sorted numbers according to curent digit
        for (i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
    
    /**
     * A utility function to get maximum value in arr[]
     * @return int MAximun value of array
     */
    private int getMax()
    {
        int mx = myCollection.size();
        for (Integer i : myCollection)
            if (i > mx)
                mx = i;
        return mx;
    }
    
    
    
    @Override
    public String toString() {
        return "MyCollection{\n"
                + "\t" + myCollection
                + "\n}";
    }

}
