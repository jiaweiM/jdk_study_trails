/*
 * Copyright 2017 JiaweiMao jiaweiM_philo@hotmail.com
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
package tutorial.nio;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.testng.Assert.*;


/**
 * @author JiaweiMao
 * @date Mar 7, 2016 9:30:04 PM
 */
public class PathTest {

    private Path path;

    @BeforeClass
    public void setUp() {
        path = Paths.get("E:\\FileFormat\\C#\\compressedFile.txt");
    }

    @Test
    public void testGet() {
        Path path = Paths.get("/tmp/foo");
        assertEquals(path.toString(), "\\tmp\\foo");

        Path p2 = Paths.get("D:/", "test", "p.xml");
        assertEquals(p2.toString(), "D:\\test\\p.xml");

        Path path1 = Paths.get("D:/test", ".xml");
        System.out.println(path1);
    }

    @Test
    public void testGetAbs() {
        Path p1 = Paths.get("C:\\Program Files\\Git\\etc\\", "hello.txt");
        assertEquals(p1.toString(), "C:\\Program Files\\Git\\etc\\hello.txt");
        assertEquals(Paths.get("C:\\Program Files", "Git/etc", "hello.txt").toString(), "C:\\Program Files\\Git\\etc\\hello.txt");
        assertEquals(Paths.get("C:\\Program Files", "Git", "etc", "hello.txt").toString(), "C:\\Program Files\\Git\\etc\\hello.txt");
    }

    @Test
    public void testUri2Path() {
        URI uri = URI.create("file:///C:/test/hello.txt");
        Path path = Paths.get(uri);
        assertEquals(path.toString(), "C:\\test\\hello.txt");
    }

    @Test
    public void testGetInfo() {
        Path path = Paths.get("C:\\home\\joe\\foo");
        assertEquals(path.toString(), "C:\\home\\joe\\foo");
        assertEquals(path.getFileName().toString(), "foo");
        assertEquals(path.getName(0).toString(), "home");
        assertEquals(path.getNameCount(), 3);
        assertEquals(path.subpath(0, 2).toString(), "home\\joe");
        assertEquals(path.getParent().toString(), "C:\\home\\joe");
        assertEquals(path.getRoot().toString(), "C:\\");
    }

    @Test
    public void testGetInfoRelative() {
        Path path = Paths.get("sally/bar");
        assertEquals(path.toString(), "sally\\bar");
        assertEquals(path.getFileName().toString(), "bar");
        assertEquals(path.getName(0).toString(), "sally");
        assertEquals(path.getNameCount(), 2);
        assertEquals(path.subpath(0, 2).toString(), "sally\\bar");
        assertEquals(path.subpath(0, 1).toString(), "sally");
        assertEquals(path.getParent().toString(), "sally");
        assertNull(path.getRoot());
    }

    @Test
    public void testToURI() {
        Path path = Paths.get("/home/logfile");
        URI uri = path.toUri();
        assertEquals(uri.toString(), "file:///D:/home/logfile");
    }

    @Test
    public void testtoAbsolutePath() {
        Path path = Paths.get("");
        Path path1 = path.toAbsolutePath();
        assertEquals(path1.toString(), "D:\\code\\tutorials\\jdk-tutorial");
    }

    @Test
    public void testGetFileName() {
        Path namePath = path.getFileName();
        assertEquals("compressedFile.txt", namePath.toString());
    }

    @Test
    public void testGetName() {
        Path name = path.getName(0);
        assertEquals("FileFormat", name.toString());
    }

    @Test
    public void testCtr1() {
        Path path = Paths.get("E:", "FileFormat", "C#", "compressedFile.txt");
        assertEquals("E:\\FileFormat\\C#\\compressedFile.txt", path.toString());
    }

    @Test
    public void testResolve() {
        // define the fixed path
        Path base = Paths.get("E:/FileFormat");
        // resolve 20131216-hela.mzML file
        Path path_1 = base.resolve("20131216-hela.mzML");
        assertEquals("E:\\FileFormat\\20131216-hela.mzML", path_1.toString());
        // resolve 20131216-hela.log.txt file
        Path path_2 = base.resolve("20131216-hela.log.txt");
        assertEquals("E:\\FileFormat\\20131216-hela.log.txt", path_2.toString());
    }

    @Test
    public void testResolve2() {
        Path path = Paths.get("C:\\home\\joe\\foo");
        assertEquals(path.resolve("bar").toString(), "C:\\home\\joe\\foo\\bar");
        assertEquals(Paths.get("foo").resolve("/home/joe").toString(), "\\home\\joe");
    }

    @Test
    public void testResolveSibling() {
        // define the fixed path
        Path base = Paths.get("E:\\FileFormat\\20131216-hela.mzML");
        // resolve sibling 20131216-hela.PSMs.tsv file
        Path path = base.resolveSibling("20131216-hela.PSMs.tsv");
        assertEquals("E:\\FileFormat\\20131216-hela.PSMs.tsv", path.toString());
    }

    @Test
    public void testRelative() {
        Path path01 = Paths.get("BNP.txt");
        Path path02 = Paths.get("AEGON.txt");

        Path path01_to_path02 = path01.relativize(path02);
        assertEquals("..\\AEGON.txt", path01_to_path02.toString());
        Path path02_to_path01 = path02.relativize(path01);
        assertEquals("..\\BNP.txt", path02_to_path01.toString());
    }

    @Test
    public void testRelativize2() {
        Path p1 = Paths.get("zhao");
        Path p2 = Paths.get("qian");

        Path p1_2 = p1.relativize(p2);
        assertEquals(p1_2.toString(), "..\\qian");

        Path p2_1 = p2.relativize(p1);
        assertEquals(p2_1.toString(), "..\\zhao");

        Path p3 = Paths.get("home");
        Path p4 = Paths.get("home/zhao/er");
        assertEquals(p3.relativize(p4).toString(), "zhao\\er");
        assertEquals(p4.relativize(p3).toString(), "..\\..");
    }

    @Test
    public void testRelativize() {
        Path path01 = Paths.get("/tournaments/2009/BNP.txt");
        Path path02 = Paths.get("/tournaments/2011");

        Path path01_to_path02 = path01.relativize(path02);
        assertEquals("..\\..\\2011", path01_to_path02.toString());
        Path path02_to_path01 = path02.relativize(path01);
        assertEquals("..\\2009\\BNP.txt", path02_to_path01.toString());
    }

    @Test
    public void testEquals() {
        Path begin = Paths.get("/home");
        Path end = Paths.get("foo");
        Path path = Paths.get("/home/zhao/foo");
        assertTrue(path.startsWith(begin));
        assertTrue(path.endsWith(end));
    }
}