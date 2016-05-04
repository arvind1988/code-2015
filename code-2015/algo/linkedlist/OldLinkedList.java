package algo.linkedlist;

public class OldLinkedList {

  private class Node {
    int  key;
    Node next;

    public Node(int key, Node next) {
      this.key = key;
      this.next = next;
    }
  }

  /* public Node sumOfListedListInSameOrder(Node head1, Node head2){ if(head1 ==
   * null){ return head2; } if(head2 == null){ return head1; } int carry = 0;
   * int sum = 0; int countHead1 = getCount(head1); int countHead2 =
   * getCount(head2); if(countHead1 == countHead2){ } } */

  private int carry = 0;

  public Node addSameSizeLists(Node head1, Node head2) {

    if (head1 == null && head2 == null) { return null; }
    Node sumHead = addSameSizeLists(head1.next, head2.next);

    int sum = head1.key + head2.key + carry;
    carry = sum / 10;
    if (sumHead == null) {
      Node sumNode = new Node(sum % 10, null);
      sumHead = sumNode;

    } else {
      Node sumNode = new Node(sum % 10, null);
      sumNode.next = sumHead;
      sumHead = sumNode;
    }

    return sumHead;
  }

  /**
   * This is an incremental approach of reversing K links from x. We reverse the
   * links of one node at a time.
   * 
   * @param x
   * @param K
   */
  public Node reverseKLinks(Node x, int K) {
    if (x == null) return null;
    Node curr = x;
    Node t = curr;
    Node previous = null;
    while(curr != null && K > 0){
      t = curr; // save the curr node pointer
      curr = curr.next; // move the curr to next node for next iteration
      t.next = previous; // break the link of the curr node;
      previous = t;
      K--;
    }
    // First node in the original node is made to point to the node where the
    // last node of the original  list is pointing to. This is done for reverseAllKSublist()
    // to get the starting node for next k node reversal
    x.next = curr;
    return t;
  }
  
  public Node reverseAllKSublist(Node x, int K){
    Node start = x;
    Node head = reverseKLinks(start, K);
    while(start != null){
      start = x.next;
      x.next = reverseKLinks(start, K);
      x = start;
    }
    return head;

  }

  public int getCount(Node list) {

    if (list == null) { return 0; }
    return 1 + getCount(list.next);
  }

  public Node sumOfLinkedList(Node head1, Node head2) {

    if (head1 == null) { return head2; }

    if (head2 == null) { return head1; }
    int carry = 0;
    int sum = 0;
    Node sumListHead = null;
    Node sumListTail = null;
    while (head1 != null || head2 != null) {
      sum = ((head1 != null) ? head1.key : 0) + (head2 != null ? head2.key : 0)
          + carry;
      if (sumListHead != null) {
        sumListTail.next = new Node(sum % 10, null);
        sumListTail = sumListTail.next;
        carry = sum / 10;

      } else {
        sumListHead = new Node(sum % 10, null);
        carry = sum / 10;
        sumListTail = sumListHead;
      }
      if (head1 != null) head1 = head1.next;

      if (head2 != null) head2 = head2.next;
    }
    return sumListHead;
  }

  public Node deleteAlternative(Node head) {

    if (head == null) { return head; }

    int count = 0;
    Node prev = null;
    Node current = head;

    /* In deletion of a node deletion of head node has special treatment */
    while (current != null) {

      if (count % 2 == 1) {
        prev.next = current.next;
        Node temp = current;
        current = current.next;
        temp.next = null;
      } else {
        prev = current;
        current = current.next;
      }
      count += 1;
    }
    return head;
  }

  public Node sortedIntersection(Node head1, Node head2) {

    if (head1 == null || head2 == null) {
      // Intersection a null set with a empty set is a empty set
      return null;
    }

    Node curr_1 = head1;
    Node curr_2 = head2;
    Node iHead = null;
    Node iTail = null;
    while (curr_1 != null && curr_2 != null) {

      // If curr_1.key is less than curr_2.key then curr_1.key cannot be the
      // second list.
      if (curr_1.key < curr_2.key) {
        curr_1 = curr_1.next;

      } else if (curr_2.key < curr_1.key) {
        curr_2 = curr_2.next;
      } else {
        if (iHead != null) {
          Node newNode = new Node(curr_1.key, null);
          iTail.next = newNode;
          iTail = iTail.next;

        } else {
          iHead = new Node(curr_1.key, null);
          iTail = iHead;
          iHead.next = null;
        }
        curr_1 = curr_1.next;
        curr_2 = curr_2.next;
      }
    }
    return iHead;

  }

  /**
   * Reverse a given linked list
   */

  public Node reverseList(Node head) {

    if (head == null) { return null; }

    Node current = head;
    Node previous = null;
    Node temp = head;

    while (current != null) {

      temp = current.next;
      current.next = previous;
      previous = current;
      current = temp;
    }
    return previous;

  }

