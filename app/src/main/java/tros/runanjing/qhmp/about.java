package tros.runanjing.qhmp;

import android.os.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;

public class about extends AppCompatActivity 
{
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
		Toolbar toolbar=(Toolbar)findViewById(R.id.标题栏);
		setSupportActionBar(toolbar);   
	}
}
