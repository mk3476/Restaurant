
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import javax.swing.filechooser.FileNameExtensionFilter;

public class restaurant implements ActionListener {
	private static Connection dbTest;
	private String username;
	private String password;
	private JFrame frame = new JFrame();
	private JPanel panel = new JPanel();
	private JPanel panel1 = new JPanel();
	private JPanel panel2 = new JPanel();
	private JLabel idLabel = new JLabel("���̵�");
	private JLabel pwdLabel = new JLabel("��й�ȣ");
	private JTextField idInput = new JTextField();
	private JPasswordField pwdInput = new JPasswordField();
	private JButton loginButton = new JButton("�α���");
	private JTextArea check_area = new JTextArea();
	private JComboBox<String> check_box = new JComboBox<String>();
	private JComboBox<String> buy_box = new JComboBox<String>();
	private JTextField model_field = new JTextField();
	private JButton buyButton = new JButton("����");
	private JLabel label = new JLabel("�Ĵ� ���� ���α׷�");
	private ArrayList<String> List = new ArrayList<String>();
	private SimpleDateFormat fm1 = new SimpleDateFormat("yyyyMMdd");
	private String date = fm1.format(new Date(0));
	private JButton button22 = new JButton("��ȸ");
	private JTextArea ta3 = new JTextArea("", 7, 30);
	private JTextArea ta2 = new JTextArea("", 1, 5);
	private JButton buttons[] = new JButton[20];
	private JTextArea ta33 = new JTextArea("", 1, 10);
	private JButton button32 = new JButton("��ȸ");
	private JTextArea ta333 = new JTextArea("", 7, 30);
	private JButton button44 = new JButton("��ȸ");
	private JTextArea ta = new JTextArea("", 1, 10);
	private JTextArea ta4 = new JTextArea("", 7, 30);
	private JButton button123 = new JButton("�ֹ�");
	private JTextArea ta123 = new JTextArea("", 7, 20);
	private int mcount;
	private JLabel[] label1 = new JLabel[20];
	private JComboBox nameCombo = new JComboBox();
	private JButton button2 = new JButton("���");
	private JButton button3 = new JButton("����");
	private JButton button11 = new JButton("����");
	private JButton button33 = new JButton("�������");
	private JButton button345 = new JButton("�޴����");

	public restaurant() {
		panel.setLayout(null);

		idLabel.setBounds(20, 10, 60, 30);
		pwdLabel.setBounds(20, 50, 60, 30);
		idInput.setBounds(100, 10, 80, 30);
		pwdInput.setBounds(100, 50, 80, 30);
		loginButton.setBounds(200, 25, 80, 35);
		loginButton.addActionListener(this);

		panel.add(idLabel);
		panel.add(pwdLabel);
		panel.add(idInput);
		panel.add(pwdInput);
		panel.add(loginButton);

		frame.add(panel);

		frame.setTitle("�Ĵ� ���� �ý���");
		frame.setSize(320, 130);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	public void createMenu() {
		JMenuBar mb = new JMenuBar();
		JMenu fileMenu = new JMenu("Menu");
		String[] itemTitle = { "Open", "Login" };
		JMenuItem[] menuItem = new JMenuItem[2];
		for (int i = 0; i < menuItem.length; i++) {
			menuItem[i] = new JMenuItem(itemTitle[i]);
			menuItem[i].addActionListener(new MenuActionListener());
			fileMenu.add(menuItem[i]);
		}

		mb.add(fileMenu);

		frame.setJMenuBar(mb);
	}

	class MenuActionListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			String cmd = e.getActionCommand();
			if (cmd.equals("Open")) {
				JFileChooser fc = new JFileChooser("C://");
				FileNameExtensionFilter filter = new FileNameExtensionFilter("java files", "java");
				fc.addChoosableFileFilter(filter);
				int ret = fc.showOpenDialog(null);
				if (ret != JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, "������ �������� �ʾҽ��ϴ�", "���", JOptionPane.WARNING_MESSAGE);
					return;
				}
				if (ret == JFileChooser.APPROVE_OPTION) {
					System.out.println("��� �����Ͻ� ���� :" + fc.getSelectedFile().getName());
					File file = fc.getSelectedFile();
					System.out.println("������ �ִ� ���丮 :" + file);
					String filePath = fc.getSelectedFile().getPath();
					try {
						BufferedReader br = new BufferedReader(new FileReader(file));
						String str;
						str = br.readLine();
						int index = Integer.parseInt(str);
						int count = 0;
						while (true) {
							for (int i = 0; i < index; i++) {
								str = br.readLine();
								String b = new String(str.getBytes("UTF-8"), "UTF-8");
								List.add(b);

							}
							str = br.readLine();
							index = Integer.parseInt(str);
							count += 1;
						}

					} catch (Exception ex) {

					}

				}
				try {
					create_query1();
					create_query2();
					create_query3();
					insert_query();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}

			}

