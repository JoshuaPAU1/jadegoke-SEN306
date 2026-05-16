

public class Main {
    public static void main(String[] args) {
        QueueADT queue = new LinkedQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println("Dequeued: " + queue.dequeue()); // 10
        System.out.println("Size: " + queue.size());         // 2

        System.out.println("Contains 20: " + queue.contains(20)); // true
        System.out.println("Contains 99: " + queue.contains(99)); // false

        System.out.println("Index of 30: " + queue.indexOf(30));  // 1
        System.out.println("Index of 99: " + queue.indexOf(99));  // -1
    }
}

// Interface
interface QueueADT {
    void enqueue(int element);
    int dequeue();
    boolean isEmpty();
    int size();

    // New methods
    boolean contains(int target);
    int indexOf(int target);
}

// Node class
class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

// LinkedQueue implementation
class LinkedQueue implements QueueADT {
    private Node head;
    private Node tail;
    private int count;

    public void enqueue(int element) {
        Node newNode = new Node(element);

        if (isEmpty()) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        count++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new RuntimeException("Queue is empty");
        }

        int value = head.data;
        head = head.next;

        if (head == null) {
            tail = null;
        }

        count--;
        return value;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }

    // Search method 1
    public boolean contains(int target) {
        Node current = head;

        while (current != null) {
            if (current.data == target) {
                return true;
            }
            current = current.next;
        }

        return false;
    }

    // Search method 2
    public int indexOf(int target) {
        Node current = head;
        int index = 0;

        while (current != null) {
            if (current.data == target) {
                return index;
            }
            current = current.next;
            index++;
        }

        return -1;
    }
}