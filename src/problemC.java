
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;

public class problemC {

	public static void main(String[] args) {
		Kattio io = new Kattio(System.in, System.out);

		int N=io.getInt();
		int M=io.getInt();
		String king = io.getWord();
		int index=0;
		double value=0;
		
		String[] child=new String[N];
		String[] parent1=new String[N];
		String[] parent2=new String[N];
		String[] claimer=new String[M];
		double[] blood = new double[M];
		for (int i=0;i<M;i++){
			blood[i]=0;
		}
		double factor=0.5;

		
		for (int i=0;i<N;i++){
			child[i]=io.getWord();
			parent1[i]=io.getWord();
			parent2[i]=io.getWord();
		}
		for (int j=0;j<M;j++){
			claimer[j]=io.getWord();
		}
//-----------------Main function--------------------------
		for (int j=0;j<M;j++){
			findBloodLine(j,
					claimer[j],
					king,
					blood,
					factor,
					child,
					parent1,
					parent2,
					N);	
		}

		for (int i =0;i<M;i++){
			if (blood[i]>value){
				value=blood[i];
				index=i;
			}
		}
		System.out.println(claimer[index]);
		io.close(); // reading ends
		}
	
	public static double[] findBloodLine(int claimerIndex,
			String claimer,//the claimer i.e claimer[claimerIndex]
			String king,
			double[] blood,
			double factor,
			String[] child,
			String[] parent1,
			String[] parent2,
			int N){
				
				for (int i=0;i<N;i++){
					if (claimer.equals(child[i])){
						if (parent1[i].equals(king)){
							blood[claimerIndex]+=factor;
							break;
						}
						else{
							findBloodLine(claimerIndex,
									parent1[i],// claimer
									king,
									blood,
									factor*0.5,//genes halves per generation
									child,
									parent1,
									parent2,
									N);
							
						}
					}
				}
				
				for (int i=0;i<N;i++){
					if (claimer.equals(child[i])){
						if (parent2[i].equals(king)){
							blood[claimerIndex]+=factor;
							break;
						}
						else{
							findBloodLine(claimerIndex,
									parent2[i],// claimer
									king,
									blood,
									factor*0.5,//genes halves per generation
									child,
									parent1,
									parent2,
									N);
							
						}
					}
				}
				 	
				
				return blood;
			}
				
	}

//-------------Kattio reader---------------------
class Kattio extends PrintWriter {
	public Kattio(InputStream i) {
		super(new BufferedOutputStream(System.out));
		r = new BufferedReader(new InputStreamReader(i));
	}
	public Kattio(InputStream i, OutputStream o) {
		super(new BufferedOutputStream(o));
		r = new BufferedReader(new InputStreamReader(i));
	}

	public boolean hasMoreTokens() {
		return peekToken() != null;
	}

	public int getInt() {
		return Integer.parseInt(nextToken());
	}

	public double getDouble() {
		return Double.parseDouble(nextToken());
	}

	public long getLong() {
		return Long.parseLong(nextToken());
	}

	public String getWord() {
		return nextToken();
	}



	private BufferedReader r;
	private String line;
	private StringTokenizer st;
	private String token;

	private String peekToken() {
		if (token == null)
			try {
				while (st == null || !st.hasMoreTokens()) {
					line = r.readLine();
					if (line == null) return null;
					st = new StringTokenizer(line);
				}
				token = st.nextToken();
			} catch (IOException e) { }
		return token;
	}

	private String nextToken() {
		String ans = peekToken();
		token = null;
		return ans;
	}
}