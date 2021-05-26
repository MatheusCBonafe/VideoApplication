package com.example.videoapplication.list;

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.data.movieapi.MovieRepository


class ListViewModel : ViewModel() {
    private var _text = MutableLiveData<String>()
    val text: LiveData<String> get() = _text

    private var _recyclerItems = MutableLiveData<List<RecyclerItem>>()
    val recyclerItems: LiveData<List<RecyclerItem>> get() = _recyclerItems

    private val movieRepository by lazy {
        MovieRepository()
    }

    fun text() {
        movieRepository.getItemData()
        _recyclerItems.value = listOf(
            RecyclerItem(
                "https://2.bp.blogspot.com/-bPIkBuAXsbY/XR76sW9L4RI/AAAAAAAAELA/UcveontcdhwsrsHt9V5IqpYe6tMp3QmlACKgBGAs/w0/fantasy-monster-cthulhu-uhdpaper.com-4K-158.jpg",
                "Cthulu, The Sleeping God",
                "Cthulu is a Great Old One of great power who lies in a death-like slumber beneath the Pacific Ocean in his sunken city of R'lyeh. He remains a dominant presence in the eldrich dealings on our world. ",
                true
            ),

            RecyclerItem(
                "https://images-wixmp-ed30a86b8c4ca887773594c2.wixmp.com/f/52b5d914-8ec4-4b4d-a1bf-d6885de9f09c/d6ubvy6-50dcb45c-bb4e-40ca-a764-8a5f9ad06758.jpg/v1/fill/w_1024,h_614,q_75,strp/yog_sothoth_rising_by_tentaclesandteeth_d6ubvy6-fullview.jpg?token=eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1cm46YXBwOjdlMGQxODg5ODIyNjQzNzNhNWYwZDQxNWVhMGQyNmUwIiwiaXNzIjoidXJuOmFwcDo3ZTBkMTg4OTgyMjY0MzczYTVmMGQ0MTVlYTBkMjZlMCIsIm9iaiI6W1t7ImhlaWdodCI6Ijw9NjE0IiwicGF0aCI6IlwvZlwvNTJiNWQ5MTQtOGVjNC00YjRkLWExYmYtZDY4ODVkZTlmMDljXC9kNnVidnk2LTUwZGNiNDVjLWJiNGUtNDBjYS1hNzY0LThhNWY5YWQwNjc1OC5qcGciLCJ3aWR0aCI6Ijw9MTAyNCJ9XV0sImF1ZCI6WyJ1cm46c2VydmljZTppbWFnZS5vcGVyYXRpb25zIl19.67hHdPxALt59AWH96g-2jzpx5xkWUTDGymId-ZsLEf8",
                "Yog-Sothoth, The Lurker at The Threshold",
                "Yog-Sothoth is a cosmic entity and Outer God. Born of the Nameless Mist, he is the progenitor of Cthulhu, Hastur the Unspeakable and the ancestor of the Voormi.",
                false
            ),

            RecyclerItem(
                "https://i0.wp.com/actualplay.roleplayingpublicradio.com/wp-content/uploads/blackwind.jpg",
                "Nyarlathotep, The Crawling Chaos",
                "Nyarlathotep, known to many by his epithet The Crawling Chaos, is an Outer God in the Cthulhu Mythos. He is the spawn of Azathoth.",
                true
            )
        )
    }
}

