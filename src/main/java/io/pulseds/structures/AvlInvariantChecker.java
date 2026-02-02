package io.pulseds.structures;

final class AvlInvariantChecker {

    static InvariantViolation checkAll(AvlNode root) {
        if (root == null) return null;

        // 1) BST ordering check (strict)
        InvariantViolation bst = checkBst(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
        if (bst != null) return bst;

        // 2) Height correctness + 3) Balance factor check
        return checkHeightAndBalance(root);
    }

    private static InvariantViolation checkBst(AvlNode n, int min, int max) {
        if (n == null) return null;

        if (n.value <= min || n.value >= max) {
            return new InvariantViolation(
                    "BST_ORDER",
                    "Node " + n.value + " violates BST range (" + min + "," + max + ")"
            );
        }

        InvariantViolation left = checkBst(n.left, min, n.value);
        if (left != null) return left;

        return checkBst(n.right, n.value, max);
    }

    private static InvariantViolation checkHeightAndBalance(AvlNode n) {
        if (n == null) return null;

        InvariantViolation left = checkHeightAndBalance(n.left);
        if (left != null) return left;

        InvariantViolation right = checkHeightAndBalance(n.right);
        if (right != null) return right;

        int lh = (n.left == null) ? 0 : n.left.height;
        int rh = (n.right == null) ? 0 : n.right.height;

        int expectedHeight = 1 + Math.max(lh, rh);
        if (n.height != expectedHeight) {
            return new InvariantViolation(
                    "HEIGHT_CORRECTNESS",
                    "Node " + n.value + " has height=" + n.height + " expected=" + expectedHeight +
                            " (lh=" + lh + ", rh=" + rh + ")"
            );
        }

        int bf = lh - rh;
        if (bf < -1 || bf > 1) {
            return new InvariantViolation(
                    "AVL_BALANCE",
                    "Node " + n.value + " has balanceFactor=" + bf + " (lh=" + lh + ", rh=" + rh + ")"
            );
        }

        return null;
    }
}
