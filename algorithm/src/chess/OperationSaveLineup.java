/**
 * Author by metaphy
 * Date Feb 26, 2008
 * All Rights Reserved.
 */
package chess;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;

import org.apache.commons.beanutils.BeanComparator;

import chess.Player.Located;


public class OperationSaveLineup implements ActionListener {

	public void actionPerformed(ActionEvent e) {
		MainFrame parent = MainFrame.me;
		if (parent.getGameState() == 0 || parent.getGameState() == 1) {
			JFileChooser chooser = new JFileChooser();
			chooser.setCurrentDirectory(new File("c:\\"));
			chooser.setFileSelectionMode(JFileChooser.FILES_ONLY);  
			chooser.setMultiSelectionEnabled(false);  
			chooser.setFileFilter(new LineupFileFilter());
			chooser.setSelectedFile(new File("c:\\abc.military"));
			
             int isSelect = chooser.showSaveDialog(parent);  
             if (isSelect == JFileChooser.APPROVE_OPTION)  
             {  
                File savedFile ;
                if (chooser.getSelectedFile().getAbsolutePath().endsWith(".military")) {
                	savedFile = new File(chooser.getSelectedFile().getAbsolutePath());
                }else{
                	savedFile = new File(chooser.getSelectedFile().getAbsolutePath()+".military");
                } 
                
     			PrintWriter out;
     			try {
     				out = new PrintWriter(new BufferedWriter(new FileWriter(savedFile)));
     				List<Chessman> list = new ArrayList<Chessman>();
     				
     				for(String key : MainPanel.figureMap.keySet()){
     					ChessmanFigure cf = MainPanel.figureMap.get(key);
     					list.add(cf.getChessman());
     				}
     				
     				Collections.sort(list, new BeanComparator("coordinate.coordinateValue"));
     				
//     				for(Chessman chessman : list){
//     					System.out.println(chessman.getCaption());
//     				}
     				
     				for(int j=0; j<list.size(); j++){
     					int i=j+1;
     					if(i==5 || i==8 || i==12 || i==15 || i==20){
     						out.print(list.get(j).getCaption());
     						out.println();
     					}else if(i==25){
     						out.print(list.get(j).getCaption());
     					}else{
     						out.print(list.get(j).getCaption()+",");
     					}
     				}
     				
     				out.close();
     			} catch (Exception e1) {
     				// TODO Auto-generated catch block
     				e1.printStackTrace();
     			}
             }  
			
		}
	}

	private class LineupFileFilter extends FileFilter {
		@Override
		public boolean accept(File f) {
			return f.getName().toLowerCase().endsWith(".military") || f.isDirectory();
		}

		@Override
		public String getDescription() {
			return null;
		}
	}
}
