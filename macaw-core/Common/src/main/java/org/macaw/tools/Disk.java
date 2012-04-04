/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package org.macaw.tools;

import java.io.File;

/**
 *
 * @author sunye
 */
public class Disk {

    public static File createTempDir(String name) {
        String tempDir = System.getProperty("java.io.tmpdir");
        File newDir = new File(tempDir, name);
        newDir.mkdir();

        return newDir;
    }

    static public void removeDir(File path) {
        if (path == null) {
            return;
        }
        if (path.exists()) {
            for (File f : path.listFiles()) {
                if (f.isDirectory()) {
                    removeDir(f);
                    f.delete();
                } else {
                    f.delete();
                }
            }
            path.delete();
        }
    }

    public static void main(String[] args) {
        File dir = Disk.createTempDir("storage");

        System.out.println(dir.getAbsolutePath());
        
        Disk.removeDir(dir);
    }
}
