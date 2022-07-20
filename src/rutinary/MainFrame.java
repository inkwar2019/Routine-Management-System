package rutinary;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.lang.*;
import java.util.*;

public class MainFrame extends JFrame implements ActionListener,ItemListener{
	Container c;
	JTextField text;
	JButton search;
	FileOutputStream write;
	BufferedReader reader;
	Font AB16,AI16;
	Color[] Field;
	int departmentFlage=1,yearFlage=1,semisterFlage=1,typeFlage=1,sectionFlage = 1;
	JLabel department,year,semister,type,status,statusBar,errorStatus,section;
	JLabel[] day,period;
	JLabel[][][] rotin;
	MainPanel up,down,left,right;
	MainPanel[] center;
	String[] yearChoose= {"Frist","Second","Third","Forth"},semisterChoose= {"Odd","Even"};
	String[] departmentChoose= {"CSE","EEE","ETE","ECE","CE","URP","BECM","ARC","ME","GCE","MTE","MSE","IPE","CFPE"};
	String[] typeChoose= {"TeacherID","CourseNo","RoomNo"};
	String[] dayName= {"A","B","C","D","E"},sectionChoose= {"A","B","C"};
	String[] periodName= {"1","2","3","4","5","6","7","8","9"};
	JRadioButton[] yearCheck,semisterCheck,departmentCheck,typeCheck,sectionCheck;
	public MainFrame()throws Exception
	{
		c = this.getContentPane();
		c.setLayout(null);
		c.setBackground(new Color(100,211,255));
		
		up =new MainPanel();
		down =new MainPanel();
		left =new MainPanel();
		right =new MainPanel();
		center =new MainPanel[15];
		text =new JTextField();
		search = new JButton("Search");
		department = new JLabel(" Department");
		year = new JLabel("Year");
		semister = new JLabel("Semister");
		section = new JLabel("Section");
		type = new JLabel("--Search Type--");
		AI16 = new Font("Arial",Font.ITALIC,16);
		AB16 = new Font("Arial",Font.BOLD,16);
		yearCheck =new JRadioButton[4];
		semisterCheck =new JRadioButton[2];
		departmentCheck =new JRadioButton[14];
		typeCheck = new JRadioButton[3];
		sectionCheck = new JRadioButton[3];
		status = new JLabel("Status :");
		statusBar = new JLabel("Static.....");
		errorStatus = new JLabel("Normal");
		day = new JLabel[5];
		period = new JLabel[9];
		rotin = new JLabel[15][3][3];
		Field = new Color[3];
		up.setLayout(null);
		down.setLayout(null);
		left.setLayout(null);
		right.setLayout(null);
		
		up.setBounds(0,0,790,100);
		up.setBackground(Color.GRAY);
		down.setBounds(101,587,792,33);
		down.setBackground(Color.GRAY);
		left.setBounds(0,101,100,519);
		left.setBackground(Color.GRAY);
		right.setBounds(791,0,102,585);
		right.setBackground(Color.GRAY);
		text.setFont(AI16);
		text.setBounds(400,30,300,40);
		text.setBackground(new Color(105,211,255));
		text.addActionListener(this);
		search.setBounds(700,30,80,40);
		search.setBackground(Color.black);
		search.setForeground(Color.red);
		search.addActionListener(this);
		department.setBounds(0, 0, 100,50);
		department.setForeground(Color.BLACK);
		department.setFont(AB16);
		year.setFont(AB16);
		year.setBounds(30, 0,50,30);
		year.setForeground(Color.black);
		semister.setFont(AB16);
		semister.setBounds(10,230,80,30);
		semister.setForeground(Color.BLACK);
		section.setForeground(Color.BLACK);
		section.setFont(AB16);
		section.setBounds(15,360,80,30);
		type.setFont(AB16);
		type.setForeground(Color.BLACK);
		type.setBounds(130,0,200,50);
		createCheckBox();
		departmentCheck[departmentFlage-1].setSelected(true);
		yearCheck[departmentFlage-1].setSelected(true);
		semisterCheck[departmentFlage-1].setSelected(true);
		typeCheck[typeFlage-1].setSelected(true);
		sectionCheck[sectionFlage-1].setSelected(true);
		status.setBounds(15,0,80,30);
		statusBar.setBounds(80,0,500,30);
		statusBar.setForeground(Color.WHITE);
		errorStatus.setFont(AB16);
		errorStatus.setBounds(600,0,200,30);
		errorStatus.setForeground(Color.GREEN);
		
		up.add(text);
		up.add(search);
		up.add(type);
		down.add(status);
		down.add(statusBar);
		down.add(errorStatus);
		left.add(year);
		left.add(semister);
		left.add(section);
		right.add(department);
		c.add(up);
		c.add(down);
		c.add(left);
		c.add(right);
	}
	///.......................................Creating......................................
	void createCheckBox()
	{ 
		int temp,counter=0; 
		temp = 50;
			for(int i=0;;i++)
			{
				if(counter == 14)
					break;
				if(counter == 6 || counter==13) {
					departmentCheck[counter] = new JRadioButton(departmentChoose[counter]);
					departmentCheck[counter].setBounds(0,temp,100,50);
					departmentCheck[counter].addItemListener(this);
					right.add(departmentCheck[counter]);
					temp+=50;
					counter++;
					continue;
				}
				departmentCheck[counter] = new JRadioButton(departmentChoose[counter]);
				departmentCheck[counter].setBounds(0,temp,50,50);
				departmentCheck[counter].addItemListener(this);
				right.add(departmentCheck[counter]);
				counter++;
				departmentCheck[counter] = new JRadioButton(departmentChoose[counter]);
				departmentCheck[counter].setBounds(50,temp,50,50);
				departmentCheck[counter].addItemListener(this);
				right.add(departmentCheck[counter]);
				temp+=50;
				counter++;
			}
		temp = 30;
			for(int i=0;i<4;i++)
			{
				yearCheck[i] = new JRadioButton(yearChoose[i]);
				yearCheck[i].setBounds(10,temp,80,40);
				yearCheck[i].addItemListener(this);
				left.add(yearCheck[i]);
				temp+=50;
			}
		temp = 260;
			for(int i=0;i<2;i++)
			{
				semisterCheck[i] = new JRadioButton(semisterChoose[i]);
				semisterCheck[i].setBounds(10,temp,80,40);
				semisterCheck[i].addItemListener(this);
				left.add(semisterCheck[i]);
				temp+=50;
			}
			temp = 390;
			for(int i=0;i<3;i++)
			{
				sectionCheck[i] = new JRadioButton(sectionChoose[i]);
				sectionCheck[i].setBounds(10,temp,80,40);
				sectionCheck[i].addItemListener(this);
				left.add(sectionCheck[i]);
				temp+=42;
			}
	    temp =50;
	    for(int i=0;i<3;i++)
	    {
	    	typeCheck[i] = new JRadioButton(typeChoose[i]);
	    	typeCheck[i].setBounds(temp,40,90,40);
	    	typeCheck[i].addItemListener(this);
			up.add(typeCheck[i]);
			temp+=100;
	    }
	    temp =130;
	    for(int i=0;i<5;i++)
	    {
	    	day[i] = new JLabel(dayName[i]);
	    	day[i].setBounds(101,temp,15,90);
			c.add(day[i]);
			temp+=90;
	    }
	    temp =130;
	    for(int i=0;i<9;i++)
	    {
	    	period[i] = new JLabel(periodName[i]);
	    	period[i].setBounds(temp,101,75,25);
	    	period[i].setBackground(Color.RED);
	    	period[i].setForeground(Color.black);
			c.add(period[i]);
			temp+=75;
	    }
	    int cntr = 0,x=10,y=127;
	    for(int i=0;i<5;i++)
	    {
	    	x=115;
	    	for(int j=0;j<3;j++) {
	    		center[cntr] = new MainPanel();
	    		center[cntr].setLayout(null);
	    		center[cntr].setBounds(x, y,223,90);
	    		c.add(center[cntr]);
	    		x+=225;
	    		cntr++;
	    	}
	    	if(cntr == 15)
	    		break;
	    	y+=92;
	    }
	    temp = 225;
	    for(int i=0;i<3;i++)
	    {
	    	Field[i] =new Color(i*75,0,temp);
	    	temp-=75;
	    }
	    for(int i=0;i<15;i++)
	    {
	    	x=0;
	    	y=0;
	    	for(int j=0;j<3;j++)
	    	{
	    		y=0;
	    		for(int k=0;k<3;k++)
	    		{
	    			rotin[i][j][k] = new JLabel("----------");
	    			rotin[i][j][k].setFont(new Font("Arial",Font.BOLD,14));
	    			rotin[i][j][k].setBounds(x, y,73,30);
	    			rotin[i][j][k].setBackground(Field[j]);
	    			center[i].add(rotin[i][j][k]);
	    			y+=30;
	    		}
	    		x+=75;
	    	}
	    }
	}
	
	
	
