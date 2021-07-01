/*
 *Эта программа рассчитывает стоимость удаляемых зеленых насаждений,
 *потребность в удалении которых возникает в процессе строительства.
 *Расчет производится по все действующим техническим нормам (на данный момент).
 *Программа рассчитывает количество базовых величин.
 *Вся информация выводистя в doc файл.
 */
package compensation;

import java.awt.Color;
import java.awt.ComponentOrientation;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;

public class CompensationInMoney {
	
	JFrame jframeWindow = new JFrame ("Расчет компенсации растительности");
	JPanel panelMoney = new JPanel();
	JScrollPane jScrollPaneMoney = new JScrollPane(panelMoney);
	JTabbedPane frameTabs = new JTabbedPane();
	GridBagConstraints constraints = new GridBagConstraints();
	String [] massWhatTreesCompensateMoney = {"Хвойной породы", 
			"Медленнорастущей лиственной породы",
			"Быстрорастущие лиственной породы", "Малоценное лиственное", 
			"Высаженное в целях озеленения: хвойное <1см.",
			"Высаженное в целях озеленения: медленнораст. листв. <1см." , 
			"Высаженное в целях озеленения: быстрораст. листв. <1см." , 
			"Высаженное в целях озеленения: малоцен. листв. <4см." ,
			"Участок поросли(самосева),др. с диаметром <1(4) см"};
	String [] stateOfTree  = {"Хорошее", "Удовлетв", "Плохое", "Ненадлеж"};
	Tree tree = new Tree ();
	ArrayList <Tree> arrayTree = new ArrayList<Tree>();
	JButton pushTheButton = new JButton("PUSH THE BUTTON ");
	JButton plusTheButton = new JButton();
	
	public void launchingTheProgram(){
		createJFrame();
		createJPanelMoney();
		createJTabbedPane();
		constraints.insets = new Insets (1,1,1,1); //расстояние между элементами
		createJComboBox(massWhatTreesCompensateMoney, 0, 0, 2, 6, 2);
		createJComboBox(stateOfTree,3, 0, 1, 6, 1);
		createJTextField("Диаметр(см) или площадь поросли(м2)", 4, 0, 1, 12);
		createJCheckBox("K1", "Удаление объектов растительного мира, в отношении которых установлены ограничения или запреты", 
				1, 5, 0, 1);
		createJCheckBox("K2", "Удаление объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов", 
				2, 6, 0, 1);
		createJCheckBox("K3", "Удаление объектов растительного мира, препятствующих эксплуатации инженерных сетей", 
				3, 7, 0, 1);
		createJCheckBox("K4", "Удаление объектов растительного мира, произрастающих за границами населенных пунктов", 
				4, 8, 0, 1);
		arrayTree.add(tree);
		createJButtonPush();
		createJButtonPlus();
		
		jframeWindow.repaint();
		jframeWindow.setVisible(true);
	}
	
	public static void main(String[] args) {
		CompensationInMoney compensationInMoney = new CompensationInMoney();
		compensationInMoney.launchingTheProgram();
	}
	
	public void createJFrame(){
		jframeWindow.setSize(850,570);
		jframeWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		jframeWindow.setLocationRelativeTo(null);
		jframeWindow.setIconImage(Toolkit.getDefaultToolkit().getImage("./src/Tree.png"));
	}
	
	public void createJPanelMoney(){
		panelMoney.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
		panelMoney.setLayout(new GridBagLayout());
		panelMoney.revalidate();
		jScrollPaneMoney.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		jScrollPaneMoney.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		jScrollPaneMoney.getVerticalScrollBar().setUnitIncrement(10);
	}
	
	public void createJTabbedPane(){
		frameTabs.addTab("Деревья деньгами",jScrollPaneMoney);
	    jframeWindow.getContentPane().add(frameTabs);
	}
	
