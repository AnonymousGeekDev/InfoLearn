package tk.infotech.infolearn20.models

class Subject(val subjectId: Int, val subjectName: String) {

    private var chapterList: MutableList<Chapter> = ArrayList()

    fun addOneOrManyChapters(vararg chapterOrChapters: Chapter){
        chapterList.addAll(chapterOrChapters)
    }
}