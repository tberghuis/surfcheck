package site.thomasberghuis.surfcheck

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs


class BeachFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val safeArgs: BeachFragmentArgs by navArgs()
        val beachName = safeArgs.beach
        val root = inflater.inflate(R.layout.fragment_beach, container, false)

        return root
    }


    fun getBeachUrl(beach: String): String {

        // TODO probably use an enum for beaches
        return when (beach) {
            "lennox" -> "https://cams.cdn-surfline.com/cdn-au/au-lennoxhead/playlist.m3u8"
            "byron_pass" -> "https://cams.cdn-surfline.com/cdn-au/au-thepassoverview/playlist.m3u8"
            else -> { // Note the block
                "THIS is wrong"
            }
        }
    }


}
