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
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
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


public class Compensation_v_2_0 extends JFrame{
	
	private static final long serialVersionUID = 1L;
	static int numberOfTreesHvoinoe_hor;
	static int numberOfTreesHvoinoe_udovl;
	static int numberOfTreesHvoinoe_ploh;
	static int numberOfTreesHvoinoe_nenadl;
	
	static int numberOfTreesMedlennoeListvennoe_hor;
	static int numberOfTreesMedlennoeListvennoe_udovl;
	static int numberOfTreesMedlennoeListvennoe_ploh;
	static int numberOfTreesMedlennoeListvennoe_nenadl;
	
	static int numberOfTreesBystroListvennoe_hor;
	static int numberOfTreesBystroListvennoe_udovl;
	static int numberOfTreesBystroListvennoe_ploh;
	static int numberOfTreesBystroListvennoe_nenadl;
	
	static int numberOfTreesMalocennoe_hor;
	static int numberOfTreesMalocennoe_udovl;
	static int numberOfTreesMalocennoe_ploh;
	static int numberOfTreesMalocennoe_nenadl;
	
	static int numberOfOther_hor;
	static int numberOfOther_udovl;
	static int numberOfOther_ploh;
	static int numberOfOther_nenadl;
	
	int ThanTree,ThanTree_$, skolkoDereviev, K1_ogranichenia;
	
	static double numberOfPorosol_hor;
	static double numberOfPorosol_udovl;
	static double numberOfPorosol_ploh;
	static double numberOfPorosol_nenadl;
	
	static double  Vobj;
	static double  Vobj_$;
	
	double diametrObolochka, K2_budget, K3_seti, K4_naselennyiPunt; 
	
	double K5_sostoianie_hor = 1;;
	double K5_sostoianie_udovl = 0.75;
	double K5_sostoianie_ploh = 0.5;
	double K5_sostoianie_nenadl = 0.1;
	
	
	
	DecimalFormat df = new DecimalFormat("###.##"); //округляет цифры с плав. точкой до 2х знаков после запятой

	String [] massWhatTreesCompensate = {"Хвойной породы", "Медленнорастущей лиственной породы", "Быстрорастущие лиственной породы"};
	
	String [] massWhatTreesCompensate_$ = {"Хвойной породы", "Медленнорастущей лиственной породы", "Быстрорастущие лиственной породы", "Малоценное лиственное"
			, "Высаженное в целях озеленения: хвойное <1см.", "Высаженное в целях озеленения: медленнораст. листв. <1см." , 
			"Высаженное в целях озеленения: быстрораст. листв. <1см." , "Высаженное в целях озеленения: малоцен. листв. <4см." ,
			"Участок поросли(самосева),др. с диаметром <1(4) см"};
	
	String [] yesNo  = {"Да", "Нет"};
	
	String [] state  = {"Хорошее", "Удовлетв", "Плохое", "Ненадлеж"};
	
	StringBuffer Hvoinoe = new StringBuffer("");
	StringBuffer Medlenno = new StringBuffer("");
	StringBuffer Bystro = new StringBuffer("");
	StringBuffer Malocennoe = new StringBuffer("");
	StringBuffer VysagennoeHvoinoe = new StringBuffer("");
	StringBuffer VysagennoeMedlenno = new StringBuffer("");
	StringBuffer VysagennoeBystro = new StringBuffer("");
	StringBuffer VysagennoeMalocennoe = new StringBuffer("");
	StringBuffer Porosol = new StringBuffer("");
	
	ArrayList <Tree> arrayTree = new ArrayList<Tree>();
	
	
	public void go()
	
	{
	
		JFrame jfrm = new JFrame ("Расчет компенсации растительности");
	    jfrm.setSize(850,570);
	    jfrm.setDefaultCloseOperation(EXIT_ON_CLOSE);
	    jfrm.setLocationRelativeTo(null);
	    jfrm.setIconImage(Toolkit.getDefaultToolkit().getImage("C:/Users/k801_1/workspace1/compensation/src/Tree.png")); //установка иконки
		
	    JPanel panel = new JPanel();
	    JScrollPane jScrollPane = new JScrollPane(panel);
	    panel.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    panel.setLayout(new GridBagLayout());
	    panel.revalidate();
	    jScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jScrollPane.getVerticalScrollBar().setUnitIncrement(10);//увеличение скорости прокрутки
	    
	    JPanel panelSecond = new JPanel();
	    JScrollPane jScrollPaneSecond = new JScrollPane(panelSecond);
	    panelSecond.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    panelSecond.setLayout(new GridBagLayout());
	    panelSecond.revalidate();
	    jScrollPaneSecond.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPaneSecond.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jScrollPaneSecond.getVerticalScrollBar().setUnitIncrement(10);//увеличение скорости прокрутки
	    
	    JPanel panelThird = new JPanel();
	    JScrollPane jScrollPanelThird = new JScrollPane(panelThird);
	    panelThird.setComponentOrientation(ComponentOrientation.LEFT_TO_RIGHT);
	    panelThird.setLayout(new GridBagLayout());
	    panelThird.revalidate();
	    jScrollPanelThird.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
	    jScrollPanelThird.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
	    jScrollPanelThird.getVerticalScrollBar().setUnitIncrement(10);//увеличение скорости прокрутки
	    
	    JTabbedPane jtp = new JTabbedPane();
	    jtp.addTab("Деревья деревьями",jScrollPane);
	    jtp.addTab("Деревья деньгами",jScrollPaneSecond);
	    jtp.addTab("Виды деревьев",jScrollPanelThird);
	    jfrm.getContentPane().add(jtp);
	    

	    GridBagConstraints constraints = new GridBagConstraints();
	    constraints.insets = new Insets (0,0,0,0); // расстояние между элементами
	    
	    JLabel jlab1_1 = new JLabel("<html> <ul><font size = 5 color=red >К хвойным относятся : </font>"
	    		+ "<li> <i>ель обыкновенная, ель колючая, ель канадская, сосна обыкновенная, </i>"
	    		+ "<li> <i>сосна черная, сосна кедровая сибирская, сосна Веймутова, туя западная,</i>"
	    		+ "<li> <i>дуглассия (псевдотсуга), пихта одноцветная, пихта сибирская, лиственница европейская,</i>"
	    		+ "<li> <i>лиственница сибирская, лиственница Сукачева и другие хвойные деревья.</ul></html>");
	    JLabel jlab1_2 = new JLabel("<html> <ul><font size = 5 color=red>К лиственным медленнорастущим :</font>"
	    		+ "<li> <i>вяз гладкий, вяз перисто-ветвистый, вяз шершавый (ильм), граб обыкновенный,</i>"
	    		+ "<li> <i>дуб красный, дуб черешчатый, каштан конский, сумах, клен ложноплатановый (явор),</i>"
	    		+ "<li> <i>липа мелколистная, липа крупнолистная, береза карельская и другие лиственные медленнорастущие деревья.</ul>");
	    
	    JLabel jlab1_3 = new JLabel("<html> <ul><font size = 5 color=red>К лиственным быстрорастущим :</font>"
	    		+ "<li> <i>береза пушистая, береза повислая, ива серебристая, ива белая,</i>"
	    		+ "<li> <i>ива вавилонская, ясень обыкновенный, ясень пенсильванский, ясень маньчжурский,</i>"
	    		+ "<li> <i>орех грецкий, орех серый, орех маньчжурский, клен остролистный,</i>"
	    		+ "<li> <i>клен серебристый, тополь пирамидальный и другие лиственные быстрорастущие деревья.</ul>");
	    
	    JLabel jlab1_4 = new JLabel("<html> <ul><font size = 5 color=red>К лиственным плодовым :</font>"
	    		+ "<li> <i>груша обыкновенная, яблоня домашняя, алыча, слива,</i>"
	    		+ "<li> <i>вишня, черешня, рябина обыкновенная,рябина гибридная,</i>"
	    		+ "<li> <i>черемуха обыкновенная, черемуха виргинская, черемуха поздняя, черемуха Маака,</i>"
	    		+ "<li> <i>облепиха, шелковица и другие лиственные плодовые деревья.</ul>");
	    
	    JLabel jlab1_5 = new JLabel("<html> <ul><font size = 5 color=red>К лиственным малоценным :</font>"
	    		+ "<li> <i>ива ломкая, ива козья, ива серая, тополь (все виды, кроме пирамидального),</i>"
	    		+ "<li> <i>ольха серая и другие лиственные малоценные деревья.</ul>"); // <i> - курсив  <li> - новая строка
	    
	    constraints.gridx = 0;
	    constraints.gridy = 0;
	    constraints.weightx = 0;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    
	    panelThird.add(jlab1_1,constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    constraints.weightx = 0;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    
	    panelThird.add(jlab1_2,constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 2;
	    constraints.weightx = 0;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    
	    panelThird.add(jlab1_3,constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.weightx = 0;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    
	    panelThird.add(jlab1_4,constraints);
	    
	    constraints.gridx = 0;
	    constraints.gridy = 4;
	    constraints.weightx = 0;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    
	    panelThird.add(jlab1_5,constraints);
	    
	    	constraints.insets = new Insets (1,1,1,1); // расстояние между элементами
	    
		    JLabel jlab11 = new JLabel("ХОРОШ");
		    constraints.gridx = 1;
		    constraints.gridy = 0;
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab11,constraints);
		    
		    JLabel jlab12 = new JLabel("УДОВЛ.");
		    constraints.gridx = 2;
		    constraints.gridy = 0;
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab12,constraints);
		   
		    JLabel jlab13 = new JLabel("ПЛОХОЕ");
		    constraints.gridx = 3;
		    constraints.gridy = 0;
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab13,constraints);
		    
		    JLabel jlab14 = new JLabel("НЕНАДЛЕЖ.");
		    constraints.gridx = 4;
		    constraints.gridy = 0;
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab14,constraints);
	    
