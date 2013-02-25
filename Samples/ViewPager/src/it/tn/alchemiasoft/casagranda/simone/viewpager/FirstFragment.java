package it.tn.alchemiasoft.casagranda.simone.viewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

/**
 * TUTORIAL: communication between fragments in a ViewPAger
 * 
 * Fragment that visualizes messages arrived from fragment two 
 * 
 * @author Simone Casagranda
 * @category Android Tutorials
 *
 */
public class FirstFragment extends Fragment implements MessageLoader{
	
	private TextView mMessageView;
	private Button mClearButton;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflating layout
		View v = inflater.inflate(R.layout.first_fragment, container, false);
		// We obtain layout references
		mMessageView = (TextView) v.findViewById(R.id.message_textview);
		mClearButton = (Button) v.findViewById(R.id.clear_button);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// We set clear listener
		mClearButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// We clear view
				mMessageView.setText("- empty -");
				// We visualize toast in chaining
				Toast.makeText(getActivity(), "Message loader cleared.", Toast.LENGTH_SHORT).show();
			}
		});
	}

	@Override
	public void loadMessage(String message) {
		// We check if the message is empty
		if(TextUtils.isEmpty(message)){
			mMessageView.setText("You have passed an empty message!");
		}else{
			mMessageView.setText(message);
		}
	}

}
