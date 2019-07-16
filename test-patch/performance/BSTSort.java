class TreeNode {
    TreeNode(int value) { this.value = value; }

    private int value;
    public final int getValue() { return value; }
    final void setValue(int value) { this.value = value; }

    private TreeNode lChild, rChild;
    final public TreeNode getLChild() { return lChild; }
    final void setLChild(TreeNode value) { lChild = value; }
    final public TreeNode getRChild() { return rChild; }
    final void setRChild(TreeNode value) { rChild = value; }
}

abstract class TreeVisitor {
    abstract void visit(TreeNode node);
}

class BinarySearchTree {
    public BinarySearchTree(Comparer comparer) {
        this.root = null;
        this.comparer = comparer;
    }

    public void insert(int value) {
        TreeNode node = new TreeNode(value);
        if (root == null) {
            root = node;
        }
        else {
            insert(root, node);
        }
    }

    private void insert(TreeNode parent, TreeNode node) {
        if (comparer.compare(node.getValue(), parent.getValue()) <= 0) {
            if (parent.getLChild() == null) {
                parent.setLChild(node);
            }
            else {
                insert(parent.getLChild(), node);
            }
        }
        else {
            if (parent.getRChild() == null) {
                parent.setRChild(node);
            }
            else {
                insert(parent.getRChild(), node);
            }
        }
    }

    public void inorderVisit(TreeVisitor visitor) {
        if (root != null) {
            inorderVisit(root, visitor);
        }
    }

    private void inorderVisit(TreeNode node, TreeVisitor visitor) {
        if (node.getLChild() != null) {
            inorderVisit(node.getLChild(), visitor);
        }
        visitor.visit(node);
        if (node.getRChild() != null) {
            inorderVisit(node.getRChild(), visitor);
        }
    }

    TreeNode getRoot() { return root; }

    private TreeNode root;
    private Comparer comparer;
}

public class BSTSort extends Sorter {
    public String getType() {
        return "BinarySearchTreeSort";
    }

    public void sort(int[] array, Comparer comparer) {
        BinarySearchTree tree = new BinarySearchTree(comparer);
        for (int idx = 0; idx < array.length; ++idx) {
            tree.insert(array[idx]);
        }

        // checker.inorder(tree.getRoot());

        class InsertVisitor extends TreeVisitor {
            private int[] array;
            private int tail;
            InsertVisitor(int[] array) {
                this.array = array;
                this.tail = 0;
            }

            void visit(TreeNode node) {
                // System.out.print("tail = ");
                // System.out.print(tail);
                // System.out.print(" inserting: ");
                // System.out.println(node.getValue());
                array[tail++] = node.getValue();
            }
        }

        TreeVisitor visitor = new InsertVisitor(array);
        tree.inorderVisit(visitor);
    }
}