package sort;

import java.util.ArrayList;
import java.util.List;

/**
 * a binary tree represented in an array 
 * @author lhfba
 */
public class MaxHeap {
    
    private List<Integer> array;
    private int heapSize;

    public MaxHeap(String fileToRead) {
        
        // build arrayList
        array = new ArrayList<>();
        String readFromFile = fileManager.FileManager.readFromFile(fileToRead);
        String[] split = readFromFile.split("\n");
        for(String number : split){
            array.add(Integer.parseInt(number));
        }
        
        this.heapSize = this.array.size()-1;
        
    }

    public MaxHeap(List<Integer> array) {
        this.array = array;
        this.heapSize = this.array.size()-1;
    }
    
    
    
    /**
     * @param i
     * @return Return the index of the father
     */
    private int parent(int i) {
        return (i - 1) / 2;
    }
    
    /**
     * 
     * @param i
     * @return return the index of the right son
     */
    private int right(int i) {
        return (i * 2 + 1) + 1;
    }
    
    /**
     * 
     * @param i
     * @return return the index of the left son
     */
    private int left(int i) {
        return (i * 2 + 1);
    }
    
    public void heapify(){
        
        for (int i = this.getHeapSize()/2 ; i >=0 ; i--){
            this.heapify(i, this.getHeapSize());
        }
    }
   
    private void heapify(int currentIndex, int n){
        int leftSonIndex = this.left(currentIndex);
        int rightSonIndex = this.right(currentIndex);
        int grt = currentIndex;
        
        int auxValue;
        
        if (leftSonIndex <= n && this.array.get(leftSonIndex)>(this.array.get(currentIndex))) {
            grt = leftSonIndex;
        }
        if (rightSonIndex <= n && this.array.get(rightSonIndex)>(this.array.get(grt))) {
            grt = rightSonIndex;
        }
        if (grt != currentIndex) {
            //swap
            auxValue = this.array.get(currentIndex);
            this.array.set(currentIndex, this.array.get(grt));
            this.array.set(grt, auxValue);
            
            heapify(grt, n);
        }
        
        
    }
    
    
    private void heapify_old(int currentIndex){
        
        int leftSonIndex;
        int rightSonIndex;
        
        // swap variable
        int auxValue;
        int greaterSonIndex;

        
        // go through entire array
        for (int index = currentIndex; index<this.getHeapSize()/2; index++){
        //for (int index = currentIndex; index >= 0; index--){
        
            leftSonIndex = this.left(index);
            rightSonIndex = this.right(index);
            
            // search greater son
            if(indexIsValid(leftSonIndex) && indexIsValid(rightSonIndex)){
                if (this.array.get(leftSonIndex) >= this.array.get(rightSonIndex)){
                    greaterSonIndex = (leftSonIndex);
                }else{
                    greaterSonIndex = (rightSonIndex);
                }
                
            } else if(!indexIsValid(rightSonIndex)){
                greaterSonIndex = (leftSonIndex);
            }else{
                greaterSonIndex = (rightSonIndex);
            }
            
            //swap current and greater
            if (this.array.get(index) < this.array.get(greaterSonIndex)){
                auxValue = this.array.get(index);
                this.array.set(index, this.array.get(greaterSonIndex));
                this.array.set(greaterSonIndex, auxValue);
                
                // go recursivamente
                this.heapify();
                
            }
        }
    }
    
    private boolean indexIsValid(int index){
        return (index < this.array.size() && index >= 0);
    }
    
    protected List heapSort(){
        this.heapify();
        //swap root with last element then, subtract heapSize
        while (this.getHeapSize() > 0) {
            int auxValue = this.array.get(this.getHeapSize());
            this.array.set(this.getHeapSize(), this.array.get(0));
            this.array.set(0, auxValue);
            
            this.subtractHeapSize();
            
            this.heapify();
        }
        
        return this.array;
    }
    
    @Override
    public String toString() {
        return "Max heap{\n"
                + "\t" + array
                + "\n}";
    }

    public int getHeapSize() {
        return heapSize;
    }
    
    public void subtractHeapSize(){
        this.heapSize--;
    }

 
    
    
}
