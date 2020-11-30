package com.project.segunfrancis.droidcamplocal_storage.util

import android.util.Patterns
import android.widget.Toast
import androidx.fragment.app.Fragment

/**
 * An extension function that displays a toast message
 *
 * @param message is the [String] to be displayed by the toast
 * This method should be called inside a [Fragment] always
 */
fun Fragment.showToast(message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}

/**
 * A function that verifies the email entered by the user
 *
 * @param fragment is the [Fragment] where this method is being called
 * @param email is the email address entered by the user
 */
fun verifyEmail(fragment: Fragment, email: String?): Boolean {
    if (email.isNullOrEmpty()) {
        fragment.showToast("Email field is required")
        return false
    }
    if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
        fragment.showToast("Email address is not valid")
        return false
    }
    return true
}

/**
 * A function that verifies the email entered by the user
 *
 * @param fragment is the [Fragment] where this method is being called
 * @param password is the password entered by the user
 */
fun verifyPassword(fragment: Fragment, password: String?): Boolean {
    if (password.isNullOrEmpty()) {
        fragment.showToast("Password field is required")
        return false
    }
    if (password.length < 6) {
        fragment.showToast("Password is too short")
        return false
    }
    return true
}

/**
 * A function that verifies the name entered by the user
 *
 * @param fragment is the [Fragment] where this method is being called
 * @param name is the name entered by the user
 */
fun verifyName(fragment: Fragment, name: String?): Boolean {
    if (name.isNullOrEmpty()) {
        fragment.showToast("Name field is required")
        return false
    }
    if (name.length < 5) {
        fragment.showToast("Name is too short")
        return false
    }
    return true
}

/**
 * A function that verifies the school entered by the user
 *
 * @param fragment is the [Fragment] where this method is being called
 * @param school is the password entered by the user
 */
fun verifySchool(fragment: Fragment, school: String?): Boolean {
    if (school.isNullOrEmpty()) {
        fragment.showToast("School field is required")
        return false
    }
    return true
}