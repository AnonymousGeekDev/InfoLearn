package tk.infotech.infolearn20.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Subject(val subjectId: Int, val subjectName: String) : Parcelable