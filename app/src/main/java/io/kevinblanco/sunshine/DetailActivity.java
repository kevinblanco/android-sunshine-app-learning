package io.kevinblanco.sunshine;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        if (savedInstanceState == null) {
            getFragmentManager().beginTransaction()
                    .add(R.id.container, new DetailViewFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_settings) {

            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * The DetailView Fragment that shows the forecast for a day.
     */
    public static class DetailViewFragment extends Fragment {

        //Object instances
        TextView forecastText;

        public DetailViewFragment() {

        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {

            //We receive the extra from the intent
            Intent intent = getActivity().getIntent();
            View rootView = inflater.inflate(R.layout.fragment_detail, container, false);

            //If we have content on the intent extra
            if( intent != null && intent.hasExtra(Intent.EXTRA_TEXT)){

                //We display it on the Textview
                String forecastReceived = intent.getStringExtra(Intent.EXTRA_TEXT);
                forecastText = (TextView) rootView.findViewById(R.id.detailed_forecast);
                forecastText.setText(forecastReceived);
            }


            return rootView;
        }
    }
}
