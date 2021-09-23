/**
 * Definition for a Node.
 * class Node {
 *  public int val;
 *  public Node left;
 *  public Node right;
 *  public Node next;
 *
 *  public Node() {}
 *
 *  public Node(int _val,Node _left,Node _right,Node _next) {
 *      val = _val;
 *      left = _left;
 *      right = _right;
 *      next = _next;
 *  }
 *  };
 */
class Solution116 {
    /**
     * BFS
     */
    public Node connect1(Node root) {
        if (root == null)
            return null;

        int depth = 0;
        LinkedList<Node> nodes = new LinkedList<Node>();
        nodes.add(root);

        int cnt = (int) Math.pow(2, depth);
        while (cnt > 0) {
            Node curr = nodes.removeFirst();
            if (curr.left != null) {
                nodes.add(curr.left);
                nodes.add(curr.right);
            }
            cnt--;

            if (cnt == 0) {
                if (nodes.size() == 0)
                    break;
                cnt = (int) Math.pow(2, ++depth);
                for (int i = 0; i < cnt - 1; i++) {
                    Node n = nodes.get(i);
                    n.next = nodes.get(i+1);
                }
            }
        }
        return root;
    }

    /**
     * Recursion
     */
    public Node connect2(Node root) {
        if (root == null)
            return null;

        if (root.left != null) {
            root.left.next = root.right;
            if (root.next != null) {
                root.right.next = root.next.left;
            }
            else {
                root.right.next = null;
            }
        }

        connect(root.left);
        connect(root.right);

        return root;
    }
}