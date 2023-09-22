package io.gigabyte.labs.playground.core;

import java.util.Objects;

public class Node<T> {

    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(Node.class);
    private Node<T> left;
    private Node<T> right;
    private T item;

    public Node() {
    }

    public Node(Node<T> left, Node<T> right, T item) {
        this.left = left;
        this.right = right;
        this.item = item;
    }

    public Node<T> getLeft() {
        return left;
    }

    public Node<T> getRight() {
        return right;
    }

    public T getItem() {
        return item;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Node<?> node)) return false;

        if (Objects.nonNull(getLeft()) ? !getLeft().equals(node.getLeft()) : Objects.nonNull(node.getLeft())) return false;
        if (Objects.nonNull(getRight()) ? !getRight().equals(node.getRight()) : Objects.nonNull(node.getRight())) return false;
        return getItem() != null ? getItem().equals(node.getItem()) : node.getItem() == null;
    }

    @Override
    public int hashCode() {
        int result = Objects.nonNull(getLeft()) ? getLeft().hashCode() : 0;
        result = 31 * result + ( Objects.nonNull(getRight())  ? getRight().hashCode() : 0);
        result = 31 * result + (Objects.nonNull(getItem())  ? getItem().hashCode() : 0);
        return result;
    }
}
