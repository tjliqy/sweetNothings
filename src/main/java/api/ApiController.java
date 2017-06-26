package api;

import com.jfinal.kit.HashKit;
import com.jfinal.kit.PathKit;
import com.jfinal.kit.PropKit;
import com.jfinal.plugin.activerecord.Page;
import com.jfinal.upload.UploadFile;
import common.Exception.JsonException;
import common.JsonBaseController;
import model.Record;
import net.coobird.thumbnailator.Thumbnails;

import java.io.File;
import java.io.IOException;

/**
 * Created by tjliqy on 2017/6/26.
 */
public class ApiController extends JsonBaseController {
    public void index() {
        throw new JsonException("不不不~");
    }

    public void record() {
        Page<Record> paginate = Record.dao.paginate(getParaToInt("page", 1), getParaToInt("size", 10), "SELECT *", "FROM record");
        addBody("page", paginate);
    }

    public void add() {
        Record record = getModel(Record.class);
        record.save();
        addBody("record",record);
    }

    public void upload() {
        UploadFile file = getFile();
        String fileName = file.getOriginalFileName();
        String extension = fileName.substring(fileName.lastIndexOf("."));
        String finalName = fileName.substring(0, fileName.lastIndexOf(".")) + System.currentTimeMillis();
        finalName = HashKit.md5(finalName) + extension;
        //图片保存路径
        String path = PathKit.getWebRootPath() + PropKit.get("imgUploadFolder");
        String thumbPath = PathKit.getWebRootPath() + PropKit.get("thumbImgUploadFolder");

        if (file.getFile().renameTo(new File(path + finalName))) {
            try {
                Thumbnails.of(new File(path + finalName))
                        .size(400, 400)
                        .toFile(new File(thumbPath + finalName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addBody("img", finalName);
    }
}
