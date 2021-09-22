package com.juc_demo.jvm;


import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 * @author : liubin
 * @date : 2021/7/28 16:01
 */
public class MetaSpaceOOM extends ClassLoader {
    public static void main(String[] args) {
        int j = 0;
        try {
            MetaSpaceOOM metaSpaceooM = new MetaSpaceOOM();
            for (int i = 0; i < 10000; i++) {
                ClassWriter classWriter = new ClassWriter(0);
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                byte[] bytes = classWriter.toByteArray();
                metaSpaceooM.defineClass("Class" + i, bytes, 0, bytes.length);
                j ++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
