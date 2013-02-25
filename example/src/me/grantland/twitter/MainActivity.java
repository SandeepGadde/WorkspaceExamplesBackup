package me.grantland.twitter;

import me.grantland.twitter.Twitter.DialogListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity implements OnClickListener {

    public static final String CONSUMER_KEY    = "MjW9VGieLpBdWBdfCdA3gw";
    public static final String CONSUMER_SECRET = "F9j7rTP58XlGfzv1VOQ7DV1zZ49tRQewH0OfM02AAyc";

    private Twitter mTwitter;

	private Button mTwitterButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        mTwitter = new Twitter(CONSUMER_KEY, CONSUMER_SECRET);

		mTwitterButton = (Button)findViewById(R.id.twitter_login);
		mTwitterButton.setOnClickListener(this);
	}

	public void onClick(View v) {
		if (v.equals(mTwitterButton)) {
		    mTwitter.authorize(this, new DialogListener() {
		        public void onComplete(String accessKey, String accessSecret) {
		            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		            builder.setTitle("Success")
		                   .setMessage("access_key: " + accessKey
		                        + "\naccess_secret: " + accessSecret)
		                   .setPositiveButton("Ok", null);
		            AlertDialog alert = builder.create();
		            alert.show();
		        }

		        public void onCancel() {
		            AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
		            builder.setTitle("Canceled")
		                   .setMessage("Twitter Login Canceled")
		                   .setPositiveButton("Ok", null);
		            AlertDialog alert = builder.create();
		            alert.show();
		        }

		        public void onError(TwitterError error) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("Error")
                           .setMessage(error.getMessage())
                           .setPositiveButton("Ok", null);
                    AlertDialog alert = builder.create();
                    alert.show();
		        }
		    });
		}
	}

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Twitter Auth Callback
        mTwitter.authorizeCallback(requestCode, resultCode, data);
    }
}
