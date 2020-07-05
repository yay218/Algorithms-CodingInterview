/*
Serialize and Deserialize Binary Tree
Leetcode #: 297
Lintcode #: 7

Serialization is the process of converting a data structure 
or object into a sequence of bits so that it can be stored 
in a file or memory buffer, or transmitted across a network 
connection link to be reconstructed later in the same or 
another computer environment.

Design an algorithm to serialize and deserialize a binary 
tree. There is no restriction on how your serialization/
deserialization algorithm should work. You just need to 
ensure that a binary tree can be serialized to a string and 
this string can be deserialized to the original tree structure.

Input: [3,9,20,#,#,15,7]
Output: {3,9,20,#,#,15,7}
*/

/**
 * Definition of TreeNode:
 * public class TreeNode {
 *     public int val;
 *     public TreeNode left, right;
 *     public TreeNode(int val) {
 *         this.val = val;
 *         this.left = this.right = null;
 *     }
 * }
 */


public class 2_SerializeAndDeserializeBinaryTree {
    /**
     * This method will be invoked first, you should design your own algorithm 
     * to serialize a binary tree which denote by a root node to a string which
     * can be easily deserialized by your own "deserialize" method later.
     */
    public String serialize(TreeNode root) {
        // write your code here
        if (root == null) {
            return "{}";
        }
        
        List<TreeNode> queue = new ArrayList();
        queue.add(root);
        
        for (int i = 0; i < queue.size(); i++) {
            TreeNode currNode = queue.get(i);
            if (currNode == null) {
                continue;
            }
            queue.add(currNode.left);
            queue.add(currNode.right);
        }
        
        while (queue.get(queue.size() - 1) == null) {
            queue.remove(queue.size() - 1);
        }
        
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        sb.append(queue.get(0).val);
        for (int i = 1; i < queue.size(); i++) {
            if (queue.get(i) == null) {
                sb.append(",#");
            } else {
                sb.append(",");
                sb.append(queue.get(i).val);
            }
        }
        sb.append("}");
        return sb.toString();
    }

    /**
     * This method will be invoked second, the argument data is what exactly
     * you serialized at method "serialize", that means the data is not given by
     * system, it's given by your own serialize method. So the format of data is
     * designed by yourself, and deserialize it here as you serialize it in 
     * "serialize" method.
     */
    public TreeNode deserialize(String data) {
        // write your code here
        if (data.equals("{}")) {
            return null;
        }
        
        String[] vals = data.substring(1, data.length() - 1).split(",");
        List<TreeNode> queue = new ArrayList();
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        queue.add(root);
        
        int index = 0;
        boolean isLeft = true;
        for (int i = 1; i < vals.length; i++) {
            if (!vals[i].equals("#")) {
                TreeNode node = new TreeNode(Integer.parseInt(vals[i]));
                if (isLeft) {
                    queue.get(index).left = node;
                } else {
                    queue.get(index).right = node;
                }
                queue.add(node);
            }
            if (!isLeft) {
                index++;
            }
            isLeft = !isLeft;
        }
        return root;
    }
}

