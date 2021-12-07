package com.axel.biofeature.security

import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import android.widget.Toast
import androidx.biometric.BiometricPrompt

class Biometric (private val activity: AppCompatActivity) {

    fun showBioDialog(){
        val executor = ContextCompat.getMainExecutor(activity)
        val biometricPrompt= BiometricPrompt(activity, executor,
            object : BiometricPrompt.AuthenticationCallback() {
                override fun onAuthenticationError(
                    errorCode: Int, errString: CharSequence
                ) {
                    super.onAuthenticationError(errorCode, errString)
                    Toast.makeText(activity, errString.toString(), Toast.LENGTH_SHORT).show()
                }

               override fun onAuthenticationSucceeded(
                    result: BiometricPrompt.AuthenticationResult
                ) {
                    super.onAuthenticationSucceeded(result)

                    Toast.makeText(activity, "Successful", Toast.LENGTH_SHORT).show()
                }

//                override fun onAuthenticationFailed() {
//                    super.onAuthenticationFailed()
//
//                }

            })

        val promptInfo = BiometricPrompt.PromptInfo.Builder()
            .setTitle("Biometric Authentication")
            .setSubtitle("Scan fingerprint or use credentials")
            .setDescription("Confirm your identity using your biometric or credentials")
            .setDeviceCredentialAllowed(true)
            .build()
        biometricPrompt.authenticate(promptInfo)

//        val promptInfo = BiometricPrompt.PromptInfo.Builder()
//            .setTitle("Biometric Authentication")
//            .setSubtitle("use your biometrics to proceed")
//            .setDescription("use your set or preferred form of biometric")
//            .setDeviceCredentialAllowed(true)
//        biometricPrompt.authenticate(promptInfo)

    }
}