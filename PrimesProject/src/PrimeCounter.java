import java.util.ArrayList;
import java.util.BitSet;

public class PrimeCounter {


	static ArrayList<Integer> listOfPrimes = new ArrayList<Integer>();

	public static void main(String[]args){
		double beginningTime = System.currentTimeMillis();
		final Integer lower =3 ;

		sieveOfEratothenes(100, lower);
		sieveOfEratothenes(1000, lower);
		sieveOfEratothenes(10000, lower);
		sieveOfEratothenes(100000,lower);
		sieveOfEratothenes(1000000, lower);
		sieveOfEratothenes(10000000, lower);

		System.out.println("Please wait about a two minutes for the last two intervals");
		System.out.println("");
		slowSieve(7870000000L, 7879999999L);

		slowSieve(9390000000L, 9399999999L);
		double endingTime = System.currentTimeMillis();
		double deltaTime = 0.001*(endingTime - beginningTime);
		System.out.println("----------------");
		System.out.println("Total time taken since the program started was : " + deltaTime +" seconds.");
		

	}




	public static void sieveOfEratothenes (final Integer MAX, final Integer lower)
	{	 double  startingTime = System.currentTimeMillis();
	Integer counter =0;
	BitSet primes = new BitSet (MAX);
	// Assume the you start counting from 0 such that the index equals the number.
	// make only odd numbers candidates...
	for (int i = lower; i < MAX; i+=2)
	{
		primes.set(i);

	}
	// ... except no. 2
	primes.set (2, true);


	final Integer MAXROOT = (int)Math.sqrt(MAX);
	for (int i = lower; i < MAXROOT+1; i++)
	{
		// 
		if (primes.get (i))
		{

			for (int j = i*i; j < MAX; j += i) {
				primes.set(j,  false);
			}

		}

	}
	counter = primes.cardinality();
	//             for(int x =0; x<MAX ; x++){
	//            	 if (primes.get(x)){
	//            		 counter++;
	//            		
	//            	 }
	//            	 
	//             }
	if(MAX==100000){
		for(int x =0; x<MAX ; x++){
			if(primes.get(x)){
				listOfPrimes.add(x);
			}
		}
	}
	System.out.println("The number of primes between 1 and "+MAX+" is: "+ counter);
	printLastFive (primes);
	double endingTime = System.currentTimeMillis();
	double deltaTime = 0.001*(endingTime - startingTime);
	System.out.println("Estimated Running Time was "+ deltaTime+ " seconds.");

	System.out.println("----------------");

	}  
	public static void slowSieve (long lowerBound, long upperBound){
		double startingTime = System.currentTimeMillis();
		int delta = (int) (upperBound - lowerBound);
		//It is safe to assume that the list of Primes is going to be less than half of the interval
		BitSet bigPrimes = new BitSet(delta);

		//Assume all odds are prime.
		bigPrimes.set(0, delta, true);
		for (int evenIndex=0 ; evenIndex< delta; evenIndex =evenIndex+2 ){
			bigPrimes.set(evenIndex,false);

		}
// Consider only the odd numbers. Why bother analyze the evens if the already have a prime factor 2.
		for(long value = lowerBound+1; value <upperBound; value+=2){

			for (int index = 0; index< listOfPrimes.size();index++){
				if( bigPrimes.get((int)(value-lowerBound)) && value % (long) listOfPrimes.get(index) == 0){
					bigPrimes.set((int)(value -lowerBound),false);

					break;
				}
			}
		}


		System.out.println("The total of Primes between "+ lowerBound +" and "+ upperBound+ " is: "+ (bigPrimes.cardinality()));
		printFirstFive(bigPrimes,lowerBound);
		printLastFive(bigPrimes,lowerBound);
		double endingTime = System.currentTimeMillis();
		double deltaTime = 0.001*(endingTime - startingTime);
		System.out.println("Estimated Running Time was "+ deltaTime+" seconds.");
		System.out.println();
		System.out.println("----------------");

	}
	public static void printLastFive(BitSet primesSet){
		int lastPrimes [] = new int[5];
		int n = primesSet.length();
		for(int i =5; i>0;i--){
// Only true values are primes
			while(!primesSet.get(n-1)){
				n--;
			}
			lastPrimes[i-1] = n-1;
			n--;
		}
		System.out.println("The last five numbers:");
		for(int index =0; index<lastPrimes.length;index++){
			System.out.println(lastPrimes[index]);
		}
	}
	// Overloaded method
	public static void printLastFive(BitSet primesSet, long initial){
		int lastPrimes [] = new int[5];
		int n = primesSet.length();
		for(int i =5; i>0;i--){

			while(!primesSet.get(n-1)){
				n--;
			}
			lastPrimes[i-1] = n-1;
			n--;
		}
		System.out.println("The last five numbers:");
		for(int index =0; index<lastPrimes.length;index++){
			System.out.println(initial + lastPrimes[index]);

		}
	}
	public static void printFirstFive(BitSet primesSet, long initial){
		int counter =0;
		int length = primesSet.length();
		System.out.println("The first five numbers: ");
		for(int index =0; index< length; index++){
			if(primesSet.get(index)&& counter <5 ){
				System.out.println(initial+index);
				counter ++;	
			}
			else if(counter == 5) break;

		}
	}
	// Prime Counter

}





