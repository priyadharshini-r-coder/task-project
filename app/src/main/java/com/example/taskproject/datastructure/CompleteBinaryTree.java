package com.example.taskproject.datastructure;

/*
* A complete binary tree is a binary tree in which all the levels are completely
* filled except possibly the lowest one,which is filled from the left.
* A complete binary tree is a binary tree in which all the levels are completely filled except possibly the lowest one,
* which is filled from the left.
* A complete binary tree is just like a full binary tree,but with two major differences .
* All the leaf elements must lean towards the left.
* The last leaf might not have a right sibling i.e a complete binary tree
* doesn't have to be a full binary tree.*/
public class CompleteBinaryTree {
    int data;
    CompleteBinaryTree left,right;
    CompleteBinaryTree(int item)
    {
        data =item;
        left=right=null;
    }

}
class BinaryTreess{
    CompleteBinaryTree completeBinaryTree;
    int countNumNodes(CompleteBinaryTree tree)
    {
        if(tree==null)
            return (0);
        return (1+countNumNodes(tree.left) +countNumNodes(tree.right));
    }
    boolean checkComplete(CompleteBinaryTree tree,int index,int numberNodes)
    {
        if(tree==null)
            return true;
        if(index >=numberNodes)
            return false;
        return checkComplete(tree.left,2*index+1,numberNodes)
                &&checkComplete(tree.right,2*index+2,numberNodes);


    }
    public static void main(String args[]) {
        BinaryTreess tree = new BinaryTreess();

        tree.completeBinaryTree = new CompleteBinaryTree(1);
        tree.completeBinaryTree.left = new CompleteBinaryTree(2);
        tree.completeBinaryTree.right = new CompleteBinaryTree(3);
        tree.completeBinaryTree.left.right = new CompleteBinaryTree(5);
        tree.completeBinaryTree.left.left = new CompleteBinaryTree(4);
        tree.completeBinaryTree.right.left = new CompleteBinaryTree(6);

        int node_count = tree.countNumNodes(tree.completeBinaryTree);
        int index = 0;

        if (tree.checkComplete(tree.completeBinaryTree, index, node_count))
            System.out.println("The tree is a complete binary tree");
        else
            System.out.println("The tree is not a complete binary tree");
    }

}
/*
* Applications of Complete binary tree
* Heap-based data structures
* Heap sort*/
