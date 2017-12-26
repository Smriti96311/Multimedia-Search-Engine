
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.*;
import java.awt.Font;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import javax.imageio.ImageIO;
//import javax.imageio.ImageIO;
public class Image_Search extends JFrame implements ActionListener{

	private static final long serialVersionUID = 1L;
	static JButton jb;
	static JLabel label ,label1;
	static JFileChooser jf;
	static JLabel jl,jl1,jl2,jl3;
	static	JPanel jp,jp1;
 JTable table;

DefaultTableModel dtm = new DefaultTableModel(0, 0);
String[] columns = new String[] { "Image", "Path", "Percentage Of Result Images" };
	 
static JScrollPane js;

	float[][] onePercentage ;
	//ArrayList character;
	char[] character = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
			'A','B','C','D','E','F','G','H','I','J','K','L','M','N','O','P','Q','R','S','T','U','V','W','X','Y','Z'};
	
public Image_Search(){
	 jb=new JButton("Browse");
	jl=new JLabel();
	jl1=new JLabel();
	jl2=new JLabel();
	jl3=new JLabel();
	label=new JLabel();
	jp=new JPanel();
	jp1=new JPanel();
	
	jf=new JFileChooser();
table= new JTable(){
	 public Class getColumnClass(int column)
     {
         return getValueAt(0, column).getClass();
     }
};

table.setRowHeight(85);

table.setPreferredScrollableViewportSize(new Dimension(1100,470));
table.setFillsViewportHeight(true);


js=new JScrollPane(table);

jp.add(js);
jp1.setBackground(Color.DARK_GRAY);

	  
dtm.setColumnIdentifiers(columns);

    table.setModel(dtm);

    
	
	 add(jb);
	 add(jl);
	 add(jl1);
	 add(jl2);
	 add(jl3);
	add(jp);
	
	
	add(jp1);
	add(label);
	

	
	 jb.addActionListener(this);
	 jb.setBounds(20,20 , 100, 50);
	 jl.setBounds(20,60,400,100);
	 jl.setFont(new Font("Courier New", Font.ITALIC, 12));
	    jl.setForeground(Color.DARK_GRAY);
	    jl3.setForeground(Color.DARK_GRAY);
	 jl1.setBounds(20, 80,400, 100);
	 jl1.setFont(new Font("Courier New", Font.ITALIC, 12));
	    jl1.setForeground(Color.BLUE);
	
	 jl2.setBounds(50, 20, 30, 30);
	 jl3.setBounds(20,140,400,20);
	 label.setBounds(350,20 ,100, 100);

    
	  TableColumnModel tcm = table.getColumnModel();
	    tcm.getColumn(0).setPreferredWidth(150); 
	    tcm.getColumn(1).setPreferredWidth(400); 
	    tcm.getColumn(2).setPreferredWidth(400); 


	 jp.setBounds(10,160,1180,500);
	 jp.setBackground(Color.GREEN);
	 
//setPreferredSize(this.getParent().getPreferredSize());
	 
	 jp1.setBounds(870,20 ,130, 120);

	 
	onePercentage = new float[10][52];


}
	
//The function that calculates the 1's %age of an image and returns the 1's percentage to the "compute" function.
//private float calculate(String string) {
private static float calculatePercentageOfOne(String fileName){
			File f =new File(fileName);
			BufferedImage img = null; 
				int c=0;
				float one=0, one_per=0;
				try {
					img = ImageIO.read(f);
					int p= img.getWidth();
					int q=img.getHeight();
					 int[][] pixel=new int[p][q];
		      	     
				      for(int i=0;i<p;i++)
				      {
				    	  for(int j=0;j<q;j++)
				    	  {
				    		 c++;
				    		       
				    		  pixel[i][j] = img.getRGB(i,j);
				    		
				    		    }
				    	 
				      }
				     
					for(int i=0;i<p;i++)
				      {
				    	  for(int j=0;j<q;j++)
				    	  {
				    		  if(pixel[i][j]==-1){
				    			  }
				    		  
				    		  else{
				    			  one++;
				    			//  System.out.print("1");
				    			  }
				    		  
				    			  
				    		 
				    	  }
				   
				      one_per=(one/c)*100;
		 			 
		 

				} }catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			return one_per;
		}
		//Function for reading image files from a directory and returning the file name as string,example  'a1.png' ,to a string array
