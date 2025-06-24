package com.example.labSystem.utils;

import org.junit.Assert;
import org.junit.Test;


public class IpUtilTest {

    @Test
    public void testIpUtils(){
        var result = IpUtil.isInNetworkIpv4("172.22.236.10","172.22.236.0/24");
        Assert.assertTrue("在统一网络内正确性判断",result);
        var result1 = IpUtil.isInNetworkIpv4("172.22.236.10","172.22.235.0/24");
        Assert.assertFalse("不在统一网络内正确性判断",result1);
    }
}
