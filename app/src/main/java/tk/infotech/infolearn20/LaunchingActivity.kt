package tk.infotech.infolearn20

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.BounceInterpolator
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import tk.infotech.infolearn20.activities.HomeActivity

class LaunchingActivity : AppCompatActivity() {

    private lateinit var logo: ImageView
    private lateinit var mWelcomeText: TextView
    private val TIMEOUT = 3500
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

        logo = findViewById(R.id.imageView)
        mWelcomeText = findViewById(R.id.message_intro)
        mLoadingProgressAnim = findViewById(R.id.progressView)

        logo.visibility = View.GONE
        mWelcomeText.visibility = View.GONE
        mLoadingProgressAnim.visibility = View.VISIBLE

        launchAnimation()
     // The handler here is used for delaying the intent code execution for some time.
     // TODO: Could we use coroutines instead?(We will try out)
        Handler().postDelayed({
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
            finish()
        }, TIMEOUT.toLong())

    }

    private fun launchAnimation() {
        mLogoDownwardMovementAnimator = ObjectAnimator()
        mLogoFadeInAnimator = ObjectAnimator()
        mTextFadeInAnimator = ObjectAnimator()
        mTextDownwardMovementAnimator = ObjectAnimator()
        mLogoFadeInAnimator.apply {
            target = logo
            setPropertyName("alpha")
            setFloatValues(0.0f, 1.0f)
            duration = ANIMATION_DURATION.toLong()
            interpolator = AccelerateDecelerateInterpolator()
        }

        // This animation is used for moving the logo downwards

        mLogoDownwardMovementAnimator.apply {
            target = logo
            setPropertyName("translationY")
            setFloatValues(0f, 150f)
            duration = ANIMATION_DURATION.toLong()
            interpolator = AccelerateDecelerateInterpolator()

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

        mTextFadeInAnimator.apply {
            target = mWelcomeText
            setPropertyName("alpha")
            setFloatValues(0.0f, 1.0f)
            duration = ANIMATION_DURATION.toLong()
        }

        mTextDownwardMovementAnimator.apply {
            target = mWelcomeText
            setPropertyName("translationY")
            setFloatValues(0f, 100f)
            duration = ANIMATION_DURATION.toLong()
        }

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
    }
}
