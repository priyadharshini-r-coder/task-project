package com.example.taskproject.datastructure;


/*
* Full Binary Tree
* A full binary tree is a special type of binary tree in which every parent node/internal node
* has either two or no children.
* proper binary tree
* */
public class Nodes {
    int data;
    Nodes leftChild,rightChild;

    Nodes(int item)
    {
        data=item;
        leftChild=rightChild=null;
    }
}
class BinaryTrees{
    Nodes root;
    boolean isFullBinaryTree(Nodes node)
    {
        if(node==null)
        {
            return true;
        }
        if(node.leftChild==null && node.rightChild==null)
        {
            return true;
        }
        if(node.leftChild!=null && node.rightChild!=null)
            return isFullBinaryTree(node.leftChild) && isFullBinaryTree(node.rightChild);
        return false;
    }
    public static void main(String args[])
    {
        BinaryTrees tree=new BinaryTrees();
        tree.root=new Nodes(1);
        tree.root.leftChild=new Nodes(2);
        tree.root.rightChild=new Nodes(3);
        tree.root.leftChild.leftChild=new Nodes(4);
        tree.root.leftChild.rightChild=new Nodes(5);
        tree.root.rightChild.rightChild=new Nodes(6);
        tree.root.rightChild.leftChild=new Nodes(7);

        if (tree.isFullBinaryTree(tree.root))
            System.out.print("The tree is a full binary tree");
        else
            System.out.print("The tree is not a full binary tree");

    }
}
