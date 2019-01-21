package FenwickTree;

/**
* @author Vikram Gopali
* */

public class FenwickTree{

    private int[] arr;              //fenwick tree / Binary Index Tree structure
    private int[] originalArr;      //Copy of the original array for internal processing

    /**
    * This is a public constructor for creating a Fenwick tree with an array
    * @param arr integer array whose fenwick tree has to be constructed
     * @see #construct(int[])
    * */
    public FenwickTree(int[] arr){
        this.arr = new int[arr.length+1];
        this.construct(arr);
    }

    /**
     * This is a public constructor for initializing the fenwick tree when the elements are unknown.
     * It can be used to later create the fenwick tree using insert operations.
     * @param size size of the fenwick tree to be initialized
     * @see #insert(int, int)
     * */
    public FenwickTree(int size){
        this.arr = new int[size+1];
    }


    /**
     * This method is used to construct the fenwick tree with existing array.
     * @param arr integer array whose fenwick tree has to be constructed
     * */
    public boolean construct(int[] arr){
        this.originalArr = arr;
        for(int i=0;i<arr.length;i++){
            this.insert(arr[i],i+1);
        }
        return true;
    }

    /**
     * This method is used to construct the fenwick tree with existing array.
     * @param element the element to be inserted at the perticular index
     * @param index the index at which the element is to be inserted
     * @return  return true for each element added
     * */
    private boolean insert(int element, int index){
        while(index < this.arr.length){
            this.arr[index] += element;
            index += Integer.lowestOneBit(index);
        }
        return true;
    }

    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append("[");
        for(int i=0;i<this.arr.length;i++){
            str.append(this.arr[i]+",");
        }
        str.deleteCharAt(str.length()-1);
        str.append("]");
        return str.toString();
    }

    /**
     * This method is used to update the elements fenwick tree.
     * @param element the new element to be updated at the particular index
     * @param index the index at which the new element is to be updated
     * @return  return true for each element added
     * */
    public boolean update(int element, int index){
        int delta = element - this.originalArr[index];
        this.updateHelper(index+1,delta);
        return true;
    }

    /**
     * This method is the helper function to update the elements fenwick tree.
     * @param index the index at which the new element is to be updated
     * @param delta the change in the previous element to the new element that needs to be updated
     * @return  return true for each element added
     * */
    private boolean updateHelper(int index,int delta){
        while(index < this.arr.length){
            this.arr[index] += delta;
            index += Integer.lowestOneBit(index);
        }
        return true;
    }

    /**
     * This method is used to find the sum of elements from index 0 to a particular index.
     * @param ind the index upto which the sum is to be calculated.
     * @return  return the sum of elements from 0 to particular index
     * */
    public int sum(int ind){
        int sum = 0;
        int index = ind+1;
        while(index > 0){
            sum += this.arr[index];
            index -= Integer.lowestOneBit(index);
        }
        return sum;
    }

    /**
     * This method is used to find the sum of elements in a range of indices.
     * @param ind1 the start index in the range the sum is to be calculated.
     * @param ind2 the end index in the range the sum is to be calculated.
     * @return  return the sum of elements from range ind1 to ind2\
     * */
    public int rangeSum(int ind1, int ind2){
        return this.sum(ind2) - this.sum(ind1-1);
    }

    /**
     * This method returns the size of the fenwick tree.
     * @return return the size of the fenwick tree
     * */
    public int size(){
        return this.arr.length;
    }


    public static void main(String[] args){
        FenwickTree ft = new FenwickTree(11);
        ft.construct(new int[]{3,2,-1,6,5,4,-3,3,7,2,3});
        System.out.println(ft);
        /*
        * Output:
        * [0,3,5,-1,10,5,9,-3,19,7,9,3]
        * */


        System.out.println(ft.sum(6));
        /*
         * Output:
         * 16
         * */


        ft.update(9,3);
        System.out.println(ft);
        /*
         * Output:
         * [0,3,5,-1,13,5,9,-3,22,7,9,3]
         * */


        System.out.println(ft.rangeSum(3,6));
        /*
         * Output:
         * 15
         * */

    }

}
