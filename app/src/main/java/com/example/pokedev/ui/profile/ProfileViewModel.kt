package com.example.pokedev.ui.profile

import androidx.lifecycle.*
import com.example.data.repository.ProfileRepository
import com.example.pokedev.common.BaseViewModel
import kotlinx.coroutines.launch

class ProfileViewModel(private val profileRepository: ProfileRepository) : BaseViewModel() {

    val obsName : LiveData<String> = profileRepository.getProfileName().asLiveData()
    val obsSurname : LiveData<String> = profileRepository.getProfileSurname().asLiveData()
    val obsBirthday : LiveData<String> = profileRepository.getProfileBirthday().asLiveData()
    val obsGender : LiveData<String> = profileRepository.getProfileGender().asLiveData()

    fun onActionNameWritten(name:String){
        viewModelScope.launch {
            profileRepository.saveProfileName(name)
        }
    }

    fun onActionSurnameWritten(surname:String){
        viewModelScope.launch {
            profileRepository.saveProfileSurname(surname)
        }
    }

    fun onActionBirthdaySelected(birthday:String){
        viewModelScope.launch {
            profileRepository.saveProfileBirthday(birthday)
        }
    }

    fun onActionGenderWritten(gender:String){
        viewModelScope.launch {
            profileRepository.saveProfileGender(gender)
        }
    }
}