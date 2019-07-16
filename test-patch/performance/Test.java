import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.lang.System;

public class Test {

    static public void main(String[] args) {
        String input = args.length >= 1 ? args[0] : "data1000.txt";
        input = "input/" + input;
        String sortType = args.length >= 2 ? args[1] : "QuickSort";
        try {
            int numCount;
            BufferedReader reader = new BufferedReader(new FileReader(input));
            numCount = Integer.parseInt(reader.readLine());
            int[] array = new int[numCount];
            for (int idx = 0; idx < array.length; ++idx) {
                array[idx] = Integer.parseInt(reader.readLine());
            }
            reader.close();
            Sorter sorter = createSorter(sortType);
            System.out.print("Sorting with ");
            System.out.println(sorter.getType());
            /* warm up */
            for (int t = 0; t < 5; ++t) {
                if (!runOnce(array.clone(), sorter)) {
                    return;
                }
            }
            /* run */
            long startTime = System.currentTimeMillis();
            int passCount = 10;
            for (int t = 0; t < passCount; ++t) {
                if (!runOnce(array.clone(), sorter)) {
                    return;
                }
            }
            long endTime = System.currentTimeMillis();
            System.out.println("run " + passCount + " times, avg time = " + (endTime - startTime) / passCount);
        } catch (NumberFormatException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    static public boolean runOnce(int[] array, Sorter sorter) {
            sort(sorter, array, null);
            for (int idx = 1; idx < array.length; ++idx) {
                if (array[idx - 1] > array[idx]) {
                    System.out.println("fail");
                    return false;
                }
                // System.out.print(array[idx]);
                // System.out.print(' ');
            }
            // System.out.println("success");
            return true;
    }

    static public void sort(Sorter sorter, int[] array, Comparer comparer) {
        Sorter realSorter = sorter != null ? sorter : new QuickSort();
        Comparer realComparer = comparer != null ? comparer : new Less();
        realSorter.sort(array, realComparer);
    }

    static public void sort(int[] array) {
        sort(new BSTSort(), array, null);
    }

    static public Sorter createSorter(String name) {
        // System.out.println(name);
        if (name.equals("InsertSort")) {
            return new InsertSort();
        }
        else if (name.equals("QuickSort")) {
            return new QuickSort();
        }
        else if (name.equals("BST")) {
            return new BSTSort();
        }
        else {
            System.out.println("using default sorter: quick sort");
            return new QuickSort();
        }
    }
}