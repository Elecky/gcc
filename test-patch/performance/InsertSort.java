public class InsertSort extends Sorter {
    public String getType() {
        return "InsertSort";
    }

    public void sort(int[] array, Comparer comparer) {
        int i = 1;
        while (i < array.length) {
            int j = i;
            int key = array[j];
            while (j != 0 && comparer.compare(key, array[j - 1]) < 0) {
                array[j] = array[j - 1];
                --j;
            }
            array[j] = key;
            ++i;
        }
    }
}