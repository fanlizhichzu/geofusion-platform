package io.github.fanlizhichzu.common.utils.gm;

import org.bouncycastle.crypto.AsymmetricCipherKeyPair;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ECPublicKeyParameters;
import org.bouncycastle.math.ec.ECPoint;

import java.io.IOException;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.Map;

public class SM2Utils {

    /**
     * 定义公钥算法
     */
    private final static String KEY_PUBLICKEY = "PublicKey";
    /**
     * 定义私钥算法
     */
    private final static String KEY_PRIVATEKEY = "PrivateKey";

    private  static SM2 sm2Instance;

    public static void setSm2Instance(){
        sm2Instance=SM2.Instance();
    }

    public final static String prik = "3690655E33D5EA3D9A4AE1A1ADD766FDEA045CDEAA43A9206FB8C430CEFE0D94";
    // 国密规范正式公钥
    public final static  String pubk = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";

    // 生成随机秘钥对
    public static Map<String, String> generateKeyPair() {
        if(keys==null) {
            Map<String, String> map = null;
            if (sm2Instance == null)
                sm2Instance = SM2.Instance();
            SM2 sm2 = sm2Instance;
            AsymmetricCipherKeyPair key = sm2.ecc_key_pair_generator
                    .generateKeyPair();
            ECPrivateKeyParameters ecpriv = (ECPrivateKeyParameters) key
                    .getPrivate();
            ECPublicKeyParameters ecpub = (ECPublicKeyParameters) key.getPublic();
            BigInteger privateKey = ecpriv.getD();
            ECPoint publicKey = ecpub.getQ();

            // 将密钥封装为map
            map = new HashMap<String, String>(2);
            map.put(KEY_PUBLICKEY, Util.byteToHex(publicKey.getEncoded()));
            map.put(KEY_PRIVATEKEY, Util.byteToHex(privateKey.toByteArray()));

            //System.out.println("公钥: " + Util.byteToHex(publicKey.getEncoded()));
            //System.out.println("私钥: " + Util.byteToHex(privateKey.toByteArray()));
            keys=map;
            return  map;
        }else{
            return  keys;
        }
    }


    public static  Map<String,String> keys;
    /**
     * 获取公钥
     *
     * @param map
     * @return
     */
    public static String getPublicKey(Map<String, String> map) {
        return map.get(KEY_PUBLICKEY);
    }

    /**
     * 获取私钥
     *
     * @param map
     * @return
     */
    public static String getPrivateKey(Map<String, String> map) {
        return map.get(KEY_PRIVATEKEY);
    }

    public static String encrypt(String publicKey, String data) {
        try {
            byte[] bytesPublicKey = Util.hexToByte(publicKey);
            byte[] bytesData = data.getBytes();
            return encrypt(bytesPublicKey, bytesData);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // 数据加密
    public static String encrypt(byte[] publicKey, byte[] data)
            throws IOException {
        if (publicKey == null || publicKey.length == 0) {
            return null;
        }

        if (data == null || data.length == 0) {
            return null;
        }

        byte[] source = new byte[data.length];
        System.arraycopy(data, 0, source, 0, data.length);

        Cipher cipher = new Cipher();
        SM2 sm2 = SM2.Instance();
        ECPoint userKey = sm2.ecc_curve.decodePoint(publicKey);

        ECPoint c1 = cipher.Init_enc(sm2, userKey);
        cipher.Encrypt(source);
        byte[] c3 = new byte[32];
        cipher.Dofinal(c3);

        // System.out.println("C1 " + Util.byteToHex(c1.getEncoded()));
        // System.out.println("C2 " + Util.byteToHex(source));
        // System.out.println("C3 " + Util.byteToHex(c3));
        // C1 C2 C3拼装成加密字串
        return Util.byteToHex(c1.getEncoded()) + Util.byteToHex(source)
                + Util.byteToHex(c3);

    }

    public static byte[] decrypt(String privateKey, String encryptedData) {
        try {
            byte[] bytesPrivateKey = Util.hexToByte(privateKey);
            byte[] bytesEncryptedData = Util.hexToByte(encryptedData);
            return decrypt(bytesPrivateKey, bytesEncryptedData);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    // 数据解密
    public static byte[] decrypt(byte[] privateKey, byte[] encryptedData)
            throws IOException {
        if (privateKey == null || privateKey.length == 0) {
            return null;
        }

        if (encryptedData == null || encryptedData.length == 0) {
            return null;
        }
        // 加密字节数组转换为十六进制的字符串 长度变为encryptedData.length * 2
        String data = Util.byteToHex(encryptedData);
        /***
         * 分解加密字串 （C1 = C1标志位2位 + C1实体部分128位 = 130） （C3 = C3实体部分64位 = 64） （C2 =
         * encryptedData.length * 2 - C1长度 - C2长度）
         */
        byte[] c1Bytes = Util.hexToByte(data.substring(0, 130));
        int c2Len = encryptedData.length - 97;
        byte[] c2 = Util.hexToByte(data.substring(130, 130 + 2 * c2Len));
        byte[] c3 = Util
                .hexToByte(data.substring(130 + 2 * c2Len, 194 + 2 * c2Len));

        SM2 sm2 = SM2.Instance();
        BigInteger userD = new BigInteger(1, privateKey);

        // 通过C1实体字节来生成ECPoint
        ECPoint c1 = sm2.ecc_curve.decodePoint(c1Bytes);
        Cipher cipher = new Cipher();
        cipher.Init_dec(userD, c1);
        cipher.Decrypt(c2);
        cipher.Dofinal(c3);

        // 返回解密结果
        return c2;
    }



    public static void main(String[] args) throws Exception {
        // 生成密钥对
        Map<String, String> map = generateKeyPair();

        String plainText = "ah123456";
        byte[] sourceData = plainText.getBytes();

        // 下面的秘钥可以使用generateKeyPair()生成的秘钥内容
        // 国密规范正式私钥
        String prik = "3690655E33D5EA3D9A4AE1A1ADD766FDEA045CDEAA43A9206FB8C430CEFE0D94";
        // 国密规范正式公钥
        String pubk = "04F6E0C3345AE42B51E06BF50B98834988D54EBC7460FE135A48171BC0629EAE205EEDE253A530608178A98F1E19BB737302813BA39ED3FA3C51639D7A20C7391A";


        //prik = getPrivateKey(map);
        //pubk = getPublicKey(map);

        System.out.println(Util.byteToHex(sourceData));


        System.out.println("加密: ");
        String cipherText = SM2Utils.encrypt(Util.hexToByte(pubk), sourceData);
        //System.out.println(cipherText);

        cipherText = SM2Utils.encrypt(Util.hexToByte(pubk), sourceData);
       // System.out.println(cipherText);

        String cipherText1 = SM2Utils.encrypt(pubk, plainText);
        System.out.println("加密结果:"+cipherText1);

        cipherText1 = SM2Utils.encrypt(pubk, plainText);
       // System.out.println(cipherText1);

        System.out.println("解密: ");
        plainText = new String(SM2Utils.decrypt(Util.hexToByte(prik),
                Util.hexToByte(cipherText)));
       // System.out.println(plainText);


        plainText = new String(SM2Utils.decrypt(prik, cipherText1));
        System.out.println(plainText);

    }
}
