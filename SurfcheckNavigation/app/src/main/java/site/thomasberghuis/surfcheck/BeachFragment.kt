package site.thomasberghuis.surfcheck

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util


class BeachFragment : Fragment() {

    private var playerView: PlayerView? = null
    private var exoplayer: SimpleExoPlayer? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val safeArgs: BeachFragmentArgs by navArgs()
        val beachName = safeArgs.beach
        val url = getBeachUrl(beachName)

        val root = inflater.inflate(R.layout.fragment_beach, container, false)
        setupPlayer(root, url)
        return root
    }

    private fun setupPlayer(v: View, url: String) {
        playerView = v.findViewById(R.id.player_view)
        exoplayer = SimpleExoPlayer.Builder(requireContext()).build();

        playerView?.player = exoplayer

//        val userAgent = Util.getUserAgent(baseContext, "Exo")
        val userAgent = "whatever"

        val mediaUri = Uri.parse(url)

        val mediaSource = HlsMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent))
            .createMediaSource(mediaUri)

        exoplayer?.prepare(mediaSource)

        exoplayer?.playWhenReady = true

    }

    override fun onDestroyView() {
        super.onDestroyView()
        releasePlayer()
    }

    private fun releasePlayer() {
        if (exoplayer != null) {
            exoplayer?.stop()
            exoplayer?.release()
            exoplayer = null
        }
    }


    fun getBeachUrl(beach: String): String {

        // TODO probably use an enum for beaches
        return when (beach) {
            "lennox" -> "https://cams.cdn-surfline.com/cdn-au/au-lennoxhead/playlist.m3u8"
            "byron_pass" -> "https://cams.cdn-surfline.com/cdn-au/au-thepassoverview/playlist.m3u8"
            "shelly" -> "https://cams.cdn-surfline.com/cdn-au/au-ballinashellybeach/playlist.m3u8"


            else -> { // Note the block
                "THIS is wrong"
            }
        }
    }


}
