package com.example.data.repository

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext

class ProfileRepository(private val context: Context) {

    private val Context.dataStore : DataStore<Preferences> by preferencesDataStore(name = "ProfilePreferences")
    private val KEY_PROFILE_NAME = stringPreferencesKey("KEY_PROFILE_NAME")
    private val KEY_PROFILE_SURNAME = stringPreferencesKey("KEY_PROFILE_SURNAME")
    private val KEY_PROFILE_BIRTHDAY = stringPreferencesKey("KEY_PROFILE_BIRTHDAY")
    private val KEY_PROFILE_GENDER = stringPreferencesKey("KEY_PROFILE_GENDER")

    //Name
    fun getProfileName(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_PROFILE_NAME]?:""
        }
    }

    suspend fun saveProfileName(name:String){
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences ->
                preferences[KEY_PROFILE_NAME] = name
            }
        }
    }

    //Surname
    fun getProfileSurname(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_PROFILE_SURNAME]?:""
        }
    }

    suspend fun saveProfileSurname(surname:String){
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences ->
                preferences[KEY_PROFILE_SURNAME] = surname
            }
        }
    }

    //Birthday
    fun getProfileBirthday(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_PROFILE_BIRTHDAY]?:""
        }
    }

    suspend fun saveProfileBirthday(birthday:String){
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences ->
                preferences[KEY_PROFILE_BIRTHDAY] = birthday
            }
        }
    }

    //Gender
    fun getProfileGender(): Flow<String> {
        return context.dataStore.data.map { preferences ->
            preferences[KEY_PROFILE_GENDER]?:""
        }
    }

    suspend fun saveProfileGender(gender:String){
        withContext(Dispatchers.IO){
            context.dataStore.edit { preferences ->
                preferences[KEY_PROFILE_GENDER] = gender
            }
        }
    }
}