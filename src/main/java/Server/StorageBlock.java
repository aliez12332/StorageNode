package Server;

import java.util.HashMap;

/**
 * 存储文件name和路径的映射关系
 */
public class StorageBlock {
    private static int id = 0;
    public static HashMap<String , HashMap > portToMap = new HashMap<>();
    public static synchronized String getFileName(){
        id=id+1;
        return Thread.currentThread().getName()+"存储的" + id + ".txt";
    }
    public static InforFile  getInforFileByName(String threadName , String name){
        HashMap<String ,InforFile> storageMap = portToMap.get(threadName);
        return storageMap.get(name);
    }

    public static synchronized void add(String threadName , String name,String filePath , int ranking){
        HashMap<String ,InforFile> storageMap = new HashMap<>();
        InforFile inforFile = new InforFile(filePath,ranking);
        storageMap.put(name,inforFile);
        portToMap.put(threadName,storageMap);
    }

}
