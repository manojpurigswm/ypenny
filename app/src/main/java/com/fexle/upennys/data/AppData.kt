package com.fexle.upennys.data

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson

class AppData(var context: Context){

    private var prefs = context.getSharedPreferences("AppData", Context.MODE_PRIVATE)

    private val isLogin = "isLogin"
    private val userId = "userId"
    private val userToken = "userToken"
    private val name = "name"
    private val firstName = "firstName"
    private val lastName = "lastName"
    private val mobileNumber = "mobileNumber"
    private val email = "email"
    private val password = "password"
    private val isStatus = "isStatus"
    private val createdOn = "createdOn"
    private val appLanguage = "appLanguage"
    private val cartData = "cartData"

    private val profilePicture = "profilePicture"


    fun setIsLogin(isLogin: Boolean) {
        val editor = prefs.edit()
        editor.putBoolean(this.isLogin, isLogin)
        editor.apply()
    }

    fun setUserId(userId: String) {
        val editor = prefs.edit()
        editor.putString(this.userId, userId)
        editor.apply()
    }

    fun setUserToken(userToken: String) {
        val editor = prefs.edit()
        editor.putString(this.userToken, userToken)
        editor.apply()
    }

    fun setName(name: String) {
        val editor = prefs.edit()
        editor.putString(this.name, name)
        editor.apply()
    }

    fun setFirstName(firstName: String) {
        val editor = prefs.edit()
        editor.putString(this.firstName, firstName)
        editor.apply()
    }

    fun setLastName(lastName: String) {
        val editor = prefs.edit()
        editor.putString(this.lastName, lastName)
        editor.apply()
    }


    fun setMobileNumber(mobileNumber: String) {
        val editor = prefs.edit()
        editor.putString(this.mobileNumber, mobileNumber)
        editor.apply()
    }

    fun setEmail(email: String) {
        val editor = prefs.edit()
        editor.putString(this.email, email)
        editor.apply()
    }

    fun setPassword(password: String) {
        val editor = prefs.edit()
        editor.putString(this.password, password)
        editor.apply()
    }



    fun setCreatedOn(createdOn: String) {
        val editor = prefs.edit()
        editor.putString(this.createdOn, createdOn)
        editor.apply()
    }


    fun setIsStatus(isStatus: String) {
        val editor = prefs.edit()
        editor.putString(this.isStatus, isStatus)
        editor.apply()
    }

    fun setProfilePicture(profilePicture: String) {
        val editor = prefs.edit()
        editor.putString(this.profilePicture, profilePicture)
        editor.apply()
    }

    fun setAppLanguage(appLanguage: String?) {
        val editor = prefs.edit()
        editor.putString(this.appLanguage, appLanguage)
        editor.apply()
    }

    
    fun getIsLogin(): Boolean {
        return prefs.getBoolean(isLogin, false)
    }


    fun getUserId(): String {
        return prefs.getString(userId, "").toString()
    }

    fun getUserToken(): String{
        return prefs.getString(userToken, "").toString()
    }

    fun getName(): String {
        return prefs.getString(name, "").toString()
    }

    fun getFirstName(): String {
        return prefs.getString(firstName, "").toString()
    }

    fun getLastName(): String {
        return prefs.getString(lastName, "").toString()
    }

    fun getMobileNumber(): String {
        return prefs.getString(mobileNumber, "").toString()
    }

    fun getEmail(): String {
        return prefs.getString(email, "").toString()
    }


    fun getPassword(): String {
        return prefs.getString(password, "").toString()
    }

    fun getIsStatus(): String? {
        return prefs.getString(isStatus, "")
    }

    fun getAppLanguage(): String?{
        return prefs.getString(appLanguage, "en")
    }

    fun logoutUser() : Boolean{
        val editor = prefs.edit()
        editor.clear()
        return editor.commit()
    }

    fun getPrefs(): SharedPreferences{
        return prefs
    }

    fun setBaseData(cartData: BaseData) {
        var json = Gson().toJson(cartData)
        val editor = prefs.edit()
        editor.putString(this.cartData, json)
        editor.apply()
    }

    fun getBaseData(): BaseData?{
        var json = prefs.getString(this.cartData, "").toString()

        return if(json.isNullOrEmpty()){
            null
        } else{
            Gson().fromJson(json, BaseData::class.java) as BaseData
        }
    }

    companion object {

        private var INSTANCE: AppData? = null

        @JvmStatic
        fun getInstance(context: Context?) =
            INSTANCE ?: synchronized(AppData::class.java) {
                if(context != null){
                    INSTANCE ?: AppData(context)
                        .also { INSTANCE = it }
                }
                else{
                    INSTANCE
                }
            }!!


        /**
         * Used to force [getInstance] to create a new instance
         * next time it's called.
         */
        @JvmStatic
        fun destroyInstance() {
            INSTANCE = null
        }
    }
}