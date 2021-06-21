package compensation;


public class Tree {
	
 @Override
	public String toString() {
		return "Tree [treeName=" + treeName + ", diameter=" + diameter + ", state=" + state + ", K1=" + K1 + ", K2="
				+ K2 + ", K3=" + K3 + ", K4=" + K4 + "]";
	}

String treeName;

 double diameter;

 String state;
 
 double K1 = 1, K2 = 1, K3 = 1, K4 = 1;
 
 double Kstate = 1;
	
 double S;

}