	    JLabel jlab20 = new JLabel("СКОЛЬКО ХВОЙНЫХ ДЕРЕВЬЕВ ВЫРУБАЕТСЯ(ДИАМЕТР >= 1см), ШТ.?");
	    constraints.gridx = 0;
	    constraints.gridy = 1;
	    //constraints.ipady = 15;
	    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
	    constraints.weightx = 0.5;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(jlab20,constraints);
	    
	    JTextField jtf21 = new JTextField();
	    Kolichestvo(jtf21,1);
		constraints.gridx = 1;
	    constraints.gridy = 1;
	    constraints.weightx = 0.5;
	    constraints.ipady = 15;
		panel.add(jtf21,constraints);
		
		JTextField jtf22 = new JTextField();
	    Kolichestvo(jtf22,2);
		constraints.gridx = 2;
	    constraints.gridy = 1;
	    constraints.weightx = 0.5;
		panel.add(jtf22,constraints);
		
		JTextField jtf23 = new JTextField();
	    Kolichestvo(jtf23,3);
		constraints.gridx = 3;
	    constraints.gridy = 1;
	    constraints.weightx = 0.5;
		panel.add(jtf23,constraints);
		
		JTextField jtf24 = new JTextField();
	    Kolichestvo(jtf24,4);
		constraints.gridx = 4;
	    constraints.gridy = 1;
	    constraints.weightx = 0.5;
		panel.add(jtf24,constraints);
		
