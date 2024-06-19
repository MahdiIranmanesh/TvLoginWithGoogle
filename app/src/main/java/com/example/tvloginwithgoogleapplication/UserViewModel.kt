package com.example.tvloginwithgoogleapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.android.gms.auth.api.signin.GoogleSignInAccount

class UserViewModel : ViewModel() {
    private val _userLiveData = MutableLiveData<GoogleSignInAccount?>()
    val userLiveData: LiveData<GoogleSignInAccount?> get() = _userLiveData

    fun setUser(account: GoogleSignInAccount) {
        _userLiveData.value = account
    }

    fun clearUser() {
        _userLiveData.value = null
    }
}