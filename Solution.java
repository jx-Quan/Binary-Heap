import java.util.PriorityQueue;
public class Solution {
    //使用java库中自带的优先队列解决select K
    public int findKthLargest(int[]nums,int k){
        PriorityQueue<Integer> pq=new PriorityQueue<>();
        for(int i=0;i<k;i++)
            pq.add(nums[i]);
        for(int i=k;i<nums.length;i++)
            if(!pq.isEmpty()&&nums[i]>pq.peek()){
                pq.remove();
                pq.add(nums[i]);
            }
        return pq.peek();
    }
}
