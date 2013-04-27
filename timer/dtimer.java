import java.util.*;
import java.util.Timer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class dtimer extends JFrame{
	private Timer timer;
	private TimerTask task;
	private int cnt=0;
	private  int type=0;
	private long rest=10*60*100;//10 min to rest;
	private long delay=5*60*100;//5 min to delay;
	private long per=60*60*1000;//and hour a circle
	public void run1()
	{
		System.out.println("now is "+cnt++);
	}
	public void run2()
	{
		//System.out.println(new Date().getTime()/1000);
		final JFrame show;
		if(type==0)show=new JFrame("Stop now!");
		else show=new JFrame("Begin now!");
		show.setSize(400,300);
		show.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		show.setLocationRelativeTo(null);
		show.setVisible(true);
		show.setAlwaysOnTop(true);
		Container con=show.getContentPane();
		
		Box box=Box.createHorizontalBox();
		con.add(box);
		JButton okButton = new JButton("OK");
		box.add(okButton);
		//JTextField txt=new JTextField("Stop!!!");
		okButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				type=1;
				task.cancel();
				timer.purge();
				task=new TimerTask(){public void run(){run2();}};
				timer=new Timer();
				Date date=new Date();
				date.setTime(date.getTime()+rest);
				timer.schedule(task,date,per);
				show.dispose();
			}
		});
		JButton delayButton=new JButton("Delay 5 mins");
		box.add(delayButton);
		delayButton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				type=0;
				task.cancel();
				timer.purge();
				task=new TimerTask(){public void run(){run2();}};
				timer=new Timer();
				Date date=new Date();
				date.setTime(date.getTime()+delay);
				timer.schedule(task,date,per);
				show.dispose();
			}
		});
		//box.add(okButton);
		//box.add(delayButton);
		//con.add(box);
		//con.add(txt);
		//	JOptionPane.setAlwaysOnTop(true);
	}

	public dtimer(String str)
	{
		super(str);
		this.setSize(800,600);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLocationRelativeTo(null);
		//menubar
		JMenuBar jb=new JMenuBar();
		JMenu fileMenu=new JMenu("File");
		jb.add(fileMenu);

		JMenuItem exitMenu=new JMenuItem("Exit");
		fileMenu.add(exitMenu);
		exitMenu.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				System.exit(0);
			}
		});
		JMenu helpMenu=new JMenu("Help");
		JMenuItem aboutMenu=new JMenuItem("About");
		jb.add(helpMenu);
		helpMenu.add(aboutMenu);
		aboutMenu.addActionListener(
				new ActionListener(){
						public void actionPerformed(ActionEvent e){
							JOptionPane.showMessageDialog(null,"pengfeituan@gmail.com","Author",JOptionPane.INFORMATION_MESSAGE);
						}
					}
				);
		this.setJMenuBar(jb);
		task=new TimerTask(){public void run(){run2();}};
		timer=new Timer();
		Date date=new Date();
		date.setTime(date.getTime()+per);
		timer.schedule(task,date,per);
	
		//container
		
		//Container contentPane = getContentPane();
		//Box box = Box.createHorizontalBox();
		//JButton okButton =new Button("OK");

		this.setVisible(true);
	}
	public static void main( String[] args)
	{
		//System.out.println("duan's timer start now!");
		new dtimer("duan's timer");
	}
}
