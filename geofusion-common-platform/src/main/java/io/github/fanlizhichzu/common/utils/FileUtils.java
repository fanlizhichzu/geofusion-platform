package io.github.fanlizhichzu.common.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import jakarta.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

/**
 * @author WL
 * @Description: 文件工具封装
 * @date 2021/5/28 13:37
 */
public class FileUtils {

    /***
     * 文件导出
     * @param fileName
     * @throws Exception
     */
    public static void exportFile(String fileName, HttpServletResponse response, Object data) throws Exception {
        fileName = URLEncoder.encode(fileName, "utf-8").replaceAll("\\+", "%20");
        response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
        response.getOutputStream().write(JSON.toJSONString(data).getBytes(StandardCharsets.UTF_8));
    }

    public static String readFileByChars(String fileName) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        Resource resource = new ClassPathResource(fileName);
        InputStream inputStream = resource.getInputStream();
        Reader reader = null;
        try {
            // 一次读一个字符
            reader = new InputStreamReader(inputStream, StandardCharsets.UTF_8);
            int tempchar;
            while ((tempchar = reader.read()) != -1) {
                // 对于windows下，\r\n这两个字符在一起时，表示一个换行。
                // 但如果这两个字符分开显示时，会换两次行。
                // 因此，屏蔽掉\r，或者屏蔽\n。否则，将会多出很多空行。
                if (((char) tempchar) != '\r') {
                    stringBuilder.append((char) tempchar);
                }
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return stringBuilder.substring(4);
    }

    public static void createMachineCodeFile(String path, String data, String fileName) throws IOException {
        FileWriter fw = null;
        try {
            File dir = new File(path);
            dir.mkdirs();
            String datePath = path + fileName;
            File makeBoatsLog = new File(datePath);
            fw = new FileWriter(makeBoatsLog, true);
            fw.write("key:" + data);
            fw.flush();
        } catch (IOException e) {
            throw new IOException();
        } finally {
            try {
                assert fw != null;
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
