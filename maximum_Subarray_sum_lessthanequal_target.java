/*
Maximum Subarray sum less than or equal to target
Complexity :- O(N log N)
Implemented By Aman Kotiyal
*/


		int maximum_Subarray_sum_lessthanequal_target(int a[],int k) {
			long ans=Integer.MIN_VALUE;
			TreeSet<Long> tset=new TreeSet<>();
			tset.add(0l);    
			long sum=0;
			for(int i=0;i<a.length;i++) {
				sum+=a[i];
				try {
				long x=tset.higher(sum-k-1);  //gives strictly greater than (sum-k)
				ans=Math.max(ans, sum-x);
				}catch(Exception e) {}
				tset.add(sum);
			}
			return (int)ans;        //returns Integer.MIN_VALUE if not exists
		}
