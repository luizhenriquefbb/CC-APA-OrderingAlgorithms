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
     * initialize list with random values
     */
    public MyCollection() {
        this.myCollection = new ArrayList<>();
        this.myCollection.add(2);
        this.myCollection.add(5);
        this.myCollection.add(10);
        this.myCollection.add(237);
        this.myCollection.add(53);
        this.myCollection.add(0);
        this.myCollection.add(7);
    }

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

    @Override
    public String toString() {
        return "MyCollection{\n"
                + "\t" + myCollection
                + "\n}";
    }

}
