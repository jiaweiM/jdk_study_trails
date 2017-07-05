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

package test.jdk.util.concurrent;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author JiaweiMao on 2017.05.25
 * @since 1.0-SNAPSHOT
 */
public class ConnectionManager {

    private static Connection connection = null;

    public static Connection openConnection(){
        if(connection == null)
            connection = DriverManager.getConnection();
    }
}
