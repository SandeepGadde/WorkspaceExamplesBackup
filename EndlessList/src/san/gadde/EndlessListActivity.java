package san.gadde;

import java.util.ArrayList;


import android.app.Activity;
import android.content.Context;
import android.database.DataSetObserver;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class EndlessListActivity extends Activity {
    /** Called when the activity is first created. */
	ListAdapter ls;
	Context ctx = null;
	ListView lv= null;
	
	ArrayList<String> names = new ArrayList<String>();
	int totalItems = 0;private boolean loadingMore = false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_INDETERMINATE_PROGRESS);
        setProgressBarIndeterminateVisibility(false);
        setContentView(R.layout.main);
        ctx = this;
        for(int i=0;i<10;i++)
        {
        	names.add("abc");
        }
        ls = new MyAdapter(this,names);
        lv = (ListView)findViewById(R.id.listView1);
        lv.setAdapter(ls);
        lv.setOnScrollListener(new EndlessScrollListener());
    }
    public void preExec()
    {
    	names.add("loading....");
    	lv.invalidateViews();
    }
    public void postExec()
    {
    	//lv.invalidateViews();
    	names.remove(names.size()-1);
    	names.add("Added after refresh..."+totalItems);
    	setProgressBarIndeterminateVisibility(false);
    	loadingMore = false;
    	lv.invalidateViews();
    	
    	Log.d("Added ","String");
    	Log.d("totalItems ",String.valueOf(totalItems));
    }
	public class MyAdapter extends ArrayAdapter
	{
		Context ctx = null;
		public MyAdapter(Context context,ArrayList<String> names) {
			super(context,android.R.layout.simple_list_item_1, names);
			// TODO Auto-generated constructor stub		
			ctx= context;
		}
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			LayoutInflater inflater = (LayoutInflater) ctx
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			View rowView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			TextView textView = (TextView) rowView.findViewById(android.R.id.text1);
			textView.setText(names.get(position));
			return rowView;
		}		
	}
	public class EndlessScrollListener implements OnScrollListener
	{		
	    public EndlessScrollListener() {
	    }
	    
	    public void onScroll(AbsListView view, int firstVisibleItem,
	    		int visibleItemCount, int totalItemCount) 
	    {
	    	totalItems = totalItemCount;
	    	Log.d("onSCroll"," method");
	    	//what is the bottom iten that is visible
            int lastInScreen = firstVisibleItem + visibleItemCount;             

            //is the bottom item visible & not loading more already ? Load more !
            if((lastInScreen == totalItemCount) && (loadingMore)){
            	//lv.invalidateViews(); 
            }
            if((lastInScreen == totalItemCount) && !(loadingMore)){
            	Log.d("Here"," added");
            	new GetDataTask().execute();            	
	            loadingMore = true;
            }
	    }

		public void onScrollStateChanged(AbsListView arg0, int arg1) {
			// TODO Auto-generated method stub
			/*Log.d("onSCroll State Changed"," method");
			if(arg1 == SCROLL_STATE_FLING)
			{
				Log.d("Scrollling ", " Flinged");
			}*/
		}
		
	}
	 private class GetDataTask extends AsyncTask<Void, Void, String[]> {
		 	@Override
		 	protected void onPreExecute(){		 		
		 		setProgressBarIndeterminateVisibility(true);
		 		preExec();		 		
		 	}
	        @Override
	        protected String[] doInBackground(Void... params) {
	            // Simulates a background job.
	            try {
	                Thread.sleep(2000);
	            } catch (InterruptedException e) {
	                ;
	            }
	            return null;
	        }

	        @Override
	        protected void onPostExecute(String[] result) {
	            
	            postExec();
	        }
	    }
}