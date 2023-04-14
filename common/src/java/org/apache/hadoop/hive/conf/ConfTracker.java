/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.hadoop.hive.conf;


import edu.illinois.confuzz.internal.ConfigTracker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConfTracker {
    private static final Logger LOG = LoggerFactory.getLogger(ConfTracker.class);
    public static boolean isLogEnabled = Boolean.getBoolean("ctest.log");

    private static String getStackTrace() {
        String stacktrace = " ";
        for (StackTraceElement element : Thread.currentThread().getStackTrace()) {
            if (element.getClassName().contains("Test")) {
                stacktrace = stacktrace.concat(element + "\t");
            }
        }
        return stacktrace;
    }

    public static void trackGet(String name, String value) {
        ConfigTracker.trackGet(name, value);
        if (isLogEnabled) {
            LOG.info(String.format("[CTEST][GET-PARAM] %s = %s, %s", name, value, getStackTrace()));
        }
    }

    public static void trackSet(String name, String value) {
        ConfigTracker.trackSet(name, value);
        if (isLogEnabled) {
            LOG.info(String.format("[CTEST][SET-PARAM] %s = %s, %s", name, value, getStackTrace()));
        }
    }
}
