package ent.dom.slidingbars;

import android.app.Activity;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.view.animation.Animation;
import android.widget.ImageView;

public class AnimActivity extends Activity {
	//AnimationDrawable rocketAnimation;
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animlayout);
		ImageView rocketImage = (ImageView) findViewById(R.id.rocket_image);
		rocketImage.setBackgroundResource(R.drawable.arrow_down);

		AnimationDrawable rocketAnimation = (AnimationDrawable) rocketImage.getBackground();
		rocketAnimation.start();
	}
}
