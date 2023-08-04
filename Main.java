import java.util.*;

public class Main {
    static Scanner sc = new Scanner(System.in);
    static Queue messageQueue = new Queue(10);
    static Stack messageStack = new Stack(10);

    public static void main(String[] args) throws Exception {
        int choice = 0;
        do {
            menu();
            choice = SelectChoice();
            sc.nextLine();
            switch (choice) {
                case 1:
                    System.out.print("Input the person send this message: ");
                    String person = sc.nextLine();
                    System.out.print("Input the message: ");
                    String message = sc.nextLine();
                    if (message.length() > 250) {
                        System.out.println("It's out of 250 characters!");
                        break;
                    }
                    Message mess = new Message(person, message);
                    messageQueue.enqueue(mess);
                    break;
                case 2:
                    sendMessage();
                    break;
                case 3:
                    viewMessages();
                    break;
                case 4:
                    System.out.println("Thank for using this system!");
                    return;
                default:
                    System.out.println("Please input the correct number of function!");
                    break;
            }
        } while (choice != 4);
    }

    public static void menu() {
        System.out.println("--------------------------");
        System.out.println("1. Input Message");
        System.out.println("2. Send Message");
        System.out.println("3. Received message");
        System.out.println("4. Exit");
        System.out.println("");
    }

    public static int SelectChoice() {
        boolean check = true;
        int choice = 0;
        do {
            System.out.print("Enter your choice: ");
            try {
                choice = sc.nextInt();
                check = false;
            } catch (InputMismatchException e) {
                System.out.println("please enter the number of the function!");
                sc.nextLine();
            }
        } while (check);
        return choice;
    }

    public static void sendMessage() {
        if (messageQueue.isEmpty()) {
            System.out.println("No message to send.");
        } else {
            System.out.println("Sending messages...");
            while (!messageQueue.isEmpty()) {
                Message mess = messageQueue.dequeue();
                messageStack.push(mess);
                System.out.println("Message sent: " + mess.getPerson() + ": " + mess.getMessage());
            }
        }
    }

    public static void viewMessages() {
        if (messageStack.isEmpty()) {
            System.out.println("No message received.");
        } else {
            System.out.println("Received messages:");
            while (!messageStack.isEmpty()) {
                Message mess = messageStack.pop();
                System.out.println(mess.getPerson() + ": " + mess.getMessage());
            }
        }
    }
}

class Queue {
    private int maxSize;
    private int front;
    private int rear;
    private int nItems;
    private Message[] queueArray;

    public Queue(int size) {
        maxSize = size;
        queueArray = new Message[maxSize];
        front = 0;
        rear = -1;
        nItems = 0;
    }

    public boolean isEmpty() {
        return (nItems == 0);
    }

    public boolean isFull() {
        return (nItems == maxSize);
    }

    public int getSize() {
        return nItems;
    }

    public void enqueue(Message m) {
        if (isFull()) {
            System.out.println("Queue is full, cannot enqueue.");
        } else {
            if (rear == maxSize - 1) {
                rear = -1;
            }
            queueArray[++rear] = m;
            nItems++;
        }
    }

    public Message dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty, cannot dequeue.");
            return null;
        } else {
            Message temp = queueArray[front++];
            if (front == maxSize) {
                front = 0;
            }
            nItems--;
            return temp;
        }
    }
}

class Stack {
    private int maxSize;
    private int top;
    private Message[] stackArray;

    public Stack(int size) {
        maxSize = size;
        stackArray = new Message[maxSize];
        top = -1;
    }

    public boolean isEmpty() {
        return (top == -1);
    }

    public boolean isFull() {
        return (top == maxSize - 1);
    }

    public int getSize() {
        return top + 1;
    }

    public void push(Message m) {
        if (isFull()) {
            System.out.println("Stack is full, cannot push.");
        } else {
            stackArray[++top] = m;
        }
    }

    public Message pop() {
        if (isEmpty()) {
            System.out.println("Stack is empty, cannot pop.");
            return null;
        } else {
            return stackArray[top--];
        }
    }
}

class Message {
    private String person;
    private String message;

    public Message(String person, String message) {
        this.person = person;
        this.message = message;
    }

    public String getPerson() {
        return person;
    }

    public String getMessage() {
        return message;
    }
}
