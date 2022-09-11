import org.omg.CORBA.Object;

public class Array<E> {
    private E[]data;
    private int size;
    /**
     *
     * @param capacity
     */
    //构造函数，传入数组容量capacity构造Array
    public Array(int capacity){
        data =(E[])new Object[capacity];
        size=0;
    }
    //无参构造函数，默认数组容量capacity=10
    public Array(){
        this(10);
    }
    //获取数组元素个数
    public int getSize(){
        return size;
    }
    public Array(E[]arr){
        data=(E[])new Object[arr.length];
        for(int i=0;i<arr.length;i++)
            data[i]=arr[i];
        size=arr.length;
    }
    //获取数组的容量
    public int getCapacity(){
        return data.length;
    }
    public boolean isEmpty(){
        return size==0;
    }
    //在所有元素后添加新元素
    public void addLast(E e){
        if(size==data.length)
            throw new IllegalArgumentException("AddLAst failed.Array is full");
        data[size]=e;
        size++;
        //整体可替换为add（size，e）
    }
    public void addFirst(E e){
        add(0,e);
    }
    //在第index个位置插入一个新元素e
    public void add(int index,E e){
        if(size==data.length)
            resize(2*data.length);
        //throw new IllegalArgumentException("AddLAst failed.Array is full");

        if (index<0||index>size)
            throw new  IllegalArgumentException("AddLAst failed.Require index index>=0 and index <=size");

        for(int i=size-1;i>= index;i--)
            data[i+1]=data[i];
        data[index]=e;
        size++;

    }
    public E Get(int index){
        if(index<0||index>=size)
            throw new  IllegalArgumentException("Get failed.Index is illegal.");
        return data[index];
    }
    //修改数组中index未知的元素为e
    public void set(int index,E e){
        if(index<0||index>=size)
            throw new  IllegalArgumentException("Set failed.Index is illegal.");
        data[index]=e;
    }
    //查找数组中是否有元素e
    public boolean contains(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e))
                return true;
        }
        return false;
    }
    //查找数组中元素e所在的索引，若不存在元素e，则返回-1
    public int find(E e){
        for(int i=0;i<size;i++){
            if(data[i].equals(e))
                return i;
        }
        return -1;
    }
    //从数组中删除索引为index的元素，并返回删除的元素
    public E remove(int index){
        if(index<0||index>=size)
            throw new  IllegalArgumentException("Remove failed.Index is illegal.");
        E ret =data[index];
        for(int i=index+1;i<size;i++)
            data[i-1]=data[i];
        size--;
        data[size]=null;//非必须  loitering objects!=memory leak

        if(size==data.length/4&&data.length/2!=0)
            resize(data.length/2);
        return ret;
    }
    //从数组中删除第一个元素，并返回删除的元素
    public E removeFirst(){
        return remove(0);
    }
    //从数组中删除最后一个元素，并返回删除的元素
    public E removeLast(){
        return remove(size-1);
    }
    //从数组中删除元素e
    public void removeElement(E e){
        int index=find(e);
        if(index!=-1)
            remove(index);
    }
    public void swap(int i,int j){
        if(i<0||i>=size||j<0||j>=size)
            throw new IllegalArgumentException("Index is illegal");
        E t=data[i];
        data[i]=data[j];
        data[j]=t;
    }
    @Override
    public String toString(){
        StringBuilder res=new StringBuilder();
        res.append(String.format("Array:size=%d,capacity=%d\n",size,data.length));
        res.append('[');
        for(int i=0;i<size;i++){
            res.append(data[i]);
            if(i!=size-1)
                res.append(",");
        }
        res.append(']');
        return res.toString();
    }
    private void resize(int newCapacity){
        E[]newData=(E[])new Object[newCapacity];
        for(int i=0;i<size;i++)
            newData[i]=data[i];
        data=newData;
    }
}

