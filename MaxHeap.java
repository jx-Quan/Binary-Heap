public class MaxHeap<E extends Comparable<E>> {
    private Array<E> data;
    public MaxHeap(int capacity){
        data=new Array<>(capacity);
    }
    public MaxHeap(){
        data=new Array<>();
    }
    public MaxHeap(E[]arr){
        data=new Array<>(arr);
        for(int i=parent(arr.length-1);i>=0;i--)
            siftDown(i);
    }
    //返回堆中元素个数
    public int size(){
        return data.getSize();
    }
    //返回一个布尔值，确定堆是否为空
    public boolean isEmpty(){
        return data.isEmpty();
    }
    private int parent(int index){
        if(index==0)
            throw new IllegalArgumentException("index-0 doesn't have parent");
        return (index-1)/2;
    }
    private int leftChild(int index){
        return index*2+1;
    }
    private int rightChild(int index){
        return index*2+2;
    }
    //添加元素
    public void add(E e){
        data.addLast(e);
        siftUp(data.getSize()-1);
    }
    private void siftUp(int k){
        while(k>0&&data.Get(parent(k)).compareTo(data.Get(k))<0){
            data.swap(k,parent(k));
            k=parent(k);
        }
    }
    //查看堆中最大元素
    public E findMax(){
        if(data.getSize()==0)
            throw new IllegalArgumentException("can not findMax when heap is empty");
        return data.Get(0);
    }
    //取出堆中最大元素
    public E extractMax(){
        E ret=findMax();
        data.swap(0,data.getSize()-1);
        data.removeLast();
        siftDown(0);
        return ret;
    }
    private void siftDown(int k){
        while(leftChild(k)<data.getSize()){
            int j=leftChild(k);
            if(j+1<data.getSize()&&data.Get(j+1).compareTo(data.Get(j))>0)
                j=rightChild(k);
            if(data.Get(k).compareTo(data.Get(j))>=0)
                break;
            data.swap(k,j);
            k=j;
        }
    }
    //取出最大元素，并且替换为元素e
    public E replace(E e){
        E ret=findMax();
        data.set(0,e);
        siftDown(0);
        return ret;
    }
}
