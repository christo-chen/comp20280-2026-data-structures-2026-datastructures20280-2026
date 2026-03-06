package project20280.tree;

import project20280.interfaces.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Concrete implementation of a binary tree using a node-based, linked
 * structure.
 */
public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {

    static java.util.Random rnd = new java.util.Random();
    /**
     * The root of the binary tree
     */
    protected Node<E> root = null; // root of the tree

    // LinkedBinaryTree instance variables
    /**
     * The number of nodes in the binary tree
     */
    private int size = 0; // number of nodes in the tree

    /**
     * Constructs an empty binary tree.
     */
    public LinkedBinaryTree() {
    } // constructs an empty binary tree

    // constructor


    private int heightCallCount = 0; // for Q2

    // Return the height of the entire tree
    public int height() {
        heightCallCount = 0;
        if (isEmpty()) return -1;
        return height(root());
    }

    // increase the call count
    public int height(Position<E> p) {
        heightCallCount++;
        int h = 0;
        for (Position<E> c : children(p)) {
            h = Math.max(h, 1 + height(c));
        }
        return h;
    }

    public int getHeightCallCount() {
        return heightCallCount;
    }


    public static LinkedBinaryTree<Integer> makeRandom(int n) {
        LinkedBinaryTree<Integer> bt = new LinkedBinaryTree<>();
        bt.root = randomTree(null, 1, n);
        return bt;
    }

    // nonpublic utility

    public static <T extends Integer> Node<T> randomTree(Node<T> parent, Integer first, Integer last) {
        if (first > last) return null;
        else {
            Integer treeSize = last - first + 1;
            Integer leftCount = rnd.nextInt(treeSize);
            Integer rightCount = treeSize - leftCount - 1;
            Node<T> root = new Node<T>((T) ((Integer) (first + leftCount)), parent, null, null);
            root.setLeft(randomTree(root, first, first + leftCount - 1));
            root.setRight(randomTree(root, first + leftCount + 1, last));
            return root;
        }
    }

    // accessor methods (not already implemented in AbstractBinaryTree)

    public static void main(String[] args) {
        // test q2
        System.out.println("q2 testing... ");
        LinkedBinaryTree<String> bt1 = new LinkedBinaryTree<>();
        String[] arr = { "A", "B", "C", "D", "E", null, "F", null, null, "G", "H", null, null, null, null };
        bt1.createLevelOrder(arr);
        System.out.println(bt1.toBinaryTreeString());

        // test q3
        System.out.println("\ntesting q3... ");
        Integer[] inorder = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17,
                18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30 };
        Integer[] preorder = { 18, 2, 1, 14, 13, 12, 4, 3, 9, 6, 5, 8, 7, 10, 11, 15, 16,
                17, 28, 23, 19, 22, 20, 21, 24, 27, 26, 25, 29, 30 };

        // build a new integer to test q3
        LinkedBinaryTree<Integer> bt2 = new LinkedBinaryTree<>();
        bt2.construct(inorder, preorder);
        System.out.println(bt2.toBinaryTreeString());

        // test q5
        System.out.println("\ntesting q5... ");

        Integer[] inorder5 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22 };
        Integer[] preorder5 = { 6, 5, 3, 2, 1, 0, 4, 17, 10, 9, 8, 7, 16, 14, 13, 12, 11, 15, 21, 20, 19, 18, 22 };

        LinkedBinaryTree<Integer> bt5 = new LinkedBinaryTree<>();
        bt5.construct(inorder5, preorder5);

        System.out.println(bt5.toBinaryTreeString());

        int result = bt5.diameter();
        System.out.println("The diameter of this tree is: " + result + " (Result should be 13)");


        // test recusion_q9.....
        System.out.println("\ntesting q9...");
        bt1.printLeaves();  // should output [D, G, H, F]
    }


    /**
     * Factory function to create a new node storing element e.
     */
    protected Node<E> createNode(E e, Node<E> parent, Node<E> left, Node<E> right) {
        return new Node<E>(e, parent, left, right);
    }

    /**
     * Verifies that a Position belongs to the appropriate class, and is not one
     * that has been previously removed. Note that our current implementation does
     * not actually verify that the position belongs to this particular list
     * instance.
     *
     * @param p a Position (that should belong to this tree)
     * @return the underlying Node instance for the position
     * @throws IllegalArgumentException if an invalid position is detected
     */
    protected Node<E> validate(Position<E> p) throws IllegalArgumentException {
        if (!(p instanceof Node)) throw new IllegalArgumentException("Not valid position type");
        Node<E> node = (Node<E>) p; // safe cast
        if (node.getParent() == node) // our convention for defunct node
            throw new IllegalArgumentException("p is no longer in the tree");
        return node;
    }

    /**
     * Returns the number of nodes in the tree.
     *
     * @return number of nodes in the tree
     */
    @Override
    public int size() {
        return size;
    }

    /**
     * Returns the root Position of the tree (or null if tree is empty).
     *
     * @return root Position of the tree (or null if tree is empty)
     */
    @Override
    public Position<E> root() {
        return root;
    }

    // update methods supported by this class

    /**
     * Returns the Position of p's parent (or null if p is root).
     *
     * @param p A valid Position within the tree
     * @return Position of p's parent (or null if p is root)
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    @Override
    public Position<E> parent(Position<E> p) throws IllegalArgumentException {
        return validate(p).getParent();
    }

    /**
     * Returns the Position of p's left child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the left child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> left(Position<E> p) throws IllegalArgumentException {
        return validate(p).getLeft();
    }

    /**
     * Returns the Position of p's right child (or null if no child exists).
     *
     * @param p A valid Position within the tree
     * @return the Position of the right child (or null if no child exists)
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     */
    @Override
    public Position<E> right(Position<E> p) throws IllegalArgumentException {
        return validate(p).getRight();
    }

    /**
     * Places element e at the root of an empty tree and returns its new Position.
     *
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalStateException if the tree is not empty
     */
    public Position<E> addRoot(E e) throws IllegalStateException {
        if (!isEmpty()) throw new IllegalStateException("Tree is not empty");
        root = createNode(e, null, null, null);
        size = 1;
        return root;
    }

    public void insert(E e) {
        if (isEmpty()) {
            addRoot(e);
        } else {
            addRecursive(root, e);
        }
    }

    // recursively add Nodes to binary tree in proper position
    private Node<E> addRecursive(Node<E> p, E e) {
        // Simple implementation for BST style insertion if Comparable
        Comparable<E> element = (Comparable<E>) e;
        if (element.compareTo(p.getElement()) < 0) {
            if (p.getLeft() == null) {
                return (Node<E>) addLeft(p, e);
            } else {
                return addRecursive(p.getLeft(), e);
            }
        } else {
            if (p.getRight() == null) {
                return (Node<E>) addRight(p, e);
            } else {
                return addRecursive(p.getRight(), e);
            }
        }
    }

    /**
     * Creates a new left child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the left of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p already has a left child
     */
    public Position<E> addLeft(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getLeft() != null) throw new IllegalArgumentException("p already has a left child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setLeft(child);
        size++;
        return child;
    }

    /**
     * Creates a new right child of Position p storing element e and returns its
     * Position.
     *
     * @param p the Position to the right of which the new element is inserted
     * @param e the new element
     * @return the Position of the new element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p already has a right child
     */
    public Position<E> addRight(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> parent = validate(p);
        if (parent.getRight() != null) throw new IllegalArgumentException("p already has a right child");
        Node<E> child = createNode(e, parent, null, null);
        parent.setRight(child);
        size++;
        return child;
    }

    /**
     * Replaces the element at Position p with element e and returns the replaced
     * element.
     *
     * @param p the relevant Position
     * @param e the new element
     * @return the replaced element
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     */
    public E set(Position<E> p, E e) throws IllegalArgumentException {
        Node<E> node = validate(p);
        E temp = node.getElement();
        node.setElement(e);
        return temp;
    }

    /**
     * Attaches trees t1 and t2, respectively, as the left and right subtree of the
     * leaf Position p. As a side effect, t1 and t2 are set to empty trees.
     *
     * @param p  a leaf of the tree
     * @param t1 an independent tree whose structure becomes the left child of p
     * @param t2 an independent tree whose structure becomes the right child of p
     * @throws IllegalArgumentException if p is not a valid Position for this tree
     * @throws IllegalArgumentException if p is not a leaf
     */
    public void attach(Position<E> p, LinkedBinaryTree<E> t1, LinkedBinaryTree<E> t2) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (isInternal(node)) throw new IllegalArgumentException("p must be a leaf");
        size += t1.size() + t2.size();
        if (!t1.isEmpty()) {
            t1.root.setParent(node);
            node.setLeft(t1.root);
            t1.root = null;
            t1.size = 0;
        }
        if (!t2.isEmpty()) {
            t2.root.setParent(node);
            node.setRight(t2.root);
            t2.root = null;
            t2.size = 0;
        }
    }

    /**
     * Removes the node at Position p and replaces it with its child, if any.
     *
     * @param p the relevant Position
     * @return element that was removed
     * @throws IllegalArgumentException if p is not a valid Position for this tree.
     * @throws IllegalArgumentException if p has two children.
     */
    public E remove(Position<E> p) throws IllegalArgumentException {
        Node<E> node = validate(p);
        if (numChildren(p) == 2) throw new IllegalArgumentException("p has two children");
        Node<E> child = (node.getLeft() != null ? node.getLeft() : node.getRight());
        if (child != null)
            child.setParent(node.getParent()); // child's grandparent becomes its parent
        if (node == root)
            root = child; // child becomes root
        else {
            Node<E> parent = node.getParent();
            if (node == parent.getLeft())
                parent.setLeft(child);
            else
                parent.setRight(child);
        }
        size--;
        E temp = node.getElement();
        node.setElement(null); // help garbage collection
        node.setLeft(null);
        node.setRight(null);
        node.setParent(node); // our convention for defunct node
        return temp;
    }


    // Required for toString() as per Q1(f, g)
    public Iterable<Position<E>> inorder() {
        List<Position<E>> snapshot = new ArrayList<>();
        if (!isEmpty()) inorderSubtree(root(), snapshot);
        return snapshot;
    }

    private void inorderSubtree(Position<E> p, List<Position<E>> snapshot) {
        if (left(p) != null) inorderSubtree(left(p), snapshot);
        snapshot.add(p);
        if (right(p) != null) inorderSubtree(right(p), snapshot);
    }

    @Override
    public Iterable<Position<E>> positions() {
        return inorder(); // Q1(f): positions calls inorder
    }

    public String toString() {
        return positions().toString();
    }


    // q2
    public void createLevelOrder(E[] arr) {
        this.root = null;
        this.size = 0;
        this.root = createLevelOrderHelper(arr, null, 0);
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> parent, int i) {
        if (i < arr.length && arr[i] != null) {
            Node<E> curr = createNode(arr[i], parent, null, null);
            size++;
            curr.setLeft(createLevelOrderHelper(arr, curr, 2 * i + 1));
            curr.setRight(createLevelOrderHelper(arr, curr, 2 * i + 2));
            return curr;
        }
        return null;
    }

    // q3
    public void construct(E[] inorder, E[] preorder) {
        this.root = null;
        this.size = 0;
        this.root = constructHelper(inorder, 0, inorder.length - 1, preorder, 0, preorder.length - 1, null);
    }

    private Node<E> constructHelper(E[] inorder, int inStart, int inEnd,
                                    E[] preorder, int preStart, int preEnd,
                                    Node<E> parent)
    {
        if (inStart > inEnd || preStart > preEnd)
        {
            return null;
        }

        E rootVal = preorder[preStart];

        Node<E> curr = createNode(rootVal, parent, null, null);
        size++;

        int rootIndex = -1;
        for (int i = inStart; i <= inEnd; i++) {
            if (inorder[i].equals(rootVal)) {
                rootIndex = i;
                break;
            }
        }

        int leftTreeSize = rootIndex - inStart;

        curr.setLeft(constructHelper(inorder, inStart, rootIndex - 1,
                preorder, preStart + 1, preStart + leftTreeSize,
                curr));

        curr.setRight(constructHelper(inorder, rootIndex + 1, inEnd,
                preorder, preStart + leftTreeSize + 1, preEnd,
                curr));

        return curr;
    }

    // q4
    public List<List<E>> rootToLeafPaths() {
        List<List<E>> allPaths = new ArrayList<>();
        if (root != null) {
            rootToLeafPathsHelper(root, new ArrayList<>(), allPaths);
        }
        return allPaths;
    }

    private void rootToLeafPathsHelper(Node<E> node, List<E> currentPath, List<List<E>> allPaths) {
        if (node == null) return;

        currentPath.add(node.getElement());

        if (node.getLeft() == null && node.getRight() == null) {
            allPaths.add(new ArrayList<>(currentPath));
        } else {
            rootToLeafPathsHelper(node.getLeft(), currentPath, allPaths);
            rootToLeafPathsHelper(node.getRight(), currentPath, allPaths);
        }

        currentPath.remove(currentPath.size() - 1);
    }

    // q5
    public int diameter() {
        return diameterHelper(root);
    }

    private int diameterHelper(Node<E> node) {
        if (node == null) {
            return 0;
        }

        int lHeight = nodeHeight(node.getLeft());
        int rHeight = nodeHeight(node.getRight());

        int lDiameter = diameterHelper(node.getLeft());
        int rDiameter = diameterHelper(node.getRight());

        int pathThroughNode = lHeight + rHeight + 1;

        return Math.max(pathThroughNode, Math.max(lDiameter, rDiameter));
    }

    // recusion_q9: print all leaf nodes from left to right
    public void printLeaves() {
        List<E> leaves = new ArrayList<>();
        if (!isEmpty()) collectLeaves(root, leaves);
        System.out.println(leaves);
    }

    private void collectLeaves(Node<E> node, List<E> leaves) {
        if (node == null) return;
        if (node.getLeft() == null && node.getRight() == null) {
            leaves.add(node.getElement());
            return;
        }
        collectLeaves(node.getLeft(), leaves);
        collectLeaves(node.getRight(), leaves);
    }


    private int nodeHeight(Node<E> node) {
        if (node == null) {
            return 0;
        }
        return 1 + Math.max(nodeHeight(node.getLeft()), nodeHeight(node.getRight()));
    }


    /**
    public void createLevelOrder(ArrayList<E> l) {
        root = createLevelOrderHelper(l, null, 0);
    }

    private Node<E> createLevelOrderHelper(java.util.ArrayList<E> l, Node<E> p, int i) {
        Node<E> curr = null;
        if (i < l.size() && l.get(i) != null) {
            curr = createNode(l.get(i), p, null, null);
            size++;
            curr.setLeft(createLevelOrderHelper(l, curr, 2 * i + 1));
            curr.setRight(createLevelOrderHelper(l, curr, 2 * i + 2));
        }
        return curr;
    }

    public void createLevelOrder(E[] arr) {
        this.size = 0; // 重置大小
        this.root = createLevelOrderHelper(arr, null, 0); // 必须赋值给 this.root
    }

    private Node<E> createLevelOrderHelper(E[] arr, Node<E> p, int i) {
        if (i < arr.length && arr[i] != null) {
            Node<E> curr = createNode(arr[i], p, null, null);
            size++; // 确保 size 增加，否则 isEmpty() 会返回 true
            curr.setLeft(createLevelOrderHelper(arr, curr, 2 * i + 1));
            curr.setRight(createLevelOrderHelper(arr, curr, 2 * i + 2));
            return curr;
        }
        return null;
    }
    */

    public String toBinaryTreeString() {
        BinaryTreePrinter<E> btp = new BinaryTreePrinter<>(this);
        return btp.print();
    }

    /**
     * Nested static class for a binary tree node.
     */
    public static class Node<E> implements Position<E> {
        private E element;
        private Node<E> left, right, parent;

        public Node(E e, Node<E> p, Node<E> l, Node<E> r) {
            element = e;
            left = l;
            right = r;
            parent = p;
        }

        // accessor
        public E getElement() {
            return element;
        }

        // modifiers
        public void setElement(E e) {
            element = e;
        }

        public Node<E> getLeft() {
            return left;
        }

        public void setLeft(Node<E> n) {
            left = n;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setRight(Node<E> n) {
            right = n;
        }

        public Node<E> getParent() {
            return parent;
        }

        public void setParent(Node<E> n) {
            parent = n;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            if (element == null) {
                sb.append("\u29B0");
            } else {
                sb.append(element);
            }
            return sb.toString();
        }
    }
}