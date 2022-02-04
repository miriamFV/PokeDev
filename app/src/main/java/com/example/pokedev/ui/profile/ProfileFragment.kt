package com.example.pokedev.ui.profile

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import com.example.pokedev.R
import com.example.pokedev.common.BaseFragment
import com.example.pokedev.common.setText
import com.example.pokedev.databinding.FragmentProfileBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.*

class ProfileFragment : BaseFragment<FragmentProfileBinding, ProfileViewModel>() {

    override val vm: ProfileViewModel by viewModel()
    private val genders by lazy{ resources.getStringArray(R.array.genders) }
    private val arrayAdapter by lazy{ ArrayAdapter(requireContext(), R.layout.dropdown_item, genders) }

    override fun provideBinding(inflater: LayoutInflater, container: ViewGroup?): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater, container, false)
    }

    private val nameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(text: Editable?) {
            vm.onActionNameWritten(text.toString())
        }
    }

    private val surnameTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(text: Editable?) {
            vm.onActionSurnameWritten(text.toString())
        }
    }

    private val genderTextWatcher = object : TextWatcher{
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
        override fun afterTextChanged(text: Editable?) {
            vm.onActionGenderWritten(text.toString())
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.apply {
            autotvProfileGender.setAdapter(arrayAdapter)
            etProfileName.addTextChangedListener(nameTextWatcher)
            etProfileSurName.addTextChangedListener(surnameTextWatcher)
            etProfileBirthday.setOnClickListener { showDatePickerDialog() }
            autotvProfileGender.addTextChangedListener(genderTextWatcher)
        }
        observeData(vm.obsName,::onObserveName)
        observeData(vm.obsSurname,::onObserveSurname)
        observeData(vm.obsBirthday,::onObserveBirthday)
        observeData(vm.obsGender,::onObserveGender)
    }


    private fun onObserveName(name: String) {
        binding.apply {
            tvProfileName.text = name
            etProfileName.setText(name, nameTextWatcher)
        }
    }

    private fun onObserveSurname(surname: String) {
        var profileName : String = binding.tvProfileName.text.toString()
        profileName += " $surname"
        binding.apply {
            tvProfileName.text = profileName
            etProfileSurName.setText(surname, surnameTextWatcher)
        }

    }

    private fun showDatePickerDialog(){
        val datePicker = DatePickerFragment { day, month, year -> onDateSelected(day, month, year) }
        activity?.let { datePicker.show(it.supportFragmentManager, "datePicker") }
    }

    private fun onDateSelected(day:Int, month:Int, year:Int){
        val birthday : String = "$day/${month+1}/$year"
        binding.etProfileBirthday.setText(birthday)
        vm.onActionBirthdaySelected(birthday)
    }


    fun onObserveBirthday(birthday: String) {
        binding.etProfileBirthday.setText(birthday)
    }


    private fun onObserveGender(gender: String) {
        binding.apply {
            when(gender.lowercase(Locale.getDefault())){
                "hombre" -> {
                    ivProfileImage.setImageResource(R.drawable.ic_profile_man)
                    textInputLayoutGender.setStartIconDrawable(R.drawable.ic_male)
                }
                "mujer" -> {
                    ivProfileImage.setImageResource(R.drawable.ic_profile_woman)
                    textInputLayoutGender.setStartIconDrawable(R.drawable.ic_female)
                }
                else -> {
                    ivProfileImage.setImageResource(R.drawable.ic_question_blue)
                    textInputLayoutGender.setStartIconDrawable(R.drawable.ic_question)
                }
            }
        }

    }
}
