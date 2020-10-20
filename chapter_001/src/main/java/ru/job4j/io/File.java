package ru.job4j.io;

import java.nio.file.Path;
import java.util.Objects;

public class File {
    private Path fileName;
    private Path path;
    private long size;

    public File(Path fileName, Path path, long size) {
        this.fileName = fileName;
        this.path = path;
        this.size = size;
    }

    public Path fileName() {
        return fileName;
    }

    public Path path() {
        return path;
    }

    public long size() {
        return size;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof File)) return false;
        File file = (File) o;
        return size == file.size &&
                Objects.equals(fileName, file.fileName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(fileName, size);
    }

    @Override
    public String toString() {
        return "File{" +
                "fileName=" + fileName +
                ", size=" + size +
                '}';
    }
}
