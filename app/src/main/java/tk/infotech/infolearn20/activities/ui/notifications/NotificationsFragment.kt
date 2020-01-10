package tk.infotech.infolearn20.activities.ui.notifications

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.Preference
import androidx.preference.PreferenceCategory
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreferenceCompat

class NotificationsFragment : PreferenceFragmentCompat() {

    private lateinit var notificationsViewModel: NotificationsViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        notificationsViewModel =
//            ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        notificationsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
//        return root
//    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        notificationsViewModel = ViewModelProviders.of(this).get(NotificationsViewModel::class.java)

        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        val notificationPreference = SwitchPreferenceCompat(context)
        notificationPreference.key = "notifications"
        notificationPreference.title = "Enable message notifications"
        notificationsViewModel.text.observe(this, Observer {
            notificationPreference.summary = it
        })

        val notificationCategory = PreferenceCategory(context)
        notificationCategory.key = "notifications_category"
        notificationCategory.title = "Notifications"
        screen.addPreference(notificationCategory)
        notificationCategory.addPreference(notificationPreference)

        val feedbackPreference = Preference(context)
        feedbackPreference.key = "feedback"
        feedbackPreference.title = "Send feedback"
        feedbackPreference.summary = "Report technical issues or suggest new features"

        val helpCategory = PreferenceCategory(context)
        helpCategory.key = "help"
        helpCategory.title = "Help"
        screen.addPreference(helpCategory)
        helpCategory.addPreference(feedbackPreference)

        preferenceScreen = screen
    }
}