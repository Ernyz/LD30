package com.ld30.game.View.UI;

import java.util.concurrent.TimeUnit;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class Timer extends Label{
	private long startTime;
	private TimeUnit t;
	private int i = 1;
	private boolean stopped;

	public Timer(CharSequence text, Skin skin) {
		super(text, skin);
		setFontScale(2f);
		pack();
	}
	
	public void start() {
		startTime = System.currentTimeMillis() * 1;
	}
	
	public void stop () {
		stopped = true;
	}
	
	@Override
	public void act(float delta) {
		super.act(delta);
		
		if (stopped) {
			return;
		}
		
		//TimeUnit.valueOf
		String s;
		
		long time = System.currentTimeMillis() * 1 - startTime;
		long minuteTime = TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS);
		long secondTime = time / 1000;
		long formatedTime;
		
		if(secondTime > 60 * i - 1) {
			i++;
		}
		formatedTime = secondTime - 60 * (i-1);
		
		s = formatedTime >= 10 ? String.valueOf(formatedTime) : "0" + String.valueOf(formatedTime);
		
		setText(
				String.valueOf(minuteTime)
				+ ":"
				+ s);
	}

	public String getFormatedTime () {
		String s;
		
		long time = System.currentTimeMillis() * 1 - startTime;
		long minuteTime = TimeUnit.MINUTES.convert(time, TimeUnit.MILLISECONDS);
		long secondTime = time / 1000;
		long formatedTime;
		
		if(secondTime > 60 * i - 1) {
			i++;
		}
		formatedTime = secondTime - 60 * (i-1);
		
		s = formatedTime >= 10 ? String.valueOf(formatedTime) : "0" + String.valueOf(formatedTime);
		
		return String.valueOf(minuteTime) + ":" + s;
	}
	
}
