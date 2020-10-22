/*
https://www.spoj.com/problems/RAONE/

*/





import java.io.*;
import java.util.*;

class Digit_dp_02
{
	public static void main(String[] args)throws Exception{ new Digit_dp_02().run();} 
	long mod=1000000000+7;
	
	void solve() throws Exception
	{
		for(int ii=ni();ii>0;ii--)
		{
			String s=ns();  long x=Long.parseLong(s);	x--;  s=x+"";  //first number - 1
			int l[]=new int[s.length()];
			for(int i=0;i<s.length();i++)l[i]=s.charAt(i)-'0';
			s=ns();     // second number
			int r[]=new int[s.length()];
			for(int i=0;i<s.length();i++)r[i]=s.charAt(i)-'0';
			
			out.println(get(r)-get(l));
		}
	}
	long get(int a[]) 
	{
		long dp[][][][]=new long[20][2][50][50];
		long ans=0;
		int N=a.length;
		dp[N][1][0][0]=1;dp[N][0][0][0]=1;
		
		for(int i=N-1;i>=0;i--) 
			for(int tight=0;tight<2;tight++)
				for(int even=0;even<50;even++)
					for(int odd=0;odd<50;odd++) {
						
	                    if((N - i) % 2==1) {
	                        if(tight==1) {
	                            for(int d = 0; d <= a[i]; d++) {
	                            	if(odd-d>=0)
	                                dp[i][1][even][odd] += (d == a[i]) ? dp[i+1][1][even][odd-d] : dp[i+1][0][even][odd-d];
	                            }
	                        }
	                        else {
	                            for(int d = 0; d < 10; d++) {
	                            	if(odd-d>=0)
	                                dp[i][0][even][odd] += dp[i+1][0][even][odd-d] ;
	                            }
	                        }
	                    }
	                    else {
	                        if(tight==1) {
	                            for(int d = 0; d <=a[i]; d++) {
	                            	if(even-d>=0)
	                                dp[i][1][even][odd] += (d == a[i]) ? dp[i+1][1][even-d][odd] : dp[i+1][0][even-d][odd] ;
	                            }
	                        }
	                        else {
	                            for(int d = 0; d < 10; d++) {
	                            	if(even-d>=0)
	                                dp[i][0][even][odd] +=  dp[i+1][0][even-d][odd] ;
	                            }
	                        }
	                    }
					
				}//sum
		
		for(int e=0;e<50;e++) {
			for(int o=0;o<50;o++)
				if(e-o==1)
				ans+=dp[0][1][e][o];
		}
		return ans;
	}
	
	/*IMPLEMENTATION BY AMAN KOTIYAL, FAST INPUT OUTPUT & METHODS BELOW*/
	
	private byte[] buf=new byte[1024];
	private int index;
	private InputStream in;
	private int total;
	private SpaceCharFilter filter;
	PrintWriter out;
	
	int gcd(int a, int b) 
	{ 
		if (a == 0) 
			return b; 
		return gcd(b%a, a); 
	} 
	long gcd(long a, long b) 
	{ 
		if (a == 0) 
			return b; 
		return gcd(b%a, a); 
	}
	/* for (1/a)%mod = ( a^(mod-2) )%mod  ----> use expo to calc -->(a^(mod-2)) */
	long expo(long p,long q)  /*  (p^q)%mod   */
	{
		long z = 1;
		while (q>0) {
			if (q%2 == 1) {
				z = (z * p)%mod;
			}
			p = (p*p)%mod;
			q >>= 1;
		}
		return z;
	}
	void run()throws Exception
	{
		in=System.in; out = new PrintWriter(System.out);
		solve();
		out.flush();
	}
	private int scan()throws IOException
	{
		if(total<0)
			throw new InputMismatchException();
		if(index>=total)
		{
			index=0;
			total=in.read(buf);
			if(total<=0)
				return -1;
		}
		return buf[index++];
	}
	private int ni() throws IOException 
	{
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		int sgn = 1;
		if (c == '-') {
			sgn = -1;
			c = scan();
		}
		int res = 0;
		do {
			if (c < '0' || c > '9')
				throw new InputMismatchException();
			res *= 10;
			res += c - '0';
			c = scan();
		} while (!isSpaceChar(c));
		return res * sgn;
	}
	private long nl() throws IOException 
	{
		long num = 0;
		int b;
		boolean minus = false;
		while ((b = scan()) != -1 && !((b >= '0' && b <= '9') || b == '-'))
			;
		if (b == '-') {
			minus = true;
			b = scan();
		}
		
		while (true) {
			if (b >= '0' && b <= '9') {
				num = num * 10 + (b - '0');
			} else {
				return minus ? -num : num;
			}
			b = scan();
		}
	}
	private double nd() throws IOException{
		return Double.parseDouble(ns());
	}
	private String ns() throws IOException {
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		StringBuilder res = new StringBuilder();
		do {
			if (Character.isValidCodePoint(c))
				res.appendCodePoint(c);
			c = scan();
		} while (!isSpaceChar(c));
		return res.toString();
	}
	private String nss() throws IOException
	{
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		return br.readLine();
	}
	private char nc() throws IOException 
	{
		int c = scan();
		while (isSpaceChar(c))
			c = scan();
		return (char) c;
	}
	private boolean isWhiteSpace(int n)
	{
		if(n==' '||n=='\n'||n=='\r'||n=='\t'||n==-1)
			return true;
		return false;
	}
	private boolean isSpaceChar(int c) {
		if (filter != null)
			return filter.isSpaceChar(c);
		return isWhiteSpace(c);
	}
	private interface SpaceCharFilter {
		public boolean isSpaceChar(int ch);
	}
}
