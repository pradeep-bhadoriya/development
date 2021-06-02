import java.io.*;
import java.util.*;

public class NextGreaterToRight {
    public static void display(int[] a) {
        StringBuilder sb = new StringBuilder();

        for (int val : a) {
            sb.append(val + "\n");
        }
        System.out.println(sb);
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // int n = Integer.parseInt(br.readLine());
        // int[] a = new int[n];
        // for (int i = 0; i < n; i++) {
        // a[i] = Integer.parseInt(br.readLine());
        // }

        // int[] nge = solveNextRightGreater(a);
        // int[] nge = solveNextRightSmall(a);
        // int[] nge = solveNextLeftGreater(a);
        int[] a = { 1, 3, 2, 5, 4 };
        int[] nge = solveNextLeftSmall(a);

        display(nge);
    }

    public static int[] solveNextRightGreater(int[] arr) {

        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < n; i++) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        // System.out.print(Arrays.toString(res));
        return res;

    }

    public static int[] solveNextLeftGreater(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] < arr[i]) {
                res[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        // System.out.print(Arrays.toString(res));
        return res;
    }

    public static int[] solveNextRightSmall(int[] arr) {

        int n = arr.length;
        int[] res = new int[n];
        Stack<Integer> st = new Stack<>();
        st.push(0);
        for (int i = 1; i < n; i++) {
            while (st.size() > 0 && arr[st.peek()] > arr[i]) {
                res[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            res[st.pop()] = -1;
        }
        // System.out.print(Arrays.toString(res));
        return res;

    }

    public static int[] solveNextLeftSmall(int[] arr) {
        Stack<Integer> st = new Stack<>();
        int n = arr.length;
        // int[] res = new int[arr.length];
        // res[0] = -1;
        st.push(n - 1);
        for (int i = n - 2; i >= 0; i--) {
            while (st.size() > 0 && arr[st.peek()] >= arr[i]) {
                arr[st.peek()] = arr[i];
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            arr[st.pop()] = -1;
        }
        return arr;
    }
}