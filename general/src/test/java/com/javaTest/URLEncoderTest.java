package com.javaTest;

import org.testng.annotations.Test;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 *
 */
public class URLEncoderTest {

    @Test
    public void test() throws UnsupportedEncodingException {
        String text = "vpmnPhU0GAGjfaiDPToW5gTsPtv3HRAxGEcF9ExMhdE=";

        String encode = URLEncoder.encode(text, "UTF-8");
        String decode = URLDecoder.decode("vpmnPhU0GAGjfaiDPToW5gTsPtv3HRAxGEcF9ExMhdE=", "UTF-8");

        System.out.println(text);
        System.out.println(encode);
        System.out.println(decode);
    }
}
