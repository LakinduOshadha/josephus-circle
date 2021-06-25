import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author Lakindu Oshadha (lakinduoshadha98@gmail.com)
 */
public class JosephusCircle {
    /**
     * Implementing Circular linked list
     *
     */
    private Node last;
    private int length;

    /**
     * Creating the inner class for implementing the node.
     */
    private class Node {
        // Implementing local variables
        private int data;
        private Node next;
        private Node previous;

        /**
         * Constructs node
         *
         * @param data Value which should be entered
         */
        public Node(int data){
            this.data = data;
        }
    }

    /**
     * Constructor of JosephusCircle
     *
     */
    public JosephusCircle(){
        this.last = null;
        this.length = 0;
    }

    /**
     * Getter for length
     *
     * @return length of the list
     */
    public int length() {
        return length;
    }

    /**
     * Checks the list is empty or not
     *
     * @return True or false
     */
    public boolean isEmpty() {
        return length == 0;
    }


    /**
     * Inserts a person at the last.
     *
     * @param data The value that should be inserted
     */
    public void insertLast(int data) {
        Node newNode = new Node(data); // Implementing temp Node
        // Setting the value to the last node.
        if(isEmpty()) {
            last = newNode;
            newNode.next = last;
            newNode.previous = last;
        } else {
            newNode.next = last.next;
            newNode.next.previous = newNode;
            last.next = newNode;
            newNode.previous = last;
            last = newNode;

        }
        length++;   // Increasing the length of the list after insertion
    }

    /**
     * Creates the circle to given number of people
     *
     * @param n number of people
     */
    public void createTheCircle(int n) {
        for( int i = 1; i != n + 1; i++) {
            insertLast(i);
        }
    }

    /**
     * Prints the executed people respectively
     *
     * @param m The number used forcounting
     */
    public void printExecuted(int m){

        System.out.print("People who committed suicide : ");
        // Initializing local variables
        int i = 1;
        Node person = last.next;

        // Finds and prints who is executing, respectively
        while(this.length() != 1) {
            if(i % m == 0) {
                System.out.print(person.data + " ");

                // Deleting the executed person
                person.next.previous = person.previous;
                person.previous.next = person.next;
                if(person == last) {
                    last = person.previous;
                }

                // Points person to the next person
                person = person.next;
                length--;   // Decreases length by 1
                i++;
            }
            person = person.next;   // Points person to the next person
            i++;
        }
        System.out.println();
    }

    /**
     * Prints the position to Josephus should be alive
     */
    public void printAlivePosition() {
        if(this.length() == 1) {
            System.out.println("The position to be alive : " + last.data);
        }
    }

    /**
     * Prints the list in forward (created for checking the code)
     */
    public void printForward() {
        // Exit from the method if there is no elements
        if(last == null) {
            return;
        }

        Node temp = last.next;
        //Printing the values
        do{
            System.out.print(temp.data + " ");
            temp = temp.next;
        }while(temp != last.next );
        System.out.println();
    }

    /**
     * Gives the Number of people in th Circle
     * @return the Number of people in th Circle
     * @throws IOException
     */
    public static int getNumberOfPeople() throws IOException {
        System.out.print("Enter the Number of people in th Circle : ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        return n;
    }

    /**
     * Gives the Number used for Counting
     * @return the Number used for Counting
     * @throws IOException
     */
    public static int getNumberForCounting() throws IOException {
        System.out.print("Enter the Number used for Counting : ");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int m = Integer.parseInt(reader.readLine());
        return m;
    }

    /**
     * main
     * @param args
     * @throws IOException
     */
    public static void main(String [] args) throws IOException {
        // Getting user input
        System.out.println("This Program will find the position to be alive for Josephus ! \n");
        int n = getNumberOfPeople();
        int m = getNumberForCounting();

        // Creating the object of the class JosephusCircle
        JosephusCircle circle = new JosephusCircle();
        // Creating the circle
        circle.createTheCircle(n);

        // Prints results
        circle.printExecuted(m);
        circle.printAlivePosition();
    }
}

