package com.project.segunfrancis.droidcamplocal_storage.util

import android.content.Context
import android.content.SharedPreferences
import com.project.segunfrancis.droidcamplocal_storage.model.SignInModel
import com.project.segunfrancis.droidcamplocal_storage.model.SignUpModel
import com.project.segunfrancis.droidcamplocal_storage.util.AppConstants.EMAIL_KEY
import com.project.segunfrancis.droidcamplocal_storage.util.AppConstants.NAME_KEY
import com.project.segunfrancis.droidcamplocal_storage.util.AppConstants.PASSWORD_KEY
import com.project.segunfrancis.droidcamplocal_storage.util.AppConstants.PREFERENCE_NAME
import com.project.segunfrancis.droidcamplocal_storage.util.AppConstants.SCHOOL_KEY

/**
 * Created by SegunFrancis
 *
 * This class manages all [SharedPreferences] related activities
 * @param context is required to initialize the [SharedPreferences] object
 */

class SharedPrefUtil(context: Context) {

    private var sharedPreferences: SharedPreferences =
        context.getSharedPreferences(PREFERENCE_NAME, Context.MODE_PRIVATE)

    /**
     * This method signs up a new user
     *
     * @param signUpModel is a data class containing all the sign up details of the user
     * The preference key is a combination of, for example, the name and the email address of the user
     * Since each user is supposed to have a unique email address, adding the email address to the
     * preference key will lead to unique keys for each user that can easily be retrieved
     */
    fun signUp(signUpModel: SignUpModel) {
        val editor = sharedPreferences.edit()
        editor.putString(NAME_KEY.plus(signUpModel.email), signUpModel.name)
        editor.putString(EMAIL_KEY.plus(signUpModel.email), signUpModel.email)
        editor.putString(SCHOOL_KEY.plus(signUpModel.email), signUpModel.school)
        editor.putString(PASSWORD_KEY.plus(signUpModel.email), signUpModel.password)
        editor.apply()
    }

    /**
     * This method logs in an already existing user
     *
     * @param signInModel is a data class that contains the sign in details of the user - email & password
     * An enum class (instead of a [Boolean]) is used to manage the possible outcomes of the login attempt
     * because there are more than 2 possible outcomes while the user tries to login
     */
    fun login(signInModel: SignInModel): LoginState {
        return if (signInModel.email == sharedPreferences.getString(EMAIL_KEY.plus(signInModel.email), "")
            &&
            signInModel.password == sharedPreferences.getString(PASSWORD_KEY.plus(signInModel.email), "")
        ) {
            LoginState.SUCCESS
        } else if (signInModel.email == sharedPreferences.getString(EMAIL_KEY.plus(signInModel.email), "")
            &&
            signInModel.password != sharedPreferences.getString(PASSWORD_KEY.plus(signInModel.email), "")
        ) {
            LoginState.INCORRECT_PASSWORD
        } else {
            LoginState.NOT_REGISTERED
        }
    }

    /**
     * This method is called after a successful login in order to get the entire details of the logged in user
     *
     * @param signInModel is a data class that contains the sign in details of the user - email & password
     */
    fun getUserDetails(signInModel: SignInModel): SignUpModel {
        return SignUpModel(
            name = sharedPreferences.getString(NAME_KEY.plus(signInModel.email), "")!!,
            email = sharedPreferences.getString(EMAIL_KEY.plus(signInModel.email), "")!!,
            school = sharedPreferences.getString(SCHOOL_KEY.plus(signInModel.email), "")!!,
            password = sharedPreferences.getString(PASSWORD_KEY.plus(signInModel.email), "")!!
        )
    }

    /**
     * Use this class to manage the various outcomes when a user tries to login
     */
    enum class LoginState {
        INCORRECT_PASSWORD, NOT_REGISTERED, SUCCESS
    }
}