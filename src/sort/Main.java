package sort;
/**
 *
 * @author lhfba
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        String fileToRead = "couting.txt";
        //String fileToRead = "test.txt";
        
        MyCollection collection1 = new MyCollection(fileToRead);
        MyCollection collection2 = new MyCollection(fileToRead);
        MyCollection collection3 = new MyCollection(fileToRead);
        MyCollection collection4 = new MyCollection(fileToRead);
        MaxHeap maxHeap = new MaxHeap(fileToRead);
        MyCollection collection5 = new MyCollection(fileToRead);
        
        System.out.println("Unsorted List");
        System.out.println(collection1);
        
        System.out.println("================================================\n"
                + "part 1");
        
        System.out.println("\nSorted by selectionSort");
        collection1.selectionSort();
        System.out.println(collection1);
        
        System.out.println("\nSorted by insertionSort");
        collection2.insertionSort();
        System.out.println(collection2);
        
        System.out.println("================================================\n"
                + "part 2");
        
        System.out.println("\nSorted by mergeSort");
        collection3.mergeSort();
        System.out.println(collection3);
        
        System.out.println("\nSorted by quickSort");
        collection4.quickSort();
        System.out.println(collection4);
        
        System.out.println("=================================================\n"
                + "Part 3");
        System.out.println("\nHeapifing array");
        maxHeap.heapify();
        System.out.println(maxHeap);
        
        
        System.out.println("\nsorted bt heapSort");
        collection5.heapSort();
        System.out.println(collection5);
        
    }
    
}