	public void createJComboBox(String [] arrayInformation, int x, int y, int gridwidth, int ipady, int chooserStateOrName){
		final JComboBox<String> comboBoxName = new JComboBox<String>(arrayInformation);
		if(chooserStateOrName==1){
			treeState(comboBoxName,tree);
		}else if(chooserStateOrName==2) {
			treeName(comboBoxName, tree);
		}
		constraints.gridx = x;
	    constraints.gridy = y;
	    constraints.gridwidth = gridwidth; // 2 колонки шириной
	    constraints.ipady = ipady; // ширина
	    panelMoney.add(comboBoxName,constraints);
	}
	
	public void createJTextField(String nameTextField, int x, int y, int gridwidth, int ipady){
		JTextField textField = new JTextField();
		textField.setToolTipText(nameTextField);
		constraints.gridx = x;
	    constraints.gridy = y;
	    constraints.gridwidth = gridwidth; // 2 колонки шириной
	    constraints.ipady = ipady;// ширина
	    quantityTrees(textField, tree);
	    panelMoney.add(textField,constraints);
	}
	
	public void createJCheckBox(String nameCheckBox, String prompt, int numberCoefficientint, int x, int y, int gridwidth){
		final JCheckBox checkBox =  new  JCheckBox (nameCheckBox);
		checkBox.setToolTipText(prompt);
		Koefficient (checkBox, tree, numberCoefficientint);
		constraints.gridx = x;
	    constraints.gridy = y;
	    constraints.gridwidth = gridwidth; // 1 колонки шириной
	    panelMoney.add(checkBox,constraints);
	}
	
