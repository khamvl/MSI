package ru.academits.kharitonov.tree_main;

import ru.academits.kharitonov.tree.Tree;

import java.util.function.Consumer;

public class Tree_Main {
    public static void main(String[] args) {
        Tree<Integer> tree = new Tree<>();
        Consumer<Integer> consumer = x -> System.out.print(x + " ");

        tree.add(8);
        tree.add(7);
        tree.add(3);
        tree.add(11);
        tree.add(5);
        tree.add(6);
        tree.add(4);
        tree.add(9);
        tree.add(1);

        System.out.println("Размер дерева: " + tree.getSize());
        System.out.println("------------------------------------");

        if (tree.contains(8)) {
            System.out.println("Дерево содержит элемент");
        } else {
            System.out.println("Дерево не содержит элемент");
        }

        System.out.println("------------------------------------");
        System.out.print("Обход в ширину: ");
        tree.breadthTraversal(consumer);
        System.out.println();

        System.out.println("------------------------------------");
        System.out.print("Обход в глубину: ");
        tree.deepTraversal(consumer);
        System.out.println();

        System.out.println("------------------------------------");
        System.out.print("Обход в глубину рекурсия: ");
        tree.deepTraversalRecursion(consumer);
        System.out.println();

        System.out.println("------------------------------------");

        if (tree.remove(8)) {
            System.out.println("Элемент удален");
        } else {
            System.out.println("Элемент не удален");
        }

        System.out.print("Обход в глубину: ");
        tree.deepTraversal(consumer);
    }
}