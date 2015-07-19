package client.catan;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

import client.controller.ModelController;
import client.controller.ModelController.ModelControllerListener;
import shared.definitions.*;
import shared.model.player.Player;

@SuppressWarnings("serial")
public class TitlePanel extends JPanel implements ModelControllerListener
{
	private JLabel titleLabel;
	
	public TitlePanel()
	{
		this.setLayout(new BorderLayout());
		this.setBorder(new EmptyBorder(10, 10, 10, 10));
		
		titleLabel = new JLabel("CS 340: Settlers");
		titleLabel.setOpaque(true);
		
		Font font = titleLabel.getFont();
		Font newFont = font.deriveFont(font.getStyle(), 48);
		titleLabel.setFont(newFont);
		
		this.add(titleLabel, BorderLayout.CENTER);
		
		ModelController.getInstance().addListener(this);
	}
	
	public void setLocalPlayerColor(CatanColor value)
	{
		this.setBackground(value.getJavaColor());
		titleLabel.setBackground(value.getJavaColor());
	}

	@Override
	public void ModelChanged() {
		Player clientPlayer = ModelController.getInstance().getClientPlayer();
		if ( clientPlayer != null) {
			Color color = clientPlayer.getColor().getJavaColor();
			setBackground(color);
			titleLabel.setBackground(color);
		}
	}
	
}

