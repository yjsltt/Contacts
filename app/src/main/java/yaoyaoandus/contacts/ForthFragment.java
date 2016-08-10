package yaoyaoandus.contacts;

/**
 * Created by LJY on 16/8/10.
 */

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
      * A placeholder fragment containing a simple view.
      */
public  class ForthFragment extends android.support.v4.app.Fragment {

    public ForthFragment() { }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
        View rootView = inflater.inflate(R.layout.fragment_4, container, false);
        TextView textView = (TextView) rootView.findViewById(R.id.textview_frag4);
        textView.setText("个人设置");
        return rootView;
    }
}