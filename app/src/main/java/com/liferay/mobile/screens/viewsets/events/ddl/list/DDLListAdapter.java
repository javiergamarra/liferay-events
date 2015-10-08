package com.liferay.mobile.screens.viewsets.events.ddl.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.liferay.events.R;
import com.liferay.mobile.screens.base.list.BaseListAdapter;
import com.liferay.mobile.screens.base.list.BaseListAdapterListener;
import com.liferay.mobile.screens.ddl.model.Record;

import java.util.List;

public class DDLListAdapter
	extends BaseListAdapter<Record, DDLListAdapter.TwoTextsViewHolder> {

	public DDLListAdapter(
		int layoutId, int progressLayoutId, BaseListAdapterListener listener) {

		super(layoutId, progressLayoutId, listener);
	}

	@Override
	public TwoTextsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
		LayoutInflater inflater = LayoutInflater.from(parent.getContext());

		View view;

		if (viewType == LAYOUT_TYPE_DEFAULT) {
			view = inflater.inflate(getLayoutId(), parent, false);
		}
		else {
			view = inflater.inflate(getProgressLayoutId(), parent, false);
		}

		return new TwoTextsViewHolder(view, getListener());
	}

	@Override
	protected void fillHolder(Record entry, TwoTextsViewHolder holder) {
		StringBuilder builder = new StringBuilder();

		String titleField = entry.getServerValue("Title");
		holder.textView.setText(titleField);
		holder.subtitleTextView.setText(entry.getServerValue("Subtitle"));
		holder.date.setText(entry.getServerValue("Hour"));
		String room = entry.getServerValue("Room");
		int color = "[\"value 1\"]".equals(room) ? R.color.colorPrimaryDark : R.color.colorAccent;
		holder.room.setBackgroundResource(color);
	}

	public static class TwoTextsViewHolder extends BaseListAdapter.ViewHolder {

		public TextView subtitleTextView;
		public TextView date;
		public View room;

		public TwoTextsViewHolder(View view, BaseListAdapterListener listener) {
			super(view, listener);

			this.subtitleTextView = (TextView) view.findViewById(R.id.liferay_list_subtitle);
			this.date = (TextView) view.findViewById(R.id.liferay_date);
			this.room = view.findViewById(R.id.room);
		}
	}

}