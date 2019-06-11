package xyz.tmlh.airthmetic.sort;

import xyz.tmlh.airthmetic.util.ArraysUtil;

/*
 * 快排,递归版
 */
public class QuickSort {

    public static void main(String[] args) {
        int[] arr = ArraysUtil.genArray(1000 * 100);
        long startTime  = System.currentTimeMillis();
        quickSort(arr, 0, arr.length - 1);
        System.out.println("shellSort executes time : " + (System.currentTimeMillis() -startTime));
    }

    private static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex >= endIndex) {
            return;
        }

        int pivotIndex = getPivot2(arr, startIndex, endIndex);
        // 把排序元素分成两部分
        quickSort(arr, startIndex, pivotIndex - 1);
        quickSort(arr, pivotIndex + 1, endIndex);
    }

    /*
     * 挖坑法
     * 1、从right指针开始，把指针所指向的元素和基准元素做比较。如果比pivot大，则right指针向左移动；如果比pivot小，则把right所指向的元素填入坑中。
     * 2、接下来，我们切换到left指针进行比较。如果left指向的元素小于pivot，则left指针向右移动；如果元素大于pivot，则把left指向的元素填入坑中。
     * 3、让最终位置等于arr[pivot]
     */
    private static int getPivot(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        // 坑的位置
        int index = startIndex;
        int left = startIndex;
        int right = endIndex;

        while (right >= left) {
            while (right >= left) {
                // 从right指针开始
                if (arr[right] < pivot) {
                    arr[index] = arr[right];
                    index = right;
                    left++;
                    break;
                }
                right--;
            }

            while (right >= left) {
                // 从left指针开始
                if (arr[left] > pivot) {
                    arr[index] = arr[left];
                    index = left;
                    right--;
                    break;
                }
                left++;
            }

        }
        arr[index] = pivot;
        return index;
    }
    
    /*
     * 💗指针交换法
     * 1、第一次循环，从right指针开始，把指针所指向的元素和基准元素做比较。如果大于等于pivot，则指针向左移动；如果小于pivot，则right指针停止移动，切换到left指针。
     * 2、轮到left指针行动，把指针所指向的元素和基准元素做比较。如果小于等于pivot，则指针向右移动；如果大于pivot，则left指针停止移动。left与right元素进行交换。
     * 3、当left和right指针重合之时，我们让pivot元素和left与right重合点的元素进行交换。
     */
    private static int getPivot2(int[] arr, int startIndex, int endIndex) {
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left < right) {
            while (left < right) {
                if(arr[right] <= pivot) {
                    //切换指针
                    break;
                }
                right --;
            }
            
            while (left < right) {
                if(arr[left] > pivot) {
                    break;
                }
                left ++;
            }
            exchange(arr, left, right);
        }
        exchange(arr, left, startIndex);
        return left;
    }

    private static void exchange(int[] arr, int left, int right) {
        int tmp = arr[left]; 
        arr[left] = arr[right]; 
        arr[right] = tmp;
    }
    
    
    
    
    
    
    
    
    
    
}