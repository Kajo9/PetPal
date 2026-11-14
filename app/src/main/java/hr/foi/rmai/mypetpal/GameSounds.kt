package hr.foi.rmai.mypetpal

import android.content.Context
import android.media.MediaPlayer

class GameSounds(context: Context) {
    private val hitCookieSound: MediaPlayer = MediaPlayer.create(context, R.raw.hit_cookie_sound)
    private val hitTreatSound: MediaPlayer = MediaPlayer.create(context, R.raw.hit_treat_sound)
    private val hitPoopSound: MediaPlayer = MediaPlayer.create(context, R.raw.hit_poop_sound)

    fun playHitCookieSound() {
        hitCookieSound.start()
    }

    fun playHitTreatSound() {
        hitTreatSound.start()
    }

    fun playHitPoopSound() {
        hitPoopSound.start()
    }
}