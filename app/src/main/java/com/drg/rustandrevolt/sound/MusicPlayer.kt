package com.drg.rustandrevolt.sound

import android.content.Context
import android.media.MediaPlayer
import com.drg.rustandrevolt.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import kotlin.random.Random

class MusicPlayer @Inject constructor(
    @ApplicationContext private val context: Context
) {

    private var mediaPlayer: MediaPlayer? = null
    private var resourceId: Int = 0

    fun play(inMenu: Boolean) {
        releaseMediaPlayer()
        mediaPlayer = MediaPlayer()

        when{
            inMenu -> resourceId = Music.getRandomMenuMusic()
            else -> resourceId = Music.getRandomCombatMusic()
        }
        val assetFileDescriptor = context.resources.openRawResourceFd(resourceId)

        mediaPlayer?.setDataSource(assetFileDescriptor.fileDescriptor, assetFileDescriptor.startOffset, assetFileDescriptor.length)
        mediaPlayer?.prepare()
        mediaPlayer?.start()
    }

    fun playFX(resourceId: Int) {
        releaseMediaPlayer()
        mediaPlayer = MediaPlayer()

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
}