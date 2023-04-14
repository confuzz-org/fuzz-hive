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

import edu.illinois.confuzz.internal.ConfuzzGenerator;
import org.apache.hadoop.conf.Configuration;

import com.pholser.junit.quickcheck.generator.GenerationStatus;
import com.pholser.junit.quickcheck.generator.Generator;
import com.pholser.junit.quickcheck.random.SourceOfRandomness;

import java.io.IOException;
import java.util.Map;

public class HiveConfGenerator extends Generator<HiveConf> {
    private static HiveConf generatedConf = null;
    /**
     * Constructor for Configuration Generator
     */
    public HiveConfGenerator() throws IOException {
        super(HiveConf.class);
    }

    public static HiveConf getGeneratedConfig() {
        if (generatedConf == null) {
            return null;
        }
        return new HiveConf(generatedConf);
    }

    /**
     * This method is invoked to generate a Configuration object
     * @param random
     * @param generationStatus
     * @return
     */
    @Override
    public HiveConf generate(SourceOfRandomness random, GenerationStatus generationStatus) {
        Configuration conf = new Configuration(true);
        try {
            Map<String,Object> configMap = ConfuzzGenerator.generate(random);
            for (Map.Entry<String,Object> entry: configMap.entrySet()) {
                conf.set(entry.getKey(), String.valueOf(entry.getValue()));
            }
            generatedConf = new HiveConf(conf, HiveConf.class);
            return generatedConf;
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }

}
