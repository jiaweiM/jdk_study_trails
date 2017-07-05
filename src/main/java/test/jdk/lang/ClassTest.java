/*
 * Copyright 2017 Jiawei Mao
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package test.jdk.lang;

import javafx.scene.control.Button;
import org.junit.jupiter.api.Test;

import java.net.URISyntaxException;
import java.security.CodeSource;
import java.security.ProtectionDomain;

/**
 * @author JiaweiMao
 * @version 1.0.0
 * @date 2016.09.13, 2:48 PM
 */
class ClassTest {

    /**
     * Class.getResourceAsStream() 和 ClassLoader.getResourceAsStream()
     * 都可以从 classpath 下读取资源，差别在于，Class.get... 使用的相对 object 的路径，
     * ClassLoader.get... 使用相对于 classpath 的路径，是绝对路径。
     *
     * 使用Class.getResourceAsStream，资源路径有两种方式：
     * 以 '/' 开头，这样的路径是绝对路径，不以 '/' 开头，就是相对这个Class 所在的包的相对路径。
     *
     * 使用ClassLoader.getResourceAsStream, 资源路径是相对于classpath的绝对路径。
     *
     * 实例：下面三个语句的结果是一样的
     * com.explorers.Test.class.getResourceAsStream("abc.jpg")
     * com.explorers.Test.class.getResourceAsStream("/com/explorers/abc.jpg")
     * ClassLoader.getResourceAsStream("com/explorers/abc.jpg")
     *
     */
    @Test
    void getResourcesAsStream(){

        ClassTest.class.getResourceAsStream("");
    }

    @Test
    void classLoadergetResourceAsStream(){
        ClassTest.class.getClassLoader().getResourceAsStream("");
    }

    @Test
    public void testGetProtectionDomain() throws ClassNotFoundException, URISyntaxException {
        Class cls = getClass();
        ProtectionDomain p = cls.getProtectionDomain();
        CodeSource cs = p.getCodeSource();
        String path = cs.getLocation().toURI().getPath();
        System.out.println(path);
    }

    /**
     * 获得数组的Class类型
     */
    @Test
    public void testGetComponentType(){
        int[] ints = new int[2];
        Button[] buttons = new Button[6];
        String[][] twoDim = new String[4][5];
        String[] two = new String[4];

        printComponentType(ints);
        printComponentType(buttons);
        printComponentType(twoDim);
        printComponentType(two);
    }

    static void printComponentType(Object array) {
        Class arrayClass = array.getClass();
        String arrayName = arrayClass.getName();
        Class componentClass = arrayClass.getComponentType();
        String componentName = componentClass.getName();
        System.out.println("ArrayClass:"+arrayClass+" ,----ArrayName: " + arrayName + ",------- Component: "
                + componentName);
    }
    /**
     * 判断一个类是否是另一个类的父类
     * 是打印true
     * 否打印false
     */
    @Test
    public void testAssignableFrom(){
        System.out.println("String是Object的父类:"+String.class.isAssignableFrom(Object.class));
    }

    /**
     * 判断一个类是否是另一个类的父类
     * 是打印true
     * 否打印false
     */
    @Test
    public void testIsAssignedFrom2() {
        System.out.println("Object是String的父类:"+Object.class.isAssignableFrom(String.class));
    }
    /**
     * 判断一个类是否和另一个类相同
     * 是打印true
     * 否打印false
     */
    @Test
    public void testIsAssignedFrom3() {
        System.out.println("Object和Object相同:"+Object.class.isAssignableFrom(Object.class));
    }

    /**
     * 判断str是否是Object类的实例
     * 是打印true
     * 否打印false
     */
    @Test
    public void testInstanceOf1() {
        String str = new String();
        System.out.print("str是Object的实例:");
        System.out.println(str instanceof Object);
    }
    /**
     * 判断o是否是Object类的实例
     * 是打印true
     * 否打印false
     */
    @Test
    public void testInstanceOf2() {
        Object o = new Object();
        System.out.print("o是Object的实例:");
        System.out.println(o instanceof Object);
    }
}
