package com.xuanqi.he.o2omvp.utils;

import java.io.File;

/**
 * @author Created by He on 2017/5/15.
 * @description 文件工具类
 */

public class FileUtils {

    /**
     * 删除目录下的文件
     *
     * @param file
     */
    public static void deleteAllFiles(File file) {
        if (file.isFile()) {
            file.delete();
            return;
        }
        if (file.isDirectory()) {
            File[] childFile = file.listFiles();
            if (childFile == null || childFile.length == 0) {
                file.delete();
                return;
            }
            for (File f : childFile) {
                deleteAllFiles(f);
            }
            file.delete();
        }
    }
}
