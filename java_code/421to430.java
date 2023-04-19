/*
// Definition for a Node.
class Node {
    public int val;
    public Node left;
    public Node right;

    public Node() {}

    public Node(int _val,Node _left,Node _right) {
        val = _val;
        left = _left;
        right = _right;
    }
};
*/
class Solution426 {
    private class Pair {
        Node head;
        Node tail;
        Pair(Node head, Node tail) {
            this.head = head;
            this.tail = tail;
        }
    }

    /**
     * 想了超久的divide & conquer
     * Time: O(n)
     * Space: O(1) (not counting recursive stack)
     */
    private Pair treeToDoublyListHelper(Node root) {
        if (root.left == null && root.right == null) {
            root.left = root;
            root.right = root;
            return new Pair(root, root);
        }

        Pair leftPair = null;
        if (root.left != null) {
            leftPair = treeToDoublyListHelper(root.left);
            root.left = leftPair.tail;
            leftPair.tail.right = root;
        }
        Pair rightPair = null;
        if (root.right != null) {
            rightPair = treeToDoublyListHelper(root.right);
            root.right = rightPair.head;
            rightPair.head.left = root;
        }

        Node head = (leftPair == null)? root: leftPair.head;
        Node tail = (rightPair == null)? root: rightPair.tail;

        tail.right = head;
        head.left = tail;
        return new Pair(head, tail);
    }

    public Node treeToDoublyList(Node root) {
        if (root == null) {
            return null;
        }
        Pair headTail = treeToDoublyListHelper(root);
        return headTail.head;
    }
}