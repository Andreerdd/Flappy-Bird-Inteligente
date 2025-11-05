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

		boolean reinicio;
		int reinicioInt = JOptionPane.showConfirmDialog(null, "Deseja reinício automático do material genético?");
		if(reinicioInt == 0)
			reinicio  = true;
		else if(reinicioInt == 1)
			reinicio  = false;
		else return;

		Populacao.REINICIO_AUTOMATICO = reinicio;
		Populacao.NUMERO_PASSAROS = n;

		Debug.log("NÚMERO DE PORCOS = " + n);
		Debug.log("REINÍCIO AUTOMÁTICO " + (reinicio ? "LIGADO" : "DESLIGADO"));
		PApplet.main("flappy.Flappy");
	}
}
