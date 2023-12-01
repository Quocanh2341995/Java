import java.util.NoSuchElementException;

public class Solution {
    public static void enqueue(Queue queue, int data) {
        Node newNode = new Node(data);

        if (queue.isEmpty()) {
            queue.front = newNode;
            queue.rear = newNode;
        } else {
            queue.rear.link = newNode;
            queue.rear = newNode;
            queue.rear.link = queue.front;
        }
    }

    public static int dequeue(Queue queue) {
        if (queue.isEmpty()) {
            throw new NoSuchElementException("Queue is empty");
        }

        int data = queue.front.data;

        if (queue.front == queue.rear) {
            queue.front = null;
            queue.rear = null;
        } else {
            queue.front = queue.front.link;
            queue.rear.link = queue.front;
        }

        return data;
    }

    public static void displayQueue(Queue queue) {
        if (queue.isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }

        Node currentNode = queue.front;
        System.out.print("Queue: ");
        do {
            System.out.print(currentNode.data + " ");
            currentNode = currentNode.link;
        } while (currentNode != queue.front);
        System.out.println();
    }


}
