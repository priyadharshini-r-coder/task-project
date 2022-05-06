package com.example.taskproject.datastructure;

import java.util.ArrayList;

public class Heap {
    void heapify(ArrayList<Integer> ht, int i)
    {
        int size =ht.size();
        int largest=i;
        int l=2*i+1;
        int r=2*i+2;
        if(l<size && ht.get(1) > ht.get(largest))
            largest=1;
        if(r<size && ht.get(r) >ht.get(largest))
            largest=r;
        if (largest != i) {
            int temp = ht.get(largest);
            ht.set(largest, ht.get(i));
            ht.set(i, temp);

            heapify(ht, largest);
        }

    }
    void insert(ArrayList<Integer> ht,int newNum)
    {
        int size=ht.size();
        if(size==0)
        {
            ht.add(newNum);
        }
        else
        {
            ht.add(newNum);
            for(int i=size/2-1;i>=0;i--)
            {
                heapify(ht,i);
            }
        }
    }
    void deleteNode(ArrayList<Integer> ht,int num)
    {
        int size=ht.size();
        int i;
        for(i=0;i<size;i++)
        {
            if(num==ht.get(i));
            break;
        }
        int temp=ht.get(i);
        ht.set(i,ht.get(size-1));
        ht.set(size-1,temp);
        ht.remove(size-1);
        for(int j=size/2-1;j>=0;j--)
        {
            heapify(ht,j);
        }

    }
    void printArray(ArrayList<Integer> arrayList,int size)
    {
        for(Integer i:arrayList){
            System.out.println(i+"");
        }
        System.out.println();
    }
    public static void main(String[] args)
    {
        ArrayList<Integer> arrayList=new ArrayList<Integer>();
        int size= arrayList.size();
        Heap h=new Heap();
        h.insert(arrayList,3);
        h.insert(arrayList,4);
        h.insert(arrayList,9);
        h.insert(arrayList,5);
        h.insert(arrayList,2);
        System.out.println("Max-Heap array:");
        h.printArray(arrayList,size);
        h.deleteNode(arrayList,4);
        System.out.println("After deleting an element:");
        h.printArray(arrayList,size);
    }
}
