package thinktank.javabot.fileManagement;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 * File Explorer.
 */
public class FileExplorer {
    /**
     * Verifier si le repertoire existe
     * Creer un nouveau sinon.
     *
     * @param directoryPath chemin
     */
    public static final void checkFor(final String directoryPath) {
        File dir = new File(directoryPath);
        if (!dir.exists()) {
            createDirectory(dir);
        }
    }

    /**
     * Creer un nouveau repertoire.
     *
     * @param directory repertoire
     */
    public static final void createDirectory(
            final File directory) {
        directory.mkdir();
    }

    /**
     * Getter liste des fichiers.
     *
     * @param directoryPath chemin
     * @param fileExtension extension
     * @return liste
     */
    public static final List<File> getListFile(final String directoryPath,
                                               final String fileExtension) {
        File dir = new File(directoryPath);
        File[] filesInFolder = dir.listFiles(new FilenameFilter() {
            public boolean accept(final File dir, final String name) {
                return name.toLowerCase().endsWith(fileExtension);
            }
        });
        List<File> files = Arrays.asList(filesInFolder);
        return files;
    }

    /**
     * Sauvegarder le fichier.
     *
     * @param filePath chemin
     * @param newText  contenu
     */
    public static final void saveFile(final String filePath,
                                      final String newText) {
        try (
                Writer writer = new BufferedWriter(
                        new OutputStreamWriter(
                                new FileOutputStream(filePath), "utf-8"))) {
            writer.write(newText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Supprimer un fichier.
     *
     * @param filePath chemin
     */
    public static final void deleteFile(final String filePath) {
        try {
            Files.delete(Paths.get(filePath));
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + "file or directory%n", filePath);
        } catch (DirectoryNotEmptyException x) {
            System.err.format("%s not empty%n", filePath);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    /**
     * Creer un fichier.
     *
     * @param filePath    chemin
     * @param textToWrite contenu
     */
    public static final void createFile(final String filePath,
                                        final String textToWrite) {
        try (
                Writer writer = new BufferedWriter(new OutputStreamWriter(
                        new FileOutputStream(filePath), "utf-8"))) {
            writer.write(textToWrite);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creer un fichier.
     *
     * @param directoryPath chemin
     * @param fileName      nom du fichier
     * @param textToWrite   contenu
     */
    public static final void createFile(final String directoryPath,
                                        final String fileName,
                                        final String textToWrite) {
        createFile(directoryPath + "/" + fileName, textToWrite);
    }

    /**
     * Lire le contenu d'un fichier.
     * @param path chemin
     * @return son contenu
     */
    public static final String readFile(final String path) {
        byte[] encoded = null;
        String fileContent = null;
        try {
            encoded = Files.readAllBytes(Paths.get(path));
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            fileContent = new String(encoded, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return fileContent;
    }
}
