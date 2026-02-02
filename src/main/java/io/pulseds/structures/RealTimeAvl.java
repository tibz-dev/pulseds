package io.pulseds.structures;

import io.pulseds.events.DsEvent;
import io.pulseds.events.EventSink;
import io.pulseds.events.EventType;

public class RealTimeAvl 
{

    private AvlNode root;
    private final EventSink sink;

    public RealTimeAvl(EventSink sink) {
        this.sink = sink;
    }

    /* ---------- Public API ---------- */

   public synchronized void insert(int value) 
   {
    root = insert(root, value);
    verifyInvariants();
    emit(EventType.INSERT, "Inserted " + value);
    }


    /* ---------- Internal Logic ---------- */

    private AvlNode insert(AvlNode node, int value) {
        if (node == null) {
            emit(EventType.NODE_CREATE, "Created node " + value);
            return new AvlNode(value);
        }

        if (value < node.value) {
            node.left = insert(node.left, value);
        } else if (value > node.value) {
            node.right = insert(node.right, value);
        } else {
            return node; // no duplicates
        }

        updateHeight(node);
        return rebalance(node);
    }

    /* ---------- AVL Mechanics ---------- */

    private int height(AvlNode n) {
        return n == null ? 0 : n.height;
    }

    private void updateHeight(AvlNode n) {
        n.height = 1 + Math.max(height(n.left), height(n.right));
    }

    private int balanceFactor(AvlNode n) {
        return height(n.left) - height(n.right);
    }

    private AvlNode rebalance(AvlNode n) {
        int bf = balanceFactor(n);

        if (bf > 1) {
            if (balanceFactor(n.left) < 0) {
                emit(EventType.ROTATE_L, "Left rotation at " + n.left.value);
                n.left = rotateLeft(n.left);
            }
            emit(EventType.ROTATE_R, "Right rotation at " + n.value);
            return rotateRight(n);
        }

        if (bf < -1) {
            if (balanceFactor(n.right) > 0) {
                emit(EventType.ROTATE_R, "Right rotation at " + n.right.value);
                n.right = rotateRight(n.right);
            }
            emit(EventType.ROTATE_L, "Left rotation at " + n.value);
            return rotateLeft(n);
        }

        return n;
    }

    private AvlNode rotateRight(AvlNode y) {
        AvlNode x = y.left;
        AvlNode t2 = x.right;

        x.right = y;
        y.left = t2;

        updateHeight(y);
        updateHeight(x);

        return x;
    }

    private AvlNode rotateLeft(AvlNode x) {
        AvlNode y = x.right;
        AvlNode t2 = y.left;

        y.left = x;
        x.right = t2;

        updateHeight(x);
        updateHeight(y);

        return y;
    }

    /* ---------- Events ---------- */

    private void emit(EventType type, String msg) {
        sink.publish(new DsEvent(
                System.currentTimeMillis(),
                Thread.currentThread().getId(),
                "AVL",
                type,
                msg
        ));
    }

    private void verifyInvariants() 
    {
    InvariantViolation v = AvlInvariantChecker.checkAll(root);
    if (v != null) 
    {
        emit(EventType.VIOLATION, v.rule() + " | " + v.message());
    }
    }

}
