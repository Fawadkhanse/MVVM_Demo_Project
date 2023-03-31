package com.appinsnap.lwmc.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.appinsnap.lwmc.dataclasses.responsehandler.ErrorWrapper
import com.appinsnap.lwmc.dataclasses.model.request.LoginDataModel
import com.appinsnap.lwmc.dataclasses.model.response.LoginResponseModel
import com.appinsnap.lwmc.dataclasses.observerhandler.Event
import dagger.hilt.android.lifecycle.HiltViewModel
import health.dept.ecarecameroon.core.repository.doctorrepository.LwmsRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val lwmsRepository: LwmsRepository
):ViewModel(){
    val error : MutableLiveData<Event<ErrorWrapper>> by lazy {
        MutableLiveData<Event<ErrorWrapper>>()
    }


    private var _loginResponse = MutableLiveData<Event<LoginResponseModel>>()
    var loginResponse:LiveData<Event<LoginResponseModel>> = _loginResponse

    fun callLoginApi(loginDataModel: LoginDataModel){
        viewModelScope.launch{
            val response = lwmsRepository.loginApi(loginDataModel)
            response.onSuccess {
                _loginResponse.value = Event(it)
            }.onFailure {
                error.value = Event(it)
            }

        }
    }
}