package ru.academits.kharitonov.tree;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {
    private Node<T> root;
    private int size;
    private final Comparator<? super T> comparator;

    public Tree(Comparator<T> comparator) {
        this.comparator = Objects.requireNonNullElseGet(comparator, () -> this::compare);
    }

    public Tree() {
        comparator = this::compare;
    }

    private int compare(T o1, T o2) {
        if (o1 == null && o2 == null) {
            return 0;
        }

        if (o1 == null) {
            return -1;
        }

        if (o2 == null) {
            return 1;
        }

        //noinspection unchecked
        return ((Comparable<T>) o1).compareTo(o2);
    }

    private boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public void add(T data) {
        Node<T> addedNode = new Node<>(data);

        if (root == null) {
            root = addedNode;
            size++;

            return;
        }

        Node<T> currentNode = root;

        while (true) {
            if (compare(data, currentNode.getData()) == -1) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();

                    continue;
                }

                currentNode.setLeft(addedNode);
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();

                    continue;
                }

                currentNode.setRight(addedNode);
            }

            size++;
            return;
        }
    }

    public boolean contains(T data) {
        if (isEmpty()) {
            return false;
        }

        Node<T> currentNode = root;

        while (true) {
            int comparatorResult = compare(data,currentNode.getData());

            if (comparatorResult == 0) {
                return true;
            }

            if (comparatorResult < 0) {
                if (currentNode.getLeft() != null) {
                    currentNode = currentNode.getLeft();

                    continue;
                }
            } else {
                if (currentNode.getRight() != null) {
                    currentNode = currentNode.getRight();

                    continue;
                }
            }

            return false;
        }
    }

    public void traverseInWidth(Consumer<T> consumer) {
        if (isEmpty()) {
            return;
        }

        Queue<Node<T>> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node<T> currentNode = queue.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getLeft() != null) {
                queue.add(currentNode.getLeft());
            }

            if (currentNode.getRight() != null) {
                queue.add(currentNode.getRight());
            }
        }
    }

    public void traverseInDepth(Consumer<T> consumer) {
        if (isEmpty()) {
            return;
        }

        Deque<Node<T>> deque = new LinkedList<>();
        deque.push(root);

        while (!deque.isEmpty()) {
            Node<T> currentNode = deque.remove();
            consumer.accept(currentNode.getData());

            if (currentNode.getRight() != null) {
                deque.addFirst(currentNode.getRight());
            }

            if (currentNode.getLeft() != null) {
                deque.addFirst(currentNode.getLeft());
            }
        }
    }

    public void traverseInDepthRecursively(Consumer<T> consumer) {
        traverseInDepthRecursively(root, consumer);
    }

    public void traverseInDepthRecursively(Node<T> currentNode, Consumer<T> consumer) {
        if (currentNode == null) {
            return;
        }

        consumer.accept(currentNode.getData());

        if (currentNode.getLeft() != null) {
            traverseInDepthRecursively(currentNode.getLeft(), consumer);
        }

        if (currentNode.getRight() != null) {
            traverseInDepthRecursively(currentNode.getRight(), consumer);
        }
    }

    public boolean remove(T data) {
        if (isEmpty()) {
            return false;
        }

        Node<T> removedNode = root;
        Node<T> removedNodeParent = null;

        int comparatorResult = compare(data, removedNode.getData());
        boolean isLeftChild = false;

        while (comparatorResult != 0) {
            removedNodeParent = removedNode;

            if (comparatorResult < 0) {
                removedNode = removedNode.getLeft();
                isLeftChild = true;
            } else {
                removedNode = removedNode.getRight();
                isLeftChild = false;
            }

            if (removedNode == null) {
                return false;
            }

            comparatorResult = compare(data, removedNode.getData());
        }

        if (removedNode.getLeft() == null && removedNode.getRight() == null) {
            if (removedNode == root) {
                root = null;
                size--;

                return true;
            }

            if (isLeftChild) {
                removedNodeParent.setLeft(null);
            } else {
                assert removedNodeParent != null;
                removedNodeParent.setRight(null);
            }

            size--;

            return true;
        }

        if (removedNode.getLeft() == null) {
            if (removedNode == root) {
                root = root.getRight();
                size--;

                return true;
            }

            if (isLeftChild) {
                removedNodeParent.setLeft(removedNode.getRight());
            } else {
                assert removedNodeParent != null;
                removedNodeParent.setRight(removedNode.getRight());
            }

            size--;

            return true;
        }

        if (removedNode.getRight() == null) {
            if (removedNode == root) {
                root = root.getLeft();
                size--;

                return true;
            }

            if (isLeftChild) {
                removedNodeParent.setLeft(removedNode.getLeft());
            } else {
                assert removedNodeParent != null;
                removedNodeParent.setRight(removedNode.getLeft());
            }

            size--;

            return true;
        }

        Node<T> removedNodeLeftChild = removedNode.getLeft();
        Node<T> removedNodeRightChild = removedNode.getRight();

        if (removedNodeRightChild.getLeft() == null) {
            if (removedNodeParent == null) {
                root = removedNodeRightChild;
                root.setLeft(removedNodeLeftChild);
                size--;

                return true;
            }

            if (isLeftChild) {
                removedNodeParent.setLeft(removedNodeRightChild);
            } else {
                removedNodeParent.setRight(removedNodeRightChild);
            }

            removedNodeRightChild.setLeft(removedNodeLeftChild);
            size--;

            return true;
        }

        Node<T> smallestLeftNodeParent = null;
        Node<T> smallestLeftNode = removedNodeRightChild;

        while (smallestLeftNode.getLeft() != null) {
            smallestLeftNodeParent = smallestLeftNode;
            smallestLeftNode = smallestLeftNode.getLeft();
        }

        assert smallestLeftNodeParent != null;
        smallestLeftNodeParent.setLeft(smallestLeftNode.getRight());
        smallestLeftNode.setRight(removedNodeRightChild);
        smallestLeftNode.setLeft(removedNodeLeftChild);

        if (removedNodeParent == null) {
            root = smallestLeftNode;
        } else {
            if (isLeftChild) {
                removedNodeParent.setLeft(smallestLeftNode);
            } else {
                removedNodeParent.setRight(smallestLeftNode);
            }
        }

        size--;

        return true;
    }
}