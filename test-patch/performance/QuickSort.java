public class QuickSort extends Sorter {
    public String getType() {
        return "QuickSort";
    }
    public void sort(int[] array, Comparer comparer) {
        sort(array, 0, array.length - 1, comparer);
    }

    public final void sort(int[] array, int from, int to, Comparer comparer) {
        if (array.length > 0) {
            sort_(array, from, to, comparer);
        }
    }

    private void sort_(int[] array, int from, int to, Comparer comparer) {
        if (from == to) {
            return;
        }
        int key = array[from];
        int lt = from, rt = to;
        while (true) {
            while (lt <= rt && comparer.compare(array[lt], key) < 0) {
                ++lt;
            }
            while (lt <= rt && comparer.compare(array[rt], key) > 0) {
                --rt;
            }
            if (lt < rt) {
                /* swap array[lt], array[rt] */
                int temp = array[lt];
                array[lt] = array[rt];
                array[rt] = temp;
                ++lt;
                --rt;
            }
            else {
                break;
            }
        }

        if (from < lt - 1) {
            sort_(array, from, lt - 1, comparer);
        }
        if (rt + 1 < to) {
            sort_(array, rt + 1, to, comparer);
        }
    }
}