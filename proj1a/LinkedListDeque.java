import com.sun.source.tree.UsesTree;

public class  LinkedListDeque<T> {

    private  class Innode<T>{
        Innode pre;
        T item;
        Innode next;
        private Innode(T item_, Innode pre_, Innode next_){
            item = item_;
            pre = pre_;
            next = next_;
        }

    }
    private int size;
    private Innode sentinel;
    private Innode first;
    private Innode Last;

    public LinkedListDeque(){
        size = 0;
        sentinel = new Innode(null, sentinel ,sentinel);
        sentinel.next=sentinel;
        sentinel.pre =sentinel;
    }
    public LinkedListDeque(T item){
        size = 0;
        sentinel = new Innode(null, sentinel ,sentinel);
        sentinel.next=sentinel;
        sentinel.pre =sentinel;

        first = new Innode(item, sentinel,sentinel);
        Last = first;
        sentinel.next = first;
        sentinel.pre =  Last;

        size = 1;
    }
    public void addFirst(T item){
        size += 1;
        if(size ==1 )
            first =new Innode<>(item, sentinel, sentinel);
       else
           first = new Innode(item, sentinel, sentinel.next);
        sentinel.next = first;
        first.next.pre = first;
        Last = sentinel.pre;
        first = sentinel.next;

    }

    public void addLast(T item){
        this.Last = new Innode(item, this.Last, sentinel);
        Last.pre.next = Last;
        sentinel.pre = Last;
        size += 1;

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
        Innode p = first;
        while(p.next == sentinel)
            System.out.print(p.item+" ");
        System.out.println();
    }

    public T removeFirst(){
        Innode temp =first;
        sentinel.next = first.next;
        first.next.pre = sentinel;
        first = first.next;
        size --;
        return (T)temp.item;
    }

    public T removeLast(){
        Innode temp =Last;
        Last.pre.next = null;
        Last = Last.pre;
        sentinel.pre = Last;
        size --;
        return (T)temp.item;
    }

    public T get(int index){

        if(size < index + 1)
            return null;
        Innode p =first;
        while(index > 0)
        {
            p = p.next;
            index--;
        }
        return (T)p.item;

    }
    public T getRs(Innode node,int index){
            if(index == 0){
              return (T)node.item;
            }
            else {
                return (T)getRs(node.next,index -1);
            }
    }
    public T getRecursive(int index){
        if(size < index + 1)
            return null;
        Innode p =first;
        return getRs(p,index);
    }


}
