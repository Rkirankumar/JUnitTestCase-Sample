package com.unittesting

import android.content.SharedPreferences
import java.util.*

/**
 * Helper class to manage access to [SharedPreferences].
 */
class SharedPreferencesHelper
(private val mSharedPreferences: SharedPreferences) {

    val personalInfo: SharedPreferenceEntry
        get() {
            val name = mSharedPreferences.getString(KEY_NAME, "")
            val dobMillis = mSharedPreferences.getLong(KEY_DOB, Calendar.getInstance().timeInMillis)
            val dateOfBirth = Calendar.getInstance()
            dateOfBirth.timeInMillis = dobMillis
            val email = mSharedPreferences.getString(KEY_EMAIL, "")
            //return SharedPreferenceEntry(name, dateOfBirth, email)
            return SharedPreferenceEntry(name.toString(), dateOfBirth, email.toString())
        }

    fun savePersonalInfo(sharedPreferenceEntry: SharedPreferenceEntry): Boolean {
        // Start a SharedPreferences transaction.
        val editor = mSharedPreferences.edit()
        editor.putString(KEY_NAME, sharedPreferenceEntry.name)
        editor.putLong(KEY_DOB, sharedPreferenceEntry.dateOfBirth.timeInMillis)
        editor.putString(KEY_EMAIL, sharedPreferenceEntry.email)
        // Commit changes to SharedPreferences.
        return editor.commit()
    }

    companion object {
        // Keys for saving values in SharedPreferences.
        internal val KEY_NAME = "key_name"
        internal val KEY_DOB = "key_dob_millis"
        internal val KEY_EMAIL = "key_email"
    }
}
