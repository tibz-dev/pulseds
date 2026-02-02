package io.pulseds.structures;

final class AvlNode 
{
    int value;
    int height;
    AvlNode left;
    AvlNode right;

    AvlNode(int value) 
    {
        this.value = value;
        this.height = 1;
    }
}
