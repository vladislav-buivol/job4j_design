package ru.job4j.ood.isp;

import java.util.Iterator;

interface GraphTraversable<T> {
    Iterator<T> bfs();
    Iterator<T> dfs();
}