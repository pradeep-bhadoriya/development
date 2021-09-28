public class Arrays_String {

    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        System.out.println(Arrays.toString(nums));
        return Math.max(nums[0] * nums[1] * nums[n - 1], nums[n - 1] * nums[n - 2] * nums[n - 3]);
    }

    public boolean isVowel(char ch) {
        if (ch == 'a' || ch == 'e' || ch == 'i' || ch == 'o' || ch == 'u' || ch == 'A' || ch == 'E' || ch == 'I'
                || ch == 'O' || ch == 'U') {
            return true;
        }
        return false;
    }

    public String reverseVowels(String s) {
        char[] arr = s.toCharArray();
        int left = 0;
        int right = arr.length - 1;
        while (left < right) {
            // make left pointer at vowel
            while (left < right && isVowel(arr[left]) == false) {
                left++;
            }
            // make right pointer at vowel
            while (left < right && isVowel(arr[right]) == false) {
                right--;
            }

            char ch = arr[left];
            arr[left] = arr[right];
            arr[right] = ch;
        }
        return Arrays.stringOf(arr);
    }

    public int[] productExceptSelf(int[] nums) {
        int[] left = new int[nums.length];
        int[] right = new int[nums.length];

        left[0] = 1;
        right[nums.length - 1] = 1;
        int j = nums.length - 2;
        for (int i = 1; i < nums.length; i++, j--) {
            left[i] = left[i - 1] * nums[i - 1];
            right[j] = right[j + 1] * nums[j + 1];
        }
        int[] res = new int[nums.length];
        for (int k = 0; k < nums.length; k++) {
            res[k] = left[k] * right[k];
        }
        return res;
    }

    public int maxDistToClosest(int[] seats) {
        int overCount = 0;
        int prevCount = 0;
        int maxInd = 0;
        int j = 0;
        int initialZero = 0;
        boolean flag = false;
        while (j < seats.length) {
            prevCount = 0;
            while (j < seats.length && seats[j] != 1) {
                prevCount += 1;
                j++;
            }
            if (flag == false) {
                initialZero = prevCount;
            }
            flag = true;
            if (prevCount >= overCount) {
                overCount = prevCount;
                maxInd = j;
            }
            j++;
        }
        return Math.max((overCount + 1) / 2, Math.max(initialZero, seats[seats.length - 1] == 0 ? prevCount : 0));

    }

    public static List<List<Integer>> twoSum(int[] arr, int target) {
        // write your code here
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();
        int i = 0;
        int j = arr.length - 1;
        while (i < j) {
            if (i != 0 && j != arr.length - 1 && arr[i] == arr[i - 1]) {
                i++;
            }
            if (arr[i] + arr[j] == target) {
                List<Integer> temp = new ArrayList<>();
                temp.add(arr[i]);
                temp.add(arr[j]);
                list.add(temp);
                i++;
                j--;
            } else if (arr[i] + arr[j] < target) {
                i++;
            } else {
                j--;
            }
        }

    }

    public int maximumSwap(int num) {
        String s=""+num;
        char[] arr=s.toCharArray();
        int[] res=new int[arr.length];
        res[arr.length-1]=arr.length-1;
        for(int i=arr.length-2;i>=0;i--){
            if(arr[i]>arr[res[i+1]]){
                res[i]=i;
            }
            else{
                res[i]=res[i+1];
            }
        }
        boolean flag=false;
        for(int i=0;i<res.length;i++){
            if(arr[i]<arr[res[i]]){
                flag=true;
                char temp=arr[i];
                arr[i]=arr[res[i]];
                arr[res[i]]=temp;
                break;
            }
        }
        if(flag){
            int ans=Integer.parseInt(String.valueOf(arr));
            return ans;
        }
        else{
            return num;
        }
    }

    public int[] twoSumLeetCode(int[] arr, int target) {
        int[] org=new int[arr.length];
        for(int i=0;i<arr.length;i++){
            org[i]=arr[i];
        }
        Arrays.sort(arr);
        int[] list=new int[2];
        int i=0;
        int j=arr.length-1;
        while(i<j){
            if(i!=0 && arr[i]==arr[i-1]){
                i++;
            }
            else if(arr[i]+arr[j]==target){
                list[0]=i;
                list[1]=j;
                break;
            }
            else if(arr[i]+arr[j]<target){
                i++;
            }
            else{
                j--;
            }
        }
        int[] res=new int[2];
        boolean flag1=false;
        boolean flag2=false;
        int temp1=arr[list[0]];
        int temp2=arr[list[1]];
        for(int k=0;i<org.length;k++){
            if(org[k]==temp1 && flag1==false){
                res[0]=k;
                flag1=true;
            }
            else if(org[k]==temp2 && flag2==false){
                res[1]=k;
                flag2=true;
            }
            if(flag1 && flag2){
                break;
            }
        }
        return res;
    }

    public boolean findPairDifference(int arr[], int size, int n)
    {
        //code here.
        Arrays.sort(arr);
        int i=0;
        int j=i+1;
        while(j<size){
            if(arr[j]-arr[i]==n){
                return true;
            }
            else if(arr[j]-arr[i]>n){
                i++;
            }
            else{
                j++;
            }
        }
        return false;
    }

    //Seive theorem
    public int countPrimes(int n) {
        if(n==0){
            return 0;
        }
        boolean[] arr=new boolean[n+1];
        Arrays.fill(arr, true);
        arr[0]=false;
        arr[1]=false;
        for(int i=2;i*i<=n;i++){
            if(arr[i]){
                for(int j=i+i;j<=n;j=j+i){
                    if(arr[j]){
                        arr[j]=false;
                    }
                }
            }
        }
        int count=0;
        for(int i=0;i<arr.length-1;i++){
            if(arr[i]){
                count++;
            }
        }
        return count;
    }

    //Pascals Traingle II
    public List<Integer> getRow(int rowIndex) {
        long prev=1;
        List<Integer> res=new ArrayList<>();
        res.add(1);
        if(rowIndex==0){
            return res;
        }
        for(int r=1;r<rowIndex;r++){
            long cur=((rowIndex-r+1)*prev)/r;
            res.add((int)cur);
            prev=cur;
        }
        res.add(1);
        return res;
    }

    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        // step 1. find occurence of one and mark out of range element
        boolean one = false;
        for(int i = 0; i < n; i++) {
            if(nums[i] == 1) one = true;

            if(nums[i] < 1 || n < nums[i]) {
                nums[i] = 1;
            }
        }
        if(one == false) return 1;
        // step 2. mark element in array
        for(int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            int indx = val - 1;
            nums[indx] = -Math.abs(nums[indx]);
        }

        // step 3. check first missing positive
        for(int i = 0; i < n; i++) {
            if(nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }

    public static void swap(int[] arr, int indx){
        int temp=arr[indx];
        arr[indx]=arr[indx-1];
        arr[indx-1]=temp;
    }
    public static void wiggleSort1(int[] arr) {
      // write your code here
      for(int i=1;i<arr.length;i++){
          if(i%2==1){
              if(arr[i]<arr[i-1]){
                  swap(arr,i);
              }
          }
          else{
              if(arr[i]>arr[i-1]){
                  swap(arr,i);
              }
          }
      }

      private static int ceilIndex(char[] arr, int indx) {
        for(int i = arr.length - 1; i >= 0; i--) {
            if(arr[i] > arr[indx]) {
                return i;
            }
        }
        return -1;
    } 

    private static void swap(char[] arr, int left, int right) {
        char temp = arr[left];
        arr[left] = arr[right];
        arr[right] = temp;
    }

    private static void reverse(char[] arr, int indx) {
        int left = indx;
        int right = arr.length - 1;
        while(left < right) {
            swap(arr, left, right);
            left++;
            right--;
        }
    }

    public static String nextGreaterElement_(String str) {
        if(str.length() == 1) return "-1";
        char[] arr = str.toCharArray();
        int dipIndx = dipIndex(arr);
        if(dipIndx == -1) {
            return "-1";
        }
        int ceilIndx = ceilIndex(arr, dipIndx);
        swap(arr, dipIndx, ceilIndx);
        reverse(arr, dipIndx + 1);
        return String.valueOf(arr);
    }

    // for leetcode, above problem
    public int nextGreaterElement(int n) {
        String str = n + "";
        String res_ = nextGreaterElement_(str);
        long res = Long.parseLong(res_);
        if(res <= Integer.MAX_VALUE) {
            return (int)res;
        } else {
            return -1;
        }
    }

    // leetcode 769. https://leetcode.com/problems/max-chunks-to-make-sorted/
    public int maxChunksToSorted1(int[] arr) {
        int chunks = 0;
        int max = 0;
        for(int i = 0; i < arr.length; i++) {
            max = Math.max(max, arr[i]);
            if(max == i)
                chunks++;
        }
        return chunks;
    }

    // leetcode 768 https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
    public int maxChunksToSorted2(int[] arr) {
        int n = arr.length;
        int[] rightMin = new int[n + 1];
        rightMin[n] = Integer.MAX_VALUE;
        int chunks = 0;
        // prepare right min
        for(int i = n - 1; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]);
        }
        // solve count of using using a leftmax variable
        int leftmax = Integer.MIN_VALUE;
        for(int i = 0; i < n; i++) {
            leftmax = Math.max(leftmax, arr[i]);

            if(leftmax <= rightMin[i + 1])
                chunks++;
        }
        return chunks;
    }
    
    // difference pair with k, 
    // https://practice.geeksforgeeks.org/problems/find-pair-given-difference1559/1
    public boolean findPair(int arr[], int size, int n) {
        if(arr.length == 1) return false;
        //code here.
        Arrays.sort(arr);
        int i = 0;
        int j = 1;
            
        while(j < arr.length) {
            int diff = arr[j] - arr[i];
            if(diff == n) {
                return true;
            } else if(diff > n) {
                i++;
            } else {
                j++;
            }
        }
        return false;
    }
    
    public String pushDominoes(String dominoes) {
        dominoes="L"+dominoes+"R";
        char[] arr=dominoes.toCharArray();
        int i=0;
        int j=1;
        while(j<arr.length){
            while(arr[j]=='.'){
                j++;
            }
            if(arr[i]=='L' && arr[j]=='L'){
                while(i<j){
                    arr[i]='L';
                    i++;
                }
                i=j;j++;
            }
            else if(arr[i]=='L' && arr[j]=='R'){
                i=j;j++;
            }
            else if(arr[i]=='R' && arr[j]=='L'){
                int k=j;
                while(i<k){
                    arr[i]='R';
                    arr[k]='L';
                    i++;k--;
                }
                i=j;j++;
            }
            else if(arr[i]=='R' && arr[j]=='R'){
                while(i<j){
                    arr[i]='R';
                    i++;
                }
                i=j;j++;
            }
        }
        return String.valueOf(arr).substring(1,arr.length-1);
    }
    
    public int consecutiveNumbersSum(int n) {
        int count=0;
        for(int k=1;k*(k-1)<2*n;k++){
            int numerator=(n-(k*(k-1)/2));
            if(numerator%k==0){
                count++;
            }   
        }
        return count;
    }
    
    public boolean validPalindrome(String s) {
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)==s.charAt(j)){
                i++;j--;
            }
            else{
                if(checkPlaindrome(s.substring(i+1,j+1))){
                    return true;
                }
                if(checkPlaindrome(s.substring(i,j))){
                    return true;
                }
                return false;
            }
        }
        return true;
    }
    public boolean checkPlaindrome(String s){
        int i=0,j=s.length()-1;
        while(i<j){
            if(s.charAt(i)!=s.charAt(j)){
                return false;
            }
            i++;j--;
        }
        return true;
    }
    
    public int reachNumber(int target) {
        target=Math.abs(target);
        int count=0;
        int sum=0;
        int i=1;
        for(;sum<target;i++){
            
            sum=sum+i;
            if(sum==target){
                break;
            }
            else if(sum>target){
                if((sum-target)%2==0){
                    // count++;
                    break;
                }
                else{
                    if((sum+i+1-target)%2==0){
                        i++;
                        break;
                    }
                    else{
                        i+=2;
                        break;
                    }
                }
            }
        }
        return i;
    }
    
    public List<Integer> partitionLabels(String s) {
        HashMap<Character, Integer> hm=new HashMap<>();
        List<Integer> list=new ArrayList<>();
        int prev=0;
        for(int i=0;i<s.length();i++){
            char ch=s.charAt(i);
            hm.put(ch,i);
        }
        int count=0;
        int i=0;
        int j=hm.get(s.charAt(i));
        while(i<s.length()){
            while(i<s.length() && i!=j){
                if(hm.get(s.charAt(i))>j){
                    j=hm.get(s.charAt(i));
                }
                i++;
            }
            if(i>=s.length()){
                return list;
            }
            list.add(i-prev+1);
            i++;
            prev=i;
            
            if(i>=s.length()){
                break;
            }
            j=hm.get(s.charAt(i));
        }
        return list;
    }
    
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a,b)->Integer.compare(a[0], b[0]));
        int lsp=intervals[0][0];
        int lep=intervals[0][1];
        ArrayList<int[]> list=new ArrayList<>();
        for(int i=1;i<intervals.length;i++){
            int sp=intervals[i][0];
            int ep=intervals[i][1];
            
            if(lep<sp){
                // new interval found
                int[] arr={lsp,lep};
                list.add(arr);
                lsp=sp;
                lep=ep;
            }
            else{
                // overlap
                if(lep<ep){
                    lep=ep;
                }
                else{
                    // nothing to do
                }
            }
        }
        int[] arr={lsp, lep};
        list.add(arr);
        int[][] myArray = new int[list.size()][];
        return list.toArray(myArray);
    }
    
        
    
    public static void demo(){
        System.out.println("Arrays and String")
    }

    public static void main(String[] args) {
        demo();
    }
}