  /**
   * Reverse a Linked List in groups of given size Given a linked list, write a
   * function to reverse every k nodes (where k is an input to the function).
   * 
   * Example: Inputs: 1->2->3->4->5->6->7->8->NULL and k = 3 Output:
   * 3->2->1->6->5->4->8->7->NULL.
   * 
   * Inputs: 1->2->3->4->5->6->7->80->NULL and k = 5 Output:
   * 5->4->3->2->1->8->7->6->NULL.
   * 
   * @param head
   * @param k
   * @return
   */
  public Node reverseKlengthSubLists(Node head, int k) {

    if (head == null) { return head; }

    Node previous = null;
    Node current = head;
    Node temp = head;

    Node tail = null;
    Node start = current;
    while (current != null) {

      for (int i = k; current != null && i > 0; i--) {
        temp = current.next;
        current.next = previous;
        previous = current;
        current = temp;
      }

      if (tail == null) {
        head = previous;
        tail = start;
      } else {
        tail.next = previous;
        tail = start;
      }
      start = current;
      previous = null;

    }
    return head;
  }

  public Node pairWiseSwap(Node head) {

    if (head == null) { return null; }

    Node p = head;
    Node q = head.next;

    head = q;
    Node tempP = p;
    Node tempQ = q;
    while (p != null && q != null) {
      tempP = p;
      tempQ = q;
      if (p.next == null || q.next == null) {
        p = p.next;
        q = q.next;
      } else {
        p = p.next.next;
        q = q.next.next;
      }

      if (tempQ.next == null || tempQ.next.next == null) {
        tempP.next = tempQ.next;
      } else {
        tempP.next = tempQ.next.next;
      }
      tempQ.next = tempP;
    }

    return head;

  }

  private Node headOfReverseList;

  public Node recursiveReverseList(Node curr) {

    if (curr.next == null) {
      // headOfReverseList = curr;
      return curr;
    }

    Node tail = recursiveReverseList(curr.next);
    Node prev = curr;
    curr.next.next = prev;
    prev.next = null;
    return tail;
    // ;
  }

  public Node reverseListNew(Node x) {
    if (x.next == null) return x;
    Node head = recursiveReverseList(x.next);
    x.next.next = x;
    x.next = null;
    return head;
  }

  /**
   * Append an element
   */
  /**
   * Insert in the head of the list
   * 
   * @param head
   * @param key
   * @param next
   * @return
   */
  public Node insertNodeAtFront(Node head, int key) {

    if (head == null) {
      head = new Node(key, null);
      return head;
    }

    Node n = new Node(key, null);
    n.next = head;
    head = n;
    return head;
  }

  public Node insertNodeEnd(Node head, Node tail, int key) {

    if (head == null) {
      head = new Node(key, null);
      tail = head;
      return head;
    }
    Node n = new Node(key, null);
    head.next = n;
    return head;
  }

  public void printNodes(Node head) {
    Node temp = head;
    while (temp != null) {
      System.out.print(temp.key + " ");
      temp = temp.next;
    }
    System.out.println();
  }

  public void printRecursively(Node head) {

    if (head != null) {
      System.out.print(head.key + " ");
      printRecursively(head.next);
    }
    System.out.println();

  }

  public Node deleteNode(Node head, int k) {
    Node current = head;
    Node previous = null;

    if (head == null) { return head; }
    while (current != null) {
      if (head.key == k) {
        head = head.next;
      } else if (current.key == k) {
        previous.next = current.next;

      }
      previous = current;
      current = current.next;
    }
    return head;
  }

  /**
   * Case 1: delete the first matching key node; Case 2: delete all matching key
   * nodes;
   */
  private Node headList = null;

  public void deleteNodeRecursive(Node current, Node previous, int k) {

    if (current == null) return;
    // we have found the element to be deleted
    if (current.key == k) {
      // If it is the head of the list
      if (previous == null) {
        current = current.next;
        headList = current;
        // return headList;
      } else {
        previous.next = current.next;
      }
    }// else{ // If we call the function from this else part then it will only
     // delete the first occurence of the key in the list

    // }
    previous = current;
    deleteNodeRecursive(current.next, previous, k);
  }

  public int pop(Node head) {
    if (head == null) {
      System.out.println("No items to pop");
      return -1;
    } else {
      int data = head.key;
      head = head.next;
      return data;
    }
  }

  public Node insertAt(Node head, int n, int k) {

    Node tempNode = head;
    if (tempNode == null) { return null; }
    if (n == 0) {
      // need to insert at the head
      Node newNode = new Node(k, null);
      newNode.next = head;
      head = newNode;
      return head;
    }
    int count = 0;
    while (count != n - 1) {
      tempNode = tempNode.next;
      count++;
    }
    Node newNode = new Node(k, null);
    Node temp = tempNode.next;
    tempNode.next = newNode;
    newNode.next = temp;

    return head;
  }

