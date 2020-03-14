import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.io.BufferedReader;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.io.OutputStream;


public class problemB {

	public static void main(String[] args) {

		Kattio io = new Kattio(System.in, System.out);

		double T = io.getInt();
		int R; 
		double P; 
		double D;
		double scalingFactor;


		
		for (int i=0;i<T;i++) {
			double factor=0;
			List<String> ingridient= new ArrayList<>();
			ArrayList<Double> weight = new ArrayList<>();
			ArrayList<Double> bp = new ArrayList<>();
			R = io.getInt();
			P = io.getInt();
			D = io.getInt();
			scalingFactor= P/D;
			for (int j=0; j<R;j++){
				ingridient.add(io.getWord());
				weight.add(io.getDouble());
				bp.add(io.getDouble());
				if (bp.get(j)==100.0){
					factor=D/P*weight.get(j);
				}
			}
			System.out.println("Recipe # "+ (i + 1));
			for (int k=0; k<R;k++){
				System.out.print(ingridient.get(k));
				System.out.print(" ");
				System.out.print(String.format("%.1f",(factor*bp.get(k))/100));
				System.out.print("\n");
			}
			System.out.print("----------------------------------------");
			System.out.print("\n");
			
			
		}
	}
}

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