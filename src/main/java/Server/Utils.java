package Server;

import java.io.*;
import java.util.ArrayList;

public class Utils {

    public static  byte[] fileToBytes(String fileFullName) throws IOException {
        File file = new File(fileFullName);
        return fileToBytes(file);
    }
    public static byte[] fileToBytes(File file) throws IOException {
        if (file == null || !file.exists()) {
            System.err.println("file is not null or exist !");
            return null;
        }
        if (file.length() > Integer.MAX_VALUE) {
            System.err.println("file is too big ,not to read !");
            throw new IOException(file.getName() + " is too big ,not to read ");
        }

        int _bufferSize = (int) file.length();
        //System.out.println("buffer的大小为："+_bufferSize);
        //定义buffer缓冲区大小
        byte[] buffer = new byte[_bufferSize];

        FileInputStream in = null;
        try {
            in = new FileInputStream(file);
            int len = 0;
            if ((len = in.available()) <= buffer.length) {
                in.read(buffer, 0, len);
            }
        } finally {
            in.close();
        }
        return buffer;

    }

    public static ArrayList<byte []> bytesToMoreBytes(int times , byte[] bytes){
        ArrayList<byte []> bytesArrayList = new ArrayList<>(times);

        int bytelength = bytes.length/times; //一个byte数组放的byte的位数
        int num = 1; //第几个byte数组

        for (int i = 0 ; i < bytes.length ; ) {
            if(num < times){
                byte[] bytes1 = new byte[bytelength];
                for (int j = 0; j < bytelength; j++,i++) {
                    bytes1[j] = bytes[i];
                }
                num++ ;
                bytesArrayList.add(bytes1);
            }
            else {
                byte[] bytes2 = new byte[bytes.length - bytelength * 9];
                for (int j = 0; j < bytes.length - bytelength * 9 ; j++,i++) {
                    bytes2[j] = bytes[i];
                }
                bytesArrayList.add(bytes2);
            }

        }
        return bytesArrayList ;
    }


//    public static void bytesToFile(byte[] bytes){
//
//        return;
//    }


    public static File bytesToFile(byte[] bytes, String outPath, String fileName) {
        BufferedOutputStream bos = null;
        FileOutputStream fos = null;
        File file = null;
        try {
            File dir = new File(outPath);
            if (!dir.exists() && dir.isDirectory()) { //判断文件目录是否存在
                dir.mkdirs();
            }
            file = new File(outPath + File.separator + fileName);
            fos = new FileOutputStream(file);
            bos = new BufferedOutputStream(fos);
            bos.write(bytes);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }
        }

        return file;
    }

    public static byte[] bytesArrToBytes(ArrayList<byte[]> bytesArr){
        int k = 0;
        for (int i = 0; i < bytesArr.size(); i++) {
            k += bytesArr.get(i).length;
        }
        byte[] result = new byte[k];
        int l = 0;
        for (int i = 0; i < bytesArr.size(); i++) {
            for (int j = 0; j < bytesArr.get(i).length; j++) {
                result[l++] = bytesArr.get(i)[j];
            }
        }
        return result;
    }


}
