package baizhuan.hangzhou.com.androidlibstudy;

/**
 * User:    Xiaoc
 * 项目名:  AndroidStudio2
 * Package: baizhuan.hangzhou.com.androidlibstudy
 * Date:    16-11-30.
 * Time:    上午10:46
 * 类描述：  算法测试
 *
 * @vesion
 */
public class TestAlgorithm {

    public static void main(String[] args) {

        int[] nums = {10, 50, 11, 14, 40, 88,45};
        MaoPaoSort(nums);
        nums = new int[]{10, 50, 11, 14, 40, 88, 45, 25, 90};
        choseSort2(nums);
        nums = new int[]{10, 50, 11, 14, 40, 88, 45, 25, 90};
        choseSort(nums);

    }

    /***
     * 冒泡排序
     * 相邻2个数 进行对比， 然后交换位置
     *
     * @param nums
     */
    public static void MaoPaoSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int temp;
            for (int j = 0; j < nums.length - 1 - i; j++) {
                if (nums[j + 1] > nums[j]) {
                    temp = nums[j];
                    nums[j] = nums[j + 1];
                    nums[j + 1] = temp;
                }
                print("冒泡排序"+i, nums);
            }
        }
        print("冒泡排序", nums);
    }

    /***
     * 直接选择排序
     * 第i趟简单选择排序是指通过n-i次关键字的比较，从n-i+1个记录中选出关键字最小的记录，并和第i个记录进行交换。先临时记录其位置，只有在一趟
     * 循环完以后确定了最小的数据，才会发生交换。
     *
     * @param nums
     */
    public static void choseSort(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            //默认 这个是 最大数的坐标
            int min = i;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[min] < nums[j]) {
                    min = j;
                }
            }
            if (i != min) { //已经找到最小值，交换位置
                int temp = nums[min];
                nums[min] = nums[i];
                nums[i] = temp;
            }
        }
        print("直接选择排序", nums);

    }

    /***
     * 简单选择排序
     *
     * @param nums
     */
    public static void choseSort2(int[] nums) {
        for (int i = 0; i < nums.length - 1; i++) {
            int temp;
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] < nums[j]) {
                    temp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = temp;
                }
            }
        }
        print("简单选择排序", nums);
    }


    public static void print(String msg, int[] nums) {
        System.out.print(msg + ":\t[");
        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + "\t");
        }
        System.out.println("]");
    }

}
