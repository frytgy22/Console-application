package org.lebedeva.file;

import lombok.Data;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.stream.Collectors;

/**
 * Class for checking that the file is TXT and returns its text
 */

@Data
public class FileTxt {
    private File file;
    private String name;

    public FileTxt(File file) {
        this.file = file;
        this.name = file.getName();
    }

    public boolean fileCanRead() {
        return file != null && file.exists() && file.canRead() && file.isFile();
    }

    public boolean fileIsTxt() {
        return file.getName().endsWith(".txt");
    }

    public String readFile() throws IOException {
        if (fileCanRead() && fileIsTxt()) {
            return Files.lines(file.toPath()).collect(Collectors.joining("\n"));
        }
        return "";
    }
}
