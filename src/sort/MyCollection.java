package sort;

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
        
    @Override
    public String toString() {
        return "MyCollection{\n"
                + "\t" + myCollection
                + "\n}";
    }

}
