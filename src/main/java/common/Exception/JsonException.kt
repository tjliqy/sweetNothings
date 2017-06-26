package common.Exception

/**
 * Created by tjliqy on 2017/6/26.
 */
class JsonException(errorMsg:String?= "", errorNumber: Int?):Exception(){
    var errorNumber = -1
    var errorMsg = ""
    init {
        if(errorMsg != null){
            this.errorMsg = errorMsg
            this.errorNumber = 1
        }
        if (errorNumber != null){
            this.errorNumber = errorNumber
        }
    }
}