	///...............................Reading File.....................................
	void fileRead(int dd)
	{
		String n,m;
		String Dir = null,subDir;
		int indicator1=0,indicator2=0;
		String[] details = new String[3];
		
		///.................................If Space Search....................................
		n = text.getText();
		n = n.toLowerCase();
		if(dd==1) {
			if("                                                                    ".indexOf(n) != -1)
			{
				errorStatus.setForeground(Color.RED);
				errorStatus.setText("Invalid Search");
				return;
			}
		}
		else if(dd == 2) {
		///................................Directory Create..................................
		Dir = ".//\\src\\rutinary\\DATA\\";
		subDir = departmentChoose[departmentFlage-1] + yearChoose[yearFlage-1]+semisterChoose[semisterFlage-1]+sectionChoose[sectionFlage-1]+".txt";
		Dir += subDir;
		}
		else if(dd == 3)
		{
			Dir = ".//\\src\\rutinary\\DATA\\";
			subDir = "NULL"+".txt";
			Dir += subDir;
		}
		errorStatus.setForeground(Color.GREEN);
		errorStatus.setText("Normal");
		text.setText("");
		
		///................................File Find....................................
		try {
			reader = new BufferedReader(new FileReader(Dir));
			for(int i=0;i<15;i++)
			{
				for(int j=0;j<3;j++) {
					m = reader.readLine();
					m = m.toLowerCase();
					if((m.indexOf(n) != -1 && dd==1) || (m.indexOf("null") == -1 && dd==2))
					{
						m = m.toUpperCase();
						indicator1 = m.indexOf(' ');
						details[0] = m.substring(0, indicator1);
						indicator2 = m.indexOf(' ',indicator1+1);
						details[1] = m.substring(indicator1+1, indicator2);
						details[2] = m.substring(indicator2+1);
						rotin[i][j][0].setText(details[0]);
						rotin[i][j][1].setText(details[1]);
						rotin[i][j][2].setText(details[2]);
					}
					else
					{
						rotin[i][j][0].setText("--------");
						rotin[i][j][1].setText("--------");
						rotin[i][j][2].setText("--------");
					}
				}
			}
		 reader.close();
		}catch(Exception g) {
			 errorStatus.setForeground(Color.RED);
			 errorStatus.setText(g.getMessage());
		}
	}
	
	
	
	
	///...............................ActionListener..........................................
	
