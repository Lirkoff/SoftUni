package implementations;

import interfaces.AbstractTree;

import java.util.*;

public class Tree<E> implements AbstractTree<E> {
    private E key;
    private Tree<E> parent;
    private List<Tree<E>> children;

    public Tree(E key, Tree<E>... children) {
        this.key = key;
        this.children = new ArrayList<>();

        for (Tree<E> child : children) {
            this.children.add(child);
            child.parent = this;
        }

    }

    @Override
    public List<E> orderBfs() {
        List<E> result = new ArrayList<>();
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();
            result.add(current.key);

            for (Tree<E> child : current.children) {
                queue.offer(child);
            }

        }
        return result;
    }

    @Override
    public List<E> orderDfs() {
        List<E> order = new ArrayList<>();
        this.dfs(this, order);
        return order;
    }

    private void dfs(Tree<E> tree, List<E> order) {
        for (Tree<E> child : tree.children) {
            this.dfs(child,order);
        }
        order.add(tree.key);
    }

    @Override
    public void addChild(E parentKey, Tree<E> child) {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            if (current.key == parentKey) {
                current.children.add(child);
                break;
            }

            for (Tree<E> ch : current.children) {
                queue.offer(ch);
            }

        }
    }
	
	@Override
    public void removeNode(E nodeKey) {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            if (current.key == nodeKey) {
                current.children.clear();
                current.parent.children.remove(current);
                break;
            }

            for (Tree<E> ch : current.children) {
                queue.offer(ch);
            }

        }
    }

    @Override
    public void swap(E firstKey, E secondKey) {
        Deque<Tree<E>> queue = new ArrayDeque<>();
        queue.offer(this);
        List<Tree<E>> elementsToSwap = new ArrayList<>();

        while (queue.size() > 0) {
            Tree<E> current = queue.poll();

            if (current.key == firstKey) {
                elementsToSwap.add(current);
            } else if (current.key == secondKey) {
                elementsToSwap.add(current);
            }

            if (elementsToSwap.size() == 2) {
                break;
            }

            for (Tree<E> ch : current.children) {
                queue.offer(ch);
            }

        }

        if (!elementsToSwap.isEmpty()) {
            Tree<E> first = elementsToSwap.get(0);
            Tree<E> second = elementsToSwap.get(1);
            Tree<E> secondParent = second.parent;
            Tree<E> firstParent = first.parent;
            List<Tree<E>> firstChildren = first.children;
            List<Tree<E>> secondChildren = second.children;

            first.key = secondKey;
            second.key = firstKey;

            first.parent = secondParent;
            second.parent = firstParent;

            first.children = secondChildren;
            second.children = firstChildren;
        }
    }
}



