package it.tn.alchemiasoft.casagranda.simone.viewpager;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

/**
 * TUTORIAL: communication between fragments in a ViewPAger
 * 
 * Fragment that supplies container to ask FirstFragment to charge messages
 * 
 * @author Simone Casagranda
 * @category Android Tutorials
 *
 */
public class SecondFragment extends Fragment {

	private EditText mMessageEditText;
	private Button mClearButton;
	
	private MessageLoader mMessageLoader;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		if(activity instanceof MessageLoader){
			mMessageLoader = (MessageLoader) activity;
		}else{
			throw new RuntimeException("You have to pass a MessageLoader");
		}
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflating layout
		View v = inflater.inflate(R.layout.second_fragment, container, false);
		// We obtain layout references
		mMessageEditText = (EditText) v.findViewById(R.id.message_edittext);
		mClearButton = (Button) v.findViewById(R.id.send_button);
		return v;
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		// We set clear listener
		mClearButton.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View v) {
				// We send message
				mMessageLoader.loadMessage(mMessageEditText.getText().toString());
				// We clear view
				mMessageEditText.setText("");
				// We visualize toast in chaining
				Toast.makeText(getActivity(), "Message sended.", Toast.LENGTH_SHORT).show();
			}
		});
	}
}
