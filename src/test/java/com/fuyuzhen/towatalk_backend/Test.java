package com.fuyuzhen.towatalk_backend;

import com.fuyuzhen.towatalk_backend.test.DynamicProxyTestAutoBoxing;
import com.fuyuzhen.towatalk_backend.test.ITestAutoBoxing;
import com.fuyuzhen.towatalk_backend.test.TestAutoBoxing;

/**
 * @author ：Negen
 * @Date ：Created in 16:08 2020/4/16
 * @Description：
 * @Modified By：
 * @Version: 1.0
 */
public class Test{
    public static void main(String[] args) {
        ITestAutoBoxing testAutoBoxing = (ITestAutoBoxing)new DynamicProxyTestAutoBoxing().bind(new TestAutoBoxing());
        testAutoBoxing.LongSum();
        testAutoBoxing.longSum();
    }

}
