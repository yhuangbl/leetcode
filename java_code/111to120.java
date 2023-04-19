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
     * BFS 2
     */
    public Node connect2(Node root) {
        if (root == null) {
            return null;
        }
        int level = 0;
        int curr_level_max = (int) Math.pow(2, level);
        int cnt = 0;
        Node prev =  null;
        Queue<Node> q = new LinkedList<Node>();
        q.add(root);
        while (!q.isEmpty()) {
            Node curr = q.poll();
            if (prev != null) {
                prev.next = curr;
            }
            if (++cnt == curr_level_max) {
                curr.next = null;
                curr_level_max = (int) Math.pow(2, ++level);
                cnt = 0;
                prev = null;
            } else {
              prev = curr;
            }
            if (curr.left != null && curr.right != null) { // it's balanced 
                            q.add(curr.left);
            q.add(curr.right);
            }
        }
        return root;
    }

    /**
     * Recursion
     */
    public Node connect3(Node root) {
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

        connect3(root.left);
        connect3(root.right);

        return root;
    }

    /**
     * Recursion
     */
    public Node connect4(Node root) {
        if (root == null) {
            return null;
        }

        if (root.left != null && root.right != null) { // balanced 
            root.left.next = root.right;
            root.right.next = root.next == null? null: root.next.left;
            connect4(root.left);
            connect4(root.right);
        }
        
        return root;
    }
}