			JLabel jlab30 = new JLabel("А МЕДЛЕНОРАСТ. ЛИСТВЕННЫХ  ВЫРУБАЕТСЯ(ДИАМЕТР >= 1см), ШТ.?");
		    constraints.gridx = 0;
		    constraints.gridy = 2;
		    //constraints.ipady = 15;
		    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab30,constraints);
			
		    JTextField jtf31 = new JTextField();
		    Kolichestvo(jtf31,5);
			constraints.gridx = 1;
		    constraints.gridy = 2;
		    constraints.weightx = 0.5;
			panel.add(jtf31,constraints);
			
			JTextField jtf32 = new JTextField();
		    Kolichestvo(jtf32,6);
			constraints.gridx = 2;
		    constraints.gridy = 2;
		    constraints.weightx = 0.5;
			panel.add(jtf32,constraints);
			
			JTextField jtf33 = new JTextField();
		    Kolichestvo(jtf33,7);
			constraints.gridx = 3;
		    constraints.gridy = 2;
		    constraints.weightx = 0.5;
			panel.add(jtf33,constraints);
			
			JTextField jtf34 = new JTextField();
		    Kolichestvo(jtf34,8);
			constraints.gridx = 4;
		    constraints.gridy = 2;
		    constraints.weightx = 0.5;
			panel.add(jtf34,constraints);
		
		JLabel jlab40 = new JLabel("А БЫСТРОРАСТ. ЛИСТВЕННЫХ  ВЫРУБАЕТСЯ(ДИАМЕТР >= 1см), ШТ.?");
	    constraints.gridx = 0;
	    constraints.gridy = 3;
	    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
	    constraints.weightx = 0.5;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(jlab40,constraints);
	    
	    JTextField jtf41 = new JTextField();
	    Kolichestvo(jtf41,9);
		constraints.gridx = 1;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
		panel.add(jtf41,constraints);
	    
		JTextField jtf42 = new JTextField();
	    Kolichestvo(jtf42,10);
		constraints.gridx = 2;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
		panel.add(jtf42,constraints);
		
		JTextField jtf43 = new JTextField();
	    Kolichestvo(jtf43,11);
		constraints.gridx = 3;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
		panel.add(jtf43,constraints);
		
		JTextField jtf44 = new JTextField();
	    Kolichestvo(jtf44,12);
		constraints.gridx = 4;
	    constraints.gridy = 3;
	    constraints.weightx = 0.5;
		panel.add(jtf44,constraints);
		
			JLabel jlab50 = new JLabel("А МАЛОЦЕННОЙ ПОРОДЫ  ВЫРУБАЕТСЯ(ДИАМЕТР >= 4см), ШТ.?");
		    constraints.gridx = 0;
		    constraints.gridy = 4;
		    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab50,constraints);
		    
		    JTextField jtf51 = new JTextField();
		    Kolichestvo(jtf51,13);
			constraints.gridx = 1;
		    constraints.gridy = 4;
		    constraints.weightx = 0.5;
			panel.add(jtf51,constraints);
			
			JTextField jtf52 = new JTextField();
		    Kolichestvo(jtf52,14);
			constraints.gridx = 2;
		    constraints.gridy = 4;
		    constraints.weightx = 0.5;
			panel.add(jtf52,constraints);
			
			JTextField jtf53 = new JTextField();
		    Kolichestvo(jtf53,15);
			constraints.gridx = 3;
		    constraints.gridy = 4;
		    constraints.weightx = 0.5;
			panel.add(jtf53,constraints);
			
			JTextField jtf54 = new JTextField();
		    Kolichestvo(jtf54,16);
			constraints.gridx = 4;
		    constraints.gridy = 4;
		    constraints.weightx = 0.5;
			panel.add(jtf54,constraints);		
		
		JLabel jlab60 = new JLabel("А ХВОЙН.,ЛИСТВ.МЕДЛЕН/БЫСТРО(<1СМ),МАЛОЦЕН.(ДИАМЕТР < 4см),ШТ.?");
	    constraints.gridx = 0;
	    constraints.gridy = 5;
	    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
	    constraints.weightx = 0.5;  
	    constraints.fill = GridBagConstraints.HORIZONTAL;
	    panel.add(jlab60,constraints);
		
	    JTextField jtf61 = new JTextField();
	    Kolichestvo(jtf61,17);
		constraints.gridx = 1;
	    constraints.gridy = 5;
	    constraints.weightx = 0.5;
		panel.add(jtf61,constraints);
	    
		JTextField jtf62 = new JTextField();
	    Kolichestvo(jtf62,18);
		constraints.gridx = 2;
	    constraints.gridy = 5;
	    constraints.weightx = 0.5;
		panel.add(jtf62,constraints);
		
		JTextField jtf63 = new JTextField();
	    Kolichestvo(jtf63,19);
		constraints.gridx = 3;
	    constraints.gridy = 5;
	    constraints.weightx = 0.5;
		panel.add(jtf63,constraints);
		
		JTextField jtf64 = new JTextField();
	    Kolichestvo(jtf64,20);
		constraints.gridx = 4;
	    constraints.gridy = 5;
	    constraints.weightx = 0.5;
		panel.add(jtf64,constraints);
		
			JLabel jlab70 = new JLabel("А САМОСЕВ/ПОРОСОЛЬ, 10КВ.МЕТРОВ?");
		    constraints.gridx = 0;
		    constraints.gridy = 6;
		    constraints.weighty = 1.0; // запрашиваем дополнительное вертикальное пространство
		    constraints.weightx = 0.5;  
		    constraints.fill = GridBagConstraints.HORIZONTAL;
		    panel.add(jlab70,constraints);
		    
		    JTextField jtf71 = new JTextField();
		    Kolichestvo(jtf71,21);
			constraints.gridx = 1;
		    constraints.gridy = 6;
		    constraints.weightx = 0.5;
			panel.add(jtf71,constraints);
			
			JTextField jtf72 = new JTextField();
		    Kolichestvo(jtf72,22);
			constraints.gridx = 2;
		    constraints.gridy = 6;
		    constraints.weightx = 0.5;
			panel.add(jtf72,constraints);
			
			JTextField jtf73 = new JTextField();
		    Kolichestvo(jtf73,23);
			constraints.gridx = 3;
		    constraints.gridy = 6;
		    constraints.weightx = 0.5;
			panel.add(jtf73,constraints);
			
			JTextField jtf74 = new JTextField();
		    Kolichestvo(jtf74,24);
			constraints.gridx = 4;
		    constraints.gridy = 6;
		    constraints.weightx = 0.5;
			panel.add(jtf74,constraints);
			
			
		JLabel jlab80 = new JLabel("КАКИМИ ДЕРЕВЬЯМИ БУДЕТ КОМПЕНСАЦИЯ?");
		constraints.gridx = 0;
	    constraints.gridy = 7;
	    constraints.weightx = 0.5;
	    panel.add(jlab80,constraints);
		
	    final JComboBox<String> jcmbb81 = new JComboBox<String>(massWhatTreesCompensate);
	    jcmbb81.setSelectedItem(null);
	    jcmbb81.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				if (jcmbb81.getSelectedItem().equals("Хвойной породы")){ThanTree = 1;};
				if (jcmbb81.getSelectedItem().equals("Медленнорастущей лиственной породы")){ThanTree = 2;};
				if (jcmbb81.getSelectedItem().equals("Быстрорастущие лиственной породы")){ThanTree = 3;};
				
			}
		});
		
	    constraints.gridx = 1;
	    constraints.gridy = 7;
	    constraints.gridwidth = 3; // 3 колонки шириной
	    panel.add(jcmbb81,constraints);
	    constraints.gridwidth = 1;
	    
		    JLabel jlab90 = new JLabel("К ВЫРУБАЕМЫМ ДЕРЕВЬЯМ УСТАНОВЛЕНЫ ЗАПРЕТЫ/ОГРАНИЧЕНИЯ?");
			constraints.gridx = 0;
		    constraints.gridy = 8;
		    //constraints.weightx = 0.5;
		    panel.add(jlab90,constraints);
		    
		    final JComboBox<String> jcmbb91 = new JComboBox<String>(yesNo);
		    jcmbb91.setSelectedItem(null);
		    jcmbb91.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					if (jcmbb91.getSelectedItem().equals("Да")){K1_ogranichenia = 2;};
					if (jcmbb91.getSelectedItem().equals("Нет")){K1_ogranichenia = 1;};
					
				}
			});
			
		    constraints.gridx = 1;
		    constraints.gridy = 8;
		    constraints.gridwidth = 3; // 3 колонки шириной
		    panel.add(jcmbb91,constraints);
		    constraints.gridwidth = 1;
	    
		JLabel jlab10_0 = new JLabel("ФИНАНСИРОВАНИЕ ЗА СЧЕТ РЕСПУБЛИКАНСКИХ/МЕСТНЫХ БЮДЖЕТОВ?");
		constraints.gridx = 0;
		constraints.gridy = 9;
		panel.add(jlab10_0,constraints);
	    
	   
		final JComboBox<String> jcmbb10_1 = new JComboBox<String>(yesNo);
		jcmbb10_1.setSelectedItem(null);
		jcmbb10_1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
				{
					if (jcmbb10_1.getSelectedItem().equals("Да")){K2_budget = 0.5;};
					if (jcmbb10_1.getSelectedItem().equals("Нет")){K2_budget = 1;};
					
				}
		}); 
		    
		constraints.gridx = 1;
		constraints.gridy = 9;
		constraints.gridwidth = 3; // 3 колонки шириной
		panel.add(jcmbb10_1,constraints);
		constraints.gridwidth = 1;
		    
		   
			JLabel jlab11_0 = new JLabel("ДЕРЕВЬЯ ПРЕПЯТСТВУЮТ ЭКСПЛУАТАЦИИ ИНЖЕНЕРНЫХ СЕТЕЙ?");
			constraints.gridx = 0;
			constraints.gridy = 10;
			panel.add(jlab11_0,constraints);
		    
			final JComboBox<String> jcmbb11_1 = new JComboBox<String>(yesNo);
			jcmbb11_1.setSelectedItem(null);
			jcmbb11_1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					if (jcmbb11_1.getSelectedItem().equals("Да")){K3_seti = 0.5;};
					if (jcmbb11_1.getSelectedItem().equals("Нет")){K3_seti = 1;};
					
				}
			});
		    
		    constraints.gridx = 1;
		    constraints.gridy = 10;
		    constraints.gridwidth = 3; // 3 колонки шириной
		    panel.add(jcmbb11_1,constraints);
		    constraints.gridwidth = 1;
			
		    JLabel jlab12_0 = new JLabel("ДЕРЕВЬЯ ПРОИЗРАСТАЮТ ЗА ГРАНИЦАМИ НАСЕЛЕННОГО ПУНКТА?");
			constraints.gridx = 0;
			constraints.gridy = 11;
			panel.add(jlab12_0,constraints);
		    
		    
			final JComboBox<String> jcmbb12_1 = new JComboBox<String>(yesNo);
			jcmbb12_1.setSelectedItem(null);
			jcmbb12_1.addActionListener(new ActionListener()
			{
				public void actionPerformed(ActionEvent ae)
				{
					if (jcmbb12_1.getSelectedItem().equals("Да")){K4_naselennyiPunt = 0.1;};
					if (jcmbb12_1.getSelectedItem().equals("Нет")){K4_naselennyiPunt = 1;};
					
				}
			});
		    
		    constraints.gridx = 1;
		    constraints.gridy = 11;
		    constraints.gridwidth = 3; // 3 колонки шириной
		    panel.add(jcmbb12_1,constraints);
		    constraints.gridwidth = 1;
		    
  
	    
		JButton jbtAlpha = new JButton("PUSH THE BUTTON ");
		jbtAlpha.setBackground(Color.GREEN);
		final JLabel jlabAlpha = new JLabel(" ");
		panel.add(jlabAlpha);
		jbtAlpha.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			{
			if (jcmbb12_1.getSelectedItem()==null ||jcmbb11_1.getSelectedItem()==null 
			|| jcmbb10_1.getSelectedItem() == null ||jcmbb91.getSelectedItem()==null 
			|| jcmbb81.getSelectedItem() == null || 
			
			(numberOfTreesHvoinoe_hor==0 && numberOfTreesHvoinoe_udovl ==0 && numberOfTreesHvoinoe_ploh ==0 && numberOfTreesHvoinoe_nenadl==0
			&& numberOfTreesMedlennoeListvennoe_hor==0 && numberOfTreesMedlennoeListvennoe_udovl ==0 && numberOfTreesMedlennoeListvennoe_ploh ==0 && numberOfTreesMedlennoeListvennoe_nenadl==0
			&&numberOfTreesBystroListvennoe_hor==0 && numberOfTreesBystroListvennoe_udovl==0 && numberOfTreesBystroListvennoe_ploh==0 && numberOfTreesBystroListvennoe_nenadl==0
			&&numberOfTreesMalocennoe_hor==0 && numberOfTreesMalocennoe_udovl==0 && numberOfTreesMalocennoe_ploh==0 && numberOfTreesMalocennoe_nenadl==0
			&&numberOfOther_hor==0 && numberOfOther_udovl==0 && numberOfOther_ploh==0 && numberOfOther_nenadl==0		
			&&numberOfPorosol_hor==0 && numberOfPorosol_udovl==0 && numberOfPorosol_ploh==0 && numberOfPorosol_nenadl==0)) 
				
				{
				JOptionPane.showMessageDialog(null,"Вы не ввели все требуемые значения","Red Alert",JOptionPane.PLAIN_MESSAGE);		
				} 
							else {
								
								try {
									writefile();
								} catch (IOException e1) {
									e1.printStackTrace();
								}
								
														
								 }  
			}

	
		});
		constraints.gridx = 1;
	    constraints.gridy = 12;
	    constraints.gridwidth = 3;
		panel.add(jbtAlpha,constraints);
		
		/////////////////////////////////////ВТОРАЯ ЗАКЛАДКА НАЧАЛО////////////////////////////////////////////////////////////////////
		
		GridBagConstraints constraints$ = new GridBagConstraints();
		constraints$.insets = new Insets (1,1,1,1); // расстояние между элементами
	     
	    Tree theTree = new Tree ();
	    
	    //Порода
	    
	    final JComboBox<String> jcmbb$_01 = new JComboBox<String>(massWhatTreesCompensate_$);
	    treeName(jcmbb$_01, theTree);
	    constraints$.gridx = 0;
	    constraints$.gridy = 0;
	    constraints$.gridwidth = 2; // 2 колонки шириной
	    constraints$.ipady = 6; // ширина
	    panelSecond.add(jcmbb$_01,constraints$);
	    

	    ///Состояние
	    final JComboBox<String> jcmbb$_02 = new JComboBox<String>(state);
	    treeState(jcmbb$_02,theTree);
	    constraints$.gridx = 3;
	    constraints$.gridy = 0;
	    constraints$.gridwidth = 1; // 1 колонки шириной
	    constraints$.ipady = 6; // ширина
	    panelSecond.add(jcmbb$_02,constraints$);
	    
	    
	    //Диаметр
	    JTextField jtf$_002 = new JTextField();
	    jtf$_002.setToolTipText("Диаметр(см) или площадь поросли(м2)");
	    constraints$.gridx = 4;
	    constraints$.gridy = 0;
	    constraints$.gridwidth = 1; // 2 колонки шириной
	    constraints$.ipady = 12;// ширина
	    Kolichestvo$(jtf$_002, theTree);
	    panelSecond.add(jtf$_002,constraints$);
	    
	    
	    //Коэффициенты
	    
	    final JCheckBox checkBox1 =  new  JCheckBox ( "K1" ); 
	    checkBox1.setToolTipText("Удаление объектов растительного мира, в отношении которых установлены ограничения или запреты"); //Подсказка при наведенеии курсора
	    Koefficient (checkBox1, theTree, 1);
	    constraints$.gridx = 5;
	    constraints$.gridy = 0;
	    constraints$.gridwidth = 1; // 1 колонки шириной
	    panelSecond.add(checkBox1,constraints$);
	    
	    final JCheckBox checkBox2 =  new  JCheckBox ( "K2" );
	    checkBox2.setToolTipText("Удаление объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов"); //Подсказка при наведенеии курсора
	    Koefficient (checkBox2, theTree, 2);
	    constraints$.gridx = 6;
	    constraints$.gridy = 0;
	    panelSecond.add(checkBox2,constraints$);
	    
	    final JCheckBox checkBox3 =  new  JCheckBox ( "K3" );
	    checkBox3.setToolTipText("Удаление объектов растительного мира, препятствующих эксплуатации инженерных сетей"); //Подсказка при наведенеии курсора
	    Koefficient (checkBox3, theTree, 3);
	    constraints$.gridx = 7;
	    constraints$.gridy = 0;
	    panelSecond.add(checkBox3,constraints$);
	    
	    final JCheckBox checkBox4 =  new  JCheckBox ( "K4" );
	    checkBox4.setToolTipText("Удаление объектов растительного мира, произрастающих за границами населенных пунктов"); //Подсказка при наведенеии курсора
	    Koefficient (checkBox4, theTree, 4);
	    constraints$.gridx = 8;
	    constraints$.gridy = 0;
	    panelSecond.add(checkBox4,constraints$);
	    
	    arrayTree.add(theTree);
	    
	    JButton jbtGamma = new JButton("PUSH THE BUTTON ");
	    jbtGamma.setBackground(Color.GREEN);
		final JLabel jlabBeta = new JLabel(" ");
		panelSecond.add(jlabBeta);
		jbtGamma.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			
			{

			zeroCheck(arrayTree);
			try {
				writefile_$();
			} catch (FileNotFoundException e1) {
				System.out.println("нет этого файла");
				e1.printStackTrace();
			}
			
			}

		});
		
		constraints$.gridx = 1;
		constraints$.gridy = 1;
		constraints$.gridwidth = 1;// 1 колонки шириной
		panelSecond.add(jbtGamma,constraints$);
	    
	    
		//Добавление деревье +
	   
	    Icon icon = new ImageIcon("C:/Users/k801_1/workspace1/compensation/src/+.png");
	    JButton jbtBetta = new JButton(icon);
	    JPanel jbtBettaPanel = new JPanel();
	    jbtBettaPanel.add(jbtBetta);

		//final JLabel jlabBetta = new JLabel(" ");
		constraints$.gridx = 8;
		constraints$.gridy = 1;
		constraints$.gridwidth = 1; // 1 колонки шириной
	    panelSecond.add(jbtBettaPanel,constraints$);
		
	    jbtBetta.addActionListener(new ActionListener()
		{
		public void actionPerformed(ActionEvent e) 
			{
			
			Tree theTreePlus = new Tree ();
			final JComboBox<String> jcmbb$_01 = new JComboBox<String>(massWhatTreesCompensate_$);
			treeName(jcmbb$_01, theTreePlus);
			
		    constraints$.gridy +=2;
		    panelSecond.add(jbtBettaPanel,constraints$);
		    
		    constraints$.gridx = 1;
		    constraints$.gridwidth = 2;// ширина
		    panelSecond.add(jbtGamma,constraints$);

		    constraints$.gridy -=1;
		    constraints$.gridwidth = 2; // 2 колонки шириной
		    constraints$.ipady = 6; // ширина
		    panelSecond.add(jcmbb$_01,constraints$);
		     
		    final JComboBox<String> jcmbb$_02 = new JComboBox<String>(state);
		    treeState(jcmbb$_02, theTreePlus);
		    
		    constraints$.gridx +=2;
		    constraints$.gridwidth = 1; // 1 колонки шириной
		    constraints$.ipady = 6; // ширина
		    panelSecond.add(jcmbb$_02,constraints$);
		    
		    JTextField jtf$_02 = new JTextField();
		    jtf$_02.setToolTipText("Диаметр(см) или площадь поросли(м2)");
		    constraints$.gridx +=1;
		    constraints$.gridwidth = 1; // 1 колонки шириной
		    constraints$.ipady = 12; // ширина
		    panelSecond.add(jtf$_02,constraints$);
		    
		    Kolichestvo$(jtf$_02,theTreePlus);
		    arrayTree.add(theTreePlus);
		    
		    final JCheckBox checkBox1 =  new  JCheckBox ( "K1" );
		    checkBox1.setToolTipText("Удаление объектов растительного мира, в отношении которых установлены ограничения или запреты"); //Подсказка при наведенеии курсора
		    Koefficient (checkBox1, theTreePlus, 1);
		    constraints$.gridx +=1;
		    constraints$.gridwidth = 1; // 1 колонки шириной
		    panelSecond.add(checkBox1,constraints$);
		    
		    final JCheckBox checkBox2 =  new  JCheckBox ( "K2" );
		    checkBox2.setToolTipText("Удаление объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов"); //Подсказка при наведенеии курсора
		    Koefficient (checkBox2, theTreePlus, 2);
		    constraints$.gridx +=1;
		    panelSecond.add(checkBox2,constraints$);
		    
		    final JCheckBox checkBox3 =  new  JCheckBox ( "K3" );
		    checkBox3.setToolTipText("Удаление объектов растительного мира, препятствующих эксплуатации инженерных сетей"); //Подсказка при наведенеии курсора
		    Koefficient (checkBox3, theTreePlus, 3);
		    constraints$.gridx +=1;
		    panelSecond.add(checkBox3,constraints$);
		    
		    final JCheckBox checkBox4 =  new  JCheckBox ( "K4" );
		    checkBox4.setToolTipText("Удаление объектов растительного мира, произрастающих за границами населенных пунктов"); //Подсказка при наведенеии курсора
		    Koefficient (checkBox4, theTreePlus, 4);
		    constraints$.gridx +=1;
		    panelSecond.add(checkBox4,constraints$);
		    
		    jfrm.repaint();
			}

		});
	 
	    /////////////////////////////////////ВТОРАЯ ЗАКЛАДКА ОКОНЧАНИЕ////////////////////////////////////////////////////////////////////
		
		//jfrm.pack();
		jfrm.setVisible(true);
	}
	
	
	public void zeroCheck(ArrayList<Tree> arrayTree) {
		
		for (Tree tree : arrayTree) {
			
			if ( (tree.treeName == null && (tree.state != null || tree.diameter > 0))  
					
					|| (tree.state == null && (tree.treeName != null || tree.diameter > 0))
					
					|| (tree.diameter == 0 && (tree.treeName != null || tree.state != null))
					
					|| tree.treeName == null
					
					) 
			
			
			{
				
				JOptionPane.showMessageDialog(null,"Вы не ввели все требуемые значения","Red Alert",JOptionPane.PLAIN_MESSAGE);
				
			}
			
			else countingMoneyForOneTree (arrayTree);
		}
		
	}
	
	
	public void countingMoneyForOneTree (ArrayList<Tree> arrayTree){
			
		for (Tree tree : arrayTree) {
			
		
		if ( tree.treeName.equals("Хвойной породы")  ) {
			
					if ( tree.diameter>= 1 &&  tree.diameter<=4 ) 
					{
						tree.S = 2;	
					}
			
					if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) 
					{
						tree.S = 5;	
					}
					
					if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) 
					{
						tree.S = 7;	
					}
					
					if ( tree.diameter>= 12.1 &&  tree.diameter<=16 ) 
					{
						tree.S = 10;	
					}
					
					if ( tree.diameter>= 16.1 &&  tree.diameter<=20 ) 
					{
						tree.S = 12;	
					}
					
					if ( tree.diameter>= 20.1 &&  tree.diameter<=30 ) 
					{
						tree.S = 13;	
					}
					
					if ( tree.diameter>30) 
					{
						tree.S = 15;	
					}
			
		}
		
		else if ( tree.treeName.equals("Медленнорастущей лиственной породы") ) {
					
					if ( tree.diameter>= 1 &&  tree.diameter<=4 ) 
					{
						tree.S = 2;	
					}
			
					if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) 
					{
						tree.S = 5;	
					}
					
					if ( tree.diameter>= 8.1 &&  tree.diameter<=10 ) 
					{
						tree.S = 7;	
					}
					
					if ( tree.diameter>= 10.1 &&  tree.diameter<=14 ) 
					{
						tree.S = 10;	
					}
					
					if ( tree.diameter>= 14.1 &&  tree.diameter<=16 ) 
					{
						tree.S = 12;	
					}
					
					if ( tree.diameter>= 16.1 &&  tree.diameter<=22 ) 
					{
						tree.S = 13.5;	
					}
					
					if ( tree.diameter>= 22.1 &&  tree.diameter<=28 ) 
					{
						tree.S = 15;	
					}
			
					if ( tree.diameter>28) 
					{
						tree.S = 17;	
					}
		}
		
		else if ( tree.treeName.equals("Быстрорастущие лиственной породы") ) {
			
					if ( tree.diameter>= 1 &&  tree.diameter<=4 ) 
					{
						tree.S = 1;	
					}
			
					if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) 
					{
						tree.S = 3;	
					}
					
					if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) 
					{
						tree.S = 5;	
					}
					
					if ( tree.diameter>= 12.1 &&  tree.diameter<=15 ) 
					{
						tree.S = 7;	
					}
					
					if ( tree.diameter>= 15.1 &&  tree.diameter<=18 ) 
					{
						tree.S = 10;	
					}
					
					if ( tree.diameter>= 18.1 &&  tree.diameter<=23 ) 
					{
						tree.S = 12;	
					}
					
					if ( tree.diameter>23) 
					{
						tree.S = 14;	
					}
			
			
		}
		
		else if ( tree.treeName.equals("Малоценное лиственное") ) {
			
					if ( tree.diameter>= 4.1 &&  tree.diameter<=8 ) 
					{
						tree.S = 0.3;	
					}
					
					if ( tree.diameter>= 8.1 &&  tree.diameter<=12 ) 
					{
						tree.S = 1;	
					}
					
					if ( tree.diameter>= 12.1 &&  tree.diameter<=15 ) 
					{
						tree.S = 2;	
					}
					
					if ( tree.diameter>15) 
					{
						tree.S = 5;	
					}
			
		}
		
		else if ( tree.treeName.equals("Высаженное в целях озеленения: хвойное <1см.") || tree.treeName.equals("Высаженное в целях озеленения: медленнораст. листв. <1см.") ) {
			
			tree.S = 2;	
		}
		
		else if ( tree.treeName.equals("Высаженное в целях озеленения: быстрораст. листв. <1см.") ) {
			
			tree.S = 1;	
		}
		
		else if ( tree.treeName.equals("Высаженное в целях озеленения: малоцен. листв. <4см.") ) {
			
			tree.S = 0.3;
		}
		
		else if ( tree.treeName.equals("Участок поросли(самосева),др. с диаметром <1(4) см") ) {
			
			tree.S = 0.2;
		}
		
		}
		
		
	}
	
	public void writefile_$() throws FileNotFoundException{ 
		
		
		for ( Tree tree : arrayTree) {
	    	
	    	//System.out.println(tree.treeName + "; Диаметр - " + tree.diameter + "; Состояние - " + tree.state + "; К1 - " + tree.K1 + "; К2 - " +  tree.K2 + "; К3 - " + tree.K3 + "; К4 - " + tree.K4 + "; Kstate- " + tree.Kstate + "; Стоимость- " + tree.S);
	    	
		}

		File file = new File ("D:\\DOWNLOADS", "Расчет компенсации деревьев(деньгами).doc");
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
		
		/*for ( Tree tree : arrayTree) {
	    	
	    	System.out.println(tree.treeName + "; Диаметр - " + tree.diameter + "; Состояние - " + tree.state + "; К1 - " + tree.K1 + "; К2 - " +  tree.K2 + "; К3 - " + tree.K3 + "; К4 - " + tree.K4 + "; Kstate- " + tree.Kstate + "; Стоимость- " + tree.S);
	    	
		}*/
		
		
		/*if (Hvoinoe != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); Hvoinoe.append(" = ");};
		if (Medlenno != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); Medlenno.append(" = ");};
		if (Bystro != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); Bystro.append(" = ");};
		if (Malocennoe != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); Malocennoe.append(" = ");};
		if (VysagennoeHvoinoe != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); VysagennoeHvoinoe.append(" = ");};
		if (VysagennoeMedlenno != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); VysagennoeMedlenno.append(" = ");};
		if (VysagennoeBystro != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); VysagennoeBystro.append(" = ");};
		if (VysagennoeMalocennoe != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); VysagennoeMalocennoe.append(" = ");};
		if (Porosol != null) {Hvoinoe.delete(Hvoinoe.length()-3,Hvoinoe.length()-1); Porosol.append(" = ");};*/
		
		
		/*for ( Tree tree : arrayTree) {
			double V = tree.S * tree.K1 * tree.K2 * tree.K3 * tree.K4 * tree.Kstate;
			pw.print(String.format("%.0f",V) + " + "); 
			Vobj_$ += tree.S * tree.K1 * tree.K2 * tree.K3 * tree.K4 * tree.Kstate;
		}*/
		//pw.print(" = " + String.format("%.0f",Vobj_$)); // округление до целого
		//pw.print(Hvoinoe);
		pw.close();
	}
	
	public void arrayDecomposition(ArrayList <Tree> arrayTree){
		
		for ( Tree tree : arrayTree) {
			
			if (tree.treeName.equals("Хвойной породы")) 
			{Hvoinoe.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Медленнорастущей лиственной породы")) 
			{Medlenno.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Быстрорастущие лиственной породы")) 
			{Bystro.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Малоценное лиственное")) 
			{Malocennoe.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Высаженное в целях озеленения: хвойное <1см.")) 
			{VysagennoeHvoinoe.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Высаженное в целях озеленения: медленнораст. листв. <1см.")) 
			{VysagennoeMedlenno.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Высаженное в целях озеленения: быстрораст. листв. <1см.")) 
			{VysagennoeBystro.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else if (tree.treeName.equals("Высаженное в целях озеленения: малоцен. листв. <4см.")) 
			{VysagennoeMalocennoe.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
			else  
			{Porosol.append(tree.S + " * " + tree.K1 +  " * " + tree.K2 + " * " + tree.K3 +" * " + tree.K4 + " * " + tree.Kstate + " + ");}
									
		}
		
		if(Hvoinoe.equals("")) {removePlusSign(Hvoinoe);} //??
		else if(Medlenno.equals("")) {removePlusSign(Medlenno);}
		else if(Bystro.equals("")) {removePlusSign(Bystro);}
		else if(Malocennoe.equals("")) {removePlusSign(Malocennoe);}
		else if(VysagennoeHvoinoe.equals("")) {removePlusSign(VysagennoeHvoinoe);}
		else if(VysagennoeMedlenno.equals("")) {removePlusSign(VysagennoeMedlenno);}
		else if(VysagennoeBystro.equals("")) {removePlusSign(VysagennoeBystro);}
		else if(VysagennoeMalocennoe.equals("")) {removePlusSign(VysagennoeMalocennoe);}
		else if(Porosol.equals("")) {removePlusSign(Porosol);};
		

	};
	
	public void removePlusSign(StringBuffer strB){
		
		strB.delete(strB.length()-3,strB.length()-1); strB.append(" = ");
		System.out.println(strB);
		
	};
	
	
	public void writefile() throws IOException{
		
		File file = new File ("D:\\DOWNLOADS", "Расчет компенсации деревьев(зелеными насаждениями).doc");
		PrintWriter pw = new PrintWriter(file);
		pw.println("");
		pw.print("			РАСЧЕТ КОМПЕНСАЦИИ ДЕРЕВЬЕВ ");
		pw.println("");
		pw.println("");
		pw.println("Расчет выполняется в соответствии с Постановлением Совета Министров от 08.05.2013 г. № 354 «Положение о порядке определения условий проведения");
		pw.println("компенсационных посадок либо осуществления компенсационных выплат стоимости удаляемых, пересаживаемых объектов растительного мира» (Приложение 4).");
		pw.println("Размер компенсационных посадок рассчитывается по следующей формуле:");
		pw.println("  V = S * N * K1 * K2 * K3 * K4, ");
		pw.println("где V - количество компенсационных посадок;");
		pw.println("S - количество вырубаемых деревьев, шт.;");
		pw.println("N - количества объектов растительного мира, высаживаемых взамен каждого удаляемого объекта растительного мира, согласно Приложения 4, шт.;");
		pw.println("К1 - коэффициент, равный 2, применяемый в случаях удаления объектов растительного мира, в отношении которых установлены ограничения или запреты и (или) расположенных в границах природных территорий, подлежащих особой и (или) специальной охране;");
		pw.println("К2 - коэффициент, равный 0,5, применяемый в случаях удаления объектов растительного мира при строительстве, финансирование которого осуществляется за счет средств республиканского, местных бюджетов без привлечения иных источников финансирования (при наличии письменного подтверждения заказчика о всех источниках финансирования такого строительства) и (или) в интересах и на территории бюджетной организации;");
		pw.println("К3 - коэффициент, равный 0,5, применяемый в случаях удаления объектов растительного мира, препятствующих эксплуатации инженерных сетей, осуществляемого на основании разрешения на удаление, решения об изменении или снятии установленных ограничений или запретов;");
		pw.println("К4 - коэффициент, равный 0,1, применяемый в случаях удаления объектов растительного мира, произрастающих за границами населенных пунктов.");
		pw.println("К5 - коэффициент качественного состояния вырубаемых объектов растительного мира,");
		pw.println("-для удаляемых деревьев, находящихся в хорошем качественном состоянии, – коэффициент, равный 1;");
		pw.println("-для удаляемых деревьев, находящихся в удовлетворительном качественном состоянии, – коэффициент, равный 0,75;");
		pw.println("-для удаляемых деревьев, находящихся в плохом качественном состоянии, – коэффициент, равный 0,5;");
		pw.println("-для удаляемых деревьев, находящихся в ненадлежащем качественном состоянии, – коэффициент, равный 0,25;");
		
		
		if (numberOfTreesHvoinoe_hor!=0 || numberOfTreesHvoinoe_udovl!=0 || numberOfTreesHvoinoe_ploh!=0 || numberOfTreesHvoinoe_nenadl!=0){ 
		pw.println("");
		pw.println("Для компенсации сноса хвойных деревьев, необходима посадка: ");
		pw.println("");}
		
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesHvoinoe_hor,3,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesHvoinoe_udovl,3,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesHvoinoe_ploh,3,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesHvoinoe_nenadl,3,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);
			
			pw.println("");
			pw.println(" Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesHvoinoe_hor,6,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesHvoinoe_udovl,6,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesHvoinoe_ploh,6,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesHvoinoe_nenadl,6,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);
			
			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (numberOfTreesMedlennoeListvennoe_hor!=0 || numberOfTreesMedlennoeListvennoe_udovl!=0 || numberOfTreesMedlennoeListvennoe_ploh!=0 || numberOfTreesMedlennoeListvennoe_nenadl!=0){ 
		pw.println("");
		pw.println("Для компенсации сноса лиственных медленнорастущих деревьев, необходима посадка: ");
		pw.println("");}
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_hor,3,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_udovl,3,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_ploh,3,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_nenadl,3,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_hor,6,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_udovl,6,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_ploh,6,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesMedlennoeListvennoe_nenadl,6,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
	
		if (numberOfTreesBystroListvennoe_hor!=0 || numberOfTreesBystroListvennoe_udovl!=0 || numberOfTreesBystroListvennoe_ploh!=0 || numberOfTreesBystroListvennoe_nenadl!=0){
		pw.println("");
		pw.println("Для компенсации сноса лиственных быстрорастущих деревьев, необходима посадка: ");
		pw.println("");}
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesBystroListvennoe_hor,2,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_udovl,2,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_ploh,2,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_nenadl,2,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesBystroListvennoe_hor,3,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_udovl,3,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_ploh,3,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesBystroListvennoe_nenadl,3,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (numberOfTreesMalocennoe_hor!=0 || numberOfTreesMalocennoe_udovl!=0 || numberOfTreesMalocennoe_ploh!=0 || numberOfTreesMalocennoe_nenadl!=0){
		pw.println("");
		pw.println("Для компенсации сноса  деревьев малоценной породы, необходима посадка: ");
		pw.println("");}
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesMalocennoe_hor,1,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesMalocennoe_udovl,1,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesMalocennoe_ploh,1,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesMalocennoe_nenadl,1,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfTreesMalocennoe_hor,2,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfTreesMalocennoe_udovl,2,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfTreesMalocennoe_ploh,2,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfTreesMalocennoe_nenadl,2,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (numberOfOther_hor!=0 || numberOfOther_udovl!=0 || numberOfOther_ploh!=0 || numberOfOther_nenadl!=0){
		pw.println("");
		pw.println("Для компенсации сноса  хвойных, лиственных медленно-/быстрорастущих(диаметром <1 см.), малоценной породы (диаметром < 4см.), необходима посадка: ");
		pw.println("");}
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfOther_hor,1,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfOther_udovl,1,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfOther_ploh,1,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfOther_nenadl,1,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfOther_hor,2,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfOther_udovl,2,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfOther_ploh,2,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfOther_nenadl,2,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (numberOfPorosol_hor!=0 || numberOfPorosol_udovl!=0 || numberOfPorosol_ploh!=0 || numberOfPorosol_nenadl!=0){
		pw.println("");
		pw.println("Для компенсации сноса  поросли/самосева, необходима посадка: ");
		pw.println("");}
		
		if (ThanTree == 1 || ThanTree == 2){
			Vobj = 0;
			Method_XSeven(pw,numberOfPorosol_hor,1,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfPorosol_udovl,1,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfPorosol_ploh,1,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfPorosol_nenadl,1,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		if (ThanTree == 3){
			Vobj = 0;
			Method_XSeven(pw,numberOfPorosol_hor,2,"(хорошее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_hor);
			Method_XSeven(pw,numberOfPorosol_udovl,2,"(удовлетворительное состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_udovl);
			Method_XSeven(pw,numberOfPorosol_ploh,2,"(плохое состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_ploh);
			Method_XSeven(pw,numberOfPorosol_nenadl,2,"(ненадлежащее состояние)", K1_ogranichenia, K2_budget, K3_seti, K4_naselennyiPunt, K5_sostoianie_nenadl);

			pw.println("");
			pw.println("Vобщее = " + Math.round(Vobj) + ";");
		};
		
		

		pw.close();
		
		
	   
	}
	
	public void Method_XSeven (PrintWriter pw, double numberOfTrees, int N, String sostoianie, int K1_ogranichenia, double K2_budget, double K3_seti, double K4_naselennyiPunt, double K5_sostoianie_hor) {
		
		if(numberOfTrees>0) {
		
		pw.println("");
		pw.println(" V = " +  numberOfTrees + " * " +  N  + " * " + K1_ogranichenia + " * " + K2_budget + " * " + K3_seti + " * " + K4_naselennyiPunt + " * " + K5_sostoianie_hor + 
				" = " + df.format(numberOfTrees*N*K1_ogranichenia*K2_budget*K3_seti*K4_naselennyiPunt*K5_sostoianie_hor) + sostoianie + ";");
		}
		Vobj += numberOfTrees*N*K1_ogranichenia*K2_budget*K3_seti*K4_naselennyiPunt*K5_sostoianie_hor;
	}
	
	public static boolean itContainsInregerNumbers(String s, String s1)
	{
       return s.matches(s1);
	}
	
	
	public static void main(String[] args) {
		
		Compensation_v_2_0 firstCompensation = new Compensation_v_2_0();
		
		firstCompensation.go();

		
	}
	
				public void Kolichestvo (JTextField jtf, int i) {
				    	
				    	
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
									else {
										
										if ((i==21|| i==22|| i==23|| i==24) && str1 != null  && itContainsInregerNumbers(str1, "[0-9]{0,3}[.]?[0-9]{0,2}")){jtf.setBackground(null); {
											
											if(i==21) {numberOfPorosol_hor =  Double.parseDouble(str1);jtf.setText(str1);}
											if(i==22) {numberOfPorosol_udovl = Double.parseDouble(str1); jtf.setText(str1);}
											if(i==23) {numberOfPorosol_ploh =  Double.parseDouble(str1); jtf.setText(str1);}
											if(i==24) {numberOfPorosol_nenadl = Double.parseDouble(str1); jtf.setText(str1);}
																																														  }
										}
										
										else if (str1 != null  && itContainsInregerNumbers(str1, "[0-9]?[0-9]?")){jtf.setBackground(null);
											if(i==1) {numberOfTreesHvoinoe_hor =  Integer.parseInt(str1); jtf.setText(str1);}
											if(i==2) {numberOfTreesHvoinoe_udovl = Integer.parseInt(str1);jtf.setText(str1);}
											if(i==3) {numberOfTreesHvoinoe_ploh =  Integer.parseInt(str1); jtf.setText(str1);}
											if(i==4) {numberOfTreesHvoinoe_nenadl =  Integer.parseInt(str1);jtf.setText(str1);}
											
											if(i==5) {numberOfTreesMedlennoeListvennoe_hor =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==6) {numberOfTreesMedlennoeListvennoe_udovl = Integer.parseInt(str1);jtf.setText(str1);}
											if(i==7) {numberOfTreesMedlennoeListvennoe_ploh =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==8) {numberOfTreesMedlennoeListvennoe_nenadl =  Integer.parseInt(str1);jtf.setText(str1);}
											
											if(i==9) {numberOfTreesBystroListvennoe_hor =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==10) {numberOfTreesBystroListvennoe_udovl = Integer.parseInt(str1);jtf.setText(str1);}
											if(i==11) {numberOfTreesBystroListvennoe_ploh =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==12) {numberOfTreesBystroListvennoe_nenadl =  Integer.parseInt(str1);jtf.setText(str1);}
											
											if(i==13) {numberOfTreesMalocennoe_hor =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==14) {numberOfTreesMalocennoe_udovl = Integer.parseInt(str1);jtf.setText(str1);}
											if(i==15) {numberOfTreesMalocennoe_ploh =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==16) {numberOfTreesMalocennoe_nenadl =  Integer.parseInt(str1);jtf.setText(str1);}
											
											if(i==17) {numberOfOther_hor =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==18) {numberOfOther_udovl = Integer.parseInt(str1);jtf.setText(str1);}
											if(i==19) {numberOfOther_ploh =  Integer.parseInt(str1);jtf.setText(str1);}
											if(i==20) {numberOfOther_nenadl =  Integer.parseInt(str1);jtf.setText(str1);}
										}
				
										
									else {jtf.setBackground(Color.RED);jtf.setText(" ");}
										 }
								}		
							});
						
						
				    };
    
			    public void Kolichestvo$(JTextField jtf, Tree tree) {
			
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
			    
			    public void treeName(JComboBox<String> jcmbb$_01, Tree tree) {
			    	
			    	jcmbb$_01.setSelectedItem(null);
				    jcmbb$_01.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent ae)
						{
							if (jcmbb$_01.getSelectedItem().equals("Хвойной породы")){tree.treeName = "Хвойной породы";};
							if (jcmbb$_01.getSelectedItem().equals("Медленнорастущей лиственной породы")){tree.treeName = "Медленнорастущей лиственной породы";};
							if (jcmbb$_01.getSelectedItem().equals("Быстрорастущие лиственной породы")){tree.treeName = "Быстрорастущие лиственной породы";};
							if (jcmbb$_01.getSelectedItem().equals("Малоценное лиственное")){tree.treeName = "Малоценное лиственное";};
							if (jcmbb$_01.getSelectedItem().equals("Высаженное в целях озеленения: хвойное <1см.")){tree.treeName = "Высаженное в целях озеленения: хвойное <1см.";};
							if (jcmbb$_01.getSelectedItem().equals("Высаженное в целях озеленения: медленнораст. листв. <1см.")){tree.treeName = "Высаженное в целях озеленения: медленнораст. листв. <1см.";};
							if (jcmbb$_01.getSelectedItem().equals("Высаженное в целях озеленения: быстрораст. листв. <1см.")){tree.treeName = "Высаженное в целях озеленения: быстрораст. листв. <1см.";};
							if (jcmbb$_01.getSelectedItem().equals("Высаженное в целях озеленения: малоцен. листв. <4см.")){tree.treeName = "Высаженное в целях озеленения: малоцен. листв. <4см.";};
							if (jcmbb$_01.getSelectedItem().equals("Участок поросли(самосева),др. с диаметром <1(4) см")){tree.treeName = "Участок поросли(самосева),др. с диаметром <1(4) см";};
							
						}
					});
			    	
			    }
	
			    public void treeState(JComboBox<String> jcmbb$_02, Tree tree) {

				    jcmbb$_02.setSelectedItem(null);
				    jcmbb$_02.addActionListener(new ActionListener()
					{
						public void actionPerformed(ActionEvent ae)
						{
							if (jcmbb$_02.getSelectedItem().equals("Хорошее")){tree.state = "Хорошее"; tree.Kstate = 1; };
							if (jcmbb$_02.getSelectedItem().equals("Удовлетв")){tree.state = "Удовлетв"; tree.Kstate = 0.75;};
							if (jcmbb$_02.getSelectedItem().equals("Плохое")){tree.state = "Плохое"; tree.Kstate = 0.5;};
							if (jcmbb$_02.getSelectedItem().equals("Ненадлеж")){tree.state = "Ненадлеж"; tree.Kstate = 0.25;};
							
						}
					});
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
			    
}

