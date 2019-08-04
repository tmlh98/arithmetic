package xyz.tmlh.airthmetic.sort;

import java.util.Stack;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 快排,Stack版
 */
public class QuickSort2 {

    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(/*1000 * 1000 **/ 10);
        long startTime  = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        ArraysUtil.printArray(arr);
        System.out.println("quickSort executes time : " + (System.currentTimeMillis() -startTime));
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        // 用一个集合栈来代替递归的函数栈
        Stack<Params> quickSortStack = new Stack<Params>();

        // 整个数列的起止下标
        quickSortStack.push(new Params(startIndex, endIndex));

        // 循环结束条件：栈为空时结束
        while (!quickSortStack.isEmpty()) {
            // 栈顶元素出栈，得到起止下标
            Params p = quickSortStack.pop();
            startIndex = p.getStartIndex();
            endIndex = p.getEndIndex();
            // 得到基准元素位置
            int pivotIndex = getPivot(arr, startIndex, endIndex);

            // 根据基准元素分成两部分, 把每一部分的起止下标入栈
            if (startIndex < pivotIndex - 1) {
                quickSortStack.push(new Params(startIndex, pivotIndex - 1));
            }
            if (pivotIndex + 1 < endIndex) {
                quickSortStack.push(new Params(pivotIndex + 1, endIndex));
            }

        }

    }

    private static int getPivot(int[] arr, int left, int right) {
        //记录初始基准值位置
        int pivotIndex = left;
        //获取基准值
        int pivotVal = arr[pivotIndex];
        while (right > left) {
            while (right > left) {
                if(arr[right] <= pivotVal) {
                    break;
                }
                right -- ;
            }
            while (right > left) {
                if(arr[left] > pivotVal) {
                    break;
                }
                left ++;
            }
            exchange(arr, left, right);
        }
        exchange(arr, pivotIndex, left);
        return left;
    }

    private static void exchange(int[] arr, int left, int right) {
        int tmp = arr[left];
        arr[left] = arr[right];
        arr[right] = tmp;
    }
    
    private static class Params{
        private int startIndex;
        private int endIndex;
        public Params(int startIndex, int endIndex) {
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }
        public int getStartIndex() {
            return startIndex;
        }
        public int getEndIndex() {
            return endIndex;
        }
    }
    

}