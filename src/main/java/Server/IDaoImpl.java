package Server;

import java.io.IOException;

public class IDaoImpl implements IDao {

    @Override
    public Boolean saveFile(DataFile dataFile) {
        String fileName = StorageBlock.getFileName();
        String filePath = "E:\\storageNode\\"+ fileName;
        StorageBlock.add(Thread.currentThread().getName(), dataFile.name, filePath,dataFile.ranking);
        Utils.bytesToFile(dataFile.dataToSave,"E:\\storageNode",fileName);
        return true;
    }

    @Override
    public DataFile getFile(String name) throws IOException {
        InforFile inforFile = StorageBlock.getInforFileByName(Thread.currentThread().getName(),name);
        DataFile dataFile = new DataFile(name,Utils.fileToBytes(inforFile.filePath), inforFile.ranking);
        return dataFile;
    }
}