			else if (cmd.equals("Login")) {
				MyDialog dialog = new MyDialog(frame, "��� �α���");
				dialog.setVisible(true);
			}
		}
	}
	class MyDialog1 extends JDialog {
		private String username;
		private String password;
		private JFrame frame = new JFrame();
		private JPanel panel = new JPanel();
		
		private JLabel idLabel = new JLabel("����");
		private JLabel pwdLabel = new JLabel("����(4�ڸ�)");
		private JLabel Label = new JLabel("����ó");
		private JTextField idInput = new JTextField();
		private JTextField pwdInput = new JTextField();
		private JTextField idInput2= new JTextField();
		private JButton loginButton = new JButton("���Խ�û");
		private JButton loginButton2 = new JButton("���");

		public MyDialog1(JFrame frame, String title) {
			super(frame, title);
			panel.setLayout(null);
			idLabel.setBounds(20, 10, 90, 30);
			pwdLabel.setBounds(20, 60, 90, 30);
			Label.setBounds(20, 110, 90, 30);
			
			idInput.setBounds(100, 10, 120, 30);
			pwdInput.setBounds(100, 60, 120, 30);
			idInput2.setBounds(100,110, 120, 30);
			
			loginButton.setBounds(20,180, 100, 35);
			loginButton2.setBounds(150, 180, 100, 35);

			panel.add(idLabel);
			panel.add(pwdLabel);
			panel.add(idInput);
			panel.add(pwdInput);
			panel.add(loginButton);
			panel.add(Label);
			panel.add(loginButton2);
			panel.add(idInput2);

			add(panel);

			setSize(400, 300);
			loginButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	class MyDialog2 extends JDialog {
		private String username;
		private String password;
		private JFrame frame = new JFrame();
		private JPanel panel = new JPanel();
		
		private JLabel idLabel = new JLabel("������");
		JComboBox<String> Combo = new JComboBox<String>();
		// �ӽ÷�
		
		private JLabel pwdLabel = new JLabel("����");
		private JTextField idInput = new JTextField();
		private JButton loginButton = new JButton("���");
		private JButton loginButton2 = new JButton("���");

		public MyDialog2(JFrame frame, String title) {
			super(frame, title);
			panel.setLayout(null);
			String[] names = { "Staff", "Supervisor" };
			for (int i = 0; i < names.length; i++) {
				Combo.addItem(names[i]);
			}
			idLabel.setBounds(20, 50, 90, 30);
			pwdLabel.setBounds(20, 100, 90, 30);
			
			
			idInput.setBounds(100, 50, 120, 30);
			Combo.setBounds(100, 100, 120, 30);
			
			loginButton.setBounds(20,180, 100, 35);
			loginButton2.setBounds(150, 180, 100, 35);

			panel.add(idLabel);
			panel.add(pwdLabel);
			panel.add(idInput);
			panel.add(Combo);
			panel.add(loginButton);
			
			panel.add(loginButton2);
			

			add(panel);

			setSize(400, 300);
			loginButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	class MyDialog3 extends JDialog {
		private String username;
		private String password;
		private JFrame frame = new JFrame();
		private JPanel panel = new JPanel();
		
		private JLabel idLabel = new JLabel("�޴���");
		private JLabel pwdLabel = new JLabel("����");
		private JLabel Label = new JLabel("����ó");
		private JTextField idInput = new JTextField();
		private JTextField pwdInput = new JTextField();
		private JTextField idInput2= new JTextField();
		private JButton loginButton = new JButton("���");
		private JButton loginButton2 = new JButton("���");

		public MyDialog3(JFrame frame, String title) {
			super(frame, title);
			panel.setLayout(null);
			idLabel.setBounds(20, 50, 90, 30);
			pwdLabel.setBounds(20, 100, 90, 30);
			
			
			idInput.setBounds(100, 50, 120, 30);
			pwdInput.setBounds(100, 100, 120, 30);
			
			
			loginButton.setBounds(20,180, 100, 35);
			loginButton2.setBounds(150, 180, 100, 35);

			panel.add(idLabel);
			panel.add(pwdLabel);
			panel.add(idInput);
			panel.add(pwdInput);
			panel.add(loginButton);
			
			panel.add(loginButton2);
		

			add(panel);

			setSize(400, 300);
			loginButton2.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}

	
	class MyDialog extends JDialog {
		private String username;
		private String password;
		private JFrame frame = new JFrame();
		private JPanel panel = new JPanel();
		private JPanel panel1 = new JPanel();
		private JPanel panel2 = new JPanel();
		private JLabel idLabel = new JLabel("�̸�");
		private JLabel pwdLabel = new JLabel("�����ȣ");
		private JTextField idInput = new JTextField();
		private JPasswordField pwdInput = new JPasswordField();
		private JButton loginButton = new JButton("�α���");

		public MyDialog(JFrame frame, String title) {
			super(frame, title);

			panel.setLayout(null);

			idLabel.setBounds(20, 10, 60, 30);
			pwdLabel.setBounds(20, 50, 60, 30);
			idInput.setBounds(100, 10, 80, 30);
			pwdInput.setBounds(100, 50, 80, 30);
			loginButton.setBounds(200, 25, 80, 35);

			panel.add(idLabel);
			panel.add(pwdLabel);
			panel.add(idInput);
			panel.add(pwdInput);
			panel.add(loginButton);

			add(panel);

			setSize(320, 130);
			loginButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					setVisible(false);
				}
			});
		}
	}
	


	private void PCstore() {

			frame = new JFrame();
			frame.setVisible(false);
			frame.setLayout(new GridLayout(2, 2));
			panel = new JPanel();
			createMenu();
			JLabel table = new JLabel("");
			panel.setFont(new Font("�ʱ�ü", 1, 12));
			panel.setBorder(new TitledBorder("���̺� ��Ȳ"));
			// panel.setBounds(10,10,380,380);
			panel.setLayout(null);
			// panel.setBorder(new LineBorder(Color.gray, 2));
			int num = 1;
			int k =0;
			for (int i = 1; i < 5; i++) {
				if (i == 2) {
					num = 6;
				}
				if (i == 3) {
					num = 11;
				}
				if (i == 4) {
					num = 16;
				}
				for (int j = 0; j < 5; j++) {

					String a = String.valueOf(num + j);
					label1[k]=new JLabel(a);
					label1[k].setBounds(30 + (j * 60), 70 * i, 50, 50);
					label1[k].setBorder(new LineBorder(Color.black, 2));
					label1[k].setHorizontalAlignment(JLabel.CENTER);
					label1[k].setOpaque(true);
					label1[k].setBackground(Color.white);
					panel.add(label1[k]);
					k = k+1;
				}
			}

			JPanel panel2 = new JPanel();
			panel2.setFont(new Font("�ʱ�ü", 1, 12));
			panel2.setBorder(new TitledBorder("�ֹ� ����"));
			
			JLabel label1 = new JLabel("����");
			JTextArea ta2 = new JTextArea("", 1, 5);
			JLabel label2 = new JLabel("���̺��");
			
			
			
			// �ӽ÷�
			
			String[] names = { "1", "2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20" };
			for (int i = 0; i < names.length; i++) {
				nameCombo.addItem(names[i]);
			}
			ta123.setBounds(10, 20, 200, 330);
			ta123.setBorder(new LineBorder(Color.gray, 2));
			panel2.add(ta123);
			label1.setBounds(220, 20, 50, 20);
			panel2.add(label1);
			ta2.setBounds(220, 50, 100, 20);
			ta2.setBorder(new LineBorder(Color.gray, 2));
			panel2.add(ta2);
			label2.setBounds(220, 80, 80, 20);
			panel2.add(label2);
			nameCombo.setBounds(280, 110, 40, 20);
			panel2.add(nameCombo);
			button123.setBounds(260, 170, 60, 40);
			button123.addActionListener(this);
			button123.setHorizontalAlignment(JButton.CENTER);
			panel2.add(button123);
			button2.setBounds(260, 240, 60, 40);
			button2.setHorizontalAlignment(JButton.CENTER);
			button2.addActionListener(this);
			button3.addActionListener(this);
			panel2.add(button2);
			button3.setBounds(260, 310, 60, 40);
			button3.setHorizontalAlignment(JButton.CENTER);
			panel2.add(button3);
			panel2.setLayout(null);
			JPanel panel3 = new JPanel();
			panel3.setFont(new Font("�ʱ�ü", 1, 12));
			panel3.setBorder(new TitledBorder("�޴�"));
			int count = 0;
			for (int i = 1; i < 11; i++) {
				for (int j = 0; j < 2; j++) {
					buttons[count] = new JButton();
					buttons[count].setBounds(10 + (j * 170), 5 + (i * 32), 150, 20);
					buttons[count].setHorizontalAlignment(JButton.CENTER);
					panel3.add(buttons[count]);
					buttons[count].addActionListener(this);
					count = count + 1;
					// button.setBorder();

				}
			}

			panel3.setLayout(null);
			;
			JPanel panel4 = new JPanel();
			panel4.setFont(new Font("�ʱ�ü", 1, 12));
			panel4.setBorder(new TitledBorder("���/��ȸ"));
			JTabbedPane pane = createTabbedPane();
			pane.setBounds(20, 20, 300, 330);
			panel4.add(pane, BorderLayout.CENTER);
			panel4.setLayout(null);
			;

			frame.add(panel);
			frame.add(panel2);
			frame.add(panel3);
			frame.add(panel4);
			frame.setTitle("�Ĵ� ���� �ý���  ");
			frame.setSize(700, 800);
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			frame.setVisible(true);
		}

	private JTabbedPane createTabbedPane() {
		JTabbedPane pane = new JTabbedPane();
		JPanel panel = new JPanel();
		panel.setLayout(null);
		JLabel label1 = new JLabel("����");

		label1.setBounds(20, 20, 50, 20);
		ta2.setBounds(20, 50, 100, 20);
		button11.setBounds(160, 40, 60, 30);
		panel.add(button11);
		button11.addActionListener(this);
		button22.setBounds(230, 40, 60, 30);
		button22.addActionListener(this);
		panel.add(button22);
		panel.add(label1);
		panel.add(ta2);
		ta3.setBounds(20, 100, 250, 190);
		panel.add(ta3);
		// ta2.setBounds(20, 50, 100, 20);
		// ta2.setBorder(new LineBorder(Color.gray, 2));
		// label.add(ta2);
		pane.addTab("��", panel);

		JPanel panel2 = new JPanel();
		panel2.setLayout(null);
		JLabel name22 = new JLabel("�Ⱓ");
		name22.setBounds(20, 50, 50, 20);
		JComboBox Combo = new JComboBox();
		// �ӽ÷�
		String[] names = { "2015-01-01", "b" };
		for (int i = 0; i < names.length; i++) {
			Combo.addItem(names[i]);
		}
		Combo.setBounds(60, 40, 100, 30);
		panel2.add(Combo);
		panel2.add(name22);

		JTextArea ta22 = new JTextArea("", 7, 30);
		ta22.setBounds(20, 100, 250, 190);
		panel2.add(ta22);

		pane.addTab("����", panel2);
		JPanel panel3 = new JPanel();
		panel3.setLayout(null);
		JLabel name2 = new JLabel("������");

		name2.setBounds(20, 20, 50, 20);
		ta33.setBounds(20, 50, 100, 20);

		
		button33.setBounds(135, 40, 90, 30);
		panel3.add(button33);
		button33.addActionListener(this);

		button32.setBounds(230, 40, 60, 30);
		panel3.add(button32);
		button32.addActionListener(this);
		panel3.add(name2);
		panel3.add(ta33);

		ta333.setBounds(20, 100, 250, 190);
		panel3.add(ta333);

		pane.addTab("����", panel3);
		JPanel panel4 = new JPanel();
		panel4.setLayout(null);
		JLabel name = new JLabel("�޴���");

		name.setBounds(20, 20, 50, 20);
		ta.setBounds(20, 50, 100, 20);
		
		button345.setBounds(135, 40, 90, 30);
		panel4.add(button345);
		button345.addActionListener(this);
		button44.addActionListener(this);
		button44.setBounds(230, 40, 60, 30);
		panel4.add(button44);
		panel4.add(name);
		panel4.add(ta);

		ta4.setBounds(20, 100, 250, 190);
		panel4.add(ta4);
		pane.addTab("�޴�", panel4);
		return pane;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == loginButton) {
			username = idInput.getText();
			password = new String(pwdInput.getPassword());
			connectDB();

			PCstore();
		} else if (e.getSource() == button22) {
			int a =0;
			try {

				String name = ta2.getText();
				String sqlStr = "select id,name,birth,phone,rank,total from customer where name = '" + name + "'";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					a=a+1;
					String cname = rs.getString("name");
					ta3.append("���� : " + cname);
					String cid = rs.getString("id");
					ta3.append("\n��ID : " + cid);
					String cb = rs.getString("birth");
					ta3.append("\n��   �� : " + cb);
					String cp = rs.getString("phone");
					ta3.append("\n��ȭ��ȣ : " + cp);
					String cr = rs.getString("rank");
					ta3.append("\n����� : " + cr);
					String ct = rs.getString("total");
					ta3.append("\n�ѱ��űݾ� : " + ct);

				}
			} catch (SQLException se) {
				
				se.printStackTrace();
			}
			if(a==0){
				JOptionPane.showMessageDialog(null, "ã���ô� ���� �����ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == button32) {
			int a=0;
			try {

				String name = ta33.getText();
				String sqlStr = "select name,position,record from employee where name = '" + name + "'";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				
				while (rs.next()) {
					a=a+1;
					String cname = rs.getString("name");
					ta333.append("������ : " + cname);
					String cid = rs.getString("position");
					ta333.append("\n���� : " + cid);
					String cb = rs.getString("record");
					ta333.append("\n�ѽ��� : " + cb);

				}
			} catch (SQLException se) {
				
				se.printStackTrace();
			}
			if(a==0){
				JOptionPane.showMessageDialog(null, "ã���ô� ������ �����ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			}

		} else if (e.getSource() == button44) {
			int a=0;
			try {

				String name = ta.getText();
				String sqlStr = "select name,price from menu where name = '" + name + "'";
				PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
				ResultSet rs = stmt.executeQuery();
				
				
				while (rs.next()) {
					a=a+1;
					String cname = rs.getString("name");
					ta4.append("�޴��� : " + cname);
					int cid = rs.getInt("price");
					ta4.append("\n���� : " + cid);

				}
			} catch (SQLException se) {
				
				se.printStackTrace();
			}
			if(a==0){
				JOptionPane.showMessageDialog(null, "ã���ô� �޴��� �����ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			}

		}
		int a=0;
			for(int i=0;i<mcount;i++){
				System.out.print(mcount);
				String cmenu = buttons[i].getText();
				
				if (e.getSource() == buttons[i]) {
				try {
					String sqlStr = "select name,price from menu where name = '" + cmenu + "'";
					PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
					ResultSet rs = stmt.executeQuery();
					
					while (rs.next()) {
						a=a+1;
						String cname = rs.getString("name");
						ta123.append(cname);
						int cid = rs.getInt("price");
						ta123.append("\n���� : " + cid);
						ta123.append("\n\n");

					}
					
				} catch (SQLException se) {
					
					se.printStackTrace();
				}
				}
			}
			if(a==0){
				JOptionPane.showMessageDialog(null, "�޴��� �����ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			}
			
			if (e.getSource() == button123) {
					
						int Tablenum = Integer.valueOf(((String) nameCombo.getSelectedItem()).toUpperCase());
						
						for(int ii=0;ii<20;ii++){
							if(ii==Tablenum-1){
								label1[ii].setBackground(Color.YELLOW);
							}
						}
					
					
					}
				else if (e.getSource() == button2) {
					ta123.setText(" ");
					int Tablenum = Integer.valueOf(((String) nameCombo.getSelectedItem()).toUpperCase());
					System.out.println(Tablenum);
					for(int ii=0;ii<20;ii++){
						if(ii==Tablenum-1){
							label1[ii].setBackground(Color.white);
						}
					}
				
				
				}
				else if (e.getSource() == button3) {
					int Tablenum = Integer.valueOf(((String) nameCombo.getSelectedItem()).toUpperCase());
					System.out.println(Tablenum);
					for(int ii=0;ii<20;ii++){
						if(ii==Tablenum-1){
							label1[ii].setBackground(Color.white);
						}
					}
				}
				else if (e.getSource() == button11) {
					MyDialog1 dialog = new MyDialog1(frame, "ȸ�����");
					dialog.setVisible(true);
				}
				else if (e.getSource() == button33) {
					MyDialog2 dialog = new MyDialog2(frame, "�������");
					dialog.setVisible(true);
				}
				else if (e.getSource() == button345) {
					MyDialog3 dialog = new MyDialog3(frame, "�޴����");
					dialog.setVisible(true);
				}
			
			}
					

			
					
		
			
			
			
		
			
			
		

	
	
	
	private void create_query1() throws SQLException {
		String sqlStr = "create table customer (id	number(4)	not null,name	char(30)	not null,birth	char(30)	not null,phone	char(30)	not null,rank	char(30)	not null,total	integer not null,primary key(id))";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		stmt.executeQuery();

	}

	private void create_query2() throws SQLException {
		String sqlStr = "create table employee (id	number(4)	not null,name char(30)	not null,position	char(30)	not null,record	integer	not null,primary key(id))";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		stmt.executeQuery();

	}

	private void create_query3() throws SQLException {
		String sqlStr = "create table menu (id	number(4),name	char(40)	not null,price	integer	not null,primary key(id))";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		stmt.executeQuery();

	}

	private void insert_query() throws SQLException {
		for (int i = 0; i < 6; i++) {
			String customer[] = List.get(i).split("\t");

			String name33 = customer[0];
			String birth = customer[1];
			String phone = customer[2];
			String rank = customer[3];
			String sqlStr = "insert into customer values (" + i + ",'" + name33 + "','" + birth + "','" + phone + "','"
					+ rank + "'," + i + ")";
			PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
			stmt.executeQuery();
		}
		for (int i = 6; i < 12; i++) {
			String employee[] = List.get(i).split("\t");
			String name3 = employee[0];
			String position = employee[1];
			String sqlStr = "insert into employee values (" + i + ",'" + name3 + "','" + position + "'," + i + ")";
			PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
			stmt.executeQuery();
			stmt.close();

		}
		for (int i = 12; i < 22; i++) {
			String menu[] = List.get(i).split("\t");
			String name34 = menu[0];
			int price = Integer.parseInt(menu[1]);
			String sqlStr = "insert into menu values (" + i + ",'" + name34 + "'," + price + ")";
			PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
			stmt.executeQuery();
			stmt.close();
		}

		int i = 0;
		String sqlStr = "select name from menu";
		PreparedStatement stmt = dbTest.prepareStatement(sqlStr);
		ResultSet rs = stmt.executeQuery();
		while (rs.next()) {
			String a = rs.getString("name");
			System.out.println(a);
			buttons[i].setText(a);
			i=i+1;
			mcount = mcount+1;

		}
		

	}
	

	private void connectDB() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
			dbTest = DriverManager.getConnection("jdbc:oracle:thin:" + "@localhost:1521:XE", username, password);
			System.out.println("�����ͺ��̽����� ���� - id: " + username);
		} catch (SQLException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "������ ���̽��� ���ῡ �����Ͽ����ϴ�.", "���", JOptionPane.WARNING_MESSAGE);
			System.out.println("������ ���̽��� ���ῡ �����Ͽ����ϴ�.");
			System.out.println("SQLException:" + e);
		} catch (Exception e) {
			System.out.println("Exception:" + e);
		}
	}

	public static void main(String[] argv) {
		new restaurant();
	}
}
