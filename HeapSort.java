public class HeapSort {
    private HeapSort(){}
    //使用额外空间的堆排序
    public static <E extends Comparable<E>> void sort(E []data){
        MaxHeap<E> maxHeap =new MaxHeap<>();
        for(E e:data)
            maxHeap.add(e);
        for(int i=data.length-1;i>=0;i--)
            data[i]=maxHeap.extractMax();
    }
    //优化后堆排序
    public static <E extends Comparable<E>> void sort2(E []data){
        if(data.length<=1) return;
        for(int i=(data.length-2)/2;i>=0;i--)
            siftDown(data,i,data.length);
        for (int i=data.length-1;i>=0;i--){
            swap(data,0,i);
            siftDown(data,0,i);
        }
    }
    //对data[0,n]所形成的最大堆中，索引为k的元素，执行siftdown
    private static<E extends Comparable<E>> void siftDown(E[]data,int k,int n){
        while(2*k+1<n){
            int j=2*k+1;
            if(j+1<n&&data[j+1].compareTo(data[j])>0)
                j=2*k+2;
            if(data[k].compareTo(data[j])>=0)
                break;
            swap(data,k,j);
            k=j;
        }
    }
    public static <E>void swap(E[]data,int i,int j){
        E t=data[i];
        data[i]=data[j];
        data[j]=t;
    }
}
