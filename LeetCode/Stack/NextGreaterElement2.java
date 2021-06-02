import java.util.*;

public class NextGreaterElement2 {
    public static int[] nextGreaterElements2(int[] nums) {
        int n = nums.length;
        int[] ngr = new int[n];
        // int[] ngl=new int[n];
        // work for next greater right
        Stack<Integer> str = new Stack<>();
        str.push(0);
        for (int i = 1; i < 2 * n; i++) {
            while (str.size() > 0 && nums[str.peek()] < nums[i % n]) {
                ngr[str.pop()] = nums[i % n];
            }
            str.push(i % n);
        }

        // int max;
        // int maxind;
        // if(ngr[0]==-1){
        // max=nums[0];
        // maxind=0;
        // }
        // else{
        // max=nums[ngr[0]];
        // maxind=ngr[0];
        // }
        // work for next greater left
        // Stack<Integer> stl=new Stack<>();
        // stl.push(n-1);
        // for(int i=n-2;i>=0;i--){
        // while(stl.size()>0 && nums[stl.peek()]<nums[i]){
        // ngl[stl.pop()]=nums[i];
        // }
        // stl.push(i);
        // }
        // while(stl.size()>0){
        // ngl[stl.pop()]=-1;
        // }

        // int[] res=new int[n];
        // for(int i=0;i<n;i++){
        // if(ngr[i]!=-1){
        // res[i]=nums[ngr[i]];
        // }
        // else
        // res[i]=max;
        // }
        // res[maxind]=-1;
        return ngr;
    }

    public static void main(String[] args) {
        int[] arr = { 6, 9, 3, 8, 5 };
        int[] res = nextGreaterElements2(arr);
        System.out.println(Arrays.toString(res));
    }

}
