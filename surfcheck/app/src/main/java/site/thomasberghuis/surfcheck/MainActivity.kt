package site.thomasberghuis.surfcheck

import android.content.ComponentName
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.media.session.MediaSessionCompat
import android.support.v4.media.session.PlaybackStateCompat
import com.google.android.exoplayer2.ExoPlayerFactory
import com.google.android.exoplayer2.SimpleExoPlayer
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory
import com.google.android.exoplayer2.source.ExtractorMediaSource
import com.google.android.exoplayer2.source.hls.HlsMediaSource
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector
import com.google.android.exoplayer2.ui.PlayerView
import com.google.android.exoplayer2.ui.SimpleExoPlayerView
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory
import com.google.android.exoplayer2.upstream.DefaultHttpDataSourceFactory
import com.google.android.exoplayer2.util.Util

class MainActivity : AppCompatActivity() {

    private var playerView: PlayerView? = null
    private var exoplayer: SimpleExoPlayer? = null
    private var playbackStateBuilder: PlaybackStateCompat.Builder? = null
    private var mediaSession: MediaSessionCompat? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playerView = findViewById(R.id.player_view)
        initializePlayer()
    }


    private fun initializePlayer() {

        exoplayer = SimpleExoPlayer.Builder(this).build();

        playerView?.player = exoplayer

        val userAgent = Util.getUserAgent(baseContext, "Exo")

        val mediaUri = Uri.parse("https://cams.cdn-surfline.com/cdn-au/au-lennoxhead/playlist.m3u8")

        val mediaSource = HlsMediaSource.Factory(DefaultHttpDataSourceFactory(userAgent))
            .createMediaSource(mediaUri)

        exoplayer?.prepare(mediaSource)

        exoplayer?.playWhenReady = true

    }

    override fun onDestroy() {
        super.onDestroy()
        releasePlayer()
    }

    private fun releasePlayer() {
        if (exoplayer != null) {
            exoplayer?.stop()
            exoplayer?.release()
            exoplayer = null
        }
    }

}