  public Node sortedInsert(Node head, Node newNode) {

    if (newNode == null) return head;
    if (head == null) { return newNode; }

    Node current = head;
    Node previous = null;
    while (current != null && current.key < newNode.key) {
      previous = current;
      current = current.next;
    }

    if (previous == null) {
      newNode.next = current;
      current = newNode;
      return current;
    } else {
      previous.next = newNode;
      newNode.next = current;
      return head;
    }

  }

  public Node append(Node aList, Node bList) {

    if (aList == null && bList == null) {
      return null;
    } else if (aList == null) {
      return bList;
    } else if (bList == null) {
      return aList;
    } else {
      Node lastNode = aList;
      while (lastNode.next != null) {
        lastNode = lastNode.next;
      }
      lastNode.next = bList;
      bList = null;
      return aList;
    }
  }

  private Node frontList = null;
  private Node backList  = null;

  public void frontBackSplit(Node head) {

    if (head == null) {
      System.out.println("The list is null");
      return;
    }

    Node p = head;
    Node q = head;

    if (q.next == null) {
      // the list has only one element

      frontList = head;
      backList = null;
    }

    while (q != null && q.next != null) {
      p = p.next; // the normal slow pointer
      q = q.next.next; // the fast moving the speed of twice as fast as p
                       // pointer.
    }

    // The number of elements are even
    if (q == null) {
      Node temp = head;
      while (temp.next != p) {

        temp = temp.next;
      }
      temp.next = null;
      frontList = head;
      backList = p;
    } else {
      Node temp = head;
      while (temp != p) {
        temp = temp.next;
      }
      p = p.next;
      temp.next = null;
      frontList = head;
      backList = p;
    }

  }

  /**
   * This method would give you the wrong results if the list is not sorted
   * 
   * @param head
   * @return
   */
  /* public Node deleteNodeFromSortedList(Node head){ if(head == null){ return
   * head; } Node temp = head.next; int tempKey = head.key ; LinkedList list =
   * new LinkedList(); while(temp != null){ if(tempKey == temp.key){
   * list.deleteNode(head, temp.key); } } } */

  /**
   * This is a variant on Push(). Instead of creating a new node and pushing it
   * onto the given list, MoveNode() takes two lists, removes the front node
   * from the second list and pushes it onto the front of the first.
   * 
   * @param args
   */
  public Node[] moveNode(Node dest, Node source) {

    if (source == null) {
      System.out.println("Source list can't be null");
      return null;
    }

    // Pop the source list;

    Node sourceNode = source;
    source = source.next;

    // Push this node to the destination

    sourceNode.next = dest;
    dest = sourceNode;

    Node[] lists = new Node[2];
    lists[0] = dest;
    lists[1] = source;
    return lists;
  }

  public Node[] alternativeSplit(Node head) {

    if (head == null) { return null; }

    Node[] heads = new Node[2];
    heads[0] = new Node(-1, null);
    heads[1] = new Node(-1, null);
    int marker = 0;
    Node tail0 = heads[0];
    Node tail1 = heads[1];

    // Dummy nodes

    while (head != null) {
      // We append at the tail of the list

      if (marker == 0) {
        tail0.next = head;
        tail0 = head;
        head = head.next;
        tail0.next = null;
        marker = 1;
      } else {
        tail1.next = head;
        tail1 = head;
        head = head.next;
        tail1.next = null;
        marker = 0;
      }
    }

    return heads;
  }

  public Node mergeLists(Node a, Node b) {

    if (a == null & b == null) { return null; }
    if (a == null) { return b; }
    if (b == null) { return a; }

    Node c = new Node(-1, null);
    Node tail = c;
    while (a != null & b != null) {

      if (a.key <= b.key) {
        tail.next = a;
        tail = a;
        a = a.next;
        tail.next = null;
      } else {
        tail.next = b;
        tail = b;
        b = b.next;
        tail.next = null;
      }
    }
    if (a == null & b == null) {
      return c;
    } else if (a == null) {
      while (b != null) {
        tail.next = b;
        tail = b;
        b = b.next;
        tail.next = null;
      }
      return c;
    } else {
      while (a != null) {
        tail.next = a;
        tail = a;
        a = a.next;
        tail.next = null;
      }
      return c;
    }
  }

  /**
   * Using FrontBackSplit and mergeList functions we can do merge sort very
   * easily.
   * 
   * @param args
   */

  public void mergeSort(Node head) {

  }

  public static void main(String[] args) {

    OldLinkedList list = new OldLinkedList();
    Node head = list.insertNodeAtFront(null, 8);
    head = list.insertNodeAtFront(head, 7);
    head = list.insertNodeAtFront(head, 6);
    head = list.insertNodeAtFront(head, 5);
    head = list.insertNodeAtFront(head, 4);
    head = list.insertNodeAtFront(head, 3);
    head = list.insertNodeAtFront(head, 2);
    head = list.insertNodeAtFront(head, 1);
    
    //list.printRecursively(head);
    head = list.reverseAllKSublist(head, 3);;
    System.out.println();
    list.printRecursively(head);

  }

}
