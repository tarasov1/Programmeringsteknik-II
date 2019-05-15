import java.util.Scanner;

public class Recursion { 

		public static String myArgs;  	

		public static void main(String[] args) {  
			myArgs = args[0]; 

		}
		//uppgift 1
		public static double power(double x, int n) {  
			if (n == 0) 
				return 1.0; 
			else 
				return x * power(x, n-1); 
		}

		//uppgift 2
		public static int multiply(int m, int n) {
			if (n == 1)
				return m; 
			else 
				return m + multiply(m, n-1); 
		}

		//uppgift 3
		public static int divide(int t, int n) {
			if (n == t)
				return 1; 
			else 
				return  1 + divide(t, n-t); 	 
		}

		//uppgift 4
		public static double harmonic(int n) {
			if (n == 1)
				return 1.0;
			else 
				return 1.0/n + harmonic(n-1);
		}

		//uppgift 5 
		public static int largest(int[] a, int i) {
			if (i == 0)
				return a[0];
			else
				return Math.max(a[i], largest(a, i-1));
		}

		//uppgift 6 
		public static String reverse(String s) {
			if (s.length() <= 1)
				return s; 
			else 
				return reverse(s.substring(1))+s.charAt(0); 
		} 

		//uppgift 7 
		public static void bricklek(char from, char to, char help, int n) {
			if (n == 1) {
				System.out.println(from + " -> " + to); 
			} else {
				bricklek(from, help, to, n-1); 
				System.out.println(from + " -> " + to);
				bricklek(help, to, from, n-1);   
			} 
		}

		//uppgift 8 
		public static int[] change = {1, 5, 10, 50, 100};
		
		public static int count(int a, int n) { 
			if (a == 0)
				return 1; 
			else if (a < 0 || n == 0)
				return 0; 
			else
				return count(a, n-1) + count(a-change[n-1], n);	 
		}

		//uppgift 9
		public static void printCount(int a) {
			int[] count = new int[change.length];
			printCount(a, change.length, count); 
		}  

		public static void printCount(int a, int n, int[] count) {
			if (a == 0) {
				for (int i = 0; i < count.length; i++) { 
					if (count[i] > 0)
						System.out.print(count[i] + "*" + change[i] + " ");
				}
					System.out.println("\n"); 
			} else if (a > 0 && n > 0) {
				count[n - 1]++; 
				printCount(a - change[n - 1], n, count);
				count[n - 1]--; 
				printCount(a, n - 1, count); 
			}
		}

		//uppgift 10
		public static String reverseInt(int n) {
			if (n < 10) {
				return Integer.toString(n); 
			 } else {
				return n%10 + reverseInt(n/10);
			}

		}

		//uppgift 11 
		public static String reverseNumbers(long x, int b) {
			if (x < b) {
				return Long.toString(x); 
			} else {
				return x%b + reverseNumbers(x/b, b); 
			}
		}

		//uppgift 12
		public static String reverseNumbers(Scanner scan) {
			   if (!scan.hasNext()) {
			      return "";
			   }
			   else {
				  String next = scan.next(); 
			      return reverseNumbers(scan)  + " " + next;
			   }
		} 

		public static String reverseNumbers() {
			if(myArgs.length() != 0) {
				return reverseNumbers(new Scanner(myArgs));
			} else {
				return "No command-line arguments"; 
			}
		}

}