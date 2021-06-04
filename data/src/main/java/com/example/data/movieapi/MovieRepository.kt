import android.util.Log
import com.example.data.movieapi.MovieDataClass
import com.example.data.movieapi.MovieIndexDataClass
import com.example.data.movieapi.MovieInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MovieRepository {

    val BASE_URL: String = "https://api.themoviedb.org/3/"

    fun getItemData(callback: (List<MovieDataClass>) -> Unit) {     //pass a variable?
        val retrofitBuilder = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(MovieInterface::class.java)

        val retrofitData = retrofitBuilder.getData()

        retrofitData.enqueue(object : Callback<MovieIndexDataClass?> {
            override fun onFailure(call: Call<MovieIndexDataClass?>, t: Throwable) {
                Log.d("MainActivity", "onFailure: " + t.message)
            }

            override fun onResponse(
                call: Call<MovieIndexDataClass?>,
                response: Response<MovieIndexDataClass?>
            ) {
                callback(response.body()?.results?: emptyList())
            }
        })
    }
}