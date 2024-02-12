package com.drg.rustandrevolt.sound

import android.content.Context
import android.media.MediaPlayer
import com.drg.rustandrevolt.R
import kotlin.random.Random

class MusicPlayer(private val context: Context) {

    private var mediaPlayer: MediaPlayer? = null
    private var resourceId: Int = 0

    fun play(inMenu: Boolean) {
        releaseMediaPlayer()
        mediaPlayer = MediaPlayer()

        when{
            inMenu -> resourceId = Music.getRandomMenuMusic()
            else -> resourceId = getCombatMusic()
        }
        val assetFileDescriptor = context.resources.openRawResourceFd(resourceId)

        mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

    fun stop() {
        releaseMediaPlayer()
    }

    private fun releaseMediaPlayer() {
        mediaPlayer?.release()
        mediaPlayer = null
    }

    private fun getCombatMusic(): Int{
        return R.raw.musiccomb1
    }
}