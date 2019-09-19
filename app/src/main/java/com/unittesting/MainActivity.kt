package com.unittesting

import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast

import androidx.appcompat.app.AppCompatActivity

import java.util.Calendar

/**
 * An [AppCompatActivity] that represents an input form page where the user can provide his name, date
 * of birth and email address. The personal information can be saved to [SharedPreferences]
 * by clicking a button.
 */

class MainActivity : AppCompatActivity() {
    private var mSharedPreferencesHelper: SharedPreferencesHelper? = null
    private var mNameText: EditText? = null
    private var mDobPicker: DatePicker? = null
    private var mEmailText: EditText? = null
    private var mEmailValidator: EmailValidator? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mNameText = findViewById<View>(R.id.userNameInput) as EditText
        mDobPicker = findViewById<View>(R.id.dateOfBirthInput) as DatePicker
        mEmailText = findViewById<View>(R.id.emailInput) as EditText
        mEmailValidator = EmailValidator()
        mEmailText!!.addTextChangedListener(mEmailValidator)
        val sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this)
        mSharedPreferencesHelper = SharedPreferencesHelper(sharedPreferences)
        populateUi()
    }

    /**
     * Initialize all fields from the personal info saved in the SharedPreferences.
     */
    private fun populateUi() {
        val sharedPreferenceEntry: SharedPreferenceEntry
        sharedPreferenceEntry = mSharedPreferencesHelper!!.personalInfo
        mNameText!!.setText(sharedPreferenceEntry.name)
        val dateOfBirth = sharedPreferenceEntry.dateOfBirth
        mDobPicker!!.init(dateOfBirth.get(Calendar.YEAR), dateOfBirth.get(Calendar.MONTH),
                dateOfBirth.get(Calendar.DAY_OF_MONTH), null)
        mEmailText!!.setText(sharedPreferenceEntry.email)
    }

    /**
     * Called when the "Save" button is clicked.
     */
    fun onSaveClick(view: View) {
        if (!mEmailValidator!!.isValid) {
            mEmailText!!.error = "Invalid email"
            Log.w(TAG, "Not saving personal information: Invalid email")
            return
        }
        val name = mNameText!!.text.toString()
        val dateOfBirth = Calendar.getInstance()
        dateOfBirth.set(mDobPicker!!.year, mDobPicker!!.month, mDobPicker!!.dayOfMonth)
        val email = mEmailText!!.text.toString()
        val sharedPreferenceEntry = SharedPreferenceEntry(name, dateOfBirth, email)
        val isSuccess = mSharedPreferencesHelper!!.savePersonalInfo(sharedPreferenceEntry)
        if (isSuccess) {
            Toast.makeText(this, "Personal information saved", Toast.LENGTH_LONG).show()
            Log.i(TAG, "Personal information saved")
        } else {
            Log.e(TAG, "Failed to write personal information to SharedPreferences")
        }
    }

    /**
     * Called when the "Revert" button is clicked.
     */
    fun onRevertClick(view: View) {
        populateUi()
        Toast.makeText(this, "Personal information reverted", Toast.LENGTH_LONG).show()
        Log.i(TAG, "Personal information reverted")
    }

    companion object {
      private val TAG = "MainActivity"
    }
}
