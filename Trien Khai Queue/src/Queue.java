import java.util.NoSuchElementException;

public class Queue {
    Node front;
    Node rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public boolean isEmpty() {
        return front == null;
    }

    public void enQueue (int data) {
        Node node = new Node(data);
        if (isEmpty()) {
            front = node;
            rear = node;
        } else {
            rear.link = node;
            rear = node;
        }
    }

    public int deQueue() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        int data = front.data;
        front = front.link;

        if (front == null) {
            rear = null;
        }

        return data;
    }

    public int peek() {
        if (isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        return front.data;
    }
}
