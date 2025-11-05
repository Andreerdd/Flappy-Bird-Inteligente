package flappy;

import processing.core.PApplet;

import javax.swing.*;
import java.awt.*;

public class Main {
	public static void main(String[] args) {
		int n = -1;
		while(n < 1) {
			try {
				String data = JOptionPane.showInputDialog("Número inicial de porcos:");
				if(data == null) return;
				n = Integer.parseInt(data);
			} catch(NumberFormatException e) {
				JOptionPane.showMessageDialog(null, "Valor inválido.");
			} catch(HeadlessException e) {
				return;
			}
		}
		Populacao.NUMERO_PASSAROS = n;
		PApplet.main("flappy.Flappy");
	}
}
