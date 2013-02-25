package com.example.dynamiclistview;

public class Helper {
	public String text;
	public int checked;
	Helper(String text,int checked) {
		this.setText(text);
		this.setChecked(checked);
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public int getChecked() {
		return checked;
	}
	public void setChecked(int checked) {
		this.checked = checked;
	}
}
