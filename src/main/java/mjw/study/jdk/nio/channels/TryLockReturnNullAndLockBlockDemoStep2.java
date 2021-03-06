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

package mjw.study.jdk.nio.channels;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;

/**
 * !!NOTE: before run TryLockReturnNullAndLockBlockDemoStep2, you must run
 * TryLockReturnNullAndLockBlockDemoStep1 first
 *
 * this class is used to test when one JVM is locking a file, what happen when
 * try to call tryLock/Lock method in another JVM
 *
 * following codes show that:
 *
 * 1. when u call tryLock method, the method return (return value is null)
 * immediately
 *
 * 2. when u call Lock method, the method will block until
 * TryLockReturnNullAndLockBlockDemoStep1 release lock (after 30 secconds)
 */
public class TryLockReturnNullAndLockBlockDemoStep2 {
    public static void main(String args[]) {
        try {
            RandomAccessFile r = new RandomAccessFile("E:\\test\\bfile.csv", "rw");
            FileChannel channel = r.getChannel();
            System.out.println("calling tryLock method...");
            FileLock f1 = channel.tryLock();
            System.out.println("tryLock return value is null?  " + (f1 == null));
            System.out.println("calling Lock method... (it will block until TryLockReturnNullAndLockBlockDemoStep1 " +
                            "release lock (after 30 secconds)");
            FileLock f2 = channel.lock();
            System.out.println("lock method is not blocked");
            System.out.println("lock return value is null?  " + (f2 == null));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
