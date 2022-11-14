import java.util.Arrays;

public class ArrayDeque<T>{
    private int size;
    private int first;
    private int last;
    private T[] Q;
    public ArrayDeque(){
        Q = (T[])new Object[8];
        size = 0;
        first = -1;
        last  = -1;
    }
    public ArrayDeque(T item){
        Q = (T[])new Object[8];
        Q[0] = item;
        size += 1;
        first = 0;
        last = 0;
    }
    public void reSize(int capacity)
    {
        T[] temp = (T[]) new Object[capacity];
        if(first < last)
            System.arraycopy(Q,0, temp, 0,size);
        if(first > last) {
            System.arraycopy(Q, first, temp, 0, Q.length - first);
            System.arraycopy(Q,0,temp,Q.length - first,last + 1);
            first = 0;
            last = Q.length -1 ;
        }
            Q = temp;
    }
    public void addFirst(T item){
       if(size >= Q.length){
           reSize(Q.length * 2);
       }
        if(size == 0){
        Q[0] = item;
        first = 0;
        last = 0;
        }
        else{
         first = (first + Q.length - 1) % Q.length;
        }
        Q[first] = item;
        size += 1;

    }

    public void addLast(T item){
        if(size >= Q.length )
            reSize(Q.length * 2);
        Q[size - 1] = item;
        size += 1;
        last += 1;
    }

    public boolean isEmpty(){
        if(size == 0)
            return true;
        return false;
    }

    public int size(){
        return size;
    }

    public void printDeque(){
    for(int i =0;i < size; i++){
        System.out.print(Q[(first+i) % Q.length]+" " );
    }
    System.out.println();

    }


    public T removeFirst(){
        if(size < Q.length * 0.25){
            reSize((int)(Q.length * 0.25));
        }
        T temp = Q[first];
        Q[first] = null;
        first = (first+1) % Q.length;
        size -= 1;
        return temp;
    }

    public T removeLast(){
        if(size < Q.length * 0.25){
            reSize((int)(Q.length * 0.25));
        }
        T temp = Q[last];
        Q[last] = null;
        last = (last + Q.length -1) % Q.length;
        size -= 1;
        return temp;
    }

    public T get(int index){
        if(index >= Q.length)
            return null;
        return Q[index];

    }



}
