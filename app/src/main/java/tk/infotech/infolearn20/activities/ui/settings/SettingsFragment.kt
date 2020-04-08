package tk.infotech.infolearn20.activities.ui.settings

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.preference.*

class SettingsFragment : PreferenceFragmentCompat() {

    private lateinit var settingsViewModel: SettingsViewModel

//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//
//        settingsViewModel =
//            ViewModelProviders.of(this).get(SettingsViewModel::class.java)
//        val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//        val textView: TextView = root.findViewById(R.id.text_notifications)
//        settingsViewModel.text.observe(this, Observer {
//            textView.text = it
//        })
//        return root
//    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {

        settingsViewModel = ViewModelProviders.of(this).get(SettingsViewModel::class.java)

        val context = preferenceManager.context
        val screen = preferenceManager.createPreferenceScreen(context)

        val notificationPreference = SwitchPreferenceCompat(context)
        notificationPreference.key = "settings"
        notificationPreference.title = "Enable message settings"
        settingsViewModel.text.observe(this, Observer {
            notificationPreference.summary = it
        })


        val textPreferenceDialogFragmentCompat = EditTextPreference(context)
        textPreferenceDialogFragmentCompat.key = "username"
        textPreferenceDialogFragmentCompat.title = "UserName"
        textPreferenceDialogFragmentCompat.summary = "Enter your name"
        textPreferenceDialogFragmentCompat.dialogTitle = "Test"
        textPreferenceDialogFragmentCompat.dialogMessage = "Test name"
        textPreferenceDialogFragmentCompat.setOnBindEditTextListener {
            textPreferenceDialogFragmentCompat.summary = it.text
        }

        val notificationCategory = PreferenceCategory(context)
        notificationCategory.key = "notifications_category"
        notificationCategory.title = "Notifications"
        screen.addPreference(notificationCategory)
        notificationCategory.addPreference(notificationPreference)
        notificationCategory.addPreference(textPreferenceDialogFragmentCompat)

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