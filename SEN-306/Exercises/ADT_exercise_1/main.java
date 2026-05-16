package Exercises.ADT_exercise_1;

public class main {
    public static void main(String[] args) {
        QueueADT queue = new LinkedQueue();

        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        System.out.println(queue.dequeue()); 
        System.out.println(queue.size());    
    }
}

interface QueueADT {
    void enqueue(int element);
    int dequeue();
    boolean isEmpty();
    int size();
}


class Node {
    int data;
    Node next;

    Node(int data) {
        this.data = data;
    }
}

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
        if (isEmpty()) throw new RuntimeException("Queue is empty");

        int value = head.data;
        head = head.next;

        if (head == null) tail = null;

        count--;
        return value;
    }

    public boolean isEmpty() {
        return count == 0;
    }

    public int size() {
        return count;
    }
}