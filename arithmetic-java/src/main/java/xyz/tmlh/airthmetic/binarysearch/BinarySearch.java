package xyz.tmlh.airthmetic.binarysearch;

public class BinarySearch {

    public static void main(String[] args) {
        int arr[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 , 11, 12, 13,14,15,16,17,18,19,20 };
        System.out.println("index = " + binarySearch(arr, 0 ,arr.length -1 ,11));//
        System.out.println("index = " + binarySearch(arr , 11));//
    }

    private static int binarySearch(int[] arr, int left, int right, int val) {
        int mid = (left + right) / 2;
        
        if(arr[mid] == val) {
            return mid;
        }else if (arr[mid] > val) {
            return binarySearch(arr, left, mid -1 , val);
        }else if (arr[mid] < val){
            return binarySearch(arr, mid + 1, right , val);
        }
        return -1;
    }

    private static int binarySearch(int[] arr, int val) {
        int left = 0;
        int right = arr.length -1;
        while(left <= right) {
            int mid = (left + right) / 2;
            if(arr[mid] == val) {
                return mid;
            }else if (arr[mid] > val) {
                right = mid -1;
            }else if (arr[mid] < val){
                left =  mid + 1;
            }
        }
        return -1;
    }
}
