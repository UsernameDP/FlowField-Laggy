public class LinkedListS<T> {
    public static class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T data_) {
            this.value = data_;
            this.next = null;
        }

    }

    private Node<T> head;
    public int size;

    public LinkedListS() {
        head = null;
        size = 0; // size is initialized to 0
    }

    public void add(T data) {
        Node<T> newNode = new Node<T>(data);

        if (head == null) {
            head = newNode;
        } else {
            Node<T> currentNode = this.head;
            while (currentNode.next != null) {
                currentNode = currentNode.next;
            }
            currentNode.next = newNode; // b/c the currentNode.next will be null
        }

        size++; // can efficient do size this way
    }

    public Node<T> getFirst() {
        return head;
    }

    public void removeFirst() {
        this.head = this.head.next;

        size--;
    }

}
