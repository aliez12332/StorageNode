package Server;

import java.io.IOException;

public interface IDao {
    //向服务器保存一个文件
    public Boolean saveFile(DataFile data);
    //向服务器获取一组对象
    public DataFile getFile(String name) throws IOException;

}