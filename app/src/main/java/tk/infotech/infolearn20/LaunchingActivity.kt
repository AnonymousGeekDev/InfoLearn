package tk.infotech.infolearn20

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import tk.infotech.infolearn20.activities.HomeActivity

class LaunchingActivity : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var mWelcomeText: TextView
    private lateinit var mLoadingProgressAnim: ProgressBar
    private val ANIMATION_DURATION = 1500
    private lateinit var mLogoFadeInAnimator: ObjectAnimator
    private lateinit var mLogoDownwardMovementAnimator: ObjectAnimator
    private lateinit var mTextFadeInAnimator: ObjectAnimator
    private lateinit var mTextDownwardMovementAnimator: ObjectAnimator
    private lateinit var mScaleAndBounceAnimator: ViewPropertyAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_launching)


        //The coroutine scope in which we will launch our coroutine.
        val coroutineScope = CoroutineScope(Dispatchers.Main)

        //Do we really need to use findViewById to have access to these views? We could just use Kotlin Synthetic Lib.
        //TODO: Replace findViewById calls and use kotlin synthetics as a replacement.
        logo = findViewById(R.id.imageView)
        mWelcomeText = findViewById(R.id.message_intro)
        mLoadingProgressAnim = findViewById(R.id.progressView)

        logo.visibility = View.GONE
        mWelcomeText.visibility = View.GONE
        mLoadingProgressAnim.visibility = View.VISIBLE

        launchAnimation()
     // The coroutine used here is for delaying the intent code execution for some time.


        coroutineScope.launch {
            delay(ANIMATION_DURATION.toLong())
            val intent = Intent(this@LaunchingActivity, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }

    }

    private fun launchAnimation() {

        //Initialization of animators. Still considering possibility of using ObjectAnimator.ofFloat(...) for animations.

        mLogoDownwardMovementAnimator = ObjectAnimator.ofFloat(logo, "translationY", 0f, 70f)
        mLogoFadeInAnimator = ObjectAnimator.ofFloat(logo, "alpha", 0.0f, 1.0f)
        mTextFadeInAnimator = ObjectAnimator.ofFloat(mWelcomeText, "alpha", 0.0f, 1.0f)
        mTextDownwardMovementAnimator = ObjectAnimator.ofFloat(mWelcomeText, "translationY", 0f, 90f)

        mLogoDownwardMovementAnimator.apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = ANIMATION_DURATION.toLong()
        }

        mLogoFadeInAnimator.apply {
            interpolator = AccelerateDecelerateInterpolator()
            duration = ANIMATION_DURATION.toLong()
        }

        mTextFadeInAnimator.apply {
            duration = ANIMATION_DURATION.toLong()
        }

        mTextDownwardMovementAnimator.apply {
          //  interpolator = AccelerateDecelerateInterpolator()
            duration = ANIMATION_DURATION.toLong()
        }


        mScaleAndBounceAnimator = logo.animate().apply {
            scaleX(1.5f)
            scaleY(1.5f)
            interpolator = BounceInterpolator()
            duration = ANIMATION_DURATION.toLong()
        }


        //The animation used for 'zooming' the logo in, while adding a bounce effect.
        //Using @ViewPropertyAnimator for conciseness purposes, and for compatibility testing.
        //TODO Write instrumentation tests for the animators

        val set = AnimatorSet()
        set.playTogether(
            mLogoFadeInAnimator,
            mLogoDownwardMovementAnimator,
            mTextFadeInAnimator,
            mTextDownwardMovementAnimator
        )
        mScaleAndBounceAnimator.start()
        set.start()

        logo.visibility = View.VISIBLE
        mWelcomeText.visibility = View.VISIBLE


    }
}
