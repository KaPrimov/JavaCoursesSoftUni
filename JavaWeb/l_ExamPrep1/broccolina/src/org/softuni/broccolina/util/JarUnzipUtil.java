package org.softuni.broccolina.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class JarUnzipUtil {
    public void unzipJar(String jarCanonicalPath) throws IOException {
        JarFile jar = new JarFile(new File(jarCanonicalPath));
        File outDirectiory = new File(jarCanonicalPath.replace(".jar", ""));

        if(outDirectiory.exists()) outDirectiory.delete();
        outDirectiory.mkdir();

        Enumeration<JarEntry> entries = jar.entries();

        while(entries.hasMoreElements()) {
            JarEntry currentEntry = entries.nextElement();

            File currentFile = new File(
                    outDirectiory.getCanonicalPath()
                    + File.separator
                    + currentEntry.getName());

            if(currentEntry.isDirectory()) {
                currentFile.mkdir();
                continue;
            }

            InputStream inputStream = jar.getInputStream(currentEntry);
            FileOutputStream outputStream = new FileOutputStream(currentFile);

            while(inputStream.available() > 0) {
                outputStream.write(inputStream.read());
            }

            outputStream.close();
            inputStream.close();
        }

        jar.close();
    }
}
