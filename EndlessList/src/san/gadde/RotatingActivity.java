package san.gadde;



import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;

public class RotatingActivity extends Activity{
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.rotating);
        final ImageView rotatingButton = (ImageView) findViewById(R.id.rotatingButton);
        SeekBar seekBar = (SeekBar) findViewById(R.id.rotationY);
        seekBar.setMax(360);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {

            public void onStopTrackingTouch(SeekBar seekBar) {
            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onProgressChanged(SeekBar seekBar, int progress,
                    boolean fromUser) {
                // prevent seeking on app creation
                rotatingButton.setRotationY((float)progress);
            }
        });
	}
}
