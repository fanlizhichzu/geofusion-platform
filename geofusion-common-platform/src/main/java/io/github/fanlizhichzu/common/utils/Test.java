package io.github.fanlizhichzu.common.utils;

/**
 * @Description:
 * @Auther: fanlz
 * @Date: 2021/12/23 10:56
 */
public class Test {
    public static void main(String[] args) {
        Long start = System.currentTimeMillis();
        int a = 1;
        Long end = System.currentTimeMillis();
        System.out.println(end-start);
//        System.out.println(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss.SSS")));
    }
}
