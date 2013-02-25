package ent.dom.slidingbars;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.Window;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends Activity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.videoview);		
		VideoView v = (VideoView)findViewById(R.id.videoView);
		/*String fileName = "android.resource://" + getPackageName() + "/" + R.raw.wildlife;		
		Uri uri = Uri.parse();
		v.setMediaController(new MediaController(this));
		v.setVideoURI(uri);
		v.start();*/
		
		MediaController mediaController= new MediaController(this);
	    mediaController.setAnchorView(v);        
	    Uri uri=Uri.parse("android.resource://"+getPackageName()+"/"+R.raw.documentariesandyou);        
	    v.setMediaController(mediaController);
	    v.setVideoURI(uri);        
	    v.requestFocus();

	    v.start();
	}
}
