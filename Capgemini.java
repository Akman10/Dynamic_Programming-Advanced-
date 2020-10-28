/*

Given an integer array 'P',
find two subarray a,b in 'P' such that (a1*bn)+(a2*bn-1)+(a3*bn-2).....(an*b1) is maximum
a and b  are disjoint
Constraints--> N=3000
-1000000<=P[i]<=1000000

example:
Input ->
8
1 9 2 3 0 6 7 8
Output-->
104

Explanation : a={9,2,3} b={6,7,8}   sum=9*8 + 2*7 + 3*6

*/
import java.io.*;
import java.util.*;

class ques1
{
	public static void main(String[] args)throws Exception{ new ques1().run();} 
	long mod=1000000000+7;
	
	void solve() throws Exception
	{
		int N=ni();
		long a[]=new long[N];
		long b[]=new long[N];
		for (int i = 0; i <N; i++) 
			a[i]=nl();
		
		long prod=Long.MIN_VALUE;
		long dp[][]=new long[N][N];
		
		for(int i=0;i<N;i++) {
			for(int j=N-1;j>=0;j--) {
				dp[i][N-1-j]=a[i]*a[j];
			}
		}
		HashMap<Integer,ArrayList<Long>> map= new HashMap<>();
		for(int i=0;i<N;i++){
			for(int j=0;j<N;j++) {
				
				if(i+j==N-1)break;
				
				ArrayList<Long> tem;
				if(map.containsKey(i-j))
					tem=map.get(i-j);
				else
					tem=new ArrayList<Long>();
				
				tem.add(dp[i][j]);
				map.put( i-j,tem );
			}
		}
		long ans=Long.MIN_VALUE;
		for(Integer key:map.keySet()) {
			//subarraysum
			long temo=maxSubArray(map.get(key));
			ans=Math.max(ans,temo);
		}
		out.println(ans);
	}
	 public long maxSubArray(ArrayList<Long> nums) 
	    {
	      long max=Integer.MIN_VALUE;
	      long sum=0;
	        int n=nums.size();
	        for(int i=0;i<n;i++){
	            sum=Math.max( nums.get(i) , sum+nums.get(i));
	            max=Math.max(max,sum);
	        }
	        return max;
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
