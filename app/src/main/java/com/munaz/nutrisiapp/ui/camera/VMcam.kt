package com.munaz.nutrisiapp.ui.camera

import androidx.lifecycle.ViewModel
import com.munaz.nutrisiapp.repo.Repo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class VMcam @Inject constructor(private val repo: Repo) : ViewModel(){

}