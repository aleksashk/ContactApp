package com.aleksandrphilimonov;

import static org.apache.commons.codec.digest.DigestUtils.md5Hex;

public class DigestService {
    public String digest(String password){return md5Hex(password);}
}
