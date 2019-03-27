package com.sismics.sapparot.file;

import com.google.common.io.ByteStreams;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * ZIP utilities.
 * 
 * @author jtremeaux
 */
public class ZipUtil {
    /**
     * Unzip a single file from the archive and returns a temporary file (which the caller must delete after use).
     * If the archive contains several files, take the first one.
     * If the first one is a directory, throw an exception.
     * 
     * @param inputZipFile The zip file
     * @return The unzipped file
     */
    public static File unzipSingleFile(File inputZipFile) {
        try (ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(inputZipFile))) {
            ZipEntry zipEntry = zipInputStream.getNextEntry();
            if (zipEntry.isDirectory()) {
                throw new RuntimeException("The ZIP archive must contain only one file");
            }
            File outputFile = File.createTempFile("file_unzipped", null);
            ByteStreams.copy(zipInputStream, new FileOutputStream(outputFile));

            return outputFile;
        } catch (Exception e) {
            throw new RuntimeException("Error unzipping file", e);
        }
    }
}
