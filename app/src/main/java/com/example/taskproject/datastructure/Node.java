package com.example.taskproject.datastructure;

/*
Binary Tree
*A binary tree is a tree data structure in which each parent node can have
at most two children.
Each node of a binary tree consists of three items:
data item
address of left child
address of right child
 */
public class Node {
    int key;
    Node left, right;

    public Node(int item) {
        key = item;
        left = right = null;
    }
}
class BinaryTree{
    Node root;
    BinaryTree(int key)
    {
        root=new Node(key);
    }
    BinaryTree()
    {
        root=null;
    }
    public void traverseInOrder(Node node)
    {
        if(node !=null)
        {
            traverseInOrder(node.left);
            System.out.println(""+node.key);
            traverseInOrder(node.right);
        }
    }
    public void transversePostOrder(Node node)
    {
        if(node!=null)
        {
            transversePostOrder(node.left);
            transversePostOrder(node.right);
            System.out.println(""+node.key);
        }
    }
    public void traversePreOrder(Node node)
    {
        if(node!=null)
        {
            System.out.println(""+node.key);
            traversePreOrder(node.left);
            traversePreOrder(node.right);
        }
    }
    public static void main(String[] args)
    {
        BinaryTree tree=new BinaryTree();
        tree.root=new Node(1);
        tree.root.left=new Node(2);
        tree.root.right=new Node(3);
        tree.root.left.left=new Node(4);

        System.out.println("Pre order Traversal:");
        tree.traversePreOrder(tree.root);
        System.out.println("\nIn order Traversal:");
        tree.traverseInOrder(tree.root);
        System.out.println("\nPost order Traversal");
        tree.transversePostOrder(tree.root);
    }
}
/*Applications of Binary tree
* For easy and quick access to data
* In router algorithms
* To implement heap data structure
* Syntax tree
* */

