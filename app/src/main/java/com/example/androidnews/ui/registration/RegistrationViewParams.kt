package com.example.androidnews.ui.registration

import com.example.androidnews.data.model.Source

data class RegistrationViewParams(
    var source: Source = Source(),
    var author: String = "",
    var title: String = "",
    var description: String = "",
    var image: String = "",
    var data: String = ""
)
