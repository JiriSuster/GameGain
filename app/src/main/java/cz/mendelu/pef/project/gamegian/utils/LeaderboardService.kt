package cz.mendelu.pef.project.gamegian.utils

import android.util.Log
import com.google.firebase.Firebase
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.firestore

data class LeaderboardEntry(
    val username: String = "",
    val score: Int = 0
)

class LeaderBoardService {

    private val TAG = "LeaderBoardService"
    private val db = Firebase.firestore

    fun saveOrUpdateUserScore(username: String, score: Int) {
        val usersRef = db.collection("Leaderboard")

        usersRef.whereEqualTo("username", username).get().addOnSuccessListener { documents ->
            if (documents.isEmpty) {
                // Create a new entry if the user does not exist
                val newEntry = LeaderboardEntry(username, score)
                usersRef.add(newEntry)
            } else {
                // Update the existing entry
                for (document in documents) {
                    usersRef.document(document.id).update("score", score)
                }
            }
        }.addOnFailureListener { exception ->
            Log.d(TAG, "get failed with ", exception)
        }
    }

    fun getLeaderboard(callback: (List<LeaderboardEntry>) -> Unit) {
        db.collection("Leaderboard")
            .orderBy("score", Query.Direction.DESCENDING)
            .get()
            .addOnSuccessListener { result ->
                val leaderboard = mutableListOf<LeaderboardEntry>()
                for (document in result) {
                    val entry = document.toObject(LeaderboardEntry::class.java)
                    leaderboard.add(entry)
                }
                callback(leaderboard)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
    }

    fun usernameExists(username: String, callback: (Boolean) -> Unit) {
        db.collection("Leaderboard")
            .whereEqualTo("username", username)
            .get()
            .addOnSuccessListener { result ->
                callback(!result.isEmpty)
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error checking username existence.", exception)
                callback(false)
            }
    }
}
