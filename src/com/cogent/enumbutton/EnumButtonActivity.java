package com.cogent.enumbutton;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;

public class EnumButtonActivity extends Activity{
	private Button btnEnum;
	List<ValueName> list = new ArrayList<ValueName>();//所有的数据list

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main);
		initDefaultLists();
		btnEnum = (Button) findViewById(R.id.btnEnum);
		btnEnum.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				SelectView.btn = btnEnum;		
				SelectView.list = list;
				Intent intent = new Intent(EnumButtonActivity.this,SelectView.class);
				startActivity(intent);

			}
		});
	}
	
	private void initDefaultLists() {
		ValueName domain = new ValueName();
		for (int i = 1; i <= 20; i++) {
			domain = new ValueName();
			domain.setName("测试数据" + i);
			domain.setValue(i + "");
			list.add(domain);
		}

	}
}