package com.cogent.enumbutton;

import java.util.ArrayList;
import java.util.List;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

/** 数据选择控件，相对于Spinner控件，增加了筛选功能*/
public class SelectView extends Activity{
	/** 用来接收选择结果*/
	public static Button btn;
	/** 用来展示提供选择的数据源*/
	public static List<ValueName> list = new ArrayList<ValueName>();
	
	private EditText edit_search;
	private ListView listView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.select_view);
		initControl();

	}

	private void initControl() {
		edit_search = (EditText) findViewById(R.id.edit_search);
		edit_search.addTextChangedListener(new TextWatcher_edit_search());
		
		SelectViewAdapter adapter = new SelectViewAdapter(this, list);		
		listView = (ListView) findViewById(R.id.list);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new onclick());
	}


	/** 
	 * 对传入的数据对象进行筛选
	 * @param str 用来进行匹配筛选的字符
	 */
	private List<ValueName> selectData(String str) {
		List<ValueName> templist = new ArrayList<ValueName>();
		
		for (int i = 0; i < list.size(); i++) {
			ValueName domain = list.get(i);
			if (domain.getName().contains(str))
				templist.add(domain);
		}
		return templist;
	}

	class onclick implements OnItemClickListener {

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
			SelectViewAdapter adapter = (SelectViewAdapter) listView.getAdapter();
			ValueName valueName = adapter.getItem(position);
			btn.setText(valueName.getName());
			SelectView.this.finish();
		}

	}

	class TextWatcher_edit_search implements TextWatcher {

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count,int after) {}

		@Override
		public void onTextChanged(CharSequence s, int start, int before,int count) {}

		@Override
		public void afterTextChanged(Editable s) {
			List<ValueName> tempList = selectData(edit_search.getText().toString());
			SelectViewAdapter adapter = new SelectViewAdapter(SelectView.this,tempList);
			listView.setAdapter(adapter);
		}

	}

	public class SelectViewAdapter extends BaseAdapter {
		private Context context;
		private List<ValueName> data;

		public SelectViewAdapter(Context context, List<ValueName> data) {
			this.context = context;
			this.data = data;
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public ValueName getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				LayoutInflater inflater = LayoutInflater.from(context);
				convertView = inflater.inflate(R.layout.select_view_item, null);
			}

			TextView textView = (TextView) convertView.findViewById(R.id.txt);
			textView.setText(data.get(position).getName());
			return convertView;
		}

	}
}
