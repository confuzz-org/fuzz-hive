package org.apache.hadoop.hive.common;

import org.apache.hadoop.hive.conf.HiveConf;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TestDebug {

    @Test
    public void test() throws Exception {
        HiveConf config = new HiveConf();
        int count = 0;
        String name = config.get("myname");

        String age = config.get("myage");
        System.out.println("name: " + name + ", age: " + age);

        String str = config.get("fake-param");
        config.set("fake2", "200");
        config.set("fake3", "300");
        assertEquals("200", config.get("fake2"));

        //System.out.println(str);
        if (str != null) {
            if (str.equals("always")) {
                System.out.println("always");
                count++;
                // This should fail if the fuzzer is not specificed with -DexpectedException=java.lang.AssertionError
                assertEquals("200", config.get("fake3"));
            } else if (str.equals("asneeded")) {
                System.out.println("asneeded");
                config.get("fake-config1", "15");
                count--;
                throw new Exception("Fake Bug asneeded");
            } else {
                System.out.println(str);
            }
        } else {
            System.out.println("str is null!!!");
        }
    }
}