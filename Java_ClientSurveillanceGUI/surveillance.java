import java.applet.Applet;
import java.applet.AudioClip;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.TargetDataLine;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class surveillance{

	private JFrame frame;
	JPanel panel = new JPanel();
	JPanel panel1 = new JPanel();
	JPanel panel2 = new JPanel();
	JPanel panel3 = new JPanel();
	ImageIcon imageicon;
	ImageIcon imageicon1;
	ImageIcon imageicon2;
	ImageIcon imageicon3;
	JLabel label;
	JLabel label1;
	JLabel label2;
	JLabel label3;
	static FileOutputStream fos = null;
	static PrintStream ps = null;
	
	/**
	 * Launch the application.
	 * @throws InterruptedException 
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException 
	{
		try
		{
			fos = new FileOutputStream("\\\\ABHIJITH-PC\\Images\\Logs.csv");
			ps = new PrintStream(fos);
		}
		catch(Exception e)
		{
			System.out.println("File not found");
		}
		surveillance window = new surveillance();
		window.frame.setVisible(true);
		window.surveillance1();
	}

	/**
	 * Create the application.
	 */
	public surveillance()
	{
		frame = new JFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(0, 0, 1370, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
	}
	public void surveillance1() throws InterruptedException, IOException 
	{
		int i=1;
		while(true)
		{
			File f1 = new File("\\\\ABHIJITH-PC\\Images\\processed");
			if(f1.list().length>0)
			{
				System.out.println("hello");
				initialize(i);
				frame.revalidate();
				Thread.sleep(3000);
				refresh();
				i++;
			}
		}
	}

	private void refresh()
	{
		panel.remove(label);
		panel1.remove(label1);
		panel2.remove(label2);
		panel3.remove(label3);
	}
	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	
	private void initialize(int i) throws IOException, InterruptedException 
	{
		Border border = BorderFactory.createLineBorder(Color.RED, 15);
		//DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");  
		LocalDateTime now = LocalDateTime.now();  
		System.out.println(dtf.format(now)); 
		
		panel.setBounds(0, 0, 675, 350);
		frame.getContentPane().add(panel);
		File f = new File("\\\\ABHIJITH-PC\\Images\\processed");
		String s[] = f.list();
		String first = "";
		boolean flag = false;
		int index=0;
		for(int j=0; j<s.length; j++)
		{
			if(s[j].contains(i+"_1unknown"))
			{
				first = f+"\\"+s[j];
				flag=true;
				index=j;
				break;
			}
			if(s[j].contains(i+"_1p"))
			{
				first = f+"\\"+s[j];
				index=j;
				break;
			}
		}
		System.out.println(first);
		imageicon = new ImageIcon(first);
		Image image = imageicon.getImage(); // transform it 
		Image newimg = image.getScaledInstance(675, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageicon = new ImageIcon(newimg);  // transform it back
		label = new JLabel("", imageicon, JLabel.CENTER);
		if(flag)
		{
			label.setBorder(border);
			ps.println(dtf.format(now)+",1,"+first);
		}
		panel.add( label, BorderLayout.CENTER );
		//copy image to postprocessed and delete from processed
		File sourceFile = new File(first);
        File destinationFile = new File("\\\\ABHIJITH-PC\\Images\\postprocessed\\"+s[index]);
        InputStream in = new FileInputStream(sourceFile);
        OutputStream out = new FileOutputStream(destinationFile);
        byte[] buffer = new byte[1024];
        Thread.sleep(500);
        int length;
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        Thread.sleep(500);
        in.close();
        out.close();
        //sourceFile.delete();
		
		f = new File("\\\\ABHIJITH-PC\\Images\\processed");
		String second = "";
		flag = false;
		for(int j=0; j<s.length; j++)
		{
			if(s[j].contains(i+"_2unknown"))
			{
				second = f+"\\"+s[j];
				flag=true;
				break;
			}
			if(s[j].contains(i+"_2p"))
			{
				second = f+"\\"+s[j];
				break;
			}
		}
		panel1.setBounds(675, 0, 675, 350);
		frame.getContentPane().add(panel1);
		imageicon1 = new ImageIcon(second);
		image = imageicon1.getImage(); // transform it 
		newimg = image.getScaledInstance(675, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageicon1 = new ImageIcon(newimg);  // transform it back
		label1 = new JLabel("", imageicon1, JLabel.CENTER);
		label1.setBorder(null);
		if(flag)
		{
			label.setBorder(border);
			ps.println(dtf.format(now)+",2,"+second);
		}
		panel1.add( label1, BorderLayout.CENTER );
		//copy image to postprocessed and delete from processed  
		sourceFile = new File(first);
        destinationFile = new File("\\\\ABHIJITH-PC\\Images\\postprocessed\\"+s[index]);
        in = new FileInputStream(sourceFile);
        out = new FileOutputStream(destinationFile);
        buffer = new byte[1024];
        Thread.sleep(500);
        while ((length = in.read(buffer)) > 0) {
            out.write(buffer, 0, length);
        }
        Thread.sleep(500);
        in.close();
        out.close();
		//sourceFile.delete();
        
		f = new File("\\\\ABHIJITH-PC\\Images\\processed");
		String third = "";
		flag = false;
		for(int j=0; j<s.length; j++)
		{
			if(s[j].contains(i+"_3unknown"))
			{
				third = f+"\\"+s[j];
				flag=true;
				break;
			}
			if(s[j].contains(i+"_3p"))
			{
				third = f+"\\"+s[j];
				break;
			}
		}
		panel2.setBounds(0, 350, 675, 350);
		frame.getContentPane().add(panel2);
		imageicon2 = new ImageIcon(third);
		image = imageicon2.getImage(); // transform it 
		newimg = image.getScaledInstance(675, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageicon2 = new ImageIcon(newimg);  // transform it back
		label2 = new JLabel("", imageicon2, JLabel.CENTER);
		if(flag)
		{
			label.setBorder(border);
			ps.println(dtf.format(now)+",3,"+third);
		}
		panel2.add( label2, BorderLayout.CENTER );
		//copy image to postprocessed and delete from processed
		
		panel3.setBounds(675, 350, 675, 350);
		frame.getContentPane().add(panel3);
		imageicon3 = new ImageIcon("\\\\ABHIJITH-PC\\Images\\"+i+"_4p.jpg");
		image = imageicon3.getImage(); // transform it 
		newimg = image.getScaledInstance(675, 350,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way  
		imageicon3 = new ImageIcon(newimg);  // transform it back
		label3 = new JLabel("", imageicon3, JLabel.CENTER);
		panel3.add( label3, BorderLayout.CENTER );
	}

}