public String[] readFileNames(String directoryPath) {  
	
	
	int count=0,count1=0;int fileCount = 0;
	
	Object[][] newarray1=new Object[3][260];
	File dir = new File(directoryPath);
	ArrayList<String> files  =new ArrayList<String>();
	
	if(dir.isDirectory()){
	File[] listFiles = dir.listFiles();


	
	for(File file : listFiles){
	    if(file.isFile()) {fileCount++;}}
	System.out.println("Total number of files: "+ fileCount);
	
	
	for(File file : listFiles){
	    if(file.isFile()) {
	   
	    	files.add(file.getAbsolutePath());
	    	System.out.print("\tReading "+ file.getAbsolutePath()); 
	    	newarray1[0][count]=file.getAbsolutePath();
	    	System.out.print("\tReading the new array "+ newarray1[0][count]);
	    	if(count==fileCount)
		    	break;
		    count++; 
	      
	    
	    	
	    	// +"  "+ indexInChar(file.getName().trim().charAt(0)) +"  "+file.getName().substring(1, file.getName().indexOf('.')));
	    	
	    	//float onePC = calculatePercentageOfOne(file.getAbsolutePath());
	    	//System.out.println ("    "+onePC);
	    	
	    	onePercentage[Integer.parseInt(file.getName().substring(1, file.getName().indexOf('.')))][indexInChar(file.getName().trim().charAt(0))] = calculatePercentageOfOne(file.getAbsolutePath()) ;
	    	System.out.println ("    "+ onePercentage[Integer.parseInt(file.getName().substring(1, file.getName().indexOf('.')))][indexInChar(file.getName().trim().charAt(0))]*10) ;
	    	newarray1[1][count1]=onePercentage[Integer.parseInt(file.getName().substring(1, file.getName().indexOf('.')))][indexInChar(file.getName().trim().charAt(0))];
			//	System.out.println(newarray1[1][count]);
				 if(count1==fileCount)
				    	break;
				 count1++;
	   
	}
	}
	}
	System.out.println(count);
	System.out.println("\n\nSparse Matrix of onePercentage Array (rowNumber colnumNumber and %)");
	
	for(int i=0 ; i<10;i++) {
		for(int j=0; j<onePercentage[i].length;j++) {
			if(onePercentage[i][j]!=0.0) {
				System.out.println(i+" "+j+" "+onePercentage[i][j]+" ");}}}
	
		System.out.println(fileCount);
		
	for(int i1=0;i1<fileCount;i1++){
		
		
			System.out.print((float)newarray1[1][i1]);
			System.out.println();
			}
	int h1=0;

  
	float querynumber=find();
	
	for(int h=0;h<fileCount;h++)
	{
		
		if(((float)newarray1[1][h]>=querynumber-10)&&((float)newarray1[1][h]<=querynumber+10))
		{
			
			System.out.println(newarray1[0][h]+"\t"+(float)newarray1[1][h]);	
			ImageIcon image = new ImageIcon(newarray1[0][h].toString());
			Image image1=image.getImage();
			Image newimage=image1.getScaledInstance(70, 70,java.awt.Image.SCALE_SMOOTH);
			image=new ImageIcon(newimage);
			//JLabel label = new JLabel(newarray1[0][h].toString(),image,JLabel.CENTER);
		h1++;
		
		
		
	dtm.addRow(new Object[] { image, "Path: "+newarray1[0][h].toString(),(float)newarray1[1][h]+"%"});
	
	js.setVisible(true);

	
		
			}
	}
		
	System.out.println(h1);
	jl1.setText("Searched "+String.valueOf(h1)+" images out of 100....");
	jl3.setText(String.valueOf("Percentage of Query Image:  "+querynumber+"%"));

 
	return files.toArray(new String[]{});
	}


private int indexInChar(char c) {
	int i = 0;
	for(; i<character.length; i++) if(character[i] == c) break;
	return i;
}
public static float find()
{	
float percentage=0;
jf.showOpenDialog(jf);	
File file = jf.getSelectedFile();
String path= file.getAbsolutePath();

percentage=calculatePercentageOfOne(path);
jl.setText("Path:   "+path);


ImageIcon image2 = new ImageIcon(path);
Image image3=image2.getImage();
Image newimage=image3.getScaledInstance(100,100,java.awt.Image.SCALE_SMOOTH);
 		
image2=new ImageIcon(newimage);
label.invalidate();
label.setIcon(image2);
jp1.add(label);
//label.setVisible(true);
System.out.println("Query percentage for image :"+path+"is :"+percentage);
	System.out.println("After searching... matched image is");
return percentage;
	}



@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==jb)
	{
	try{
	
		dtm.getDataVector().removeAllElements();
		readFileNames("/home/smriti/Pictures/black_white_images");
		
		
	}
	catch(Exception e1)
	{
		e1.getMessage();
	}
		
		
	}
	
	
}

public static void main(String[] args){
	Image_Search frame=new Image_Search();
	
	frame.setSize(1200,670);
	frame.setLocationRelativeTo(null);
	frame.setLayout(null);
	frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	frame.setVisible(true);
	

			
		}


}		
	
