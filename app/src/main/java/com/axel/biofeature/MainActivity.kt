package com.axel.biofeature

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import androidx.appcompat.app.AlertDialog
import com.axel.biofeature.security.Biometric

class MainActivity : AppCompatActivity() {
    lateinit var bios: Biometric
    private var useBio = 0
    val MyPREFERENCES = "MyPrefs"
    var sharedpreferences: SharedPreferences? = null
    private var firstTimeLogin = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE)
        val editor = sharedpreferences!!.edit()

        useBio = sharedpreferences?.getInt("useBio", 0)!!

        firstTimeLogin = sharedpreferences?.getInt("firstTimeLogin",0)!!
        if (firstTimeLogin == 0) {

            firstTimeLogin = 1
            editor.putInt("firstTimeLogin",firstTimeLogin)
            editor.apply()

        var checker = sharedpreferences!!.getInt("firstTimeLogin", 0).toString()
        Log.e("###Login", checker)

        val mAlertDialog = AlertDialog.Builder(this)
        mAlertDialog.setTitle("Enable biometrics for privacy")
        mAlertDialog.setIcon(R.drawable.fingerprint)
        mAlertDialog.setCancelable(false)// or tou can put it to true.
        mAlertDialog.setPositiveButton("Ok") { _, _ ->
            //when they accept to use biometric, we call the biometric function and set a flag that brings this popUp every time the user logs out of app
            useBio = 1
            editor.putInt("useBio", useBio)
            editor.apply()
            bios = Biometric(this)
            bios.showBioDialog()
        }
        mAlertDialog.setNegativeButton("Cancel") { dialog, negativeButton__ ->
            useBio = 0
            dialog.dismiss()
        }
        val mAlertBuilderDialog = mAlertDialog.create()
        mAlertBuilderDialog.show()
        mAlertBuilderDialog.window?.setGravity(Gravity.BOTTOM) // // controls width and height of the alert dialogue
    }
        if(useBio == 1){
            bios = Biometric(this)
            bios.showBioDialog()

        }

  }

}