	public void createJButtonPush(){
		pushTheButton.setBackground(Color.GREEN);
		final JLabel labelButton = new JLabel(" ");
		panelMoney.add(labelButton);
		pushTheButton.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			{
			zeroCheck(arrayTree);
			try {
				writefileMoneyCompensation();
			} catch (FileNotFoundException e1) {
				System.out.println("нет этого файла");
				e1.printStackTrace();
			}
			}
		});
		constraints.gridx = 1;
		constraints.gridy = 1;
		constraints.gridwidth = 1;// 1 колонки шириной
		panelMoney.add(pushTheButton,constraints);
	}
	
	public void createJButtonPlus(){
		Icon icon = new ImageIcon("./src/+.png");
		plusTheButton.setIcon(icon);
		JPanel plusPanel = new JPanel();
		plusPanel.add(plusTheButton);
		constraints.gridx = 8;
		constraints.gridy = 1;
		constraints.gridwidth = 1;
		panelMoney.add(plusPanel,constraints);
		plusTheButton.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			{
			Tree theTreePlus = new Tree ();
			final JComboBox<String> comboBoxTree = new JComboBox<String>(massWhatTreesCompensateMoney);
			treeName(comboBoxTree, theTreePlus);
		    constraints.gridy +=2;
		    panelMoney.add(plusPanel,constraints);
		    constraints.gridx = 1;
		    constraints.gridwidth = 2;// ширина
		    panelMoney.add(pushTheButton,constraints);
		    constraints.gridy -=1;
		    constraints.gridwidth = 2; // 2 колонки шириной
		    constraints.ipady = 6; // ширина
		    panelMoney.add(comboBoxTree,constraints);
		    
		    final JComboBox<String> comboBoxState = new JComboBox<String>(stateOfTree);
		    treeState(comboBoxState, theTreePlus);
		    constraints.gridx +=2;
		    constraints.gridwidth = 1; // 1 колонки шириной
		    constraints.ipady = 6; // ширина
		    panelMoney.add(comboBoxState,constraints);
		    
		    JTextField textFieldDiameter = new JTextField();
		    textFieldDiameter.setToolTipText("Диаметр(см) или площадь поросли(м2)");
		    constraints.gridx +=1;
		    constraints.gridwidth = 1; // 1 колонки шириной
		    constraints.ipady = 12; // ширина
		    panelMoney.add(textFieldDiameter,constraints);
		    quantityTrees(textFieldDiameter,theTreePlus);
		    arrayTree.add(theTreePlus);
		   
		    final JCheckBox checkBoxK1 =  new  JCheckBox ( "K1" );
		    checkBoxK1.setToolTipText("Удаление объектов растительного мира, в отношении которых установлены ограничения или запреты"); //Подсказка при наведенеии курсора
		    Koefficient (checkBoxK1, theTreePlus, 1);
		    constraints.gridx +=1;
		    constraints.gridwidth = 1; // 1 колонки шириной
		    panelMoney.add(checkBoxK1,constraints);
		    
		    final JCheckBox checkBoxK2 =  new  JCheckBox ( "K2" );
		    checkBoxK2.setToolTipText("Удаление объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов"); //Подсказка при наведенеии курсора
		    Koefficient (checkBoxK2, theTreePlus, 2);
		    constraints.gridx +=1;
		    panelMoney.add(checkBoxK2,constraints);
		    
		    final JCheckBox checkBoxK3 =  new  JCheckBox ( "K3" );
		    checkBoxK3.setToolTipText("Удаление объектов растительного мира, препятствующих эксплуатации инженерных сетей"); //Подсказка при наведенеии курсора
		    Koefficient (checkBoxK3, theTreePlus, 3);
		    constraints.gridx +=1;
		    panelMoney.add(checkBoxK3,constraints);
		    
		    final JCheckBox checkBoxK4 =  new  JCheckBox ( "K4" );
		    checkBoxK4.setToolTipText("Удаление объектов растительного мира, произрастающих за границами населенных пунктов"); //Подсказка при наведенеии курсора
		    Koefficient (checkBoxK4, theTreePlus, 4);
		    constraints.gridx +=1;
		    panelMoney.add(checkBoxK4,constraints);
		    
		    jframeWindow.repaint();
			}

		});
	}
	
	public void treeName(JComboBox<String> comboBoxName, Tree tree) {
		comboBoxName.setSelectedItem(null);
		comboBoxName.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if (comboBoxName.getSelectedItem().equals("Хвойной породы")){tree.treeName = "Хвойной породы";};
				if (comboBoxName.getSelectedItem().equals("Медленнорастущей лиственной породы")){tree.treeName = "Медленнорастущей лиственной породы";};
				if (comboBoxName.getSelectedItem().equals("Быстрорастущие лиственной породы")){tree.treeName = "Быстрорастущие лиственной породы";};
				if (comboBoxName.getSelectedItem().equals("Малоценное лиственное")){tree.treeName = "Малоценное лиственное";};
				if (comboBoxName.getSelectedItem().equals("Высаженное в целях озеленения: хвойное <1см.")){tree.treeName = "Высаженное в целях озеленения: хвойное <1см.";};
				if (comboBoxName.getSelectedItem().equals("Высаженное в целях озеленения: медленнораст. листв. <1см.")){tree.treeName = "Высаженное в целях озеленения: медленнораст. листв. <1см.";};
				if (comboBoxName.getSelectedItem().equals("Высаженное в целях озеленения: быстрораст. листв. <1см.")){tree.treeName = "Высаженное в целях озеленения: быстрораст. листв. <1см.";};
				if (comboBoxName.getSelectedItem().equals("Высаженное в целях озеленения: малоцен. листв. <4см.")){tree.treeName = "Высаженное в целях озеленения: малоцен. листв. <4см.";};
				if (comboBoxName.getSelectedItem().equals("Участок поросли(самосева),др. с диаметром <1(4) см")){tree.treeName = "Участок поросли(самосева),др. с диаметром <1(4) см";};	
			}
		});
    }
	
	public void quantityTrees(JTextField jtf, Tree tree) {
			jtf.setText("Ø/Sпор");
			jtf.addMouseListener(new MouseAdapter(){
	            public void mouseClicked(MouseEvent me) {
	                ((JTextField)me.getSource()).selectAll();
	            }
	        });
			jtf.addActionListener(new ActionListener()
				{
					public void actionPerformed(ActionEvent e) 
					{
						String str1 = jtf.getText();
						if (str1 == null) {jtf.setText("");}
							if (itContainsInregerNumbers(str1, "[0-9]{0,2}[.]?[0-9]{0,1}")){jtf.setBackground(null); {
								jtf.setText(str1);
								tree.diameter = Double.parseDouble(str1);
				  																									}
																							}	
						else {jtf.setBackground(Color.RED);jtf.setText("Диаметр/Площадь поросли");}		 
					}		
				});
		}
	
	public static boolean itContainsInregerNumbers(String s, String s1)
		{
	       return s.matches(s1);
		}
	
	public void Koefficient (JCheckBox checkBox1, Tree tree, int i){
    	checkBox1.addItemListener(new ItemListener() {
			@Override
			public void itemStateChanged(ItemEvent e) {
				if(e.getStateChange()==1 && i == 1){tree.K1=2; };
				if(e.getStateChange()==1 && i == 2){tree.K2=0.5; };
				if(e.getStateChange()==1 && i == 3){tree.K3=0.5; };
				if(e.getStateChange()==1 && i == 4){tree.K4=0.1; };
			}    
          });   	
    }
	
	public void zeroCheck(ArrayList<Tree> arrayTree) {
		boolean boo=true;
		for (Tree tree : arrayTree) {
			System.out.println(tree.toString());
			if ( (tree.treeName == null && (tree.state != null || tree.diameter > 0))  
					|| (tree.state == null && (tree.treeName != null || tree.diameter > 0))
					|| (tree.diameter == 0 && (tree.treeName != null || tree.state != null))
					|| tree.treeName == null) 
			{boo = false;
			}else boo = true;
		}	
		if (boo){
			JOptionPane.showMessageDialog(null,"Расчет выполнен успешно.См'Расчет компенсации деревьев(деньгами).doc'","All is Good",JOptionPane.PLAIN_MESSAGE);
			countingMoneyForOneTree (arrayTree);
		}else if (boo==false) JOptionPane.showMessageDialog(null,"Вы не ввели все требуемые значения","Red Alert",JOptionPane.PLAIN_MESSAGE);
	}

	public void countingMoneyForOneTree (ArrayList<Tree> arrayTree){
		for (Tree tree : arrayTree) {
		if ( tree.treeName.equals("Хвойной породы")  ) {
					if ( tree.diameter>= 1 &&  tree.diameter<=4 ) {
						tree.S = 2;
					} else if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) {
						tree.S = 5;
					} else if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) {
						tree.S = 7;
					} else if ( tree.diameter>= 12.1 &&  tree.diameter<=16 ) {
						tree.S = 10;
					} else if ( tree.diameter>= 16.1 &&  tree.diameter<=20 ) {
						tree.S = 12;
					} else if ( tree.diameter>= 20.1 &&  tree.diameter<=30 ) {
						tree.S = 13;
					} else if ( tree.diameter>30) {
						tree.S = 15;
					}
		} else if ( tree.treeName.equals("Медленнорастущей лиственной породы") ) {
					if ( tree.diameter>= 1 &&  tree.diameter<=4 ) {
						tree.S = 2;	
					} else if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) {
						tree.S = 5;	
					} else if ( tree.diameter>= 8.1 &&  tree.diameter<=10 ) {
						tree.S = 7;	
					} else if ( tree.diameter>= 10.1 &&  tree.diameter<=14 ) {
						tree.S = 10;	
					} else if ( tree.diameter>= 14.1 &&  tree.diameter<=16 ) {
						tree.S = 12;	
					} else if ( tree.diameter>= 16.1 &&  tree.diameter<=22 ) {
						tree.S = 13.5;	
					} else if ( tree.diameter>= 22.1 &&  tree.diameter<=28 ) 
					{
						tree.S = 15;	
					} else if ( tree.diameter>28) 
					{
						tree.S = 17;	
					}
		} else if ( tree.treeName.equals("Быстрорастущие лиственной породы") ) {
			if ( tree.diameter>= 1 &&  tree.diameter<=4 ) {
						tree.S = 1;	
					} else if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) {
						tree.S = 3;	
					} else if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) {
						tree.S = 5;	
					} else if ( tree.diameter>= 12.1 &&  tree.diameter<=15 ) {
						tree.S = 7;	
					} else if ( tree.diameter>= 15.1 &&  tree.diameter<=18 ) {
						tree.S = 10;	
					} else if ( tree.diameter>= 18.1 &&  tree.diameter<=23 ) {
						tree.S = 12;	
					} else if ( tree.diameter>23) {
						tree.S = 14;	
					}
		} else if ( tree.treeName.equals("Малоценное лиственное") ) {
			if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) {
						tree.S = 0.3;	
					} else if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) {
						tree.S = 1;	
					} else if ( tree.diameter>= 12.1 &&  tree.diameter<=15 ) {
						tree.S = 2;	
					} else if ( tree.diameter>15) {
						tree.S = 5;	
					}	
		} else if ( tree.treeName.equals("Высаженное в целях озеленения: хвойное <1см.") || tree.treeName.equals("Высаженное в целях озеленения: медленнораст. листв. <1см.") ) {
			tree.S = 2;	
		} else if ( tree.treeName.equals("Высаженное в целях озеленения: быстрораст. листв. <1см.") ) {
			tree.S = 1;	
		} else if ( tree.treeName.equals("Высаженное в целях озеленения: малоцен. листв. <4см.") ) {
			tree.S = 0.3;
		} else if ( tree.treeName.equals("Участок поросли(самосева),др. с диаметром <1(4) см") ) {
			tree.S = 0.2;
		}
		}
		
		
	}

	public void writefileMoneyCompensation() throws FileNotFoundException{ 
		File file = new File ("./src/result/", "Расчет компенсации деревьев(деньгами).doc");
		PrintWriter pw = new PrintWriter(file);
		pw.println("");
		pw.print("			РАСЧЕТ КОМПЕНСАЦИИ ДЕРЕВЬЕВ ");
		pw.println("");
		pw.println("");
		pw.println("Расчет выполняется в соответствии с Постановлением Совета Министров от 08.05.2013 г. № 354 «Положение о порядке определения условий проведения");
		pw.println("компенсационных посадок либо осуществления компенсационных выплат стоимости удаляемых, пересаживаемых объектов растительного мира» (Приложение 4).");
		pw.println("Размер компенсационных посадок рассчитывается по следующей формуле:");
		pw.println("  V = S * K1 * K2 * K3 * K4  * К5, ");
		pw.println("где V - размер компенсационных выплат (в белорусских рублях)");
		pw.println("S - стоимость i-го удаляемого объекта растительного мира согласно приложениям 6–8 (в базовых величинах);");
		pw.println("К1 - коэффициент, равный 2, применяемый в случаях удаления объектов растительного мира, в отношении которых установлены ограничения или запреты и (или) расположенных в границах природных территорий, подлежащих особой и (или) специальной охране;");
		pw.println("К2 - коэффициент, равный 0,5, применяемый в случаях удаления объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов без привлечения иных источников финансирования (при наличии письменного подтверждения заказчика о всех источниках финансирования такого строительства) и (или) в интересах и на территории бюджетной организации;");
		pw.println("К3 - коэффициент, равный 0,5, применяемый в случаях удаления объектов растительного мира, препятствующих эксплуатации инженерных сетей, осуществляемого на основании разрешения на удаление, решения об изменении или снятии установленных ограничений или запретов;");
		pw.println("К4 - коэффициент, равный 0,1, применяемый в случаях удаления объектов растительного мира, произрастающих за границами населенных пунктов.");
		pw.println("К5 - коэффициент качественного состояния вырубаемых объектов растительного мира,");
		pw.println("-для удаляемых деревьев, находящихся в хорошем качественном состоянии, – коэффициент, равный 1;");
		pw.println("-для удаляемых деревьев, находящихся в удовлетворительном качественном состоянии, – коэффициент, равный 0,75;");
		pw.println("-для удаляемых деревьев, находящихся в плохом качественном состоянии, – коэффициент, равный 0,5;");
		pw.println("-для удаляемых деревьев, находящихся в ненадлежащем качественном состоянии, – коэффициент, равный 0,25;");
		pw.println("");
		pw.println(" V = ");
		arrayDecomposition(arrayTree);
		
		double compensationAmount = 0;
		for ( Tree tree : arrayTree) {
			double V = tree.S * tree.K1 * tree.K2 * tree.K3 * tree.K4 * tree.Kstate;
			pw.print(String.format("%.0f",V) + " + "); 
			compensationAmount += tree.S * tree.K1 * tree.K2 * tree.K3 * tree.K4 * tree.Kstate;
		}
		pw.print(" = " + String.format("%.0f",compensationAmount)); // округление до целого
		pw.close();
	}
	
	public void arrayDecomposition(ArrayList <Tree> arrayTree){	
	StringBuffer coniferTree = new StringBuffer("");
	StringBuffer slowGrowingTree = new StringBuffer("");
	StringBuffer fastGrowingTree = new StringBuffer("");
	StringBuffer lowValueTree = new StringBuffer("");
	StringBuffer plantedConiferousTree = new StringBuffer("");
	StringBuffer plantedSlowGrowingTree = new StringBuffer("");
	StringBuffer plantedFastGrowingTree = new StringBuffer("");
	StringBuffer plantedlowValueTree = new StringBuffer("");
	StringBuffer Porosol = new StringBuffer("");
	
		for ( Tree tree : arrayTree) {
			if (tree.treeName.equals("Хвойной породы")){
				coniferTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Медленнорастущей лиственной породы")) {slowGrowingTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Быстрорастущие лиственной породы")) {fastGrowingTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Малоценное лиственное")) {lowValueTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Высаженное в целях озеленения: хвойное <1см.")) {plantedConiferousTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Высаженное в целях озеленения: медленнораст. листв. <1см.")) {plantedSlowGrowingTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Высаженное в целях озеленения: быстрораст. листв. <1см.")) {plantedFastGrowingTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else if (tree.treeName.equals("Высаженное в целях озеленения: малоцен. листв. <4см.")) {plantedlowValueTree.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				} else {Porosol.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");
				}						
		}
		
		if(coniferTree.equals("")) {removePlusSign(coniferTree);
		} else if(slowGrowingTree.equals("")) {removePlusSign(slowGrowingTree);
		} else if(fastGrowingTree.equals("")) {removePlusSign(fastGrowingTree);
		} else if(lowValueTree.equals("")) {removePlusSign(lowValueTree);
		} else if(plantedConiferousTree.equals("")) {removePlusSign(plantedConiferousTree);
		} else if(plantedSlowGrowingTree.equals("")) {removePlusSign(plantedSlowGrowingTree);
		} else if(plantedFastGrowingTree.equals("")) {removePlusSign(plantedFastGrowingTree);
		} else if(plantedlowValueTree.equals("")) {removePlusSign(plantedlowValueTree);
		} else if(Porosol.equals("")) {removePlusSign(Porosol);
		};
	};
	
	public void removePlusSign(StringBuffer strB){
		strB.delete(strB.length()-3,strB.length()-1); strB.append(" = ");
		System.out.println(strB);	
	};
	
	public void treeState(JComboBox<String> comboBox, Tree tree) {
		comboBox.setSelectedItem(null);
		comboBox.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if (comboBox.getSelectedItem().equals("Хорошее")){tree.state = "Хорошее"; tree.Kstate = 1; };
				if (comboBox.getSelectedItem().equals("Удовлетв")){tree.state = "Удовлетв"; tree.Kstate = 0.75;};
				if (comboBox.getSelectedItem().equals("Плохое")){tree.state = "Плохое"; tree.Kstate = 0.5;};
				if (comboBox.getSelectedItem().equals("Ненадлеж")){tree.state = "Ненадлеж"; tree.Kstate = 0.25;};
			}
		});
    }
}
