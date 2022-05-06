package com.example.taskproject.datastructure;

public class PerfectBinarytree {
    static class Node{
        int key;
        Node left,right;
    }
    //calculate the depth
    static int depth(Node node)
    {
        int d=0;
        while(node!=null)
        {
            d++;
            node=node.left;
        }
        return d;
    }
    static boolean is_perfect(Node root,int d,int level)
    {
        if(root==null)
        {
            return true;
        }
        //if for children
        if(root.left==null || root.right==null)
        {
            return false;
        }
        return is_perfect(root.left,d,level+1) && is_perfect(root.right,d,level+1);
    }
    static boolean is_Perfect(Node root) {
        int d = depth(root);
        return is_perfect(root, d, 0);
    }
    static Node newNode(int k)
    {
        Node node=new Node();
        node.key=k;
        node.right=null;
        node.left=null;
        return node;
    }
    public static void main(String args[])
    {
        Node root=null;
        root=newNode(1);
        root.left=newNode(2);
        root.right=newNode(3);
        root.left.left=newNode(4);
        root.left.right=newNode(5);
        if(is_Perfect(root))
        {
            System.out.println("The tree is a perfect binary tree");

        }
        else
        {
            System.out.println("The tree is not a perfect binary tree");
        }
    }
}
