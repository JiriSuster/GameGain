import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

class LeaderBoardViewModel @Inject constructor() : ViewModel() {

    private val client = OkHttpClient()
    private val scores = mutableStateOf<List<Pair<String, Int>>>(emptyList())

    fun fetchScores() {
        val request = Request.Builder()
            .url("https://17d2b3c9-1301-4800-a229-8fb77b40cafc-00-143xnzxngzsb2.worf.replit.dev/get_scores")
            .build()

        viewModelScope.launch {
            withContext(Dispatchers.IO) {
                try {
                    client.newCall(request).execute().use { response ->
                        if (!response.isSuccessful) throw IOException("Unexpected code $response")

                        val jsonResponse = response.body?.string()
                        if (!jsonResponse.isNullOrEmpty()) {
                            val jsonObject = JSONObject(jsonResponse)
                            val newScores = mutableListOf<Pair<String, Int>>()
                            for (key in jsonObject.keys()) {
                                val score = jsonObject.getInt(key)
                                newScores.add(Pair(key, score))
                            }
                            scores.value = newScores
                        }
                    }
                } catch (e: IOException) {
                    // Handle network errors
                    e.printStackTrace()
                }
            }
        }
    }

    fun getScores(): State<List<Pair<String, Int>>> {
        return scores
    }
}
