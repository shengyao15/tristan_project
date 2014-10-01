package com.tristan.encode;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.spec.InvalidKeySpecException;
import java.util.Hashtable;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class BaseAesSecurity {

	private final Logger logger = LoggerFactory.getLogger(BaseAesSecurity.class);
    /* 加解密算法 */
    private static final String ALGORITHM = "DES";

    static {
        // 添加新安全算法
        Security.addProvider(new com.sun.crypto.provider.SunJCE());
    }

    /* 线程安全的密钥map，用于保存处理后的密钥（非原始密钥），一个密钥生成一次即可多次使用  */
    private Hashtable<String, CipherPair> cipherPairMap = new Hashtable<String, CipherPair>();

    /**
     * AES加密
     *
     * @param text 待加密内容
     * @param key  加密密钥
     * @return
     */
    public String encode(String text, String key) {
        CipherPair pair = this.getCipherPair(key);
        if (pair == null) {
            return "";
        }
        Cipher cipher = pair.encodeCipher;

        synchronized (pair) {
            try {
                byte[] buff = text.getBytes("utf-8");
                return new String(Base64.encodeBase64(cipher.doFinal(buff)));
                //return Base64.encodeBytes(cipher.doFinal(buff));
            } catch (IllegalStateException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * AES解密
     *
     * @param text 待解密内容
     * @param key  解密密钥
     * @return
     */
    public String decode(String text, String key) {
    	
//    	String text = URLDecoder.decode(orgText);
//    	logger.info("URLDecode  orgText: {}, text:{}", orgText, text);
    	
        CipherPair pair = this.getCipherPair(key);
        if (pair == null) {
            return "";
        }
        Cipher cipher = pair.decodeCipher;
        byte[] buff = Base64.decodeBase64(text.getBytes());
        //byte[] buff = Base64.decode(text);
        byte[] clearByte;

        synchronized (pair) {
            try {
                clearByte = cipher.doFinal(buff);

                return new String(clearByte, "utf-8");
            } catch (IllegalStateException e) {
                throw new RuntimeException(e);
            } catch (IllegalBlockSizeException e) {
                throw new RuntimeException(e);
            } catch (BadPaddingException e) {
                throw new RuntimeException(e);
            } catch (UnsupportedEncodingException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private CipherPair getCipherPair(String rawKey) {
        CipherPair cipherPair = (CipherPair) cipherPairMap.get(rawKey);

        if (cipherPair == null) {
            CipherPair pair = generateCipher(rawKey);
            cipherPairMap.put(rawKey, pair);

            return pair;
        } else {
            return cipherPair;
        }
    }

    /**
     * 根据密码生成加密和解密器
     *
     * @param
     * @param rawKey
     * @return
     */
    private CipherPair generateCipher(String rawKey) {
        try {
            DESKeySpec dks = new DESKeySpec(rawKey.getBytes());

            SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
            SecretKey deskey = keyFactory.generateSecret(dks);
            Cipher encodeCipher = Cipher.getInstance(ALGORITHM);
            encodeCipher.init(Cipher.ENCRYPT_MODE, deskey);

            Cipher decodeCipher = Cipher.getInstance(ALGORITHM);
            decodeCipher.init(Cipher.DECRYPT_MODE, deskey);

            CipherPair pair = new CipherPair(encodeCipher, decodeCipher);

            return pair;
        } catch (InvalidKeyException e) {
            throw new RuntimeException(e);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        } catch (NoSuchPaddingException e) {
            throw new RuntimeException(e);
        } catch (InvalidKeySpecException e) {
            throw new RuntimeException(e);
        }
    }

    class CipherPair {

        public Cipher encodeCipher;
        public Cipher decodeCipher;

        public CipherPair(Cipher encodeCipher, Cipher decodeCipher) {
            this.encodeCipher = encodeCipher;
            this.decodeCipher = decodeCipher;
        }
    }
    
    public static void main(String[] args) throws UnsupportedEncodingException {
		BaseAesSecurity b = new BaseAesSecurity();
/*		System.out.println(b.encode("3637340020 ", "a2Fi%k6="));
		//System.out.println(b.decode("urep8MyxRmR9t%20Bfe4hQDg==", "a2Fi%k6="));
		//System.out.println(b.decode("m+Exu2lUQwQeUMfmsVao5Q==", "a2Fi%k6="));
		System.out.println(URLDecoder.decode("urep8MyxRmR9t%20Bfe4hQDg=="));
		System.out.println(b.decode("urep8MyxRmR9t+Bfe4hQDg==", "a2Fi%k6="));
		
		System.out.println(b.decode(URLDecoder.decode("urep8MyxRmR9t%2BBfe4hQDg%3D%3D%20"),"a2Fi%k6="));*/
//		System.out.println(b.decode("urep8MyxRmR9t+Bfe4hQDg==", "a2Fi%k6="));
//		System.out.println(b.encode("3637340020", "a2Fi%k6="));
//		
//		System.out.println(b.decode("urep8MyxRmR9t%20Bfe4hQDg==", "a2Fi%k6=" ));
		
		
/*		System.out.println(b.decode(URLDecoder.decode("O6F194GGbbIeUMfmsVao5Q%3D%3D"), "a2Fi%k6=")); 
		System.out.println(b.encode("3637340020", "a2Fi%k6="));   //redirect的时候  可能把中间的+号 替换成 %20 再试试  urep8MyxRmR9t%20Bfe4hQDg==
		System.out.println(b.decode("urep8MyxRmR9t+Bfe4hQDg==", "a2Fi%k6="));   
		//System.out.println(b.decode("urep8MyxRmR9t%20Bfe4hQDg==", "a2Fi%k6=")); 
		System.out.println(URLEncoder.encode("urep8MyxRmR9t+Bfe4hQDg=="));  //urep8MyxRmR9t%2BBfe4hQDg%3D%3D
		System.out.println(b.decode("urep8MyxRmR9t%2BBfe4hQDg%3D%3D", "a2Fi%k6="));   */
		
		//123456898fwefih89   不用URLDecode 也不会报错
		String s1 = "3637340020";
		
		System.out.println(b.encode(s1, "a2Fi%k6="));   //urep8MyxRmR9t+Bfe4hQDg==
		//System.out.println(b.decode("urep8MyxRmR9t+Bfe4hQDg==", "a2Fi%k6="));  //3637340020
		
		System.out.println(URLEncoder.encode(b.encode(s1, "a2Fi%k6="))); //urep8MyxRmR9t%2BBfe4hQDg%3D%3D
		
		
		
		
		
	}
}

