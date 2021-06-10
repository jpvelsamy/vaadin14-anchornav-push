package com.example.application.views.cardlist;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import com.example.application.views.main.MainView;
import com.vaadin.flow.component.AttachEvent;
import com.vaadin.flow.component.DetachEvent;
import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.grid.GridVariant;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.IronIcon;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.AfterNavigationEvent;
import com.vaadin.flow.router.AfterNavigationObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;

@Route(value = "card-list", layout = MainView.class)
@PageTitle("Card List")
public class CardListView extends Div implements AfterNavigationObserver {

    Grid<Person> grid1 = new Grid<>();
    Grid<Person> grid2 = new Grid<>();
    Grid<Person> grid3 = new Grid<>();
    Grid<Person> grid4 = new Grid<>();
    private FeederThread thread;
    private AtomicInteger counter = new AtomicInteger(0);
    
    public CardListView() {
        addClassName("card-list-view");
        setSizeFull();
        grid1.setHeight("100%");
        grid1.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid1.addComponentColumn(person -> createCard(person));
        
        grid2.setHeight("100%");
        grid2.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid2.addComponentColumn(person -> createCard(person));
        
        grid3.setHeight("100%");
        grid3.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid3.addComponentColumn(person -> createCard(person));
        
        grid4.setHeight("100%");
        grid4.addThemeVariants(GridVariant.LUMO_NO_BORDER, GridVariant.LUMO_NO_ROW_BORDERS);
        grid4.addComponentColumn(person -> createCard(person));
        
        HorizontalLayout layout = new HorizontalLayout();
        layout.setSizeFull();
        layout.setId("successlayout");
        layout.addClickListener(listener -> {
			//System.out.println("Success listener clicked...");
        	counter.incrementAndGet();        	
			//Notification.show("Helooooo Success listener clicked...");
			
		});
        
        layout.setSpacing(true);
        layout.add(grid1, grid2, grid3, grid4);
        //layout.add(grid1);
        add(layout);
       //add(grid1);
    }

    private HorizontalLayout createCard(Person person) {
        HorizontalLayout card = new HorizontalLayout();
        card.addClassName("card");
        card.setSpacing(false);
        card.getThemeList().add("spacing-s");

        Image image = new Image();
        image.setSrc(person.getImage());
        VerticalLayout description = new VerticalLayout();
        description.addClassName("description");
        description.setSpacing(false);
        description.setPadding(false);

        HorizontalLayout header = new HorizontalLayout();
        header.addClassName("header");
        header.setSpacing(false);
        header.getThemeList().add("spacing-s");

        Span name = new Span(person.getName());
        name.addClassName("name");
        Span date = new Span(person.getDate());
        date.addClassName("date");
        header.add(name, date);

        Span post = new Span(person.getPost());
        post.addClassName("post");

        HorizontalLayout actions = new HorizontalLayout();
        actions.addClassName("actions");
        actions.setSpacing(false);
        actions.getThemeList().add("spacing-s");

        IronIcon likeIcon = new IronIcon("vaadin", "heart");
        Span likes = new Span(person.getLikes());
        likes.addClassName("likes");
        IronIcon commentIcon = new IronIcon("vaadin", "comment");
        Span comments = new Span(person.getComments());
        comments.addClassName("comments");
        IronIcon shareIcon = new IronIcon("vaadin", "connect");
        Span shares = new Span(person.getShares());
        shares.addClassName("shares");

        actions.add(likeIcon, likes, commentIcon, comments, shareIcon, shares);

        description.add(header, post, actions);
        card.add(image, description);
        return card;
    }

    @Override
    public void afterNavigation(AfterNavigationEvent event) {

        // Set some data when this view is displayed.
        List<Person> persons = Arrays.asList( //
                createPerson("https://randomuser.me/api/portraits/men/42.jpg", "John Smith", "May 8",
                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/42.jpg", "Abagail Libbie", "May 3",
                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/men/24.jpg", "Alberto Raya", "May 3",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/24.jpg", "Emmy Elsner", "Apr 22",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/men/76.jpg", "Alf Huncoot", "Apr 21",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/76.jpg", "Lidmila Vilensky", "Apr 17",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/men/94.jpg", "Jarrett Cawsey", "Apr 17",
                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/94.jpg", "Tania Perfilyeva", "Mar 8",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/men/16.jpg", "Ivan Polo", "Mar 5",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/16.jpg", "Emelda Scandroot", "Mar 5",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/men/67.jpg", "Marcos Sá", "Mar 4",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20"),
                createPerson("https://randomuser.me/api/portraits/women/67.jpg", "Jacqueline Asong", "Mar 2",

                        "In publishing and graphic design, Lorem ipsum is a placeholder text commonly used to demonstrate the visual form of a document without relying on meaningful content (also called greeking).",
                        "1K", "500", "20")

        );

        grid1.setItems(persons);
        grid2.setItems(persons);
        grid3.setItems(persons);
        grid4.setItems(persons);
           
    }

    private static Person createPerson(String image, String name, String date, String post, String likes,
            String comments, String shares) {
        Person p = new Person();
        p.setImage(image);
        p.setName(name);
        p.setDate(date);
        p.setPost(post);
        p.setLikes(likes);
        p.setComments(comments);
        p.setShares(shares);

        return p;
    }
    
    @Override
	protected void onAttach(AttachEvent attachEvent) {
    	
    	thread = new FeederThread(attachEvent.getUI(), this);
    	thread.setDaemon(true);
    	thread.setName("Feeder thread");
		thread.start();
		System.out.println(attachEvent.toString() + "Inside attachevent");
		
    	
    }
    
    @Override
	protected void onDetach(DetachEvent detachEvent) {
    	thread.interrupt();
		thread = null;
		
	}

	public Grid<Person> getGrid1() {
		return grid1;
	}

	public void setGrid1(Grid<Person> grid1) {
		this.grid1 = grid1;
	}

	public Grid<Person> getGrid2() {
		return grid2;
	}

	public void setGrid2(Grid<Person> grid2) {
		this.grid2 = grid2;
	}

	public Grid<Person> getGrid3() {
		return grid3;
	}

	public void setGrid3(Grid<Person> grid3) {
		this.grid3 = grid3;
	}

	public Grid<Person> getGrid4() {
		return grid4;
	}

	public void setGrid4(Grid<Person> grid4) {
		this.grid4 = grid4;
	}

	public AtomicInteger getCounter() {
		return counter;
	}
    
    

}
