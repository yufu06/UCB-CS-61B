import org.w3c.dom.NodeList;
import sun.awt.image.ImageWatched;

public class LinkedListDeque<T> {

    class LinkNode {
        public LinkNode last;
        public LinkNode next;
        public T item;

        public LinkNode (LinkNode l, LinkNode n, T v) {
            last = l;
            next = n;
            item = v;
        }
    }

    public int size;
    public LinkNode sentinel;


    public LinkedListDeque() {
        size = 0;
        sentinel = new LinkNode(null, null, null);
        sentinel.last = sentinel;
        sentinel.next = sentinel;
    }

    public LinkedListDeque(LinkedListDeque other) {
        size = other.size;
        sentinel = new LinkNode(null, null, (T) other.sentinel.item);
        LinkNode ptr = sentinel;
        LinkNode ptrOther = other.sentinel;
        ptr.next = new LinkNode(ptr, null, ptrOther.next.item);
        ptr = ptr.next;
        ptrOther = ptrOther.next;
        while (ptrOther.next != other.sentinel) {
            ptr.next = new LinkNode(ptr, null, ptrOther.next.item);
            ptr = ptr.next;
            ptrOther = ptrOther.next;
        } ptr.next = new LinkNode(ptr, sentinel, ptrOther.next.item);
    }

    public void addFirst(T item) {
        LinkNode node = new LinkNode(this.sentinel, this.sentinel.next, item);
        sentinel.next = node;
        node.next.last = node;
        this.size++;
    }

    public void addLast(T item) {
        LinkNode node = new LinkNode(this.sentinel.last, this.sentinel, item);
        this.sentinel.last.next = node;
        this.sentinel.last = node;
        this.size++;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int size() {
        return this.size;
    }

    public void printDeque() {
        LinkNode ptr = this.sentinel.next;
        while (ptr != this.sentinel) {
            System.out.print(ptr.item);
            if (ptr.next != sentinel) {
                System.out.print(" ");
            } else {
                System.out.print("\n");
            } ptr = ptr.next;
        }
    }

    public LinkNode removeFirst() {
        LinkNode ptr = this.sentinel.next;
        this.sentinel.next = this.sentinel.next.next;
        this.sentinel.next.last = this.sentinel;
        this.size--;
        return ptr;
    }

    public LinkNode removeLast() {
        LinkNode ptr = this.sentinel.last;
        this.sentinel.last = this.sentinel.last.last;
        this.sentinel.last.next = this.sentinel;
        this.size--;
        return ptr;
    }

    public LinkNode get(int index) {
        LinkNode ptr = this.sentinel;
        for (int i = 0; i < index; i++) {
            ptr = ptr.next;
        } return ptr;
    }

    public LinkNode getRecursive(int index) {
        return getRecursiveHelper(this.sentinel, index);
    }

    private LinkNode getRecursiveHelper(LinkNode ptr, int index) {
        if (index == 0) {
            return ptr;
        } else {
            return getRecursiveHelper(ptr.next, index - 1);
        }
    }


}
