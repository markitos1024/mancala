package com.mancala.view;

import com.mancala.logic.FinalizedGameMancalaException;
import com.mancala.logic.InvalidMancalaInvalidMoveException;
import com.mancala.logic.Tablero;
import com.vaadin.annotations.Theme;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;

@SuppressWarnings("serial")
@Theme("mancala")
public class MancalaUI extends UI {

	private Tablero tablero = new Tablero();
	private TextField txtMover;
	private Button btnMostrarTablero;
	
	@Override
	protected void init(VaadinRequest request) {
		final VerticalLayout layout = new VerticalLayout();
		layout.setMargin(true);
		setContent(layout);

		btnMostrarTablero = new Button("Tablero");
		btnMostrarTablero.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				int n=0;
				for(Integer i : tablero.getReceptaculos()){
					layout.addComponent(new Label("Posicion"+n+": " +i));
					n++;
				}
			}
		});
		
		 txtMover= new TextField();

		Button btnMover = new Button("Mover");
		btnMover.addClickListener(new Button.ClickListener() {
			public void buttonClick(ClickEvent event) {
				
				try{
					Integer pos = Integer.parseInt(txtMover.getValue());
					
					tablero.mover(pos);
					btnMostrarTablero.click();
				} catch (InvalidMancalaInvalidMoveException e){
					Notification.show("Movimiento Invalido");
				
				} catch (FinalizedGameMancalaException e){
					Notification.show("Partida Finalizada ");
				
				} catch (Exception e){
					Notification.show("La posicion debe ser un numero");
					e.printStackTrace();
			
				}
				
			}
		});
		
		layout.addComponent(btnMostrarTablero);
		layout.addComponent(txtMover);
		layout.addComponent(btnMover);
	}

}