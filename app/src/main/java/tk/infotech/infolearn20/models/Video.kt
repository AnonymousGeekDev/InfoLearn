package tk.infotech.infolearn20.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat

@Parcelize
class Video(var videoName: String, var duration : String, var videoThumbNailId: Int = 0) : Parcelable {

//    constructor(videoName: String, videoDuration: String, videoThumbNailId: Int) : this() {
//        this.videoName = videoName
//        this.duration = videoDuration
//        this.videoThumbNailId = videoThumbNailId
//    }

    init {

        videoName = "video" + SimpleDateFormat.getDateTimeInstance().parse("2020-01-22")

    }

    // Will need to add or remove this method in order to implement the SOC principle of architecture.

    //    public List<Video> getListOfVideos() {
    //        List<Video> videos = new ArrayList<>();
    //        videos.add(new Video("Chapter 1", "05:20", 10002));
    //        return videos;
    //    }
}
