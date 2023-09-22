package io.gigabyte.labs.playground.core;

import java.util.Objects;

public class TreeUtils {
    private static final org.slf4j.Logger log = org.slf4j.LoggerFactory.getLogger(TreeUtils.class);

    public static <T> int countNodes(Node<T> root) {
        if (Objects.isNull(root)) {
            return 0;
        } else {
            int count = 1;
            count += countNodes(root.getLeft());
            count += countNodes(root.getRight());
            return count;
        }
    }

}
