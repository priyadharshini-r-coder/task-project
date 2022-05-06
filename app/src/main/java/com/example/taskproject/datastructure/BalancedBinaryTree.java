package com.example.taskproject.datastructure;

/*
* A balanced  binary tree ,also referred to as height-balanced binary tree,
* is defined as a binary tree in which the height of the left and right subtree
* of any node differ by not more than 1.*/
public class BalancedBinaryTree {
    int data;
    BalancedBinaryTree left, right;

    BalancedBinaryTree(int d) {
        data = d;
        left = right = null;
    }

    // Calculate height
    static class Height {
        int height = 0;
    }
    static class BinaryTrees{
        BalancedBinaryTree balancedBinaryTree;
        boolean checkHeightBalance(BalancedBinaryTree root,Height height)
        {
            if(root==null)
            {
                height.height=0;
                return true;
            }
            Height leftHeighteight =new Height();
            Height rightHeighteight=new Height();
            boolean l=checkHeightBalance(root.left,leftHeighteight);
            boolean r=checkHeightBalance(root.right,rightHeighteight);
            int leftHeight= leftHeighteight.height;
            int rightHeight=rightHeighteight.height;
            height.height=leftHeight>rightHeight?leftHeight:rightHeight+1;
         if((leftHeight-rightHeight>=2) || (rightHeight-leftHeight>=2))
         {
             return false;
         }
         else
         {
             return l && r;

         }

        }


    }
    public static void main(String[] args)
    {
     Height height=new Height();
        BinaryTrees tree = new BinaryTrees();
        tree.balancedBinaryTree = new BalancedBinaryTree(1);
        tree.balancedBinaryTree.left = new BalancedBinaryTree(2);
        tree.balancedBinaryTree.right = new BalancedBinaryTree(3);
        tree.balancedBinaryTree.left.left = new BalancedBinaryTree(4);
        tree.balancedBinaryTree.left.right = new BalancedBinaryTree(5);

        if (tree.checkHeightBalance(tree.balancedBinaryTree, height))
            System.out.println("The tree is balanced");
        else
            System.out.println("The tree is not balanced");
    }

    }


