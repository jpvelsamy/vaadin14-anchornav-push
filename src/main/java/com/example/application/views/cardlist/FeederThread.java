package com.example.application.views.cardlist;

import com.vaadin.flow.component.Component;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.notification.Notification;

public class FeederThread extends Thread {

	private final UI ui;
	private CardListView view;
	private Integer currentValue=0;
	//private int count = 1;

	public FeederThread(UI ui, CardListView view) {
		this.ui = ui;
		this.view = view;
	}

	@Override
	public void run() {

		while(true) 
		{
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		/*
		 * if(currentValue>0 && currentValue>view.getCounter().get()) ui.access(() ->
		 * view.add(update(view))); else { currentValue=view.getCounter().get()+10; }
		 */
		if(view.getCounter().get()>0)
		ui.access(() ->  view.add(update(view)));
		}

	}

	public Component update(CardListView view) {		
		int value = view.getCounter().get();
		if(value%2==0)
		view.getGrid1().getStyle().set("background-color", "pink");
		if(value%3==0)
			view.getGrid2().getStyle().set("background-color", "blue");
		if(value%4==0)
			view.getGrid3().getStyle().set("background-color", "red");
		if(value%5==0)
			view.getGrid4().getStyle().set("background-color", "orange");
		
		return Notification.show("Push notification "+value);
	}

}
