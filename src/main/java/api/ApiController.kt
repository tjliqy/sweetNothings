package api

import com.jfinal.core.Controller
import com.jfinal.kit.HashKit
import java.io.IOException
import com.jfinal.kit.PathKit
import java.io.File
import com.jfinal.upload.UploadFile
import common.JsonBaseController
import net.coobird.thumbnailator.Thumbnails


/**
 * Created by tjliqy on 2017/6/25.
 */
class ApiController :JsonBaseController(){
    internal fun index(){
        addBody("hahah","heiheihei")
    }

    fun add(){

    }

    fun delete(){

    }

    fun upload(){
        val fileName = file.originalFileName
        val extension = fileName.substring(fileName.lastIndexOf("."))
        var finalName = fileName.substring(0, fileName.lastIndexOf(".")) + System.currentTimeMillis()
        finalName = HashKit.md5(finalName) + extension
        if (file.file.renameTo(File(PathKit.getWebRootPath() + "/upload/img/" + finalName))) {
            try {
                Thumbnails.of(File(PathKit.getWebRootPath() + "/upload/img/" + finalName))
                        .size(400, 400)
                        .toFile(File(PathKit.getWebRootPath() + "/upload/thumbImg/" + finalName))
            } catch (e: IOException) {
                e.printStackTrace()
            }

        }
        addBody("file",finalName)
    }
}