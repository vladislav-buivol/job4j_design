package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {
    private final File root;
    private final File target;
    private final String exclude;

    public void packFiles(List<Path> sources, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : sources) {
                zip.putNextEntry(new ZipEntry(convertFilePathForZip(path)));
                try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(out.readAllBytes());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String convertFilePathForZip(Path path) {
        return path.toFile().getPath().replace(root.getPath(), "").substring(1);
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getName()));
            try (BufferedInputStream out = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(out.readAllBytes());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private List<Path> filesToPack() throws IOException {
        return Search.search(root.toPath(), path -> !path.getFileName().toString().endsWith(exclude));
    }

    public Zip(ArgZip args) {
        this.root = new File(args.directory());
        if (!root.exists()) {
            throw new IllegalArgumentException(String.format("File not exist: %s", root.getPath()));
        }
        this.target = new File(args.output());
        this.exclude = args.exclude();
    }

    public static void main(String[] args) {
        Zip zip = new Zip(new ArgZip(args));
        try {
            zip.packFiles(zip.filesToPack(), zip.target);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}