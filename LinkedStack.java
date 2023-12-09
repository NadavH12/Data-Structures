/*

Nadav Horowitz CS211 6/4/2022

This class implements a Stack structure using a LinkedList structure for use in the BigIntegerAdder program.

*/
public class LinkedStack implements StackInterface {

    public LinkedStackNode front;

    public LinkedStack() {
    }

    public void push(int addedDigit) {
        if (front == null) {
            front = new LinkedStackNode(addedDigit);
        }

        else {
            LinkedStackNode cursor = front;
            front = new LinkedStackNode(addedDigit, cursor);
        }
    }

    public int pop() {
        LinkedStackNode cursor = front;
        front = front.next;
        int removedDigit = cursor.nodeData;
        return removedDigit;
    }

    public int top() {
        int topDigit = front.nodeData;
        return topDigit;
    }

    public boolean isEmpty() {
        boolean isLinkedStackEmpty = (front == null);
        return isLinkedStackEmpty;
    }

    private class LinkedStackNode {

        public int nodeData;
        public LinkedStackNode next;

        public LinkedStackNode(int data) {
            this(data, null);
        }

        public LinkedStackNode(int nodeData, LinkedStackNode next) {
            this.nodeData = nodeData;
            this.next = next;
        }
    }
}