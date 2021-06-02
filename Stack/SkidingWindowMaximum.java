import java.io.*;
import java.util.*;

public class SkidingWindowMaximum {

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(br.readLine());
        }
        int k = Integer.parseInt(br.readLine());
        // code
        int[] arr = new int[n];
        Stack<Integer> st = new Stack<>();
        for (int i = 0; i < n; i++) {
            while (st.size() > 0 && a[st.peek()] < a[i]) {
                arr[st.peek()] = i;
                st.pop();
            }
            st.push(i);
        }
        while (st.size() > 0) {
            arr[st.pop()] = n;
        }
        // System.out.print(Arrays.toString(arr));
        int[] ans = new int[n];
        int j = 0;
        for (int i = 0; i <= n - k; i++) {
            if (j < i)
                j = i;
            while (arr[j] < i + k) {
                j = arr[j];
            }
            ans[i] = a[j];
        }
        for (int i = 0; i <= n - k; i++) {
            System.out.println(ans[i]);
        }
    }

}
