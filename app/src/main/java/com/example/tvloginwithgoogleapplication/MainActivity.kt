package com.example.tvloginwithgoogleapplication

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import coil.compose.AsyncImage
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInAccount
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.common.api.ApiException
import com.google.android.gms.tasks.Task

class MainActivity : ComponentActivity() {

    private lateinit var googleSignInClient: GoogleSignInClient
    private val RC_SIGN_IN = 9001
    private val userViewModel: UserViewModel by viewModels()


    @OptIn(ExperimentalTvMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val gso = GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
            .requestEmail()
            .build()

        googleSignInClient = GoogleSignIn.getClient(this, gso)
        val account = GoogleSignIn.getLastSignedInAccount(this)
        if (account != null) {
            userViewModel.setUser(account)
        }

        setContent {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val user by userViewModel.userLiveData.observeAsState()

                Spacer(modifier = Modifier.size(64.dp))

                Text(text = "Name: ${user?.displayName}")
                Text(text = "Email: ${user?.email}")

                Spacer(modifier = Modifier.size(32.dp))

                user?.photoUrl?.let { url ->
                    AsyncImage(
                        model = url,
                        contentDescription = null,
                        modifier = Modifier
                            .size(50.dp)
                    )
                }

                Spacer(modifier = Modifier.size(64.dp))

                Button(onClick = {
                    val signInIntent = googleSignInClient.signInIntent
                    this@MainActivity.startActivityForResult(signInIntent, RC_SIGN_IN)
                }) {
                    Text("Sign in with Google")
                }

                Spacer(modifier = Modifier.size(64.dp))

                Button(onClick = {
                    googleSignInClient.signOut().addOnCompleteListener {
                        userViewModel.clearUser()
                        Toast.makeText(this@MainActivity, "SignOut Successfully", Toast.LENGTH_LONG)
                            .show()
                    }
                }) {
                    Text("SignOut")
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_SIGN_IN) {
            val task = GoogleSignIn.getSignedInAccountFromIntent(data)
            handleSignInResult(task)
        }
    }

    private fun handleSignInResult(completedTask: Task<GoogleSignInAccount>) {
        try {
            val account = completedTask.getResult(ApiException::class.java)
            Toast.makeText(
                this,
                "name${account.displayName} | email:${account.email} | photo: ${account.photoUrl}",
                Toast.LENGTH_LONG
            ).show()
            if (account != null) {
                userViewModel.setUser(account)
            }
        } catch (e: ApiException) {
            Toast.makeText(this, e.localizedMessage, Toast.LENGTH_LONG).show()
        }
    }
}
