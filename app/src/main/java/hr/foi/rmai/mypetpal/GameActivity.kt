package hr.foi.rmai.mypetpal

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.ComponentActivity
import androidx.lifecycle.lifecycleScope
import hr.foi.rmai.mypetpal.database.GameDAO
import hr.foi.rmai.mypetpal.database.MyPetPalDatabase
import hr.foi.rmai.mypetpal.entities.Game
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.math.floor

class GameActivity : ComponentActivity() {
    //frame
    private var gameFrame: FrameLayout? = null
    private var frameHeight = 0
    private var frameWidth = 0
    private var startLayout: LinearLayout? = null

    //Image
    private var player: ImageView? = null
    private var poop: ImageView? = null
    private var cookie: ImageView? = null
    private var treat: ImageView? = null

    //Size
    private var playerSize = 0

    //Position
    private var playerX = 0f
    private var playerY = 0f
    private var poopX = 0f
    private var poopY = 0f
    private var cookieX = 0f
    private var cookieY = 0f
    private var treatX = 0f
    private var treatY = 0f

    //Score
    private var scoreLabel: TextView? = null
    private var highScoreLabel: TextView? = null
    private var gameOverLabel: TextView? = null
    private var score = 0
    private lateinit var highScoreDao: GameDAO

    //Class
    private var soundPlayer: GameSounds? = null
    private var gameJob: Job? = null

    //Status
    private var start_flg = false
    private var treat_flg = false
    private var timeCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_game)
        soundPlayer = GameSounds(this)
        gameFrame = findViewById(R.id.gameFrame)
        startLayout = findViewById(R.id.startLayout)
        player = findViewById(R.id.player)
        poop = findViewById(R.id.poop)
        cookie = findViewById(R.id.cookie)
        treat = findViewById(R.id.treat)
        highScoreDao = MyPetPalDatabase.getInstance(this).getGameDao()
        scoreLabel = findViewById(R.id.scoreLabel)
        highScoreLabel = findViewById(R.id.highScoreLabel)
        gameOverLabel = findViewById(R.id.gameOver)
        loadHighScore()
    }
    private fun loadHighScore() {
        lifecycleScope.launch {
            val highScore = withContext(Dispatchers.IO) { highScoreDao.getHighScore() } ?: 0
            highScoreLabel!!.text = buildString {
                append("High Score:")
                append(highScore)
            }
        }
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        if (start_flg) {
            if (event.action == MotionEvent.ACTION_DOWN || event.action == MotionEvent.ACTION_MOVE) {
                playerX = event.x - player!!.width / 2
            }
        }
        return true
    }

    fun changePos() {
        //Add timerCount
        timeCount += 20
        cookieY += 12f
        val cookieCenterX = cookieX + cookie!!.width / 2
        val cookieCenterY = cookieY + cookie!!.width / 2
        if (hitCheck(cookieCenterX, cookieCenterY)) {
            cookieY = (frameHeight + 100).toFloat()
            score += 10
            soundPlayer?.playHitCookieSound()
        }
        if (cookieY > frameHeight) {
            cookieY = -100f
            cookieX =
                floor(Math.random() * (frameWidth - cookie!!.width)).toFloat()
        }
        cookie!!.x = cookieX
        cookie!!.y = cookieY
        //treat
        if (!treat_flg && timeCount % 10000 == 0) {
            treat_flg = true
            treatY = -20f
            treatX = floor(Math.random() * (frameWidth - treat!!.width)).toFloat()
        }
        if (treat_flg) {
            treatY += 20f
            val treatCenterX = treatX + treat!!.width / 2
            val treatCenterY = treatY + treat!!.width / 2
            if (hitCheck(treatCenterX, treatCenterY)) {
                score += 30
                soundPlayer?.playHitTreatSound()
            }
            if (treatY > frameHeight) treat_flg = false
            treat!!.x = treatX
            treat!!.y = treatY
        }
        //poop
        poopY += 18f
        val poopCenterX = poopX + poop!!.width / 2
        val poopCenterY = poopY + poop!!.height / 2
        if (hitCheck(poopCenterX, poopCenterY)) {
            soundPlayer?.playHitPoopSound()
            gameOver()
        }
        if (poopY > frameHeight) {
            poopY = -100f
            poopX = floor(Math.random() * (frameWidth - poop!!.width)).toFloat()
        }
        poop!!.x = poopX
        poop!!.y = poopY

        //Check player position
        if (playerX < 0) {
            playerX = 0f
        }
        if (frameWidth - playerSize < playerX) {
            playerX = (frameWidth - playerSize).toFloat()
        }
        player!!.x = playerX
        scoreLabel!!.text = buildString {
            append("Score:")
            append(score)
        }
    }

    fun hitCheck(x: Float, y: Float): Boolean {
        return playerX <= x && x <= playerX + playerSize && playerY <= y && y <= frameHeight
    }

    fun gameOver() {
        gameJob?.cancel()
        start_flg = false
        gameOverLabel!!.visibility = View.VISIBLE
        Handler(Looper.getMainLooper()).postDelayed({
            // This code will be executed after 3 seconds
            gameOverLabel!!.visibility = View.INVISIBLE
            startLayout!!.visibility = View.VISIBLE
            player!!.setVisibility(View.INVISIBLE)
            poop!!.setVisibility(View.INVISIBLE)
            cookie!!.setVisibility(View.INVISIBLE)
            treat!!.setVisibility(View.INVISIBLE)
            updateHighScore(score)
        }, 3000) // delay in milliseconds
    }
    private fun updateHighScore(currentScore: Int) {
        lifecycleScope.launch {
            val highScore = withContext(Dispatchers.IO) { highScoreDao.getHighScore() } ?: 0
            if (currentScore > highScore) {
                withContext(Dispatchers.IO) {
                    highScoreDao.updateHighScore(Game(highScore = currentScore))
                }
                highScoreLabel!!.text = buildString {
                    append("High Score:")
                    append(currentScore)
                }
            }
        }
    }

    fun startGame(view: View?) {
        start_flg = true
        startLayout!!.visibility = View.INVISIBLE
        if (frameHeight == 0) {
            frameHeight = gameFrame!!.height
            frameWidth = gameFrame!!.width
            playerSize = player!!.width
            playerX = player!!.x
            playerY = player!!.y
        }
        player!!.x = 0.0f
        poop!!.y = 3000.0f
        cookie!!.y = 3000.0f
        treat!!.y = 3000.0f
        poopY = poop!!.y
        cookieX = poop!!.y
        treatY = poop!!.y
        player!!.setVisibility(View.VISIBLE)
        poop!!.setVisibility(View.VISIBLE)
        cookie!!.setVisibility(View.VISIBLE)
        treat!!.setVisibility(View.VISIBLE)
        timeCount = 0
        score = 0
        scoreLabel!!.text = buildString {
            append("Score : 0")
        }
        gameJob?.cancel()
        gameJob = lifecycleScope.launch {
            while (start_flg) {
                delay(20) // delay for 20 milliseconds
                changePos()
            }
        }
    }
}