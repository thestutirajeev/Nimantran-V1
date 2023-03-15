package com.example.nimantran.ui.main.addcard

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.nimantran.models.Template

class TemplateCardViewModel: ViewModel(){
    val _selectedTemplate = MutableLiveData<Template>()
    val selectedTemplate: MutableLiveData<Template>
        get() = _selectedTemplate

    fun selectTemplate(template: Template){
        _selectedTemplate.value = template
    }


}