	public void actionPerformed(ActionEvent e)
	{
		String s,k;
		int numb = 0 ;
		try {
	
			if(typeCheck[0].isSelected())
			{
				s = typeCheck[0].getText();
				s+=".txt";
				reader = new BufferedReader(new FileReader(s));
				s = text.getText();
				text.setText("");
				for(int i=0;i<21;i++)
				{
					k = reader.readLine();
					if(k.equals(s))
					{
						fileRead(1);
						break;
					}
					else
						numb++;
					
				}
				if(numb >20)
					fileRead(3);
				reader.close();
			}
			else if(typeCheck[1].isSelected())
			{
				s = text.getText();
				text.setText("");
				if(s.length()>6)
				{
					fileRead(1);
				}
				else
					fileRead(3);
			}
			else if(typeCheck[2].isSelected())
			{
				s = text.getText();
				text.setText("");
				if(s.length()>2)
				{
					fileRead(1);
				}
				else
					fileRead(3);
			}
		}catch(Exception w)
		{
		}
	}
	
	
	
	
	
	///..................................ItemListener...........................................
	public void itemStateChanged(ItemEvent e)
	{
		int temp;
		temp = departmentFlage;
		//......................................One Controller.................................
		for(int i=0;i<14;i++)
		{
			if(i==temp-1)
				continue;
			if(departmentCheck[i].isSelected())
			{
				departmentFlage = i+1;
				break;
			}
		}
		if(temp == departmentFlage)
		{
			departmentCheck[temp-1].setSelected(true);
		}
		else {
			departmentCheck[temp-1].setSelected(false);
		}
		temp = yearFlage;
		for(int i=0;i<4;i++)
		{
			if(i==temp-1)
				continue;
			if(yearCheck[i].isSelected())
			{
				yearFlage = i+1;
			}
		}
		if(temp == yearFlage)
		{
			yearCheck[yearFlage-1].setSelected(true);
		}
		else {
			yearCheck[temp-1].setSelected(false);
		}
		temp = semisterFlage;
		for(int i=0;i<2;i++)
		{
			if(i==temp-1)
				continue;
			if(semisterCheck[i].isSelected())
			{
				semisterFlage = i+1;
			}
		}
		if(temp == semisterFlage)
		{
			semisterCheck[semisterFlage-1].setSelected(true);
		}
		else {
			semisterCheck[temp-1].setSelected(false);
		}
		
		temp = typeFlage;
		for(int i=0;i<3;i++)
		{
			if(i==temp-1)
				continue;
			if(typeCheck[i].isSelected())
			{
				typeFlage = i+1;
			}
		}
		if(temp == typeFlage)
		{
			typeCheck[typeFlage-1].setSelected(true);
		}
		else {
			typeCheck[temp-1].setSelected(false);
		}
		temp = sectionFlage;
		for(int i=0;i<3;i++)
		{
			if(i==temp-1)
				continue;
			if(sectionCheck[i].isSelected())
			{
				sectionFlage = i+1;
			}
		}
		if(temp == sectionFlage)
		{
			sectionCheck[sectionFlage-1].setSelected(true);
		}
		else {
			sectionCheck[temp-1].setSelected(false);
		}
		///..................................Ststus Changer...........................................
		String tempS;
		tempS = "Department of "+departmentChoose[departmentFlage-1]+" "+yearChoose[yearFlage-1]+" Year "+semisterChoose[semisterFlage-1]+" Semister "+sectionChoose[sectionFlage-1]+" Section";
		statusBar.setText(tempS);
		try {
		fileRead(2);
		}catch(Exception l)
		{}
	}
}
