package com.aleksandrphilimonov.service;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class DigestService {
    public String hash(String str) {
        return md5Hex(str);
    }
}
